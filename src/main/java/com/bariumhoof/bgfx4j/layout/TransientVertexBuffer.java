package com.bariumhoof.bgfx4j.layout;

import com.bariumhoof.bgfx4j.Disposable;
import com.bariumhoof.bgfx4j.Handle;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.lwjgl.bgfx.BGFXTransientVertexBuffer;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.NativeType;

import java.nio.ByteBuffer;

import static org.lwjgl.bgfx.BGFX.bgfx_alloc_transient_vertex_buffer;

// todo look into (in)efficiencies of mem system, copy vs ref, and putting things on the stack
public class TransientVertexBuffer<V extends Vertex> implements Disposable, Handle {

    // todo can have "set" by calling for normal VB? see hdr example

    // todo this always exists regardless of instantiaion, but layout does not. (Nor does size)
    //      make this information part of a builder surplus stage which is also extended by this class?
    @Getter
    @NotNull
    private final BGFXTransientVertexBuffer buf;

//    @Getter
//    @NotNull
//    final VertexLayout<V> vertexLayout;

    private TransientVertexBuffer(@NotNull BGFXTransientVertexBuffer buf) {
//        this.vertexLayout = vertexLayout;
        this.buf = buf;
    }

    // todo add creation method that takes only size, no initial integers

    @NotNull
    public static <V extends Vertex> TransientVertexBuffer<V> heapCreate() {
        return createImpl(null);
    }

    @NotNull
    public static <V extends Vertex> TransientVertexBuffer<V> create(@NotNull MemoryStack stack) {
        return createImpl(stack);
    }

    @NotNull
    public static <V extends Vertex> TransientVertexBuffer<V> heapAlloc(int vertexCount, @NotNull VertexLayout<V> vertexLayout) {
        return allocImpl(vertexLayout, vertexCount, null);
    }

    @NotNull
    public static <V extends Vertex> TransientVertexBuffer<V> alloc(@NotNull VertexLayout<V> vertexLayout, int vertexCount, @NotNull MemoryStack stack) {
        return allocImpl(vertexLayout, vertexCount, stack);
    }

    @NotNull
    private static <V extends Vertex> TransientVertexBuffer<V> allocImpl(@NotNull VertexLayout<V> vertexLayout, int vertexCount, @Nullable MemoryStack memoryStack) {
        final TransientVertexBuffer<V> tvb = createImpl(memoryStack);
        tvb.alloc(vertexLayout, vertexCount);
        return tvb;
    }

    // todo while faster to create in batch, this can be a feature for advanced api users
    // todo make use "allocImpl" and "createImpl" for plural (add later)
//    @NotNull
//    public static TransientVertexBuffer<V>[] create(int creationCount) {
//        final BGFXTransientVertexBuffer.Buffer buffers = BGFXTransientVertexBuffer.calloc(creationCount);
//        final TransientVertexBuffer<V>[] result = new TransientVertexBuffer<V>[creationCount];
//        for (int i=0; i < creationCount; i++) {
//            result[i] = new TransientVertexBuffer<V>(buffers.get(i));
//        }
//        return result;
//    }
//
//    @NotNull
//    public static TransientVertexBuffer<V>[] create(@NotNull MemoryStack stack, int creationCount) {
//        final BGFXTransientVertexBuffer.Buffer buffers = BGFXTransientVertexBuffer.callocStack(creationCount, stack);
//        final TransientVertexBuffer<V>[] result = new TransientVertexBuffer<V>[creationCount];
//        for (int i=0; i < creationCount; i++) {
//            result[i] = new TransientVertexBuffer<V>(buffers.get(i));
//        }
//        return result;
//    }
//
//    @NotNull
//    public static TransientVertexBuffer<V>[] createAndAlloc(int vertexCount, @NotNull VertexLayout<V> layout, int creationCount) {
//        final BGFXTransientVertexBuffer.Buffer buffers = BGFXTransientVertexBuffer.calloc(creationCount);
//        final TransientVertexBuffer<V>[] result = new TransientVertexBuffer<V>[creationCount];
//        for (int i=0; i < creationCount; i++) {
//            final BGFXTransientVertexBuffer tvb = buffers.get(i);
//            bgfx_alloc_transient_vertex_buffer(tvb, vertexCount, layout.get());
//            result[i] = new TransientVertexBuffer<V>(tvb);
//        }
//        return result;
//    }
//
//    @NotNull
//    public static TransientVertexBuffer<V>[] createAndAlloc(@NotNull MemoryStack stack, int vertexCount, @NotNull VertexLayout<V> layout, int creationCount) {
//        final BGFXTransientVertexBuffer.Buffer buffers = BGFXTransientVertexBuffer.callocStack(creationCount, stack);
//        final TransientVertexBuffer<V>[] result = new TransientVertexBuffer<V>[creationCount];
//        for (int i=0; i < creationCount; i++) {
//            final BGFXTransientVertexBuffer tvb = buffers.get(i);
//            bgfx_alloc_transient_vertex_buffer(tvb, vertexCount, layout.get());
//            result[i] = new TransientVertexBuffer<V>(tvb);
//        }
//        return result;
//    }

    public void alloc(@NotNull VertexLayout<V> vertexLayout, int vertexCount) {
        bgfx_alloc_transient_vertex_buffer(buf, vertexCount, vertexLayout.get());
    }

    private static <V extends Vertex> TransientVertexBuffer<V> createImpl(@Nullable MemoryStack stack) {
        final BGFXTransientVertexBuffer tvb;
        if (stack != null) {
            tvb = BGFXTransientVertexBuffer.callocStack(stack);
        } else {
            tvb = BGFXTransientVertexBuffer.calloc();
        }
        return new TransientVertexBuffer<V>(tvb);
    }

