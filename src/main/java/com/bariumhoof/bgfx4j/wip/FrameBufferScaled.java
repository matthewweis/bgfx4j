package com.bariumhoof.bgfx4j.wip;

import com.bariumhoof.bgfx4j.Disposable;
import com.bariumhoof.bgfx4j.Handle;
import com.bariumhoof.bgfx4j.enums.BGFX_TEXTURE_FORMAT;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

import static org.lwjgl.bgfx.BGFX.*;

public class FrameBufferScaled implements Disposable, Handle {

    private final short handle;

    @Getter
    private final int ratio;

    private FrameBufferScaled(int ratio, short handle) {
        this.ratio = ratio;
        this.handle = handle;
    }

    @NotNull
    public static FrameBufferScaled create(int ratio, @NotNull BGFX_TEXTURE_FORMAT format) {
        return create(ratio, format, TextureFlags.DEFAULT);
    }

    @NotNull
    public static FrameBufferScaled create(int ratio, @NotNull BGFX_TEXTURE_FORMAT format, @NotNull TextureFlags textureFlags) {
        final short handle = bgfx_create_frame_buffer_scaled(ratio, format.VALUE, textureFlags.VALUE);
        return new FrameBufferScaled(ratio, handle);
    }

    @NotNull
    public Texture getTexture() {
        final short handle = bgfx_get_texture(this.handle, 0);
        return new Texture(handle);
    }

    @Override
    public void dispose() {
        bgfx_destroy_frame_buffer(handle);
    }

    @Override
    public short handle() {
        return handle;
    }
}
