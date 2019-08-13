package com.bariumhoof.bgfx4j.enums;

import static org.lwjgl.bgfx.BGFX.*;

public enum BGFX_UNIFORM_TYPE {
    SAMPLER(BGFX_UNIFORM_TYPE_SAMPLER),
    END(BGFX_UNIFORM_TYPE_END),
    VEC4(BGFX_UNIFORM_TYPE_VEC4),
    MAT3(BGFX_UNIFORM_TYPE_MAT3),
    MAT4(BGFX_UNIFORM_TYPE_MAT4),
    COUNT(BGFX_UNIFORM_TYPE_COUNT);

    public final int VALUE;

    BGFX_UNIFORM_TYPE(int value) {
        this.VALUE = value;
    }
}