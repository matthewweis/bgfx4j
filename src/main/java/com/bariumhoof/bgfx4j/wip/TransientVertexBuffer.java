package com.bariumhoof.bgfx4j.wip;

import com.bariumhoof.assertions.Assertions;
import com.bariumhoof.bgfx4j.Disposable;
import com.bariumhoof.bgfx4j.Handle;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.lwjgl.bgfx.BGFXTransientVertexBuffer;
import org.lwjgl.bgfx.BGFXVertexLayout;
import org.lwjgl.system.MemoryUtil;

import java.nio.ByteBuffer;

import static org.lwjgl.bgfx.BGFX.bgfx_alloc_transient_vertex_buffer;
import static org.lwjgl.bgfx.BGFX.bgfx_destroy_vertex_buffer;

@Slf4j
public class TransientVertexBuffer implements Disposable, Handle {

    @Getter
    private final @NotNull BGFXTransientVertexBuffer buf;

    private TransientVertexBuffer(@NotNull BGFXTransientVertexBuffer buf) {
        this.buf = buf;
    }

    // todo add creation method that takes only size, no initial integers

    @NotNull
    static TransientVertexBuffer wrap(@NotNull BGFXTransientVertexBuffer value) {
        //todo need alloc?
        return new TransientVertexBuffer(value);
    }

    @NotNull
    public static TransientVertexBuffer allocShorts(int count, @NotNull VertexLayout decl) {
        final BGFXTransientVertexBuffer tvb = new BGFXTransientVertexBuffer(MemoryUtil.memAlloc(count * Short.BYTES));
        bgfx_alloc_transient_vertex_buffer(tvb, count, decl.get());
        return new TransientVertexBuffer(tvb);
    }

    @NotNull
    public static TransientVertexBuffer allocFloats(int count, @NotNull VertexLayout decl) {
        final BGFXTransientVertexBuffer tvb = new BGFXTransientVertexBuffer(MemoryUtil.memAlloc(count * Float.BYTES));
        bgfx_alloc_transient_vertex_buffer(tvb, count, decl.get());
        return new TransientVertexBuffer(tvb);
    }

    @NotNull
    public static TransientVertexBuffer allocInts(int count, @NotNull VertexLayout decl) {
        // todo see Cubes example, they multiply by only 2 (since it must become a short?)
        log.warn("check into allocInts byteCount is multiplied by short or integer num bytes (see example from cubes)");
//        final BGFXTransientVertexBuffer tvb = new BGFXTransientVertexBuffer(MemoryUtil.memAlloc(count * Integer.BYTES));
        final BGFXTransientVertexBuffer tvb = BGFXTransientVertexBuffer.create();
        bgfx_alloc_transient_vertex_buffer(tvb, count, decl.get());
        return new TransientVertexBuffer(tvb);
    }

    @NotNull
    public static TransientVertexBuffer create(@NotNull VertexLayout decl, @NotNull Number[][] vertices) {
        Assertions.requirePositive(vertices.length);
        Assertions.requirePositive(vertices[0].length);

        final ByteBuffer vbuf = MemoryUtil.memAlloc(getByteCount(vertices));
        final BGFXTransientVertexBuffer buf = createTransientVertexBuffer(vbuf, decl.get(), vertices);

        return new TransientVertexBuffer(buf);
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

    private static BGFXTransientVertexBuffer createTransientVertexBuffer(ByteBuffer buffer, BGFXVertexLayout decl, Number[][] vertices) {
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
        return createTransientVertexBuffer(buffer, decl);
    }

    /*
     * Creates a BGFXTransientVertexBuffer which is valid for the duration of the frame and can be reused by multiple
     * draw calls.
     */
    private static BGFXTransientVertexBuffer createTransientVertexBuffer(ByteBuffer buffer, BGFXVertexLayout decl) {
        final BGFXTransientVertexBuffer tvb = new BGFXTransientVertexBuffer(buffer);
        log.warn("using buffer.capacity() in createTransientVertexBuffer to represent numVerts. Check this!");
        final int numVerts = buffer.capacity();
        bgfx_alloc_transient_vertex_buffer(tvb, numVerts, decl);
        return tvb;
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
        bgfx_destroy_vertex_buffer(buf.handle());
    }

    @Override
    public short handle() {
        return buf.handle();
    }

    public int size() {
        return buf.size();
    }
}