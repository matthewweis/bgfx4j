package com.bariumhoof.bgfx4j.wip;

import com.bariumhoof.assertions.Assertions;
import com.bariumhoof.bgfx4j.Disposable;
import com.bariumhoof.bgfx4j.Handle;
import org.jetbrains.annotations.NotNull;
import org.lwjgl.bgfx.BGFXMemory;
import org.lwjgl.bgfx.BGFXVertexLayout;
import org.lwjgl.system.MemoryUtil;

import java.nio.ByteBuffer;

import static org.lwjgl.bgfx.BGFX.*;

public class DynamicVertexBuffer implements Disposable, Handle {

    private final @NotNull ByteBuffer verticesBuf;
    private final short handle;

    private DynamicVertexBuffer(@NotNull ByteBuffer verticesBuf, short handle) {
        this.verticesBuf = verticesBuf;
        this.handle = handle;
    }

    public static DynamicVertexBuffer create(@NotNull VertexLayout decl, @NotNull Number[][] vertices) {
        Assertions.requirePositive(vertices.length);
        Assertions.requirePositive(vertices[0].length);

        final ByteBuffer vbuf = MemoryUtil.memAlloc(getByteCount(vertices));
        final short handle = createDynamicVertexBuffer(vbuf, decl.get(), vertices);

        return new DynamicVertexBuffer(vbuf, handle);
    }

    private static int getByteCount(@NotNull Number[][] vertices) {
        final int stride = vertices[0].length;
        final int count = vertices.length;

        final int strideSum = computeTotalBytes(stride, vertices);
        final int strideSum2 = computeTotalBytesAssumingSquare(stride, count, vertices);

        if (strideSum != strideSum2) { // todo use assertions, then remove all together and add test
            System.out.println(strideSum + " vs " + strideSum2);
            throw new IllegalArgumentException(); // todo: remove this temp check!
        }

        return strideSum;
    }

    /*
     * From lwjgl bgfx tutorial - Cubes
     */
    private static short createDynamicVertexBuffer(ByteBuffer buffer, BGFXVertexLayout decl, Number[][] vertices) {
        for (Object[] vtx : vertices) {
            for (Object attr : vtx) {
                if (attr instanceof Float) {
                    buffer.putFloat((float) attr);
                } else if (attr instanceof Integer) {
                    buffer.putInt((int) attr);
                } else {
                    throw new RuntimeException("Invalid parameter type");
                }
            }
        }

        if (buffer.remaining() != 0) {
            throw new RuntimeException("ByteBuffer size and number of arguments do not match");
        }
        buffer.flip();
        return createDynamicVertexBuffer(buffer, decl);
    }

    /*
     * From lwjgl bgfx tutorial - Cubes
     */
    private static short createDynamicVertexBuffer(ByteBuffer buffer, BGFXVertexLayout decl) {
        final BGFXMemory vbhMem = bgfx_make_ref(buffer);
        Assertions.requireNonNull(vbhMem);
        return bgfx_create_dynamic_vertex_buffer_mem(vbhMem, decl, BGFX_BUFFER_NONE);
    }

    // todo keep one strategy
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

    // todo keep one strategy
    private static int computeTotalBytes(int stride, @NotNull Number[][] vertices) {
        int strideSum = 0;
        for (int j=0; j < vertices.length; j++) {
            for (int i=0; i < stride; i++) {
                final Number component = vertices[j][i];
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
        }
        return strideSum;
    }

    @Override
    public void dispose() {
        bgfx_destroy_vertex_buffer(handle);
    }

    @Override
    public short handle() {
        return handle;
    }
}