package com.bariumhoof.bgfx4j.enums;

import java.util.Set;

import static org.lwjgl.bgfx.BGFX.*;

public enum BGFX_SAMPLER_SHIFT {
    U_SHIFT(BGFX_SAMPLER_U_SHIFT),
    V_SHIFT(BGFX_SAMPLER_V_SHIFT),
    W_SHIFT(BGFX_SAMPLER_W_SHIFT),
    MIN_SHIFT(BGFX_SAMPLER_MIN_SHIFT),
    MAG_SHIFT(BGFX_SAMPLER_MAG_SHIFT),
    MIP_SHIFT(BGFX_SAMPLER_MIP_SHIFT),
    COMPARE_SHIFT(BGFX_SAMPLER_COMPARE_SHIFT),
    BORDER_COLOR_SHIFT(BGFX_SAMPLER_BORDER_COLOR_SHIFT),
    RESERVED_SHIFT(BGFX_SAMPLER_RESERVED_SHIFT);

    public final int VALUE;

    BGFX_SAMPLER_SHIFT(int value) {
        this.VALUE = value;
    }

    public static long flags(Set<BGFX_SAMPLER_SHIFT> states) {
        long bits = 0L;
        for (BGFX_SAMPLER_SHIFT next : states) {
            bits |= next.VALUE;
        }
        return bits;
    }
}
