package com.bariumhoof.bgfx4j.enums;

import static org.lwjgl.bgfx.BGFX.BGFX_RESET_RESERVED_MASK;
import static org.lwjgl.bgfx.BGFX.BGFX_RESET_RESERVED_SHIFT;

public enum BGFX_RESET_RESERVED {
    SHIFT(BGFX_RESET_RESERVED_SHIFT),
    MASK(BGFX_RESET_RESERVED_MASK);

    public final int VALUE;

    BGFX_RESET_RESERVED(int value) {
        this.VALUE = value;
    }
}