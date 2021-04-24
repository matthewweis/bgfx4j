package com.bariumhoof.bgfx4j.wip;

import com.bariumhoof.bgfx4j.Disposable;
import com.bariumhoof.bgfx4j.Handle;
import com.bariumhoof.bgfx4j.enums.BGFX_UNIFORM_TYPE;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

import java.nio.ByteBuffer;

import static org.lwjgl.bgfx.BGFX.*;

// todo make abstract and instead havbe UniformSampler, UniformVec4, etc. for type safety elsewhere in api
// todo make uniform array, and leave this class for a uniform of only one value
public class Uniform implements Handle, Disposable {

    private static final int DEFER_TO_DECL_SIZE = Bgfx4j.U_INT16_MAX;

    // todo impl getShaderUniforms in shader
    // todo impl getEncoderUniforms in encoder
    // todo consider moving all set elsewhere since encoder doesn't use them??
    // no need to impl getUniformInfo since it is all stored here instead.

    @Getter
    private final String name;

    @Getter
    private final BGFX_UNIFORM_TYPE type;

//    private final int stride;

    @Getter
    private final int count;

    private final short handle;

    private Uniform(String name, BGFX_UNIFORM_TYPE type, short handle, int count) {
        this.name = name;
        this.type = type;
//        this.stride = elementStride(type);
        this.handle = handle;
        this.count = count;
    }

//    public static Uniform create(@NotNull String name, @NotNull BGFX_UNIFORM_TYPE type) {
//        // setting _num only matters when setting value and passing UINT16_MAX
//        final short handle = bgfx_create_uniform(name, type.VALUE, 1);
//        return new Uniform(name, type, handle);
//    }

//    public static Uniform create(@NotNull String name, @NotNull BGFX_UNIFORM_TYPE type, int count) {
//        // setting _num only matters when setting value and passing UINT16_MAX
//        final short handle = bgfx_create_uniform(name, type.VALUE, count);
//        return new Uniform(name, type, handle);
//    }

    public static Uniform createSingle(@NotNull String name, @NotNull BGFX_UNIFORM_TYPE type) {
        // setting _num only matters when setting value and passing UINT16_MAX
        final short handle = bgfx_create_uniform(name, type.VALUE, 1);
        return new Uniform(name, type, handle, 1);
    }

    public static Uniform createArray(@NotNull String name, @NotNull BGFX_UNIFORM_TYPE type, int count) {
        // setting _num only matters when setting value and passing UINT16_MAX
        final short handle = bgfx_create_uniform(name, type.VALUE, count);
        return new Uniform(name, type, handle, count);
    }

//    private int size = -1;
//    public int count() {
//        return count;
//    }

//    public void setValue(short ... values) {
//        Assertions.require(values.length % stride == 0);
//        bgfx_set_uniform(handle, values, values.length / stride);
//    }
//
//    public void setValue(int ... values) {
//        Assertions.require(values.length % stride == 0);
//        bgfx_set_uniform(handle, values, values.length / stride);
//    }
//
//    public void setValue(long ... values) {
//        Assertions.require(values.length % stride == 0);
//        bgfx_set_uniform(handle, values, values.length / stride);
//    }
//
//    public void setValue(float ... values) {
//        Assertions.require(values.length % stride == 0);
//        bgfx_set_uniform(handle, values, values.length / stride);
//    }
//
//    public void setValue(double ... values) {
//        Assertions.require(values.length % stride == 0);
//        bgfx_set_uniform(handle, values, values.length / stride);
//    }

    // depreciate because all should be through encoder api?
    @Deprecated
    public void setValue(ByteBuffer buffer) {
        bgfx_set_uniform(handle, buffer, count);
    }

    private static int elementStride(BGFX_UNIFORM_TYPE type) {
        switch (type) {
            case SAMPLER: throw new RuntimeException("todo figure out stride for sampler");
            case END: throw new IllegalStateException("reserved by bgfx. todo remove END option?");
            case VEC4: return 4;
            case MAT3: return 9;
            case MAT4: return 16;
            default: throw new IllegalStateException("unhandled by bgfx4j");
        }
    }

    @Override
    public void dispose() {
        bgfx_destroy_uniform(handle);
    }

    @Override
    public short handle() {
        return handle;
    }
}
