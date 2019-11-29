package com.bariumhoof.bgfx4j.wip;

import com.bariumhoof.bgfx4j.enums.BGFX_SAMPLER;
import com.bariumhoof.bgfx4j.enums.BGFX_TEXTURE;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.jetbrains.annotations.NotNull;

import java.util.EnumSet;
import java.util.Set;

@ToString
@EqualsAndHashCode
public final class TextureFlags {

    // todo enforce rules about what can be combined, see bgfx_create_frame_buffer for example

    // todo replace all other DEFAULTS with this one "true" default
    public static final TextureFlags DEFAULT = TextureFlags.create(
            EnumSet.of(BGFX_TEXTURE.NONE),
            EnumSet.of(BGFX_SAMPLER.NONE)
    );

    @Getter
    protected final long VALUE;

    private TextureFlags(long flags) {
        this.VALUE = flags;
    }

    @NotNull
    public static TextureFlags create(@NotNull Set<BGFX_TEXTURE> textureFlags, @NotNull Set<BGFX_SAMPLER> samplerFlags) {
        final long texture = BGFX_TEXTURE.flags(textureFlags);
        final long sampler = BGFX_SAMPLER.flags(samplerFlags);
        return new TextureFlags(texture | sampler);
    }
}