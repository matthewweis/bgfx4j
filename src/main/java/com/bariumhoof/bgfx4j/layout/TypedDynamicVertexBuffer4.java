package com.bariumhoof.bgfx4j.layout;

public final class TypedDynamicVertexBuffer4<
        V1 extends Vec<?,?>,
        V2 extends Vec<?,?>,
        V3 extends Vec<?,?>,
        V4 extends Vec<?,?>>
        extends TypedDynamicVertexBuffer<Vertex.Vertex4<V1,V2,V3,V4>> {
    TypedDynamicVertexBuffer4(short handle, short layoutHandle, int numVertices) {
        super(handle, layoutHandle, numVertices);
    }
//    @SafeVarargs
//    public final void update(int startVertex, @NotNull Vertex.Vertex4<V1, V2, V3, V4>... vertices) {
//        assertValidVertexPack(getNumVertices(), startVertex, vertices);
//        vertexPack(handle(), startVertex, vertices);
//    }
}
