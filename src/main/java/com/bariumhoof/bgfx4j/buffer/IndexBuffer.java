package com.bariumhoof.bgfx4j.buffer;

import com.bariumhoof.assertions.Assertions;
import com.bariumhoof.bgfx4j.Disposable;
import com.bariumhoof.bgfx4j.Handle;
import com.bariumhoof.bgfx4j.enums.BGFX_BUFFER;
import org.jetbrains.annotations.NotNull;
import org.lwjgl.bgfx.BGFXMemory;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;

import java.nio.ByteBuffer;
import java.util.Set;

import static org.lwjgl.bgfx.BGFX.*;

public final class IndexBuffer implements Disposable, Handle {

    // exists to prevent GC of indicesBuf becuase bgfx_wrap is used... todo needed?
    private final short handle;
    private final int numIndices; // in number of indices

    private IndexBuffer(short handle, int numIndices) {
        this.handle = handle;
        this.numIndices = numIndices;
    }

    public static IndexBuffer create(@NotNull BGFXMemory mem, @NotNull Set<BGFX_BUFFER> flags) {
        final int size = mem.data().capacity() / Short.BYTES;
        final short handle = bgfx_create_index_buffer(mem, (int) BGFX_BUFFER.flags(flags));
        // todo double check size works
        return new IndexBuffer(handle, size);
    }

    /**
     * remove due to constraint on indices?
     * @param indices must not be cleared for at least two frames (see createIndexBuffer(indices))
     * @param count
     * @return
     */
    public static IndexBuffer create(@NotNull ByteBuffer indices, int count) {
        Assertions.requirePositive(count);
        final short handle = createIndexBuffer(indices);
        return new IndexBuffer(handle, count);
    }

    public static IndexBuffer create(@NotNull int[] indices) {
        Assertions.requirePositive(indices.length);

        final ByteBuffer ibuf = MemoryUtil.memAlloc(getByteCount(indices));
        final short handle = createIndexBuffer(ibuf, indices);

        return new IndexBuffer(handle, indices.length);
    }

    public static IndexBuffer createStack(@NotNull MemoryStack stack, @NotNull int[] indices) {
        Assertions.requirePositive(indices.length);

        final ByteBuffer ibuf = stack.calloc(getByteCount(indices));
        final short handle = createIndexBuffer(ibuf, indices);

        return new IndexBuffer(handle, indices.length);
    }

    public static IndexBuffer create(@NotNull short[] indices) {
        Assertions.requirePositive(indices.length);

        final ByteBuffer ibuf = MemoryUtil.memAlloc(getByteCount(indices));
        final short handle = createIndexBuffer(ibuf, indices);

        return new IndexBuffer(handle, indices.length);
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
    private static short createIndexBuffer(ByteBuffer buffer, int[] indices) {
        for (int idx : indices) {
            buffer.putShort((short) idx);
        }

        if (buffer.remaining() != 0) {
            throw new RuntimeException("ByteBuffer size and number of arguments do not match");
        }

        buffer.flip();

        BGFXMemory ibhMem = bgfx_make_ref(buffer);

        return bgfx_create_index_buffer(ibhMem, BGFX_BUFFER.NONE.VALUE);
    }

    /*
     * From lwjgl bgfx tutorial - Cubes
     */
    private static short createIndexBuffer(ByteBuffer buffer, short[] indices) {
        for (short idx : indices) {
            buffer.putShort(idx);
        }

        if (buffer.remaining() != 0) {
            throw new RuntimeException("ByteBuffer size and number of arguments do not match");
        }

        buffer.flip();

        BGFXMemory ibhMem = bgfx_make_ref(buffer);

        return bgfx_create_index_buffer(ibhMem, BGFX_BUFFER.NONE.VALUE);
    }

    private static short createIndexBuffer(ByteBuffer indices) {
        // todo MUST be alive for at least two frame calls
        final BGFXMemory ibhMem = bgfx_make_ref(indices); // todo does this need be disposed using bgfx_make_ref_release
        Assertions.requireNonNull(ibhMem);
        return bgfx_create_index_buffer(ibhMem, BGFX_BUFFER.NONE.VALUE);
    }

    @Override
    public void dispose() {
        bgfx_destroy_vertex_buffer(handle);
    }

    @Override
    public short handle() {
        return handle;
    }

    public int size() {
        return numIndices;
    }


}
