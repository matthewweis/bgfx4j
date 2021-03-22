package com.bariumhoof.bgfx4j.examples._06_bump;

import com.bariumhoof.Capabilities;
import com.bariumhoof.bgfx4j.Application;
import com.bariumhoof.bgfx4j.buffer.StaticIndexBuffer;
import com.bariumhoof.bgfx4j.enums.BGFX_CAPS;
import com.bariumhoof.bgfx4j.enums.BGFX_STATE;
import com.bariumhoof.bgfx4j.enums.BGFX_UNIFORM_TYPE;
import com.bariumhoof.bgfx4j.layout.StaticVertexBuffer;
import com.bariumhoof.bgfx4j.layout.Vec;
import com.bariumhoof.bgfx4j.layout.VertexLayout;
import com.bariumhoof.bgfx4j.shaders.Program;
import com.bariumhoof.bgfx4j.view.View;
import com.bariumhoof.bgfx4j.wip.Encoder;
import com.bariumhoof.bgfx4j.wip.Texture;
import com.bariumhoof.bgfx4j.wip.Uniform;
import org.jetbrains.annotations.NotNull;
import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.lwjgl.bgfx.BGFXInstanceDataBuffer;
import org.lwjgl.bgfx.BGFXVertexLayout;
import org.lwjgl.system.MemoryUtil;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.util.EnumSet;
import java.util.List;

import static com.bariumhoof.bgfx4j.layout.Vec.*;
import static com.bariumhoof.bgfx4j.layout.Vertex.Vertex4;
import static com.bariumhoof.bgfx4j.layout.Vertex.vertex;
import static org.lwjgl.bgfx.BGFX.*;

/**
 * Port of:
 * https://github.com/LWJGL/lwjgl3-demos/blob/master/src/org/lwjgl/demo/bgfx/Bump.java
 */
public class Bump extends Application {

    private View view;

    ByteBuffer vbTans;
    private VertexLayout<Vertex4<FLOAT_Vec3, UINT8_Vec4, UINT8_Vec4, INT16_Vec2>> layout;
    private StaticVertexBuffer<Vertex4<FLOAT_Vec3, UINT8_Vec4, UINT8_Vec4, INT16_Vec2>> vb;
    private StaticIndexBuffer ib;
    private Uniform uniformTexColor;
    private Uniform uniformTexNormal;
    private int numLights;
    private Uniform uniformLightPosRadius;
    private Uniform uniformLightRgbInnerR;
    private Program program;

    private Texture textureColor;
    private Texture textureNormal;
    private boolean instancingSupported;

    private Matrix4f viewMat = new Matrix4f();
    private FloatBuffer viewBuf;
    private Matrix4f projMat = new Matrix4f();
    private FloatBuffer projBuf;
    private Matrix4f mtxMat = new Matrix4f();
    private FloatBuffer mtxBuf;
    private float[] uniformBuf;

    @Override
    public void init() {
        view = View.create("My view");

        layout = VertexLayout.builder()
                .position().float_vec3().then()
                .normal().uint8_vec4().normalized().asInt().then()
                .tangent().uint8_vec4().normalized().asInt().then()
                .texcoord0().int16_vec2().normalized().asInt().build();

        instancingSupported = Capabilities.isSupported(BGFX_CAPS.INSTANCING);

        vb = StaticVertexBuffer.create(verts, layout);
        ib = StaticIndexBuffer.create(cubeIndices);
        vbTans = calcTangents(verts, verts.size(), layout.get(), cubeIndices, cubeIndices.length);

        uniformTexColor = Uniform.createSingle("s_texColor", BGFX_UNIFORM_TYPE.VEC4);
        uniformTexNormal = Uniform.createSingle("s_texNormal", BGFX_UNIFORM_TYPE.VEC4);

        numLights = 4;
        uniformLightPosRadius = Uniform.createArray("u_lightPosRadius", BGFX_UNIFORM_TYPE.VEC4, numLights);
        uniformLightRgbInnerR = Uniform.createArray("u_lightRgbInnerR", BGFX_UNIFORM_TYPE.VEC4, numLights);

        program = Program.loadOrNull(
                Application.locateVertexShaderByName(instancingSupported ? "bump-instanced" : "bump"), // vertex shader
                Application.locateFragmentShaderByName("bump")); // fragment shader

        textureColor = Texture.loadOrNull(Bump.class.getResource("/textures/fieldstone-rgba.dds"));
        textureNormal = Texture.loadOrNull(Bump.class.getResource("/textures/fieldstone-n.dds"));

        viewBuf = MemoryUtil.memAllocFloat(16);
        projBuf = MemoryUtil.memAllocFloat(16);
        mtxBuf = MemoryUtil.memAllocFloat(16);
        uniformBuf = new float[16];
    }

