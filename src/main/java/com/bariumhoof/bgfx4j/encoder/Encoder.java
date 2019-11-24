package com.bariumhoof.bgfx4j.encoder;

import com.bariumhoof.EnumUtils;
import com.bariumhoof.bgfx4j.enums.BGFX_STATE;
import com.bariumhoof.bgfx4j.view.View;
import com.bariumhoof.bgfx4j.wip.IndexBuffer;
import com.bariumhoof.bgfx4j.wip.Program;
import com.bariumhoof.bgfx4j.wip.VertexBuffer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.util.EnumSet;

import static org.lwjgl.bgfx.BGFX.*;

/**
 *
 * "Encoders are used for submitting draw calls from multiple threads. Only one encoder per thread should be used.
 * Use bgfx::begin() to obtain an encoder for a thread."
 */
public class Encoder {

    private static @Nullable Encoder singleThreadEncoder = null;
    private static ThreadLocal<Encoder> encoders = new ThreadLocal<>();

    private final long id;

    private Encoder(boolean forThread) {
        id = bgfx_encoder_begin(forThread);// default is false
    }

    public static Encoder begin(boolean forThread) {
        if (forThread) {
            var encoder = encoders.get();
            if (encoder == null) {
                encoder = new Encoder(forThread);
                encoders.set(encoder);
            }
            return encoder;
        } else {
            if (singleThreadEncoder == null) { // todo race condition if multithreaded?
                singleThreadEncoder = new Encoder(forThread);
            }
            return singleThreadEncoder;
        }
    }

    public void end() {
        bgfx_encoder_end(id);
    }

    public void setTransform(@NotNull ByteBuffer mtx) {
        bgfx_encoder_set_transform(id, mtx);
    }

    public void setTransform(@NotNull FloatBuffer mtx) {
        bgfx_encoder_set_transform(id, mtx);
    }

    public void setTransform(@NotNull float[] mtx) {
        bgfx_encoder_set_transform(id, mtx);
    }

    /**
     *
     * @param state one or more states to use
     */
    public void setState(@NotNull BGFX_STATE state) {
        setState(state, 0);
    }

    /*
     * todo: the rgba arg is ONLY needed when in one of two enum states. Possible to make typesafe?
     */
    public void setState(@NotNull EnumSet<BGFX_STATE> states) {
        setState(states, 0);
    }

    /**
     *
     * @param state one or more states to use
     * @param rgba blend factor if state is BGFX_STATE_BLEND_FACTOR or BGFX_STATE_BLEND_INV_FACTOR
     */
    public void setState(@NotNull BGFX_STATE state, int rgba) {
        bgfx_encoder_set_state(id, state.VALUE, rgba);
    }

    /*
     * todo: the rgba arg is ONLY needed when in one of two enum states. Possible to make typesafe?
     */
    public void setState(@NotNull EnumSet<BGFX_STATE> states, int rgba) {
        bgfx_encoder_set_state(id, EnumUtils.flags(states), rgba);
    }

    /**
     * // todo make boolean class for "isAliasing" in buffer, since this type requires special handle to be passed
     * // todo look into "stream"
     * @param vertexBuffer non-aliasing buffer?
     */
    public void setVertexBuffer(VertexBuffer vertexBuffer) {
        bgfx_encoder_set_vertex_buffer(id, 0, vertexBuffer.handle(), 0, vertexBuffer.size(), BGFX_INVALID_HANDLE);
    }

    public void setIndexBuffer(IndexBuffer indexBuffer) {
        bgfx_encoder_set_index_buffer(id, indexBuffer.handle(), 0, indexBuffer.size());
    }

    public void submit(View view, Program program) {
        submit(view, program, 0);
    }

    public void submit(View view, Program program, int depth) {
        submit(view, program, depth, false);
    }

    public void submit(View view, Program program, boolean preserveState) {
        submit(view, program, 0, preserveState);
    }

    public void submit(View view, Program program, int depth, boolean preserveState) {
        bgfx_encoder_submit(id, view.id(), program.handle(), depth, preserveState);
    }

    public long id() {
        return id;
    }


}
