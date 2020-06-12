package com.bariumhoof.bgfx4j.layout;

import com.bariumhoof.assertions.Assertions;
import com.bariumhoof.bgfx4j.enums.BGFX_BUFFER;
import org.jetbrains.annotations.NotNull;
import org.lwjgl.bgfx.BGFXVertexLayout;

import java.util.EnumSet;

import static org.lwjgl.bgfx.BGFX.bgfx_create_dynamic_vertex_buffer;
import static org.lwjgl.bgfx.BGFX.bgfx_create_vertex_layout;

public class VertexLayoutStruct2<E1 extends BgfxAttrib, N1 extends Num, T1 extends BgfxAttribType, V1 extends Vec<N1, T1>,
        E2 extends BgfxAttrib, N2 extends Num, T2 extends BgfxAttribType, V2 extends Vec<N2, T2>> extends VertexLayoutStruct {
    protected VertexLayoutStruct2(BGFXVertexLayout layout) {
        super(layout);
    }

    @NotNull
    public final TypedDynamicVertexBuffer2<T1,N1,V1,T2,N2,V2> mallocDynamicBuffer(
            @NotNull VertexLayoutStruct2<E1,N1,T1,V1,E2,N2,T2,V2> vertexLayout,
            int numVertices, @NotNull EnumSet<BGFX_BUFFER> flags){
        Assertions.requirePositive(numVertices);
        final short handle = bgfx_create_dynamic_vertex_buffer(numVertices, vertexLayout.get(), (int) BGFX_BUFFER.flags(flags));
        final short layoutHandle = bgfx_create_vertex_layout(vertexLayout.get());
        return new TypedDynamicVertexBuffer2<>(handle, layoutHandle, numVertices);
    }

}
