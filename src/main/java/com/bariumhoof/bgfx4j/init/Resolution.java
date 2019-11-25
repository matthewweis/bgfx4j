package com.bariumhoof.bgfx4j.init;

import com.bariumhoof.bgfx4j.enums.BGFX_RESET;
import com.bariumhoof.bgfx4j.enums.BGFX_TEXTURE_FORMAT;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Value;
import org.jilt.Builder;
import org.jilt.BuilderStyle;
import org.jilt.Opt;

@Value
@Builder(style = BuilderStyle.TYPE_SAFE)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
public final class Resolution {
    final int width; // required
    final int height; // required
    @Opt final BGFX_RESET reset;
    @Opt final BGFX_TEXTURE_FORMAT format;
    @Opt final byte maxFrameLatency;
    @Opt final byte numBackBuffers;

    public static ResolutionBuilders.Height width(int width) {
        return ResolutionBuilder.resolution()
                .width(width);
    }

    public static ResolutionBuilders.Width builder() {
        return ResolutionBuilder.resolution();
    }

    public static ResolutionBuilders.Optionals of(int width, int height) {
        return ResolutionBuilder.resolution().width(width).height(height);
    }
}
