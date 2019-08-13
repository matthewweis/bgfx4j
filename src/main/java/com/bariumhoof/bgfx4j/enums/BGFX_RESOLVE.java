package com.bariumhoof.bgfx4j.enums;

import static org.lwjgl.bgfx.BGFX.*;

public enum BGFX_RESOLVE {
    NONE(BGFX_RESOLVE_NONE),
    AUTO_GEN_MIPS(BGFX_RESOLVE_AUTO_GEN_MIPS);

    public final byte VALUE;

    BGFX_RESOLVE(byte value) {
        this.VALUE = value;
    }
}