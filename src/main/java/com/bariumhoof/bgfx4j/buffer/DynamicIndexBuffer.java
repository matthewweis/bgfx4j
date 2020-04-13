package com.bariumhoof.bgfx4j.buffer;

import com.bariumhoof.assertions.Assertions;
import com.bariumhoof.bgfx4j.Disposable;
import com.bariumhoof.bgfx4j.Handle;
import com.bariumhoof.bgfx4j.enums.BGFX_BUFFER;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.lwjgl.bgfx.BGFXMemory;
import org.lwjgl.system.MemoryUtil;

import java.nio.ByteBuffer;
import java.util.Set;

import static org.lwjgl.bgfx.BGFX.*;

/**
 * Every one of these needs:
 *
 * dynamic get:
 *  create()
 *  create(BGFXMemory init)
 *  update(BGFXMemory mem)
 *
 *  static get:
 *    create(BGFXMemory mem)
 *
 *  transient get:
 *    alloc()
 *
 *  except transient buffers
 *
 *  and thats it?
 */
public class DynamicIndexBuffer implements Disposable, Handle {

    private final short handle;

    @Getter
    private final int numIndices;

    private DynamicIndexBuffer(short handle, int numIndices) {
        this.handle = handle;
        this.numIndices = numIndices;
    }

    public static DynamicIndexBuffer create(int numIndices, @NotNull Set<BGFX_BUFFER> flags) {
        Assertions.requirePositive(numIndices);
        final short handle = bgfx_create_dynamic_index_buffer(numIndices, (int) BGFX_BUFFER.flags(flags));
        return new DynamicIndexBuffer(handle, numIndices);
    }

    public static DynamicIndexBuffer createAndInit(@NotNull BGFXMemory init, @NotNull Set<BGFX_BUFFER> flags) {
        final short handle = bgfx_create_dynamic_index_buffer_mem(init, (int) BGFX_BUFFER.flags(flags));
        final int size = init.data().asShortBuffer().capacity(); // todo confirm size
        return new DynamicIndexBuffer(handle, size);
    }

    public static DynamicIndexBuffer create(@NotNull int[] indices) {
        Assertions.requirePositive(indices.length);

        final ByteBuffer ibuf = MemoryUtil.memAlloc(getByteCount(indices));
        final short handle = createIndexBuffer(ibuf, indices);

        return new DynamicIndexBuffer(handle, indices.length);
    }

    public void update(@NotNull BGFXMemory memory) {
        update(0, memory);
    }

    public void update(int startIndex, @NotNull BGFXMemory memory) {
        bgfx_update_dynamic_index_buffer(handle, startIndex, memory);
    }

    private static int getByteCount(@NotNull int[] indices) {
//        return indices.length * Integer.BYTES;
        return indices.length * 2; // see Cubes example, they multiply by only 2
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

        final BGFXMemory ibhMem = bgfx_make_ref(buffer);

        return bgfx_create_dynamic_index_buffer_mem(ibhMem, BGFX_BUFFER.NONE.VALUE);
    }

    @Override
    public void dispose() {
        bgfx_destroy_dynamic_vertex_buffer(handle);
    }

    @Override
    public short handle() {
        return handle;
    }

}