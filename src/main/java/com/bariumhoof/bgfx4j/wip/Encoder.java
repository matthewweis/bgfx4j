package com.bariumhoof.bgfx4j.wip;

import com.bariumhoof.bgfx4j.enums.BGFX_SAMPLER;
import com.bariumhoof.bgfx4j.enums.BGFX_STATE;
import com.bariumhoof.bgfx4j.view.View;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class Encoder {

    private final static int USE_DEFAULT_TEXTURE_FLAG = 0xFFFFFFFF;

    private final long id;

    // todo want to avoid creating Encoder garbage each frame.
    // todo threadlocal's dont work.

    private Encoder(boolean forWorkerThread) {
        id = bgfx_encoder_begin(forWorkerThread);// default is false
    }

    public static Encoder begin() {
        return new Encoder(false);
    }

    public static Encoder begin(boolean forWorkerThread) {
        return new Encoder(forWorkerThread);
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

    public void touch(@NotNull View view) {
        bgfx_encoder_touch(id, view.id());
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
        bgfx_encoder_set_state(id, BGFX_STATE.flags(states), rgba);
    }

    /**
     * // todo make boolean class for "isAliasing" in buffer, since this type requires special handle to be passed
     * // todo look into "stream"
     * @param vertexBuffer non-aliasing buffer?
     */
    public void setVertexBuffer(VertexBuffer vertexBuffer) {
        bgfx_encoder_set_vertex_buffer(id, 0, vertexBuffer.handle(), 0, vertexBuffer.size(), BGFX_INVALID_HANDLE);
    }

    public void setVertexBuffer(VertexBuffer vertexBuffer, int startVertex, int size) {
        bgfx_encoder_set_vertex_buffer(id, 0, vertexBuffer.handle(), startVertex, size, BGFX_INVALID_HANDLE);
    }

    public void setIndexBuffer(IndexBuffer indexBuffer) {
        bgfx_encoder_set_index_buffer(id, indexBuffer.handle(), 0, indexBuffer.size());
    }

    public void setIndexBuffer(IndexBuffer indexBuffer, int startVertex, int size) {
        bgfx_encoder_set_index_buffer(id, indexBuffer.handle(), startVertex, size);
    }

    public void setVertexBuffer(TransientVertexBuffer vertexBuffer) {
        bgfx_encoder_set_transient_vertex_buffer(id, 0, vertexBuffer.getBuf(), 0, vertexBuffer.size(), BGFX_INVALID_HANDLE);
    }

    public void setVertexBuffer(TransientVertexBuffer vertexBuffer, int startVertex, int size) {
        bgfx_encoder_set_transient_vertex_buffer(id, 0, vertexBuffer.getBuf(), startVertex, size, BGFX_INVALID_HANDLE);
    }

    public void setTransientIndexBuffer(TransientIndexBuffer indexBuffer) {
        bgfx_encoder_set_transient_index_buffer(id, indexBuffer.getBuf(), 0, indexBuffer.size());
    }

    public void setTransientIndexBuffer(TransientIndexBuffer indexBuffer, int startVertex, int size) {
        bgfx_encoder_set_transient_index_buffer(id, indexBuffer.getBuf(), startVertex, size);
    }

    public void setTransientVertexBuffer(TransientVertexBuffer vertexBuffer) {
        bgfx_encoder_set_transient_vertex_buffer(id, 0, vertexBuffer.getBuf(), 0, vertexBuffer.size(), vertexBuffer.layoutHandle());
    }

    public void setTransientVertexBuffer(TransientVertexBuffer vertexBuffer, int startVertex, int size) {
        bgfx_encoder_set_transient_vertex_buffer(id, 0, vertexBuffer.getBuf(), startVertex, size, vertexBuffer.layoutHandle());
    }

    public void setInstanceBuffer(@NotNull InstanceBuffer instanceBuffer) {
        bgfx_encoder_set_instance_data_buffer(id, instanceBuffer.get(), 0, instanceBuffer.num());
    }

    public void setInstanceBuffer(@NotNull InstanceBuffer instanceBuffer, int startVertex, int size) {
        bgfx_encoder_set_instance_data_buffer(id, instanceBuffer.get(), startVertex, size);
    }

    // todo make Uniform types for each value. Sampler, Vec4x4, etc to make typesafe
    public void setTexture(int stage, @NotNull Uniform sampler, @NotNull Texture texture) {
        setTexture(stage, sampler, texture, null);
    }

    public void setTexture(int stage, @NotNull Uniform sampler, @NotNull Texture texture, @Nullable BGFX_SAMPLER samplerSetting) {
        final int samplerSettingFlag = samplerSetting == null ? USE_DEFAULT_TEXTURE_FLAG : samplerSetting.VALUE;
        bgfx_encoder_set_texture(id, stage, sampler.handle(), texture.handle(), samplerSettingFlag);
    }

//    public void setUniform(@NotNull Uniform uniform, int numElements, short ... values) {
//        bgfx_encoder_set_uniform(id, uniform.handle(), values, numElements);
//    }
//
//    public void setUniform(@NotNull Uniform uniform, int numElements, int ... values) {
//        bgfx_encoder_set_uniform(id, uniform.handle(), values, numElements);
//    }
//
//    public void setUniform(@NotNull Uniform uniform, int numElements, long ... values) {
//        bgfx_encoder_set_uniform(id, uniform.handle(), values, numElements);
//    }
//
//    public void setUniform(@NotNull Uniform uniform, int numElements, float ... values) {
//        bgfx_encoder_set_uniform(id, uniform.handle(), values, numElements);
//    }
//
//    public void setUniform(@NotNull Uniform uniform, int numElements, double ... values) {
//        bgfx_encoder_set_uniform(id, uniform.handle(), values, numElements);
//    }

    public void setUniform(@NotNull Uniform uniform, short ... values) {
        bgfx_encoder_set_uniform(id, uniform.handle(), values, uniform.getCount());
    }

    public void setUniform(@NotNull Uniform uniform, int ... values) {
        bgfx_encoder_set_uniform(id, uniform.handle(), values, uniform.getCount());
    }

    public void setUniform(@NotNull Uniform uniform, long ... values) {
        bgfx_encoder_set_uniform(id, uniform.handle(), values, uniform.getCount());
    }

    public void setUniform(@NotNull Uniform uniform, float ... values) {
        bgfx_encoder_set_uniform(id, uniform.handle(), values, uniform.getCount());
    }

    public void setUniform(@NotNull Uniform uniform, double ... values) {
        bgfx_encoder_set_uniform(id, uniform.handle(), values, uniform.getCount());
    }

    public void setUniform(@NotNull Uniform uniform, @NotNull ByteBuffer values) {
        // todo look into why using UINT16_MAX doesn't work here (even when properly declaring size at creation time)
        bgfx_encoder_set_uniform(id, uniform.handle(), values, uniform.getCount());
    }

    // todo add once InstanceDataBuffer exists
//    public void setInstanceBuffer(InstanceDataBuffer instanceDataBuffer) {
//        bgfx_encoder_set_index_buffer(id, IndexBuffer);
//    }

    public void submit(View view, Program program) {
        submit(view, program, 0, false);
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
