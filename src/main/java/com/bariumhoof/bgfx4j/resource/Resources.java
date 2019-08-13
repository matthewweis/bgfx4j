package com.bariumhoof.bgfx4j.resource;

import com.bariumhoof.assertions.Assertions;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.lwjgl.bgfx.BGFX;
import org.lwjgl.bgfx.BGFXMemory;
import org.lwjgl.bgfx.BGFXReleaseFunctionCallback;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.nio.ByteBuffer;
import java.util.Objects;

import static org.lwjgl.bgfx.BGFX.*;
import static org.lwjgl.system.APIUtil.apiLog;
import static org.lwjgl.system.MemoryUtil.*;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class Resources {

    private static BGFXReleaseFunctionCallback RELEASE_MEMORY_CALLBACK =
            BGFXReleaseFunctionCallback.create((_ptr, _userData) -> nmemFree(_ptr));


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
