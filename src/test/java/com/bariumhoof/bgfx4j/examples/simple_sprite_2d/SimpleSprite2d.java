package com.bariumhoof.bgfx4j.examples.simple_sprite_2d;

import com.bariumhoof.bgfx4j.Application;
import com.bariumhoof.bgfx4j.buffer.StaticIndexBuffer;
import com.bariumhoof.bgfx4j.buffer.StaticVertexBufferOld;
import com.bariumhoof.bgfx4j.buffer.VertexLayoutOld;
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

import java.io.IOException;
import java.util.EnumSet;

@Slf4j
public class SimpleSprite2d extends Application {

    final float[][] vertices = new float[][] {
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
    private StaticVertexBufferOld vb;
    private StaticIndexBuffer ib;
    private Uniform uniformTexColor;
    private Program program;

    private Texture tex;

    @Override
    public void render(float dt, float time) {
        final var encoder = Encoder.begin();

        view.setViewRect(0, 0, getWidth(), getHeight());
        encoder.touch(view);

        encoder.setTexture(0, uniformTexColor, tex);

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

        final VertexLayoutOld layout = VertexLayoutOld.builder()
                .beginWith(BGFX_ATTRIB.POSITION, 3, BGFX_ATTRIB_TYPE.FLOAT, true, false)
                .thenUseNormalizedAsInt(BGFX_ATTRIB.TEXCOORD0, 2, BGFX_ATTRIB_TYPE.FLOAT)
                .build();

        tex = Texture.loadOrNull(Bump.class.getResource("/textures/fieldstone-rgba.dds"));

        vb = StaticVertexBufferOld.create(layout, vertices);
        ib = StaticIndexBuffer.create(indices);

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
        new SimpleSprite2d().start();
    }
}










