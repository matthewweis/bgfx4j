package com.bariumhoof.bgfx4j.wip;

import com.bariumhoof.assertions.Assertions;
import com.bariumhoof.bgfx4j.enums.BGFX_CAPS;
import com.bariumhoof.bgfx4j.enums.BGFX_TEXTURE_FORMAT;
import com.bariumhoof.bgfx4j.resource.Resources;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.lwjgl.bgfx.BGFXMemory;

import java.io.IOException;
import java.net.URL;

import static org.lwjgl.bgfx.BGFX.*;

public class Texture2D extends Texture {

    @Getter
    private final int width;

    @Getter
    private final int height;

    private Texture2D(short handle, int width, int height) {
        super(handle);
        this.width = width;
        this.height = height;
    }

    // todo replace BGFXMemory with api-specific reference
//    public void update(@NotNull BGFXMemory textureUpdateInfo, int layer, int mip, int x, int y, int width, int height, int pitch) {
//        /*
//         {@link BGFX#bgfx_alloc alloc}, {@link BGFX#bgfx_copy copy}, or {@link BGFX#bgfx_make_ref make_ref}.
//         */
//        bgfx_update_texture_2d(handle, layer, mip, x, y, width, height, textureUpdateInfo, pitch);
//    }

    // todo do we even want to expose memory?
    public void update(@NotNull Memory textureUpdateInfo, int layer, int mip, int x, int y, int width, int height, int pitch) {
        /*
         {@link BGFX#bgfx_alloc alloc}, {@link BGFX#bgfx_copy copy}, or {@link BGFX#bgfx_make_ref make_ref}.
         */
        bgfx_update_texture_2d(handle, layer, mip, x, y, width, height, textureUpdateInfo.bgfxMemory, pitch);
    }

    public void update(@NotNull BGFXMemory textureUpdateInfo, int layer, int mip, int x, int y, int width, int height, int pitch) {
        /*
         {@link BGFX#bgfx_alloc alloc}, {@link BGFX#bgfx_copy copy}, or {@link BGFX#bgfx_make_ref make_ref}.
         */
        bgfx_update_texture_2d(handle, layer, mip, x, y, width, height, textureUpdateInfo, pitch);
    }

//    public void update(byte[] textureUpdateInfo, int layer, int mip, int x, int y, int width, int height, int pitch) {
//        // bgfx alloc'd memory is free'd automatically by bgfx
////        bgfx_alloc()
//
//        final BGFXMemory bgfxMemory = bgfx_alloc(7);
//
//        bgfx_update_texture_2d(handle, layer, mip, x, y, width, height, textureUpdateInfo.bgfxMemory, pitch);
//    }

    public void update(int[] textureUpdateInfo, int x, int y, int width, int height) {
        // bgfx alloc'd memory is free'd automatically by bgfx
//        bgfx_alloc()

        final BGFXMemory bgfxMemory = bgfx_copy(textureUpdateInfo);
        final int pitch = width * Integer.BYTES;

        // pitch is the number of bytes per row in the input image
        // must be mutable image

        bgfx_update_texture_2d(handle, 0, 0, x, y, width, height, bgfxMemory, pitch);
    }

    public void update(@NotNull BGFXMemory textureUpdateInfo, int x, int y, int width, int height) {
        /*
         {@link BGFX#bgfx_alloc alloc}, {@link BGFX#bgfx_copy copy}, or {@link BGFX#bgfx_make_ref make_ref}.
         */
        final int pitch = width * Integer.BYTES;
        bgfx_update_texture_2d(handle, 0, 0, x, y, width, height, textureUpdateInfo, pitch);
    }

    public int[] readToInts() {
        return readToInts(0);
    }

    public int[] readToInts(int mip) {
        final int[] result = new int[width * height];
        bgfx_read_texture(handle, result, 0);
        return result;
    }

    // all createMutableEmpty are mutable because _mem passed is NULL

    @NotNull
    public static Texture2D createMutableEmpty(int width, int height, @NotNull BGFX_TEXTURE_FORMAT format) {
        return createMutableEmpty(width, height, false, 1, format, DEFAULT);
    }

