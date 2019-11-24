package com.bariumhoof.bgfx4j.enums;

import java.util.Set;

import static org.lwjgl.bgfx.BGFX.*;

public enum BGFX_BUFFER {
    NONE(BGFX_BUFFER_NONE),
    COMPUTE_READ(BGFX_BUFFER_COMPUTE_READ),
    COMPUTE_WRITE(BGFX_BUFFER_COMPUTE_WRITE),
    DRAW_INDIRECT(BGFX_BUFFER_DRAW_INDIRECT),
    ALLOW_RESIZE(BGFX_BUFFER_ALLOW_RESIZE),
    INDEX32(BGFX_BUFFER_INDEX32),
    COMPUTE_READ_WRITE(BGFX_BUFFER_COMPUTE_READ_WRITE);

    public final int VALUE;

    BGFX_BUFFER(int value) {
        this.VALUE = value;
    }

    public static long flags(Set<BGFX_BUFFER> states) {
        long bits = 0L;
        for (BGFX_BUFFER next : states) {
            bits |= next.VALUE;
        }
        return bits;
    }
}
