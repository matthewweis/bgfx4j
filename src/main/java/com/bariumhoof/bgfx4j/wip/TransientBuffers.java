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

//    public static boolean alloc(@NotNull TransientVertexBuffer transientVertexBuffer, @NotNull TransientIndexBuffer transientIndexBuffer) {
//
//    }

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

    private static int computeTotalBytesAssumingSquare(int stride, int count, @NotNull Number[][] vertices) {
        int strideSum = 0;
        for (int i = 0; i < stride; i++) {
            final Number component = vertices[0][i];
            if (component instanceof Byte) {
                strideSum += Byte.BYTES;
            } else if (component instanceof Short) {
                strideSum += Short.BYTES;
            } else if (component instanceof Integer) {
                strideSum += Integer.BYTES;
            } else if (component instanceof Long) {
                strideSum += Long.BYTES;
            } else if (component instanceof Float) {
                strideSum += Float.BYTES;
            } else if (component instanceof Double) {
                strideSum += Double.BYTES;
            } else {
                throw new IllegalStateException();
            }
        }
        return strideSum * count;
    }
//
//    /**
//     * Attempts to reuse the transientBuffers declared in a previous frame to avoid reallocation and garbage collections.
//     * @return a boolean indicating whether or not this could be successfully allocated for the frame
//     */
//    public boolean reuse() {
//        BGFXTransientIndexBuffer tib = new BGFXTransientIndexBuffer(indexBuffer);
//        BGFXTransientVertexBuffer tvb = new BGFXTransientVertexBuffer(vertexBuffer);
//
//        return bgfx_alloc_transient_buffers(tvb, layout.get(), tvb.size(), tib, tib.size());
//    }

}
