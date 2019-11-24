package com.bariumhoof.bgfx4j.wip;

import com.bariumhoof.bgfx4j.Disposable;
import com.bariumhoof.bgfx4j.Handle;

import static org.lwjgl.bgfx.BGFX.bgfx_destroy_texture;


public abstract class Texture implements Disposable, Handle {

    private final short handle;

    protected Texture(short handle) {
        this.handle = handle;
    }

    // todo setTexture should be in a "Stage" class

//    @Precondition("Texture must be created with BGFX_TEXTURE_READ_BACK flag.")
//    @Availability(BGFX_CAPS.TEXTURE_READ_BACK)
//    public void read(int mipLevel) {
//
//        // read texture returns the frame number when it will be ready.
//        // this will need some sort of CompleteableFuture. Should it be custom?
////        bgfx_read_texture(handle, );
//    }

    @Override
    public void dispose() {
        bgfx_destroy_texture(handle);
    }

    @Override
    public short handle() {
        return handle;
    }
}

























