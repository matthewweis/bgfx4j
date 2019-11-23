package com.bariumhoof;

import com.bariumhoof.bgfx4j.enums.BGFX_CAPS_FORMAT_TEXTURE;

public @interface TextureAvailability {
    BGFX_CAPS_FORMAT_TEXTURE[] value();
}
