package com.bariumhoof.bgfx4j.examples.simple_sprite_2d;

import com.bariumhoof.bgfx4j.Application;
import com.bariumhoof.bgfx4j.buffer.DynamicIndexBuffer;
import com.bariumhoof.bgfx4j.buffer.DynamicVertexBufferOld;
import com.bariumhoof.bgfx4j.buffer.VertexLayoutOld;
import com.bariumhoof.bgfx4j.enums.*;
import com.bariumhoof.bgfx4j.examples._06_bump.Bump;
import com.bariumhoof.bgfx4j.shaders.Program;
import com.bariumhoof.bgfx4j.view.View;
import com.bariumhoof.bgfx4j.wip.Encoder;
import com.bariumhoof.bgfx4j.wip.Texture;
import com.bariumhoof.bgfx4j.wip.Uniform;
import lombok.extern.slf4j.Slf4j;
import org.lwjgl.bgfx.BGFXMemory;

import java.io.IOException;
import java.util.EnumSet;

import static org.lwjgl.bgfx.BGFX.bgfx_copy;

@Slf4j
public class SimpleSprite2dDynamic extends Application {

    final Number[][] vertices = new Number[][] {
            { -0.5f, -0.5f, 0.0f, /* <- pos, texture coord -> */ 0.0f, 0.0f },
            {  0.5f, -0.5f, 0.0f, /* <- pos, texture coord -> */ 1.0f, 0.0f },
            { -0.5f,  0.5f, 0.0f, /* <- pos, texture coord -> */ 0.0f, 1.0f },
            {  0.5f,  0.5f, 0.0f, /* <- pos, texture coord -> */ 1.0f, 1.0f },
    };

    final float[]vs = new float[]{
             -0.5f, -0.5f, 0.0f, /* <- pos, texture coord -> */ 0.0f, 0.0f ,
              0.5f, -0.5f, 0.0f, /* <- pos, texture coord -> */ 1.0f, 0.0f ,
             -0.5f,  0.5f, 0.0f, /* <- pos, texture coord -> */ 0.0f, 1.0f ,
              0.5f,  0.5f, 0.0f, /* <- pos, texture coord -> */ 1.0f, 1.0f ,
    };

    final short[] indices = new short[] {
            0, 2, 3,
            1, 0, 3,
    };

    private View view;
    private DynamicVertexBufferOld vb;
    private DynamicIndexBuffer ib;
    private Uniform uniformTexColor;
    private Program program;

    private Texture tex;

    boolean first = true;

    @Override
    public void render(float dt, float time) {


        final var encoder = Encoder.begin();

        view.setViewRect(0, 0, getWidth(), getHeight());
        encoder.touch(view);

        encoder.setTexture(0, uniformTexColor, tex);

//        final BGFXMemory memory = bgfx_copy(vs);

        if (first) {
//            final FloatBuffer malloc1 = MemoryUtil.memAllocFloat(vs.length);
//            final ShortBuffer malloc2 = MemoryUtil.memAllocShort(indices.length);
//
//            for (int i=0; i < vs.length; i++) {
//                malloc1.put(vs[i]);
//            }
//            malloc1.flip();
//
//            for (int i=0; i < indices.length; i++) {
//                malloc2.put((short) indices[i]);
//            }
//            malloc2.flip();
//
//            vb.update(bgfx_make_ref(malloc1));
//            ib.update(bgfx_make_ref(malloc2));
            final BGFXMemory vmem = bgfx_copy(vs);
            final BGFXMemory imem = bgfx_copy(indices);

            vb.update(vmem);
            ib.update(imem);

//            first = false;
        }

        for (int i=0; i < vs.length; i++) {
            vs[i] += (Math.random() - 0.5f) * 0.1f;
        }


        encoder.setDynamicVertexBuffer(vb);
        encoder.setDynamicIndexBuffer(ib);

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
//                .thenUseNormalizedAsInt(BGFX_ATTRIB.TEXCOORD0, 2, BGFX_ATTRIB_TYPE.FLOAT)
                .thenUseNormalized(BGFX_ATTRIB.TEXCOORD0, 2, BGFX_ATTRIB_TYPE.FLOAT)
                .build();

        tex = Texture.loadOrNull(Bump.class.getResource("/textures/fieldstone-rgba.dds"));

        vb = DynamicVertexBufferOld.create(layout, vertices.length, EnumSet.of(BGFX_BUFFER.NONE));
//        ib = DynamicIndexBuffer.create(indices);
        ib = DynamicIndexBuffer.create(indices.length, EnumSet.of(BGFX_BUFFER.NONE));

//        vb = DynamicVertexBuffer.create(layout, vertices);

        final BGFXMemory vmem = bgfx_copy(vs);
        final BGFXMemory imem = bgfx_copy(indices);

        vb.update(vmem);
        ib.update(imem);

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
        new SimpleSprite2dDynamic().start();
    }
}