    private static final float[][] lightRgbInnerR = {
            { 1.0f, 0.7f, 0.2f, 0.8f },
            { 0.7f, 0.2f, 1.0f, 0.8f },
            { 0.2f, 1.0f, 0.7f, 0.8f },
            { 1.0f, 0.4f, 0.2f, 0.8f }
    };

    @Override
    public void render(float dt, float time) {
        bgfx_dbg_text_printf(0, 1, 0x4f, "bgfx/examples/06-bump");
        bgfx_dbg_text_printf(0, 2, 0x6f, "Description: Loading textures.");
        bgfx_dbg_text_printf(0, 3, 0x0f, String.format("Frame: % 7.3f[ms]", dt));

        final Vector3f eye = new Vector3f(0.0f, 0.0f, -7.0f);

        lookAt(new Vector3f(0.0f, 0.0f, 0.0f), eye, viewMat);
        perspective(60.0f, getWidth(), getHeight(), 0.1f, 100.0f, projMat);

        view.setTransform(viewMat.get(viewBuf), projMat.get(projBuf));

        view.setViewRect(0, 0, getWidth(), getHeight());

        for (int ii = 0; ii < numLights; ++ii) {
            uniformBuf[4*ii] = ((float) (Math.sin((time * (0.1f + ii * 0.17f) + ii * Math.PI * 0.5f * 1.37f)) * 3.0f));
            uniformBuf[4*ii + 1] = ((float) (Math.cos((time * (0.2f + ii * 0.29f) + ii * Math.PI * 0.5f * 1.49f)) * 3.0f));
            uniformBuf[4*ii + 2] = (-2.5f);
            uniformBuf[4*ii + 3] = (3.0f);
        }

        final Encoder encoder = Encoder.begin(false);

        encoder.setUniform(uniformLightPosRadius, uniformBuf);

        int i = 0;
        for (float[] ll : lightRgbInnerR) {
            for (float l : ll) {
                uniformBuf[i++] = l;
            }
        }

        encoder.setUniform(uniformLightRgbInnerR, uniformBuf);

        int instanceStride = 64;
        int numInstances = 3;

        if (instancingSupported) {
            // Write instance data for 3x3 cubes.
            for (int yy = 0; yy < 3; ++yy) {
                // todo use bgfx api
                BGFXInstanceDataBuffer idb = BGFXInstanceDataBuffer.calloc();
                bgfx_alloc_instance_data_buffer(idb, numInstances, instanceStride);
                ByteBuffer data = idb.data();

                for (int xx = 0; xx < 3; ++xx) {
                    mtxMat.setRotationXYZ(time * 0.023f + xx * 0.21f, time * 0.03f + yy * 0.37f, 0.0f)
                            .setTranslation(-3.0f + xx * 3.0f, -3.0f + yy * 3.0f, 0.0f)
                            .get(data);
                    data.position(data.position() + instanceStride);
                }

                // Set instance data buffer.
                bgfx_encoder_set_instance_data_buffer(encoder.id(), idb, 0, numInstances);

                // Set vertex and index buffer.
                encoder.setVertexBuffer(vb);
                encoder.setIndexBuffer(ib);

                // also works for VB (using calcTangents)
//                final BGFXMemory bgfxMemory = bgfx_make_ref(vbTans);
//                final short vb2 = bgfx_create_vertex_buffer(bgfxMemory, layout.get(), 0);
//                bgfx_set_vertex_buffer(0, vb2, 0, verts.size());

                // Bind textures.
                encoder.setTexture(0, uniformTexColor, textureColor);
                encoder.setTexture(1, uniformTexNormal, textureNormal);

                // Set render states.
                encoder.setState(EnumSet.of(BGFX_STATE.WRITE_RGB, BGFX_STATE.WRITE_A, BGFX_STATE.WRITE_Z, BGFX_STATE.DEPTH_TEST_LESS, BGFX_STATE.MSAA));

                // Submit primitive for rendering to view 0.
                encoder.submit(view, program);
                idb.free();
            }
        } else {
            for (int yy = 0; yy < 3; ++yy) {
                for (int xx = 0; xx < 3; ++xx) {
                    // Set transform for draw call.
                    encoder.setTransform(
                            mtxMat.setRotationXYZ(time * 0.023f + xx * 0.21f, time * 0.03f + yy * 0.37f, 0.0f)
                                    .setTranslation(-3.0f + xx * 3.0f, -3.0f + yy * 3.0f, 0.0f)
                                    .get(mtxBuf));

                    // Set vertex and index buffer.
                    encoder.setVertexBuffer(vb);
                    encoder.setIndexBuffer(ib);

                    // Bind textures.
                    encoder.setTexture(0, uniformTexColor, textureColor);
                    encoder.setTexture(1, uniformTexNormal, textureNormal);

                    // Set render states.
                    encoder.setState(EnumSet.of(BGFX_STATE.WRITE_RGB, BGFX_STATE.WRITE_A, BGFX_STATE.WRITE_Z, BGFX_STATE.DEPTH_TEST_LESS, BGFX_STATE.MSAA));

                    // Submit primitive for rendering to view 0.
                    encoder.submit(view, program);
                }
            }
        }

        encoder.end();
    }

