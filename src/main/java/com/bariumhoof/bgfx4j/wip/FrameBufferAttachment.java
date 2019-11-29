package com.bariumhoof.bgfx4j.wip;

import com.bariumhoof.bgfx4j.Disposable;
import com.bariumhoof.bgfx4j.Handle;
import com.bariumhoof.bgfx4j.enums.BGFX_SAMPLER;
import com.bariumhoof.bgfx4j.enums.BGFX_TEXTURE;
import org.apache.commons.lang3.NotImplementedException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.lwjgl.bgfx.BGFXAttachment;

import java.util.EnumSet;

import static org.lwjgl.bgfx.BGFX.*;

// distinct from frameBuffer as frameBufferAttachment's getTexture method requires an attachmentIndex
public class FrameBufferAttachment implements Disposable, Handle {

    protected static final TextureFlags DEFAULT = TextureFlags.create(
            EnumSet.of(BGFX_TEXTURE.NONE),
            EnumSet.of(BGFX_SAMPLER.NONE)
    );

    private final short handle;

    private FrameBufferAttachment(short handle) {
        this.handle = handle;
    }

    // TODO IMPLEMENT THIS
    @NotNull
    public static FrameBufferAttachment create(BGFXAttachment.Buffer attachment, boolean destroyHandles) {
        throw new NotImplementedException("todo");
//        final short handle = bgfx_create_frame_buffer_from_attachment(attachment, destroyHandles);
//        return new FrameBufferAttachment(0, 0, handle);
    }

    @NotNull
    public Texture getTexture() {
        final short handle = bgfx_get_texture(this.handle, 0);
        return new Texture(handle);
    }

    @NotNull
    public Texture getTexture(int attachmentIndex) {
        final short handle = bgfx_get_texture(this.handle, attachmentIndex);
        return new Texture(handle);
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
}
