package com.bariumhoof.bgfx4j.examples.textures;

import com.bariumhoof.bgfx4j.Application;
import com.bariumhoof.bgfx4j.enums.BGFX_ATTRIB;
import com.bariumhoof.bgfx4j.enums.BGFX_ATTRIB_TYPE;
import com.bariumhoof.bgfx4j.enums.BGFX_STATE;
import com.bariumhoof.bgfx4j.enums.BGFX_UNIFORM_TYPE;
import com.bariumhoof.bgfx4j.view.View;
import com.bariumhoof.bgfx4j.wip.*;
import lombok.extern.slf4j.Slf4j;
import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.joml.Vector4f;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;

import static org.lwjgl.bgfx.BGFX.bgfx_dbg_text_printf;
import static org.lwjgl.bgfx.BGFX.bgfx_encoder_end;

/**
 * Port of:
 * https://github.com/LWJGL/lwjgl3-demos/blob/master/src/org/lwjgl/demo/bgfx/Raymarch.java
 *
 * which itself is a port of:
 * https://github.com/bkaradzic/bgfx/blob/master/examples/03-raymarch/screenshot.png
 */
@Slf4j
public class Texture2d extends Application {

    private VertexLayout layout;
    private Program program;
    private Uniform uniformMtx;
    private Uniform uniformLightDirTime;

    private Matrix4f view = new Matrix4f();
    private FloatBuffer viewBuf;
    private Matrix4f proj = new Matrix4f();
    private FloatBuffer projBuf;

    private Matrix4f ortho = new Matrix4f();
    private FloatBuffer orthoBuf;
    private Matrix4f vp = new Matrix4f();
    private Matrix4f mtx = new Matrix4f();
    private Matrix4f mtxInv = new Matrix4f();
    private Matrix4f mvp = new Matrix4f();
    private Matrix4f invMvp = new Matrix4f();

    private ByteBuffer mtxBuf;
    private ByteBuffer lightDirTimeBuf;

    private View view0;
    private View view1;

    private void renderScreenSpaceQuad(Encoder encoder, View view, Program program, float _x, float _y, float _width, float _height) {
        try (MemoryStack stack = MemoryStack.stackPush()) {

            // todo clean up this system find better way (perhaps make buffer access enclosed in similar try-catch idiom
            //     to allow memStack efficient stack allocation behind the scenes
            final TransientBuffers buffers =
                    TransientBuffers.allocTransientBuffers(stack, layout, 4, 6);

            if (buffers != null) {
                final TransientVertexBuffer tvb = buffers.getTransientVertexBuffer();
                final TransientIndexBuffer tib = buffers.getTransientIndexBuffer();

                ByteBuffer vertex = tvb.data();

                float zz = 0.0f;

                float minx = _x;
                float maxx = _x + _width;
                float miny = _y;
                float maxy = _y + _height;

                float minu = -1.0f;
                float minv = -1.0f;
                float maxu = 1.0f;
                float maxv = 1.0f;

                vertex.putFloat(minx);
                vertex.putFloat(miny);
                vertex.putFloat(zz);
                vertex.putInt(0xff0000ff);
                vertex.putFloat(minu);
                vertex.putFloat(minv);

                vertex.putFloat(maxx);
                vertex.putFloat(miny);
                vertex.putFloat(zz);
                vertex.putInt(0xff00ff00);
                vertex.putFloat(maxu);
                vertex.putFloat(minv);

                vertex.putFloat(maxx);
                vertex.putFloat(maxy);
                vertex.putFloat(zz);
                vertex.putInt(0xffff0000);
                vertex.putFloat(maxu);
                vertex.putFloat(maxv);

                vertex.putFloat(minx);
                vertex.putFloat(maxy);
                vertex.putFloat(zz);
                vertex.putInt(0xffffffff);
                vertex.putFloat(minu);
                vertex.putFloat(maxv);

                ByteBuffer indices = tib.data();

                indices.putShort((short) 0);
                indices.putShort((short) 2);
                indices.putShort((short) 1);
                indices.putShort((short) 0);
                indices.putShort((short) 3);
                indices.putShort((short) 2);

                encoder.setState(BGFX_STATE.DEFAULT, 0);

                indices.flip();
                encoder.setTransientIndexBuffer(tib); // todo check if works without specifying exact size and start/end

                vertex.flip();
                encoder.setTransientVertexBuffer(tvb);

                encoder.submit(view, program);
            }
        }
    }

