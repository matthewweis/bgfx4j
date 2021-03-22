package com.bariumhoof.bgfx4j.examples._00_hello_world;

import com.bariumhoof.bgfx4j.Application;
import com.bariumhoof.bgfx4j.view.View;
import com.bariumhoof.bgfx4j.wip.Debug;
import com.bariumhoof.bgfx4j.wip.Encoder;

import java.io.IOException;
import java.nio.ByteBuffer;

import static com.bariumhoof.bgfx4j.wip.DebugColor.*;

/**
 * Bgfx4j port of lwjgl's bgfx example:
 * https://github.com/LWJGL/lwjgl3/blob/master/modules/samples/src/test/java/org/lwjgl/demo/bgfx/HelloBGFX.java
 *
 * Which itself is a port of bgfx example:
 * https://raw.githubusercontent.com/bkaradzic/bgfx/master/examples/00-helloworld/helloworld.cpp
 */
public class HelloWorld extends Application {

    private static final String WS = "    "; // whitespace string
    private static final ByteBuffer logo = Logo.create();

    private View view;

    @Override
    public void render(float dt, float time) {
        final var encoder = Encoder.begin();
        view.setViewRect(0, 0, getWidth(), getHeight());
        encoder.touch(view);
        encoder.debugTextClear();

        final int logo_x = Math.max(getWidth() / 2 / 8, 20) - 20;
        final int logo_y = Math.max(getHeight() / 2 / 16, 6) - 6;
        final int logo_width = 40;
        final int logo_height = 12;
        final int logo_pitch = 160;

        encoder.debugTextImage(logo_x, logo_y, logo_width, logo_height, logo, logo_pitch);

        encoder.debugTextPrintf(0, 1, BLUE, WHITE, "bgfx/examples/25-c99");
        encoder.debugTextPrintf(0, 2, CYAN, WHITE, "Description: Initialization and debug text with C99 API.");

        encoder.debugTextPrintf(0, 3, COLORED_DESCRIPTION_STRING);
        encoder.debugTextPrintf(80, 4, COLOR_PALETTE_ROW_1);
        encoder.debugTextPrintf(80, 5, COLOR_PALETTE_ROW_2);
    }

    @Override
    public void init() {
        view = View.create();
    }

    @Override
    public void dispose() { }

    private static final String COLORED_DESCRIPTION_STRING = Debug.buildColoredString()
            .foreground(WHITE).text("Color can be changed with ANSI ")
            .foreground(BRIGHT_BLUE).text("e")       // E
            .foreground(BRIGHT_GREEN).text("s")      // S
            .foreground(BRIGHT_CYAN).text("c")       // C
            .foreground(BRIGHT_RED).text("a")        // A
            .foreground(BRIGHT_MAGENTA).text("p")    // P
            .foreground(YELLOW).text("e")            // E
            .foreground(WHITE).text(" code too.")
            .build();

    private static final String COLOR_PALETTE_ROW_1 = Debug.buildColoredString()
            .background(BLACK).text(WS)
            .background(BLUE).text(WS)
            .background(GREEN).text(WS)
            .background(CYAN).text(WS)
            .background(RED).text(WS)
            .background(MAGENTA).text(WS)
            .background(BROWN).text(WS)
            .background(GREY).text(WS)
            .background(BLACK).text(WS)
            .build();

    private static final String COLOR_PALETTE_ROW_2 = Debug.buildColoredString()
            .background(DARK_GREY).text(WS)
            .background(BRIGHT_BLUE).text(WS)
            .background(BRIGHT_GREEN).text(WS)
            .background(BRIGHT_CYAN).text(WS)
            .background(BRIGHT_RED).text(WS)
            .background(BRIGHT_MAGENTA).text(WS)
            .background(YELLOW).text(WS)
            .background(WHITE).text(WS)
            .background(BLACK).text(WS)
            .build();

    public static void main(String[] args) throws IOException {
        new HelloWorld().start();
    }
}
