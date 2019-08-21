package com.bariumhoof.bgfx4j.enums;

import static org.lwjgl.bgfx.BGFX.BGFX_TEXTURE_RT_MASK;
import static org.lwjgl.bgfx.BGFX.BGFX_TEXTURE_RT_MSAA_MASK;

public enum BGFX_TEXTURE_RT {
    MSAA_MASK(BGFX_TEXTURE_RT_MSAA_MASK),
    MASK(BGFX_TEXTURE_RT_MASK);

    public final long VALUE;

    BGFX_TEXTURE_RT(long value) {
        this.VALUE = value;
    }
}