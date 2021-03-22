package com.bariumhoof.bgfx4j.examples._04_mesh;

import com.bariumhoof.bgfx4j.Application;
import com.bariumhoof.bgfx4j.buffer.StaticIndexBuffer;
import com.bariumhoof.bgfx4j.buffer.StaticVertexBufferOld;
import com.bariumhoof.bgfx4j.buffer.VertexLayoutOld;
import com.bariumhoof.bgfx4j.enums.BGFX_ATTRIB;
import com.bariumhoof.bgfx4j.enums.BGFX_ATTRIB_TYPE;
import com.bariumhoof.bgfx4j.enums.BGFX_STATE;
import com.bariumhoof.bgfx4j.shaders.Program;
import com.bariumhoof.bgfx4j.view.View;
import com.bariumhoof.bgfx4j.wip.Encoder;
import lombok.Value;
import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.lwjgl.system.MemoryUtil;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.util.EnumSet;

import static com.bariumhoof.bgfx4j.enums.BGFX_STATE.WRITE_A;
import static com.bariumhoof.bgfx4j.enums.BGFX_STATE.WRITE_RGB;
import static org.lwjgl.bgfx.BGFX.*;

/**
 * Port of:
 * https://github.com/LWJGL/lwjgl3-demos/blob/master/src/org/lwjgl/demo/bgfx/Cubes.java
 */
public class TeapotsGrid extends Application {

    private StaticVertexBufferOld vertices1;
    private StaticVertexBufferOld vertices2;
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

        final VertexLayoutOld layout = VertexLayoutOld.builder()
                .beginWith(BGFX_ATTRIB.POSITION, 3, BGFX_ATTRIB_TYPE.FLOAT, false, false)
                .thenUse(BGFX_ATTRIB.COLOR0, 4, BGFX_ATTRIB_TYPE.UINT8, true, false)
                .build();

        final Model mesh1;
        final Model mesh2;
        try {
            mesh1 = load(Mesh.class.getResource("/meshes/teapot.obj"), 0xffaa0000, 1.0f, 3);
            mesh2 = load(Mesh.class.getResource("/meshes/teapot.obj"), 0x8800aa00, 1.2f, -3);
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalStateException("unable to load mesh");
        }

        vertices1 = StaticVertexBufferOld.create(layout, mesh1.vertices);
        vertices2 = StaticVertexBufferOld.create(layout, mesh2.vertices);
        indices = StaticIndexBuffer.create(mesh1.indices);

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
        bgfx_set_view_clear(0, BGFX_CLEAR_COLOR | BGFX_CLEAR_DEPTH, 0xaa00aa00, 1.0f, 0);
        bgfx_dbg_text_printf(0, 1, 0x4f, "bgfx/examples/01-cubes");
        bgfx_dbg_text_printf(0, 2, 0x6f, "Description: Rendering simple static mesh.");

        lookAt(new Vector3f(0.0f, 0.0f, 0.0f), new Vector3f(0.0f, 0.0f, -35.0f), view);
        perspective(60.0f, getWidth(), getHeight(), 0.1f, 100.0f, proj);

        bgfxView.setTransform(view.get(viewBuf), proj.get(projBuf));

        final Encoder encoder = Encoder.begin(false);
        encoder.setState(EnumSet.of(WRITE_RGB, WRITE_A, BGFX_STATE.DEFAULT, BGFX_STATE.MSAA));
        encoder.setTransform(model.translation(0.0f, 0.0f, 0.0f)
                .rotateAffineXYZ(-0.35f, time + 0.77f, 0.0f)
                .get(modelBuf));
        encoder.setVertexBuffer(vertices1);
        encoder.setIndexBuffer(indices);
        encoder.setState(BGFX_STATE.DEFAULT);
        encoder.submit(bgfxView, program, true);

        encoder.setVertexBuffer(vertices2);
        encoder.submit(bgfxView, program, false);

