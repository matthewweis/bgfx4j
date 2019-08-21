package com.bariumhoof.bgfx4j.examples.common;

import com.bariumhoof.bgfx4j.enums.*;
import com.bariumhoof.bgfx4j.init.*;
import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.lwjgl.bgfx.BGFXInit;
import org.lwjgl.bgfx.BGFXPlatformData;
import org.lwjgl.glfw.*;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.Platform;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.stream.Stream;

import static org.lwjgl.bgfx.BGFX.*;
import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.system.MemoryStack.stackPush;
import static org.lwjgl.system.MemoryUtil.NULL;

public abstract class TestApplication {

    protected final int width = 1280;
    protected final int height = 720;
    protected final BGFX_DEBUG debug;
    protected final BGFX_RESET reset;

    long window = -1L;

    private static boolean zZeroToOne;

    public TestApplication(BGFX_DEBUG debug, BGFX_RESET reset) {
        this.debug = debug;
        this.reset = reset;
    }

    public final void start() throws IOException {
        try {
            init();
            initPlatform(); // calls render
        } catch (Exception e) {
            throw e;
        } finally {
            dispose();
        }
    }

    private void initPlatform() throws IOException {
        if (!glfwInit()) {
            throw new RuntimeException("Error initializing GLFW");
        }

        // the client (renderer) API is managed by bgfx
        glfwWindowHint(GLFW_CLIENT_API, GLFW_NO_API);
        if (Platform.get() == Platform.MACOSX) {
            glfwWindowHint(GLFW_COCOA_RETINA_FRAMEBUFFER, GLFW_FALSE);
        }

        window = glfwCreateWindow(width, height, "TestApplication", 0, 0);

        if (window == NULL) {
            throw new RuntimeException("Error creating GLFW window");
        }

        glfwSetKeyCallback(window, (windowHnd, key, scancode, action, mods) -> {
            if (action != GLFW_RELEASE) {
                return;
            }

            switch (key) {
                case GLFW_KEY_ESCAPE:
                    glfwSetWindowShouldClose(windowHnd, true);
                    break;
            }
        });

        try (MemoryStack stack = stackPush()) {
            final BGFXInit init = BGFXInit.mallocStack(stack);
            bgfx_init_ctor(init);

            System.out.println(Arrays.toString(BGFX_TEXTURE_FORMAT.values()));
            Stream.of(BGFX_TEXTURE_FORMAT.values()).forEach(it -> System.out.println(String.format("%s -> %d", it.toString(), it.VALUE)));
            init
                    .resolution(it -> {
                        System.out.println(it.format());
                        it
                                .width(width)
                                .height(height);
//                                .reset(BGFX_RESET_VSYNC);
                    });

            switch (Platform.get()) {
                case LINUX:
                    init.platformData()
                            .ndt(GLFWNativeX11.glfwGetX11Display())
                            .nwh(GLFWNativeX11.glfwGetX11Window(window));
                    break;
                case MACOSX:
                    init.platformData()
                            .nwh(GLFWNativeCocoa.glfwGetCocoaWindow(window));
                    break;
                case WINDOWS:
                    init.platformData()
                            .nwh(GLFWNativeWin32.glfwGetWin32Window(window));
                    break;
            }

            if (!bgfx_init(init)) {
                throw new RuntimeException("Error initializing bgfx renderer");
            }

            zZeroToOne = !bgfx_get_caps().homogeneousDepth();
        }


        System.out.println("bgfx renderer: " + bgfx_get_renderer_name(bgfx_get_renderer_type()));

        // Enable debug text.
        bgfx_set_debug(BGFX_DEBUG_TEXT);

        bgfx_set_view_clear(0, BGFX_CLEAR_COLOR | BGFX_CLEAR_DEPTH, 0x303030ff, 1.0f, 0);

        long numFrames = 1L;
        long totalTime = 0L;
        long lastTime;
        long startTime = lastTime = glfwGetTimerValue();

        while (!glfwWindowShouldClose(window)) {
            glfwPollEvents();

            long now = glfwGetTimerValue();
            long frameTime = now - lastTime;
            lastTime = now;
            numFrames++;
            totalTime += frameTime;

            if (numFrames % 500 == 0) {
                numFrames = 1;
                totalTime = 0;
            }

            double freq = glfwGetTimerFrequency();
            double toMs = 1000.0 / freq;

//            float time = (float)((now - startTime) / freq);

            render(toMs);

            // Advance to next frame. Rendering thread will be kicked to
            // process submitted rendering primitives.
            bgfx_frame(false);
        }

        shutdown();
    }

    public abstract void init();
    public abstract void render(double dt);
    public abstract void dispose();

    private final void shutdown() {
        bgfx_shutdown();
        glfwFreeCallbacks(window);
        glfwDestroyWindow(window);
        glfwTerminate();
    }

    static void lookAt(Vector3f at, Vector3f eye, Matrix4f dest) {
        dest.setLookAtLH(eye.x, eye.y, eye.z, at.x, at.y, at.z, 0.0f, 1.0f, 0.0f);
    }

    static void perspective(float fov, int width, int height, float near, float far, Matrix4f dest) {
        float fovRadians = fov * (float) Math.PI / 180.0f;
        float aspect = width / (float) height;
        dest.setPerspectiveLH(fovRadians, aspect, near, far, zZeroToOne);
    }

}