    @Override
    public void dispose() {
        ib.dispose();
        vb.dispose();
        program.dispose();

        textureColor.dispose();
        textureNormal.dispose();
        uniformTexColor.dispose();
        uniformTexNormal.dispose();
        uniformLightPosRadius.dispose();
        uniformLightRgbInnerR.dispose();

        MemoryUtil.memFree(viewBuf);
        MemoryUtil.memFree(projBuf);
        MemoryUtil.memFree(mtxBuf);

        layout.dispose();
    }

    private static int packUint32(int _x, int _y, int _z, int _w) {
        return ((_w & 0xff) << 24) | ((_z & 0xff) << 16) | ((_y & 0xff) << 8) | (_x & 0xff);
    }

    private static int packF4u(float _x, float _y, float _z) {
        return packF4u(_x, _y, _z, 0.0f);
    }

    private static int packF4u(float _x, float _y, float _z, float _w) {
        int xx = (int) (_x * 127.0f + 128.0f);
        int yy = (int) (_y * 127.0f + 128.0f);
        int zz = (int) (_z * 127.0f + 128.0f);
        int ww = (int) (_w * 127.0f + 128.0f);
        return packUint32(xx, yy, zz, ww);
    }

    private static final List<Vertex4<@NotNull FLOAT_Vec3, @NotNull UINT8_Vec4, @NotNull UINT8_Vec4, @NotNull INT16_Vec2>> verts = List.of(
            vertex(float_vec3(-1.0f, 1.0f, 1.0f), encodeNormalRgba8(0.0f, 0.0f, 1.0f), uint8_vec4(0), int16_vec2(0, 0)),
            vertex(float_vec3(1.0f, 1.0f, 1.0f), encodeNormalRgba8(0.0f, 0.0f, 1.0f), uint8_vec4(0), int16_vec2(0x7fff, 0)),
            vertex(float_vec3(-1.0f, -1.0f, 1.0f), encodeNormalRgba8(0.0f, 0.0f, 1.0f), uint8_vec4(0), int16_vec2(0, 0x7fff)),
            vertex(float_vec3(1.0f, -1.0f, 1.0f), encodeNormalRgba8(0.0f, 0.0f, 1.0f), uint8_vec4(0), int16_vec2(0x7fff, 0x7fff)),
            vertex(float_vec3(-1.0f, 1.0f, -1.0f), encodeNormalRgba8(0.0f, 0.0f, -1.0f), uint8_vec4(0), int16_vec2(0, 0)),
            vertex(float_vec3(1.0f, 1.0f, -1.0f), encodeNormalRgba8(0.0f, 0.0f, -1.0f), uint8_vec4(0), int16_vec2(0x7fff, 0)),
            vertex(float_vec3(-1.0f, -1.0f, -1.0f), encodeNormalRgba8(0.0f, 0.0f, -1.0f), uint8_vec4(0), int16_vec2(0, 0x7fff)),
            vertex(float_vec3(1.0f, -1.0f, -1.0f), encodeNormalRgba8(0.0f, 0.0f, -1.0f), uint8_vec4(0), int16_vec2(0x7fff, 0x7fff)),
            vertex(float_vec3(-1.0f, 1.0f, 1.0f), encodeNormalRgba8(0.0f, 1.0f, 0.0f), uint8_vec4(0), int16_vec2(0, 0)),
            vertex(float_vec3(1.0f, 1.0f, 1.0f), encodeNormalRgba8(0.0f, 1.0f, 0.0f), uint8_vec4(0), int16_vec2(0x7fff, 0)),
            vertex(float_vec3(-1.0f, 1.0f, -1.0f), encodeNormalRgba8(0.0f, 1.0f, 0.0f), uint8_vec4(0), int16_vec2(0, 0x7fff)),
            vertex(float_vec3(1.0f, 1.0f, -1.0f), encodeNormalRgba8(0.0f, 1.0f, 0.0f), uint8_vec4(0), int16_vec2(0x7fff, 0x7fff)),
            vertex(float_vec3(-1.0f, -1.0f, 1.0f), encodeNormalRgba8(0.0f, -1.0f, 0.0f), uint8_vec4(0), int16_vec2(0, 0)),
            vertex(float_vec3(1.0f, -1.0f, 1.0f), encodeNormalRgba8(0.0f, -1.0f, 0.0f), uint8_vec4(0), int16_vec2(0x7fff, 0)),
            vertex(float_vec3(-1.0f, -1.0f, -1.0f), encodeNormalRgba8(0.0f, -1.0f, 0.0f), uint8_vec4(0), int16_vec2(0, 0x7fff)),
            vertex(float_vec3(1.0f, -1.0f, -1.0f), encodeNormalRgba8(0.0f, -1.0f, 0.0f), uint8_vec4(0), int16_vec2(0x7fff, 0x7fff)),
            vertex(float_vec3(1.0f, -1.0f, 1.0f), encodeNormalRgba8(1.0f, 0.0f, 0.0f), uint8_vec4(0), int16_vec2(0, 0)),
            vertex(float_vec3(1.0f, 1.0f, 1.0f), encodeNormalRgba8(1.0f, 0.0f, 0.0f), uint8_vec4(0), int16_vec2(0x7fff, 0)),
            vertex(float_vec3(1.0f, -1.0f, -1.0f), encodeNormalRgba8(1.0f, 0.0f, 0.0f), uint8_vec4(0), int16_vec2(0, 0x7fff)),
            vertex(float_vec3(1.0f, 1.0f, -1.0f), encodeNormalRgba8(1.0f, 0.0f, 0.0f), uint8_vec4(0), int16_vec2(0x7fff, 0x7fff)),
            vertex(float_vec3(-1.0f, -1.0f, 1.0f), encodeNormalRgba8(-1.0f, 0.0f, 0.0f), uint8_vec4(0), int16_vec2(0, 0)),
            vertex(float_vec3(-1.0f, 1.0f, 1.0f), encodeNormalRgba8(-1.0f, 0.0f, 0.0f), uint8_vec4(0), int16_vec2(0x7fff, 0)),
            vertex(float_vec3(-1.0f, -1.0f, -1.0f), encodeNormalRgba8(-1.0f, 0.0f, 0.0f), uint8_vec4(0), int16_vec2(0, 0x7fff)),
            vertex(float_vec3(-1.0f, 1.0f, -1.0f), encodeNormalRgba8(-1.0f, 0.0f, 0.0f), uint8_vec4(0), int16_vec2(0x7fff, 0x7ff)));

