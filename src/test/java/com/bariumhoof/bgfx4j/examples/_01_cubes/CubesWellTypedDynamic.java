package com.bariumhoof.bgfx4j.examples._01_cubes;

import com.bariumhoof.bgfx4j.Application;
import com.bariumhoof.bgfx4j.buffer.StaticIndexBuffer;
import com.bariumhoof.bgfx4j.enums.BGFX_STATE;
import com.bariumhoof.bgfx4j.layout.DynamicVertexBuffer;
import com.bariumhoof.bgfx4j.layout.Vertex.Vertex2;
import com.bariumhoof.bgfx4j.layout.VertexLayout;
import com.bariumhoof.bgfx4j.shaders.Program;
import com.bariumhoof.bgfx4j.view.View;
import com.bariumhoof.bgfx4j.wip.Encoder;
import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.lwjgl.system.MemoryUtil;

import java.io.IOException;
import java.nio.FloatBuffer;
import java.util.List;

import static com.bariumhoof.bgfx4j.layout.Vec.*;
import static com.bariumhoof.bgfx4j.layout.Vertex.vertex;
import static org.lwjgl.bgfx.BGFX.bgfx_dbg_text_printf;
import static org.lwjgl.bgfx.BGFX.bgfx_set_view_rect;

/**
 * Port of:
 * https://github.com/LWJGL/lwjgl3-demos/blob/master/src/org/lwjgl/demo/bgfx/Cubes.java
 */
public class CubesWellTypedDynamic extends Application {

    final List<Vertex2<FLOAT_Vec3, UINT8_Vec4>> cubeVerts = List.of(
            vertex(float_vec3(-1.0f, 1.0f, 1.0f), uint8_vec4(0xff000000)),
            vertex(float_vec3(1.0f, 1.0f, 1.0f), uint8_vec4(0xff0000ff)),
            vertex(float_vec3(-1.0f, -1.0f, 1.0f), uint8_vec4(0xff00ff00)),
            vertex(float_vec3(1.0f, -1.0f, 1.0f), uint8_vec4(0xff00ffff)),
            vertex(float_vec3(-1.0f, 1.0f, -1.0f), uint8_vec4(0xffff0000)),
            vertex(float_vec3(1.0f, 1.0f, -1.0f), uint8_vec4(0xffff00ff)),
            vertex(float_vec3(-1.0f, -1.0f, -1.0f), uint8_vec4(0xffffff00)),
            vertex(float_vec3(1.0f, -1.0f, -1.0f), uint8_vec4(0xfffffff)));

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

    private DynamicVertexBuffer<Vertex2<FLOAT_Vec3, UINT8_Vec4>> vertices;
    private StaticIndexBuffer indices;

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

        final VertexLayout<Vertex2<FLOAT_Vec3, UINT8_Vec4>> layout = VertexLayout.builder()
                .position().float_vec3().then()
                .color0().uint8_vec4().normalized()
                .build();

        vertices = DynamicVertexBuffer.create(cubeVerts.size(), layout);
        vertices.update(cubeVerts);
        indices = StaticIndexBuffer.create(cubeIndices);

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
        bgfx_set_view_rect(0, 0, 0, getWidth(), getHeight());
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
                encoder.setDynamicVertexBuffer(vertices); // todo get rid of "dynamic" distinction in setter
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
        new CubesWellTypedDynamic().start();
    }

}
