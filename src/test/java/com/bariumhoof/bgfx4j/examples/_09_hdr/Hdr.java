package com.bariumhoof.bgfx4j.examples._09_hdr;

import com.bariumhoof.Capabilities;
import com.bariumhoof.bgfx4j.Application;
import com.bariumhoof.bgfx4j.enums.BGFX_BACKBUFFER_RATIO;
import com.bariumhoof.bgfx4j.enums.BGFX_DEBUG;
import com.bariumhoof.bgfx4j.enums.BGFX_RESET;
import com.bariumhoof.bgfx4j.enums.BGFX_UNIFORM_TYPE;
import com.bariumhoof.bgfx4j.layout.TransientVertexBuffer;
import com.bariumhoof.bgfx4j.layout.Vec;
import com.bariumhoof.bgfx4j.layout.Vec.FLOAT_Vec2;
import com.bariumhoof.bgfx4j.layout.Vec.FLOAT_Vec3;
import com.bariumhoof.bgfx4j.layout.Vec.UINT8_Vec4;
import com.bariumhoof.bgfx4j.layout.Vertex.Vertex3;
import com.bariumhoof.bgfx4j.layout.VertexLayout;
import com.bariumhoof.bgfx4j.shaders.Program;
import com.bariumhoof.bgfx4j.view.View;
import com.bariumhoof.bgfx4j.wip.*;
import lombok.Value;
import org.jetbrains.annotations.NotNull;
import org.lwjgl.bgfx.BGFX;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.util.*;

import static com.bariumhoof.bgfx4j.enums.BGFX_CAPS.TEXTURE_BLIT;
import static com.bariumhoof.bgfx4j.enums.BGFX_CAPS.TEXTURE_READ_BACK;
import static com.bariumhoof.bgfx4j.enums.BGFX_SAMPLER.UVW_CLAMP;
import static com.bariumhoof.bgfx4j.enums.BGFX_TEXTURE.READ_BACK;
import static com.bariumhoof.bgfx4j.enums.BGFX_TEXTURE_FORMAT.BGRA8;
import static java.util.Collections.emptySet;

// todo update shader compiler scripts to support the existing test layout format (folder per test)
public class Hdr extends Application {

    private View view;
    private Texture m_uffizi;
    private Program m_skyProgram;
    private Program m_lumProgram;
    private Program m_lumAvgProgram;
    private Program m_blurProgram;
    private Program m_brightProgram;
    private Program m_meshProgram;
    private Program m_tonemapProgram;

    private Uniform s_texCube;
    private Uniform s_texColor;
    private Uniform s_texLum;
    private Uniform s_texBlur;
    private Uniform u_mtx;
    private Uniform u_tonemap;
    private Uniform u_offset;

    private Model m_mesh;

    private FrameBuffer m_fbh;
    private final FrameBuffer[] m_lum = new FrameBuffer[5];
    private FrameBuffer m_bright;
    private FrameBuffer m_blur;

    static float s_texelHalf = 0.0f;

    private final BGFX_RESET m_reset = BGFX_RESET.VSYNC;
    private final BGFX_DEBUG m_debug = BGFX_DEBUG.NONE;

    private int m_oldWidth;
    private int m_oldHeight;
    private BGFX_RESET m_oldReset;
    private float m_speed;
    private float m_middleGray;
    private float m_white;
    private float m_threshold;
    private float m_scrollArea;
    private float m_time;

    public static VertexLayout<Vertex3<FLOAT_Vec3, UINT8_Vec4, FLOAT_Vec2>> vertexLayout;

    int m_lumBgra8 = 0;

    Texture m_rb;
    Texture m_rb2; // todo rm?

    // todo make all vecs have public constructors and non-final
    private static class PosColorTexCoord0Vertex extends Vertex3<FLOAT_Vec3, UINT8_Vec4, FLOAT_Vec2> {

