package com.bariumhoof.bgfx4j.enums;

import java.util.Set;

import static org.lwjgl.bgfx.BGFX.BGFX_STATE_MASK;
import static org.lwjgl.bgfx.BGFX.*;

public enum BGFX_STATE_MASK {
    DEPTH_TEST_MASK(BGFX_STATE_DEPTH_TEST_MASK),
    BLEND_MASK(BGFX_STATE_BLEND_MASK),
    BLEND_EQUATION_MASK(BGFX_STATE_BLEND_EQUATION_MASK),
    CULL_MASK(BGFX_STATE_CULL_MASK),
    ALPHA_REF_MASK(BGFX_STATE_ALPHA_REF_MASK),
    PT_MASK(BGFX_STATE_PT_MASK),
    POINT_SIZE_MASK(BGFX_STATE_POINT_SIZE_MASK),
    RESERVED_MASK(BGFX_STATE_RESERVED_MASK),
    MASK(BGFX_STATE_MASK);

    public final long VALUE;

    BGFX_STATE_MASK(long value) {
        this.VALUE = value;
    }

    public static long flags(Set<BGFX_STATE_MASK> states) {
        long bits = 0L;
        for (BGFX_STATE_MASK next : states) {
            bits |= next.VALUE;
        }
        return bits;
    }
}
