package com.bariumhoof.bgfx4j.examples.simple_sprite_2d;

import com.bariumhoof.bgfx4j.Application;
import com.bariumhoof.bgfx4j.buffer.DynamicIndexBuffer;
import com.bariumhoof.bgfx4j.buffer.DynamicVertexBuffer;
import com.bariumhoof.bgfx4j.buffer.VertexLayout;
import com.bariumhoof.bgfx4j.enums.*;
import com.bariumhoof.bgfx4j.examples._06_bump.Bump;
import com.bariumhoof.bgfx4j.shaders.Program;
import com.bariumhoof.bgfx4j.view.View;
import com.bariumhoof.bgfx4j.wip.Encoder;
import com.bariumhoof.bgfx4j.wip.Memory;
import com.bariumhoof.bgfx4j.wip.Texture;
import com.bariumhoof.bgfx4j.wip.Uniform;
import lombok.extern.slf4j.Slf4j;
import org.lwjgl.system.MemoryStack;

import java.io.IOException;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.EnumSet;
import java.util.Random;

/**
 * Same as {@link MovingSimpleSprite2d} except uses heal allocated Transient buffers.
 *
 * Since TransientBuffers are designed to be changed every frame, one should always try to use a {@link MemoryStack}
 * instead.
 */
@Slf4j
public class MovingSimpleSprite2dDynamic extends Application {

    private final static Random random = new Random();

    final int numVerts = 4;
    final float[] vertices = new float[] {
            -0.5f, -0.5f, 0.0f, /* <- pos, texture coord -> */ 0.0f, 0.0f,
            0.5f, -0.5f, 0.0f, /* <- pos, texture coord -> */ 1.0f, 0.0f,
            -0.5f,  0.5f, 0.0f, /* <- pos, texture coord -> */ 0.0f, 1.0f,
            0.5f,  0.5f, 0.0f, /* <- pos, texture coord -> */ 1.0f, 1.0f,
    };



    final FloatBuffer verticesBuffer = FloatBuffer.wrap(vertices);

    final int[] indices = new int[] {
            0, 2, 3,
            1, 0, 3,
    };

    final IntBuffer indicesBuffer = IntBuffer.wrap(indices);


    private View view;
    private VertexLayout layout;
    private Uniform uniformTexColor;
    private Program program;
    private DynamicVertexBuffer vb;
    private DynamicIndexBuffer ib;


    private Texture tex;

    public MovingSimpleSprite2dDynamic() {
        super(defaultInitBuilder().build());
    }

    int x = 0;

    @Override
    public void render(float dt, float time) {

        final var encoder = Encoder.begin();

        view.setViewRect(0, 0, getWidth(), getHeight());
        encoder.touch(view);


        encoder.setTexture(0, uniformTexColor, tex);

//        for (float[] vert : vertices) {
//            vert[0] = vert[0].floatValue() + smallRandom();
//            vert[1] = vert[1].floatValue() + smallRandom();
//        }

        if (x % 20 != 0) {
            final Memory v_mem = Memory.copy(vertices);
            vb.update(v_mem.getBgfxMemory());

            final Memory i_mem = Memory.copy(indices);
            ib.update(i_mem.getBgfxMemory());

            encoder.setState(EnumSet.of(BGFX_STATE.WRITE_RGB, BGFX_STATE.WRITE_A));

            encoder.setDynamicVertexBuffer(vb, 0, 4);

            encoder.setDynamicIndexBuffer(ib, 0, 6);

            // Submit primitive for rendering to view 0.
            encoder.submit(view, program);
        } else {
            x++;
        }


//        vb.alloc(layout, vertices.length); // make valid for frame
//        ib.alloc(indices.length);
//
//        ByteBuffer vertex = vb.data();
//        for (Number[] vert : vertices) {
//            for (Number number : vert) {
//                vertex.putFloat(number.floatValue());
//            }
//        }
//
//        final ByteBuffer indicesBuf = ib.data(); // EACH CALL TO ib.data starts at 0 position!
//        for (int index : indices) {
//            indicesBuf.putShort((short)index);
//        }



        encoder.end();
    }

    private static float smallRandom() {
        return (random.nextFloat() - 0.5f) * 0.05f;
    }

    @Override
    public void init() {
        view = View.create();

        layout = VertexLayout.builder()
                .beginWith(BGFX_ATTRIB.POSITION, 3, BGFX_ATTRIB_TYPE.FLOAT, true, false)
//                .thenUseNormalizedAsInt(BGFX_ATTRIB.TEXCOORD0, 2, BGFX_ATTRIB_TYPE.FLOAT)
                .thenUseNormalized(BGFX_ATTRIB.TEXCOORD0, 2, BGFX_ATTRIB_TYPE.FLOAT)
                .build();

        tex = Texture.loadOrNull(Bump.class.getResource("/textures/fieldstone-rgba.dds"));

        vb = DynamicVertexBuffer.create(layout, numVerts, EnumSet.of(BGFX_BUFFER.NONE));
        ib = DynamicIndexBuffer.create(indices.length, EnumSet.of(BGFX_BUFFER.NONE));

        uniformTexColor = Uniform.createSingle("s_texColor", BGFX_UNIFORM_TYPE.VEC4);
        program = Program.loadOrNull(
                Application.locateVertexShaderByName("simple-texture"), // vertex shader
                Application.locateFragmentShaderByName("simple-texture")); // fragment shader
    }

    @Override
    public void dispose() {
        tex.dispose();
    }

    public static void main(String[] args) throws IOException {
        new MovingSimpleSprite2dDynamic().start();
    }
}










