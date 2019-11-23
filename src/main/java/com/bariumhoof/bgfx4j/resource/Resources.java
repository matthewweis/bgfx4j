package com.bariumhoof.bgfx4j.resource;

import com.bariumhoof.assertions.Assertions;
import com.bariumhoof.bgfx4j.enums.BGFX_SAMPLER;
import com.bariumhoof.bgfx4j.enums.BGFX_TEXTURE;
import com.bariumhoof.bgfx4j.enums.BGFX_TEXTURE_FORMAT;
import org.jetbrains.annotations.NotNull;
import org.lwjgl.bgfx.BGFXMemory;
import org.lwjgl.bgfx.BGFXReleaseFunctionCallback;
import org.lwjgl.bgfx.BGFXReleaseFunctionCallbackI;
import org.slf4j.Logger;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.util.EnumSet;

import static org.lwjgl.bgfx.BGFX.*;
import static org.lwjgl.system.MemoryUtil.*;

public final class Resources {

    private static final Logger log = org.slf4j.LoggerFactory.getLogger(Resources.class);

    // todo integrate with custom memory release callbacks?
    // todo this should have its dispose called, but how to do from static method? (does it matter?)
    private static BGFXReleaseFunctionCallback RELEASE_MEMORY_CALLBACK =
            BGFXReleaseFunctionCallback.create((_ptr, _userData) -> nmemFree(_ptr));
    private Resources() { }


    // todo rm: no need to handle OS type, this is done by lib using bgfx4j
    public static short loadShader(@NotNull URL url) throws IOException {
        final var shaderCode = loadResource(url);
        final BGFXMemory bgfxMemory = bgfx_make_ref_release(shaderCode, RELEASE_MEMORY_CALLBACK, NULL);
        Assertions.requireNonNull(bgfxMemory);

        return bgfx_create_shader(bgfxMemory);
    }

    // todo looks like he never uses loadTexture directly but instead loadTexture2D, 3D, etc...
//    public static short loadTexture(@NotNull URL url) throws IOException {
//        final var texture = loadResource(url);
//        final BGFXMemory bgfxMemory = bgfx_make_ref_release(texture, RELEASE_MEMORY_CALLBACK, NULL);
//        Assertions.requireNonNull(bgfxMemory);
//
//        return bgfx_create_texture(bgfxMemory, BGFX_TEXTURE_);
//    }
//
//    public static short loadTexture(@NotNull EnumSet<BGFX_TEXTURE> textureFlags,
//                                    @NotNull EnumSet<BGFX_SAMPLER> samplerFlags,
//                                    @NotNull URL url) throws IOException {
//        final var texture = loadResource(url);
//        final BGFXMemory bgfxMemory = bgfx_make_ref_release(texture, RELEASE_MEMORY_CALLBACK, NULL);
//        Assertions.requireNonNull(bgfxMemory);
//        BGFX_SAMPLER.
//        return bgfx_create_texture(bgfxMemory, BGFX_TEXTURE_);
//    }

    private static @NotNull ByteBuffer loadResource(@NotNull URL url) throws IOException {
        final int size = url.openConnection().getContentLength();
        final var res = memAlloc(size);

        try (final var bis = new BufferedInputStream(url.openStream())) {
            int b;
            while ((b = bis.read()) != -1) {
                res.put((byte)b);
            }
        }

        res.flip();
        return res;
    }

}
