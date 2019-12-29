package com.bariumhoof.bgfx4j.examples._01_cubes;

import com.bariumhoof.bgfx4j.Application;
import com.bariumhoof.bgfx4j.enums.BGFX_ATTRIB;
import com.bariumhoof.bgfx4j.enums.BGFX_ATTRIB_TYPE;
import com.bariumhoof.bgfx4j.enums.BGFX_RENDERER_TYPE;
import com.bariumhoof.bgfx4j.view.View;
import com.bariumhoof.bgfx4j.wip.IndexBuffer;
import com.bariumhoof.bgfx4j.wip.Program;
import com.bariumhoof.bgfx4j.wip.VertexBuffer;
import com.bariumhoof.bgfx4j.wip.VertexLayout;
import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.lwjgl.system.MemoryUtil;

import java.io.IOException;
import java.nio.FloatBuffer;

import static org.lwjgl.bgfx.BGFX.*;

public class CubesMultipleVertexStream extends Application {

//    private static final Number[][] cubeVertices = {
//            {-1.0f, 1.0f, 1.0f, 0xff000000},
//            {1.0f, 1.0f, 1.0f, 0xff0000ff},
//            {-1.0f, -1.0f, 1.0f, 0xff00ff00},
//            {1.0f, -1.0f, 1.0f, 0xff00ffff},
//            {-1.0f, 1.0f, -1.0f, 0xffff0000},
//            {1.0f, 1.0f, -1.0f, 0xffff00ff},
//            {-1.0f, -1.0f, -1.0f, 0xffffff00},
//            {1.0f, -1.0f, -1.0f, 0xffffffff}
//    };

    // https://github.com/bkaradzic/bgfx/blob/ea6153796ca6217a4e12fccdac38121d1c379e7f/examples/34-mvs/mvs.cpp#L49
    private static final float[][] cubePosVertices =
            {
                    {-1.0f,  1.0f,  1.0f },
                    { 1.0f,  1.0f,  1.0f },
                    {-1.0f, -1.0f,  1.0f },
                    { 1.0f, -1.0f,  1.0f },
                    {-1.0f,  1.0f, -1.0f },
                    { 1.0f,  1.0f, -1.0f },
                    {-1.0f, -1.0f, -1.0f },
                    { 1.0f, -1.0f, -1.0f },
            };

    private static final int[][] cubeColorVertices =
            {
                    { 0xff000000 },
                    { 0xff0000ff },
                    { 0xff00ff00 },
                    { 0xff00ffff },
                    { 0xffff0000 },
                    { 0xffff00ff },
                    { 0xffffff00 },
                    { 0xffffffff },
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

    private VertexLayout layout_position;
    private VertexLayout layout_color;
    private VertexBuffer vb_position;
    private VertexBuffer vb_color;
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

        layout_position = VertexLayout.builder(BGFX_RENDERER_TYPE.METAL)
                .beginWith(BGFX_ATTRIB.POSITION, BGFX_ATTRIB_TYPE.FLOAT, false, false)
                .build();

        layout_color = VertexLayout.builder(BGFX_RENDERER_TYPE.METAL)
                .beginWith(BGFX_ATTRIB.COLOR0, BGFX_ATTRIB_TYPE.UINT8, true, false)
                .build();

        vb_position = VertexBuffer.create(layout_position, cubePosVertices);
        vb_color = VertexBuffer.create(layout_color, cubeColorVertices);
        indices = IndexBuffer.create(cubeIndices);

        program = Program.loadOrNull(
                Application.locateVertexShaderByName("cubes"), // vertex shader
                Application.locateFragmentShaderByName("cubes") // fragment shader
        );

        bgfxView = View.create("my view");

        viewBuf = MemoryUtil.memAllocFloat(16);
        projBuf = MemoryUtil.memAllocFloat(16);
        modelBuf = MemoryUtil.memAllocFloat(16);
    }

    @Override
    public void render(float dt, float time) {
        bgfx_set_view_rect(0, 0, 0, width, height);
        bgfx_dbg_text_printf(0, 1, 0x4f, "bgfx/examples/01-cubes (multiple vertex streams)");
        bgfx_dbg_text_printf(0, 2, 0x6f, "Description: Rendering simple static mesh.");

        lookAt(new Vector3f(0.0f, 0.0f, 0.0f), new Vector3f(0.0f, 0.0f, -35.0f), view);
        perspective(60.0f, getWidth(), getHeight(), 0.1f, 100.0f, proj);

        bgfxView.setTransform(view.get(viewBuf), proj.get(projBuf)); // todo make view work more like a camera...

        for (int yy = 0; yy < 11; ++yy) {
            for (int xx = 0; xx < 11; ++xx) {
                bgfx_set_transform(model.translation(-15.0f + xx * 3.0f, -15.0f + yy * 3.0f, 0.0f)
                        .rotateAffineXYZ(time + xx * 0.21f, time + yy * 0.37f, 0.0f)
                        .get(modelBuf));


                bgfx_set_vertex_buffer(0, vb_position.handle(), 0, vb_position.size());
                bgfx_set_vertex_buffer(1, vb_color.handle(), 0, vb_color.size());
                bgfx_set_index_buffer(indices.handle(), 0, indices.size());
                bgfx_submit(0, program.handle(), 0, false);
            }
        }
    }

    @Override
    public void dispose() {
        MemoryUtil.memFree(viewBuf);
        MemoryUtil.memFree(projBuf);
        MemoryUtil.memFree(modelBuf);

        if (vb_position != null) { vb_position.dispose(); }
        if (indices != null) { indices.dispose(); }
        if (program != null) { program.dispose(); }
    }

    public static void main(String[] args) throws IOException {
        new CubesMultipleVertexStream().start();
    }

}
