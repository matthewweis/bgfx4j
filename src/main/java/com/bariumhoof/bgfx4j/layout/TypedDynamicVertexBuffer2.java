package com.bariumhoof.bgfx4j.layout;

public final class TypedDynamicVertexBuffer2<
        V1 extends Vec<?,?>,
        V2 extends Vec<?,?>> extends TypedDynamicVertexBuffer<Vertex.Vertex2<V1,V2>> {
    TypedDynamicVertexBuffer2(short handle, short layoutHandle, int numVertices) {
        super(handle, layoutHandle, numVertices);
    }
//    @SafeVarargs
//    public final void update(int startVertex, @NotNull Vertex.Vertex2<V1, V2>... vertices) {
//        assertValidVertexPack(getNumVertices(), startVertex, vertices);
//        vertexPack(handle(), startVertex, vertices);
//    }
}
