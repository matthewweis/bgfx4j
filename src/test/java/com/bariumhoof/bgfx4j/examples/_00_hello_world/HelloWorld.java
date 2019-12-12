package com.bariumhoof.bgfx4j.examples._00_hello_world;

import com.bariumhoof.bgfx4j.Application;

import java.io.IOException;
import java.nio.ByteBuffer;

import static org.lwjgl.bgfx.BGFX.*;

/**
 * Bgfx4j port of bgfx example:
 * https://raw.githubusercontent.com/bkaradzic/bgfx/master/examples/00-helloworld/helloworld.cpp
 *
 * Bgfx4j port of lwjgl's port of the above port (lol):
 * https://github.com/LWJGL/lwjgl3/blob/master/modules/samples/src/test/java/org/lwjgl/demo/bgfx/HelloBGFX.java
 */
public class HelloWorld extends Application {

    final ByteBuffer logo = Logo.create();

    public HelloWorld() {
        super();
    }

    @Override
    public void init() {
        System.out.println("init!");
    }

    @Override
    public void render(float dt, float time) {
//        System.out.println("frame! " + dt);
        // Set view 0 default viewport.
        bgfx_set_view_rect(0, 0, 0, getWidth(), getHeight());

        // This dummy draw call is here to make sure that view 0 is cleared
        // if no other draw calls are submitted to view 0.
        bgfx_touch(0);

        // Use debug font to print information about this example.
        bgfx_dbg_text_clear(0, false);
        bgfx_dbg_text_image(Math.max(getWidth() / 2 / 8, 20) - 20,
                Math.max(getHeight() / 2 / 16, 6) - 6,
                40,
                12,
                logo,
                160
        );

        bgfx_dbg_text_printf(0, 1, 0x1f, "bgfx/examples/25-c99");
        bgfx_dbg_text_printf(0, 2, 0x3f, "Description: Initialization and debug text with C99 API.");

        bgfx_dbg_text_printf(0, 3, 0x0f, "Color can be changed with ANSI \u001b[9;me\u001b[10;ms\u001b[11;mc\u001b[12;ma\u001b[13;mp\u001b[14;me\u001b[0m code too.");

        bgfx_dbg_text_printf(80, 4, 0x0f, "\u001b[;0m    \u001b[;1m    \u001b[; 2m    \u001b[; 3m    \u001b[; 4m    \u001b[; 5m    \u001b[; 6m    \u001b[; 7m    \u001b[0m");
        bgfx_dbg_text_printf(80, 5, 0x0f, "\u001b[;8m    \u001b[;9m    \u001b[;10m    \u001b[;11m    \u001b[;12m    \u001b[;13m    \u001b[;14m    \u001b[;15m    \u001b[0m");


    }

    @Override
    public void dispose() {
        System.out.println("dispose!");
    }

    public static void main(String[] args) throws IOException {
        new HelloWorld().start();
    }
}
