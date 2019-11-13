package com.bariumhoof.bgfx4j.view;

import com.bariumhoof.bgfx4j.enums.BGFX_CLEAR;

public final class ClearStrategy {

    public final int VALUE;

    // todo replace with EnumSet!
    public static ClearStrategy just(BGFX_CLEAR flag) {
        return new ClearStrategy(flag);
    }

    public static ClearStrategy all(BGFX_CLEAR ... flags) {
        return new ClearStrategy(flags);
    }

    private ClearStrategy(BGFX_CLEAR ... flags) {
        int value = 0;
        for (BGFX_CLEAR flag : flags) {
            value |= flag.VALUE;
        }
        this.VALUE = value;
    }

}
