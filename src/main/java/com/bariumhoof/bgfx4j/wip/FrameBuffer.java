package com.bariumhoof.bgfx4j.wip;

import com.bariumhoof.bgfx4j.Disposable;
import com.bariumhoof.bgfx4j.Handle;
import com.bariumhoof.bgfx4j.enums.BGFX_TEXTURE_FORMAT;
import org.apache.commons.lang3.NotImplementedException;
import org.jetbrains.annotations.NotNull;

public interface FrameBuffer extends Disposable, Handle {

    @NotNull
    public static FrameBufferAttachment createFromAttachment(boolean destroyHandles) {
        throw new NotImplementedException("todo");
//        return FrameBufferAttachment.create(null, destroyHandles);
    }

    @NotNull
    static FrameBufferTextureHandles createFromHandles(@NotNull Iterable<Texture> textures) {
        throw new NotImplementedException("todo");
    }

    @NotNull
    static FrameBufferTextureHandles createFromHandles(@NotNull Texture ... textures) {
        return createFromHandles(false, textures);
    }

    @NotNull
    static FrameBufferTextureHandles createFromHandles(boolean destroyTextures, @NotNull Texture ... textures) {
        return FrameBufferTextureHandles.create(destroyTextures, textures);
    }

    // TODO IMPLEMENT THIS
    @NotNull
    static FrameBufferNativeWindowHandle createFromNwh(long nwh, int width, int height, @NotNull BGFX_TEXTURE_FORMAT colorFormat, @NotNull BGFX_TEXTURE_FORMAT depthFormat) {
        return FrameBufferNativeWindowHandle.create(nwh, width, height, colorFormat, depthFormat);
    }

    // TODO IMPLEMENT THIS
    @NotNull
    static FrameBufferScaled createScaled(int ratio, @NotNull BGFX_TEXTURE_FORMAT format, @NotNull TextureFlags textureFlags) {
        return FrameBufferScaled.create(ratio, format, textureFlags);
    }

//    @NotNull
//    public Texture getTexture() {
//        bgfx_get_texture(handle, 0);
//    }
//
//    @Override
//    public void dispose() {
//        bgfx_destroy_frame_buffer(handle);
//    }
//
//    @Override
//    public short handle() {
//        return handle;
//    }
}
