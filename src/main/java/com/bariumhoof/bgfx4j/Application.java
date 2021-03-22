package com.bariumhoof.bgfx4j;

import com.bariumhoof.Capabilities;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.lwjgl.bgfx.BGFXInit;
import org.lwjgl.glfw.GLFWNativeWin32;
import org.lwjgl.glfw.GLFWNativeX11;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.Platform;
import org.lwjgl.system.macosx.ObjCRuntime;

import java.io.IOException;
import java.net.URL;

import static org.lwjgl.bgfx.BGFX.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.glfw.GLFWNativeCocoa.glfwGetCocoaWindow;
import static org.lwjgl.system.JNI.invokePPP;
import static org.lwjgl.system.JNI.invokePPPV;
import static org.lwjgl.system.MemoryStack.stackPush;
import static org.lwjgl.system.MemoryUtil.NULL;
import static org.lwjgl.system.macosx.ObjCRuntime.objc_getClass;
import static org.lwjgl.system.macosx.ObjCRuntime.sel_getUid;

@Slf4j
public abstract class Application {

    @Getter
    int width = 1280;

    @Getter
    int height = 720;

    public void start() throws IOException {

        long window = initGlfwWindow();
        initBgfx(window);

        System.out.println("bgfx renderer: " + bgfx_get_renderer_name(bgfx_get_renderer_type()));

        // Enable debug text.
        bgfx_set_debug(BGFX_DEBUG_TEXT);
        bgfx_set_view_clear(0, BGFX_CLEAR_COLOR | BGFX_CLEAR_DEPTH, 0x303030ff, 1.0f, 0);

        init();

        loop(window);

        dispose();
        bgfx_shutdown();

//        glfwFreeCallbacks(window);
        glfwDestroyWindow(window);
        glfwTerminate();
    }

    private void loop(long window) {
        long numFrames = 1L;
        long totalTime = 0L;
        long lastTime;
        long startTime = lastTime = glfwGetTimerValue();

        while (!glfwWindowShouldClose(window)) {
            glfwPollEvents();

            final long now = glfwGetTimerValue();
            final long frameTime = now - lastTime;
            lastTime = now;
            numFrames++;
            totalTime += frameTime;

            if (numFrames % 500 == 0) {
                numFrames = 1;
                totalTime = 0;
            }

            final double freq = glfwGetTimerFrequency();
            final double toMs = 1000.0 / freq;

            final float time = (float)((now - startTime) / freq);
            final float dt = (float) (frameTime * toMs);

            bgfx_dbg_text_printf(0, 1, 0x4f, "bgfx/examples/01-cubes");
            bgfx_dbg_text_printf(0, 2, 0x6f, "Description: Rendering simple static mesh.");
            bgfx_dbg_text_printf(0, 3, 0x0f, String.format("Frame: %7.3f[ms]", dt));

            // Set view 0 default viewport.
//            bgfx_set_view_rect(0, 0, 0, width, height);

//            // This dummy draw call is here to make sure that view 0 is cleared
//            // if no other draw calls are submitted to view 0.
            bgfx_touch(0);

            // MY CODE

//            lookAt(new Vector3f(0.0f, 0.0f, 0.0f), new Vector3f(0.0f, 0.0f, -35.0f), view);
//            perspective(60.0f, width, height, 0.1f, 100.0f, proj);
//
//            view.get(viewBuf);
//            proj.get(projBuf);
//            bgfx_set_view_transform(0, viewBuf, projBuf);
//
//            long encoder = bgfx_encoder_begin(false);


            render(dt, time);


            // Advance to next frame. Rendering thread will be kicked to
            // process submitted rendering primitives.
            bgfx_frame(false);

//            try {
//                //noinspection BusyWait
//                Thread.sleep(50); // save some battery during tests
//            } catch (InterruptedException e) {
//                System.err.println("Demo thread slowdown interrupted during game loop.");
//                e.printStackTrace();
//            }

            // todo should encoder_end be after frame? if so apps should call it themselves.
//
//            bgfx_encoder_end(encoder);
        }
    }

