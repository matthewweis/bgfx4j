package com.bariumhoof.bgfx4j.buffer;

import com.bariumhoof.assertions.Assertions;
import com.bariumhoof.bgfx4j.Disposable;
import com.bariumhoof.bgfx4j.Handle;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.lwjgl.bgfx.BGFXTransientIndexBuffer;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeType;

import java.nio.ByteBuffer;

import static org.lwjgl.bgfx.BGFX.bgfx_alloc_transient_index_buffer;

@Slf4j
public final class TransientIndexBufferOld implements Disposable, Handle {

    @Getter // todo better solution
    private final @NotNull BGFXTransientIndexBuffer buf;

    private TransientIndexBufferOld(@NotNull BGFXTransientIndexBuffer buf) {
        this.buf = buf;
    }

    // todo expose in advanced api
    @NotNull
    static TransientIndexBufferOld wrap(@NotNull BGFXTransientIndexBuffer value) {
        return new TransientIndexBufferOld(value);
    }

    // todo want this?
    @NotNull
    public static TransientIndexBufferOld heapCreate() {
        return createImpl(null);
    }

    @NotNull
    public static TransientIndexBufferOld create(@NotNull MemoryStack stack) {
        return createImpl(stack);
    }

    @NotNull
    public static TransientIndexBufferOld heapAlloc(int count) {
        return allocImpl(count, null);
    }

    @NotNull
    public static TransientIndexBufferOld alloc(int count, @NotNull MemoryStack stack) {
        return allocImpl(count, stack);
    }

    @NotNull
    private static TransientIndexBufferOld createImpl(@Nullable MemoryStack stack) {
        final BGFXTransientIndexBuffer buf;
        if (stack != null) {
            buf = BGFXTransientIndexBuffer.callocStack(stack);
        } else {
            buf = BGFXTransientIndexBuffer.calloc();
        }
        return new TransientIndexBufferOld(buf);
    }

    @NotNull
    public static TransientIndexBufferOld allocImpl(int count, @Nullable MemoryStack stack) {
        final TransientIndexBufferOld tib = createImpl(stack);
        tib.alloc(count);
        return tib;
    }

    @Deprecated
    @NotNull
    public static TransientIndexBufferOld heapCreate(@NotNull int[] indices) {
        Assertions.requirePositive(indices.length);

        final ByteBuffer ibuf = MemoryUtil.memAlloc(getByteCount(indices));
        final BGFXTransientIndexBuffer buf = createTransientIndexBuffer(ibuf, indices);

        return new TransientIndexBufferOld(buf);
    }

    @Deprecated
    @NotNull
    public static TransientIndexBufferOld heapCreate(@NotNull short[] indices) {
        Assertions.requirePositive(indices.length);

        final ByteBuffer ibuf = MemoryUtil.memAlloc(getByteCount(indices));
        final BGFXTransientIndexBuffer buf = createTransientIndexBuffer(ibuf, indices);

        return new TransientIndexBufferOld(buf);
    }

//    @NotNull
//    public static TransientIndexBufferOld createAndInit(@NotNull MemoryStack memoryStack, int num) {
//        final BGFXTransientIndexBuffer buf = BGFXTransientIndexBuffer.callocStack(memoryStack);
//        bgfx_alloc_transient_index_buffer(buf, num);
//        return new TransientIndexBufferOld(buf);
//    }

    public void alloc(int count) {
        bgfx_alloc_transient_index_buffer(buf, count);
    }

    private static int getByteCount(@NotNull int[] indices) {
//        return indices.length * Integer.BYTES;
        return indices.length * 2; // see Cubes example, they multiply by only 2 (since it must become a short?)
    }

    private static int getByteCount(@NotNull short[] indices) {
        return indices.length * Short.BYTES;
//        return indices.length * 2; // see Cubes example, they multiply by only 2 (for int)s
    }

    /*
     * From lwjgl bgfx tutorial - Cubes
     */
    private static BGFXTransientIndexBuffer createTransientIndexBuffer(ByteBuffer buffer, int[] indices) {
        for (int idx : indices) {
            buffer.putShort((short) idx);
        }

        if (buffer.remaining() != 0) {
            throw new RuntimeException("ByteBuffer size and number of arguments do not match");
        }

        buffer.flip();

        final BGFXTransientIndexBuffer tib = new BGFXTransientIndexBuffer(buffer);

        return tib;
    }

    /*
     * From lwjgl bgfx tutorial - Cubes
     */
    private static BGFXTransientIndexBuffer createTransientIndexBuffer(ByteBuffer buffer, short[] indices) {
        for (short idx : indices) {
            buffer.putShort(idx);
        }

        if (buffer.remaining() != 0) {
            throw new RuntimeException("ByteBuffer size and number of arguments do not match");
        }

        buffer.flip();

        final BGFXTransientIndexBuffer tib = new BGFXTransientIndexBuffer(buffer);
        bgfx_alloc_transient_index_buffer(tib, indices.length);

        return tib;
    }

    public int sizeof() {
        return buf.sizeof();
    }

    @NativeType("uint8_t *")
    public ByteBuffer data() {
        return buf.data();
    }

    /**
     * Frees the data backing this {@link TransientIndexBufferOld}. This is only required for
     * {@link TransientIndexBufferOld}s that are created on heap. Those created on stack (by passing {@link MemoryStack})
     * are automatically disposed at the end of the try(MemoryStack stack = MemoryStack.stackPush()) { ... } block.
     */
    @Override
    public void dispose() {
        buf.free();
    }

    @Override
    public short handle() {
        return buf.handle();
    }

    @NativeType("uint32_t")
    public int size() {
        return buf.size();
    }

    @NativeType("uint32_t")
    public int startIndex() {
        return buf.startIndex();
    }


}
