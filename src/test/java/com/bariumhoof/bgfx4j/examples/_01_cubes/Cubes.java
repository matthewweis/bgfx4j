package com.bariumhoof.bgfx4j.examples._01_cubes;

import com.bariumhoof.bgfx4j.Application;
import com.bariumhoof.bgfx4j.TestLauncher;
import com.bariumhoof.bgfx4j.enums.*;
import com.bariumhoof.bgfx4j.view.ClearStrategy;
import com.bariumhoof.bgfx4j.view.View;
import com.bariumhoof.bgfx4j.wip.IndexBuffer;
import com.bariumhoof.bgfx4j.wip.Program;
import com.bariumhoof.bgfx4j.wip.VertexBuffer;
import com.bariumhoof.bgfx4j.wip.VertexDecl;
import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.lwjgl.bgfx.BGFX;
import org.lwjgl.bgfx.BGFXStats;

import java.io.IOException;
import java.util.EnumSet;

import static org.lwjgl.bgfx.BGFX.*;

public class Cubes extends Application {

    static class PosColorVertex {
        float m_x;
        float m_y;
        float m_z;
        int m_abgr;

        public PosColorVertex(float m_x, float m_y, float m_z, int m_abgr) {
            this.m_x = m_x;
            this.m_y = m_y;
            this.m_z = m_z;
            this.m_abgr = m_abgr;
        }

        final static VertexDecl ms_layout = VertexDecl.builder(BGFX_RENDERER_TYPE.METAL)
                .beginWith(BGFX_ATTRIB.POSITION, BGFX_ATTRIB_TYPE.FLOAT)
                .thenUseNormalized(BGFX_ATTRIB.COLOR0, BGFX_ATTRIB_TYPE.UINT8)
                .build();
    }

    final static VertexDecl ms_layout = PosColorVertex.ms_layout;

//    static PosColorVertex s_cubeVertices[] =
//            {
//                    new PosColorVertex(-1.0f,  1.0f,  1.0f, 0xff000000),
//                    new PosColorVertex( 1.0f,  1.0f,  1.0f, 0xff0000ff),
//                    new PosColorVertex(-1.0f, -1.0f,  1.0f, 0xff00ff00),
//                    new PosColorVertex( 1.0f, -1.0f,  1.0f, 0xff00ffff),
//                    new PosColorVertex(-1.0f,  1.0f, -1.0f, 0xffff0000),
//                    new PosColorVertex( 1.0f,  1.0f, -1.0f, 0xffff00ff),
//                    new PosColorVertex(-1.0f, -1.0f, -1.0f, 0xffffff00),
//                    new PosColorVertex( 1.0f, -1.0f, -1.0f, 0xffffffff),
//            };

    static float s_cubeVertices[][] =
            {
                    {-1.0f,  1.0f,  1.0f, 0xff000000 },
                    { 1.0f,  1.0f,  1.0f, 0xff0000ff },
                    {-1.0f, -1.0f,  1.0f, 0xff00ff00 },
                    { 1.0f, -1.0f,  1.0f, 0xff00ffff },
                    {-1.0f,  1.0f, -1.0f, 0xffff0000 },
                    { 1.0f,  1.0f, -1.0f, 0xffff00ff },
                    {-1.0f, -1.0f, -1.0f, 0xffffff00 },
                    { 1.0f, -1.0f, -1.0f, 0xffffffff },
            };

    static final short s_cubeTriList[] =
            {
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
                    6, 3, 7,
            };

    static final short s_cubeTriStrip[] =
            {
                    0, 1, 2,
                    3,
                    7,
                    1,
                    5,
                    0,
                    4,
                    2,
                    6,
                    7,
                    4,
                    5,
            };

    static final short s_cubeLineList[] =
            {
                    0, 1,
                    0, 2,
                    0, 4,
                    1, 3,
                    1, 5,
                    2, 3,
                    2, 6,
                    3, 7,
                    4, 5,
                    4, 6,
                    5, 7,
                    6, 7,
            };

