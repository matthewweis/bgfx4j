package com.bariumhoof.bgfx4j.examples._04_mesh;

import com.bariumhoof.bgfx4j.Application;
import com.bariumhoof.bgfx4j.buffer.StaticIndexBuffer;
import com.bariumhoof.bgfx4j.buffer.StaticVertexBuffer;
import com.bariumhoof.bgfx4j.buffer.VertexLayout;
import com.bariumhoof.bgfx4j.enums.BGFX_ATTRIB;
import com.bariumhoof.bgfx4j.enums.BGFX_ATTRIB_TYPE;
import com.bariumhoof.bgfx4j.enums.BGFX_STATE;
import com.bariumhoof.bgfx4j.enums.BGFX_UNIFORM_TYPE;
import com.bariumhoof.bgfx4j.shaders.Program;
import com.bariumhoof.bgfx4j.view.View;
import com.bariumhoof.bgfx4j.wip.Uniform;
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

import static org.lwjgl.bgfx.BGFX.*;

/**
 * Port of:
 * https://github.com/LWJGL/lwjgl3-demos/blob/master/src/org/lwjgl/demo/bgfx/Cubes.java
 */
public class Mesh extends Application {

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
//
//    private static final int[] cubeIndices = {
//            0, 1, 2, // 0
//            1, 3, 2,
//            4, 6, 5, // 2
//            5, 6, 7,
//            0, 2, 4, // 4
//            4, 2, 6,
//            1, 5, 3, // 6
//            5, 7, 3,
//            0, 4, 1, // 8
//            4, 5, 1,
//            2, 3, 6, // 10
//            6, 3, 7
//    };

    private StaticVertexBuffer vertices;
    private StaticIndexBuffer indices;
    private Program program;

    private Matrix4f view = new Matrix4f();
    private View bgfxView;
    private FloatBuffer viewBuf;
    private Matrix4f proj = new Matrix4f();
    private FloatBuffer projBuf;
    private Matrix4f model = new Matrix4f();
    private FloatBuffer modelBuf;

    private Uniform u_time;
    VertexLayout layout;

//    AIScene mesh = null;
    Model mesh = null;

    @Override
    public void init() {

        try {
//            mesh = load(Mesh.class.getResource("/meshes/bunny.bin").getPath());
            mesh = load(Mesh.class.getResource("/meshes/teapot.obj"));

            // mesh load
            // https://github.com/bkaradzic/bgfx/blob/79166dfe17a2493b0b772b37c86fb33581dd861a/examples/common/bgfx_utils.cpp#L639

            // mesh submit
            // https://github.com/bkaradzic/bgfx/blob/79166dfe17a2493b0b772b37c86fb33581dd861a/examples/common/bgfx_utils.cpp#L600

        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }

        layout = VertexLayout.builder()
                .beginWith(BGFX_ATTRIB.POSITION, 3, BGFX_ATTRIB_TYPE.FLOAT, false, false)
                .thenUse(BGFX_ATTRIB.COLOR0, 4, BGFX_ATTRIB_TYPE.UINT8, true, false)
                .build();

        vertices = StaticVertexBuffer.create(layout, ByteBuffer.wrap(mesh.vertices), mesh.numVerts);
        indices = StaticIndexBuffer.create(mesh.indices);

        u_time = Uniform.createSingle("u_time", BGFX_UNIFORM_TYPE.VEC4);

        program = Program.loadOrNull(
                Application.locateVertexShaderByName("mesh"), // vertex shader
                Application.locateFragmentShaderByName("mesh") // fragment shader
        );

        bgfxView = View.create("my view");

        viewBuf = MemoryUtil.memAllocFloat(16);
        projBuf = MemoryUtil.memAllocFloat(16);
        modelBuf = MemoryUtil.memAllocFloat(16);
    }

