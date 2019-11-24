package com.bariumhoof.bgfx4j.wip;

import com.bariumhoof.assertions.Assertions;
import com.bariumhoof.bgfx4j.Disposable;
import com.bariumhoof.bgfx4j.Handle;
import org.jetbrains.annotations.NotNull;
import org.lwjgl.bgfx.BGFXMemory;
import org.lwjgl.bgfx.BGFXVertexDecl;
import org.lwjgl.system.MemoryUtil;

import java.nio.ByteBuffer;

import static org.lwjgl.bgfx.BGFX.*;

public final class VertexBuffer implements Disposable, Handle {

    private final @NotNull ByteBuffer verticesBuf;
    private final short handle;

    private final int size; // size only of 1st array since second doesn't matter to bgfx

    private VertexBuffer(@NotNull ByteBuffer verticesBuf, short handle, int size) {
        this.verticesBuf = verticesBuf;
        this.handle = handle;
        this.size = size;
    }

    public static VertexBuffer create(@NotNull VertexDecl decl, @NotNull int[][] vertices) {
        Assertions.requirePositive(vertices.length);
        Assertions.requirePositive(vertices[0].length);

        final ByteBuffer vbuf = MemoryUtil.memAlloc(getByteCount(vertices));
        final short handle = createVertexBuffer(vbuf, decl.get(), vertices);

        final int size = vertices.length;

        return new VertexBuffer(vbuf, handle, size);
    }

    private static int getByteCount(@NotNull int[][] vertices) {
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
    private static short createVertexBuffer(ByteBuffer buffer, BGFXVertexDecl decl, int[][] vertices) {
        for (int[] vtx : vertices) {
            for (int attr : vtx) {
                buffer.putInt(attr);
            }
        }

        if (buffer.remaining() != 0) {
            throw new RuntimeException("ByteBuffer size and number of arguments do not match");
        }
        buffer.flip();
        return createVertexBuffer(buffer, decl);
    }

    /*
     * From lwjgl bgfx tutorial - Cubes
     */
    private static short createVertexBuffer(ByteBuffer buffer, BGFXVertexDecl decl) {
        final BGFXMemory vbhMem = bgfx_make_ref(buffer);
        Assertions.requireNonNull(vbhMem);
        return bgfx_create_vertex_buffer(vbhMem, decl, BGFX_BUFFER_NONE);
    }

    // todo keep one strategy
    private static int computeTotalBytesAssumingSquare(int stride, int count, @NotNull int[][] vertices) {
        int strideSum = 0;
        for (int i = 0; i < stride; i++) {
            final int component = vertices[0][i];
            strideSum += Integer.BYTES;
        }
        return strideSum * count;
    }

    // todo keep one strategy
    private static int computeTotalBytes(int stride, @NotNull int[][] vertices) {
        int strideSum = 0;
        for (int j=0; j < vertices.length; j++) {
            for (int i=0; i < stride; i++) {
                final int component = vertices[j][i];
                strideSum += Integer.BYTES;
            }
        }
        return strideSum;
    }

    public static VertexBuffer create(@NotNull VertexDecl decl, @NotNull float[][] vertices) {
        Assertions.requirePositive(vertices.length);
        Assertions.requirePositive(vertices[0].length);

        final ByteBuffer vbuf = MemoryUtil.memAlloc(getByteCount(vertices));
        final short handle = createVertexBuffer(vbuf, decl.get(), vertices);

        final int size = vertices.length;

        return new VertexBuffer(vbuf, handle, size);
    }

    private static int getByteCount(@NotNull float[][] vertices) {
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
    private static short createVertexBuffer(ByteBuffer buffer, BGFXVertexDecl decl, float[][] vertices) {
        for (float[] vtx : vertices) {
            for (float attr : vtx) {
                buffer.putFloat(attr);
            }
        }

        if (buffer.remaining() != 0) {
            throw new RuntimeException("ByteBuffer size and number of arguments do not match");
        }
        buffer.flip();
        return createVertexBuffer(buffer, decl);
    }

    // todo keep one strategy
    private static int computeTotalBytesAssumingSquare(int stride, int count, @NotNull float[][] vertices) {
        int strideSum = 0;
        for (int i = 0; i < stride; i++) {
            final float component = vertices[0][i];
            strideSum += Integer.BYTES;
        }
        return strideSum * count;
    }

    // todo keep one strategy
    private static int computeTotalBytes(int stride, @NotNull float[][] vertices) {
        int strideSum = 0;
        for (int j=0; j < vertices.length; j++) {
            for (int i=0; i < stride; i++) {
                final float component = vertices[j][i];
                strideSum += Integer.BYTES;
            }
        }
        return strideSum;
    }

    // todo replace Number[][] with a version per primitive!
    public static VertexBuffer create(@NotNull VertexDecl decl, @NotNull Number[][] vertices) {
        Assertions.requirePositive(vertices.length);
        Assertions.requirePositive(vertices[0].length);

        final ByteBuffer vbuf = MemoryUtil.memAlloc(getByteCount(vertices));
        final short handle = createVertexBuffer(vbuf, decl.get(), vertices);

        final int size = vertices.length;

        return new VertexBuffer(vbuf, handle, size);
    }

    // todo replace Number[][] with a version per primitive!
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
    // todo replace Number[][] with a verion per primitive!!! *unless can be either?*
    private static short createVertexBuffer(ByteBuffer buffer, BGFXVertexDecl decl, Number[][] vertices) {
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
        return createVertexBuffer(buffer, decl);
    }

//    /*
//     * From lwjgl bgfx tutorial - Cubes
//     */
//    private static short createVertexBuffer(ByteBuffer buffer, BGFXVertexDecl decl) {
//        final BGFXMemory vbhMem = bgfx_make_ref(buffer);
//        Assertions.requireNonNull(vbhMem);
//        return bgfx_create_vertex_buffer(vbhMem, decl, BGFX_BUFFER_NONE);
//    }

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

    public int size() { return size; }

}