    @NotNull
    public static Texture2D createMutableEmpty(int width, int height, @NotNull BGFX_TEXTURE_FORMAT format, @NotNull TextureFlags textureFlags) {
        return createMutableEmpty(width, height, false, 1, format, textureFlags);
    }

    @NotNull
    public static Texture2D createMutableEmpty(int width, int height, boolean hasMips, @NotNull BGFX_TEXTURE_FORMAT format) {
        return createMutableEmpty(width, height, hasMips, 1, format, DEFAULT);
    }

    @NotNull
    public static Texture2D createMutableEmpty(int width, int height, boolean hasMips, int numLayers, @NotNull BGFX_TEXTURE_FORMAT format) {
        return createMutableEmpty(width, height, hasMips, numLayers, format, DEFAULT);
    }

    @NotNull
    public static Texture2D createMutableEmpty(int width, int height, boolean hasMips, int numLayers,
                                               @NotNull BGFX_TEXTURE_FORMAT format, @NotNull TextureFlags textureFlags) {

//        System.out.println("warn: createMutableEmpty requireNotEmpty check is off!");
//        requireNotEmpty(textureFlags);

        /**
         *      * @param _flags     texture creation and sampler flags. Default texture sampling mode is linear, and wrap mode is repeat.
         *      One or more of:<br><table><tr><td>{@link #BGFX_TEXTURE_NONE TEXTURE_NONE}</td><td>{@link #BGFX_TEXTURE_MSAA_SAMPLE TEXTURE_MSAA_SAMPLE}</td>
         *      <td>{@link #BGFX_TEXTURE_RT TEXTURE_RT}</td><td>{@link #BGFX_TEXTURE_RT_MSAA_X2 TEXTURE_RT_MSAA_X2}</td></tr>
         *      <tr><td>{@link #BGFX_TEXTURE_RT_MSAA_X4 TEXTURE_RT_MSAA_X4}</td><td>{@link #BGFX_TEXTURE_RT_MSAA_X8 TEXTURE_RT_MSAA_X8}</td>
         *      <td>{@link #BGFX_TEXTURE_RT_MSAA_X16 TEXTURE_RT_MSAA_X16}</td><td>{@link #BGFX_TEXTURE_RT_WRITE_ONLY TEXTURE_RT_WRITE_ONLY}</td></tr>
         *      <tr><td>{@link #BGFX_TEXTURE_COMPUTE_WRITE TEXTURE_COMPUTE_WRITE}</td><td>{@link #BGFX_TEXTURE_SRGB TEXTURE_SRGB}</td
         *      ><td>{@link #BGFX_TEXTURE_BLIT_DST TEXTURE_BLIT_DST}</td><td>{@link #BGFX_TEXTURE_READ_BACK TEXTURE_READ_BACK}</td></tr><tr>
         *          <td>{@link #BGFX_SAMPLER_NONE SAMPLER_NONE}</td><td>{@link #BGFX_SAMPLER_U_MIRROR SAMPLER_U_MIRROR}</td><td>{@link #BGFX_SAMPLER_U_CLAMP SAMPLER_U_CLAMP}</td><td>{@link #BGFX_SAMPLER_U_BORDER SAMPLER_U_BORDER}</td>
         *      </tr><tr><td>{@link #BGFX_SAMPLER_V_MIRROR SAMPLER_V_MIRROR}</td><td>{@link #BGFX_SAMPLER_V_CLAMP SAMPLER_V_CLAMP}</td>
         *      <td>{@link #BGFX_SAMPLER_V_BORDER SAMPLER_V_BORDER}</td><td>{@link #BGFX_SAMPLER_W_MIRROR SAMPLER_W_MIRROR}</td></tr><tr><td>{@link #BGFX_SAMPLER_W_CLAMP SAMPLER_W_CLAMP}</td>
         *      <td>{@link #BGFX_SAMPLER_W_BORDER SAMPLER_W_BORDER}</td><td>{@link #BGFX_SAMPLER_UVW_MIRROR SAMPLER_UVW_MIRROR}</td>
         *      <td>{@link #BGFX_SAMPLER_UVW_CLAMP SAMPLER_UVW_CLAMP}</td></tr><tr><td>{@link #BGFX_SAMPLER_UVW_BORDER SAMPLER_UVW_BORDER}</td><td>{@link #BGFX_SAMPLER_MIN_POINT SAMPLER_MIN_POINT}</td>
         *      <td>{@link #BGFX_SAMPLER_MIN_ANISOTROPIC SAMPLER_MIN_ANISOTROPIC}</td><td>{@link #BGFX_SAMPLER_MAG_POINT SAMPLER_MAG_POINT}</td></tr>
         *      <tr><td>{@link #BGFX_SAMPLER_MAG_ANISOTROPIC SAMPLER_MAG_ANISOTROPIC}</td><td>{@link #BGFX_SAMPLER_MIP_POINT SAMPLER_MIP_POINT}</td><td>{@link #BGFX_SAMPLER_POINT SAMPLER_POINT}</td>
         *      <td>{@link #BGFX_SAMPLER_COMPARE_LESS SAMPLER_COMPARE_LESS}</td></tr><tr><td>{@link #BGFX_SAMPLER_COMPARE_LEQUAL SAMPLER_COMPARE_LEQUAL}</td><td>{@link #BGFX_SAMPLER_COMPARE_EQUAL SAMPLER_COMPARE_EQUAL}</td>
         *      <td>{@link #BGFX_SAMPLER_COMPARE_GEQUAL SAMPLER_COMPARE_GEQUAL}</td><td>{@link #BGFX_SAMPLER_COMPARE_GREATER SAMPLER_COMPARE_GREATER}</td></tr><tr><td>{@link #BGFX_SAMPLER_COMPARE_NOTEQUAL SAMPLER_COMPARE_NOTEQUAL}</td>
         *      <td>{@link #BGFX_SAMPLER_COMPARE_NEVER SAMPLER_COMPARE_NEVER}</td><td>{@link #BGFX_SAMPLER_COMPARE_ALWAYS SAMPLER_COMPARE_ALWAYS}</td><td>{@link #BGFX_SAMPLER_SAMPLE_STENCIL SAMPLER_SAMPLE_STENCIL}</td></tr></table>
         */
        final short handle =  bgfx_create_texture_2d(width, height,  hasMips, numLayers, format.VALUE, textureFlags.VALUE, null);
        return new Texture2D(handle, width, height);
    }

