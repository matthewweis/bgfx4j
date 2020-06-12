package com.bariumhoof.bgfx4j.layout;

import org.jetbrains.annotations.NotNull;

public final class TypedDynamicVertexBuffer9<
        T1 extends BgfxAttribType, N1 extends Num, V1 extends Vec<N1, T1>,
        T2 extends BgfxAttribType, N2 extends Num, V2 extends Vec<N2, T2>,
        T3 extends BgfxAttribType, N3 extends Num, V3 extends Vec<N3, T3>,
        T4 extends BgfxAttribType, N4 extends Num, V4 extends Vec<N4, T4>,
        T5 extends BgfxAttribType, N5 extends Num, V5 extends Vec<N5, T5>,
        T6 extends BgfxAttribType, N6 extends Num, V6 extends Vec<N6, T6>,
        T7 extends BgfxAttribType, N7 extends Num, V7 extends Vec<N7, T7>,
        T8 extends BgfxAttribType, N8 extends Num, V8 extends Vec<N8, T8>,
        T9 extends BgfxAttribType, N9 extends Num, V9 extends Vec<N9, T9>>
        extends TypedDynamicVertexBuffer {
    TypedDynamicVertexBuffer9(short handle, short layoutHandle, int numVertices) {
        super(handle, layoutHandle, numVertices);
    }
    @SafeVarargs
    public final void update(int startVertex, @NotNull Vertex.Vertex9<V1, V2, V3, V4, V5, V6, V7, V8, V9>... vertices) {
        assertValidVertexPack(getNumVertices(), startVertex, vertices);
        vertexPack(handle(), startVertex, vertices);
    }
}