        encoder.end();
    }

    @Override
    public void dispose() {
        MemoryUtil.memFree(viewBuf);
        MemoryUtil.memFree(projBuf);
        MemoryUtil.memFree(modelBuf);

        if (vertices1 != null) { vertices1.dispose(); }
        if (vertices2 != null) { vertices2.dispose(); }
        if (indices != null) { indices.dispose(); }
        if (program != null) { program.dispose(); }
    }

    @Value
    static class Model {
        int numVerts;
        int numIndices;
//        byte[] vertices;
        Number[][] vertices;
        short[] indices;
//        Memory vertices;
//        Memory indices;
    }

    public static Model load(URL resource, int color, float scale, int z) throws Exception {

        // vert layout is 3 floats (pos), one 32-bit int (color)
        // load these into vert array

        // then use faces to load index array

        int numVerts = 0;
        int numIndices = 0;
        final Number[][] verts;
        final short[] faces;
        try(final BufferedReader reader = new BufferedReader(new FileReader(resource.getFile()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("v")) {
                    numVerts++;
                } else if (line.startsWith("f")) {
                    numIndices++;
                }
            }

//            verts = new byte[numVerts * 4 * 4]; // remember colors need to be added too
            verts = new Number[numVerts][4];
            faces = new short[numIndices * 3];
        }

        try(final BufferedReader reader = new BufferedReader(new FileReader(resource.getFile()))) {
            int vi = 0, fi = 0; int ci = 0;

//            final float scale = 0.5f;
            String line;
            while ((line = reader.readLine()) != null) {
                final String[] split = line.split(" "); // v or f then 3 floats or shorts
                if (split[0].equals("v")) {
//                    for (byte b : floatToBytes(Float.parseFloat(split[1]))) verts[vi++] = b;
//                    for (byte b : floatToBytes(Float.parseFloat(split[2]))) verts[vi++] = b;
//                    for (byte b : floatToBytes(Float.parseFloat(split[3]))) verts[vi++] = b;
//                    for (byte b : intToBytes(0x00FAFAFA)) verts[vi++] = b;
                    verts[vi][0] = Float.parseFloat(split[1]) * scale;
                    verts[vi][1] = Float.parseFloat(split[2]) * scale;
                    verts[vi][2] = Float.parseFloat(split[3]) * scale + z;
//                    verts[vi][3] = colors[(4 + ((ci++) / 720)) % colors.length];
//                    verts[vi][3] = 0x00ff00ff;
                    // a b g r
                    verts[vi][3] = color;
                    vi++;
                } else if (split[0].equals("f")) {
                    faces[fi++] = Short.parseShort(split[1]);
                    faces[fi++] = Short.parseShort(split[2]);
                    faces[fi++] = Short.parseShort(split[3]);
                }
            }
        }

        return new Model(numVerts, numIndices, verts, faces);
    }

//    public static Model load(URL resource) throws Exception {
//
//        // vert layout is 3 floats (pos), one 32-bit int (color)
//        // load these into vert array
//
//        // then use faces to load index array
//
//        int numVerts = 0;
//        int numIndices = 0;
//        final byte[] verts;
//        final short[] faces;
//        try(final BufferedReader reader = new BufferedReader(new FileReader(resource.getFile()))) {
//            String line;
//            while ((line = reader.readLine()) != null) {
//                if (line.startsWith("v")) {
//                    numVerts++;
//                } else if (line.startsWith("f")) {
//                    numIndices++;
//                }
//            }
//
//            verts = new byte[numVerts * 4 * 4]; // remember colors need to be added too
//            faces = new short[numIndices * 3];
//        }
//
//        try(final BufferedReader reader = new BufferedReader(new FileReader(resource.getFile()))) {
//            int vi = 0, fi = 0;
//
//            String line;
//            while ((line = reader.readLine()) != null) {
//                final String[] split = line.split(" "); // v or f then 3 floats or shorts
//                if (split[0].equals("v")) {
//                    for (byte b : floatToBytes(Float.parseFloat(split[1]))) verts[vi++] = b;
//                    for (byte b : floatToBytes(Float.parseFloat(split[2]))) verts[vi++] = b;
//                    for (byte b : floatToBytes(Float.parseFloat(split[3]))) verts[vi++] = b;
//                    for (byte b : intToBytes(0x00FAFAFA)) verts[vi++] = b;
//                } else if (split[0].equals("f")) {
//                    faces[fi++] = Short.parseShort(split[1]);
//                    faces[fi++] = Short.parseShort(split[2]);
//                    faces[fi++] = Short.parseShort(split[3]);
//                }
//            }
//        }
//
//        return new Model(numVerts, numIndices, verts, faces);
//    }

    static byte[] floatToBytes(float f) {
        return ByteBuffer.allocate(4).putFloat(f).array();
    }

    static byte[] intToBytes(int n) {
        return ByteBuffer.allocate(4).putInt(n).array();
    }

    public static void main(String[] args) throws IOException {
        new TeapotsGrid().start();
    }

}
