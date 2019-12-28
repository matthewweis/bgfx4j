package com.bariumhoof.bgfx4j.wip;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.lwjgl.bgfx.BGFXTransientIndexBuffer;
import org.lwjgl.bgfx.BGFXTransientVertexBuffer;

import java.nio.ByteBuffer;

import static org.lwjgl.bgfx.BGFX.bgfx_alloc_transient_buffers;
import static org.lwjgl.system.MemoryUtil.memAlloc;

@Slf4j
public final class TransientBuffer {

    private TransientBuffer(@NotNull TransientVertexBuffer transientVertexBuffer,
                            @NotNull TransientIndexBuffer transientIndexBuffer,
                            @NotNull ByteBuffer vertexBuffer,
                            @NotNull ByteBuffer indexBuffer,
                            @NotNull VertexLayout layout) {
        this.transientIndexBuffer = transientIndexBuffer;
        this.transientVertexBuffer = transientVertexBuffer;
        this.vertexBuffer = vertexBuffer;
        this.indexBuffer = indexBuffer;
        this.layout = layout;
    }

    @Getter
    @NotNull
    final TransientIndexBuffer transientIndexBuffer;

    @Getter
    @NotNull
    final TransientVertexBuffer transientVertexBuffer;

    @NotNull
    private final ByteBuffer vertexBuffer;

    @NotNull
    private final ByteBuffer indexBuffer;

    @NotNull
    private final VertexLayout layout;

    /**
     *
     * @param layout
     * @param numVertices
     * @param numIndices
     * @param areVerticesInts if true then use int, otherwise use short. todo replace with builder
     * @param areIndicesInts if true then use int, otherwise use short. todo replace with builder
     * @return
     */
    @Nullable
    public static TransientBuffer allocTransientBuffers(@NotNull VertexLayout layout, int numVertices, int numIndices, boolean areVerticesInts, boolean areIndicesInts) {
        if (areVerticesInts) { // todo remove me
            log.warn("using integer.bytes but unsure about conversion");
        }
        final int v_stride = areVerticesInts ? Integer.BYTES : Short.BYTES;
        final int i_stride = areIndicesInts ? Integer.BYTES : Short.BYTES;

        final ByteBuffer vertexBuf = memAlloc(numVertices * v_stride);
        final ByteBuffer indexBuf = memAlloc(numIndices * i_stride);

        BGFXTransientIndexBuffer tib = new BGFXTransientIndexBuffer(indexBuf);
        BGFXTransientVertexBuffer tvb = new BGFXTransientVertexBuffer(vertexBuf);

        boolean success = bgfx_alloc_transient_buffers(tvb, layout.get(), numVertices, tib, numIndices);

        if (success) {
            return new TransientBuffer(TransientVertexBuffer.wrap(tvb), TransientIndexBuffer.wrap(tib), vertexBuf, indexBuf, layout);
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

    /**
     * Attempts to reuse the transientBuffers declared in a previous frame to avoid reallocation and garbage collections.
     * @return a boolean indicating whether or not this could be successfully allocated for the frame
     */
    public boolean reuse() {
        BGFXTransientIndexBuffer tib = new BGFXTransientIndexBuffer(indexBuffer);
        BGFXTransientVertexBuffer tvb = new BGFXTransientVertexBuffer(vertexBuffer);

        return bgfx_alloc_transient_buffers(tvb, layout.get(), tvb.size(), tib, tib.size());
    }

}
