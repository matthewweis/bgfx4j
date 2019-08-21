package com.bariumhoof.bgfx4j.init;

import com.bariumhoof.bgfx4j.enums.BGFX_RESET;
import com.bariumhoof.bgfx4j.enums.BGFX_TEXTURE_FORMAT;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Value;
import org.jetbrains.annotations.NotNull;
import org.jilt.Builder;
import org.jilt.BuilderStyle;
import org.jilt.Opt;
import org.lwjgl.bgfx.BGFXPlatformData;

@Builder
@Value
@AllArgsConstructor(access = AccessLevel.PACKAGE)
public final class PlatformData {

    final long nwh;
    final long ndt;
    final long context;
    final long backBuffer;
    final long backBufferDS;

    public static PlatformDataBuilder builder() {
        return PlatformDataBuilder.platformData();
    }

    public static PlatformDataBuilder nwh(long nwh) {
        return PlatformDataBuilder.platformData().nwh(nwh);
    }

    public static PlatformDataBuilder ndt(long ndt) {
        return PlatformDataBuilder.platformData().ndt(ndt);
    }

    public static PlatformDataBuilder context(long context) {
        return PlatformDataBuilder.platformData().context(context);
    }

    public static PlatformDataBuilder backBuffer(long backBuffer) {
        return PlatformDataBuilder.platformData().backBuffer(backBuffer);
    }

    public static PlatformDataBuilder backBufferDS(long backBufferDS) {
        return PlatformDataBuilder.platformData().backBufferDS(backBufferDS);
    }

}