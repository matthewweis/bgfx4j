package com.bariumhoof.bgfx4j.view;


import com.bariumhoof.bgfx4j.enums.BGFX_BACKBUFFER_RATIO;
import com.bariumhoof.bgfx4j.enums.BGFX_VIEW_MODE;
import lombok.NonNull;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.joml.Matrix4f;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

import static org.lwjgl.bgfx.BGFX.*;

/**
 *
 * BEGIN QUOTE (https://bkaradzic.github.io/bgfx/bgfx.html#views)
 *   View is primary sorting mechanism in bgfx. View represent bucket of draw and compute calls. Compute and draw calls
 *   inside bucket are sorted in the way that all compute calls are executed before draw calls. Compute calls are always
 *   in order of submission, while draw calls are sorted by internal state if view is not in sequential mode. In the most
 *   of cases when z-buffer is used this change in order is not noticable to desired output. In cases where order has to
 *   be preserved (for example in rendering GUIs), view can be set to be in sequential order. Sequential order is less
 *   efficient, because it doesn’t allow state change optimization, and should be avoided when possible.
 *
 *   By default views ids are sorted in ascending order. For dynamic renderers where order might not be known until the last moment, view ids can be remaped to arbitrary order by calling bgfx::setViewOrder.
 *
 *   View state is preserved between multiple frames.
 * END QUOTE
 */
public final class View {

    // todo change this so that every instance of view isn't (a) atomically sync'd on construct and (b) can reuse 0
    private static final AtomicInteger NEXT_ID = new AtomicInteger(0);

    private final int id;
    private @Nullable ClearStrategy clearStrategy;
    private @Nullable BGFX_VIEW_MODE viewMode;

    // todo should be values like BGFX_CLEAR.COLOR.VALUE;?
    private final static int DEFAULT_CLEAR_RGBA = 0xFFFFFF;
    private final static float DEFAULT_CLEAR_DEPTH = 1.0f;
    private final static int DEFAULT_CLEAR_STENCIL = 0xFFFFFF;

    private int clearRgba;
    private float clearDepth;
    private int clearStencil;

    // todo combine view with matrix operations like lookAt, etc.
    // todo allocate ONE off-heap matrix behind the scenes per view
    // todo and implement all operations using "nbgfx" instead to reduce overhead
    // todo finally, make class disposable
    public static View create(@NotNull String name) {
        final int id = NEXT_ID.getAndIncrement();
        // transform
        // scissor
        // clear
        // clear_mrt
        // framebuffer
        // mode
        // order
        // name
        // rect
        // rect_ratio
        return new View(id, name, null, null);
    }

//    public static View create(@NotNull String name,
//                              @Nullable ClearStrategy clearStrategy,
//                              @Nullable BGFX_VIEW_MODE viewMode) {
//        final int id = (short) NEXT_ID.getAndIncrement();
////        nbgfx_set_view_mode(id, BGFX_VIEW_MODE.COUNT);
//        return new View(id, name, clearStrategy, viewMode);
//    }

    public static View create(@NotNull String name,
                              @Nullable ClearStrategy clearStrategy,
                              @Nullable BGFX_VIEW_MODE viewMode) {
        final int id = NEXT_ID.getAndIncrement();

        if (viewMode != null) {
            bgfx_set_view_mode(id, viewMode.VALUE);
        }

        if (Objects.nonNull(clearStrategy)) {
            bgfx_set_view_clear(id, clearStrategy.VALUE, DEFAULT_CLEAR_RGBA, DEFAULT_CLEAR_DEPTH, DEFAULT_CLEAR_STENCIL);
        }
        return new View(id, name, clearStrategy, viewMode);
    }

    private View(int id,
                 @NotNull String name,
                 @Nullable ClearStrategy clearStrategy,
                 @Nullable BGFX_VIEW_MODE viewMode) {
        this.id = id;
        this.clearStrategy = clearStrategy;
        this.viewMode = viewMode;
        this.clearRgba = DEFAULT_CLEAR_RGBA;
        this.clearDepth = DEFAULT_CLEAR_DEPTH;
        this.clearStencil = DEFAULT_CLEAR_STENCIL;
//        bgfx_set_view_name(id, name);
    }

