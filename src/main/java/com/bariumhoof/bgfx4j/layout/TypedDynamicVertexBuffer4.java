package com.bariumhoof.bgfx4j.layout;

import org.jetbrains.annotations.NotNull;

public final class TypedDynamicVertexBuffer4<
        T1 extends BgfxAttribType, N1 extends Num, V1 extends Vec<N1, T1>,
        T2 extends BgfxAttribType, N2 extends Num, V2 extends Vec<N2, T2>,
        T3 extends BgfxAttribType, N3 extends Num, V3 extends Vec<N3, T3>,
        T4 extends BgfxAttribType, N4 extends Num, V4 extends Vec<N4, T4>>
        extends TypedDynamicVertexBuffer {
    TypedDynamicVertexBuffer4(short handle, short layoutHandle, int numVertices) {
        super(handle, layoutHandle, numVertices);
    }
    @SafeVarargs
    public final void update(int startVertex, @NotNull Vertex.Vertex4<V1, V2, V3, V4>... vertices) {
        assertValidVertexPack(getNumVertices(), startVertex, vertices);
        vertexPack(handle(), startVertex, vertices);
    }
}