    @NotNull
    public static Texture2D load(int width, int height, @NotNull BGFX_TEXTURE_FORMAT format, @NotNull URL url) throws IOException {
        final short handle = Resources.loadTexture2D(width, height, false, 1, format.VALUE, DEFAULT.VALUE, url);
        return new Texture2D(handle, width, height);
    }

    @NotNull
    public static Texture2D load(int width, int height, @NotNull BGFX_TEXTURE_FORMAT format,
                                 @NotNull TextureFlags textureFlags, @NotNull URL url) throws IOException {
        requireNotEmpty(textureFlags); // instead of empty TEXTURE_NONE should be used. The method that doesn't include this arg will handle this for the user.
        final short handle = Resources.loadTexture2D(width, height, false, 1, format.VALUE, textureFlags.VALUE, url);
        return new Texture2D(handle, width, height);
    }

    @NotNull
    public static Texture2D load(int width, int height, int numLayers, @NotNull BGFX_TEXTURE_FORMAT format,
                                 @NotNull TextureFlags textureFlags, @NotNull URL url) throws IOException {
        Assertions.requireIfCapDisabled(numLayers == 1, BGFX_CAPS.TEXTURE_2D_ARRAY);
        requireNotEmpty(textureFlags); // todo need 1 sampler AND 1 texture?
        final short handle = Resources.loadTexture2D(width, height, false, numLayers, format.VALUE, textureFlags.VALUE, url);
        return new Texture2D(handle, width, height);
    }

    @NotNull
    public static Texture2D load(int width, int height, boolean hasMips, int numLayers, @NotNull BGFX_TEXTURE_FORMAT format,
                                 @NotNull TextureFlags textureFlags, @NotNull URL url) throws IOException {
        Assertions.requireIfCapDisabled(numLayers == 1, BGFX_CAPS.TEXTURE_2D_ARRAY);
        requireNotEmpty(textureFlags);
        final short handle = Resources.loadTexture2D(width, height, hasMips, numLayers, format.VALUE, textureFlags.VALUE, url);
        return new Texture2D(handle, width, height);
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
