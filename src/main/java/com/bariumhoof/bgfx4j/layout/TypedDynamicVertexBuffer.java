package com.bariumhoof.bgfx4j.layout;

import com.bariumhoof.assertions.Assertions;
import com.bariumhoof.bgfx4j.Disposable;
import com.bariumhoof.bgfx4j.Handle;
import com.bariumhoof.bgfx4j.enums.BGFX_ATTRIB_TYPE;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.lwjgl.bgfx.BGFXMemory;
import org.lwjgl.system.MemoryStack;

import java.nio.ByteBuffer;
import java.util.Collection;
import java.util.List;

import static org.lwjgl.bgfx.BGFX.*;

// todo look into efficiencies of mem system, copy vs ref, and putting things on the stack
public class TypedDynamicVertexBuffer<V extends Vertex> implements Disposable, Handle {

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

    TypedDynamicVertexBuffer(short handle, short layoutHandle, int numVertices) {
        this.handle = handle;
        this.layoutHandle = layoutHandle;
        this.numVertices = numVertices;
    }

    @SafeVarargs
    public final void updateFrom(int startVertex, @NotNull V ... vertices) {
        assertValidVertexPack(getNumVertices(), startVertex, vertices.length);
        vertexPack(handle(), startVertex, List.of(vertices), vertices.length);
    }

    @SafeVarargs
    public final void update(@NotNull V ... vertices) {
        updateFrom(0, vertices);
    }

    public final void updateFrom(int startVertex, @NotNull Iterable<V> vertices) {
        final int count = count(vertices);
        assertValidVertexPack(getNumVertices(), startVertex, count);
        vertexPack(handle(), startVertex, vertices, count);
    }

    public final void update(@NotNull Iterable<V> vertices) {
        updateFrom(0, vertices);
    }

    // helper methods
//
//    static <T extends Vertex> void assertValidVertexPack(int numVertices, int startVertex, T[] vertices) {
//        assertValidVertexPack(numVertices, startVertex, vertices.length);
//    }

    static void assertValidVertexPack(int numVertices, int startVertex, int count) {
        Assertions.requireNonNegative(startVertex);
        final int endIndex = startVertex + count - 1;
        Assertions.requireLessThan(endIndex, numVertices);
    }

    private static int count(Iterable<?> vertices) {
        if (vertices instanceof Collection<?>) {
            return ((Collection<?>) vertices).size();
        } else {
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
        final int numVecsPerTuple = sample.size(); // equal to cardinality of type

        // get size of one tuple, we know from contract that T is a tuple holding Vecs
        int requiredBytes = 0;
        for (int i=0; i < numVecsPerTuple; i++) {
            for (Object o : sample.array()) {
                final Vec<?, ?> vec = (Vec<?, ?>) o; // we know from public API that T is a tuple holding Vecs
                requiredBytes += computeBytes(vec.type().representedType(), vec.number(), vertexCount);
            }
        }

        try (final MemoryStack stack = MemoryStack.stackPush()) {
            // todo, some might be too big to store on stack!
            final ByteBuffer bytes = stack.malloc(requiredBytes);
//            final int vertexStrideBytes = computeStrideBytes(vertices);

            for (T tuple : vertices) {
                for (Object o : tuple.array()) {
                    final Vec<?, ?> vec = (Vec<?, ?>) o; // guaranteed by public api
                    vec.put(bytes);
                }
            }

            final BGFXMemory memory = bgfx_copy(bytes); // todo is this necessary?? currently does (extra?) copy...
            bgfx_update_dynamic_vertex_buffer(handle, startVertex, memory);
        }
    }

    static <T extends Vertex> int computeStrideBytes(Iterable<T> tuples) {
        int total = 0;
        final T sample = tuples.iterator().next();
        for (Object o : sample.array()) {
            final Vec<?, ?> vec = (Vec<?, ?>) o; // known to be vector by public api
            final BGFX_ATTRIB_TYPE type = vec.type().representedType();
            final Num num = vec.number();
            switch (type) {
                case UINT8:
                    total += num.value();
                    break;
                case UINT10:
                    total += 4;
                    break;
                case HALF:
                case INT16:
                    total += num.value() * 2; // two bytes (floating point or a short)
                    break;
                case FLOAT:
                    total += num.value() * 4; // four bytes (float)
                    break;
                default:
                    throw new IllegalStateException("Pattern matching must be exhaustive");
            }
        }
        return total;
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