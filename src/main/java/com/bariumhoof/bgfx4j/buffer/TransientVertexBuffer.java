package com.bariumhoof.bgfx4j.buffer;

import com.bariumhoof.bgfx4j.Disposable;
import com.bariumhoof.bgfx4j.Handle;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.lwjgl.bgfx.BGFXTransientVertexBuffer;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.NativeType;

import java.nio.ByteBuffer;

import static org.lwjgl.bgfx.BGFX.bgfx_alloc_transient_vertex_buffer;

/*
 *  TransientVertexBuffer and TransientIndexBuffer have the following ways to create:
 *
 *  static createSlow() // want to give a non mem stack option??
 *  static create(MemoryStack stack)
 *  static createAndAlloc(MemoryStack stack, int count, Layout layout) // vertex
 *  static createAndAlloc(MemoryStack stack, int count) // index
 *  alloc(int count) // index
 *  alloc(int count, layout) // vertex layout
 *
 *  // then wrap() methods to wrap existing data
 *
 *  // then helper methods to modify data?
 *
 *  // then add modify data helper methods to DynamicVertexData
 *
 *  BgfxInstanceData also must follow this pattern.
 *
 *  DynamicVertex/Index and regular Vertex/Index do NOT need allocate helpers, but should have:
 *
 *  non-dynamic should have create methods that accept data as byteBuffer. Maybe allow on memory stack?
 *  dynamic should have createMethods both with and without data. Maybe allow on memory stack?
 *
 */

@Slf4j
public class TransientVertexBuffer implements Disposable, Handle {

    // todo this always exists regardless of instantiaion, but layout does not. (Nor does size)
    //      make this information part of a builder surplus stage which is also extended by this class?
    @Getter
    @NotNull
    private final BGFXTransientVertexBuffer buf;

//    @Getter
//    @NotNull
//    final VertexLayout vertexLayout;

    private TransientVertexBuffer(@NotNull BGFXTransientVertexBuffer buf) {
//        this.vertexLayout = vertexLayout;
        this.buf = buf;
    }

    // todo add creation method that takes only size, no initial integers

    @NotNull
    public static TransientVertexBuffer heapCreate() {
        return createImpl(null);
    }

    @NotNull
    public static TransientVertexBuffer create(@NotNull MemoryStack stack) {
        return createImpl(stack);
    }

    @NotNull
    public static TransientVertexBuffer heapAlloc(int vertexCount, @NotNull VertexLayout vertexLayout) {
        return allocImpl(vertexLayout, vertexCount, null);
    }

    @NotNull
    public static TransientVertexBuffer alloc(@NotNull VertexLayout vertexLayout, int vertexCount, @NotNull MemoryStack stack) {
        return allocImpl(vertexLayout, vertexCount, stack);
    }

    @NotNull
    private static TransientVertexBuffer allocImpl(@NotNull VertexLayout vertexLayout, int vertexCount, @Nullable MemoryStack memoryStack) {
        final TransientVertexBuffer tvb = createImpl(memoryStack);
        tvb.alloc(vertexLayout, vertexCount);
        return tvb;
    }

