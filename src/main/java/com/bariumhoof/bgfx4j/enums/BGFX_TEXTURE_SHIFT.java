package com.bariumhoof.bgfx4j.enums;

import org.lwjgl.bgfx.BGFX;

import java.util.Set;

public class BGFX_TEXTURE_SHIFT {
    public static final int VALUE = BGFX.BGFX_TEXTURE_RT_MSAA_SHIFT;

    private BGFX_TEXTURE_SHIFT() {
        throw new UnsupportedOperationException("This class holds static values and cannot be instantiated.");
    }

    public static long flags(Set<BGFX_TEXTURE_SHIFT> states) {
        long bits = 0L;
        for (BGFX_TEXTURE_SHIFT next : states) {
            bits |= next.VALUE;
        }
        return bits;
    }
}
