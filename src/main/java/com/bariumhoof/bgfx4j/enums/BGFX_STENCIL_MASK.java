package com.bariumhoof.bgfx4j.enums;

import java.util.Set;

import static org.lwjgl.bgfx.BGFX.BGFX_STENCIL_MASK;
import static org.lwjgl.bgfx.BGFX.*;

public enum BGFX_STENCIL_MASK {
    FUNC_REF_MASK(BGFX_STENCIL_FUNC_REF_MASK),
    FUNC_RMASK_MASK(BGFX_STENCIL_FUNC_RMASK_MASK),
    TEST_MASK(BGFX_STENCIL_TEST_MASK),
    OP_FAIL_S_MASK(BGFX_STENCIL_OP_FAIL_S_MASK),
    OP_FAIL_Z_MASK(BGFX_STENCIL_OP_FAIL_Z_MASK),
    OP_PASS_Z_MASK(BGFX_STENCIL_OP_PASS_Z_MASK),
    MASK(BGFX_STENCIL_MASK);

    public final int VALUE;

    BGFX_STENCIL_MASK(int value) {
        this.VALUE = value;
    }

    public static long flags(Set<BGFX_STENCIL_MASK> states) {
        long bits = 0L;
        for (BGFX_STENCIL_MASK next : states) {
            bits |= next.VALUE;
        }
        return bits;
    }
}