    // todo while faster to create in batch, this can be a feature for advanced api users
    // todo make use "allocImpl" and "createImpl" for plural (add later)
//    @NotNull
//    public static TransientVertexBuffer[] create(int creationCount) {
//        final BGFXTransientVertexBuffer.Buffer buffers = BGFXTransientVertexBuffer.calloc(creationCount);
//        final TransientVertexBuffer[] result = new TransientVertexBuffer[creationCount];
//        for (int i=0; i < creationCount; i++) {
//            result[i] = new TransientVertexBuffer(buffers.get(i));
//        }
//        return result;
//    }
//
//    @NotNull
//    public static TransientVertexBuffer[] create(@NotNull MemoryStack stack, int creationCount) {
//        final BGFXTransientVertexBuffer.Buffer buffers = BGFXTransientVertexBuffer.callocStack(creationCount, stack);
//        final TransientVertexBuffer[] result = new TransientVertexBuffer[creationCount];
//        for (int i=0; i < creationCount; i++) {
//            result[i] = new TransientVertexBuffer(buffers.get(i));
//        }
//        return result;
//    }
//
//    @NotNull
//    public static TransientVertexBuffer[] createAndAlloc(int vertexCount, @NotNull VertexLayout layout, int creationCount) {
//        final BGFXTransientVertexBuffer.Buffer buffers = BGFXTransientVertexBuffer.calloc(creationCount);
//        final TransientVertexBuffer[] result = new TransientVertexBuffer[creationCount];
//        for (int i=0; i < creationCount; i++) {
//            final BGFXTransientVertexBuffer tvb = buffers.get(i);
//            bgfx_alloc_transient_vertex_buffer(tvb, vertexCount, layout.get());
//            result[i] = new TransientVertexBuffer(tvb);
//        }
//        return result;
//    }
//
//    @NotNull
//    public static TransientVertexBuffer[] createAndAlloc(@NotNull MemoryStack stack, int vertexCount, @NotNull VertexLayout layout, int creationCount) {
//        final BGFXTransientVertexBuffer.Buffer buffers = BGFXTransientVertexBuffer.callocStack(creationCount, stack);
//        final TransientVertexBuffer[] result = new TransientVertexBuffer[creationCount];
//        for (int i=0; i < creationCount; i++) {
//            final BGFXTransientVertexBuffer tvb = buffers.get(i);
//            bgfx_alloc_transient_vertex_buffer(tvb, vertexCount, layout.get());
//            result[i] = new TransientVertexBuffer(tvb);
//        }
//        return result;
//    }

    public void alloc(@NotNull VertexLayout vertexLayout, int vertexCount) {
        bgfx_alloc_transient_vertex_buffer(buf, vertexCount, vertexLayout.get());
    }

    private static TransientVertexBuffer createImpl(@Nullable MemoryStack stack) {
        final BGFXTransientVertexBuffer tvb;
        if (stack != null) {
            tvb = BGFXTransientVertexBuffer.callocStack(stack);
        } else {
            tvb = BGFXTransientVertexBuffer.calloc();
        }
        return new TransientVertexBuffer(tvb);
    }

    // todo expose in advanced api
    @NotNull
    static TransientVertexBuffer wrap(@NotNull BGFXTransientVertexBuffer value) {
        return new TransientVertexBuffer(value);
    }

//    // todo seperate between methods like this which create and init a buffer and those which create an uninitialized buffer.
//    //      this should eventually be type safe (and an uninitialized buffer is called like a blueprint or something)
//    @Deprecated
//    @NotNull
//    public static TransientVertexBuffer heapCreate(@NotNull Number[][] vertices) {
//        Assertions.requirePositive(vertices.length);
//        Assertions.requirePositive(vertices[0].length);
//
//        final ByteBuffer vbuf = MemoryUtil.memAlloc(getByteCount(vertices));
//        final BGFXTransientVertexBuffer buf = createTransientVertexBuffer(vbuf, vertices);
//
//        return new TransientVertexBuffer(buf);
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
//    private static BGFXTransientVertexBuffer createTransientVertexBuffer(ByteBuffer buffer, Number[][] vertices) {
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
//        return createTransientVertexBuffer(buffer, vertices.length);
//    }
//
//    /*
//     * Creates a BGFXTransientVertexBuffer which is valid for the duration of the frame and can be reused by multiple
//     * draw calls.
//     */
//    private static BGFXTransientVertexBuffer createTransientVertexBuffer(ByteBuffer buffer, int numVerts) {
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
     * Frees the data backing this {@link TransientVertexBuffer}. This is only required for
     * {@link TransientVertexBuffer}s that are created on heap. Those created on stack (by passing {@link MemoryStack})
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