    static final short s_cubeLineStrip[] =
            {
                    0, 2, 3, 1, 5, 7, 6, 4,
                    0, 2, 6, 4, 5, 7, 3, 1,
                    0,
            };

    static final short s_cubePoints[] =
            {
                    0, 1, 2, 3, 4, 5, 6, 7
            };

    static final String s_ptNames[] =
    {
        "Triangle List",
                "Triangle Strip",
                "Lines",
                "Line Strip",
                "Points"
    };

//    static final long s_ptState[] =
//    {
//        Long.parseUnsignedLong("0"), // was UINT64_C(0)
//                BGFX_STATE_PT_TRISTRIP,
//                BGFX_STATE_PT_LINES,
//                BGFX_STATE_PT_LINESTRIP,
//                BGFX_STATE_PT_POINTS
//    };

    static final BGFX_STATE s_ptState[] =
            {
                    BGFX_STATE.NONE,
                    BGFX_STATE.PT_TRISTRIP,
                    BGFX_STATE.PT_LINES,
                    BGFX_STATE.PT_LINESTRIP,
                    BGFX_STATE.PT_POINTS,
            };

    int m_pt = 0;

    boolean m_r = true;
    boolean m_g = true;
    boolean m_b = true;
    boolean m_a = true;

    final ClearStrategy clearStrategy = ClearStrategy.just(BGFX_CLEAR.COLOR);
    final BGFX_VIEW_MODE viewMode = BGFX_VIEW_MODE.DEFAULT;
//    final BGFX_VIEW_MODE viewMode = null;
    View view = null;

    VertexBuffer m_vbh;
    IndexBuffer[] m_ibh = new IndexBuffer[5];
    Program m_program;


    @Override
    public void init() {
        view = View.create("main view", clearStrategy, viewMode);

        m_vbh = VertexBuffer.create(ms_layout, s_cubeVertices);
        m_ibh[0] = IndexBuffer.create(s_cubeTriList);
        m_ibh[1] = IndexBuffer.create(s_cubeTriStrip);
        m_ibh[2] = IndexBuffer.create(s_cubeLineList);
        m_ibh[3] = IndexBuffer.create(s_cubeLineStrip);
        m_ibh[4] = IndexBuffer.create(s_cubePoints);

        m_program = Program.loadOrNull(
                Cubes.class.getResource("/shaders/metal/cubes.vert"), // vertex shader
                Cubes.class.getResource("/shaders/metal/cubes.frag")  // fragment shader
        );


//        bgfx_set_view_clear(0, BGFX_CLEAR.COLOR.VALUE | BGFX_CLEAR.DEPTH.VALUE, 0x303030ff, 1.0f, 0);

    }

    float time = 0.0f;

