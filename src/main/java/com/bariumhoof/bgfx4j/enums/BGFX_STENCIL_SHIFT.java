package com.bariumhoof.bgfx4j.enums;

import java.util.Set;

import static org.lwjgl.bgfx.BGFX.*;

public enum BGFX_STENCIL_SHIFT {
    FUNC_REF_SHIFT(BGFX_STENCIL_FUNC_REF_SHIFT),
    FUNC_RMASK_SHIFT(BGFX_STENCIL_FUNC_RMASK_SHIFT),
    TEST_SHIFT(BGFX_STENCIL_TEST_SHIFT),
    OP_FAIL_S_SHIFT(BGFX_STENCIL_OP_FAIL_S_SHIFT),
    OP_FAIL_Z_SHIFT(BGFX_STENCIL_OP_FAIL_Z_SHIFT),
    OP_PASS_Z_SHIFT(BGFX_STENCIL_OP_PASS_Z_SHIFT);

    public final int VALUE;

    BGFX_STENCIL_SHIFT(int value) {
        this.VALUE = value;
    }

    public static long flags(Set<BGFX_STENCIL_SHIFT> states) {
        long bits = 0L;
        for (BGFX_STENCIL_SHIFT next : states) {
            bits |= next.VALUE;
        }
        return bits;
    }
}
