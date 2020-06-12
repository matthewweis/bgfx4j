package com.bariumhoof.bgfx4j.layout;

import com.bariumhoof.assertions.Assertions;
import com.bariumhoof.bgfx4j.enums.BGFX_BUFFER;
import org.jetbrains.annotations.NotNull;
import org.lwjgl.bgfx.BGFXVertexLayout;

import java.util.EnumSet;

import static org.lwjgl.bgfx.BGFX.bgfx_create_dynamic_vertex_buffer;
import static org.lwjgl.bgfx.BGFX.bgfx_create_vertex_layout;

public class VertexLayoutStruct1<E1 extends BgfxAttrib, N1 extends Num, T1 extends BgfxAttribType, V1 extends Vec<N1, T1>> extends VertexLayoutStruct {
    protected VertexLayoutStruct1(BGFXVertexLayout layout) {
        super(layout);
    }

    @NotNull
    public final TypedDynamicVertexBuffer1<T1,N1,V1> mallocDynamicBuffer(
            @NotNull VertexLayoutStruct1<E1,N1,T1,V1> vertexLayout,
            int numVertices, @NotNull EnumSet<BGFX_BUFFER> flags){
        Assertions.requirePositive(numVertices);
        final short handle = bgfx_create_dynamic_vertex_buffer(numVertices, vertexLayout.get(), (int) BGFX_BUFFER.flags(flags));
        final short layoutHandle = bgfx_create_vertex_layout(vertexLayout.get());
        return new TypedDynamicVertexBuffer1<>(handle, layoutHandle, numVertices);
    }

}
