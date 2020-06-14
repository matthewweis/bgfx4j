package com.bariumhoof.bgfx4j.layout;

public final class TypedDynamicVertexBuffer1<V1 extends Vec<?,?>> extends TypedDynamicVertexBuffer<Vertex.Vertex1<V1>> {
    TypedDynamicVertexBuffer1(short handle, short layoutHandle, int numVertices) {
        super(handle, layoutHandle, numVertices);
    }
//    @SafeVarargs
//    public final void update(int startVertex, @NotNull Vertex.Vertex1<V1> ... vertices) {
//        assertValidVertexPack(getNumVertices(), startVertex, vertices);
//        vertexPack(handle(), startVertex, vertices);
//    }
}
