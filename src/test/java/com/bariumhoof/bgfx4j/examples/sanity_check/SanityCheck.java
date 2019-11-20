package com.bariumhoof.bgfx4j.examples.sanity_check;

import com.bariumhoof.bgfx4j.Application;
import com.bariumhoof.bgfx4j.enums.*;
import com.bariumhoof.bgfx4j.view.ClearStrategy;
import com.bariumhoof.bgfx4j.view.View;
import com.bariumhoof.bgfx4j.wip.IndexBuffer;
import com.bariumhoof.bgfx4j.wip.Program;
import com.bariumhoof.bgfx4j.wip.VertexBuffer;
import com.bariumhoof.bgfx4j.wip.VertexDecl;
import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.lwjgl.bgfx.BGFXMemory;
import org.lwjgl.bgfx.BGFXVertexDecl;
import org.lwjgl.system.MemoryUtil;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.util.Arrays;
import java.util.EnumSet;

import static org.lwjgl.bgfx.BGFX.*;

public class SanityCheck extends Application {

    private static final Number[][] cubeVertices = {
            {-1.0f, 1.0f, 1.0f, 0xff000000},
            {1.0f, 1.0f, 1.0f, 0xff0000ff},
            {-1.0f, -1.0f, 1.0f, 0xff00ff00},
            {1.0f, -1.0f, 1.0f, 0xff00ffff},
            {-1.0f, 1.0f, -1.0f, 0xffff0000},
            {1.0f, 1.0f, -1.0f, 0xffff00ff},
            {-1.0f, -1.0f, -1.0f, 0xffffff00},
            {1.0f, -1.0f, -1.0f, 0xffffffff}
    };

    private static final int[] cubeIndices = {
            0, 1, 2, // 0
            1, 3, 2,
            4, 6, 5, // 2
            5, 6, 7,
            0, 2, 4, // 4
            4, 2, 6,
            1, 5, 3, // 6
            5, 7, 3,
            0, 4, 1, // 8
            4, 5, 1,
            2, 3, 6, // 10
            6, 3, 7
    };

    //        private BGFXVertexLayout layout;
//        private VertexDecl layout;
    private BGFXVertexDecl layout;
    private ByteBuffer vertices;
    //        private VertexBuffer vertices;
    private short vbh;

    private ByteBuffer indices;
    //        private IndexBuffer indices;
    private short ibh;
    //        private short program;
    private Program program;
    private Matrix4f view = new Matrix4f();
    private FloatBuffer viewBuf;
    private Matrix4f proj = new Matrix4f();
    private FloatBuffer projBuf;
    private Matrix4f model = new Matrix4f();
    private FloatBuffer modelBuf;


    @Override
    public void init() {

//        layout = VertexDecl.builder(BGFX_RENDERER_TYPE.METAL)
//                .beginWith(BGFX_ATTRIB.POSITION, BGFX_ATTRIB_TYPE.FLOAT, false, false)
//                .thenUse(BGFX_ATTRIB.COLOR0, BGFX_ATTRIB_TYPE.UINT8, true, false)
//                .build();

        layout = createVertexLayout(    false, true, 0);

//        vertices = MemoryUtil.memAlloc(8 * (3 * 4 + 4));

        vertices = MemoryUtil.memAlloc(8 * (3 * 4 + 4));

        vbh = createVertexBuffer(vertices, layout, cubeVertices);
//        vertices = VertexBuffer.create(layout, cubeVertices);
//        vbh = vertices.handle();

        indices = MemoryUtil.memAlloc(cubeIndices.length * 2);
        ibh = createIndexBuffer(indices, cubeIndices);
//        indices = IndexBuffer.create(cubeIndices);
//        ibh = indices.handle();


//        short vs = BGFXDemoUtil.loadShader("vs_cubes");
//        short fs = BGFXDemoUtil.loadShader("fs_cubes");

        program = Program.loadOrNull(
                SanityCheck.class.getResource("/shaders/metal/cubes.vert"), // vertex shader
                SanityCheck.class.getResource("/shaders/metal/cubes.frag")  // fragment shader
        );

        viewBuf = MemoryUtil.memAllocFloat(16);
        projBuf = MemoryUtil.memAllocFloat(16);
        modelBuf = MemoryUtil.memAllocFloat(16);


    }

