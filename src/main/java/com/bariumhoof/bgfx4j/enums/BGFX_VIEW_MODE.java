package com.bariumhoof.bgfx4j.enums;

import java.util.Set;

import static org.lwjgl.bgfx.BGFX.*;

public enum BGFX_VIEW_MODE {
    DEFAULT(BGFX_VIEW_MODE_DEFAULT),
    SEQUENTIAL(BGFX_VIEW_MODE_SEQUENTIAL),
    DEPTH_ASCENDING(BGFX_VIEW_MODE_DEPTH_ASCENDING),
    DEPTH_DESCENDING(BGFX_VIEW_MODE_DEPTH_DESCENDING),
    COUNT(BGFX_VIEW_MODE_COUNT);

    public final int VALUE;

    BGFX_VIEW_MODE(int value) {
        this.VALUE = value;
    }

    public static long flags(Set<BGFX_VIEW_MODE> states) {
        long bits = 0L;
        for (BGFX_VIEW_MODE next : states) {
            bits |= next.VALUE;
        }
        return bits;
    }
}
