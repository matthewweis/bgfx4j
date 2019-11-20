package com.bariumhoof.bgfx4j.wip;

import com.bariumhoof.assertions.Assertions;
import com.bariumhoof.bgfx4j.Disposable;
import com.bariumhoof.bgfx4j.Handle;
import com.bariumhoof.bgfx4j.enums.BGFX_BUFFER;
import org.jetbrains.annotations.NotNull;
import org.lwjgl.bgfx.BGFXMemory;
import org.lwjgl.system.MemoryUtil;

import java.nio.ByteBuffer;

import static org.lwjgl.bgfx.BGFX.*;

public final class IndexBuffer implements Disposable, Handle {

    private final @NotNull ByteBuffer indicesBuf;
    private final short handle;
    private final int size;

    private IndexBuffer(@NotNull ByteBuffer indicesBuf, short handle, int size) {
        this.indicesBuf = indicesBuf;
        this.handle = handle;
        this.size = size;
    }

    public static IndexBuffer create(@NotNull int[] indices) {
        Assertions.requirePositive(indices.length);

        final ByteBuffer ibuf = MemoryUtil.memAlloc(getByteCount(indices));
        final short handle = createIndexBuffer(ibuf, indices);

        return new IndexBuffer(ibuf, handle, indices.length);
    }

    public static IndexBuffer create(@NotNull short[] indices) {
        Assertions.requirePositive(indices.length);

        final ByteBuffer ibuf = MemoryUtil.memAlloc(getByteCount(indices));
        final short handle = createIndexBuffer(ibuf, indices);

        return new IndexBuffer(ibuf, handle, indices.length);
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

    @Override
    public void dispose() {
//        bgfx_destroy_vertex_decl();
        bgfx_destroy_vertex_buffer(handle);
    }

    @Override
    public short handle() {
        return handle;
    }

    public int size() {
        return size;
    }


}
