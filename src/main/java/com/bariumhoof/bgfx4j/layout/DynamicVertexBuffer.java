package com.bariumhoof.bgfx4j.layout;

import com.bariumhoof.assertions.Assertions;
import com.bariumhoof.bgfx4j.Disposable;
import com.bariumhoof.bgfx4j.Handle;
import com.bariumhoof.bgfx4j.enums.BGFX_ATTRIB_TYPE;
import com.bariumhoof.bgfx4j.enums.BGFX_BUFFER;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.lwjgl.bgfx.BGFXMemory;
import org.lwjgl.system.MemoryStack;

import java.nio.ByteBuffer;
import java.util.Collection;
import java.util.EnumSet;
import java.util.List;

import static org.lwjgl.bgfx.BGFX.*;

// todo look into efficiencies of mem system, copy vs ref, and putting things on the stack
public class DynamicVertexBuffer<V extends Vertex> implements Disposable, Handle {

    private final short handle;
    private final short layoutHandle;

    public short layoutHandle() {
        return layoutHandle;
    }

    @Override
    public void dispose() {
        bgfx_destroy_dynamic_vertex_buffer(handle);
    }

    @Override
    public short handle() {
        return handle;
    }

    @Getter
    private final int numVertices;

    DynamicVertexBuffer(short handle, short layoutHandle, int numVertices) {
        this.handle = handle;
        this.layoutHandle = layoutHandle;
        this.numVertices = numVertices;
    }

    @NotNull
    public static <V extends Vertex> DynamicVertexBuffer<V> create(int numVertices, @NotNull VertexLayout<V> layout){
        return create(numVertices, layout, EnumSet.of(BGFX_BUFFER.NONE));
    }

    @NotNull
    public static <V extends Vertex> DynamicVertexBuffer<V> create(int numVertices, @NotNull VertexLayout<V> layout, @NotNull EnumSet<BGFX_BUFFER> flags){
        Assertions.requirePositive(numVertices);
        final short handle = bgfx_create_dynamic_vertex_buffer(numVertices, layout.get(), (int) BGFX_BUFFER.flags(flags));
        return new DynamicVertexBuffer<>(handle, layout.handle(), numVertices);
    }

    @SafeVarargs
    public final void update(@NotNull V ... vertices) {
        updateFrom(0, vertices);
    }

    public final void update(@NotNull Iterable<V> vertices) {
        updateFrom(0, vertices, count(vertices));
    }

    public final void update(@NotNull Iterable<V> vertices, int count) {
        updateFrom(0, vertices, count);
    }

    public final void updateFrom(int startVertex, @NotNull Iterable<V> vertices) {
        updateFrom(startVertex, vertices, count(vertices));
    }

    @SafeVarargs
    public final void updateFrom(int startVertex, @NotNull V ... vertices) {
        assertValidVertexPack(getNumVertices(), startVertex, vertices.length);
        vertexPack(handle(), startVertex, List.of(vertices), vertices.length);
    }

    public final void updateFrom(int startVertex, @NotNull Iterable<V> vertices, int count) {
        assertValidVertexPack(getNumVertices(), startVertex, count);
        vertexPack(handle(), startVertex, vertices, count);
    }

    // helper methods
//
//    static <T extends Vertex> void assertValidVertexPack(int numVertices, int startVertex, T[] vertices) {
//        assertValidVertexPack(numVertices, startVertex, vertices.length);
//    }

    static void assertValidVertexPack(int numVertices, int startVertex, int count) {
        Assertions.requireNonNegative(startVertex);
        final int endIndex = startVertex + count - 1; // todo put into assertions lib?
        Assertions.requireLessThan(endIndex, numVertices);
    }

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

    static <T extends Vertex> void vertexPack(short handle, int startVertex, Iterable<T> vertices, int vertexCount) {
        Assertions.requirePositive(vertexCount);
        final T sample = vertices.iterator().next();

        int requiredBytes = 0;
        for (Object o : sample.array()) {
            final Vec<?, ?> vec = (Vec<?, ?>) o;
            requiredBytes += computeBytes(vec.type().representedType(), vec.number(), vertexCount);
        }

        try (final MemoryStack stack = MemoryStack.stackPush()) {
            // todo, some might be too big to store on stack!
            final ByteBuffer bytes = stack.malloc(requiredBytes);
            for (T tuple : vertices) {
                for (Object o : tuple.array()) {
                    final Vec<?, ?> vec = (Vec<?, ?>) o; // guaranteed by public api
                    vec.put(bytes);
                }
            }
            bytes.flip();

            final BGFXMemory memory = bgfx_copy(bytes); // todo is this necessary?? currently does (extra?) copy...
            bgfx_update_dynamic_vertex_buffer(handle, startVertex, memory);
        }
    }

    static int computeBytes(BGFX_ATTRIB_TYPE type, Num num, int arraySize) {
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