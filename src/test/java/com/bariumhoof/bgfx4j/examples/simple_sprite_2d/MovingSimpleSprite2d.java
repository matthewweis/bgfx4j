package com.bariumhoof.bgfx4j.examples.simple_sprite_2d;

import com.bariumhoof.bgfx4j.Application;
import com.bariumhoof.bgfx4j.buffer.TransientBuffers;
import com.bariumhoof.bgfx4j.buffer.TransientIndexBuffer;
import com.bariumhoof.bgfx4j.buffer.TransientVertexBuffer;
import com.bariumhoof.bgfx4j.buffer.VertexLayout;
import com.bariumhoof.bgfx4j.enums.BGFX_ATTRIB;
import com.bariumhoof.bgfx4j.enums.BGFX_ATTRIB_TYPE;
import com.bariumhoof.bgfx4j.enums.BGFX_STATE;
import com.bariumhoof.bgfx4j.enums.BGFX_UNIFORM_TYPE;
import com.bariumhoof.bgfx4j.examples._06_bump.Bump;
import com.bariumhoof.bgfx4j.shaders.Program;
import com.bariumhoof.bgfx4j.view.View;
import com.bariumhoof.bgfx4j.wip.Encoder;
import com.bariumhoof.bgfx4j.wip.Texture;
import com.bariumhoof.bgfx4j.wip.Uniform;
import lombok.extern.slf4j.Slf4j;
import org.lwjgl.system.MemoryStack;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.EnumSet;
import java.util.Random;

@Slf4j
public class MovingSimpleSprite2d extends Application {

    final Number[][] vertices = new Number[][] {
            { -0.5f, -0.5f, 0.0f, /* <- pos, texture coord -> */ 0.0f, 0.0f },
            {  0.5f, -0.5f, 0.0f, /* <- pos, texture coord -> */ 1.0f, 0.0f },
            { -0.5f,  0.5f, 0.0f, /* <- pos, texture coord -> */ 0.0f, 1.0f },
            {  0.5f,  0.5f, 0.0f, /* <- pos, texture coord -> */ 1.0f, 1.0f },
    };

    final int[] indices = new int[] {
            0, 2, 3,
            1, 0, 3,
    };

    private View view;
    private VertexLayout layout;
    private Uniform uniformTexColor;
    private Program program;

    private final static Random r = new Random();

    private Texture tex;

    public MovingSimpleSprite2d() {
        super(defaultInitBuilder().build());
    }

    @Override
    public void render(float dt, float time) {
        final var encoder = Encoder.begin();

        view.setViewRect(0, 0, getWidth(), getHeight());
        encoder.touch(view);

        encoder.setTexture(0, uniformTexColor, tex);

        for (Number[] vert : vertices) {
            vert[0] = vert[0].floatValue() + smallRandom();
            vert[1] = vert[1].floatValue() + smallRandom();
        }

        try (final MemoryStack memoryStack = MemoryStack.stackPush()) {

            // vertCount means number of vertexes in general. Not including number of components per vert (that is what layout is for)
            final TransientBuffers transientBuffers = TransientBuffers.alloc(layout, vertices.length, indices.length, memoryStack);

            if (transientBuffers != null) {
                final TransientVertexBuffer vb = transientBuffers.getTransientVertexBuffer();
                final TransientIndexBuffer ib = transientBuffers.getTransientIndexBuffer();

                ByteBuffer vertex = vb.data();
                for (Number[] vert : vertices) {
                    for (Number number : vert) {
                        vertex.putFloat(number.floatValue());
                    }
                }

                final ByteBuffer indicesBuf = ib.data(); // EACH CALL TO ib.data starts at 0 position!
                for (int index : indices) {
                    indicesBuf.putShort((short)index);
                }

                encoder.setState(EnumSet.of(BGFX_STATE.WRITE_RGB, BGFX_STATE.WRITE_A));

                vertex.flip();
                encoder.setTransientVertexBuffer(vb);

                indicesBuf.flip();
                encoder.setTransientIndexBuffer(ib);

                // Submit primitive for rendering to view 0.
                encoder.submit(view, program);

                encoder.end();
            }
        }
    }

    private static float smallRandom() {
        return (r.nextFloat() - 0.5f) * 0.05f;
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
        new MovingSimpleSprite2d().start();
    }
}










