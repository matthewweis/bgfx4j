package com.bariumhoof.bgfx4j.layout;

public final class TypedDynamicVertexBuffer13<
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
        V13 extends Vec<?,?>>
        extends TypedDynamicVertexBuffer<Vertex.Vertex13<V1, V2, V3, V4, V5, V6, V7, V8, V9, V10, V11, V12, V13>> {
    TypedDynamicVertexBuffer13(short handle, short layoutHandle, int numVertices) {
        super(handle, layoutHandle, numVertices);
    }
//    @SafeVarargs
//    public final void update(int startVertex, @NotNull Vertex.Vertex13<V1, V2, V3, V4, V5, V6, V7, V8, V9, V10, V11, V12, V13>... vertices) {
//        assertValidVertexPack(getNumVertices(), startVertex, vertices);
//        vertexPack(handle(), startVertex, vertices);
//    }
}
