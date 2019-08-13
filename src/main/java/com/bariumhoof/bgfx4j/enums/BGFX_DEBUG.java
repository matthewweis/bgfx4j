package com.bariumhoof.bgfx4j.enums;

import static org.lwjgl.bgfx.BGFX.*;

public enum BGFX_DEBUG {
    NONE(BGFX_DEBUG_NONE),
    WIREFRAME(BGFX_DEBUG_WIREFRAME),
    IFH(BGFX_DEBUG_IFH),
    STATS(BGFX_DEBUG_STATS),
    TEXT(BGFX_DEBUG_TEXT),
    PROFILER(BGFX_DEBUG_PROFILER);

    public final int VALUE;

    BGFX_DEBUG(int value) {
        this.VALUE = value;
    }
}