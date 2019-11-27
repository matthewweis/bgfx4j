package com.bariumhoof.bgfx4j.wip;

import com.bariumhoof.assertions.Assertions;
import com.bariumhoof.bgfx4j.enums.BGFX_CAPS;
import com.bariumhoof.bgfx4j.enums.BGFX_CAPS_FORMAT_TEXTURE;
import com.bariumhoof.bgfx4j.enums.BGFX_TEXTURE_FORMAT;
import com.bariumhoof.bgfx4j.resource.Resources;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.net.URL;

import static org.lwjgl.bgfx.BGFX.bgfx_create_texture_2d;

public class Texture2D extends Texture {

    private Texture2D(short handle) {
        super(handle);
    }

    @NotNull
    public static Texture2D createEmpty(int width, int height, @NotNull BGFX_TEXTURE_FORMAT format) {
        return createEmpty(width, height, false, 1, format, DEFAULT);
    }

    @NotNull
    public static Texture2D createEmpty(int width, int height, boolean hasMips, @NotNull BGFX_TEXTURE_FORMAT format) {
        return createEmpty(width, height, hasMips, 1, format, DEFAULT);
    }

    @NotNull
    public static Texture2D createEmpty(int width, int height, boolean hasMips, int numLayers, @NotNull BGFX_TEXTURE_FORMAT format) {
        return createEmpty(width, height, hasMips, numLayers, format, DEFAULT);
    }

    @NotNull
    public static Texture2D createEmpty(int width, int height, boolean hasMips, int numLayers,
                                 @NotNull BGFX_TEXTURE_FORMAT format, @NotNull TextureFlags textureFlags) {
        requireNotEmpty(textureFlags);
        final short handle =  bgfx_create_texture_2d(width, height, hasMips, numLayers, format.VALUE, textureFlags.VALUE, null);
        return new Texture2D(handle);
    }

    @NotNull
    public static Texture2D load(int width, int height, @NotNull BGFX_TEXTURE_FORMAT format, @NotNull URL url) throws IOException {
        final short handle = Resources.loadTexture2D(width, height, false, 1, format.VALUE, DEFAULT.VALUE, url);
        return new Texture2D(handle);
    }

    @NotNull
    public static Texture2D load(int width, int height, @NotNull BGFX_TEXTURE_FORMAT format,
                                 @NotNull TextureFlags textureFlags, @NotNull URL url) throws IOException {
        requireNotEmpty(textureFlags); // instead of empty TEXTURE_NONE should be used. The method that doesn't include this arg will handle this for the user.
        final short handle = Resources.loadTexture2D(width, height, false, 1, format.VALUE, textureFlags.VALUE, url);
        return new Texture2D(handle);
    }

    @NotNull
    public static Texture2D load(int width, int height, int numLayers, @NotNull BGFX_TEXTURE_FORMAT format,
                                 @NotNull TextureFlags textureFlags, @NotNull URL url) throws IOException {
        Assertions.requireIfCapDisabled(numLayers == 1, BGFX_CAPS.TEXTURE_2D_ARRAY);
        requireNotEmpty(textureFlags); // todo need 1 sampler AND 1 texture?
        final short handle = Resources.loadTexture2D(width, height, false, numLayers, format.VALUE, textureFlags.VALUE, url);
        return new Texture2D(handle);
    }

    @NotNull
    public static Texture2D load(int width, int height, boolean hasMips, int numLayers, @NotNull BGFX_TEXTURE_FORMAT format,
                                 @NotNull TextureFlags textureFlags, @NotNull URL url) throws IOException {
        Assertions.requireIfCapDisabled(numLayers == 1, BGFX_CAPS.TEXTURE_2D_ARRAY);
        requireNotEmpty(textureFlags);
        final short handle = Resources.loadTexture2D(width, height, hasMips, numLayers, format.VALUE, textureFlags.VALUE, url);
        return new Texture2D(handle);
    }

    @Nullable
    public static Texture2D loadOrNull(int width, int height, @NotNull BGFX_TEXTURE_FORMAT format, @NotNull URL url) {
        try {
            return load(width, height, format, url);
        } catch (IOException e) {
            return null;
        }
    }

    @Nullable
    public static Texture2D loadOrNull(int width, int height, @NotNull BGFX_TEXTURE_FORMAT format,
                                       @NotNull TextureFlags textureFlags, @NotNull URL url) {
        try {
            return load(width, height, format, textureFlags, url);
        } catch (IOException e) {
            return null;
        }
    }

    @Nullable
    public static Texture2D loadOrNull(int width, int height, int numLayers, @NotNull BGFX_TEXTURE_FORMAT format,
                                 @NotNull TextureFlags textureFlags, @NotNull URL url) {
        try {
            return load(width, height, numLayers, format, textureFlags, url);
        } catch (IOException e) {
            return null;
        }
    }

    @Nullable
    public static Texture2D loadOrNull(int width, int height, boolean hasMips, int numLayers,
                                 @NotNull BGFX_TEXTURE_FORMAT format,
                                 @NotNull TextureFlags textureFlags, @NotNull URL url) {
        try {
            return load(width, height, hasMips, numLayers, format, textureFlags, url);
        } catch (IOException e) {
            return null;
        }
    }

    private static void requireNotEmpty(@NotNull TextureFlags textureFlags) {
        if (textureFlags.VALUE == 0) {
            throw new IllegalArgumentException("TextureFlags must include at least one flag");
        }
    }
}
