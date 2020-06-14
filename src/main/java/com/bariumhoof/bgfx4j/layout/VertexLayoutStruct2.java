package com.bariumhoof.bgfx4j.layout;

import com.bariumhoof.assertions.Assertions;
import com.bariumhoof.bgfx4j.enums.BGFX_BUFFER;
import org.jetbrains.annotations.NotNull;
import org.lwjgl.bgfx.BGFXVertexLayout;

import java.util.EnumSet;

import static org.lwjgl.bgfx.BGFX.bgfx_create_dynamic_vertex_buffer;
import static org.lwjgl.bgfx.BGFX.bgfx_create_vertex_layout;

public class VertexLayoutStruct2<V1 extends Vec<?,?>,
        V2 extends Vec<?,?>> extends VertexLayoutStruct {
    protected VertexLayoutStruct2(BGFXVertexLayout layout) {
        super(layout);
    }

    @NotNull
    public final TypedDynamicVertexBuffer2<V1,V2> mallocDynamicBuffer(
            int numVertices, @NotNull EnumSet<BGFX_BUFFER> flags){
        BGFXVertexLayout.Buffer s;
        Assertions.requirePositive(numVertices);
        final short handle = bgfx_create_dynamic_vertex_buffer(numVertices, get(), (int) BGFX_BUFFER.flags(flags));
        final short layoutHandle = bgfx_create_vertex_layout(get());
        return new TypedDynamicVertexBuffer2<>(handle, layoutHandle, numVertices);
    }

}
