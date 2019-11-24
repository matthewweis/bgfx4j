package com.bariumhoof.bgfx4j.enums;

import java.util.Set;

import static org.lwjgl.bgfx.BGFX.*;

public enum BGFX_CLEAR {
    NONE(BGFX_CLEAR_NONE),
    COLOR(BGFX_CLEAR_COLOR),
    DEPTH(BGFX_CLEAR_DEPTH),
    STENCIL(BGFX_CLEAR_STENCIL),
    DISCARD_COLOR_0(BGFX_CLEAR_DISCARD_COLOR_0),
    DISCARD_COLOR_1(BGFX_CLEAR_DISCARD_COLOR_1),
    DISCARD_COLOR_2(BGFX_CLEAR_DISCARD_COLOR_2),
    DISCARD_COLOR_3(BGFX_CLEAR_DISCARD_COLOR_3),
    DISCARD_COLOR_4(BGFX_CLEAR_DISCARD_COLOR_4),
    DISCARD_COLOR_5(BGFX_CLEAR_DISCARD_COLOR_5),
    DISCARD_COLOR_6(BGFX_CLEAR_DISCARD_COLOR_6),
    DISCARD_COLOR_7(BGFX_CLEAR_DISCARD_COLOR_7),
    DISCARD_DEPTH(BGFX_CLEAR_DISCARD_DEPTH),
    DISCARD_STENCIL(BGFX_CLEAR_DISCARD_STENCIL),
    DISCARD_COLOR_MASK(BGFX_CLEAR_DISCARD_COLOR_MASK),
    DISCARD_MASK(BGFX_CLEAR_DISCARD_MASK);

    public final int VALUE;

    BGFX_CLEAR(int value) {
        this.VALUE = value;
    }

    public static long flags(Set<BGFX_CLEAR> states) {
        long bits = 0L;
        for (BGFX_CLEAR next : states) {
            bits |= next.VALUE;
        }
        return bits;
    }
}
