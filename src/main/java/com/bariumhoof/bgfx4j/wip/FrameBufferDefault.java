package com.bariumhoof.bgfx4j.wip;

import com.bariumhoof.bgfx4j.Disposable;
import com.bariumhoof.bgfx4j.Handle;
import com.bariumhoof.bgfx4j.enums.BGFX_TEXTURE_FORMAT;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

import static org.lwjgl.bgfx.BGFX.*;

// distinct from frameBuffer as frameBufferAttachment's getTexture method requires an attachmentIndex
public class FrameBufferDefault implements Disposable, Handle {

    private final short handle;

    @Getter
    private final int width;

    @Getter
    private final int height;

    private FrameBufferDefault(int width, int height, short handle) {
        this.width = width;
        this.height = height;
        this.handle = handle;
    }

    @NotNull
    public static FrameBufferDefault create(int width, int height, @NotNull BGFX_TEXTURE_FORMAT format) {
        return create(width, height, format, TextureFlags.DEFAULT);
    }

    @NotNull
    public static FrameBufferDefault create(int width, int height, @NotNull BGFX_TEXTURE_FORMAT format, @NotNull TextureFlags textureFlags) {
        final short handle = bgfx_create_frame_buffer(width, height, format.VALUE, 0);
        return new FrameBufferDefault(width, height, handle);
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
