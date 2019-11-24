package com.bariumhoof.bgfx4j.enums;

import java.util.Set;

import static org.lwjgl.bgfx.BGFX.*;

public enum BGFX_CAPS_FORMAT_TEXTURE {
    NONE(BGFX_CAPS_FORMAT_TEXTURE_NONE),
    _2D(BGFX_CAPS_FORMAT_TEXTURE_2D),
    _2D_SRGB(BGFX_CAPS_FORMAT_TEXTURE_2D_SRGB),
    _2D_EMULATED(BGFX_CAPS_FORMAT_TEXTURE_2D_EMULATED),
    _3D(BGFX_CAPS_FORMAT_TEXTURE_3D),
    _3D_SRGB(BGFX_CAPS_FORMAT_TEXTURE_3D_SRGB),
    _3D_EMULATED(BGFX_CAPS_FORMAT_TEXTURE_3D_EMULATED),
    CUBE(BGFX_CAPS_FORMAT_TEXTURE_CUBE),
    CUBE_SRGB(BGFX_CAPS_FORMAT_TEXTURE_CUBE_SRGB),
    CUBE_EMULATED(BGFX_CAPS_FORMAT_TEXTURE_CUBE_EMULATED),
    VERTEX(BGFX_CAPS_FORMAT_TEXTURE_VERTEX),
    IMAGE(BGFX_CAPS_FORMAT_TEXTURE_IMAGE),
    FRAMEBUFFER(BGFX_CAPS_FORMAT_TEXTURE_FRAMEBUFFER),
    FRAMEBUFFER_MSAA(BGFX_CAPS_FORMAT_TEXTURE_FRAMEBUFFER_MSAA),
    MSAA(BGFX_CAPS_FORMAT_TEXTURE_MSAA),
    MIP_AUTOGEN(BGFX_CAPS_FORMAT_TEXTURE_MIP_AUTOGEN);

    public final short VALUE;

    BGFX_CAPS_FORMAT_TEXTURE(short value) {
        this.VALUE = value;
    }

    public static long flags(Set<BGFX_CAPS_FORMAT_TEXTURE> states) {
        long bits = 0L;
        for (BGFX_CAPS_FORMAT_TEXTURE next : states) {
            bits |= next.VALUE;
        }
        return bits;
    }
}
