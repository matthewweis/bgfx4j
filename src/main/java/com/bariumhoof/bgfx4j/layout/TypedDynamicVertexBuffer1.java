package com.bariumhoof.bgfx4j.layout;

import org.jetbrains.annotations.NotNull;

public final class TypedDynamicVertexBuffer1<T1 extends BgfxAttribType, N1 extends Num, V1 extends Vec<N1, T1>> extends TypedDynamicVertexBuffer {
    TypedDynamicVertexBuffer1(short handle, short layoutHandle, int numVertices) {
        super(handle, layoutHandle, numVertices);
    }
    @SafeVarargs
    public final void update(int startVertex, @NotNull Vertex.Vertex1<V1>... vertices) {
        assertValidVertexPack(getNumVertices(), startVertex, vertices);
        vertexPack(handle(), startVertex, vertices);
    }
}
