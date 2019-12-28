//package com.bariumhoof.bgfx4j.examples._03_raymarch;
//
//import com.bariumhoof.bgfx4j.Application;
//import com.bariumhoof.bgfx4j.encoder.Encoder;
//import com.bariumhoof.bgfx4j.enums.BGFX_ATTRIB;
//import com.bariumhoof.bgfx4j.enums.BGFX_ATTRIB_TYPE;
//import com.bariumhoof.bgfx4j.enums.BGFX_STATE;
//import com.bariumhoof.bgfx4j.enums.BGFX_UNIFORM_TYPE;
//import com.bariumhoof.bgfx4j.view.View;
//import com.bariumhoof.bgfx4j.wip.*;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.extern.slf4j.Slf4j;
//import org.joml.Matrix4f;
//import org.joml.Vector3f;
//import org.joml.Vector4f;
//
//import java.io.IOException;
//import java.nio.ByteBuffer;
//import java.util.ArrayList;
//
///**
// * Port of:
// * https://github.com/bkaradzic/bgfx/blob/master/examples/03-raymarch/screenshot.png
// */
//@Slf4j
//public class Raymarch extends Application {
//
//    public Raymarch() {
//
//    }
//
//    @Data
//    @AllArgsConstructor
//    private static class PosColorTexCoord0Vertex {
//        float m_x, m_y, m_z;
//        int m_abgr;
//        float m_u, m_v;
//    }
//
//    static VertexLayout ms_layout;
//
//    /*
//    entry::MouseState m_mouseState;
//
//	uint32_t m_width;
//	uint32_t m_height;
//	uint32_t m_debug;
//	uint32_t m_reset;
//
//	int64_t m_timeOffset;
//	bgfx::UniformHandle u_mtx;
//	bgfx::UniformHandle u_lightDirTime;
//	bgfx::ProgramHandle m_program;
//     */
//
//    Program program;
//
//    Uniform u_mtx = null;
//    Uniform u_lightDirTime = null;
//
//    @Override
//    public void init() {
//        ms_layout = VertexLayout.builder()
//                .beginWith(BGFX_ATTRIB.POSITION, BGFX_ATTRIB_TYPE.FLOAT)
//                .thenUseNormalized(BGFX_ATTRIB.COLOR0, BGFX_ATTRIB_TYPE.UINT8)
//                .thenUse(BGFX_ATTRIB.TEXCOORD0, BGFX_ATTRIB_TYPE.FLOAT)
//                .build();
//
//        u_mtx = Uniform.create("u_mtx", BGFX_UNIFORM_TYPE.MAT4);
//        u_lightDirTime = Uniform.create("u_lightDirTime", BGFX_UNIFORM_TYPE.VEC4);
//
//        program = Program.loadOrNull(
//                Application.locateVertexShaderByName("raymarching"),
//                Application.locateFragmentShaderByName("raymarching")
//        );
//    }
//
//    @Override
//    public void render(float frameTime, float time) {
//        final View view_1 = View.create("view_1");
//        final View view_2 = View.create("view_2");
//
//        view_1.setViewRect(0, 0, width, height);
//        view_2.setViewRect(0, 0, width, height);
//
//        view_1.touch();
////        view_2.touch();
//
//        final Vector3f at = new Vector3f(0.0f, 0.0f, 0.0f);
//        final Vector3f eye = new Vector3f(0.0f, 0.0f, -15.0f);
//
//        final Matrix4f view = new Matrix4f();
//        final Matrix4f proj = new Matrix4f();
//
//        lookAt(at, eye, view);
//        perspective(60.0f, getWidth(), getHeight(), 0.1f, 100.0f, proj);
//
//        view_1.setTransform(view, proj);
//
//        final Matrix4f ortho = new Matrix4f();
//        Application.ortho(0.0f, 1280.0f, 720.0f, 0.0f, 0.0f, 100.0f, ortho);
//
//        view_2.setTransform(null, ortho);
//
//        final Matrix4f vp = new Matrix4f();
//        view.mul(proj, vp); // multiple view and proj and store in vp
//
//        final Matrix4f mtx = new Matrix4f();
//        mtx.rotateTowardsXY(time, time*0.37f);
//
//        final Matrix4f mtxInv = new Matrix4f();
//        mtx.invert(mtxInv);
//
//        final Vector4f lightDirTime = new Vector4f();
//        final Vector3f out3 = new Vector3f();
//        final Vector3f lightDirModelN = new Vector3f(-0.4f, -0.5f, -1.0f).normalize();
//        lightDirModelN.mulDirection(mtxInv, out3); // todo is this right? very confused about bx::mul store usage
//
//        // see: https://github.com/bkaradzic/bgfx/blob/6a5d1b1c7ac5b01b2dfff4db9b571d5466e932d9/examples/03-raymarch/raymarch.cpp#L229
//        lightDirTime.set(out3.x, out3.y, out3.z, time);
//
////        bgfx_set_uniform();
//        // todo clean up setValue. Use different array lib (libgdx?)
//        u_lightDirTime.setValue(lightDirTime.x, lightDirTime.y, lightDirTime.z, lightDirTime.w);
//
//        final Matrix4f mvp = new Matrix4f();
//        mtx.mul(vp, mvp); // mvp = mtx * vp
//
//        final Matrix4f invMvp = new Matrix4f();
//        mvp.invert(invMvp); // invMvp = inverse(mvp)
//
//        float[] _mvp = new float[16];
//        mvp.get(_mvp);
//        u_mtx.setValue(_mvp);
//
////        renderScreenSpaceQuad(view_2, program, 0.0f, 0.0f, 1280.0f, 720.0f);
//        renderScreenSpaceQuad(view_1, program, 0.0f, 0.0f, 1280.0f, 720.0f);
//    }
//
//    @Override
//    public void dispose() {
//        u_mtx.dispose();
//        u_lightDirTime.dispose();
//    }
//
//    private static void renderScreenSpaceQuad(View view, Program program, float _x, float _y, float _width, float _height)
//    {
////        bgfx::TransientVertexBuffer tvb;
////        bgfx::TransientIndexBuffer tib;
//        TransientVertexBuffer tvb = TransientVertexBuffer.allocInts(4, ms_layout);
//        TransientIndexBuffer tib = TransientIndexBuffer.allocInts(6);
//
////        final TransientBuffer result = TransientBuffer.allocTransientBuffers(ms_layout, 4, 6, true, true);
//
////        if (result == null) {
////            log.warn("got null transientBuffer");
////            return;
////        }
////
////        tvb = result.getTransientVertexBuffer();
////        tib = result.getTransientIndexBuffer();
//
////        if (bgfx::allocTransientBuffers(&tvb, PosColorTexCoord0Vertex::ms_layout, 4, &tib, 6) ){
//        ArrayList<PosColorTexCoord0Vertex> vertex = new ArrayList<>();
//
//        ByteBuffer buf = tvb.getBuf().data();
//
//        while (buf.hasRemaining()) {
//            PosColorTexCoord0Vertex next = new PosColorTexCoord0Vertex(
//                    buf.getFloat(),
//                    buf.getFloat(),
//                    buf.getFloat(),
//                    buf.getInt(),
//                    buf.getFloat(),
//                    buf.getFloat()
//            );
//
//            vertex.add(next);
//        }
////        tvb.getBuf().data().rewind(); // todo rewind correct here?
//
//
//        float zz = 0.0f;
//
//        final float minx = _x;
//        final float maxx = _x + _width;
//        final float miny = _y;
//        final float maxy = _y + _height;
//
//        float minu = -1.0f;
//        float minv = -1.0f;
//        float maxu =  1.0f;
//        float maxv =  1.0f;
//
//        vertex.get(0).m_x = minx;
//        vertex.get(0).m_y = miny;
//        vertex.get(0).m_z = zz;
//        vertex.get(0).m_abgr = 0xff0000ff;
//        vertex.get(0).m_u = minu;
//        vertex.get(0).m_v = minv;
//
//        vertex.get(1).m_x = maxx;
//        vertex.get(1).m_y = miny;
//        vertex.get(1).m_z = zz;
//        vertex.get(1).m_abgr = 0xff00ff00;
//        vertex.get(1).m_u = maxu;
//        vertex.get(1).m_v = minv;
//
//        vertex.get(2).m_x = maxx;
//        vertex.get(2).m_y = maxy;
//        vertex.get(2).m_z = zz;
//        vertex.get(2).m_abgr = 0xffff0000;
//        vertex.get(2).m_u = maxu;
//        vertex.get(2).m_v = maxv;
//
//        vertex.get(3).m_x = minx;
//        vertex.get(3).m_y = maxy;
//        vertex.get(3).m_z = zz;
//        vertex.get(3).m_abgr = 0xffffffff;
//        vertex.get(3).m_u = minu;
//        vertex.get(3).m_v = maxv;
//
////        uint16_t* indices = (uint16_t*)tib.data;
//
//        final ByteBuffer indexData = tib.getBuf().data();
//        indexData.putInt(0);
//        indexData.putInt(2);
//        indexData.putInt(1);
//        indexData.putInt(0);
//        indexData.putInt(3);
//        indexData.putInt(2);
//        indexData.rewind();
//
//
////        bgfx::setState(BGFX_STATE_DEFAULT);
////        bgfx::setIndexBuffer(&tib);
////        bgfx::setVertexBuffer(0, &tvb);
////        bgfx::submit(_view, _program);
////        bgfx_set_index_buffer(tib.handle(), 0, tib.size());
////        bgfx_set_vertex_buffer(0, tvb.handle(), 0, tvb.size());
////        bgfx_submit(view.id(), program.handle(), 0, false);
//
//        Encoder encoder = Encoder.begin(false);
//        encoder.setVertexBuffer(tvb);
//        encoder.setTransientIndexBuffer(tib);
//        encoder.setState(BGFX_STATE.DEFAULT);
//        encoder.submit(view, program);
//        encoder.end();
//    }
//
//
//    public static void main(String[] args) throws IOException {
//        new Raymarch().start();
//    }
//}
//
//
//
//
//
//
//
//
//
//
