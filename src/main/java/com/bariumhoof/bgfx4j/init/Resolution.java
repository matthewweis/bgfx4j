package com.bariumhoof.bgfx4j.init;

import com.bariumhoof.bgfx4j.enums.BGFX_RESET;
import com.bariumhoof.bgfx4j.enums.BGFX_TEXTURE_FORMAT;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Value;
import org.jilt.Builder;
import org.jilt.BuilderStyle;
import org.jilt.Opt;

import java.util.Set;

@Value
@Builder(style = BuilderStyle.TYPE_SAFE)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class Resolution {
    int width; // required
    int height; // required
    @Opt Set<BGFX_RESET> reset;
    @Opt BGFX_TEXTURE_FORMAT format;
    @Opt byte maxFrameLatency;
    @Opt byte numBackBuffers;

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
