package com.bariumhoof.bgfx4j.examples._01_cubes;

import com.bariumhoof.bgfx4j.Application;
import com.bariumhoof.bgfx4j.encoder.Encoder;
import com.bariumhoof.bgfx4j.enums.*;
import com.bariumhoof.bgfx4j.view.ClearStrategy;
import com.bariumhoof.bgfx4j.view.View;
import com.bariumhoof.bgfx4j.wip.IndexBuffer;
import com.bariumhoof.bgfx4j.wip.Program;
import com.bariumhoof.bgfx4j.wip.VertexBuffer;
import com.bariumhoof.bgfx4j.wip.VertexDecl;
import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.lwjgl.system.MemoryUtil;

import java.io.IOException;
import java.nio.FloatBuffer;
import java.util.EnumSet;

import static org.lwjgl.bgfx.BGFX.*;

public class Cubes extends Application {

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

    private VertexDecl layout;
    private VertexBuffer vertices;
    private IndexBuffer indices;
    private Program program;

    private Matrix4f view = new Matrix4f();
    private View bgfxView;
    private FloatBuffer viewBuf;
    private Matrix4f proj = new Matrix4f();
    private FloatBuffer projBuf;
    private Matrix4f model = new Matrix4f();
    private FloatBuffer modelBuf;

    @Override
    public void init() {

        layout = VertexDecl.builder(BGFX_RENDERER_TYPE.METAL)
                .beginWith(BGFX_ATTRIB.POSITION, BGFX_ATTRIB_TYPE.FLOAT, false, false)
                .thenUse(BGFX_ATTRIB.COLOR0, BGFX_ATTRIB_TYPE.UINT8, true, false)
                .build();

        vertices = VertexBuffer.create(layout, cubeVertices);
        indices = IndexBuffer.create(cubeIndices);

        program = Program.loadOrNull(
                Cubes.class.getResource("/shaders/metal/cubes.vert"), // vertex shader
                Cubes.class.getResource("/shaders/metal/cubes.frag")  // fragment shader
        );

        bgfxView = View.create("my view");

        viewBuf = MemoryUtil.memAllocFloat(16);
        projBuf = MemoryUtil.memAllocFloat(16);
        modelBuf = MemoryUtil.memAllocFloat(16);
    }

    @Override
    public void render(float dt, float time) {
        bgfx_dbg_text_printf(0, 1, 0x4f, "bgfx/examples/01-cubes");
        bgfx_dbg_text_printf(0, 2, 0x6f, "Description: Rendering simple static mesh.");

        lookAt(new Vector3f(0.0f, 0.0f, 0.0f), new Vector3f(0.0f, 0.0f, -35.0f), view);
        perspective(60.0f, getWidth(), getHeight(), 0.1f, 100.0f, proj);

        bgfxView.setTransform(view.get(viewBuf), proj.get(projBuf)); // todo make view work more like a camera...

        Encoder encoder = Encoder.begin(false);
        for (int yy = 0; yy < 11; ++yy) {
            for (int xx = 0; xx < 11; ++xx) {
                encoder.setTransform(model.translation(-15.0f + xx * 3.0f, -15.0f + yy * 3.0f, 0.0f)
                        .rotateAffineXYZ(time + xx * 0.21f, time + yy * 0.37f, 0.0f)
                        .get(modelBuf));
                encoder.setVertexBuffer(vertices);
                encoder.setIndexBuffer(indices);
                encoder.setState(BGFX_STATE.DEFAULT);
                encoder.submit(bgfxView, program);
            }
        }
        encoder.end();
    }

    @Override
    public void dispose() {
        MemoryUtil.memFree(viewBuf);
        MemoryUtil.memFree(projBuf);
        MemoryUtil.memFree(modelBuf);

        if (vertices != null) { vertices.dispose(); }
        if (indices != null) { indices.dispose(); }
        if (program != null) { program.dispose(); }
    }

    public static void main(String[] args) throws IOException {
        new Cubes().start();
    }

}
