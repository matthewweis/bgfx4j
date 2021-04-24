package com.bariumhoof.bgfx4j.wip;

import com.bariumhoof.assertions.Assertions;
import com.bariumhoof.bgfx4j.Disposable;
import com.bariumhoof.bgfx4j.Handle;
import com.bariumhoof.bgfx4j.enums.BGFX_SAMPLER;
import com.bariumhoof.bgfx4j.enums.BGFX_TEXTURE;
import com.bariumhoof.bgfx4j.enums.BGFX_TEXTURE_FORMAT;
import com.bariumhoof.bgfx4j.resource.Resources;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.lwjgl.bgfx.BGFXTextureInfo;
import org.lwjgl.system.MemoryStack;

import java.io.IOException;
import java.net.URL;
import java.util.EnumSet;
import java.util.Set;

import static org.lwjgl.bgfx.BGFX.*;


public class Texture implements Disposable, Handle {

    protected final short handle;

    // todo shonuld default include the sampler? do they always need grouping?
    protected static final TextureFlags DEFAULT = TextureFlags.create(
            EnumSet.of(BGFX_TEXTURE.NONE),
            EnumSet.of(BGFX_SAMPLER.NONE)
    );

    protected Texture(short handle) {
        this.handle = handle;
    }

    // todo setTexture should be in a "Stage" class

//    @Precondition("Texture must be created with BGFX_TEXTURE_READ_BACK flag.")
//    @Availability(BGFX_CAPS.TEXTURE_READ_BACK)
//    public void read(int mipLevel) {
//
//        // read texture returns the frame number when it will be ready.
//        // this will need some sort of CompleteableFuture. Should it be custom?
////        bgfx_read_texture(handle, );
//    }

    @NotNull
    public static Texture load(@NotNull URL url) throws IOException {
        return load(url, DEFAULT);
    }

    @NotNull
    public static Texture load(@NotNull URL url, @NotNull TextureFlags flags) throws IOException {
        final short handle = Resources.loadTexture(flags.VALUE, 0, url);
        return new Texture(handle);
    }

    @Nullable
    public static Texture loadOrNull(@NotNull URL url) {
        try {
            return load(url, DEFAULT);
        } catch (IOException e) {
            return null;
        }
    }

    @Nullable
    public static Texture loadOrNull(@NotNull URL url, @NotNull TextureFlags flags) {
        try {
            return load(url, flags);
        } catch (IOException e) {
            return null;
        }
    }

    {
        /*
        DONE is_texture_valid                                = apiGetFunctionAddress(BGFX, "bgfx_is_texture_valid"),
        DONE? calc_texture_size                              = apiGetFunctionAddress(BGFX, "bgfx_calc_texture_size"),
        - create_texture                                     = apiGetFunctionAddress(BGFX, "bgfx_create_texture"),
        - create_texture_2d                                  = apiGetFunctionAddress(BGFX, "bgfx_create_texture_2d"),
        - create_texture_2d_scaled                           = apiGetFunctionAddress(BGFX, "bgfx_create_texture_2d_scaled"),
        - create_texture_3d                                  = apiGetFunctionAddress(BGFX, "bgfx_create_texture_3d"),
        - create_texture_cube                                = apiGetFunctionAddress(BGFX, "bgfx_create_texture_cube"),
        update_texture_2d                                    = apiGetFunctionAddress(BGFX, "bgfx_update_texture_2d"),
        update_texture_3d                                    = apiGetFunctionAddress(BGFX, "bgfx_update_texture_3d"),
        update_texture_cube                                  = apiGetFunctionAddress(BGFX, "bgfx_update_texture_cube"),
        read_texture                                         = apiGetFunctionAddress(BGFX, "bgfx_read_texture"),
        set_texture_name                                     = apiGetFunctionAddress(BGFX, "bgfx_set_texture_name"),
        get_direct_access_ptr                                = apiGetFunctionAddress(BGFX, "bgfx_get_direct_access_ptr"),
        destroy_texture                                      = apiGetFunctionAddress(BGFX, "bgfx_destroy_texture"),
        create_frame_buffer                                  = apiGetFunctionAddress(BGFX, "bgfx_create_frame_buffer"),
        create_frame_buffer_scaled                           = apiGetFunctionAddress(BGFX, "bgfx_create_frame_buffer_scaled"),
        create_frame_buffer_from_handles                     = apiGetFunctionAddress(BGFX, "bgfx_create_frame_buffer_from_handles"),
        create_frame_buffer_from_attachment                  = apiGetFunctionAddress(BGFX, "bgfx_create_frame_buffer_from_attachment"),
        create_frame_buffer_from_nwh                         = apiGetFunctionAddress(BGFX, "bgfx_create_frame_buffer_from_nwh"),
        set_frame_buffer_name                                = apiGetFunctionAddress(BGFX, "bgfx_set_frame_buffer_name"),
        get_texture                                          = apiGetFunctionAddress(BGFX, "bgfx_get_texture"),
         */
    }


    /**
     * PRECONDITION: Texture was created with TEXTURE_READ_BACK flag.
     */
    public void read() {
//        // CAPS_TEXTURE_READ_BACK
//        Assertions.require(Capabilities.isSupported(BGFX_CAPS.TEXTURE_READ_BACK));
//
//        // destination buffer
//        final int[] out = new int[100];
////        final int mip = // todo keep track of mip level for user?
////        bgfx_read_texture(handle, out, )
//
////        bgfx_get_texture()
    }


    public static boolean isTextureValid(int depth, boolean isCubeMap, int numLayers,
                                         @NotNull BGFX_TEXTURE_FORMAT format, @NotNull Set<BGFX_TEXTURE> flags) {
        Assertions.requireNonEmpty(flags); // flags must contain at LEAST one flag
        return bgfx_is_texture_valid(depth, isCubeMap, numLayers, format.VALUE, BGFX_TEXTURE.flags(flags));
    }

    // todo replace BGFXTextureInfo with custom structure
    @NotNull
    public final BGFXTextureInfo calcTextureSizeOnMemoryStack(@NotNull final MemoryStack memoryStack, int width, int height,
                                                      int depth, boolean isCubeMap, boolean hasMips, int numLayers,
                                                      @NotNull BGFX_TEXTURE_FORMAT format) {
        // todo is static method needed to conserve caller-site's escape analysis? does it even matter?
        final BGFXTextureInfo texInfo = BGFXTextureInfo.mallocStack(memoryStack);
        bgfx_calc_texture_size(texInfo, width, height, depth, isCubeMap, hasMips, numLayers, format.VALUE);
        return texInfo;
    }

    // todo replace BGFXTextureInfo with custom structure
    @NotNull
    public final BGFXTextureInfo calcTextureSizeOffStack(int width, int height, int depth, boolean isCubeMap,
                                                         boolean hasMips, int numLayers,
                                                         @NotNull BGFX_TEXTURE_FORMAT format) {
        // todo is static method needed to conserve caller-site's escape analysis? does it even matter?
        final BGFXTextureInfo texInfo = BGFXTextureInfo.malloc();
        bgfx_calc_texture_size(texInfo, width, height, depth, isCubeMap, hasMips, numLayers, format.VALUE);
        return texInfo;
    }

    @Override
    public void dispose() {
        bgfx_destroy_texture(handle);
    }

    @Override
    public short handle() {
        return handle;
    }
}

























