package com.bariumhoof.bgfx4j.enums;

import java.util.Set;

import static org.lwjgl.bgfx.BGFX.*;

public enum BGFX_OCCLUSION_QUERY_RESULT {
    INVISIBLE(BGFX_OCCLUSION_QUERY_RESULT_INVISIBLE),
    VISIBLE(BGFX_OCCLUSION_QUERY_RESULT_VISIBLE),
    NORESULT(BGFX_OCCLUSION_QUERY_RESULT_NORESULT),
    COUNT(BGFX_OCCLUSION_QUERY_RESULT_COUNT);

    public final int VALUE;

    BGFX_OCCLUSION_QUERY_RESULT(int value) {
        this.VALUE = value;
    }

    public static long flags(Set<BGFX_OCCLUSION_QUERY_RESULT> states) {
        long bits = 0L;
        for (BGFX_OCCLUSION_QUERY_RESULT next : states) {
            bits |= next.VALUE;
        }
        return bits;
    }
}