    private void initBgfx(long window) {
        try (MemoryStack stack = stackPush()) {
            BGFXInit init = BGFXInit.callocStack(stack);
            bgfx_init_ctor(init);
            init
                    .resolution(it -> it
                            .width(width)
                            .height(height)
                            .reset(BGFX_RESET_VSYNC));
//                            .reset(BGFX_RESET_NONE));

            switch (Platform.get()) {
                case LINUX:
                    init.platformData()
                            .ndt(GLFWNativeX11.glfwGetX11Display())
                            .nwh(GLFWNativeX11.glfwGetX11Window(window));
                    break;
                case MACOSX:
                    long objc_msgSend = ObjCRuntime.getLibrary().getFunctionAddress("objc_msgSend");

                    long layer = invokePPP(objc_getClass("CAMetalLayer"), sel_getUid("alloc"), objc_msgSend);
                    invokePPP(layer, sel_getUid("init"), objc_msgSend);

                    long contentView = invokePPP(glfwGetCocoaWindow(window), sel_getUid("contentView"), objc_msgSend);
                    invokePPPV(contentView, sel_getUid("setLayer:"), layer, objc_msgSend);

                    init.platformData()
                            .nwh(layer);
                    // https://github.com/LWJGL/lwjgl3/issues/619
//                            .nwh(GLFWNativeCocoa.glfwGetCocoaWindow(window));
                    break;
                case WINDOWS:
                    init.platformData()
                            .nwh(GLFWNativeWin32.glfwGetWin32Window(window));
                    break;
            }

            System.out.println("init bgfx!");
//            Application.isInitialized = true;
            if (!bgfx_init(init)) {
                throw new RuntimeException("Error initializing bgfx renderer");
            }

            zZeroToOne = !bgfx_get_caps().homogeneousDepth();
        }
    }

    private long initGlfwWindow() {
        if (!glfwInit()) {
            throw new RuntimeException("Error initializing GLFW");
        }

        // the client (renderer) API is managed by bgfx
        glfwWindowHint(GLFW_CLIENT_API, GLFW_NO_API);
        if (Platform.get() == Platform.MACOSX) {
            glfwWindowHint(GLFW_COCOA_RETINA_FRAMEBUFFER, GLFW_FALSE);
        }

        long window = glfwCreateWindow(width, height, "25-C99", 0, 0);
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
        return window;
    }

    public abstract void init();
    public abstract void render(float dt, float time);
    public abstract void dispose();

    public static void lookAt(Vector3f at, Vector3f eye, Matrix4f dest) {
        dest.setLookAtLH(eye.x, eye.y, eye.z, at.x, at.y, at.z, 0.0f, 1.0f, 0.0f);
    }

    private static boolean zZeroToOne;

    public static void perspective(float fov, int width, int height, float near, float far, Matrix4f dest) {
        float fovRadians = fov * (float) Math.PI / 180.0f;
        float aspect = width / (float) height;
        dest.setPerspectiveLH(fovRadians, aspect, near, far, zZeroToOne);
    }

    public static void ortho(float left, float right, float bottom, float top, float zNear, float zFar, Matrix4f dest) {
        dest.setOrthoLH(left, right, bottom, top, zNear, zFar, zZeroToOne);
    }

    public static boolean isInitialized() {
        log.debug("todo better solution that static init check!!");
        return true;
    }

    @NotNull
    public static URL locateFragmentShaderByName(@NotNull String shaderName) {
        switch (Capabilities.getRendererType()) {
            case DIRECT3D9:
                return Application.class.getResource(String.format("/shaders/dx9/fs_%s.bin", shaderName));
            case DIRECT3D11:
            case DIRECT3D12:
                return Application.class.getResource(String.format("/shaders/dx11/fs_%s.bin", shaderName));
            case METAL:
                return Application.class.getResource(String.format("/shaders/metal/fs_%s.bin", shaderName));
            case OPENGL:
            case OPENGLES:
                return Application.class.getResource(String.format("/shaders/glsl/fs_%s.bin", shaderName));
            case VULKAN:
                return Application.class.getResource(String.format("/shaders/spirv/%s.vert.spv", shaderName));
            default:
                throw new UnsupportedOperationException(
                        String.format("Unsupported shader lookup for renderer: %s.", Capabilities.getRendererType()));
        }
    }

    @NotNull
    public static URL locateVertexShaderByName(@NotNull String shaderName) {
        switch (Capabilities.getRendererType()) {
            case DIRECT3D9:
                return Application.class.getResource(String.format("/shaders/dx9/vs_%s.bin", shaderName));
            case DIRECT3D11:
            case DIRECT3D12:
                return Application.class.getResource(String.format("/shaders/dx11/vs_%s.bin", shaderName));
            case METAL:
                return Application.class.getResource(String.format("/shaders/metal/vs_%s.bin", shaderName));
            case OPENGL:
            case OPENGLES:
                return Application.class.getResource(String.format("/shaders/glsl/vs_%s.bin", shaderName));
            case VULKAN:
                return Application.class.getResource(String.format("/shaders/spirv/%s.vert.spv", shaderName));
            default:
                throw new UnsupportedOperationException(
                        String.format("Unsupported shader lookup for renderer: %s.", Capabilities.getRendererType()));
        }
    }

}
