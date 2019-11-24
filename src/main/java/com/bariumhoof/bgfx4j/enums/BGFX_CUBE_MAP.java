package com.bariumhoof.bgfx4j.enums;

import java.util.Set;

import static org.lwjgl.bgfx.BGFX.*;

public enum BGFX_CUBE_MAP {
    POSITIVE_X(BGFX_CUBE_MAP_POSITIVE_X),
    NEGATIVE_X(BGFX_CUBE_MAP_NEGATIVE_X),
    POSITIVE_Y(BGFX_CUBE_MAP_POSITIVE_Y),
    NEGATIVE_Y(BGFX_CUBE_MAP_NEGATIVE_Y),
    POSITIVE_Z(BGFX_CUBE_MAP_POSITIVE_Z),
    NEGATIVE_Z(BGFX_CUBE_MAP_NEGATIVE_Z);

    public final byte VALUE;

    BGFX_CUBE_MAP(byte value) {
        this.VALUE = value;
    }

    public static long flags(Set<BGFX_CUBE_MAP> states) {
        long bits = 0L;
        for (BGFX_CUBE_MAP next : states) {
            bits |= next.VALUE;
        }
        return bits;
    }
}
