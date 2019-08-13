package com.bariumhoof.bgfx4j.enums;

import org.lwjgl.bgfx.BGFX;

public class BGFX_API_VERSION {

    public static final int VALUE = BGFX.BGFX_API_VERSION;

    private BGFX_API_VERSION() {
        throw new UnsupportedOperationException("This class holds static values and cannot be instantiated.");
    }
}