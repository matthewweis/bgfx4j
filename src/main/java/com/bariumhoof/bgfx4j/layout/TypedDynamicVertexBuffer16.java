package com.bariumhoof.bgfx4j.layout;

public final class TypedDynamicVertexBuffer16<
        V1 extends Vec<?,?>,
        V2 extends Vec<?,?>,
        V3 extends Vec<?,?>,
        V4 extends Vec<?,?>,
        V5 extends Vec<?,?>,
        V6 extends Vec<?,?>,
        V7 extends Vec<?,?>,
        V8 extends Vec<?,?>,
        V9 extends Vec<?,?>,
        V10 extends Vec<?,?>,
        V11 extends Vec<?,?>,
        V12 extends Vec<?,?>,
        V13 extends Vec<?,?>,
        V14 extends Vec<?,?>,
        V15 extends Vec<?,?>,
        V16 extends Vec<?,?>>
        extends TypedDynamicVertexBuffer<Vertex.Vertex16<V1, V2, V3, V4, V5, V6, V7, V8, V9, V10, V11, V12, V13, V14, V15, V16>> {
    TypedDynamicVertexBuffer16(short handle, short layoutHandle, int numVertices) {
        super(handle, layoutHandle, numVertices);
    }
//    @SafeVarargs
//    public final void update(int startVertex, @NotNull Vertex.Vertex16<V1, V2, V3, V4, V5, V6, V7, V8, V9, V10, V11, V12, V13, V14, V15, V16>... vertices) {
//        assertValidVertexPack(getNumVertices(), startVertex, vertices);
//        vertexPack(handle(), startVertex, vertices);
//    }
}
