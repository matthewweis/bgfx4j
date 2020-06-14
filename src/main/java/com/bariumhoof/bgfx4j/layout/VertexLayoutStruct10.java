package com.bariumhoof.bgfx4j.layout;

import com.bariumhoof.assertions.Assertions;
import com.bariumhoof.bgfx4j.enums.BGFX_BUFFER;
import org.jetbrains.annotations.NotNull;
import org.lwjgl.bgfx.BGFXVertexLayout;

import java.util.EnumSet;

import static org.lwjgl.bgfx.BGFX.bgfx_create_dynamic_vertex_buffer;
import static org.lwjgl.bgfx.BGFX.bgfx_create_vertex_layout;

public class VertexLayoutStruct10<V1 extends Vec<?,?>,
        V2 extends Vec<?,?>, V3 extends Vec<?,?>,
        V4 extends Vec<?,?>, V5 extends Vec<?,?>,
        V6 extends Vec<?,?>, V7 extends Vec<?,?>,
        V8 extends Vec<?,?>, V9 extends Vec<?,?>,
        V10 extends Vec<?,?>> extends VertexLayoutStruct {
    protected VertexLayoutStruct10(BGFXVertexLayout layout) {
        super(layout);
    }

    @NotNull
    public final TypedDynamicVertexBuffer10<V1,V2,V3,V4,V5,V6,V7,V8,V9,V10> mallocDynamicBuffer(
            int numVertices, @NotNull EnumSet<BGFX_BUFFER> flags){
        Assertions.requirePositive(numVertices);
        final short handle = bgfx_create_dynamic_vertex_buffer(numVertices, get(), (int) BGFX_BUFFER.flags(flags));
        final short layoutHandle = bgfx_create_vertex_layout(get());
        return new TypedDynamicVertexBuffer10<>(handle, layoutHandle, numVertices);
    }

}
