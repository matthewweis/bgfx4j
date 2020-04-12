package com.bariumhoof.bgfx4j.wip;

import lombok.experimental.UtilityClass;
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
@UtilityClass
public class Debug {

    @NotNull
    public static ColoredStringBuilder buildColoredString() {
        return new ColoredStringBuilder("");
    }

    @NotNull
    public static ColoredStringBuilder buildColoredString(@Nullable DebugColor backgroundColor, @Nullable DebugColor foregroundColor) {
        return buildColoredString(backgroundColor, foregroundColor, "");
    }

    @NotNull
    public static ColoredStringBuilder buildColoredString(@NotNull String initialText) {
        return new ColoredStringBuilder(initialText);
    }

    @NotNull
    public static ColoredStringBuilder buildColoredString(@Nullable DebugColor backgroundColor, @Nullable DebugColor foregroundColor, @Nullable String initialText) {
        return buildColoredString(createColoredString(backgroundColor, foregroundColor, initialText));
    }

    @NotNull
    public static String createColoredString(@Nullable DebugColor backgroundColor, @Nullable DebugColor foregroundColor, @Nullable String initialText) {
        if (initialText != null) {
            if (backgroundColor != null && foregroundColor != null) {
                return "\u001b[" + foregroundColor.value + ";" + backgroundColor.value + "m" + initialText;
            } else if (backgroundColor != null) {
                return "\u001b[;" + backgroundColor.value + "m" + initialText;
            } else if (foregroundColor != null) {
                return "\u001b[" + foregroundColor.value + ";m" + initialText;
            } else {
                return initialText;
            }
        } else {
            if (backgroundColor != null && foregroundColor != null) {
                return "\u001b[" + foregroundColor.value + ";" + backgroundColor.value + "m";
            } else if (backgroundColor != null) {
                return "\u001b[;" + backgroundColor.value + "m";
            } else if (foregroundColor != null) {
                return "\u001b[" + foregroundColor.value + ";m";
            } else {
                return "";
            }
        }
    }

    public static class ColoredStringBuilder {

        private final String string;

        ColoredStringBuilder(@NotNull String string) {
            this.string = string;
        }

        @NotNull
        public ColoredStringBuilder color(@Nullable DebugColor backgroundColor, @Nullable DebugColor foregroundColor) {
            return new ColoredStringBuilder(string + createColoredString(backgroundColor, foregroundColor, null));
        }

        @NotNull
        public ColoredStringBuilder background(@Nullable DebugColor backgroundColor) {
            return new ColoredStringBuilder(string + createColoredString(backgroundColor, null, null));
        }

        @NotNull
        public ColoredStringBuilder foreground(@Nullable DebugColor foregroundColor) {
            return new ColoredStringBuilder(string + createColoredString(null, foregroundColor, null));
        }

        @NotNull
        public ColoredStringBuilder text(@Nullable String text) {
            return new ColoredStringBuilder(string + createColoredString(null, null, text));
        }

        @NotNull
        public String build() {
            return string;
        }

    }

}