    @Override
    public void render(double dt) {
        bgfx_dbg_text_printf(0, 1, 0x4f, "bgfx/examples/01-cubes");
        bgfx_dbg_text_printf(0, 2, 0x6f, "Description: Rendering simple static mesh.");

//        BGFXDemoUtil.lookAt(new Vector3f(0.0f, 0.0f, 0.0f), new Vector3f(0.0f, 0.0f, -35.0f), view);
//        BGFXDemoUtil.perspective(60.0f, getWidth(), getHeight(), 0.1f, 100.0f, proj);
        lookAt(new Vector3f(0.0f, 0.0f, 0.0f), new Vector3f(0.0f, 0.0f, -35.0f), view);
        perspective(60.0f, getWidth(), getHeight(), 0.1f, 100.0f, proj);

        bgfx_set_view_transform(0, view.get(viewBuf), proj.get(projBuf));

                long encoder = bgfx_encoder_begin(false);
        for (int yy = 0; yy < 11; ++yy) {
            for (int xx = 0; xx < 11; ++xx) {
                bgfx_set_transform(
                        model.translation(
                                -15.0f + xx * 3.0f,
                                -15.0f + yy * 3.0f,
                                0.0f)
                                .rotateAffineXYZ(
                                        ((float) dt) + xx * 0.21f,
                                        ((float) dt) + yy * 0.37f,
                                        0.0f)
                                .get(modelBuf));

                bgfx_set_vertex_buffer(0, vbh, 0, 8);
                bgfx_set_index_buffer(ibh, 0, 36);

                bgfx_set_state( BGFX_STATE_DEFAULT, 0);

                bgfx_submit(0, program.handle(), 0, false);
            }
        }
        bgfx_encoder_end(encoder);
    }

    @Override
    public void dispose() {
//        if(m_ibh!=null){
//        m_ibh.dispose();
//        }

        MemoryUtil.memFree(viewBuf);
        MemoryUtil.memFree(projBuf);
        MemoryUtil.memFree(modelBuf);

        if (vertices != null) {
//                vertices.dispose();
        }

        if (indices != null) {
//                indices.dispose();
        }

        if (program != null) {
            program.dispose();
        }
    }

    public static void main(String[] args) throws IOException {
        new SanityCheck().start();
    }

    static BGFXVertexDecl createVertexLayout(boolean withNormals, boolean withColor, int numUVs) {

        BGFXVertexDecl layout = BGFXVertexDecl.calloc();

        bgfx_vertex_decl_begin(layout, BGFX_RENDERER_TYPE.METAL.VALUE);

        bgfx_vertex_decl_add(layout,
                BGFX_ATTRIB_POSITION,
                3,
                BGFX_ATTRIB_TYPE_FLOAT,
                false,
                false);

        if (withNormals) {
            bgfx_vertex_decl_add(layout,
                    BGFX_ATTRIB_NORMAL,
                    3,
                    BGFX_ATTRIB_TYPE_FLOAT,
                    false,
                    false);
        }

        if (withColor) {
            bgfx_vertex_decl_add(layout,
                    BGFX_ATTRIB_COLOR0,
                    4,
                    BGFX_ATTRIB_TYPE_UINT8,
                    true,
                    false);
        }

        if (numUVs > 0) {
            bgfx_vertex_decl_add(layout,
                    BGFX_ATTRIB_TEXCOORD0,
                    2,
                    BGFX_ATTRIB_TYPE_FLOAT,
                    false,
                    false);
        }

        bgfx_vertex_decl_end(layout);

        return layout;
    }

    static short createVertexBuffer(ByteBuffer buffer, BGFXVertexDecl layout, Object[][] vertices) {

        for (Object[] vtx : vertices) {
            for (Object attr : vtx) {
                if (attr instanceof Float) {
                    buffer.putFloat((float) attr);
                } else if (attr instanceof Integer) {
                    buffer.putInt((int) attr);
                } else {
                    throw new RuntimeException("Invalid parameter type");
                }
            }
        }

        if (buffer.remaining() != 0) {
            throw new RuntimeException("ByteBuffer size and number of arguments do not match");
        }

        buffer.flip();

        return createVertexBuffer(buffer, layout);
    }

    static short createVertexBuffer(ByteBuffer buffer, BGFXVertexDecl layout) {

        BGFXMemory vbhMem = bgfx_make_ref(buffer);

        return bgfx_create_vertex_buffer(vbhMem, layout, BGFX_BUFFER_NONE);
    }

    static short createIndexBuffer(ByteBuffer buffer, int[] indices) {

        for (int idx : indices) {
            buffer.putShort((short) idx);
        }

        if (buffer.remaining() != 0) {
            throw new RuntimeException("ByteBuffer size and number of arguments do not match");
        }

        buffer.flip();

        BGFXMemory ibhMem = bgfx_make_ref(buffer);

        return bgfx_create_index_buffer(ibhMem, BGFX_BUFFER_NONE);
    }

}
