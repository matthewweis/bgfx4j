package com.bariumhoof.bgfx4j.wip;

import com.bariumhoof.assertions.Assertions;
import com.bariumhoof.bgfx4j.Disposable;
import com.bariumhoof.bgfx4j.Handle;
import com.bariumhoof.bgfx4j.enums.BGFX_UNIFORM_TYPE;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

import static org.lwjgl.bgfx.BGFX.*;

// todo make abstract and instead havbe UniformSampler, UniformVec4, etc. for type safety elsewhere in api
// todo make uniform array, and leave this class for a uniform of only one value
public class Uniform implements Handle, Disposable {

    // todo impl getShaderUniforms in shader
    // todo impl getEncoderUniforms in encoder
    // todo consider moving all set elsewhere since encoder doesn't use them??
    // no need to impl getUniformInfo since it is all stored here instead.

    @Getter
    private final String name;

    @Getter
    private final BGFX_UNIFORM_TYPE type;

    private final int stride;

    private final short handle;

    private Uniform(String name, BGFX_UNIFORM_TYPE type, short handle) {
        this.name = name;
        this.type = type;
        this.stride = elementStride(type);
        this.handle = handle;
    }

    public static Uniform create(@NotNull String name, @NotNull BGFX_UNIFORM_TYPE type) {
        // setting _num only matters when setting value and passing UINT16_MAX
        final short handle = bgfx_create_uniform(name, type.VALUE, 0);
        return new Uniform(name, type, handle);
    }

    public void setValue(short ... values) {
        Assertions.require(values.length % stride == 0);
        bgfx_set_uniform(handle, values, values.length / stride);
    }

    public void setValue(int ... values) {
        Assertions.require(values.length % stride == 0);
        bgfx_set_uniform(handle, values, values.length / stride);
    }

    public void setValue(long ... values) {
        Assertions.require(values.length % stride == 0);
        bgfx_set_uniform(handle, values, values.length / stride);
    }

    public void setValue(float ... values) {
        Assertions.require(values.length % stride == 0);
        bgfx_set_uniform(handle, values, values.length / stride);
    }

    public void setValue(double ... values) {
        Assertions.require(values.length % stride == 0);
        bgfx_set_uniform(handle, values, values.length / stride);
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