    @Override
    public void render(float dt, float time) {


//        final Encoder encoder = Encoder.begin(false);

//        encoder.touch(bgfxView);

//        encoder.setUniform(u_time);


//        float[] view = new float[16];
//        float[] proj = new float[16];


//        var at = new Vector3f(0.0f, 1//.0f, 0.0f);
//        var eye = new Vector3f(0.0f, 1.0f, -2.5f);

//        bgfx_set_view_transform(0, view, proj);

//        bgfx_set_view_rect();



//        bgfx_set_view_rect(0, 0, 0, width, height);
        bgfx_touch(0);
        bgfx_set_uniform(u_time.handle(), new float[] { time }, 1);
        bgfxView.setViewRect(0, 0, width, height);
        bgfx_dbg_text_printf(0, 1, 0x4f, "bgfx/examples/01-cubes");
        bgfx_dbg_text_printf(0, 2, 0x6f, "Description: Rendering simple static mesh.");

        lookAt(new Vector3f(0.0f, 1.0f, 0.0f), new Vector3f(0.0f, 1.0f, -2.5f), view);
        perspective(60.0f, getWidth(), getHeight(), 0.1f, 100.0f, proj);

        bgfxView.setTransform(view.get(viewBuf), proj.get(projBuf)); // todo make view work more like a camera...

        // todo submit it! this is not right, lots missing, see:
        // https://github.com/bkaradzic/bgfx/blob/79166dfe17a2493b0b772b37c86fb33581dd861a/examples/common/bgfx_utils.cpp#L600

//        lookAt(new Vector3f(0.0f, 0.0f, 0.0f), new Vector3f(0.0f, 0.0f, -15.0f), view);
//        perspective(60.0f, getWindowWidth(), getWindowHeight(), 0.1f, 100.0f, proj);
//
//        view0.setTransform(view.get(viewBuf), proj.get(projBuf));
//        bgfx_set_transform(view.get(viewBuf));
//        bgfxView.setTransform(view.get(viewBuf), proj.get(projBuf));

//        ortho(0.0f, 1280.0f, 720.0f, 0.0f, 0.0f, 100.0f, ortho);
//        bgfxView.setViewTransform(null, ortho.get(orthoBuf));

//        encoder.setTransform(model.translation(-15.0f + xx * 3.0f, -15.0f + yy * 3.0f, 0.0f)
//            .rotateAffineXYZ(time + xx * 0.21f, time + yy * 0.37f, 0.0f)
//            .get(modelBuf));
//        model.rotateTowardsXY(0.0f, time * 0.37f);

//        encoder.setTransform(modelBuf);
//        bgfx_set_transform(modelBuf);

//        encoder.setState(EnumSet.of(BGFX_STATE.DEFAULT));
        bgfx_set_state(BGFX_STATE.flags(EnumSet.of(BGFX_STATE.DEFAULT)), 0);

//        final Memory vmem = Memory.copy(ByteBuffer.wrap(mesh.vertices));
//        final Memory fmem = Memory.copy(ByteBuffer.wrap(mesh.indices));
//        final StaticVertexBuffer vmem = StaticVertexBuffer.create(layout, ByteBuffer.wrap(mesh.vertices), mesh.numVerts);
//        final StaticIndexBuffer fmem = StaticIndexBuffer.create(mesh.indices);

        // todo assuming numIndices is correct for size
        bgfx_set_vertex_buffer(0, vertices.handle(), 0, mesh.numVerts);
        bgfx_set_index_buffer(indices.handle(), 0, mesh.numIndices);

        bgfx_submit(bgfxView.id(), program.handle(), 0, false);

//        encoder.submit(bgfxView, program);


//        final int numTextures = mesh.mNumTextures();
//        final PointerBuffer textures = mesh.mTextures();

//        for (int i=0; i < numTextures; i++) {
//            final long texture = textures.get(i);
////            encoder.setTexture(0, u_time, texture);
//            bgfx_set_texture(i, u_time.handle(), (short)texture, (int)BGFX_SAMPLER.flags(EnumSet.of(BGFX_SAMPLER.NONE)));
//        }

//        final PointerBuffer meshes = mesh.mMeshes();
//        final int numMeshes = mesh.mNumMeshes();
//
//        for (int i=0; i < numMeshes; i++) {
//            final long meshHandle = meshes.get(i);
//            final AIMesh aiMesh = AIMesh.create(meshHandle);
//
//            final AIVector3D.Buffer aiVerts = aiMesh.mVertices();
//            final int numVerts = aiMesh.mNumVertices();
//
//            final AIFace.Buffer faces = aiMesh.mFaces();
//            final int numFaces = aiMesh.mNumFaces();
////            bgfx_encoder_set_vertex_buffer(encoder.id(), (short)meshHandle, 0, numVerts);
//
//            final BGFXMemory i_mem = BGFXMemory.calloc();
//            final IntBuffer i_data = i_mem.data().asIntBuffer();
//
//            final BGFXMemory v_mem = BGFXMemory.calloc();
//            final FloatBuffer v_data = v_mem.data().asFloatBuffer();
//
//            final IntBuffer indices = faces.mIndices();
//            final int numIndices = faces.mNumIndices();
//
//            for (int j=0; j < numIndices; j++) {
//                i_data.put(j, indices.get(j));
//            }
//            i_data.flip();
//
//            for (int j=0; j < numVerts; j++) {
//                v_data.put(j, aiVerts.get(j).x());
//                v_data.put(j, aiVerts.get(j).y());
//                v_data.put(j, aiVerts.get(j).z());
//            }
//            i_data.flip();
//
////            bgfx_set_index_buffer(, 0, numIndices);
////            bgfx_set_vertex_buffer(i, (short)meshHandle, 0, numVerts);
////            bgfx_
//            bgfx_set_vertex_buffer(0, );
//
////            bgfx_encoder_set_vertex_buffer(encoder.id(), i, (short)meshHandle, 0, numVerts, vertexL);

//            final int x;
//            if (i == numMeshes - 1) {
//                bgfx_submit(bgfxView.id(), program.handle(), 0, false);
//            } else {
//                bgfx_submit(bgfxView.id(), program.handle(), 0, true);
//            }

//            bgfx_submit(bgfxView.id(), program.handle(), 0, true);
//        }

//        encoder.end();
    }

