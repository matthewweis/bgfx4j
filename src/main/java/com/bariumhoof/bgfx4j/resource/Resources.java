package com.bariumhoof.bgfx4j.resource;

import com.bariumhoof.assertions.Assertions;
import org.jetbrains.annotations.NotNull;
import org.lwjgl.bgfx.BGFXMemory;
import org.lwjgl.bgfx.BGFXReleaseFunctionCallback;
import org.slf4j.Logger;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.ByteBuffer;

import static org.lwjgl.bgfx.BGFX.bgfx_create_shader;
import static org.lwjgl.bgfx.BGFX.bgfx_make_ref_release;
import static org.lwjgl.system.MemoryUtil.*;

public final class Resources {

    private static final Logger log = org.slf4j.LoggerFactory.getLogger(Resources.class);

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
