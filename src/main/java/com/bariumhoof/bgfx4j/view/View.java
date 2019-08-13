package com.bariumhoof.bgfx4j.view;


import org.lwjgl.bgfx.BGFX;

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

    private final int id;
    private final ClearStrategy clearStrategy;

    {
//        bgfx_create_program()
    }

    public static View create(int id, String name, ClearStrategy clearStrategy) {
        return new View(id, name, clearStrategy);
    }

    private View(int id, String name, ClearStrategy clearStrategy) {
        this.id = id;
        this.clearStrategy = clearStrategy;
    }

}
