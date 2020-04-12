package com.bariumhoof.bgfx4j.examples.textures;

import com.bariumhoof.bgfx4j.Application;
import com.bariumhoof.bgfx4j.buffer.IndexBuffer;
import com.bariumhoof.bgfx4j.buffer.VertexBuffer;
import com.bariumhoof.bgfx4j.buffer.VertexLayout;
import com.bariumhoof.bgfx4j.enums.*;
import com.bariumhoof.bgfx4j.examples._00_hello_world.Logo;
import com.bariumhoof.bgfx4j.examples._06_bump.Bump;
import com.bariumhoof.bgfx4j.shaders.Program;
import com.bariumhoof.bgfx4j.view.View;
import com.bariumhoof.bgfx4j.wip.*;
import lombok.extern.slf4j.Slf4j;
import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.lwjgl.bgfx.BGFX;
import org.lwjgl.bgfx.BGFXMemory;
import org.lwjgl.system.MemoryUtil;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.util.EnumSet;
import java.util.Random;

import static com.bariumhoof.bgfx4j.wip.DebugColor.*;

@Slf4j
public class Texture2dTest extends Application {

    private static final String WS = "    "; // whitespace string
    private static final ByteBuffer logo = Logo.create();

    private Matrix4f viewMat = new Matrix4f();
    private FloatBuffer viewBuf;
    private Matrix4f projMat = new Matrix4f();
    private FloatBuffer projBuf;
//    private Matrix4f modelMat = new Matrix4f();
//    private FloatBuffer modelBuf;

    final float[][] verts = new float[][] {
            // make 0.5s into 1.0s to cover whole screen
//            { -0.5f, -0.5f, 0.0f, /* <- pos, texcoord -> */ 0.0f, 0.0f },
//            {  0.5f, -0.5f, 0.0f, /* <- pos, texcoord -> */ 1.0f, 0.0f },
//            { -0.5f,  0.5f, 0.0f, /* <- pos, texcoord -> */ 0.0f, 1.0f },
//            {  0.5f,  0.5f, 0.0f, /* <- pos, texcoord -> */ 1.0f, 1.0f },

            { -1.0f, -1.0f, 0.0f, /* <- pos, texcoord -> */ 0.0f, 0.0f },
            {  1.0f, -1.0f, 0.0f, /* <- pos, texcoord -> */ 1.0f, 0.0f },
            { -1.0f,  1.0f, 0.0f, /* <- pos, texcoord -> */ 0.0f, 1.0f },
            {  1.0f,  1.0f, 0.0f, /* <- pos, texcoord -> */ 1.0f, 1.0f },
    };

    final int[] indices = new int[] {
            0, 2, 3,
            1, 0, 3,
    };

    private View view;
    private VertexLayout layout;
    private VertexBuffer vb;
    private IndexBuffer ib;
    private Uniform uniformTexColor;
    private Program program;
    private TextureFlags textureFlags;
    private Texture2D tex;

    private Texture textureColor;

    final int dim = 64;
    final int size = dim * dim;
    final ByteBuffer backingOffHeapMemory = MemoryUtil.memAlloc(Integer.BYTES * size);

    public Texture2dTest() {
        super(defaultInitBuilder().type(BGFX_RENDERER_TYPE.OPENGL).build());
    }

    int x = 0;

