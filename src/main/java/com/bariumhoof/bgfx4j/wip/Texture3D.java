package com.bariumhoof.bgfx4j.wip;

import org.jetbrains.annotations.NotNull;
import org.lwjgl.bgfx.BGFXMemory;

import static org.lwjgl.bgfx.BGFX.bgfx_update_texture_3d;

// todo
public class Texture3D extends Texture {

    protected Texture3D(short handle) {
        super(handle);
    }

    // todo replace BGFXMemory with api-specific reference
    public void update(@NotNull BGFXMemory textureUpdateInfo, int layer, int mip, int x, int y, int width, int height, int depth) {
        bgfx_update_texture_3d(handle, mip, layer, x, y, width, height, depth, textureUpdateInfo);
    }

}
