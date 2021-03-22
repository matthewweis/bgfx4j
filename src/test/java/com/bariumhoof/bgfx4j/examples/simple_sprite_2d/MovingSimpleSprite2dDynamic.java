package com.bariumhoof.bgfx4j.examples.simple_sprite_2d;

import com.bariumhoof.bgfx4j.Application;
import com.bariumhoof.bgfx4j.buffer.DynamicIndexBuffer;
import com.bariumhoof.bgfx4j.buffer.DynamicVertexBufferOld;
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
import org.lwjgl.bgfx.BGFX;
import org.lwjgl.bgfx.BGFXMemory;
import org.lwjgl.system.MemoryStack;

import java.io.IOException;
import java.util.EnumSet;
import java.util.Random;
import java.util.function.Supplier;

import static com.bariumhoof.bgfx4j.enums.BGFX_BUFFER.NONE;

/**
 * Same as {@link MovingSimpleSprite2d} except uses heap allocated Transient buffers.
 *
 * Since TransientBuffers are designed to be changed every frame,
 * one should always try to use a {@link MemoryStack} to allocate them.
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



//    final FloatBuffer verticesBuffer = FloatBuffer.wrap(vertices);

    final short[] indices = new short[] {
            0, 2, 3,
            1, 0, 3,
    };

//    final IntBuffer indicesBuffer = IntBuffer.wrap(indices);

    //        final Supplier<BGFXMemory> vSupplier = () -> Memory.alloc(vertices.length * Float.BYTES).getBgfxMemory();
    final Supplier<BGFXMemory> vSupplier = () -> BGFXMemory.calloc();
    //        final Supplier<BGFXMemory> iSupplier = () -> Memory.alloc(indices.length * Short.BYTES).getBgfxMemory();
    final Supplier<BGFXMemory> iSupplier = () -> BGFXMemory.calloc();


    private View view;
    private VertexLayoutOld layout;
    private Uniform uniformTexColor;
    private Program program;
    private DynamicVertexBufferOld vb;
    private DynamicIndexBuffer ib;

    BGFXMemory v_mem;
    BGFXMemory i_mem;



    private Texture tex;

    int frame = 0;

    @Override
    public void render(float dt, float time) {

        final var encoder = Encoder.begin();

        view.setViewRect(0, 0, getWidth(), getHeight());
        encoder.touch(view);

        encoder.setTexture(0, uniformTexColor, tex);

        vb.update(v_mem);
        ib.update(i_mem);

        encoder.setState(EnumSet.of(BGFX_STATE.WRITE_RGB, BGFX_STATE.WRITE_A));

        encoder.setDynamicVertexBuffer(vb, 0, 4);
        encoder.setDynamicIndexBuffer(ib, 0, 6);

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

        layout = VertexLayoutOld.builder()
                .beginWith(BGFX_ATTRIB.POSITION, 3, BGFX_ATTRIB_TYPE.FLOAT, true, false)
//                .thenUseNormalizedAsInt(BGFX_ATTRIB.TEXCOORD0, 2, BGFX_ATTRIB_TYPE.FLOAT)
                .thenUseNormalized(BGFX_ATTRIB.TEXCOORD0, 2, BGFX_ATTRIB_TYPE.FLOAT)
                .build();

        tex = Texture.loadOrNull(Bump.class.getResource("/textures/fieldstone-rgba.dds"));

        vb = DynamicVertexBufferOld.create(layout, numVerts, EnumSet.of(NONE));
        ib = DynamicIndexBuffer.create(indices.length, EnumSet.of(NONE));

        uniformTexColor = Uniform.createSingle("s_texColor", BGFX_UNIFORM_TYPE.VEC4);
        program = Program.loadOrNull(
                Application.locateVertexShaderByName("simple-texture"), // vertex shader
                Application.locateFragmentShaderByName("simple-texture")); // fragment shader

        v_mem = BGFX.bgfx_alloc(vertices.length * Float.BYTES);
        i_mem = BGFX.bgfx_alloc(indices.length * Short.BYTES);
//        v_mem = vSupplier.get();
//        i_mem = iSupplier.get();

//        v_mem =  new BGFXMemory[] { vSupplier.get(), vSupplier.get(), vSupplier.get() };
//        i_mem = new BGFXMemory[] { iSupplier.get(), iSupplier.get(), iSupplier.get() };
    }

    @Override
    public void dispose() {
        tex.dispose();
    }

    public static void main(String[] args) throws IOException {
        new MovingSimpleSprite2dDynamic().start();
    }
}










