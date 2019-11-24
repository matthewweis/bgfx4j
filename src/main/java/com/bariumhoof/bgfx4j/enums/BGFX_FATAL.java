package com.bariumhoof.bgfx4j.enums;

import java.util.Set;

import static org.lwjgl.bgfx.BGFX.*;

public enum BGFX_FATAL {
    DEBUG_CHECK(BGFX_FATAL_DEBUG_CHECK),
    INVALID_SHADER(BGFX_FATAL_INVALID_SHADER),
    UNABLE_TO_INITIALIZE(BGFX_FATAL_UNABLE_TO_INITIALIZE),
    UNABLE_TO_CREATE_TEXTURE(BGFX_FATAL_UNABLE_TO_CREATE_TEXTURE),
    DEVICE_LOST(BGFX_FATAL_DEVICE_LOST),
    COUNT(BGFX_FATAL_COUNT);

    public final int VALUE;

    BGFX_FATAL(int value) {
        this.VALUE = value;
    }

    public static long flags(Set<BGFX_FATAL> states) {
        long bits = 0L;
        for (BGFX_FATAL next : states) {
            bits |= next.VALUE;
        }
        return bits;
    }
}
