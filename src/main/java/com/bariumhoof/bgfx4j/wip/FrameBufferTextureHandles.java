package com.bariumhoof.bgfx4j.wip;

import com.bariumhoof.assertions.Assertions;
import com.bariumhoof.bgfx4j.Disposable;
import com.bariumhoof.bgfx4j.Handle;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static org.lwjgl.bgfx.BGFX.*;

// distinct from frameBuffer as frameBufferAttachment's getTexture method requires an attachmentIndex
public class FrameBufferTextureHandles implements Disposable, Handle {

    private final short handle;

    private final int numTextures;

    private FrameBufferTextureHandles(short handle, int numTextures) {
        this.handle = handle;
        this.numTextures = numTextures;
    }

    @NotNull
    public static FrameBufferTextureHandles create(boolean destroyTextures, @NotNull Texture ... textures) {
        final short[] handles = mapTexturesToHandles(textures);
        final short handle = bgfx_create_frame_buffer_from_handles(handles, destroyTextures);
        return new FrameBufferTextureHandles(handle, textures.length);
    }

    @NotNull
    public Texture getTexture(int attachmentIndex) {
        Assertions.requireNonNegative(attachmentIndex);
        Assertions.requireLessThan(attachmentIndex, numTextures);

        final Texture textureOrNull = getTextureOrNull(attachmentIndex);

        if (textureOrNull == null) {
            throw new IllegalStateException("attachmentIndex did not return a valid texture");
        } else {
            return textureOrNull;
        }
    }

    @Nullable
    public Texture getTextureOrNull(int attachmentIndex) {
        final short handle = bgfx_get_texture(this.handle, attachmentIndex);
        if (handle == BGFX_INVALID_HANDLE) {
            return null;
        } else {
            return new Texture(handle);
        }
    }

    @Override
    public void dispose() {
        bgfx_destroy_frame_buffer(handle);
    }

    @Override
    public short handle() {
        return handle;
    }

    private static short[] mapTexturesToHandles(@NotNull Texture[] textures) {
        final short[] textureHandles = new short[textures.length];
        for (int i=0; i < textures.length; i++) {
            textureHandles[i] = textures[i].handle();
        }
        return textureHandles;
    }

}
