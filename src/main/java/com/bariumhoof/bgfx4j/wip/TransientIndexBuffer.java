package com.bariumhoof.bgfx4j.wip;

import com.bariumhoof.assertions.Assertions;
import com.bariumhoof.bgfx4j.Disposable;
import com.bariumhoof.bgfx4j.Handle;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.lwjgl.bgfx.BGFXTransientIndexBuffer;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeType;

import java.nio.ByteBuffer;

import static org.lwjgl.bgfx.BGFX.bgfx_alloc_transient_index_buffer;
import static org.lwjgl.bgfx.BGFX.bgfx_destroy_vertex_buffer;

@Slf4j
public final class TransientIndexBuffer implements Disposable, Handle {

    @Getter // todo better solution
    private final @NotNull BGFXTransientIndexBuffer buf;

    private TransientIndexBuffer(@NotNull BGFXTransientIndexBuffer buf) {
        this.buf = buf;
    }

    @NotNull
    static TransientIndexBuffer wrap(@NotNull BGFXTransientIndexBuffer value) {
        return new TransientIndexBuffer(value);
    }

    @NotNull
    public static TransientIndexBuffer allocShorts(int count) {
        final int bytes = count * Short.BYTES;
//        final BGFXTransientIndexBuffer tib = new BGFXTransientIndexBuffer(MemoryUtil.memAlloc(bytes));
        final BGFXTransientIndexBuffer tib = BGFXTransientIndexBuffer.malloc();
        bgfx_alloc_transient_index_buffer(tib, count * Short.BYTES);
        return new TransientIndexBuffer(tib);
    }

    @NotNull
    public static TransientIndexBuffer allocInts(int count) {
        // todo see Cubes example, they multiply by only 2 (since it must become a short?)
        log.warn("check into allocInts byteCount is multiplied by short or integer num bytes (see example from cubes)");
//        final BGFXTransientIndexBuffer tib = new BGFXTransientIndexBuffer(MemoryUtil.memAlloc(count * Integer.BYTES));
        final BGFXTransientIndexBuffer tib = BGFXTransientIndexBuffer.malloc();
        bgfx_alloc_transient_index_buffer(tib, count * Short.BYTES);
        return new TransientIndexBuffer(tib);
    }

    @NotNull
    public static TransientIndexBuffer create(@NotNull int[] indices) {
        Assertions.requirePositive(indices.length);

        final ByteBuffer ibuf = MemoryUtil.memAlloc(getByteCount(indices));
        final BGFXTransientIndexBuffer buf = createTransientIndexBuffer(ibuf, indices);

        return new TransientIndexBuffer(buf);
    }

    @NotNull
    public static TransientIndexBuffer create(@NotNull short[] indices) {
        Assertions.requirePositive(indices.length);

        final ByteBuffer ibuf = MemoryUtil.memAlloc(getByteCount(indices));
        final BGFXTransientIndexBuffer buf = createTransientIndexBuffer(ibuf, indices);

        return new TransientIndexBuffer(buf);
    }

    @NotNull
    public static TransientIndexBuffer create(@NotNull MemoryStack memoryStack) {
        final BGFXTransientIndexBuffer buf = BGFXTransientIndexBuffer.callocStack(memoryStack);
        return new TransientIndexBuffer(buf);
    }

    @NotNull
    public static TransientIndexBuffer createAndInit(@NotNull MemoryStack memoryStack, int num) {
        final BGFXTransientIndexBuffer buf = BGFXTransientIndexBuffer.callocStack(memoryStack);
        bgfx_alloc_transient_index_buffer(buf, num);
        return new TransientIndexBuffer(buf);
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
        bgfx_alloc_transient_index_buffer(tib, indices.length);

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

    @Override
    public void dispose() {
        bgfx_destroy_vertex_buffer(buf.handle());
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
