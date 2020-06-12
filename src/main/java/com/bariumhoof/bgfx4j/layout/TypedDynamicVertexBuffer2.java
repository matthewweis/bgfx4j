package com.bariumhoof.bgfx4j.layout;

import org.jetbrains.annotations.NotNull;

public final class TypedDynamicVertexBuffer2<
        T1 extends BgfxAttribType, N1 extends Num, V1 extends Vec<N1, T1>,
        T2 extends BgfxAttribType, N2 extends Num, V2 extends Vec<N2, T2>> extends TypedDynamicVertexBuffer {
    TypedDynamicVertexBuffer2(short handle, short layoutHandle, int numVertices) {
        super(handle, layoutHandle, numVertices);
    }
    @SafeVarargs
    public final void update(int startVertex, @NotNull Vertex.Vertex2<V1, V2>... vertices) {
        assertValidVertexPack(getNumVertices(), startVertex, vertices);
        vertexPack(handle(), startVertex, vertices);
    }
}