    public void setClearStrategy(@NonNull ClearStrategy clearStrategy) {
        this.clearStrategy = clearStrategy;
        refreshViewClear();
    }

    public void setDefaultClearRgba(int clearRgba) {
        this.clearRgba = clearRgba;
        refreshViewClear();
    }

    public void setDefaultClearDepth(float clearDepth) {
        this.clearDepth = clearDepth;
        refreshViewClear();
    }

    public void setDefaultClearStencil(int clearStencil) {
        this.clearStencil = clearStencil;
        refreshViewClear();
    }

    private void refreshViewClear() {
        bgfx_set_view_clear(id, clearStrategy.VALUE, clearRgba, clearDepth, clearStencil);
    }

    public void setTransform(@Nullable ByteBuffer view, @Nullable ByteBuffer proj) {
        bgfx_set_view_transform(id, view, proj);
    }

    public void setTransform(@Nullable FloatBuffer view, @Nullable FloatBuffer proj) {
        bgfx_set_view_transform(id, view, proj);
    }

    public void setTransform(@Nullable float[] view, @Nullable float[] proj) {
        bgfx_set_view_transform(id, view, proj);
    }

    /**
     * According to: https://bkaradzic.github.io/bgfx/internals.html (under view API)
     *
     * "Calling any view API for different views from different threads is safe.
     *  What’s not safe is to update the same view from multiple threads. This will lead to undefined behavior."
     *
     *  For this reason, it's safe to have a non-concurrent variable for output each time this method is called.
     */
    private final float[] reusableViewOutput = new float[16]; // for use with setTransform(Matrix4f, Matrix4f) method only!
    private final float[] reusableProjOutput = new float[16]; // for use with setTransform(Matrix4f, Matrix4f) method only!
    @SuppressWarnings({"ConstantConditions", "UnnecessaryReturnStatement"})
    public void setTransform(@Nullable Matrix4f view, @Nullable Matrix4f proj) {
//        if (view != null) {
//            view.get(reusableViewOutput);
//
//            if (proj != null) {
//                proj.get(reusableProjOutput);
//                bgfx_set_view_transform(id, reusableViewOutput, reusableProjOutput);
//            } else {
//                bgfx_set_view_transform(id, reusableViewOutput, null);
//            }
//
//        } else if (proj != null) {
//            proj.get(reusableProjOutput);
//            bgfx_set_view_transform(id, null, reusableProjOutput);
//        } else {
//            bgfx_set_view_transform(id, reusableViewOutput, null);
//        }

        if (view != null && proj != null) {
            view.get(reusableViewOutput);
            proj.get(reusableProjOutput);
            bgfx_set_view_transform(id, reusableViewOutput, reusableProjOutput);
            return;
        }

        if (view == null && proj != null) {
            proj.get(reusableProjOutput);
            bgfx_set_view_transform(id, null, reusableProjOutput);
            return;
        }

        if (view != null && proj == null) {
            view.get(reusableViewOutput);
            bgfx_set_view_transform(id, reusableViewOutput, null);
            return;
        }

        if (view == null && proj == null) {
            bgfx_set_view_transform(id, (float[]) null, null);
            return;
        }
    }

    public void setViewRect(int x, int y, int width, int height) {
        bgfx_set_view_rect(id, x, y, width, height);
    }

    public void touch() {
        bgfx_touch(id);
    }

    /**
     *
     * @param x position x from upper-left window corner
     * @param y position y from upper-left window corner
     * @param ratio strategy for setting width and height of view
     */
    public void setViewRectRatio(int x, int y, @NotNull BGFX_BACKBUFFER_RATIO ratio) {
        bgfx_set_view_rect_ratio(id, x, y, ratio.VALUE);
    }

    public int id() {
        return id;
    }
}
