package com.bariumhoof.bgfx4j.buffer;

import com.bariumhoof.bgfx4j.Disposable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.lwjgl.system.MemoryStack;

import static org.lwjgl.bgfx.BGFX.bgfx_alloc_transient_buffers;

@Slf4j
@EqualsAndHashCode
@ToString
public final class TransientBuffers implements Disposable {

    private TransientBuffers(@NotNull TransientVertexBuffer transientVertexBuffer,
                             @NotNull TransientIndexBuffer transientIndexBuffer) {
        this.transientIndexBuffer = transientIndexBuffer;
        this.transientVertexBuffer = transientVertexBuffer;
    }

    @Getter
    @NotNull
    final TransientIndexBuffer transientIndexBuffer;

    @Getter
    @NotNull
    final TransientVertexBuffer transientVertexBuffer;

    /*
     * @return the alloc'd transient buffers if possible, null otherwise
     */
    @Nullable
    public static TransientBuffers heapAlloc(@NotNull VertexLayoutOld vertexLayoutOld, int numVertices, int numIndices) {
        return allocImpl(vertexLayoutOld, numVertices, numIndices, null);
    }

    @Nullable
    public static TransientBuffers alloc(@NotNull VertexLayoutOld layout, int numVertices, int numIndices, @NotNull MemoryStack memoryStack) {
        return allocImpl(layout, numVertices, numIndices, memoryStack);
    }

    @NotNull
    public static TransientBuffers heapCreate() {
        return createImpl(null);
    }

    @NotNull
    public static TransientBuffers create(@NotNull MemoryStack memoryStack) {
        return createImpl( memoryStack);
    }

    @NotNull
    private static TransientBuffers createImpl(@Nullable MemoryStack memoryStack) {
        if (memoryStack != null) {
            return new TransientBuffers(TransientVertexBuffer.create(memoryStack), TransientIndexBuffer.create(memoryStack));
        } else {
            return new TransientBuffers(TransientVertexBuffer.heapCreate(), TransientIndexBuffer.heapCreate());
        }
    }

    @Nullable
    private static TransientBuffers allocImpl(@NotNull VertexLayoutOld vertexLayoutOld, int numVertices, int numIndices, @Nullable MemoryStack memoryStack) {

        final TransientBuffers buffers;
        if (memoryStack != null) {
            buffers = create(memoryStack);
        } else {
            buffers = heapCreate();
        }

        final boolean success = buffers.alloc(vertexLayoutOld, numVertices, numIndices);

        if (success) {
            return buffers;
        } else {
            return null;
        }
    }

    public boolean alloc(@NotNull VertexLayoutOld vertexLayoutOld, int numVertices, int numIndices) {
        return bgfx_alloc_transient_buffers(transientVertexBuffer.getBuf(), vertexLayoutOld.get(), numVertices, transientIndexBuffer.getBuf(), numIndices);
    }

    /**
     * Frees the data backing the {@link TransientBuffers}. This is only required for {@link TransientBuffers} that are
     * created on heap. Those created on stack (by passing {@link MemoryStack}) are automatically disposed at the end of
     * the try(MemoryStack stack = MemoryStack.stackPush()) { ... } block.
     */
    @Override
    public void dispose() {
        transientVertexBuffer.dispose();
        transientIndexBuffer.dispose();
    }

    public short layoutHandle() {
        return transientVertexBuffer.layoutHandle();
    }
}
