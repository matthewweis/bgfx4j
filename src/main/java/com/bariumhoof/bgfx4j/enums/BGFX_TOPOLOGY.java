package com.bariumhoof.bgfx4j.enums;

import static org.lwjgl.bgfx.BGFX.*;

public enum BGFX_TOPOLOGY {
    TRI_LIST(BGFX_TOPOLOGY_TRI_LIST),
    TRI_STRIP(BGFX_TOPOLOGY_TRI_STRIP),
    LINE_LIST(BGFX_TOPOLOGY_LINE_LIST),
    LINE_STRIP(BGFX_TOPOLOGY_LINE_STRIP),
    POINT_LIST(BGFX_TOPOLOGY_POINT_LIST),
    COUNT(BGFX_TOPOLOGY_COUNT);

    public final int VALUE;

    BGFX_TOPOLOGY(int value) {
        this.VALUE = value;
    }
}