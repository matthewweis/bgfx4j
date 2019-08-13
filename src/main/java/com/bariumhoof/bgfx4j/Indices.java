package com.bariumhoof.bgfx4j;

import com.bariumhoof.assertions.Assertions;
import com.bariumhoof.bgfx4j.enums.BGFX_BUFFER;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.lwjgl.bgfx.BGFXMemory;
import org.lwjgl.system.MemoryUtil;

import java.nio.ByteBuffer;

import static org.lwjgl.bgfx.BGFX.*;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public final class Indices implements Disposable, Handle {

    private final @NotNull ByteBuffer indicesBuf;
    private final short handle;

    public static Indices create(@NotNull int[] indices) {
        Assertions.requirePositive(indices.length);

        final ByteBuffer ibuf = MemoryUtil.memAlloc(getByteCount(indices));
        final short handle = createIndexBuffer(ibuf, indices);

        return new Indices(ibuf, handle);
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

        BGFXMemory ibhMem = bgfx_make_ref(buffer);

        return bgfx_create_index_buffer(ibhMem, BGFX_BUFFER.NONE.VALUE);
    }

    private static int computeTotalBytesAssumingSquare(int stride, @NotNull Number[][] vertices) {
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
        return strideSum;
    }

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
//        bgfx_destroy_vertex_decl();
        bgfx_destroy_vertex_buffer(handle);
    }

    @Override
    public short handle() {
        return handle;
    }


}
