package com.bariumhoof.bgfx4j.layout;

import com.bariumhoof.assertions.Assertions;
import com.bariumhoof.bgfx4j.enums.BGFX_BUFFER;
import org.jetbrains.annotations.NotNull;
import org.lwjgl.bgfx.BGFXVertexLayout;

import java.util.EnumSet;

import static org.lwjgl.bgfx.BGFX.bgfx_create_dynamic_vertex_buffer;
import static org.lwjgl.bgfx.BGFX.bgfx_create_vertex_layout;

public class VertexLayoutStruct12<E1 extends BgfxAttrib, N1 extends Num, T1 extends BgfxAttribType, V1 extends Vec<N1, T1>,
        E2 extends BgfxAttrib, N2 extends Num, T2 extends BgfxAttribType, V2 extends Vec<N2, T2>, E3 extends BgfxAttrib, N3 extends Num, T3 extends BgfxAttribType, V3 extends Vec<N3, T3>,
        E4 extends BgfxAttrib, N4 extends Num, T4 extends BgfxAttribType, V4 extends Vec<N4, T4>, E5 extends BgfxAttrib, N5 extends Num, T5 extends BgfxAttribType, V5 extends Vec<N5, T5>,
        E6 extends BgfxAttrib, N6 extends Num, T6 extends BgfxAttribType, V6 extends Vec<N6, T6>, E7 extends BgfxAttrib, N7 extends Num, T7 extends BgfxAttribType, V7 extends Vec<N7, T7>,
        E8 extends BgfxAttrib, N8 extends Num, T8 extends BgfxAttribType, V8 extends Vec<N8, T8>, E9 extends BgfxAttrib, N9 extends Num, T9 extends BgfxAttribType, V9 extends Vec<N9, T9>,
        E10 extends BgfxAttrib, N10 extends Num, T10 extends BgfxAttribType, V10 extends Vec<N10, T10>, E11 extends BgfxAttrib, N11 extends Num, T11 extends BgfxAttribType, V11 extends Vec<N11, T11>,
        E12 extends BgfxAttrib, N12 extends Num, T12 extends BgfxAttribType, V12 extends Vec<N12, T12>> extends VertexLayoutStruct {
    protected VertexLayoutStruct12(BGFXVertexLayout layout) {
        super(layout);
    }

    @NotNull
    public final TypedDynamicVertexBuffer12<T1,N1,V1,T2,N2,V2,T3,N3,V3,T4,N4,V4,T5,N5,V5,T6,N6,V6,T7,N7,V7,T8,N8,V8,T9,N9,V9,T10,N10,V10,T11,N11,V11,T12,N12,V12> mallocDynamicBuffer(

            int numVertices, @NotNull EnumSet<BGFX_BUFFER> flags){
        Assertions.requirePositive(numVertices);
        final short handle = bgfx_create_dynamic_vertex_buffer(numVertices, get(), (int) BGFX_BUFFER.flags(flags));
        final short layoutHandle = bgfx_create_vertex_layout(get());
        return new TypedDynamicVertexBuffer12<>(handle, layoutHandle, numVertices);
    }

}