        public PosColorTexCoord0Vertex(@NotNull FLOAT_Vec3 float_vec3, @NotNull UINT8_Vec4 uint8_vec4, @NotNull FLOAT_Vec2 float_vec2) {
            super(float_vec3, uint8_vec4, float_vec2);
        }
    }

    public void screenSpaceQuad(float _textureWidth,
                                float _textureHeight,
                                boolean _originBottomLeft, // default: false
                                float _width, // default: 1.0f
                                float _height) { // default: 1.0f
//        final TransientVertexBufferOld vb = TransientVertexBufferOld.heapCreate();
//        final TransientVertexBufferOld vb = TransientVertexBufferOld.heapCreate();
//        final TransientVertexBuffer tvb = TransientVertexBuffer.heapAlloc(3, vertexLayout);
//        DynamicVertexBuffer.create(3, vertexLayout);

        final TransientVertexBuffer<Vertex3<FLOAT_Vec3, UINT8_Vec4, FLOAT_Vec2>> vb =
                TransientVertexBuffer.heapAlloc(3, vertexLayout);

        if (vb != null) {
        final float zz = 0.0f;

		final float minx = -_width;
		final float maxx =  _width;
		final float miny = 0.0f;
		final float maxy = _height*2.0f;

		final float texelHalfW = s_texelHalf/_textureWidth;
		final float texelHalfH = s_texelHalf/_textureHeight;
		final float minu = -1.0f + texelHalfW;
		final float maxu =  1.0f + texelHalfW;

            float minv = texelHalfH;
            float maxv = 2.0f + texelHalfH;

            if (_originBottomLeft)
            {
                float temp = minv;
                minv = maxv;
                maxv = temp;

                minv -= 1.0f;
                maxv -= 1.0f;
            }

            /*
            vertex[0].m_x = minx;
            vertex[0].m_y = miny;
            vertex[0].m_z = zz;
            vertex[0].m_rgba = 0xffffffff;
            vertex[0].m_u = minu;
            vertex[0].m_v = minv;
             */
            final PosColorTexCoord0Vertex v0 = new PosColorTexCoord0Vertex(
                    Vec.float_vec3(minx, miny, zz),
                    Vec.uint8_vec4(0xffffffff),
                    Vec.float_vec2(minu, minv)
            );

            /*
            vertex[1].m_x = maxx;
            vertex[1].m_y = miny;
            vertex[1].m_z = zz;
            vertex[1].m_rgba = 0xffffffff;
            vertex[1].m_u = maxu;
            vertex[1].m_v = minv;
             */
            final PosColorTexCoord0Vertex v1 = new PosColorTexCoord0Vertex(
                    Vec.float_vec3(maxx, miny, zz),
                    Vec.uint8_vec4(0xffffffff),
                    Vec.float_vec2(maxu, minv)
            );

            /*
            vertex[2].m_x = maxx;
            vertex[2].m_y = maxy;
            vertex[2].m_z = zz;
            vertex[2].m_rgba = 0xffffffff;
            vertex[2].m_u = maxu;
            vertex[2].m_v = maxv;
             */
            final PosColorTexCoord0Vertex v2 = new PosColorTexCoord0Vertex(
                    Vec.float_vec3(maxx, maxy, zz),
                    Vec.uint8_vec4(0xffffffff),
                    Vec.float_vec2(maxu, maxv)
            );

//            bgfx::setVertexBuffer(0, &vb);
            final ByteBuffer data = vb.data();
            List.of(v0, v1, v2).stream()
                    .flatMap(vec -> Arrays.stream(vec.array()))
                    .forEachOrdered(vec -> vec.put(data));

            data.flip();


            BGFX.bgfx_set_vertex_buffer(0, vb.handle(), 0, 3);

        }
    }

//    void setOffsets2x2Lum(bgfx::UniformHandle _handle, uint32_t _width, uint32_t _height)
    // todo type handle
    void setOffsets2x2Lum(short _handle, int _width, int _height) {
//        final float[][] offsets = new float[16][4];
        final float[] offsets = new float[16*4];
        final float du = 1.0f / _width;
        final float dv = 1.0f / _height;

//        uint16_t num = 0;
        short num = 0;
//        for (uint32_t yy = 0; yy < 3; ++yy)
        for (int yy = 0; yy < 3; ++yy)
        {
//            for (uint32_t xx = 0; xx < 3; ++xx)
            for (int xx = 0; xx < 3; ++xx)
            {
//                offsets[num][0] = (xx - s_texelHalf) * du;
//                offsets[num][1] = (yy - s_texelHalf) * dv;
                offsets[num*4+0] = (xx - s_texelHalf) * du;
                offsets[num*4+1] = (yy - s_texelHalf) * dv;
                ++num;
            }
        }

//        final double[] doubles = Arrays.stream(offsets)
//                .flatMapToDouble(inner ->
//                        IntStream.range(0, inner.length).mapToDouble(i -> inner[i]))
//                .toArray();

//        DoubleStream ds = IntStream.range(0, floatArray.length)
//                .mapToDouble(i -> floatArray[i]);

//        bgfx::setUniform(_handle, offsets, num);
        BGFX.bgfx_set_uniform(_handle, offsets, num);
    }

