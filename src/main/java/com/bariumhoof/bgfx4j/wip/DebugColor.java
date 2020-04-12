package com.bariumhoof.bgfx4j.wip;

/*
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
