package com.bariumhoof.bgfx4j.wip;

import com.bariumhoof.bgfx4j.Disposable;
import com.bariumhoof.bgfx4j.Handle;
import com.bariumhoof.bgfx4j.enums.BGFX_BACKBUFFER_RATIO;
import com.bariumhoof.bgfx4j.enums.BGFX_TEXTURE_FORMAT;
import org.apache.commons.lang3.NotImplementedException;
import org.jetbrains.annotations.NotNull;

import java.util.EnumSet;

import static com.bariumhoof.bgfx4j.enums.BGFX_SAMPLER.U_CLAMP;
import static com.bariumhoof.bgfx4j.enums.BGFX_SAMPLER.V_CLAMP;
import static java.util.Collections.emptySet;

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

    // https://bkaradzic.github.io/bgfx/bgfx.html#_CPPv4N4bgfx17createFrameBufferE8uint16_t8uint16_tN13TextureFormat4EnumE8uint64_t
    @NotNull
    static FrameBufferDefault create(int width, int height, @NotNull BGFX_TEXTURE_FORMAT colorFormat) {
        final TextureFlags defaults = TextureFlags.create(emptySet(), EnumSet.of(U_CLAMP, V_CLAMP));
        return FrameBufferDefault.create(width, height, colorFormat, defaults);
    }

    @NotNull
    static FrameBufferDefault create(int width, int height, @NotNull BGFX_TEXTURE_FORMAT colorFormat, @NotNull TextureFlags textureFlags) {
        return FrameBufferDefault.create(width, height, colorFormat, textureFlags);
    }

    // TODO IMPLEMENT THIS
    @NotNull
    static FrameBufferNativeWindowHandle createFromNwh(long nwh, int width, int height, @NotNull BGFX_TEXTURE_FORMAT colorFormat, @NotNull BGFX_TEXTURE_FORMAT depthFormat) {
        return FrameBufferNativeWindowHandle.create(nwh, width, height, colorFormat, depthFormat);
    }

    // TODO IMPLEMENT THIS
    @NotNull
    static FrameBufferScaled createScaled(@NotNull BGFX_BACKBUFFER_RATIO ratio, @NotNull BGFX_TEXTURE_FORMAT format, @NotNull TextureFlags textureFlags) {
        return FrameBufferScaled.create(ratio, format, textureFlags);
    }

    @NotNull
    static FrameBufferScaled createScaled(@NotNull BGFX_BACKBUFFER_RATIO ratio, @NotNull BGFX_TEXTURE_FORMAT format) {
        // todo confirm defaults!
        final TextureFlags defaults = TextureFlags.create(emptySet(), EnumSet.of(U_CLAMP, V_CLAMP));
        return FrameBufferScaled.create(ratio, format, defaults);
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
