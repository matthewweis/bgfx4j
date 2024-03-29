package com.bariumhoof.bgfx4j.enums;

import java.util.Set;

import static org.lwjgl.bgfx.BGFX.*;

public enum BGFX_STATE_BLEND {
    ADD(BGFX_STATE_BLEND_ADD),
    ALPHA(BGFX_STATE_BLEND_ALPHA),
    DARKEN(BGFX_STATE_BLEND_DARKEN),
    LIGHTEN(BGFX_STATE_BLEND_LIGHTEN),
    MULTIPLY(BGFX_STATE_BLEND_MULTIPLY),
    NORMAL(BGFX_STATE_BLEND_NORMAL),
    SCREEN(BGFX_STATE_BLEND_SCREEN),
    LINEAR_BURN(BGFX_STATE_BLEND_LINEAR_BURN);

    public final long VALUE;

    BGFX_STATE_BLEND(long value) {
        this.VALUE = value;
    }

    public static long flags(Set<BGFX_STATE_BLEND> states) {
        long bits = 0L;
        for (BGFX_STATE_BLEND next : states) {
            bits |= next.VALUE;
        }
        return bits;
    }
}