    @Value
    static class Model {
        int numVerts;
        int numIndices;
        byte[] vertices;
        short[] indices;
//        Memory vertices;
//        Memory indices;
    }

    @Value
    static class ModelInfo {
        int numVerts;
        int numIndices;

        public ModelInfo incrVerts() {
            return new ModelInfo(numVerts + 1, numIndices);
        }
        public ModelInfo incrIndices() {
            return new ModelInfo(numVerts, numIndices + 1);
        }
        public static ModelInfo create() {
            return new ModelInfo(0, 0);
        }
        public static ModelInfo merge(ModelInfo left, ModelInfo right) {
            return new ModelInfo(
                    left.numVerts + right.numVerts,
                    left.numIndices + right.numIndices
            );
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

    public static Model load(URL resource) throws Exception {

        // vert layout is 3 floats (pos), one 32-bit int (color)
        // load these into vert array

        // then use faces to load index array

        final ModelInfo modelInfo;
        final byte[] verts;
        final short[] faces;
        try(final BufferedReader reader = new BufferedReader(new FileReader(resource.getFile()))) {
            // parse once to collect number of verts and faces
            modelInfo = reader.lines().reduce(
                    ModelInfo.create(),
                    (acc, line) -> line.startsWith("v") ? acc.incrVerts() : acc.incrIndices(),
                    ModelInfo::merge);

            verts = new byte[modelInfo.numVerts * 4 * 4]; // remember colors need to be added too
            faces = new short[modelInfo.numIndices * 3];
        }

        try(final BufferedReader reader = new BufferedReader(new FileReader(resource.getFile()))) {
            int vi = 0, fi = 0;

            String line;

            do {
                line = reader.readLine();
                if (line != null) {
                    final String[] split = line.split(" "); // v or f then 3 floats or shorts
                    if (split[0].equals("v")) {
                        for (byte b : floatToBytes(Float.parseFloat(split[1]))) verts[vi++] = b;
                        for (byte b : floatToBytes(Float.parseFloat(split[2]))) verts[vi++] = b;
                        for (byte b : floatToBytes(Float.parseFloat(split[3]))) verts[vi++] = b;
                        for (byte b : intToBytes(0x00FAFAFA)) verts[vi++] = b;
                    } else if (split[0].equals("f")) {
                        faces[fi++] = Short.parseShort(split[1]);
                        faces[fi++] = Short.parseShort(split[2]);
                        faces[fi++] = Short.parseShort(split[3]);
                    } else {
                        if (line.isEmpty()) {
                            continue;
                        }
                        throw new IllegalStateException();
                    }
                }
            } while (line != null);
        }

        return new Model(modelInfo.numVerts, modelInfo.numIndices, verts, faces);
    }

    static byte[] floatToBytes(float f) {
        return ByteBuffer.allocate(4).putFloat(f).array();
    }

    static byte[] intToBytes(int n) {
        return ByteBuffer.allocate(4).putInt(n).array();
    }

    static byte[] shortToBytes(short s) {
        return ByteBuffer.allocate(2).putShort(s).array();
    }

    public static void main(String[] args) throws IOException {
        new Mesh().start();
    }

}
