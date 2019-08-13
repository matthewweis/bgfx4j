package com.bariumhoof.bgfx4j.view;

import com.bariumhoof.bgfx4j.enums.BGFX_CLEAR;

public final class ClearStrategy {

    private final int value;

//    @Override
//    public int intValue() {
//        return value;
//    }
//
//    @Override
//    public long longValue() {
//        return value;
//    }
//
//    @Override
//    public float floatValue() {
//        return value;
//    }
//
//    @Override
//    public double doubleValue() {
//        return value;
//    }

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
        this.value = value;
    }

}
