package com.bariumhoof.bgfx4j.layout;

public final class TypedDynamicVertexBuffer8<
        V1 extends Vec<?,?>,
        V2 extends Vec<?,?>,
        V3 extends Vec<?,?>,
        V4 extends Vec<?,?>,
        V5 extends Vec<?,?>,
        V6 extends Vec<?,?>,
        V7 extends Vec<?,?>,
        V8 extends Vec<?,?>>
        extends TypedDynamicVertexBuffer<Vertex.Vertex8<V1, V2, V3, V4, V5, V6, V7, V8>> {
    TypedDynamicVertexBuffer8(short handle, short layoutHandle, int numVertices) {
        super(handle, layoutHandle, numVertices);
    }
//    @SafeVarargs
//    public final void update(int startVertex, @NotNull Vertex.Vertex8<V1, V2, V3, V4, V5, V6, V7, V8>... vertices) {
//        assertValidVertexPack(getNumVertices(), startVertex, vertices);
//        vertexPack(handle(), startVertex, vertices);
//    }
}
