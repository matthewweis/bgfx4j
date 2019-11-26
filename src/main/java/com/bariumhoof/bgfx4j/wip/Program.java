package com.bariumhoof.bgfx4j.wip;

import com.bariumhoof.bgfx4j.Disposable;
import com.bariumhoof.bgfx4j.Handle;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.net.URL;

import static org.lwjgl.bgfx.BGFX.*;

public class Program implements Disposable, Handle {

    private final short p_handle;

    @Getter
    @NotNull
    private final Shader vs, fs;

    private Program(@NotNull Shader vs, @NotNull Shader fs, boolean destroyShaders) {
        this.vs = vs;
        this.fs = fs;
        this.p_handle = bgfx_create_program(vs.handle, fs.handle, destroyShaders);
        // from bgfx doc:
        // "Destroy shader. Once a shader program is created with _handle, it is safe to destroy that shader."
        if (destroyShaders) {
            bgfx_destroy_shader(vs.handle);
            bgfx_destroy_shader(fs.handle);
        }
    }

    public void setShaderNames(@NotNull String vertexShaderName, @NotNull String fragmentShaderName) {
        vs.setName(vertexShaderName);
        fs.setName(fragmentShaderName);
    }

    @Deprecated // not sure if this works
    public void getVertexShaderUniforms() {
        short[] uniforms = new short[0];
        final int n = bgfx_get_shader_uniforms(vs.handle, uniforms); // todo check if this works!
    }

    @Deprecated // not sure if this works
    public void getFragmentShaderUniforms() {
        short[] uniforms = new short[0];
        final int n = bgfx_get_shader_uniforms(fs.handle, uniforms); // todo check if this works!
    }

    @Override
    public void dispose() {
        bgfx_destroy_program(p_handle);
    }

    /**
     * Creates a program from a pair of corresponding vertex and fragment shaders. The vs and fs shaders'
     * {@link Shader#dispose()} methods will NOT be called automatically in case the user wants to reuse them later.
     *
     * @param vs The vertex shader that corresponds to the passed fragment shader.
     * @param fs The fragment shader that corresponds to the passed vertex shader.
     * @return A program created from the two shaders.
     */
    @NotNull
    public static Program create(@NotNull Shader vs, @NotNull Shader fs) {
        return create(vs, fs, false);
    }

    /**
     * Creates a program from a pair of corresponding vertex and fragment shaders.
     *
     * The vs and fs shaders' {@link Shader#dispose()} methods will be called if the destroyShaders is set to true.
     * Note that once destroyed, the shaders will no longer be valid.
     *
     * @param vs The vertex shader that corresponds to the passed fragment shader.
     * @param fs The fragment shader that corresponds to the passed vertex shader.
     * @param destroyShaders Whether or not to destroy the shaders after creating the program (will not affect program).
     * @return A program created from the vs and fs shaders.
     */
    @NotNull
    public static Program create(@NotNull Shader vs, @NotNull Shader fs, boolean destroyShaders) {
        return new Program(vs, fs, destroyShaders);
    }

    /**
     * Creates a program from two {@link URL}s that point to corresponding vertex and fragment shaders.
     * @param vs The {@link URL} of a vertex shader to load.
     * @param fs The {@link URL} of the corresponding fragment shader to load.
     * @return A program created from the loaded vs and fs shaders.
     * @throws IOException thrown if either {@link URL} is invalid.
     */
    @NotNull
    public static Program load(@NotNull URL vs, @NotNull URL fs) throws IOException {
        return create(Shader.load(vs), Shader.load(fs), true);
    }

    /**
     * Creates a program from two {@link URL}s that point to corresponding vertex and fragment shaders.
     * @param vs The {@link URL} of a vertex shader to load.
     * @param fs The {@link URL} of the corresponding fragment shader to load.
     * @return A program created from the loaded vs and fs shaders.
     */
    @Nullable
    public static Program loadOrNull(@NotNull URL vs, @NotNull URL fs) {
        try {
            return create(Shader.load(vs), Shader.load(fs), true);
        } catch (IOException e) {
            return null;
        }
    }

    @Override
    public short handle() {
        return p_handle;
    }
}