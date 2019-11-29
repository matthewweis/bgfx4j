package com.bariumhoof.bgfx4j.wip;

import com.bariumhoof.bgfx4j.Disposable;
import com.bariumhoof.bgfx4j.Handle;
import com.bariumhoof.bgfx4j.enums.BGFX_SAMPLER;
import com.bariumhoof.bgfx4j.enums.BGFX_TEXTURE;
import com.bariumhoof.bgfx4j.resource.Resources;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.net.URL;
import java.util.EnumSet;

import static org.lwjgl.bgfx.BGFX.bgfx_destroy_texture;


public class Texture implements Disposable, Handle {

    private final short handle;

    // todo shonuld default include the sampler? do they always need grouping?
    protected static final TextureFlags DEFAULT = TextureFlags.create(
            EnumSet.of(BGFX_TEXTURE.NONE),
            EnumSet.of(BGFX_SAMPLER.NONE)
    );

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

    @NotNull
    public static Texture load(@NotNull URL url) throws IOException {
        return load(DEFAULT, url);
    }

    @NotNull
    public static Texture load(@NotNull TextureFlags flags, @NotNull URL url) throws IOException {
        final short handle = Resources.loadTexture(flags.VALUE, 0, url);
        return new Texture(handle);
    }

    @Nullable
    public static Texture loadOrNull(@NotNull URL url) {
        try {
            return load(DEFAULT, url);
        } catch (IOException e) {
            return null;
        }
    }

    @Nullable
    public static Texture loadOrNull(@NotNull TextureFlags flags, @NotNull URL url) {
        try {
            return load(flags, url);
        } catch (IOException e) {
            return null;
        }
    }

    @Override
    public void dispose() {
        bgfx_destroy_texture(handle);
    }

    @Override
    public short handle() {
        return handle;
    }
}

























