package com.bariumhoof.bgfx4j.enums;

import java.util.Set;

import static org.lwjgl.bgfx.BGFX.*;

public enum BGFX_UNIFORM_TYPE {
    SAMPLER(BGFX_UNIFORM_TYPE_SAMPLER),
    END(BGFX_UNIFORM_TYPE_END), // Reserved. Do not use.
    VEC4(BGFX_UNIFORM_TYPE_VEC4),
    MAT3(BGFX_UNIFORM_TYPE_MAT3),
    MAT4(BGFX_UNIFORM_TYPE_MAT4),
    COUNT(BGFX_UNIFORM_TYPE_COUNT);

    public final int VALUE;

    BGFX_UNIFORM_TYPE(int value) {
        this.VALUE = value;
    }

    public static long flags(Set<BGFX_UNIFORM_TYPE> states) {
        long bits = 0L;
        for (BGFX_UNIFORM_TYPE next : states) {
            bits |= next.VALUE;
        }
        return bits;
    }
}
