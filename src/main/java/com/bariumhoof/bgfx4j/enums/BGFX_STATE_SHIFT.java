package com.bariumhoof.bgfx4j.enums;

import java.util.Set;

import static org.lwjgl.bgfx.BGFX.*;

public enum BGFX_STATE_SHIFT {
    DEPTH_TEST_SHIFT(BGFX_STATE_DEPTH_TEST_SHIFT),
    BLEND_SHIFT(BGFX_STATE_BLEND_SHIFT),
    BLEND_EQUATION_SHIFT(BGFX_STATE_BLEND_EQUATION_SHIFT),
    CULL_SHIFT(BGFX_STATE_CULL_SHIFT),
    ALPHA_REF_SHIFT(BGFX_STATE_ALPHA_REF_SHIFT),
    PT_SHIFT(BGFX_STATE_PT_SHIFT),
    POINT_SIZE_SHIFT(BGFX_STATE_POINT_SIZE_SHIFT),
    RESERVED_SHIFT(BGFX_STATE_RESERVED_SHIFT);

    public final int VALUE;

    BGFX_STATE_SHIFT(int value) {
        this.VALUE = value;
    }

    public static long flags(Set<BGFX_STATE_SHIFT> states) {
        long bits = 0L;
        for (BGFX_STATE_SHIFT next : states) {
            bits |= next.VALUE;
        }
        return bits;
    }
}