    private static final int[] cubeIndices = {
            0, 2, 1,
            1, 2, 3,
            4, 5, 6,
            5, 7, 6,

            8, 10, 9,
            9, 10, 11,
            12, 13, 14,
            13, 15, 14,

            16, 18, 17,
            17, 18, 19,
            20, 21, 22,
            21, 23, 22
    };

    private static class PosTexcoord {
        final float[] m_xyz = new float[4];
        final float[] m_uv = new float[4];
    }

    private static ByteBuffer calcTangents(Iterable<Vertex4<@NotNull FLOAT_Vec3, @NotNull UINT8_Vec4, @NotNull UINT8_Vec4, @NotNull INT16_Vec2>> _vertices,
            int _numVertices, BGFXVertexLayout _layout, int[] _indices, int _numIndices) {

        float[] out = new float[4];
        float[] tangents = new float[6 * _numVertices];

        final PosTexcoord v0 = new PosTexcoord();
        final PosTexcoord v1 = new PosTexcoord();
        final PosTexcoord v2 = new PosTexcoord();

        final ByteBuffer vertices = MemoryUtil.memAlloc(_numVertices * 6 * 4);
        for (Vertex4<FLOAT_Vec3, UINT8_Vec4, UINT8_Vec4,INT16_Vec2> vv : _vertices) {
            for (Vec<?, ?> vec : vv.array()) {
                vec.put(vertices);
            }

//            vertices.putFloat((float) vv[0]);
//            vertices.putFloat((float) vv[1]);
//            vertices.putFloat((float) vv[2]);
//            vertices.putInt((int) vv[3]);
//            vertices.putInt((int) vv[4]);
//            vertices.putShort((short) (int) vv[5]);
//            vertices.putShort((short) (int) vv[6]);
        }

        vertices.flip();

        for (int ii = 0, num = _numIndices / 3; ii < num; ++ii) {
            int index0 = ii * 3;
            int i0 = _indices[index0];
            int i1 = _indices[index0 + 1];
            int i2 = _indices[index0 + 2];

            bgfx_vertex_unpack(v0.m_xyz, BGFX_ATTRIB_POSITION, _layout, vertices, i0);
            bgfx_vertex_unpack(v0.m_uv, BGFX_ATTRIB_TEXCOORD0, _layout, vertices, i0);

            bgfx_vertex_unpack(v1.m_xyz, BGFX_ATTRIB_POSITION, _layout, vertices, i1);
            bgfx_vertex_unpack(v1.m_uv, BGFX_ATTRIB_TEXCOORD0, _layout, vertices, i1);

            bgfx_vertex_unpack(v2.m_xyz, BGFX_ATTRIB_POSITION, _layout, vertices, i2);
            bgfx_vertex_unpack(v2.m_uv, BGFX_ATTRIB_TEXCOORD0, _layout, vertices, i2);

            float bax = v1.m_xyz[0] - v0.m_xyz[0];
            float bay = v1.m_xyz[1] - v0.m_xyz[1];
            float baz = v1.m_xyz[2] - v0.m_xyz[2];
            float bau = v1.m_uv[0] - v0.m_uv[0];
            float bav = v1.m_uv[1] - v0.m_uv[1];

            float cax = v2.m_xyz[0] - v0.m_xyz[0];
            float cay = v2.m_xyz[1] - v0.m_xyz[1];
            float caz = v2.m_xyz[2] - v0.m_xyz[2];
            float cau = v2.m_uv[0] - v0.m_uv[0];
            float cav = v2.m_uv[1] - v0.m_uv[1];

            float det = (bau * cav - bav * cau);
            float invDet = 1.0f / det;

            float tx = (bax * cav - cax * bav) * invDet;
            float ty = (bay * cav - cay * bav) * invDet;
            float tz = (baz * cav - caz * bav) * invDet;

            float bx = (cax * bau - bax * cau) * invDet;
            float by = (cay * bau - bay * cau) * invDet;
            float bz = (caz * bau - baz * cau) * invDet;

            for (int jj = 0; jj < 3; ++jj) {
                int _tanu = _indices[index0 + jj] * 6;
                int _tanv = _tanu + 3;

                tangents[_tanu] += tx;
                tangents[_tanu + 1] += ty;
                tangents[_tanu + 2] += tz;

                tangents[_tanv] += bx;
                tangents[_tanv + 1] += by;
                tangents[_tanv + 2] += bz;
            }
        }

        for (int ii = 0; ii < _numVertices; ++ii) {
            Vector3f tanu = new Vector3f(tangents[ii * 6], tangents[ii * 6 + 1], tangents[ii * 6 + 2]);
            Vector3f tanv = new Vector3f(tangents[ii * 6 + 3], tangents[ii * 6 + 4], tangents[ii * 6 + 5]);

            bgfx_vertex_unpack(out, BGFX_ATTRIB_NORMAL, _layout, vertices, ii);
            Vector3f normal = new Vector3f(out[0], out[1], out[2]);
            float ndt = normal.dot(tanu);

            Vector3f nxt = new Vector3f();
            normal.cross(tanu, nxt);

            Vector3f tmp = new Vector3f(tanu.x - normal.x * ndt, tanu.y - normal.y * ndt, tanu.z - normal.z * ndt);

            Vector3f tangent = new Vector3f();
            tmp.normalize(tangent);

            out[0] = tangent.x;
            out[1] = tangent.y;
            out[2] = tangent.z;
            out[3] = nxt.dot(tanv) < 0.0f ? -1.0f : 1.0f;
            bgfx_vertex_pack(out, true, BGFX_ATTRIB_TANGENT, _layout, vertices, ii);
        }

        return vertices;
    }