    // todo expose in advanced api
    @NotNull
    static <V extends Vertex> TransientVertexBuffer<V> wrap(@NotNull BGFXTransientVertexBuffer value) {
        return new TransientVertexBuffer<V>(value);
    }

//    // todo seperate between methods like this which create and init a buffer and those which create an uninitialized buffer.
//    //      this should eventually be type safe (and an uninitialized buffer is called like a blueprint or something)
//    @Deprecated
//    @NotNull
//    public static TransientVertexBuffer<V> heapCreate(@NotNull Number[][] vertices) {
//        Assertions.requirePositive(vertices.length);
//        Assertions.requirePositive(vertices[0].length);
//
//        final ByteBuffer vbuf = MemoryUtil.memAlloc(getByteCount(vertices));
//        final BGFXTransientVertexBuffer buf = createTransientVertexBuffer<V>(vbuf, vertices);
//
//        return new TransientVertexBuffer<V>(buf);
//    }

//    private static int getByteCount(@NotNull Number[][] vertices) {
//        final int stride = vertices[0].length;
//        final int count = vertices.length;
//
//        final int strideSum = computeTotalBytes(stride, vertices);
//        final int strideSum2 = computeTotalBytesAssumingSquare(stride, count, vertices);
//
//        if (strideSum != strideSum2) { // todo use assertions, then remove all together and add test
//            System.out.println(strideSum + " vs " + strideSum2);
//            throw new IllegalArgumentException(); // todo: remove this temp check!
//        }
//
//        return strideSum;
//    }
//
//    private static BGFXTransientVertexBuffer createTransientVertexBuffer<V>(ByteBuffer buffer, Number[][] vertices) {
//        for (Object[] vtx : vertices) {
//            for (Object attr : vtx) {
//                if (attr instanceof Float) {
//                    buffer.putFloat((float) attr);
//                } else if (attr instanceof Integer) {
//                    buffer.putInt((int) attr);
//                } else {
//                    throw new RuntimeException("Invalid parameter type");
//                }
//            }
//        }
//
//        if (buffer.remaining() != 0) {
//            throw new RuntimeException("ByteBuffer size and number of arguments do not match");
//        }
//        buffer.flip();
//        return createTransientVertexBuffer<V>(buffer, vertices.length);
//    }
//
//    /*
//     * Creates a BGFXTransientVertexBuffer which is valid for the duration of the frame and can be reused by multiple
//     * draw calls.
//     */
//    private static BGFXTransientVertexBuffer createTransientVertexBuffer<V>(ByteBuffer buffer, int numVerts) {
//        final BGFXTransientVertexBuffer tvb = new BGFXTransientVertexBuffer(buffer);
//        BGFXTransientVertexBuffer.
//        return tvb;
//    }
//
//    // todo keep one strategy
//    private static int computeTotalBytesAssumingSquare(int stride, int count, @NotNull Number[][] vertices) {
//        int strideSum = 0;
//        for (int i = 0; i < stride; i++) {
//            final Number component = vertices[0][i];
//            if (component instanceof Byte) {
//                strideSum += Byte.BYTES;
//            } else if (component instanceof Short) {
//                strideSum += Short.BYTES;
//            } else if (component instanceof Integer) {
//                strideSum += Integer.BYTES;
//            } else if (component instanceof Long) {
//                strideSum += Long.BYTES;
//            } else if (component instanceof Float) {
//                strideSum += Float.BYTES;
//            } else if (component instanceof Double) {
//                strideSum += Double.BYTES;
//            } else {
//                throw new IllegalStateException();
//            }
//        }
//        return strideSum * count;
//    }
//
//    // todo keep one strategy
//    private static int computeTotalBytes(int stride, @NotNull Number[][] vertices) {
//        int strideSum = 0;
//        for (int j=0; j < vertices.length; j++) {
//            for (int i=0; i < stride; i++) {
//                final Number component = vertices[j][i];
//                if (component instanceof Byte) {
//                    strideSum += Byte.BYTES;
//                } else if (component instanceof Short) {
//                    strideSum += Short.BYTES;
//                } else if (component instanceof Integer) {
//                    strideSum += Integer.BYTES;
//                } else if (component instanceof Long) {
//                    strideSum += Long.BYTES;
//                } else if (component instanceof Float) {
//                    strideSum += Float.BYTES;
//                } else if (component instanceof Double) {
//                    strideSum += Double.BYTES;
//                } else {
//                    throw new IllegalStateException();
//                }
//            }
//        }
//        return strideSum;
//    }

    public int sizeof() {
        return buf.sizeof();
    }

    @NativeType("uint8_t *")
    public ByteBuffer data() {
        return buf.data();
    }

    /**
     * Frees the data backing this {@link TransientVertexBuffer<V>}. This is only required for
     * {@link TransientVertexBuffer<V>}s that are created on heap. Those created on stack (by passing {@link MemoryStack})
     * are automatically disposed at the end of the try(MemoryStack stack = MemoryStack.stackPush()) { ... } block.
     */
    @Override
    public void dispose() {
        buf.free();
    }

    @NativeType("bgfx_vertex_buffer_handle_t")
    @Override
    public short handle() {
        return buf.handle();
    }

    @NativeType("bgfx_vertex_layout_handle_t")
    public short layoutHandle() {
        return buf.layoutHandle();
    }

    @NativeType("uint32_t")
    public int size() {
        return buf.size();
    }

    @NativeType("uint32_t")
    public int startVertex() {
        return buf.startVertex();
    }

    @NativeType("uint16_t")
    public short stride() {
        return buf.stride();
    }

}