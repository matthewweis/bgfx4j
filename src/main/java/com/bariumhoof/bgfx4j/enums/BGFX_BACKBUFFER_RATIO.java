package com.bariumhoof.bgfx4j.enums;

import java.util.Set;

import static org.lwjgl.bgfx.BGFX.*;

public enum BGFX_BACKBUFFER_RATIO {
    EQUAL(BGFX_BACKBUFFER_RATIO_EQUAL),
    HALF(BGFX_BACKBUFFER_RATIO_HALF),
    QUARTER(BGFX_BACKBUFFER_RATIO_QUARTER),
    EIGHTH(BGFX_BACKBUFFER_RATIO_EIGHTH),
    SIXTEENTH(BGFX_BACKBUFFER_RATIO_SIXTEENTH),
    DOUBLE(BGFX_BACKBUFFER_RATIO_DOUBLE),
    COUNT(BGFX_BACKBUFFER_RATIO_COUNT);

    public final int VALUE;

    BGFX_BACKBUFFER_RATIO(int value) {
        this.VALUE = value;
    }

    public static long flags(Set<BGFX_BACKBUFFER_RATIO> states) {
        long bits = 0L;
        for (BGFX_BACKBUFFER_RATIO next : states) {
            bits |= next.VALUE;
        }
        return bits;
    }
}