    static int toUnorm(float _value, float _scale) {
        return (int)(round(clamp(_value, 0.0f, 1.0f) * _scale) );
    }

    static float clamp(float f, float lower, float higher) {
        if (lower < f) {
            return lower;
        } else if (higher > f) {
            return higher;
        } else {
            return f;
        }
    }

    static float floor(float f) {
        return (float)((int)f);
    }

    static float round(float f) {
        return floor(f + 0.5f);
    }

    static byte[] packRgba8(float[] src) {
        final byte[] dst = new byte[4];
        dst[0] = (byte) toUnorm(src[0], 255.0f);
        dst[1] = (byte) toUnorm(src[1], 255.0f);
        dst[2] = (byte) toUnorm(src[2], 255.0f);
        dst[3] = (byte) toUnorm(src[3], 255.0f);
        return dst;
    }

    static UINT8_Vec4 encodeNormalRgba8(float x, float y, float z) {
        final float w = 0.0f;
        final float src[] = {
                x * 0.5f + 0.5f,
                y * 0.5f + 0.5f,
                z * 0.5f + 0.5f,
                w * 0.5f + 0.5f,
        };
        final byte[] dst = packRgba8(src);

        return uint8_vec4(dst[0], dst[1], dst[2], dst[3]);
    }

    public static void main(String[] args) throws IOException {
        new Bump().start();
    }
}
