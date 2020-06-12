package com.bariumhoof.bgfx4j.layout;

import org.jetbrains.annotations.NotNull;

public final class TypedDynamicVertexBuffer18<
        T1 extends BgfxAttribType, N1 extends Num, V1 extends Vec<N1, T1>,
        T2 extends BgfxAttribType, N2 extends Num, V2 extends Vec<N2, T2>,
        T3 extends BgfxAttribType, N3 extends Num, V3 extends Vec<N3, T3>,
        T4 extends BgfxAttribType, N4 extends Num, V4 extends Vec<N4, T4>,
        T5 extends BgfxAttribType, N5 extends Num, V5 extends Vec<N5, T5>,
        T6 extends BgfxAttribType, N6 extends Num, V6 extends Vec<N6, T6>,
        T7 extends BgfxAttribType, N7 extends Num, V7 extends Vec<N7, T7>,
        T8 extends BgfxAttribType, N8 extends Num, V8 extends Vec<N8, T8>,
        T9 extends BgfxAttribType, N9 extends Num, V9 extends Vec<N9, T9>,
        T10 extends BgfxAttribType, N10 extends Num, V10 extends Vec<N10, T10>,
        T11 extends BgfxAttribType, N11 extends Num, V11 extends Vec<N11, T11>,
        T12 extends BgfxAttribType, N12 extends Num, V12 extends Vec<N12, T12>,
        T13 extends BgfxAttribType, N13 extends Num, V13 extends Vec<N13, T13>,
        T14 extends BgfxAttribType, N14 extends Num, V14 extends Vec<N14, T14>,
        T15 extends BgfxAttribType, N15 extends Num, V15 extends Vec<N15, T15>,
        T16 extends BgfxAttribType, N16 extends Num, V16 extends Vec<N16, T16>,
        T17 extends BgfxAttribType, N17 extends Num, V17 extends Vec<N17, T17>,
        T18 extends BgfxAttribType, N18 extends Num, V18 extends Vec<N18, T18>>
        extends TypedDynamicVertexBuffer {
    TypedDynamicVertexBuffer18(short handle, short layoutHandle, int numVertices) {
        super(handle, layoutHandle, numVertices);
    }
    @SafeVarargs
    public final void update(int startVertex, @NotNull Vertex.Vertex18<V1, V2, V3, V4, V5, V6, V7, V8, V9, V10, V11, V12, V13, V14, V15, V16, V17, V18>... vertices) {
        assertValidVertexPack(getNumVertices(), startVertex, vertices);
        vertexPack(handle(), startVertex, vertices);
    }
}