    @Override
    public void render(float dt, float time) {
        final var encoder = Encoder.begin();

        Vector3f eye = new Vector3f(0.0f, 0.0f, -7.0f);

        lookAt(new Vector3f(0.0f, 0.0f, 0.0f), eye, viewMat);
        perspective(60.0f, getWidth(), getHeight(), 0.1f, 100.0f, projMat);

        view.setTransform(viewMat.get(viewBuf), projMat.get(projBuf));

        view.setViewRect(0, 0, getWidth(), getHeight());
        encoder.touch(view);
        encoder.debugTextClear();

        final int logo_x = Math.max(getWidth() / 2 / 8, 20) - 20;
        final int logo_y = Math.max(getHeight() / 2 / 16, 6) - 6;
        final int logo_width = 40;
        final int logo_height = 12;
        final int logo_pitch = 160;

        encoder.debugTextImage(logo_x, logo_y, logo_width, logo_height, logo, logo_pitch);

        encoder.debugTextPrintf(0, 1, BLUE, WHITE, "bgfx/examples/25-c99");
        encoder.debugTextPrintf(0, 2, CYAN, WHITE, "Description: Initialization and debug text with C99 API.");

        encoder.debugTextPrintf(0, 3, COLORED_DESCRIPTION_STRING);
        encoder.debugTextPrintf(80, 4, COLOR_PALETTE_ROW_1);
        encoder.debugTextPrintf(80, 5, COLOR_PALETTE_ROW_2);

        if (x % 15 == 0) {

//            final Memory mem = Memory.alloc(16 * Integer.BYTES);
            var r = new Random();

            System.out.println("before: " + backingOffHeapMemory.position());
            for (int i=0; i < size; i++) {
                final int value = r.nextInt();
                backingOffHeapMemory.putInt(value); // does in fact advance position (by 4)
            }
            System.out.println("after: " + backingOffHeapMemory.position()); // is at end (16384)
            backingOffHeapMemory.flip();
            System.out.println("after flip: " + backingOffHeapMemory.position()); // resets to 0


            System.out.println("update");
//            final BGFXMemory mem = BGFX.bgfx_alloc(size * Integer.BYTES);
            final BGFXMemory mem = BGFX.bgfx_make_ref(backingOffHeapMemory);

            tex.update(mem, 0, 0, dim, dim);
        }

        x++; // temp since we aren't freeing but may need n frames

//        lookAt(new Vector3f(0.0f, 0.0f, 0.0f), new Vector3f(0.0f, 0.0f, -35.0f), viewMat);
//        projMat.ortho2D(-1.0f, 1.0f, -1.0f, 1.0f);

//        view.setTransform(viewMat.get(viewBuf), projMat.get(projBuf));
//        encoder.setTransform(); encoder transform is for model not view/proj

        encoder.setTexture(0, uniformTexColor, textureColor);
//        encoder.setTexture(0, uniformTexColor, tex);

        encoder.setVertexBuffer(vb);
        encoder.setIndexBuffer(ib);

        // Set render states.
        encoder.setState(EnumSet.of(BGFX_STATE.WRITE_RGB, BGFX_STATE.WRITE_A));

        // Submit primitive for rendering to view 0.
        encoder.submit(view, program, false);

        encoder.end();

    }

    @Override
    public void init() {
        view = View.create();

//        layout = VertexLayout.builder()
//                .beginWith(BGFX_ATTRIB.POSITION, BGFX_ATTRIB_TYPE.FLOAT, false, false)
////                .thenUseNormalizedAsInt(BGFX_ATTRIB.TEXCOORD0, BGFX_ATTRIB_TYPE.INT16)
//                .thenUseNormalized(BGFX_ATTRIB.TEXCOORD0, BGFX_ATTRIB_TYPE.UINT8)
//                .build();

        layout = VertexLayout.builder()
                .beginWith(BGFX_ATTRIB.POSITION, 3, BGFX_ATTRIB_TYPE.FLOAT, true, false)
//                .thenUseNormalizedAsInt(BGFX_ATTRIB.NORMAL, BGFX_ATTRIB_TYPE.UINT8)
//                .thenUseNormalizedAsInt(BGFX_ATTRIB.TANGENT, BGFX_ATTRIB_TYPE.UINT8)
                .thenUseNormalizedAsInt(BGFX_ATTRIB.TEXCOORD0, 2, BGFX_ATTRIB_TYPE.FLOAT)
                .build();

        textureColor = Texture.loadOrNull(Bump.class.getResource("/textures/fieldstone-rgba.dds"));

        vb = VertexBuffer.create(layout, verts);
        ib = IndexBuffer.create(indices);

        uniformTexColor = Uniform.createSingle("s_texColor", BGFX_UNIFORM_TYPE.VEC4);
        program = Program.loadOrNull(
                Application.locateVertexShaderByName("simple-texture"), // vertex shader
                Application.locateFragmentShaderByName("simple-texture")); // fragment shader

        textureFlags = TextureFlags.create(EnumSet.of(BGFX_TEXTURE.NONE), EnumSet.of(BGFX_SAMPLER.NONE));
        tex = Texture2D.createMutableEmpty(64, 64, false, 1, BGFX_TEXTURE_FORMAT.RGBA8, textureFlags);

        viewBuf = MemoryUtil.memAllocFloat(16);
        projBuf = MemoryUtil.memAllocFloat(16);
    }

