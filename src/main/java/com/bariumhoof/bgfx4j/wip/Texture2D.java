package com.bariumhoof.bgfx4j.wip;

import com.bariumhoof.assertions.Assertions;
import com.bariumhoof.bgfx4j.enums.BGFX_CAPS;
import com.bariumhoof.bgfx4j.enums.BGFX_SAMPLER;
import com.bariumhoof.bgfx4j.enums.BGFX_TEXTURE;
import com.bariumhoof.bgfx4j.enums.BGFX_TEXTURE_FORMAT;
import com.bariumhoof.bgfx4j.resource.Resources;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.net.URL;
import java.util.EnumSet;
import java.util.Set;

public class Texture2D extends Texture {

    private Texture2D(short handle) {
        super(handle);
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
    public static Texture2D load(int width, int height, int numLayers,
                                 @NotNull BGFX_TEXTURE_FORMAT format,
                                 @NotNull TextureFlags textureFlags, @NotNull URL url) throws IOException {
        Assertions.requireIfCapDisabled(numLayers == 1, BGFX_CAPS.TEXTURE_2D_ARRAY);
        requireNotEmpty(textureFlags); // todo need 1 sampler AND 1 texture?
        final short handle = Resources.loadTexture2D(width, height, false, numLayers, format.VALUE, textureFlags.VALUE, url);
        return new Texture2D(handle);
    }

    @NotNull
    public static Texture2D load(int width, int height, boolean hasMips, int numLayers,
                                 @NotNull BGFX_TEXTURE_FORMAT format,
                                 @NotNull TextureFlags textureFlags, @NotNull URL url) throws IOException {
        Assertions.requireIfCapDisabled(numLayers == 1, BGFX_CAPS.TEXTURE_2D_ARRAY);
        requireNotEmpty(textureFlags);
        final short handle = Resources.loadTexture2D(width, height, hasMips, numLayers, format.VALUE, textureFlags.VALUE, url);
        return new Texture2D(handle);
    }

//    public static BuilderInitialStage builder(int width, int height, @NotNull BGFX_TEXTURE_FORMAT format) {
//        return new BuilderInitialStage(rendererType);
//    }
//
//    public static class BuilderInitialStage {
//
//        final int width;
//        final int height;
//        @NotNull final BGFX_TEXTURE_FORMAT format;
//
//        private BuilderInitialStage(int width, int height, @NotNull BGFX_TEXTURE_FORMAT format) {
//            this.width = width;
//            this.height = height;
//            this.format = format;
//        }
//
//        public Texture2D build() {
//            return load(width, height, false, 1, format, );
//        }
//
//    }
//
//    public static class BuilderFormatStage {
//
//        private BuilderInitialStage() {
//
//        }
//
//        public BuilderSurplusStage beginWith(@NotNull BGFX_ATTRIB attrib, @NotNull BGFX_ATTRIB_TYPE type) {
//            beginWith(attrib, type, NORMALIZED_DEFAULT, AS_INT_DEFAULT);
//            return new BuilderSurplusStage(decl);
//        }
//    }

    private static void requireNotEmpty(@NotNull TextureFlags textureFlags) {
        if (textureFlags.VALUE == 0) {
            throw new IllegalArgumentException("TextureFlags must include at least one flag");
        }
    }
}
