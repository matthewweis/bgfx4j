package com.bariumhoof.bgfx4j.examples._05_instancing;

import com.bariumhoof.Capabilities;
import com.bariumhoof.bgfx4j.Application;
import com.bariumhoof.bgfx4j.enums.BGFX_ATTRIB;
import com.bariumhoof.bgfx4j.enums.BGFX_ATTRIB_TYPE;
import com.bariumhoof.bgfx4j.enums.BGFX_CAPS;
import com.bariumhoof.bgfx4j.enums.BGFX_STATE;
import com.bariumhoof.bgfx4j.view.View;
import com.bariumhoof.bgfx4j.wip.*;
import lombok.extern.slf4j.Slf4j;
import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;

import static org.lwjgl.bgfx.BGFX.bgfx_dbg_text_printf;
import static org.lwjgl.bgfx.BGFX.bgfx_get_avail_instance_data_buffer;

/**
 * Port of:
 * https://github.com/bkaradzic/bgfx/blob/master/examples/05-instancing/instancing.cpp
 */
@Slf4j
public class Instancing extends Application {

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

    private VertexLayout layout;
    private VertexBuffer vertices;
    private IndexBuffer indices;
    private Program program;

    private View appView;

    private Matrix4f view = new Matrix4f();
    private FloatBuffer viewBuf;
    private Matrix4f proj = new Matrix4f();
    private FloatBuffer projBuf;
    private Matrix4f model = new Matrix4f();
    private FloatBuffer modelBuf;

    @Override
    public void init() {

        layout = VertexLayout.builder()
                .beginWith(BGFX_ATTRIB.POSITION, BGFX_ATTRIB_TYPE.FLOAT)
                .thenUseNormalized(BGFX_ATTRIB.COLOR0, BGFX_ATTRIB_TYPE.UINT8)
                .build();

        vertices = VertexBuffer.create(layout, cubeVertices);
        indices = IndexBuffer.create(cubeIndices);

        program = Program.loadOrNull(
                Application.locateVertexShaderByName("instancing"), // vertex shader
                Application.locateFragmentShaderByName("instancing") // fragment shader
        );

        appView = View.create("appView");

        viewBuf = MemoryUtil.memAllocFloat(16);
        projBuf = MemoryUtil.memAllocFloat(16);
        modelBuf = MemoryUtil.memAllocFloat(16);
    }

    @Override
    public void render(float dt, float time) {
        appView.setViewRect(0, 0, width, height);
        bgfx_dbg_text_printf(0, 1, 0x4f, "bgfx/examples/05-instancing");
        bgfx_dbg_text_printf(0, 2, 0x6f, "Description: Rendering simple static mesh.");

        if (!Capabilities.isSupported(BGFX_CAPS.INSTANCING)) {
            log.error("instancing not supported!");
            bgfx_dbg_text_printf(0, 3, 0x6f, "Instancing not supported! Nothing will be displayed.");
            return;
        }

        lookAt(new Vector3f(0.0f, 0.0f, 0.0f), new Vector3f(0.0f, 0.0f, -35.0f), view);
        perspective(60.0f, getWidth(), getHeight(), 0.1f, 100.0f, proj);

        appView.setTransform(view, proj);

        final int instanceStride = 80; // 80 bytes stride = 64 bytes for 4x4 matrix + 16 bytes for RGBA color.
        final int numInstances = 121; // 11x11 cubes

        if (numInstances == bgfx_get_avail_instance_data_buffer(numInstances, instanceStride)) {

            try (final MemoryStack stack = MemoryStack.stackPush()) {

                final InstanceBuffer idb = InstanceBuffer.create(stack, numInstances, instanceStride);
                final ByteBuffer data = idb.data();

                final Encoder encoder = Encoder.begin(false);
                for (int yy = 0; yy < 11; ++yy) {
                    for (int xx = 0; xx < 11; ++xx) {
                        model.translation(-15.0f + xx * 3.0f, -15.0f + yy * 3.0f, 0.0f)
                                .rotateAffineXYZ(time + xx * 0.21f, time + yy * 0.37f, 0.0f)
                                .get(data);

                        // 64 bits for position (done on line above, but manual position movement required)
                        data.position(data.position() + 64);

                        // then 16 more bits for color (position moved automatically by put methods)
                        data.putFloat((float) (Math.sin(time + ((float)xx)/11.0f) * 0.5f + 0.5f));
                        data.putFloat((float) (Math.sin(time + ((float)yy)/11.0f) * 0.5f + 0.5f));
                        data.putFloat((float) (Math.sin(time * 3.0f) * 0.5f + 0.5f));
                        data.putFloat(1.0f);

                        encoder.setTransform(data);
                    }
                }

                encoder.setVertexBuffer(vertices);
                encoder.setIndexBuffer(indices);

                encoder.setInstanceBuffer(idb);

                encoder.setState(BGFX_STATE.DEFAULT);
                encoder.submit(appView, program);

                encoder.end();
            }
        }
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
        new Instancing().start();
    }
}

