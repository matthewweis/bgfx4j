package com.bariumhoof.bgfx4j.wip;

import lombok.AccessLevel;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Some bgfx functions take a value called "attr" which is described as:
 *
 * Color palette. Where top 4-bits represent index of background,
 * and bottom 4-bits represent foreground color from standard VGA text palette (ANSI escape codes).
 *
 * In other words its "VGA-compatible text", see: https://en.wikipedia.org/wiki/VGA-compatible_text_mode
 *
 * Bgfx4j will instead use ColorPalette.
 */
public class DebugPalette {

    public static final DebugPalette DEFAULT = DebugPalette.of(null, null);

    protected final int attr;

    private DebugPalette(final int attr) {
        this.attr = attr;
    }

    @NotNull
    public static DebugPalette of(@Nullable DebugColor background, @Nullable DebugColor foreground) {
        final int topFourBits = ((background != null ? background.value : 0x0) & 0xF) << 4;
        final int bottomFourBits = (foreground != null ? foreground.value : 0x0) & 0xF;
        return new DebugPalette(topFourBits | bottomFourBits);
    }

    /**
     * Based on https://en.wikipedia.org/wiki/Video_Graphics_Array#Color_palette
     */
    public enum DebugColor {
        BLACK(0x0),          // 0x0
        BLUE(0x1),           // 0x1
        GREEN(0x2),          // 0x2
        CYAN(0x3),           // 0x3
        RED(0x4),            // 0x4
        MAGENTA(0x5),        // 0x5
        BROWN(0x6),          // 0x6
        GREY(0x7),           // 0x7
        DARK_GREY(0x8),      // 0x8
        BRIGHT_BLUE(0x9),    // 0x9
        BRIGHT_GREEN(0xA),   // 0xA
        BRIGHT_CYAN(0xB),    // 0xB
        BRIGHT_RED(0xC),     // 0xC
        BRIGHT_MAGENTA(0xD), // 0xD
        YELLOW(0xE),         // 0xE
        WHITE(0xF);          // 0xF

        public final int value;

        DebugColor(int attr) {
            this.value = attr;
        }
    }

    @NotNull
    public static ColoredStringBuilder buildColoredString() {
        return new ColoredStringBuilder("");
    }

    @NotNull
    public static ColoredStringBuilder buildColoredString(@Nullable DebugPalette.DebugColor backgroundColor, @Nullable DebugPalette.DebugColor foregroundColor) {
        return buildColoredString(backgroundColor, foregroundColor, "");
    }

    @NotNull
    public static ColoredStringBuilder buildColoredString(@NotNull String initialText) {
        return new ColoredStringBuilder(initialText);
    }

    @NotNull
    public static ColoredStringBuilder buildColoredString(@Nullable DebugPalette.DebugColor backgroundColor, @Nullable DebugPalette.DebugColor foregroundColor, @Nullable String initialText) {
        return buildColoredString(createColoredString(backgroundColor, foregroundColor, initialText));
    }

    @NotNull
    public static String createColoredString(@Nullable DebugPalette.DebugColor backgroundColor, @Nullable DebugPalette.DebugColor foregroundColor, @Nullable String initialText) {
        if (initialText != null) {
            if (backgroundColor != null && foregroundColor != null) {
                return "\u001b[" + backgroundColor.value + ";" + foregroundColor.value + "m" + initialText;
            } else if (backgroundColor != null) {
                return "\u001b[" + backgroundColor.value + ";m" + initialText;
            } else if (foregroundColor != null) {
                return "\u001b[;" + foregroundColor.value + "m" + initialText;
            } else {
                return initialText;
            }
        } else {
            if (backgroundColor != null && foregroundColor != null) {
                return "\u001b[" + backgroundColor.value + ";" + foregroundColor.value + "m";
            } else if (backgroundColor != null) {
                return "\u001b[" + backgroundColor.value + ";m";
            } else if (foregroundColor != null) {
                return "\u001b[;" + foregroundColor.value + "m";
            } else {
                return "";
            }
        }
    }

    public static class ColoredStringBuilder {

        private final String string;

        ColoredStringBuilder(@NotNull String initialString) {
            this.string = initialString;
        }

        @NotNull
        public ColoredStringBuilder setColor(@Nullable DebugPalette.DebugColor backgroundColor, @Nullable DebugPalette.DebugColor foregroundColor) {
            return new ColoredStringBuilder(createColoredString(backgroundColor, foregroundColor, null));
        }

        @NotNull
        public ColoredStringBuilder setBackgroundColor(@Nullable DebugPalette.DebugColor backgroundColor) {
            return new ColoredStringBuilder(createColoredString(backgroundColor, null, null));
        }

        @NotNull
        public ColoredStringBuilder setForegroundColor(@Nullable DebugPalette.DebugColor foregroundColor) {
            return new ColoredStringBuilder(createColoredString(null, foregroundColor, null));
        }

        @NotNull
        public ColoredStringBuilder then(@Nullable String text) {
            return new ColoredStringBuilder(createColoredString(null, null, text));
        }

        @NotNull
        public String build() {
            return string;
        }

    }

}
