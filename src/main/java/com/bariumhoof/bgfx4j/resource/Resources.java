package com.bariumhoof.bgfx4j.resource;

import com.bariumhoof.assertions.Assertions;
import lombok.experimental.UtilityClass;
import org.jetbrains.annotations.NotNull;
import org.lwjgl.bgfx.BGFXMemory;
import org.lwjgl.bgfx.BGFXReleaseFunctionCallback;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.ByteBuffer;

import static org.lwjgl.bgfx.BGFX.*;
import static org.lwjgl.system.MemoryUtil.*;

/**
 * Provides most basic way to load resources and return a handle. No method overloading, convenience apis, etc,
 * because resources are intended to be loaded from static factory methods in their respective class.
 *
 * todo make this private to library
 */
@UtilityClass
public final class Resources {

    // todo integrate with custom memory release callbacks?
    // todo this should have its dispose called, but how to do from static method? (does it matter?)
    private static BGFXReleaseFunctionCallback RELEASE_MEMORY_CALLBACK =
            BGFXReleaseFunctionCallback.create((_ptr, _userData) -> nmemFree(_ptr));


    // todo rm: no need to handle OS type, this is done by lib using bgfx4j
    public static short loadShader(@NotNull URL url) throws IOException {
        final var bgfxMemory = loadResourceIntoMemory(url);
        return bgfx_create_shader(bgfxMemory);
    }

    public static short loadTexture(long flags, int skip, @NotNull URL url) throws IOException {
        final var bgfxMemory = loadResourceIntoMemory(url);
        return bgfx_create_texture(bgfxMemory, flags, skip, null); // todo info handle
    }

    // todo look into how null bgfxMemory creates mutable texture!
    public static short loadTexture2D(int width, int height, boolean hasMips, int numLayers,
                                      int format, long flags, @NotNull URL url) throws IOException {
        final var bgfxMemory = loadResourceIntoMemory(url);
        return bgfx_create_texture_2d(width, height, hasMips, numLayers, format, flags, bgfxMemory);
    }

    public static short loadMutableTexture2D(int width, int height, boolean hasMips, int numLayers,
                                      int format, long flags, @NotNull URL url) throws IOException {
        // todo check this!
        return bgfx_create_texture_2d(width, height, hasMips, numLayers, format, flags, null);
    }

    public static short loadTexture3D(int width, int height, int depth, boolean hasMips,
                                      int format, long flags, @NotNull URL url) throws IOException {
        final var bgfxMemory = loadResourceIntoMemory(url);
        return bgfx_create_texture_3d(width, height, depth, hasMips, format, flags, bgfxMemory);
    }

    @NotNull
    private static BGFXMemory loadResourceIntoMemory(@NotNull URL url) throws IOException {
        final var buffer = loadResource(url);
        final BGFXMemory bgfxMemory = bgfx_make_ref_release(buffer, RELEASE_MEMORY_CALLBACK, NULL);
        Assertions.requireNonNull(bgfxMemory);
        return bgfxMemory;
    }

    @NotNull
    private static ByteBuffer loadResource(@NotNull URL url) throws IOException {
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
