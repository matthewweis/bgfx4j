package com.bariumhoof.bgfx4j.enums;

import static org.lwjgl.bgfx.BGFX.*;

public enum BGFX_ATTRIB_TYPE {
    UINT8(BGFX_ATTRIB_TYPE_UINT8),
    UINT10(BGFX_ATTRIB_TYPE_UINT10),
    INT16(BGFX_ATTRIB_TYPE_INT16),
    HALF(BGFX_ATTRIB_TYPE_HALF),
    FLOAT(BGFX_ATTRIB_TYPE_FLOAT),
    COUNT(BGFX_ATTRIB_TYPE_COUNT);

    public final int VALUE;

    BGFX_ATTRIB_TYPE(int value) {
        this.VALUE = value;
    }
}