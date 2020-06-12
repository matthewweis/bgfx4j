package com.bariumhoof.bgfx4j.layout;

import com.bariumhoof.assertions.Assertions;
import com.bariumhoof.bgfx4j.enums.BGFX_BUFFER;
import org.jetbrains.annotations.NotNull;
import org.lwjgl.bgfx.BGFXVertexLayout;

import java.util.EnumSet;

import static org.lwjgl.bgfx.BGFX.bgfx_create_dynamic_vertex_buffer;
import static org.lwjgl.bgfx.BGFX.bgfx_create_vertex_layout;

public class VertexLayoutStruct5<E1 extends BgfxAttrib, N1 extends Num, T1 extends BgfxAttribType, V1 extends Vec<N1, T1>,
        E2 extends BgfxAttrib, N2 extends Num, T2 extends BgfxAttribType, V2 extends Vec<N2, T2>, E3 extends BgfxAttrib, N3 extends Num, T3 extends BgfxAttribType, V3 extends Vec<N3, T3>,
        E4 extends BgfxAttrib, N4 extends Num, T4 extends BgfxAttribType, V4 extends Vec<N4, T4>, E5 extends BgfxAttrib, N5 extends Num, T5 extends BgfxAttribType, V5 extends Vec<N5, T5>> extends VertexLayoutStruct {
    protected VertexLayoutStruct5(BGFXVertexLayout layout) {
        super(layout);
    }

    @NotNull
    public final TypedDynamicVertexBuffer5<T1,N1,V1,T2,N2,V2,T3,N3,V3,T4,N4,V4,T5,N5,V5> mallocDynamicBuffer(
            @NotNull VertexLayoutStruct5<E1,N1,T1,V1,E2,N2,T2,V2,E3,N3,T3,V3,E4,N4,T4,V4,E5,N5,T5,V5> vertexLayout,
            int numVertices, @NotNull EnumSet<BGFX_BUFFER> flags){
        Assertions.requirePositive(numVertices);
        final short handle = bgfx_create_dynamic_vertex_buffer(numVertices, vertexLayout.get(), (int) BGFX_BUFFER.flags(flags));
        final short layoutHandle = bgfx_create_vertex_layout(vertexLayout.get());
        return new TypedDynamicVertexBuffer5<>(handle, layoutHandle, numVertices);
    }

}
