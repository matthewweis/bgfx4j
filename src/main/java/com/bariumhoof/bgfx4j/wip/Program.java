package com.bariumhoof.bgfx4j.wip;

import com.bariumhoof.bgfx4j.Disposable;
import com.bariumhoof.bgfx4j.Handle;
import com.bariumhoof.bgfx4j.resource.Resources;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.net.URL;

import static org.lwjgl.bgfx.BGFX.*;

public class Program implements Disposable, Handle {

    private final short p_handle;
    private final short vs_handle;
    private final short fs_handle;

    Program(short vs_handle, short fs_handle, boolean destroyShaders) {
        this.vs_handle = vs_handle;
        this.fs_handle = fs_handle;
        this.p_handle = bgfx_create_program(vs_handle, fs_handle, destroyShaders);
        // from bgfx doc:
        // "Destroy shader. Once a shader program is created with _handle, it is safe to destroy that shader."
        if (destroyShaders) {
            bgfx_destroy_shader(vs_handle);
            bgfx_destroy_shader(fs_handle);
        }
    }

    public void setShaderNames(@NotNull String vertexShaderName, @NotNull String fragmentShaderName) {
        setVertexShaderName(vertexShaderName);
        setFragmentShaderName(fragmentShaderName);
    }

    public void setVertexShaderName(@NotNull String name) {
        bgfx_set_shader_name(vs_handle, name);
    }

    public void setFragmentShaderName(@NotNull String name) {
        bgfx_set_shader_name(fs_handle, name);
    }

    @Deprecated // not sure if this works
    public void getVertexShaderUniforms() {
        short[] uniforms = new short[0];
        final int n = bgfx_get_shader_uniforms(vs_handle, uniforms); // todo check if this works!
    }

    @Deprecated // not sure if this works
    public void getFragmentShaderUniforms() {
        short[] uniforms = new short[0];
        final int n = bgfx_get_shader_uniforms(fs_handle, uniforms); // todo check if this works!
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
    public static Program create(@NotNull Shader vs, @NotNull Shader fs, boolean destroyShaders) {
        return new Program(vs.handle, fs.handle, destroyShaders);
    }

    /**
     * Creates a program from two {@link URL}s that point to corresponding vertex and fragment shaders.
     * @param vs The {@link URL} of a vertex shader to load.
     * @param fs The {@link URL} of the corresponding fragment shader to load.
     * @return A program created from the loaded vs and fs shaders.
     * @throws IOException thrown if either {@link URL} is invalid.
     */
    public static Program load(@NotNull URL vs, @NotNull URL fs) throws IOException {
        return new Program(Resources.loadShader(vs), Resources.loadShader(fs), true);
    }

    @Override
    public short handle() {
        return p_handle;
    }
}
















