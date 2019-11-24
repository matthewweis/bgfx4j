package com.bariumhoof.bgfx4j.enums;

import java.util.Set;

import static org.lwjgl.bgfx.BGFX.*;

public enum BGFX_TOPOLOGY_CONVERT {
    TRI_LIST_FLIP_WINDING(BGFX_TOPOLOGY_CONVERT_TRI_LIST_FLIP_WINDING),
    TRI_STRIP_FLIP_WINDING(BGFX_TOPOLOGY_CONVERT_TRI_STRIP_FLIP_WINDING),
    TRI_LIST_TO_LINE_LIST(BGFX_TOPOLOGY_CONVERT_TRI_LIST_TO_LINE_LIST),
    TRI_STRIP_TO_TRI_LIST(BGFX_TOPOLOGY_CONVERT_TRI_STRIP_TO_TRI_LIST),
    LINE_STRIP_TO_LINE_LIST(BGFX_TOPOLOGY_CONVERT_LINE_STRIP_TO_LINE_LIST),
    COUNT(BGFX_TOPOLOGY_CONVERT_COUNT);

    public final int VALUE;

    BGFX_TOPOLOGY_CONVERT(int value) {
        this.VALUE = value;
    }

    public static long flags(Set<BGFX_TOPOLOGY_CONVERT> states) {
        long bits = 0L;
        for (BGFX_TOPOLOGY_CONVERT next : states) {
            bits |= next.VALUE;
        }
        return bits;
    }
}
