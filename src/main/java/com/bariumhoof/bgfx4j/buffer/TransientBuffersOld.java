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
public final class TransientBuffersOld implements Disposable {

    private TransientBuffersOld(@NotNull TransientVertexBufferOld transientVertexBufferOld,
                                @NotNull TransientIndexBufferOld transientIndexBufferOld) {
        this.transientIndexBufferOld = transientIndexBufferOld;
        this.transientVertexBufferOld = transientVertexBufferOld;
    }

    @Getter
    @NotNull
    final TransientIndexBufferOld transientIndexBufferOld;

    @Getter
    @NotNull
    final TransientVertexBufferOld transientVertexBufferOld;

    /*
     * @return the alloc'd transient buffers if possible, null otherwise
     */
    @Nullable
    public static TransientBuffersOld heapAlloc(@NotNull VertexLayoutOld vertexLayoutOld, int numVertices, int numIndices) {
        return allocImpl(vertexLayoutOld, numVertices, numIndices, null);
    }

    @Nullable
    public static TransientBuffersOld alloc(@NotNull VertexLayoutOld layout, int numVertices, int numIndices, @NotNull MemoryStack memoryStack) {
        return allocImpl(layout, numVertices, numIndices, memoryStack);
    }

    @NotNull
    public static TransientBuffersOld heapCreate() {
        return createImpl(null);
    }

    @NotNull
    public static TransientBuffersOld create(@NotNull MemoryStack memoryStack) {
        return createImpl( memoryStack);
    }

    @NotNull
    private static TransientBuffersOld createImpl(@Nullable MemoryStack memoryStack) {
        if (memoryStack != null) {
            return new TransientBuffersOld(TransientVertexBufferOld.create(memoryStack), TransientIndexBufferOld.create(memoryStack));
        } else {
            return new TransientBuffersOld(TransientVertexBufferOld.heapCreate(), TransientIndexBufferOld.heapCreate());
        }
    }

    @Nullable
    private static TransientBuffersOld allocImpl(@NotNull VertexLayoutOld vertexLayoutOld, int numVertices, int numIndices, @Nullable MemoryStack memoryStack) {

        final TransientBuffersOld buffers;
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
        return bgfx_alloc_transient_buffers(transientVertexBufferOld.getBuf(), vertexLayoutOld.get(), numVertices, transientIndexBufferOld.getBuf(), numIndices);
    }

    /**
     * Frees the data backing the {@link TransientBuffersOld}. This is only required for {@link TransientBuffersOld} that are
     * created on heap. Those created on stack (by passing {@link MemoryStack}) are automatically disposed at the end of
     * the try(MemoryStack stack = MemoryStack.stackPush()) { ... } block.
     */
    @Override
    public void dispose() {
        transientVertexBufferOld.dispose();
        transientIndexBufferOld.dispose();
    }

    public short layoutHandle() {
        return transientVertexBufferOld.layoutHandle();
    }
}
