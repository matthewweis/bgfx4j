package com.bariumhoof.bgfx4j.examples.simple_sprite_2d;

import com.bariumhoof.bgfx4j.Application;
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

/**
 * Same as {@link MovingSimpleSprite2d} except uses heal allocated Transient buffers.
 *
 * Since TransientBuffers are designed to be changed every frame, one should always try to use a {@link MemoryStack}
 * instead.
 */
@Slf4j
public class MovingSimpleSprite2dSlow extends Application {

    private final static Random random = new Random();

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
    private TransientVertexBuffer vb;
    private TransientIndexBuffer ib;


    private Texture tex;

    public MovingSimpleSprite2dSlow() {
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

        vb.alloc(layout, vertices.length); // make valid for frame
        ib.alloc(indices.length);

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

        vb.data().flip();
        encoder.setTransientVertexBuffer(vb);

        ib.data().flip();
        encoder.setTransientIndexBuffer(ib);

        // Submit primitive for rendering to view 0.
        encoder.submit(view, program);

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
                .thenUseNormalizedAsInt(BGFX_ATTRIB.TEXCOORD0, 2, BGFX_ATTRIB_TYPE.FLOAT)
                .build();

        tex = Texture.loadOrNull(Bump.class.getResource("/textures/fieldstone-rgba.dds"));

        vb = TransientVertexBuffer.heapCreate();
        ib = TransientIndexBuffer.heapCreate();

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
        new MovingSimpleSprite2dSlow().start();
    }
}










