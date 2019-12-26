package com.bariumhoof.bgfx4j.wip;

import com.bariumhoof.bgfx4j.Disposable;
import com.bariumhoof.bgfx4j.Handle;
import com.bariumhoof.bgfx4j.enums.BGFX_ACCESS;
import com.bariumhoof.bgfx4j.enums.BGFX_RESOLVE;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.lwjgl.bgfx.BGFX;
import org.lwjgl.bgfx.BGFXAttachment;

public class Attachment implements Disposable, Handle {

    @Getter
    @NotNull
    private final BGFXAttachment attachment;

    private Attachment() {
        attachment = BGFXAttachment.create();
    }

    // official bgfx api uses bit flags for resolve, but resolve only has one type?
//    public Attachment init(@NotNull Texture texture, @NotNull BGFX_ACCESS access, int layer, int mip, @NotNull EnumSet<BGFX_RESOLVE> resolve) {
//
//    }

    @NotNull
    public Attachment create() {
        return new Attachment();
    }

    /**
     *
     * @param texture render target
     * @param access capabilities of (todo: render target?)
     * @param layer cubemap side or depth layer / slice. todo can this be type safe
     * @param mip mip level, todo can this be type safe?
     * @param resolve "resolve flags" (poorly labeled from bgfx). Hint as to whether mip-map intermediates need generation.
     */
    public void init(@NotNull Texture texture, @NotNull BGFX_ACCESS access, int layer, int mip, @NotNull BGFX_RESOLVE resolve) {
        BGFX.bgfx_attachment_init(attachment, texture.handle(), access.VALUE, layer, mip, resolve.VALUE);
    }

    @Override
    public void dispose() {
        attachment.free();
    }

    @Override
    public short handle() {
        return attachment.handle();
    }
}
