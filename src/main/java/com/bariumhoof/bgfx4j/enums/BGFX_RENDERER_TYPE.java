package com.bariumhoof.bgfx4j.enums;

import java.util.Set;

import static org.lwjgl.bgfx.BGFX.*;

public enum BGFX_RENDERER_TYPE {
    NOOP(BGFX_RENDERER_TYPE_NOOP),
    DIRECT3D9(BGFX_RENDERER_TYPE_DIRECT3D9),
    DIRECT3D11(BGFX_RENDERER_TYPE_DIRECT3D11),
    DIRECT3D12(BGFX_RENDERER_TYPE_DIRECT3D12),
    GNM(BGFX_RENDERER_TYPE_GNM),
    METAL(BGFX_RENDERER_TYPE_METAL),
    NVN(BGFX_RENDERER_TYPE_NVN),
    OPENGLES(BGFX_RENDERER_TYPE_OPENGLES),
    OPENGL(BGFX_RENDERER_TYPE_OPENGL),
    VULKAN(BGFX_RENDERER_TYPE_VULKAN),
    COUNT(BGFX_RENDERER_TYPE_COUNT);

    public final int VALUE;

    BGFX_RENDERER_TYPE(int value) {
        this.VALUE = value;
    }

    public static long flags(Set<BGFX_RENDERER_TYPE> states) {
        long bits = 0L;
        for (BGFX_RENDERER_TYPE next : states) {
            bits |= next.VALUE;
        }
        return bits;
    }
}
