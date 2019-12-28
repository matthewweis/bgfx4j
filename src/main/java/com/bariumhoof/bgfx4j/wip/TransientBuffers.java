package com.bariumhoof.bgfx4j.wip;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.lwjgl.bgfx.BGFXTransientIndexBuffer;
import org.lwjgl.bgfx.BGFXTransientVertexBuffer;
import org.lwjgl.system.MemoryStack;

import static org.lwjgl.bgfx.BGFX.bgfx_alloc_transient_buffers;

@Slf4j
@EqualsAndHashCode
@ToString
public final class TransientBuffers {

    private TransientBuffers(@NotNull TransientVertexBuffer transientVertexBuffer,
                             @NotNull TransientIndexBuffer transientIndexBuffer,
                             @NotNull VertexLayout layout) {
        this.transientIndexBuffer = transientIndexBuffer;
        this.transientVertexBuffer = transientVertexBuffer;
        this.layout = layout;
    }

    @Getter
    @NotNull
    final TransientIndexBuffer transientIndexBuffer;

    @Getter
    @NotNull
    final TransientVertexBuffer transientVertexBuffer;

    @Getter
    @NotNull
    private final VertexLayout layout;

    /**
     *
     * @param layout
     * @param numVertices
     * @param numIndices
     * @return the alloced transient buffers if possible, null otherwise
     */
    @Nullable
    public static TransientBuffers allocTransientBuffers(@NotNull MemoryStack memoryStack, @NotNull VertexLayout layout, int numVertices, int numIndices) {
        final BGFXTransientIndexBuffer tib = BGFXTransientIndexBuffer.callocStack(memoryStack);
        final BGFXTransientVertexBuffer tvb = BGFXTransientVertexBuffer.callocStack(memoryStack);

        boolean success = bgfx_alloc_transient_buffers(tvb, layout.get(), numVertices, tib, numIndices);

        if (success) {
            return new TransientBuffers(TransientVertexBuffer.wrap(tvb), TransientIndexBuffer.wrap(tib), layout);
        }

        return null;
    }

}
