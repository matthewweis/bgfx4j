package com.bariumhoof.bgfx4j;

import com.bariumhoof.bgfx4j.enums.BGFX_DEBUG;
import com.bariumhoof.bgfx4j.enums.BGFX_RESET;
import com.bariumhoof.bgfx4j.enums.BGFX_TEXTURE_FORMAT;
import com.bariumhoof.bgfx4j.init.Init;
import com.bariumhoof.bgfx4j.init.PlatformData;
import com.bariumhoof.bgfx4j.init.Resolution;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.lwjgl.bgfx.BGFXInit;
import org.lwjgl.glfw.GLFWNativeCocoa;
import org.lwjgl.glfw.GLFWNativeWin32;
import org.lwjgl.glfw.GLFWNativeX11;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.Platform;

import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Stream;

import static org.lwjgl.bgfx.BGFX.*;
import static org.lwjgl.bgfx.BGFX.bgfx_shutdown;
import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.system.MemoryStack.stackPush;
import static org.lwjgl.system.MemoryUtil.NULL;

public abstract class Application {

    protected final int width = 1280;
    protected final int height = 720;
    private @NotNull BGFX_DEBUG debug = BGFX_DEBUG.TEXT;

    private Init init = null;

    private long window = -1L;

    private static boolean zZeroToOne;

    public Application(@NotNull BGFX_DEBUG debug, @NotNull Init init) {
        this.debug = debug;
        this.init = init;
    }

    public Application(@NotNull Init init) {
        this.init = init;
    }

    public Application(@NotNull BGFX_DEBUG debug) {
        this.debug = debug;
    }
    public Application() { }

    private @NotNull Init createDefaultInit() {
        // todo: JK, use EnumSet class instead!
        // todo: change from taking BGFX_RESET a class representing the union of potentially many BGFX_RESET bit operations
        final var resolution = Resolution.of(width, height).reset(BGFX_RESET.VSYNC).build();
        final var platformData = createDefaultPlatformDataFromOS();
        return Init.platformData(platformData).resolution(resolution).build();
    }

    private @NotNull PlatformData createDefaultPlatformDataFromOS() {
        switch (Platform.get()) {
            case LINUX:
                return PlatformData
                        .ndt(GLFWNativeX11.glfwGetX11Display())
                        .nwh(GLFWNativeX11.glfwGetX11Window(window))
                        .build();
            case MACOSX:
                return PlatformData
                        .nwh(GLFWNativeCocoa.glfwGetCocoaWindow(window))
                        .build();
            case WINDOWS:
                return PlatformData
                        .nwh(GLFWNativeWin32.glfwGetWin32Window(window))
                        .build();
            default:
                throw new IllegalStateException("Unable to recognize OS.");
        }
    }

    public final void start() throws IOException {
        try {
            if (Objects.isNull(init)) {
                init = createDefaultInit(); // set default init if user didn't create one // todo perhaps change this behavior to another class like SimpleApplication?
            }
            initPlatform();
            bgfxInit();
            init(); // user init

            System.out.println("bgfx renderer: " + bgfx_get_renderer_name(bgfx_get_renderer_type()));

            // Enable debug text.
            bgfx_set_debug(debug.VALUE);
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

//            shutdown();
        } catch (Exception e) {
            throw e;
        } finally {
            dispose();
            shutdown();
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

        window = glfwCreateWindow(getWidth(), getHeight(), "TestApplication", 0, 0);

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
    }

    private boolean exists(byte value) {
        return value != 0;
    }

    private boolean exists(short value) {
        return value != 0;
    }

    private boolean exists(int value) {
        return value != 0;
    }

    private boolean exists(long value) {
        return value != 0L;
    }

    private boolean exists(@Nullable Object value) {
        return Objects.nonNull(value);
    }

    private void bgfxInit() {
        try (MemoryStack stack = stackPush()) {
            final BGFXInit _init = BGFXInit.mallocStack(stack);
            bgfx_init_ctor(_init);

            // resolution
            _init.resolution(it -> {
                final var data = init.getResolution(); // known to be non-null due to if-statement above
                it.width(data.getWidth());
                it.height(data.getHeight());
                if (exists(data.getReset())) {
                    it.reset(data.getReset().VALUE);
                }
                if (exists(data.getFormat())) {
                    it.format(data.getFormat().VALUE);
                }
                if (exists(data.getMaxFrameLatency())) {
                    it.maxFrameLatency(data.getMaxFrameLatency());
                }
                if (exists(data.getNumBackBuffers())) {
                    it.numBackBuffers(data.getNumBackBuffers());
                }
            });

            // platform data
//            _init.platformData(it -> {
//                final var data = init.getPlatformData(); // non-null
//                if (exists(data.getNdt())) {
//                    it.ndt(data.getNdt());
//                }
//                if (exists(data.getNwh())) {
//                    it.ndt(data.getNwh());
//                }
//                if (exists(data.getContext())) {
//                    it.ndt(data.getContext());
//                }
//                if (exists(data.getBackBuffer())) {
//                    it.ndt(data.getBackBuffer());
//                }
//                if (exists(data.getBackBufferDS())) {
//                    it.ndt(data.getBackBufferDS());
//                }
//            });

            switch (Platform.get()) {
                case LINUX:
                    _init.platformData()
                            .ndt(GLFWNativeX11.glfwGetX11Display())
                            .nwh(GLFWNativeX11.glfwGetX11Window(window));
                    break;
                case MACOSX:
                    _init.platformData()
                            .nwh(GLFWNativeCocoa.glfwGetCocoaWindow(window));
                    break;
                case WINDOWS:
                    _init.platformData()
                            .nwh(GLFWNativeWin32.glfwGetWin32Window(window));
                    break;
            }

            // type
            if (exists(init.getType())) {
                _init.type(init.getType().VALUE);
            }

            // vendor id
            if (exists(init.getVendorId())) {
                _init.vendorId(init.getVendorId());
            }

            // device id
            if (exists(init.getDeviceId())) {
                _init.deviceId(init.getDeviceId());
            }

            // debug
            if (exists(init.getDebug())) {
                _init.debug(init.getDebug());
            }

            // profile
            if (exists(init.getProfile())) {
                _init.debug(init.getProfile());
            }

            // limits
//            if (exists(init.getLimits())) {
//                _init.limits(it -> {
//                    // todo create limits builder and then fill this in
////                    final var data = init.getLimits();
////                    if (exists(data.maxEncoders())) {
////
////                    }
//                });
//            }

            // allocator
            // todo allocator

            // callbacks
            // todo callbacks

            if (!bgfx_init(_init)) {
                throw new RuntimeException("Error initializing bgfx renderer");
            }

            zZeroToOne = !bgfx_get_caps().homogeneousDepth();
        }
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

    public int getWindowWidth() {
        throw new UnsupportedOperationException();
    }

    public int getWindowHeight() {
        throw new UnsupportedOperationException();
    }

    public int getWidth() {
        return width;
//        return init.getResolution().getWidth();
    }

    public int getHeight() {
        return height;
//        return init.getResolution().getHeight();
    }

}