    @Override
    public void dispose() {
        MemoryUtil.memFree(backingOffHeapMemory);
        MemoryUtil.memFree(viewBuf);
        MemoryUtil.memFree(projBuf);
        tex.dispose();
    }

    private static final String COLORED_DESCRIPTION_STRING = Debug.buildColoredString()
            .foreground(WHITE)
            .text("Color can be changed with ANSI ").foreground(BRIGHT_BLUE)
            .text("e").foreground(BRIGHT_GREEN)
            .text("s").foreground(BRIGHT_CYAN)
            .text("c").foreground(BRIGHT_RED)
            .text("a").foreground(BRIGHT_MAGENTA)
            .text("p").foreground(YELLOW)
            .text("e").foreground(WHITE)
            .text(" code too.").build();

    private static final String COLOR_PALETTE_ROW_1 = Debug.buildColoredString()
            .background(BLACK)
            .text(WS).background(BLUE)
            .text(WS).background(GREEN)
            .text(WS).background(CYAN)
            .text(WS).background(RED)
            .text(WS).background(MAGENTA)
            .text(WS).background(BROWN)
            .text(WS).background(GREY)
            .text(WS).background(BLACK)
            .text(WS).build();

    private static final String COLOR_PALETTE_ROW_2 = Debug.buildColoredString()
            .background(DARK_GREY)
            .text(WS).background(BRIGHT_BLUE)
            .text(WS).background(BRIGHT_GREEN)
            .text(WS).background(BRIGHT_CYAN)
            .text(WS).background(BRIGHT_RED)
            .text(WS).background(BRIGHT_MAGENTA)
            .text(WS).background(YELLOW)
            .text(WS).background(WHITE)
            .text(WS).background(BLACK)
            .text(WS).build();

    private static class PosTexcoord {
        float[] m_xyz = new float[4];
        float[] m_uv = new float[4];
    }

//    private static ByteBuffer calcTangents(Object[][] _vertices) {
//
////        float[] out = new float[4];
////        float[] tangents = new float[6 * _numVertices];
////
////        PosTexcoord v0 = new PosTexcoord();
////        PosTexcoord v1 = new PosTexcoord();
////        PosTexcoord v2 = new PosTexcoord();
////
////        ByteBuffer vertices = MemoryUtil.memAlloc(_numVertices * 6 * 4);
////        for (Object[] vv : _vertices) {
////            vertices.putFloat((float) vv[0]);
////            vertices.putFloat((float) vv[1]);
////            vertices.putFloat((float) vv[2]);
////            vertices.putInt((int) vv[3]);
////            vertices.putInt((int) vv[4]);
////            vertices.putShort((short) (int) vv[5]);
////            vertices.putShort((short) (int) vv[6]);
////        }
////
////        vertices.flip();
//
////        for (int ii = 0, num = _numIndices / 3; ii < num; ++ii) {
////            int index0 = ii * 3;
////            int i0 = _indices[index0];
////            int i1 = _indices[index0 + 1];
////            int i2 = _indices[index0 + 2];
////
////            bgfx_vertex_unpack(v0.m_xyz, BGFX_ATTRIB_POSITION, _layout, vertices, i0);
////            bgfx_vertex_unpack(v0.m_uv, BGFX_ATTRIB_TEXCOORD0, _layout, vertices, i0);
////
////            bgfx_vertex_unpack(v1.m_xyz, BGFX_ATTRIB_POSITION, _layout, vertices, i1);
////            bgfx_vertex_unpack(v1.m_uv, BGFX_ATTRIB_TEXCOORD0, _layout, vertices, i1);
////
////            bgfx_vertex_unpack(v2.m_xyz, BGFX_ATTRIB_POSITION, _layout, vertices, i2);
////            bgfx_vertex_unpack(v2.m_uv, BGFX_ATTRIB_TEXCOORD0, _layout, vertices, i2);
////        }
//
////        return vertices;
//    }

    public static void main(String[] args) throws IOException {
        new Texture2dTest().start();
    }
}