    @Override
    public void render(double dt) {
        time += dt*500;
        bgfx_set_view_rect(0, 0, 0, getWidth(), getHeight());

        bgfx_dbg_text_printf(0, 1, 0x1f, "bgfx/examples/01-cubes");
        bgfx_dbg_text_printf(0, 2, 0x3f, "Description: Rendering simple static mesh");

        // This dummy draw call is here to make sure that view 0 is cleared
        // if no other draw calls are submitted to view 0.
//        bgfx_touch(0);

        // Set render states.

        final var state = EnumSet.of(
                BGFX_STATE.WRITE_Z,
                BGFX_STATE.DEPTH_TEST_LESS,
                BGFX_STATE.CULL_CW,
                BGFX_STATE.MSAA,
                s_ptState[m_pt]
        );

        if (m_r) { state.add(BGFX_STATE.WRITE_R); }
        if (m_g) { state.add(BGFX_STATE.WRITE_G); }
        if (m_b) { state.add(BGFX_STATE.WRITE_B); }
        if (m_a) { state.add(BGFX_STATE.WRITE_A); }

        long _state = state.stream().reduce(0L, (n, s) -> n | s.VALUE, (n1, n2) -> n1 | n2);

        bgfx_set_state(_state, (int)BGFX_STATE_BLEND.NORMAL.VALUE);
//        bgfx_set_state(BGFX_STATE.DEFAULT.VALUE, (int)BGFX_STATE_BLEND.NORMAL.VALUE);

        // todo figure out why this causes us to lose sight of anything!
        setViewTransform();

        for (int yy = 0; yy < 11; ++yy) {
            for (int xx = 0; xx < 11; ++xx) {
                float[] _mtx = new float[16];


                var mtx = new Matrix4f();
                mtx.rotateXYZ(time + xx*0.21f, time + yy*0.37f, 0.0f);
                mtx.scale(0.3f);
                _mtx = mtx.get(_mtx);
//                _mtx[12] = -15.0f + ((float) xx)*3.0f;
//                _mtx[13] = -15.0f + ((float) yy)*3.0f;
//                _mtx[14] = 0.0f;

                // Set model matrix for rendering.
                bgfx_set_transform(_mtx);

                final var ibh = m_ibh[m_pt];


                // Set vertex and index buffer.
//                bgfx::setVertexBuffer(0, m_vbh);
                bgfx_set_vertex_buffer(0, m_vbh.handle(), 0, m_vbh.size());
//                bgfx::setIndexBuffer(ibh);
                bgfx_set_index_buffer(ibh.handle(), 0, ibh.size());

                // Submit primitive for rendering to view 0.
//                bgfx::submit(0, m_program);
                bgfx_submit(0, m_program.handle(), BGFX_CLEAR_DEPTH, false);
            }
        }
    }

    @Override
    public void dispose() {
        for (IndexBuffer indexBuffer : m_ibh) {
            if (indexBuffer != null) {
                indexBuffer.dispose();
            }
        }

        if (m_vbh != null) {
            m_vbh.dispose();
        }

        if (m_program != null) {
            m_program.dispose();
        }
    }

    private void setViewTransform() {
        Matrix4f viewMat = new Matrix4f();
        Matrix4f projMat = new Matrix4f();

        final var eye = new Vector3f(0.0f, 0.0f, -35.0f); // cam pos
        final var at = new Vector3f(0.0f, 0.0f, 0.0f); // point in space to look at
//        final var up = new Vector3f(0.0f, 1.0f, 0.0f); // direction of up
//        viewMat.setLookAt(eye, at, up);
        lookAt(at, eye, viewMat);

        // see: https://github.com/bkaradzic/bx/blob/master/include/bx/math.h#L481
//        final float aspect = ((float)getWidth()) / ((float)getHeight());
//        projMat.setPerspective(60.0f, aspect, 0.1f, 100.0f, Application.isZZeroToOne());
        perspective(60.0f, getWidth(), getHeight(), 0.1f, 100.0f, projMat);

        float[] _view = new float[16];
        float[] _proj = new float[16];

        _view = viewMat.get(_view);
        _proj = viewMat.get(_proj);

        view.setTransform(_view, _proj);
    }

//    private void setViewTransform() {
//        Matrix4f viewMat = new Matrix4f();
//        Matrix4f projMat = new Matrix4f();
//
//        final var eye = new Vector3f(0.0f, 0.0f, -35.0f);
//        final var at = new Vector3f(0.0f, 0.0f, 0.0f);
//        final var up = new Vector3f(0.0f, 1.0f, 0.0f);
//        viewMat.setLookAtLH(eye, at, up);
//
//        // see: https://github.com/bkaradzic/bx/blob/master/include/bx/math.h#L481
//        final float aspect = ((float)getWidth()) / ((float)getHeight());
//        projMat.setPerspectiveLH(60.0f, aspect, 0.1f, 100.0f, bgfx_get_caps().homogeneousDepth());
//
//        float[] _view = new float[16];
//        float[] _proj = new float[16];
//
//        _view = viewMat.get(_view);
//        _proj = viewMat.get(_proj);
//
//        view.setTransform(_view, _proj);
//    }

    public static void main(String[] args) throws IOException {
        new Cubes().start();
    }

}
