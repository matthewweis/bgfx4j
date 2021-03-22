package com.bariumhoof.bgfx4j.layout;

import com.bariumhoof.bgfx4j.Disposable;
import com.bariumhoof.bgfx4j.Handle;
import com.bariumhoof.bgfx4j.enums.BGFX_ATTRIB_TYPE;
import com.bariumhoof.bgfx4j.enums.BGFX_BUFFER;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.lwjgl.bgfx.BGFXMemory;
import org.lwjgl.system.MemoryUtil;

import java.nio.ByteBuffer;
import java.util.Collection;
import java.util.EnumSet;
import java.util.List;

import static org.lwjgl.bgfx.BGFX.*;

// todo look into efficiencies of mem system, copy vs ref, and putting things on the stack
public class StaticVertexBuffer<V extends Vertex> implements Disposable, Handle {

    private final short handle;
    private final short layoutHandle;

    public short layoutHandle() {
        return layoutHandle;
    }

    @Override
    public void dispose() {
        bgfx_destroy_vertex_buffer(handle);
    }

    @Override
    public short handle() {
        return handle;
    }

    @Getter
    private final int numVertices;

    StaticVertexBuffer(short handle, short layoutHandle, int numVertices) {
        this.handle = handle;
        this.layoutHandle = layoutHandle;
        this.numVertices = numVertices;
    }

    @NotNull
    public static <V extends Vertex> StaticVertexBuffer<V> create(
            @NotNull Iterable<V> vertices,
            @NotNull VertexLayout<V> layout){
        return create(vertices, count(vertices), layout, EnumSet.of(BGFX_BUFFER.NONE));
    }

    @NotNull
    public static <V extends Vertex> StaticVertexBuffer<V> create(
            @NotNull Iterable<V> vertices,
            int vertexCount,
            @NotNull VertexLayout<V> layout){
        return create(vertices, vertexCount, layout, EnumSet.of(BGFX_BUFFER.NONE));
    }

    @NotNull
    public static <V extends Vertex> StaticVertexBuffer<V> create(
            @NotNull Iterable<V> vertices,
            @NotNull VertexLayout<V> layout,
            @NotNull EnumSet<BGFX_BUFFER> flags){
        return create(vertices, count(vertices), layout, flags);
    }

    @NotNull
    @SafeVarargs
    public static <V extends Vertex> StaticVertexBuffer<V> create(
            @NotNull VertexLayout<V> layout,
            @NotNull V ... vertices){
        return create(layout, EnumSet.of(BGFX_BUFFER.NONE), vertices);
    }

    @NotNull
    @SafeVarargs
    public static <V extends Vertex> StaticVertexBuffer<V> create(
            @NotNull VertexLayout<V> layout,
            @NotNull EnumSet<BGFX_BUFFER> flags,
            @NotNull V ... vertices){
        return create(List.of(vertices), vertices.length, layout, flags);
    }

    @NotNull
    public static <V extends Vertex> StaticVertexBuffer<V> create(
            @NotNull Iterable<V> vertices,
            int vertexCount,
            @NotNull VertexLayout<V> layout,
            @NotNull EnumSet<BGFX_BUFFER> flags){
        if (vertexCount == 0) {
            final ByteBuffer bytes = MemoryUtil.memAlloc(0);
            final BGFXMemory memory = bgfx_make_ref(bytes);
            final short handle = bgfx_create_vertex_buffer(memory, layout.get(), (int) BGFX_BUFFER.flags(flags));
            return new StaticVertexBuffer<V>(handle, layout.handle(), vertexCount);
        }

        final V sample = vertices.iterator().next();
        int requiredBytes = 0;
        for (Object o : sample.array()) {
            final Vec<?, ?> vec = (Vec<?, ?>) o;
            requiredBytes += StaticVertexBuffer.computeBytes(vec.type().representedType(), vec.number(), vertexCount);
        }

        final ByteBuffer bytes = MemoryUtil.memAlloc(requiredBytes);
        for (V tuple : vertices) {
            for (Object o : tuple.array()) {
                final Vec<?, ?> vec = (Vec<?, ?>) o; // guaranteed by public api
                vec.put(bytes);
            }
        }
        bytes.flip();
        final BGFXMemory memory = bgfx_make_ref(bytes);
        final short handle = bgfx_create_vertex_buffer(memory, layout.get(), (int) BGFX_BUFFER.flags(flags));
        return new StaticVertexBuffer<V>(handle, layout.handle(), vertexCount);
    }

    // helper methods

    private static int count(Iterable<?> vertices) {
        if (vertices instanceof Collection<?>) {
            // fast path
            return ((Collection<?>) vertices).size();
        } else {
            // slow path
            int i = 0;
            for (Object ignored : vertices) {
                i += 1;
            }
            return i;
        }
    }

    private static int computeBytes(BGFX_ATTRIB_TYPE type, Num num, int arraySize) {
        switch (type) {
            case UINT8:
                return num.value() * arraySize;
            case UINT10:
                // UINT10 xyzw where each gets 10/10/10/2 bits respectively.
                // By the Khronos spec, the Num will be 3 or 4 if the type is UINT10, and if Num = 3 then
                //  those two bits just go unused.

                // Thus, we have 4 bytes (32 bits) per array slot regardless of size of each array element
                // and we just multiply the array size by 4 bytes
                return arraySize * 4;
            case HALF:
            case INT16:
                return num.value() * arraySize * 2; // two bytes (floating point or a short)
            case FLOAT:
                return num.value() * arraySize * 4; // four bytes (float)
            default:
                throw new IllegalStateException("Pattern matching must be exhaustive");
        }
    }

}