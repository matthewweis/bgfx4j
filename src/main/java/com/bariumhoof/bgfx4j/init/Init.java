package com.bariumhoof.bgfx4j.init;

import com.bariumhoof.bgfx4j.enums.BGFX_RENDERER_TYPE;
import lombok.Value;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jilt.Builder;
import org.jilt.BuilderStyle;
import org.jilt.Opt;
import org.lwjgl.bgfx.BGFXAllocatorInterface;
import org.lwjgl.bgfx.BGFXCallbackInterface;
import org.lwjgl.bgfx.BGFXInitLimits;

@Value
@Builder(style = BuilderStyle.TYPE_SAFE)
public final class Init {
    final @NotNull PlatformData platformData; // required
    final @NotNull Resolution resolution; // required
    @Opt final @Nullable BGFX_RENDERER_TYPE type;
//    @Opt final short vendorId;
//    @Opt final short deviceId;
//    @Opt final boolean debug; // todo potentially change
//    @Opt final boolean profile;
    @Opt final @Nullable Short vendorId;
    @Opt final @Nullable Short deviceId;
    @Opt final @Nullable Boolean debug;
    @Opt final @Nullable Boolean profile;
    @Opt final @Nullable BGFXInitLimits limits; // todo
    @Opt final @Nullable BGFXCallbackInterface callback; // todo
    @Opt final @Nullable BGFXAllocatorInterface allocator; // todo

    public static InitBuilders.PlatformData builder() {
        return InitBuilder.init();
    }

    public static InitBuilders.Resolution platformData(@NotNull PlatformData platformData) {
        return InitBuilder.init().platformData(platformData);
    }
}