    @Override
    public void init() {
        layout = VertexLayout.builder()
                .beginWith(BGFX_ATTRIB.POSITION, BGFX_ATTRIB_TYPE.FLOAT)
                .thenUse(BGFX_ATTRIB.COLOR0, BGFX_ATTRIB_TYPE.UINT8, true, false)
                .thenUse(BGFX_ATTRIB.TEXCOORD0, BGFX_ATTRIB_TYPE.FLOAT)
                .build();

        uniformMtx = Uniform.createSingle("u_mtx", BGFX_UNIFORM_TYPE.VEC4);
        uniformLightDirTime = Uniform.createSingle("u_lightDirTime", BGFX_UNIFORM_TYPE.VEC4);

        program = Program.loadOrNull(
                locateVertexShaderByName("raymarching"),
                locateFragmentShaderByName("raymarching")
        );

        viewBuf = MemoryUtil.memAllocFloat(16);
        projBuf = MemoryUtil.memAllocFloat(16);

        orthoBuf = MemoryUtil.memAllocFloat(16);

        mtxBuf = MemoryUtil.memAlloc(16 * 4);
        lightDirTimeBuf = MemoryUtil.memAlloc(4 * 4);

        view0 = View.create("view0");
        view1 = View.create("view1");
    }


    @Override
    public void render(float dt, float time) {
        view1.setViewRect(0, 0, getWindowWidth(), getWindowHeight());
        view0.setViewRect(0, 0, getWindowWidth(), getWindowHeight());

        bgfx_dbg_text_printf(0, 1, 0x4f, "bgfx/examples/03-raymarch");
        bgfx_dbg_text_printf(0, 2, 0x6f, "Description: Updating shader uniforms.");
        bgfx_dbg_text_printf(0, 3, 0x0f, String.format("Frame: % 7.3f[ms]", dt));

        lookAt(new Vector3f(0.0f, 0.0f, 0.0f), new Vector3f(0.0f, 0.0f, -15.0f), view);
        perspective(60.0f, getWindowWidth(), getWindowHeight(), 0.1f, 100.0f, proj);

        view0.setTransform(view.get(viewBuf), proj.get(projBuf));

        ortho(0.0f, 1280.0f, 720.0f, 0.0f, 0.0f, 100.0f, ortho);
        view1.setViewTransform(null, ortho.get(orthoBuf));

        mtx.setRotationXYZ(time, time * 0.37f, 0.0f)
                .invert(mtxInv);

        Vector4f lightDirModel = new Vector4f(-0.4f, -0.5f, -1.0f, 0.0f);
        Vector4f lightDirModelN = new Vector4f();
        lightDirModel.normalize(lightDirModelN);

        Vector4f lightDirTime = new Vector4f();
        mtxInv.transform(lightDirModelN, lightDirTime);
        lightDirTime.w = time;

        final Encoder encoder = Encoder.begin(false);
        encoder.touch(view0);

        lightDirTime.get(lightDirTimeBuf);
        encoder.setUniform(uniformLightDirTime, lightDirTimeBuf);

        encoder.setUniform(uniformMtx, proj
                .mul(view, vp)
                .mul(mtx, mvp)
                .invert(invMvp)
                .get(mtxBuf));

        renderScreenSpaceQuad(encoder, view1, program, 0.0f, 0.0f, 1280.0f, 720.0f);
        bgfx_encoder_end(encoder.id());
    }

    @Override
    public void dispose() {
        MemoryUtil.memFree(viewBuf);
        MemoryUtil.memFree(projBuf);
        MemoryUtil.memFree(orthoBuf);
        MemoryUtil.memFree(mtxBuf);
        MemoryUtil.memFree(lightDirTimeBuf);

        program.dispose();
        uniformMtx.dispose();
        uniformLightDirTime.dispose();

        layout.dispose();
    }

    public static void main(String[] args) throws IOException {
        new Texture2d().start();
    }
}










