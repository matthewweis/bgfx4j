package com.bariumhoof.bgfx4j.wip;

import com.bariumhoof.bgfx4j.Disposable;
import com.bariumhoof.bgfx4j.Handle;

// todo low priority
public class ComputeProgram implements Disposable, Handle {
    @Override
    public void dispose() {
    }

    @Override
    public short handle() {
        return 0;
    }

//    private final short p_handle;
//    private final short vs_handle;
//    private final short fs_handle;
//
//    ComputeProgram(short vs_handle, short fs_handle, boolean destroyShaders) throws IOException {
//        this.vs_handle = vs_handle;
//        this.fs_handle = fs_handle;
//        this.p_handle = bgfx_create_program(vs_handle, fs_handle, destroyShaders);
//    }
//
//    @Override
//    public void dispose() {
//        bgfx_destroy_shader(vs_handle);
//        bgfx_destroy_shader(fs_handle);
//        bgfx_destroy_program(p_handle);
//    }
//
//    public static ComputeProgram load(@NotNull URL vs, @NotNull URL fs) throws IOException {
//        return load(vs, fs, false);
//    }
//
//    public static ComputeProgram load(@NotNull URL vs, @NotNull URL fs, boolean destroyShaders) throws IOException {
//        return new ComputeProgram(Resources.loadShader(vs), Resources.loadShader(fs), destroyShaders);
//    }
//
//    @Override
//    public short handle() {
//        return p_handle;
//    }
}
















