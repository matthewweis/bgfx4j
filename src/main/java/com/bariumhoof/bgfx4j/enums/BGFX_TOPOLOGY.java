package com.bariumhoof.bgfx4j.enums;

import java.util.Set;

import static org.lwjgl.bgfx.BGFX.*;

public enum BGFX_TOPOLOGY {
    TRI_LIST(BGFX_TOPOLOGY_TRI_LIST),
    TRI_STRIP(BGFX_TOPOLOGY_TRI_STRIP),
    LINE_LIST(BGFX_TOPOLOGY_LINE_LIST),
    LINE_STRIP(BGFX_TOPOLOGY_LINE_STRIP),
    POINT_LIST(BGFX_TOPOLOGY_POINT_LIST),
    COUNT(BGFX_TOPOLOGY_COUNT);

    public final int VALUE;

    BGFX_TOPOLOGY(int value) {
        this.VALUE = value;
    }

    public static long flags(Set<BGFX_TOPOLOGY> states) {
        long bits = 0L;
        for (BGFX_TOPOLOGY next : states) {
            bits |= next.VALUE;
        }
        return bits;
    }
}