    void setOffsets4x4Lum(short _handle, int _width, int _height) {
        final float[] offsets = new float[16*4];
        final float du = 1.0f / _width;
        final float dv = 1.0f / _height;

        short num = 0;
        for (int yy = 0; yy < 4; ++yy)
        {
            for (int xx = 0; xx < 4; ++xx)
            {
                offsets[num*4+0] = (xx - s_texelHalf) * du;
                offsets[num*4+1] = (yy - s_texelHalf) * dv;
                ++num;
            }
        }

        BGFX.bgfx_set_uniform(_handle, offsets, num);
    }

    @Override
    public void init() {
        // todo setDebug? (line 166)
        vertexLayout = VertexLayout.builder()
                .position().float_vec3().then()
                .color0().uint8_vec4().normalized().then()
                .texcoord0().float_vec2()
                .build();

        final TextureFlags textureFlags = TextureFlags.create(emptySet(), EnumSet.of(UVW_CLAMP));
        m_uffizi = Texture.loadOrNull(Hdr.class.getResource("/textures/uffizi.ktx"), textureFlags);

        m_skyProgram = Program.loadOrNull(locateVertexShaderByName("hdr_skybox"), locateFragmentShaderByName("hdr_skybox"));
        m_lumProgram = Program.loadOrNull(locateVertexShaderByName("hdr_lum"), locateFragmentShaderByName("hdr_lum"));
        m_lumAvgProgram = Program.loadOrNull(locateVertexShaderByName("hdr_lumavg"), locateFragmentShaderByName("hdr_lumavg"));
        m_blurProgram = Program.loadOrNull(locateVertexShaderByName("hdr_blur"), locateFragmentShaderByName("hdr_blur"));
        m_brightProgram = Program.loadOrNull(locateVertexShaderByName("hdr_bright"), locateFragmentShaderByName("hdr_bright"));
        m_meshProgram = Program.loadOrNull(locateVertexShaderByName("hdr_mesh"), locateFragmentShaderByName("hdr_mesh"));
        m_tonemapProgram = Program.loadOrNull(locateVertexShaderByName("hdr_tonemap"), locateFragmentShaderByName("hdr_tonemap"));

        s_texCube = Uniform.createSingle("s_texCube", BGFX_UNIFORM_TYPE.SAMPLER);
        s_texColor = Uniform.createSingle("s_texColor", BGFX_UNIFORM_TYPE.SAMPLER);
        s_texLum = Uniform.createSingle("s_texLum", BGFX_UNIFORM_TYPE.SAMPLER);
        s_texBlur = Uniform.createSingle("s_texBlur", BGFX_UNIFORM_TYPE.SAMPLER);
        u_mtx = Uniform.createSingle("u_mtx", BGFX_UNIFORM_TYPE.MAT4);
        u_tonemap = Uniform.createSingle("u_tonemap", BGFX_UNIFORM_TYPE.VEC4);
        u_offset = Uniform.createArray("u_offset", BGFX_UNIFORM_TYPE.VEC4, 16);

        try {
            m_mesh = load(Objects.requireNonNull(Hdr.class.getResource("/meshes/teapot.obj")));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

//        todo m_fbh.idx = invalid handle?

        m_lum[0] = FrameBuffer.create(128, 128, BGRA8);
        m_lum[1] = FrameBuffer.create(64, 64, BGRA8);
        m_lum[2] = FrameBuffer.create(16, 16, BGRA8);
        m_lum[3] = FrameBuffer.create(4, 4, BGRA8);
        m_lum[4] = FrameBuffer.create(1, 1, BGRA8);

        m_bright = FrameBuffer.createScaled(BGFX_BACKBUFFER_RATIO.HALF, BGRA8);
        m_blur = FrameBuffer.createScaled(BGFX_BACKBUFFER_RATIO.EIGHTH, BGRA8);

        m_lumBgra8 = 0;
        if (Capabilities.allSupported(TEXTURE_BLIT, TEXTURE_READ_BACK)) {
            // todo return to me
            final TextureFlags flags = TextureFlags.create(Set.of(READ_BACK), emptySet());
            m_rb = Texture2D.createMutableEmpty(1, 1, false, 1, BGRA8, flags);
        } else {
            m_rb = null; // todo kInvalidHandle?
        }

//        m_caps = bgfx::getCaps();
        s_texelHalf = computeTextelHalf();
        m_oldWidth  = 0;
        m_oldHeight = 0;
        BGFX_RESET m_oldReset  = m_reset;
        m_speed      = 0.37f;
        m_middleGray = 0.18f;
        m_white      = 1.1f;
        m_threshold  = 1.5f;
        m_scrollArea = 0;
        m_time = 0.0f;
    }

    private float computeTextelHalf() {
        switch (Capabilities.getRendererType()) {
            case DIRECT3D9: return 0.5f;
            default: return 0.0f;
        }
    }

    @Override
    public void render(float dt, float time) {

    }

    @Override
    public void dispose() {
        // todo xd
    }

    public static void main(String[] args) throws IOException {
        new Hdr().start();
    }


    // todo generify model loading

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

//    public static Model load(URL resource, int color, float scale, int z) throws Exception {
//
//        // vert layout is 3 floats (pos), one 32-bit int (color)
//        // load these into vert array
//
//        // then use faces to load index array
//
//        int numVerts = 0;
//        int numIndices = 0;
//        final Number[][] verts;
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
////            verts = new byte[numVert  s * 4 * 4]; // remember colors need to be added too
//            verts = new Number[numVerts][4];
//            faces = new short[numIndices * 3];
//        }
//
//        try(final BufferedReader reader = new BufferedReader(new FileReader(resource.getFile()))) {
//            int vi = 0, fi = 0; int ci = 0;
//
////            final float scale = 0.5f;
//            String line;
//            while ((line = reader.readLine()) != null) {
//                final String[] split = line.split(" "); // v or f then 3 floats or shorts
//                if (split[0].equals("v")) {
////                    for (byte b : floatToBytes(Float.parseFloat(split[1]))) verts[vi++] = b;
////                    for (byte b : floatToBytes(Float.parseFloat(split[2]))) verts[vi++] = b;
////                    for (byte b : floatToBytes(Float.parseFloat(split[3]))) verts[vi++] = b;
////                    for (byte b : intToBytes(0x00FAFAFA)) verts[vi++] = b;
//                    verts[vi][0] = Float.parseFloat(split[1]) * scale;
//                    verts[vi][1] = Float.parseFloat(split[2]) * scale;
//                    verts[vi][2] = Float.parseFloat(split[3]) * scale + z;
////                    verts[vi][3] = colors[(4 + ((ci++) / 720)) % colors.length];
////                    verts[vi][3] = 0x00ff00ff;
//                    // a b g r
//                    verts[vi][3] = color;
//                    vi++;
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

}
