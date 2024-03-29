package com.bariumhoof.bgfx4j.enums;

import java.util.Set;

import static org.lwjgl.bgfx.BGFX.BGFX_TEXTURE_RT;
import static org.lwjgl.bgfx.BGFX.*;

public enum BGFX_TEXTURE {
    NONE(BGFX_TEXTURE_NONE),
    MSAA_SAMPLE(BGFX_TEXTURE_MSAA_SAMPLE),
    RT(BGFX_TEXTURE_RT),
    RT_MSAA_X2(BGFX_TEXTURE_RT_MSAA_X2),
    RT_MSAA_X4(BGFX_TEXTURE_RT_MSAA_X4),
    RT_MSAA_X8(BGFX_TEXTURE_RT_MSAA_X8),
    RT_MSAA_X16(BGFX_TEXTURE_RT_MSAA_X16),
    RT_WRITE_ONLY(BGFX_TEXTURE_RT_WRITE_ONLY),
    COMPUTE_WRITE(BGFX_TEXTURE_COMPUTE_WRITE),
    SRGB(BGFX_TEXTURE_SRGB),
    BLIT_DST(BGFX_TEXTURE_BLIT_DST),
    READ_BACK(BGFX_TEXTURE_READ_BACK);

    public final long VALUE;

    BGFX_TEXTURE(long value) {
        this.VALUE = value;
    }

    public static long flags(Set<BGFX_TEXTURE> states) {
        long bits = 0L;
        for (BGFX_TEXTURE next : states) {
            bits |= next.VALUE;
        }
        return bits;
    }
}
