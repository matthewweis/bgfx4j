package com.bariumhoof.bgfx4j.buffer;

import com.bariumhoof.assertions.Assertions;
import com.bariumhoof.bgfx4j.Disposable;
import com.bariumhoof.bgfx4j.Handle;
import com.bariumhoof.bgfx4j.enums.BGFX_BUFFER;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.lwjgl.bgfx.BGFXMemory;
import org.lwjgl.bgfx.BGFXVertexLayout;
import org.lwjgl.system.MemoryUtil;

import java.nio.ByteBuffer;
import java.util.EnumSet;

import static org.lwjgl.bgfx.BGFX.*;

public class DynamicVertexBufferOld implements Disposable, Handle {

    private final short handle;
    private final short layoutHandle;

    public short layoutHandle() {
        return layoutHandle;
    }

    @Getter
    private final int numVertices;

    private DynamicVertexBufferOld(short handle, short layoutHandle, int numVertices) {
        this.handle = handle;
        this.layoutHandle = layoutHandle;
        this.numVertices = numVertices;
    }

    public static DynamicVertexBufferOld create(@NotNull VertexLayoutOld vertexLayoutOld, int numVertices, @NotNull EnumSet<BGFX_BUFFER> flags) {
        Assertions.requirePositive(numVertices);
        final short handle = bgfx_create_dynamic_vertex_buffer(numVertices, vertexLayoutOld.get(), (int) BGFX_BUFFER.flags(flags));
        final short layoutHandle = bgfx_create_vertex_layout(vertexLayoutOld.get());
        return new DynamicVertexBufferOld(handle, layoutHandle, numVertices);
    }

    public static DynamicVertexBufferOld create(@NotNull VertexLayoutOld vertexLayoutOld, @NotNull BGFXMemory init, @NotNull EnumSet<BGFX_BUFFER> flags) {
        final short handle = bgfx_create_dynamic_vertex_buffer_mem(init, vertexLayoutOld.get(), (int) BGFX_BUFFER.flags(flags));
        final short layoutHandle = bgfx_create_vertex_layout(vertexLayoutOld.get());
        return new DynamicVertexBufferOld(handle, layoutHandle, init.size() / vertexLayoutOld.getStrideBytes());
    }

    public static DynamicVertexBufferOld create(@NotNull VertexLayoutOld vertexLayoutOld, @NotNull Number[][] vertices) {
        Assertions.requirePositive(vertices.length);
        Assertions.requirePositive(vertices[0].length);

        final ByteBuffer vbuf = MemoryUtil.memAlloc(getByteCount(vertices));
        final short handle = createDynamicVertexBuffer(vbuf, vertexLayoutOld.get(), vertices);
        final short layoutHandle = bgfx_create_vertex_layout(vertexLayoutOld.get());

        return new DynamicVertexBufferOld(handle, layoutHandle, vertices.length);
    }

    public void update(@NotNull BGFXMemory memory) {
        update(0, memory);
    }

    public void update(int startVertex, @NotNull BGFXMemory memory) {
        bgfx_update_dynamic_vertex_buffer(handle, startVertex, memory);
    }

    // todo make private
    public static int getByteCount(@NotNull Number[][] vertices) {
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
    private static short createDynamicVertexBuffer(ByteBuffer buffer, BGFXVertexLayout vertexLayout, Number[][] vertices) {
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
        return createDynamicVertexBuffer(buffer, vertexLayout, EnumSet.of(BGFX_BUFFER.NONE));
    }

    /*
     * From lwjgl bgfx tutorial - Cubes
     */
    private static short createDynamicVertexBuffer(ByteBuffer buffer, BGFXVertexLayout decl, EnumSet<BGFX_BUFFER> flags) {
        final BGFXMemory vbhMem = bgfx_make_ref(buffer);
        Assertions.requireNonNull(vbhMem);
        return bgfx_create_dynamic_vertex_buffer_mem(vbhMem, decl, (int) BGFX_BUFFER.flags(flags));
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
        bgfx_destroy_dynamic_vertex_buffer(handle);
    }

    @Override
    public short handle() {
        return handle;
    }
}