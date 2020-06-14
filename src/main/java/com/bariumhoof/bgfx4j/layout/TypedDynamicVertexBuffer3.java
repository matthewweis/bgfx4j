package com.bariumhoof.bgfx4j.layout;

public final class TypedDynamicVertexBuffer3<
        V1 extends Vec<?,?>,
        V2 extends Vec<?,?>,
        V3 extends Vec<?,?>> extends TypedDynamicVertexBuffer<Vertex.Vertex3<V1,V2,V3>> {
    TypedDynamicVertexBuffer3(short handle, short layoutHandle, int numVertices) {
        super(handle, layoutHandle, numVertices);
    }
//    @SafeVarargs
//    public final void update(int startVertex, @NotNull Vertex.Vertex3<V1, V2, V3>... vertices) {
//        assertValidVertexPack(getNumVertices(), startVertex, vertices);
//        vertexPack(handle(), startVertex, vertices);
//    }
}
