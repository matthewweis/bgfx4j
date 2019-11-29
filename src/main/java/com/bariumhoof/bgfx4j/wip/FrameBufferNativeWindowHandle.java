package com.bariumhoof.bgfx4j.wip;

import com.bariumhoof.bgfx4j.Disposable;
import com.bariumhoof.bgfx4j.Handle;
import com.bariumhoof.bgfx4j.enums.BGFX_TEXTURE_FORMAT;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

import static org.lwjgl.bgfx.BGFX.bgfx_create_frame_buffer_from_nwh;
import static org.lwjgl.bgfx.BGFX.bgfx_destroy_frame_buffer;

// distinct from FrameBuffer as there can be no getTexture for this frameBuffer
public class FrameBufferNativeWindowHandle implements Disposable, Handle {

    private final short handle;

    @Getter
    private final int width;

    @Getter
    private final int height;

    private FrameBufferNativeWindowHandle(int width, int height, short handle) {
        this.width = width;
        this.height = height;
        this.handle = handle;
    }

    @NotNull
    public static FrameBufferNativeWindowHandle create(long nwh, int width, int height, @NotNull BGFX_TEXTURE_FORMAT colorFormat, @NotNull BGFX_TEXTURE_FORMAT depthFormat) {
        final short handle = bgfx_create_frame_buffer_from_nwh(nwh, width, height, colorFormat.VALUE, depthFormat.VALUE);
        return new FrameBufferNativeWindowHandle(width, height, handle);
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
