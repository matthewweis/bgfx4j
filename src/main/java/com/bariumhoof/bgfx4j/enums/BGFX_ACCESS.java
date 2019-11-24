package com.bariumhoof.bgfx4j.enums;

import java.util.Set;

import static org.lwjgl.bgfx.BGFX.*;

public enum BGFX_ACCESS {
    READ(BGFX_ACCESS_READ),
    WRITE(BGFX_ACCESS_WRITE),
    READWRITE(BGFX_ACCESS_READWRITE),
    COUNT(BGFX_ACCESS_COUNT);

    public final int VALUE;

    BGFX_ACCESS(int value) {
        this.VALUE = value;
    }

    public static long flags(Set<BGFX_ACCESS> states) {
        long bits = 0L;
        for (BGFX_ACCESS next : states) {
            bits |= next.VALUE;
        }
        return bits;
    }
}
