package com.bariumhoof.bgfx4j.enums;

import java.util.Set;

import static org.lwjgl.bgfx.BGFX.BGFX_RESOLVE_AUTO_GEN_MIPS;
import static org.lwjgl.bgfx.BGFX.BGFX_RESOLVE_NONE;

public enum BGFX_RESOLVE {
    NONE(BGFX_RESOLVE_NONE),
    AUTO_GEN_MIPS(BGFX_RESOLVE_AUTO_GEN_MIPS);

    public final byte VALUE;

    BGFX_RESOLVE(byte value) {
        this.VALUE = value;
    }

    public static long flags(Set<BGFX_RESOLVE> states) {
        long bits = 0L;
        for (BGFX_RESOLVE next : states) {
            bits |= next.VALUE;
        }
        return bits;
    }
}
