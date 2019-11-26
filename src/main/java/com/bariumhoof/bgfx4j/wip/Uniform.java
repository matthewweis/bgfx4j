package com.bariumhoof.bgfx4j.wip;

import com.bariumhoof.bgfx4j.Disposable;
import com.bariumhoof.bgfx4j.Handle;
import com.bariumhoof.bgfx4j.enums.BGFX_UNIFORM_TYPE;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

import static org.lwjgl.bgfx.BGFX.*;

// todo make abstract and instead havbe UniformSampler, UniformVec4, etc. for type safety elsewhere in api
public class Uniform implements Handle, Disposable {

    // todo impl getShaderUniforms in shader
    // todo impl getEncoderUniforms in encoder
    // todo consider moving all set elsewhere since encoder doesn't use them??
    // no need to impl getUniformInfo since it is all stored here instead.

    @Getter
    private final String name;

    @Getter
    private final BGFX_UNIFORM_TYPE type;

    @Getter
    private final int numElementsInArray;

    private final short handle;

    private Uniform(String name, BGFX_UNIFORM_TYPE type, int numElementsInArray, short handle) {
        this.name = name;
        this.type = type;
        this.numElementsInArray = numElementsInArray;
        this.handle = handle;
    }

    public static Uniform create(@NotNull String name, @NotNull BGFX_UNIFORM_TYPE type, int numElementsInArray) {
        final short handle = bgfx_create_uniform(name, type.VALUE, numElementsInArray);
        return new Uniform(name, type, numElementsInArray, handle);
    }

    public void setValue(short ... values) {
        setValue(numElementsInArray, values);
    }

    public void setValue(int numElements, short ... values) {
        bgfx_set_uniform(handle, values, numElements);
    }

    public void setValue(int ... values) {
        setValue(numElementsInArray, values);
    }

    public void setValue(int numElements, int ... values) {
        bgfx_set_uniform(handle, values, numElements);
    }

    public void setValue(long ... values) {
        setValue(numElementsInArray, values);
    }

    public void setValue(int numElements, long ... values) {
        bgfx_set_uniform(handle, values, numElements);
    }

    public void setValue(float ... values) {
        setValue(numElementsInArray, values);
    }

    public void setValue(int numElements, float ... values) {
        bgfx_set_uniform(handle, values, numElements);
    }

    public void setValue(double ... values) {
        setValue(numElementsInArray, values);
    }

    public void setValue(int numElements, double ... values) {
        bgfx_set_uniform(handle, values, numElements);
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
