package com.bariumhoof.bgfx4j.enums;

import static org.lwjgl.bgfx.BGFX.*;

public enum BGFX_ACCESS {
    READ(BGFX_ACCESS_READ),
    WRITE(BGFX_ACCESS_WRITE),
    READWRITE(BGFX_ACCESS_READWRITE),
    COUNT(BGFX_ACCESS_COUNT);

    public final int VALUE;

    BGFX_ACCESS(int value) {
        this.VALUE = value;
    }
}