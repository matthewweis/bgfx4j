package com.bariumhoof.bgfx4j.wip;

import com.bariumhoof.bgfx4j.layout.Vec;
import com.bariumhoof.bgfx4j.layout.Vertex;
import com.bariumhoof.bgfx4j.layout.VertexLayout;
import org.lwjgl.bgfx.BGFXVertexLayout;

import java.nio.ByteBuffer;
import java.util.List;
import java.util.function.Function;

// would be nice to
public class FlatBuffer<V extends Vertex> {

    final ByteBuffer inner;

    private FlatBuffer(VertexLayout<V> layout, int size) {
        final BGFXVertexLayout bgfxLayout = layout.get();
        final short stride = bgfxLayout.stride();
        inner = ByteBuffer.allocate(stride * size);
    }

    public void put(int i, V vertex) {
        update(i, ignored -> vertex); // todo custom impl
    }

    public void put(List<? extends V> values) {
        for (V value : values) {
            for (Vec<?, ?> vec : value.array()) {
                vec.put(inner);
            }
        }
    }

    public void update(int i, Function<? super V, ? extends V> updater) {

    }

    public void update(int begin, int endExclusive, Function<? super V, ? extends V> updater) {
//        inner.
    }

}
