package com.bariumhoof.bgfx4j.examples._01_cubes;

import com.bariumhoof.bgfx4j.Application;
import com.bariumhoof.bgfx4j.buffer.StaticIndexBuffer;
import com.bariumhoof.bgfx4j.buffer.StaticVertexBufferOld;
import com.bariumhoof.bgfx4j.buffer.VertexLayoutOld;
import com.bariumhoof.bgfx4j.enums.BGFX_ATTRIB;
import com.bariumhoof.bgfx4j.enums.BGFX_ATTRIB_TYPE;
import com.bariumhoof.bgfx4j.enums.BGFX_RENDERER_TYPE;
import com.bariumhoof.bgfx4j.enums.BGFX_STATE;
import com.bariumhoof.bgfx4j.shaders.Program;
import com.bariumhoof.bgfx4j.view.View;
import com.bariumhoof.bgfx4j.wip.Encoder;
import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.lwjgl.system.MemoryUtil;

import java.io.IOException;
import java.nio.FloatBuffer;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import static org.lwjgl.bgfx.BGFX.*;

public class CubesMultithreaded extends Application {

    private static final Executor executor1 = Executors.newSingleThreadExecutor();
    private static final Executor executor2 = Executors.newSingleThreadExecutor();
    private static final Executor executor3 = Executors.newSingleThreadExecutor();

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

    private StaticVertexBufferOld vertices;
    private StaticIndexBuffer indices;
    private Program program;

    private Matrix4f view = new Matrix4f();
    private View bgfxView;
    private FloatBuffer viewBuf;
    private Matrix4f proj = new Matrix4f();
    private FloatBuffer projBuf;

    private CountDownLatch latch = new CountDownLatch(3);

    private static ThreadLocal<Matrix4f> _model = new ThreadLocal<>();
    private static ThreadLocal<FloatBuffer> _modelBuf = new ThreadLocal<>();

    @Override
    public void init() {

        final VertexLayoutOld layout = VertexLayoutOld.builder(BGFX_RENDERER_TYPE.METAL)
                .beginWith(BGFX_ATTRIB.POSITION, 3, BGFX_ATTRIB_TYPE.FLOAT, false, false)
                .thenUse(BGFX_ATTRIB.COLOR0, 4, BGFX_ATTRIB_TYPE.UINT8, true, false)
                .build();

        vertices = StaticVertexBufferOld.create(layout, cubeVertices);
        indices = StaticIndexBuffer.create(cubeIndices);

        program = Program.loadOrNull(
                Application.locateVertexShaderByName("cubes"), // vertex shader
                Application.locateFragmentShaderByName("cubes") // fragment shader
        );

        bgfxView = View.create("my view");

        viewBuf = MemoryUtil.memAllocFloat(16);
        projBuf = MemoryUtil.memAllocFloat(16);
    }

    @Override
    public void render(float dt, float time) {
        bgfx_set_view_rect(0, 0, 0, getWidth(), getHeight());
        bgfx_dbg_text_printf(0, 1, 0x4f, "bgfx/examples/01-cubes (multithreaded)");
        bgfx_dbg_text_printf(0, 2, 0x6f, "Description: Each 4x4 square is rendered on its own thread.");

        lookAt(new Vector3f(0.0f, 0.0f, 0.0f), new Vector3f(0.0f, 0.0f, -35.0f), view);
        perspective(60.0f, getWidth(), getHeight(), 0.1f, 100.0f, proj);

        bgfxView.setTransform(view.get(viewBuf), proj.get(projBuf)); // todo make view work more like a camera...

        latch = new CountDownLatch(3); // set latch that requires 3 countDowns

        // each render a 4x4 square of cubes
        executor1.execute(() -> renderCubes(0, time));
        executor2.execute(() -> renderCubes(4, time));
        executor3.execute(() -> renderCubes(8, time));

        // todo add "on exit" callback to ensure latch doesn't stop shutdown.
        try {
            latch.await(); // await all thread's completion
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void renderCubes(int start, float time) {
        Encoder encoder = Encoder.begin(false);
        bgfx_encoder_touch(encoder.id(), 0);

        Matrix4f model = _model.get();
        FloatBuffer modelBuf = _modelBuf.get();
        if (model == null) {
            model = new Matrix4f();
            _model.set(model);
            modelBuf = MemoryUtil.memAllocFloat(16);
            _modelBuf.set(modelBuf);
        }

        for (int yy = start; yy < start+4; ++yy) {
            for (int xx = start; xx < start+4; ++xx) {
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
//        bgfx_submit(start / 4, program.handle(), 0, false);
        latch.countDown();
    }

    @Override
    public void dispose() {
        MemoryUtil.memFree(viewBuf);
        MemoryUtil.memFree(projBuf);
        executor1.execute(() -> { _model.remove(); _modelBuf.remove(); });
        executor2.execute(() -> { _model.remove(); _modelBuf.remove(); });
        executor3.execute(() -> { _model.remove(); _modelBuf.remove(); });

        if (vertices != null) { vertices.dispose(); }
        if (indices != null) { indices.dispose(); }
        if (program != null) { program.dispose(); }
    }

    public static void main(String[] args) throws IOException {
        new CubesMultithreaded().start();
    }

}
