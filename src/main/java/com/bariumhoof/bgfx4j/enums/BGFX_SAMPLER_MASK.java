package com.bariumhoof.bgfx4j.enums;

import java.util.Set;

import static org.lwjgl.bgfx.BGFX.*;

public enum BGFX_SAMPLER_MASK {
    U_MASK(BGFX_SAMPLER_U_MASK),
    V_MASK(BGFX_SAMPLER_V_MASK),
    W_MASK(BGFX_SAMPLER_W_MASK),
    MIN_MASK(BGFX_SAMPLER_MIN_MASK),
    MAG_MASK(BGFX_SAMPLER_MAG_MASK),
    MIP_MASK(BGFX_SAMPLER_MIP_MASK),
    COMPARE_MASK(BGFX_SAMPLER_COMPARE_MASK),
    BORDER_COLOR_MASK(BGFX_SAMPLER_BORDER_COLOR_MASK),
    RESERVED_MASK(BGFX_SAMPLER_RESERVED_MASK),
    SAMPLER_BITS_MASK(BGFX_SAMPLER_SAMPLER_BITS_MASK);

    public final int VALUE;

    BGFX_SAMPLER_MASK(int value) {
        this.VALUE = value;
    }

    public static long flags(Set<BGFX_SAMPLER_MASK> states) {
        long bits = 0L;
        for (BGFX_SAMPLER_MASK next : states) {
            bits |= next.VALUE;
        }
        return bits;
    }
}
