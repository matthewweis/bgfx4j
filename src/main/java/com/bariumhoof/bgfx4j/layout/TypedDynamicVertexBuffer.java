package com.bariumhoof.bgfx4j.layout;

import com.bariumhoof.assertions.Assertions;
import com.bariumhoof.bgfx4j.Disposable;
import com.bariumhoof.bgfx4j.Handle;
import com.bariumhoof.bgfx4j.enums.BGFX_ATTRIB_TYPE;
import lombok.Getter;
import org.lwjgl.bgfx.BGFXMemory;
import org.lwjgl.system.MemoryStack;

import java.nio.ByteBuffer;

import static org.lwjgl.bgfx.BGFX.*;

// todo autogen for 1 to 18
// todo look into efficiencies of mem system, copy vs ref, and putting things on the stack
public abstract class TypedDynamicVertexBuffer implements Disposable, Handle {

    private final short handle;
    private final short layoutHandle;

    public short layoutHandle() {
        return layoutHandle;
    }

    @Override
    public void dispose() {
        bgfx_destroy_dynamic_vertex_buffer(handle);
    }

    @Override
    public short handle() {
        return handle;
    }

    @Getter
    private final int numVertices;

    TypedDynamicVertexBuffer(short handle, short layoutHandle, int numVertices) {
        this.handle = handle;
        this.layoutHandle = layoutHandle;
        this.numVertices = numVertices;
    }

    // factory methods

//    public static <E extends BgfxAttrib, T extends BgfxAttribType, N extends Num, V extends Vec<N, T>> TypedDynamicVertexBuffer1<T, N, V> create(
//            @NotNull VertexLayoutStruct1<E, N, T, V> vertexLayout, int numVertices, @NotNull EnumSet<BGFX_BUFFER> flags){
//        Assertions.requirePositive(numVertices);
//        final short handle = bgfx_create_dynamic_vertex_buffer(numVertices, vertexLayout.get(), (int) BGFX_BUFFER.flags(flags));
//        final short layoutHandle = bgfx_create_vertex_layout(vertexLayout.get());
//        return new TypedDynamicVertexBuffer1<>(handle, layoutHandle, numVertices);
//    }
//
//    public static <E1 extends BgfxAttrib, T1 extends BgfxAttribType, N1 extends Num, V1 extends Vec<N1, T1>,
//            E2 extends BgfxAttrib, T2 extends BgfxAttribType, N2 extends Num, V2 extends Vec<N2, T2>> TypedDynamicVertexBuffer2<T1, N1, V1, T2, N2, V2> create(
//            @NotNull VertexLayoutStruct2<E1, N1, T1, V1, E2, N2, T2, V2> vertexLayout,
//            int numVertices, @NotNull EnumSet<BGFX_BUFFER> flags){
//        Assertions.requirePositive(numVertices);
//        final short handle = bgfx_create_dynamic_vertex_buffer(numVertices, vertexLayout.get(), (int) BGFX_BUFFER.flags(flags));
//        final short layoutHandle = bgfx_create_vertex_layout(vertexLayout.get());
//        return new TypedDynamicVertexBuffer2<>(handle, layoutHandle, numVertices);
//    }
//
//    public static <E1 extends BgfxAttrib, T1 extends BgfxAttribType, N1 extends Num, V1 extends Vec<N1, T1>,
//            E2 extends BgfxAttrib, T2 extends BgfxAttribType, N2 extends Num, V2 extends Vec<N2, T2>,
//            E3 extends BgfxAttrib, T3 extends BgfxAttribType, N3 extends Num, V3 extends Vec<N3, T3>> TypedDynamicVertexBuffer3<T1, N1, V1, T2, N2, V2, T3, N3, V3> create(
//            @NotNull VertexLayoutStruct3<E1, N1, T1, V1, E2, N2, T2, V2, E3, N3, T3, V3> vertexLayout,
//            int numVertices, @NotNull EnumSet<BGFX_BUFFER> flags){
//        Assertions.requirePositive(numVertices);
//        final short handle = bgfx_create_dynamic_vertex_buffer(numVertices, vertexLayout.get(), (int) BGFX_BUFFER.flags(flags));
//        final short layoutHandle = bgfx_create_vertex_layout(vertexLayout.get());
//        return new TypedDynamicVertexBuffer3<>(handle, layoutHandle, numVertices);
//    }
//
//    public static <E1 extends BgfxAttrib, T1 extends BgfxAttribType, N1 extends Num, V1 extends Vec<N1, T1>,
//            E2 extends BgfxAttrib, T2 extends BgfxAttribType, N2 extends Num, V2 extends Vec<N2, T2>,
//            E3 extends BgfxAttrib, T3 extends BgfxAttribType, N3 extends Num, V3 extends Vec<N3, T3>,
//            E4 extends BgfxAttrib, T4 extends BgfxAttribType, N4 extends Num, V4 extends Vec<N4, T4>>
//    TypedDynamicVertexBuffer4<T1, N1, V1, T2, N2, V2, T3, N3, V3, T4, N4, V4> create(
//            @NotNull VertexLayoutStruct4<E1, N1, T1, V1, E2, N2, T2, V2, E3, N3, T3, V3, E4, N4, T4, V4> vertexLayout,
//            int numVertices, @NotNull EnumSet<BGFX_BUFFER> flags){
//        Assertions.requirePositive(numVertices);
//        final short handle = bgfx_create_dynamic_vertex_buffer(numVertices, vertexLayout.get(), (int) BGFX_BUFFER.flags(flags));
//        final short layoutHandle = bgfx_create_vertex_layout(vertexLayout.get());
//        return new TypedDynamicVertexBuffer4<>(handle, layoutHandle, numVertices);
//    }
//
//    public static <E1 extends BgfxAttrib, T1 extends BgfxAttribType, N1 extends Num, V1 extends Vec<N1, T1>,
//            E2 extends BgfxAttrib, T2 extends BgfxAttribType, N2 extends Num, V2 extends Vec<N2, T2>,
//            E3 extends BgfxAttrib, T3 extends BgfxAttribType, N3 extends Num, V3 extends Vec<N3, T3>,
//            E4 extends BgfxAttrib, T4 extends BgfxAttribType, N4 extends Num, V4 extends Vec<N4, T4>,
//            E5 extends BgfxAttrib, T5 extends BgfxAttribType, N5 extends Num, V5 extends Vec<N5, T5>>
//    TypedDynamicVertexBuffer5<T1, N1, V1, T2, N2, V2, T3, N3, V3, T4, N4, V4, T5, N5, V5> create(
//            @NotNull VertexLayoutStruct5<E1, N1, T1, V1, E2, N2, T2, V2, E3, N3, T3, V3, E4, N4, T4, V4, E5, N5, T5, V5> vertexLayout,
//            int numVertices, @NotNull EnumSet<BGFX_BUFFER> flags){
//        Assertions.requirePositive(numVertices);
//        final short handle = bgfx_create_dynamic_vertex_buffer(numVertices, vertexLayout.get(), (int) BGFX_BUFFER.flags(flags));
//        final short layoutHandle = bgfx_create_vertex_layout(vertexLayout.get());
//        return new TypedDynamicVertexBuffer5<>(handle, layoutHandle, numVertices);
//    }
//
//    public static <E1 extends BgfxAttrib, T1 extends BgfxAttribType, N1 extends Num, V1 extends Vec<N1, T1>,
//            E2 extends BgfxAttrib, T2 extends BgfxAttribType, N2 extends Num, V2 extends Vec<N2, T2>,
//            E3 extends BgfxAttrib, T3 extends BgfxAttribType, N3 extends Num, V3 extends Vec<N3, T3>,
//            E4 extends BgfxAttrib, T4 extends BgfxAttribType, N4 extends Num, V4 extends Vec<N4, T4>,
//            E5 extends BgfxAttrib, T5 extends BgfxAttribType, N5 extends Num, V5 extends Vec<N5, T5>,
//            E6 extends BgfxAttrib, T6 extends BgfxAttribType, N6 extends Num, V6 extends Vec<N6, T6>>
//    TypedDynamicVertexBuffer6<T1, N1, V1, T2, N2, V2, T3, N3, V3, T4, N4, V4, T5, N5, V5, T6, N6, V6> create(
//            @NotNull VertexLayoutStruct6<E1, N1, T1, V1, E2, N2, T2, V2, E3, N3, T3, V3, E4, N4, T4, V4, E5, N5, T5, V5, E6, N6, T6, V6> vertexLayout,
//            int numVertices, @NotNull EnumSet<BGFX_BUFFER> flags){
//        Assertions.requirePositive(numVertices);
//        final short handle = bgfx_create_dynamic_vertex_buffer(numVertices, vertexLayout.get(), (int) BGFX_BUFFER.flags(flags));
//        final short layoutHandle = bgfx_create_vertex_layout(vertexLayout.get());
//        return new TypedDynamicVertexBuffer6<>(handle, layoutHandle, numVertices);
//    }
//
//    public static <E1 extends BgfxAttrib, T1 extends BgfxAttribType, N1 extends Num, V1 extends Vec<N1, T1>,
//            E2 extends BgfxAttrib, T2 extends BgfxAttribType, N2 extends Num, V2 extends Vec<N2, T2>,
//            E3 extends BgfxAttrib, T3 extends BgfxAttribType, N3 extends Num, V3 extends Vec<N3, T3>,
//            E4 extends BgfxAttrib, T4 extends BgfxAttribType, N4 extends Num, V4 extends Vec<N4, T4>,
//            E5 extends BgfxAttrib, T5 extends BgfxAttribType, N5 extends Num, V5 extends Vec<N5, T5>,
//            E6 extends BgfxAttrib, T6 extends BgfxAttribType, N6 extends Num, V6 extends Vec<N6, T6>,
//            E7 extends BgfxAttrib, T7 extends BgfxAttribType, N7 extends Num, V7 extends Vec<N7, T7>>
//    TypedDynamicVertexBuffer7<T1, N1, V1, T2, N2, V2, T3, N3, V3, T4, N4, V4, T5, N5, V5, T6, N6, V6, T7, N7, V7> create(
//            @NotNull VertexLayoutStruct7<E1, N1, T1, V1, E2, N2, T2, V2, E3, N3, T3, V3, E4, N4, T4, V4, E5, N5, T5, V5, E6, N6, T6, V6, E7, N7, T7, V7> vertexLayout,
//            int numVertices, @NotNull EnumSet<BGFX_BUFFER> flags){
//        Assertions.requirePositive(numVertices);
//        final short handle = bgfx_create_dynamic_vertex_buffer(numVertices, vertexLayout.get(), (int) BGFX_BUFFER.flags(flags));
//        final short layoutHandle = bgfx_create_vertex_layout(vertexLayout.get());
//        return new TypedDynamicVertexBuffer7<>(handle, layoutHandle, numVertices);
//    }
//
//    public static <E1 extends BgfxAttrib, T1 extends BgfxAttribType, N1 extends Num, V1 extends Vec<N1, T1>,
//            E2 extends BgfxAttrib, T2 extends BgfxAttribType, N2 extends Num, V2 extends Vec<N2, T2>,
//            E3 extends BgfxAttrib, T3 extends BgfxAttribType, N3 extends Num, V3 extends Vec<N3, T3>,
//            E4 extends BgfxAttrib, T4 extends BgfxAttribType, N4 extends Num, V4 extends Vec<N4, T4>,
//            E5 extends BgfxAttrib, T5 extends BgfxAttribType, N5 extends Num, V5 extends Vec<N5, T5>,
//            E6 extends BgfxAttrib, T6 extends BgfxAttribType, N6 extends Num, V6 extends Vec<N6, T6>,
//            E7 extends BgfxAttrib, T7 extends BgfxAttribType, N7 extends Num, V7 extends Vec<N7, T7>,
//            E8 extends BgfxAttrib, T8 extends BgfxAttribType, N8 extends Num, V8 extends Vec<N8, T8>>
//    TypedDynamicVertexBuffer8<T1, N1, V1, T2, N2, V2, T3, N3, V3, T4, N4, V4, T5, N5, V5, T6, N6, V6, T7, N7, V7, T8, N8, V8> create(
//            @NotNull VertexLayoutStruct8<E1, N1, T1, V1, E2, N2, T2, V2, E3, N3, T3, V3, E4, N4, T4, V4, E5, N5, T5, V5, E6, N6, T6, V6, E7, N7, T7, V7, E8, N8, T8, V8> vertexLayout,
//            int numVertices, @NotNull EnumSet<BGFX_BUFFER> flags){
//        Assertions.requirePositive(numVertices);
//        final short handle = bgfx_create_dynamic_vertex_buffer(numVertices, vertexLayout.get(), (int) BGFX_BUFFER.flags(flags));
//        final short layoutHandle = bgfx_create_vertex_layout(vertexLayout.get());
//        return new TypedDynamicVertexBuffer8<>(handle, layoutHandle, numVertices);
//    }
//
//    public static <E1 extends BgfxAttrib, T1 extends BgfxAttribType, N1 extends Num, V1 extends Vec<N1, T1>,
//            E2 extends BgfxAttrib, T2 extends BgfxAttribType, N2 extends Num, V2 extends Vec<N2, T2>,
//            E3 extends BgfxAttrib, T3 extends BgfxAttribType, N3 extends Num, V3 extends Vec<N3, T3>,
//            E4 extends BgfxAttrib, T4 extends BgfxAttribType, N4 extends Num, V4 extends Vec<N4, T4>,
//            E5 extends BgfxAttrib, T5 extends BgfxAttribType, N5 extends Num, V5 extends Vec<N5, T5>,
//            E6 extends BgfxAttrib, T6 extends BgfxAttribType, N6 extends Num, V6 extends Vec<N6, T6>,
//            E7 extends BgfxAttrib, T7 extends BgfxAttribType, N7 extends Num, V7 extends Vec<N7, T7>,
//            E8 extends BgfxAttrib, T8 extends BgfxAttribType, N8 extends Num, V8 extends Vec<N8, T8>,
//            E9 extends BgfxAttrib, T9 extends BgfxAttribType, N9 extends Num, V9 extends Vec<N9, T9>>
//    TypedDynamicVertexBuffer9<T1, N1, V1, T2, N2, V2, T3, N3, V3, T4, N4, V4, T5, N5, V5, T6, N6, V6, T7, N7, V7, T8, N8, V8, T9, N9, V9> create(
//            @NotNull VertexLayoutStruct9<E1, N1, T1, V1, E2, N2, T2, V2, E3, N3, T3, V3, E4, N4, T4, V4, E5, N5, T5, V5, E6, N6, T6, V6, E7, N7, T7, V7, E8, N8, T8, V8, E9, N9, T9, V9> vertexLayout,
//            int numVertices, @NotNull EnumSet<BGFX_BUFFER> flags){
//        Assertions.requirePositive(numVertices);
//        final short handle = bgfx_create_dynamic_vertex_buffer(numVertices, vertexLayout.get(), (int) BGFX_BUFFER.flags(flags));
//        final short layoutHandle = bgfx_create_vertex_layout(vertexLayout.get());
//        return new TypedDynamicVertexBuffer9<>(handle, layoutHandle, numVertices);
//    }
//
//    public static <E1 extends BgfxAttrib, T1 extends BgfxAttribType, N1 extends Num, V1 extends Vec<N1, T1>,
//            E2 extends BgfxAttrib, T2 extends BgfxAttribType, N2 extends Num, V2 extends Vec<N2, T2>,
//            E3 extends BgfxAttrib, T3 extends BgfxAttribType, N3 extends Num, V3 extends Vec<N3, T3>,
//            E4 extends BgfxAttrib, T4 extends BgfxAttribType, N4 extends Num, V4 extends Vec<N4, T4>,
//            E5 extends BgfxAttrib, T5 extends BgfxAttribType, N5 extends Num, V5 extends Vec<N5, T5>,
//            E6 extends BgfxAttrib, T6 extends BgfxAttribType, N6 extends Num, V6 extends Vec<N6, T6>,
//            E7 extends BgfxAttrib, T7 extends BgfxAttribType, N7 extends Num, V7 extends Vec<N7, T7>,
//            E8 extends BgfxAttrib, T8 extends BgfxAttribType, N8 extends Num, V8 extends Vec<N8, T8>,
//            E9 extends BgfxAttrib, T9 extends BgfxAttribType, N9 extends Num, V9 extends Vec<N9, T9>,
//            E10 extends BgfxAttrib, T10 extends BgfxAttribType, N10 extends Num, V10 extends Vec<N10, T10>>
//    TypedDynamicVertexBuffer10<T1, N1, V1, T2, N2, V2, T3, N3, V3, T4, N4, V4, T5, N5, V5, T6, N6, V6, T7, N7, V7, T8, N8, V8, T9, N9, V9, T10, N10, V10> create(
//            @NotNull VertexLayoutStruct10<E1, N1, T1, V1, E2, N2, T2, V2, E3, N3, T3, V3, E4, N4, T4, V4, E5, N5, T5, V5, E6, N6, T6, V6, E7, N7, T7, V7, E8, N8, T8, V8, E9, N9, T9, V9, E10, N10, T10, V10> vertexLayout,
//            int numVertices, @NotNull EnumSet<BGFX_BUFFER> flags){
//        Assertions.requirePositive(numVertices);
//        final short handle = bgfx_create_dynamic_vertex_buffer(numVertices, vertexLayout.get(), (int) BGFX_BUFFER.flags(flags));
//        final short layoutHandle = bgfx_create_vertex_layout(vertexLayout.get());
//        return new TypedDynamicVertexBuffer10<>(handle, layoutHandle, numVertices);
//    }
//
//    public static <E1 extends BgfxAttrib, T1 extends BgfxAttribType, N1 extends Num, V1 extends Vec<N1, T1>,
//            E2 extends BgfxAttrib, T2 extends BgfxAttribType, N2 extends Num, V2 extends Vec<N2, T2>,
//            E3 extends BgfxAttrib, T3 extends BgfxAttribType, N3 extends Num, V3 extends Vec<N3, T3>,
//            E4 extends BgfxAttrib, T4 extends BgfxAttribType, N4 extends Num, V4 extends Vec<N4, T4>,
//            E5 extends BgfxAttrib, T5 extends BgfxAttribType, N5 extends Num, V5 extends Vec<N5, T5>,
//            E6 extends BgfxAttrib, T6 extends BgfxAttribType, N6 extends Num, V6 extends Vec<N6, T6>,
//            E7 extends BgfxAttrib, T7 extends BgfxAttribType, N7 extends Num, V7 extends Vec<N7, T7>,
//            E8 extends BgfxAttrib, T8 extends BgfxAttribType, N8 extends Num, V8 extends Vec<N8, T8>,
//            E9 extends BgfxAttrib, T9 extends BgfxAttribType, N9 extends Num, V9 extends Vec<N9, T9>,
//            E10 extends BgfxAttrib, T10 extends BgfxAttribType, N10 extends Num, V10 extends Vec<N10, T10>,
//            E11 extends BgfxAttrib, T11 extends BgfxAttribType, N11 extends Num, V11 extends Vec<N11, T11>>
//    TypedDynamicVertexBuffer11<T1, N1, V1, T2, N2, V2, T3, N3, V3, T4, N4, V4, T5, N5, V5, T6, N6, V6, T7, N7, V7, T8, N8, V8, T9, N9, V9, T10, N10, V10, T11, N11, V11> create(
//            @NotNull VertexLayoutStruct11<E1, N1, T1, V1, E2, N2, T2, V2, E3, N3, T3, V3, E4, N4, T4, V4, E5, N5, T5, V5, E6, N6, T6, V6, E7, N7, T7, V7, E8, N8, T8, V8, E9, N9, T9, V9, E10, N10, T10, V10, E11, N11, T11, V11> vertexLayout,
//            int numVertices, @NotNull EnumSet<BGFX_BUFFER> flags){
//        Assertions.requirePositive(numVertices);
//        final short handle = bgfx_create_dynamic_vertex_buffer(numVertices, vertexLayout.get(), (int) BGFX_BUFFER.flags(flags));
//        final short layoutHandle = bgfx_create_vertex_layout(vertexLayout.get());
//        return new TypedDynamicVertexBuffer11<>(handle, layoutHandle, numVertices);
//    }
//
//    public static <E1 extends BgfxAttrib, T1 extends BgfxAttribType, N1 extends Num, V1 extends Vec<N1, T1>,
//            E2 extends BgfxAttrib, T2 extends BgfxAttribType, N2 extends Num, V2 extends Vec<N2, T2>,
//            E3 extends BgfxAttrib, T3 extends BgfxAttribType, N3 extends Num, V3 extends Vec<N3, T3>,
//            E4 extends BgfxAttrib, T4 extends BgfxAttribType, N4 extends Num, V4 extends Vec<N4, T4>,
//            E5 extends BgfxAttrib, T5 extends BgfxAttribType, N5 extends Num, V5 extends Vec<N5, T5>,
//            E6 extends BgfxAttrib, T6 extends BgfxAttribType, N6 extends Num, V6 extends Vec<N6, T6>,
//            E7 extends BgfxAttrib, T7 extends BgfxAttribType, N7 extends Num, V7 extends Vec<N7, T7>,
//            E8 extends BgfxAttrib, T8 extends BgfxAttribType, N8 extends Num, V8 extends Vec<N8, T8>,
//            E9 extends BgfxAttrib, T9 extends BgfxAttribType, N9 extends Num, V9 extends Vec<N9, T9>,
//            E10 extends BgfxAttrib, T10 extends BgfxAttribType, N10 extends Num, V10 extends Vec<N10, T10>,
//            E11 extends BgfxAttrib, T11 extends BgfxAttribType, N11 extends Num, V11 extends Vec<N11, T11>,
//            E12 extends BgfxAttrib, T12 extends BgfxAttribType, N12 extends Num, V12 extends Vec<N12, T12>>
//    TypedDynamicVertexBuffer12<T1, N1, V1, T2, N2, V2, T3, N3, V3, T4, N4, V4, T5, N5, V5, T6, N6, V6, T7, N7, V7, T8, N8, V8, T9, N9, V9, T10, N10, V10, T11, N11, V11, T12, N12, V12> create(
//            @NotNull VertexLayoutStruct12<E1, N1, T1, V1, E2, N2, T2, V2, E3, N3, T3, V3, E4, N4, T4, V4, E5, N5, T5, V5, E6, N6, T6, V6, E7, N7, T7, V7, E8, N8, T8, V8, E9, N9, T9, V9, E10, N10, T10, V10, E11, N11, T11, V11, E12, N12, T12, V12> vertexLayout,
//            int numVertices, @NotNull EnumSet<BGFX_BUFFER> flags){
//        Assertions.requirePositive(numVertices);
//        final short handle = bgfx_create_dynamic_vertex_buffer(numVertices, vertexLayout.get(), (int) BGFX_BUFFER.flags(flags));
//        final short layoutHandle = bgfx_create_vertex_layout(vertexLayout.get());
//        return new TypedDynamicVertexBuffer12<>(handle, layoutHandle, numVertices);
//    }
//
//    public static <E1 extends BgfxAttrib, T1 extends BgfxAttribType, N1 extends Num, V1 extends Vec<N1, T1>,
//            E2 extends BgfxAttrib, T2 extends BgfxAttribType, N2 extends Num, V2 extends Vec<N2, T2>,
//            E3 extends BgfxAttrib, T3 extends BgfxAttribType, N3 extends Num, V3 extends Vec<N3, T3>,
//            E4 extends BgfxAttrib, T4 extends BgfxAttribType, N4 extends Num, V4 extends Vec<N4, T4>,
//            E5 extends BgfxAttrib, T5 extends BgfxAttribType, N5 extends Num, V5 extends Vec<N5, T5>,
//            E6 extends BgfxAttrib, T6 extends BgfxAttribType, N6 extends Num, V6 extends Vec<N6, T6>,
//            E7 extends BgfxAttrib, T7 extends BgfxAttribType, N7 extends Num, V7 extends Vec<N7, T7>,
//            E8 extends BgfxAttrib, T8 extends BgfxAttribType, N8 extends Num, V8 extends Vec<N8, T8>,
//            E9 extends BgfxAttrib, T9 extends BgfxAttribType, N9 extends Num, V9 extends Vec<N9, T9>,
//            E10 extends BgfxAttrib, T10 extends BgfxAttribType, N10 extends Num, V10 extends Vec<N10, T10>,
//            E11 extends BgfxAttrib, T11 extends BgfxAttribType, N11 extends Num, V11 extends Vec<N11, T11>,
//            E12 extends BgfxAttrib, T12 extends BgfxAttribType, N12 extends Num, V12 extends Vec<N12, T12>,
//            E13 extends BgfxAttrib, T13 extends BgfxAttribType, N13 extends Num, V13 extends Vec<N13, T13>>
//    TypedDynamicVertexBuffer13<T1, N1, V1, T2, N2, V2, T3, N3, V3, T4, N4, V4, T5, N5, V5, T6, N6, V6, T7, N7, V7, T8, N8, V8, T9, N9, V9, T10, N10, V10, T11, N11, V11, T12, N12, V12, T13, N13, V13> create(
//            @NotNull VertexLayoutStruct13<E1, N1, T1, V1, E2, N2, T2, V2, E3, N3, T3, V3, E4, N4, T4, V4, E5, N5, T5, V5, E6, N6, T6, V6, E7, N7, T7, V7, E8, N8, T8, V8, E9, N9, T9, V9, E10, N10, T10, V10, E11, N11, T11, V11, E12, N12, T12, V12, E13, N13, T13, V13> vertexLayout,
//            int numVertices, @NotNull EnumSet<BGFX_BUFFER> flags){
//        Assertions.requirePositive(numVertices);
//        final short handle = bgfx_create_dynamic_vertex_buffer(numVertices, vertexLayout.get(), (int) BGFX_BUFFER.flags(flags));
//        final short layoutHandle = bgfx_create_vertex_layout(vertexLayout.get());
//        return new TypedDynamicVertexBuffer13<>(handle, layoutHandle, numVertices);
//    }
//
//    public static <E1 extends BgfxAttrib, T1 extends BgfxAttribType, N1 extends Num, V1 extends Vec<N1, T1>,
//            E2 extends BgfxAttrib, T2 extends BgfxAttribType, N2 extends Num, V2 extends Vec<N2, T2>,
//            E3 extends BgfxAttrib, T3 extends BgfxAttribType, N3 extends Num, V3 extends Vec<N3, T3>,
//            E4 extends BgfxAttrib, T4 extends BgfxAttribType, N4 extends Num, V4 extends Vec<N4, T4>,
//            E5 extends BgfxAttrib, T5 extends BgfxAttribType, N5 extends Num, V5 extends Vec<N5, T5>,
//            E6 extends BgfxAttrib, T6 extends BgfxAttribType, N6 extends Num, V6 extends Vec<N6, T6>,
//            E7 extends BgfxAttrib, T7 extends BgfxAttribType, N7 extends Num, V7 extends Vec<N7, T7>,
//            E8 extends BgfxAttrib, T8 extends BgfxAttribType, N8 extends Num, V8 extends Vec<N8, T8>,
//            E9 extends BgfxAttrib, T9 extends BgfxAttribType, N9 extends Num, V9 extends Vec<N9, T9>,
//            E10 extends BgfxAttrib, T10 extends BgfxAttribType, N10 extends Num, V10 extends Vec<N10, T10>,
//            E11 extends BgfxAttrib, T11 extends BgfxAttribType, N11 extends Num, V11 extends Vec<N11, T11>,
//            E12 extends BgfxAttrib, T12 extends BgfxAttribType, N12 extends Num, V12 extends Vec<N12, T12>,
//            E13 extends BgfxAttrib, T13 extends BgfxAttribType, N13 extends Num, V13 extends Vec<N13, T13>,
//            E14 extends BgfxAttrib, T14 extends BgfxAttribType, N14 extends Num, V14 extends Vec<N14, T14>>
//    TypedDynamicVertexBuffer14<T1, N1, V1, T2, N2, V2, T3, N3, V3, T4, N4, V4, T5, N5, V5, T6, N6, V6, T7, N7, V7, T8, N8, V8, T9, N9, V9, T10, N10, V10, T11, N11, V11, T12, N12, V12, T13, N13, V13, T14, N14, V14> create(
//            @NotNull VertexLayoutStruct14<E1, N1, T1, V1, E2, N2, T2, V2, E3, N3, T3, V3, E4, N4, T4, V4, E5, N5, T5, V5, E6, N6, T6, V6, E7, N7, T7, V7, E8, N8, T8, V8, E9, N9, T9, V9, E10, N10, T10, V10, E11, N11, T11, V11, E12, N12, T12, V12, E13, N13, T13, V13, E14, N14, T14, V14> vertexLayout,
//            int numVertices, @NotNull EnumSet<BGFX_BUFFER> flags){
//        Assertions.requirePositive(numVertices);
//        final short handle = bgfx_create_dynamic_vertex_buffer(numVertices, vertexLayout.get(), (int) BGFX_BUFFER.flags(flags));
//        final short layoutHandle = bgfx_create_vertex_layout(vertexLayout.get());
//        return new TypedDynamicVertexBuffer14<>(handle, layoutHandle, numVertices);
//    }
//
//    public static <E1 extends BgfxAttrib, T1 extends BgfxAttribType, N1 extends Num, V1 extends Vec<N1, T1>,
//            E2 extends BgfxAttrib, T2 extends BgfxAttribType, N2 extends Num, V2 extends Vec<N2, T2>,
//            E3 extends BgfxAttrib, T3 extends BgfxAttribType, N3 extends Num, V3 extends Vec<N3, T3>,
//            E4 extends BgfxAttrib, T4 extends BgfxAttribType, N4 extends Num, V4 extends Vec<N4, T4>,
//            E5 extends BgfxAttrib, T5 extends BgfxAttribType, N5 extends Num, V5 extends Vec<N5, T5>,
//            E6 extends BgfxAttrib, T6 extends BgfxAttribType, N6 extends Num, V6 extends Vec<N6, T6>,
//            E7 extends BgfxAttrib, T7 extends BgfxAttribType, N7 extends Num, V7 extends Vec<N7, T7>,
//            E8 extends BgfxAttrib, T8 extends BgfxAttribType, N8 extends Num, V8 extends Vec<N8, T8>,
//            E9 extends BgfxAttrib, T9 extends BgfxAttribType, N9 extends Num, V9 extends Vec<N9, T9>,
//            E10 extends BgfxAttrib, T10 extends BgfxAttribType, N10 extends Num, V10 extends Vec<N10, T10>,
//            E11 extends BgfxAttrib, T11 extends BgfxAttribType, N11 extends Num, V11 extends Vec<N11, T11>,
//            E12 extends BgfxAttrib, T12 extends BgfxAttribType, N12 extends Num, V12 extends Vec<N12, T12>,
//            E13 extends BgfxAttrib, T13 extends BgfxAttribType, N13 extends Num, V13 extends Vec<N13, T13>,
//            E14 extends BgfxAttrib, T14 extends BgfxAttribType, N14 extends Num, V14 extends Vec<N14, T14>,
//            E15 extends BgfxAttrib, T15 extends BgfxAttribType, N15 extends Num, V15 extends Vec<N15, T15>>
//    TypedDynamicVertexBuffer15<T1, N1, V1, T2, N2, V2, T3, N3, V3, T4, N4, V4, T5, N5, V5, T6, N6, V6, T7, N7, V7, T8, N8, V8, T9, N9, V9, T10, N10, V10, T11, N11, V11, T12, N12, V12, T13, N13, V13, T14, N14, V14, T15, N15, V15> create(
//            @NotNull VertexLayoutStruct15<E1, N1, T1, V1, E2, N2, T2, V2, E3, N3, T3, V3, E4, N4, T4, V4, E5, N5, T5, V5, E6, N6, T6, V6, E7, N7, T7, V7, E8, N8, T8, V8, E9, N9, T9, V9, E10, N10, T10, V10, E11, N11, T11, V11, E12, N12, T12, V12, E13, N13, T13, V13, E14, N14, T14, V14, E15, N15, T15, V15> vertexLayout,
//            int numVertices, @NotNull EnumSet<BGFX_BUFFER> flags){
//        Assertions.requirePositive(numVertices);
//        final short handle = bgfx_create_dynamic_vertex_buffer(numVertices, vertexLayout.get(), (int) BGFX_BUFFER.flags(flags));
//        final short layoutHandle = bgfx_create_vertex_layout(vertexLayout.get());
//        return new TypedDynamicVertexBuffer15<>(handle, layoutHandle, numVertices);
//    }
//
//    public static <E1 extends BgfxAttrib, T1 extends BgfxAttribType, N1 extends Num, V1 extends Vec<N1, T1>,
//            E2 extends BgfxAttrib, T2 extends BgfxAttribType, N2 extends Num, V2 extends Vec<N2, T2>,
//            E3 extends BgfxAttrib, T3 extends BgfxAttribType, N3 extends Num, V3 extends Vec<N3, T3>,
//            E4 extends BgfxAttrib, T4 extends BgfxAttribType, N4 extends Num, V4 extends Vec<N4, T4>,
//            E5 extends BgfxAttrib, T5 extends BgfxAttribType, N5 extends Num, V5 extends Vec<N5, T5>,
//            E6 extends BgfxAttrib, T6 extends BgfxAttribType, N6 extends Num, V6 extends Vec<N6, T6>,
//            E7 extends BgfxAttrib, T7 extends BgfxAttribType, N7 extends Num, V7 extends Vec<N7, T7>,
//            E8 extends BgfxAttrib, T8 extends BgfxAttribType, N8 extends Num, V8 extends Vec<N8, T8>,
//            E9 extends BgfxAttrib, T9 extends BgfxAttribType, N9 extends Num, V9 extends Vec<N9, T9>,
//            E10 extends BgfxAttrib, T10 extends BgfxAttribType, N10 extends Num, V10 extends Vec<N10, T10>,
//            E11 extends BgfxAttrib, T11 extends BgfxAttribType, N11 extends Num, V11 extends Vec<N11, T11>,
//            E12 extends BgfxAttrib, T12 extends BgfxAttribType, N12 extends Num, V12 extends Vec<N12, T12>,
//            E13 extends BgfxAttrib, T13 extends BgfxAttribType, N13 extends Num, V13 extends Vec<N13, T13>,
//            E14 extends BgfxAttrib, T14 extends BgfxAttribType, N14 extends Num, V14 extends Vec<N14, T14>,
//            E15 extends BgfxAttrib, T15 extends BgfxAttribType, N15 extends Num, V15 extends Vec<N15, T15>,
//            E16 extends BgfxAttrib, T16 extends BgfxAttribType, N16 extends Num, V16 extends Vec<N16, T16>>
//    TypedDynamicVertexBuffer16<T1, N1, V1, T2, N2, V2, T3, N3, V3, T4, N4, V4, T5, N5, V5, T6, N6, V6, T7, N7, V7, T8, N8, V8, T9, N9, V9, T10, N10, V10, T11, N11, V11, T12, N12, V12, T13, N13, V13, T14, N14, V14, T15, N15, V15, T16, N16, V16> create(
//            @NotNull VertexLayoutStruct16<E1, N1, T1, V1, E2, N2, T2, V2, E3, N3, T3, V3, E4, N4, T4, V4, E5, N5, T5, V5, E6, N6, T6, V6, E7, N7, T7, V7, E8, N8, T8, V8, E9, N9, T9, V9, E10, N10, T10, V10, E11, N11, T11, V11, E12, N12, T12, V12, E13, N13, T13, V13, E14, N14, T14, V14, E15, N15, T15, V15, E16, N16, T16, V16> vertexLayout,
//            int numVertices, @NotNull EnumSet<BGFX_BUFFER> flags){
//        Assertions.requirePositive(numVertices);
//        final short handle = bgfx_create_dynamic_vertex_buffer(numVertices, vertexLayout.get(), (int) BGFX_BUFFER.flags(flags));
//        final short layoutHandle = bgfx_create_vertex_layout(vertexLayout.get());
//        return new TypedDynamicVertexBuffer16<>(handle, layoutHandle, numVertices);
//    }
//
//    public static <E1 extends BgfxAttrib, T1 extends BgfxAttribType, N1 extends Num, V1 extends Vec<N1, T1>,
//            E2 extends BgfxAttrib, T2 extends BgfxAttribType, N2 extends Num, V2 extends Vec<N2, T2>,
//            E3 extends BgfxAttrib, T3 extends BgfxAttribType, N3 extends Num, V3 extends Vec<N3, T3>,
//            E4 extends BgfxAttrib, T4 extends BgfxAttribType, N4 extends Num, V4 extends Vec<N4, T4>,
//            E5 extends BgfxAttrib, T5 extends BgfxAttribType, N5 extends Num, V5 extends Vec<N5, T5>,
//            E6 extends BgfxAttrib, T6 extends BgfxAttribType, N6 extends Num, V6 extends Vec<N6, T6>,
//            E7 extends BgfxAttrib, T7 extends BgfxAttribType, N7 extends Num, V7 extends Vec<N7, T7>,
//            E8 extends BgfxAttrib, T8 extends BgfxAttribType, N8 extends Num, V8 extends Vec<N8, T8>,
//            E9 extends BgfxAttrib, T9 extends BgfxAttribType, N9 extends Num, V9 extends Vec<N9, T9>,
//            E10 extends BgfxAttrib, T10 extends BgfxAttribType, N10 extends Num, V10 extends Vec<N10, T10>,
//            E11 extends BgfxAttrib, T11 extends BgfxAttribType, N11 extends Num, V11 extends Vec<N11, T11>,
//            E12 extends BgfxAttrib, T12 extends BgfxAttribType, N12 extends Num, V12 extends Vec<N12, T12>,
//            E13 extends BgfxAttrib, T13 extends BgfxAttribType, N13 extends Num, V13 extends Vec<N13, T13>,
//            E14 extends BgfxAttrib, T14 extends BgfxAttribType, N14 extends Num, V14 extends Vec<N14, T14>,
//            E15 extends BgfxAttrib, T15 extends BgfxAttribType, N15 extends Num, V15 extends Vec<N15, T15>,
//            E16 extends BgfxAttrib, T16 extends BgfxAttribType, N16 extends Num, V16 extends Vec<N16, T16>,
//            E17 extends BgfxAttrib, T17 extends BgfxAttribType, N17 extends Num, V17 extends Vec<N17, T17>>
//    TypedDynamicVertexBuffer17<T1, N1, V1, T2, N2, V2, T3, N3, V3, T4, N4, V4, T5, N5, V5, T6, N6, V6, T7, N7, V7, T8, N8, V8, T9, N9, V9, T10, N10, V10, T11, N11, V11, T12, N12, V12, T13, N13, V13, T14, N14, V14, T15, N15, V15, T16, N16, V16, T17, N17, V17> create(
//            @NotNull VertexLayoutStruct17<E1, N1, T1, V1, E2, N2, T2, V2, E3, N3, T3, V3, E4, N4, T4, V4, E5, N5, T5, V5, E6, N6, T6, V6, E7, N7, T7, V7, E8, N8, T8, V8, E9, N9, T9, V9, E10, N10, T10, V10, E11, N11, T11, V11, E12, N12, T12, V12, E13, N13, T13, V13, E14, N14, T14, V14, E15, N15, T15, V15, E16, N16, T16, V16, E17, N17, T17, V17> vertexLayout,
//            int numVertices, @NotNull EnumSet<BGFX_BUFFER> flags){
//        Assertions.requirePositive(numVertices);
//        final short handle = bgfx_create_dynamic_vertex_buffer(numVertices, vertexLayout.get(), (int) BGFX_BUFFER.flags(flags));
//        final short layoutHandle = bgfx_create_vertex_layout(vertexLayout.get());
//        return new TypedDynamicVertexBuffer17<>(handle, layoutHandle, numVertices);
//    }
//
//    public static <E1 extends BgfxAttrib, T1 extends BgfxAttribType, N1 extends Num, V1 extends Vec<N1, T1>,
//            E2 extends BgfxAttrib, T2 extends BgfxAttribType, N2 extends Num, V2 extends Vec<N2, T2>,
//            E3 extends BgfxAttrib, T3 extends BgfxAttribType, N3 extends Num, V3 extends Vec<N3, T3>,
//            E4 extends BgfxAttrib, T4 extends BgfxAttribType, N4 extends Num, V4 extends Vec<N4, T4>,
//            E5 extends BgfxAttrib, T5 extends BgfxAttribType, N5 extends Num, V5 extends Vec<N5, T5>,
//            E6 extends BgfxAttrib, T6 extends BgfxAttribType, N6 extends Num, V6 extends Vec<N6, T6>,
//            E7 extends BgfxAttrib, T7 extends BgfxAttribType, N7 extends Num, V7 extends Vec<N7, T7>,
//            E8 extends BgfxAttrib, T8 extends BgfxAttribType, N8 extends Num, V8 extends Vec<N8, T8>,
//            E9 extends BgfxAttrib, T9 extends BgfxAttribType, N9 extends Num, V9 extends Vec<N9, T9>,
//            E10 extends BgfxAttrib, T10 extends BgfxAttribType, N10 extends Num, V10 extends Vec<N10, T10>,
//            E11 extends BgfxAttrib, T11 extends BgfxAttribType, N11 extends Num, V11 extends Vec<N11, T11>,
//            E12 extends BgfxAttrib, T12 extends BgfxAttribType, N12 extends Num, V12 extends Vec<N12, T12>,
//            E13 extends BgfxAttrib, T13 extends BgfxAttribType, N13 extends Num, V13 extends Vec<N13, T13>,
//            E14 extends BgfxAttrib, T14 extends BgfxAttribType, N14 extends Num, V14 extends Vec<N14, T14>,
//            E15 extends BgfxAttrib, T15 extends BgfxAttribType, N15 extends Num, V15 extends Vec<N15, T15>,
//            E16 extends BgfxAttrib, T16 extends BgfxAttribType, N16 extends Num, V16 extends Vec<N16, T16>,
//            E17 extends BgfxAttrib, T17 extends BgfxAttribType, N17 extends Num, V17 extends Vec<N17, T17>,
//            E18 extends BgfxAttrib, T18 extends BgfxAttribType, N18 extends Num, V18 extends Vec<N18, T18>>
//    TypedDynamicVertexBuffer18<T1, N1, V1, T2, N2, V2, T3, N3, V3, T4, N4, V4, T5, N5, V5, T6, N6, V6, T7, N7, V7, T8, N8, V8, T9, N9, V9, T10, N10, V10, T11, N11, V11, T12, N12, V12, T13, N13, V13, T14, N14, V14, T15, N15, V15, T16, N16, V16, T17, N17, V17, T18, N18, V18> create(
//            @NotNull VertexLayoutStruct18<E1, N1, T1, V1, E2, N2, T2, V2, E3, N3, T3, V3, E4, N4, T4, V4, E5, N5, T5, V5, E6, N6, T6, V6, E7, N7, T7, V7, E8, N8, T8, V8, E9, N9, T9, V9, E10, N10, T10, V10, E11, N11, T11, V11, E12, N12, T12, V12, E13, N13, T13, V13, E14, N14, T14, V14, E15, N15, T15, V15, E16, N16, T16, V16, E17, N17, T17, V17, E18, N18, T18, V18> vertexLayout,
//            int numVertices, @NotNull EnumSet<BGFX_BUFFER> flags){
//        Assertions.requirePositive(numVertices);
//        final short handle = bgfx_create_dynamic_vertex_buffer(numVertices, vertexLayout.get(), (int) BGFX_BUFFER.flags(flags));
//        final short layoutHandle = bgfx_create_vertex_layout(vertexLayout.get());
//        return new TypedDynamicVertexBuffer18<>(handle, layoutHandle, numVertices);
//    }

    // helper methods

    static <T extends Vertex> void assertValidVertexPack(int numVertices, int startVertex, T[] vertices) {
        Assertions.requireNonNegative(startVertex);
        final int endIndex = startVertex + vertices.length - 1;
        Assertions.requireLessThan(endIndex, numVertices);
    }

    static <T extends Vertex> void vertexPack(short handle, int startVertex, T[] vertices) {
        final int numVecsPerTuple = vertices[0].size(); // equal to cardinality of type
        final int vertexCount = vertices.length;


        // get size of one tuple, we know from contract that T is a tuple holding Vecs
        final var sample = vertices[0];
        int requiredBytes = 0;
        for (int i=0; i < numVecsPerTuple; i++) {
            for (Object o : sample.array()) {
                final Vec<?, ?> vec = (Vec<?, ?>) o; // we know from public API that T is a tuple holding Vecs
                requiredBytes += computeBytes(vec.type().representedType(), vec.number(), vertexCount);
            }
        }

        try (final MemoryStack stack = MemoryStack.stackPush()) {
            // todo, some might be too big to store on stack!
            final ByteBuffer bytes = stack.malloc(requiredBytes);
            final int vertexStrideBytes = computeStrideBytes(vertices);

            for (T tuple : vertices) {
                for (Object o : tuple.array()) {
                    final Vec<?, ?> vec = (Vec<?, ?>) o; // guaranteed by public api
                    vec.put(bytes);
                }
            }

            final BGFXMemory memory = bgfx_copy(bytes);
            bgfx_update_dynamic_vertex_buffer(handle, startVertex, memory);
        }
    }

    static <T extends Vertex> int computeStrideBytes(T[] tuples) {
        int total = 0;
        final T sample = tuples[0];
        for (Object o : sample.array()) {
            final Vec<?, ?> vec = (Vec<?, ?>) o; // known to be vector by public api
            final BGFX_ATTRIB_TYPE type = vec.type().representedType();
            final Num num = vec.number();
            switch (type) {
                case UINT8:
                    total += num.value();
                    break;
                case UINT10:
                    total += 4;
                    break;
                case HALF:
                case INT16:
                    total += num.value() * 2; // two bytes (floating point or a short)
                    break;
                case FLOAT:
                    total += num.value() * 4; // four bytes (float)
                    break;
                default:
                    throw new IllegalStateException("Pattern matching must be exhaustive");
            }
        }
        return total;
    }

    static int computeBytes(BGFX_ATTRIB_TYPE type, Num num, int arraySize) {
        switch (type) {
            case UINT8:
                return num.value() * arraySize;
            case UINT10:
                // UINT10 xyzw where each gets 10/10/10/2 bits respectively.
                // By the Khronos spec, the Num will be 3 or 4 if the type is UINT10, and if Num = 3 then
                //  those two bits just go unused.

                // Thus, we have 4 bytes (32 bits) per array slot regardless of size of each array element
                // and we just multiply the array size by 4 bytes
                return arraySize * 4;
            case HALF:
            case INT16:
                return num.value() * arraySize * 2; // two bytes (floating point or a short)
            case FLOAT:
                return num.value() * arraySize * 4; // four bytes (float)
            default:
                throw new IllegalStateException("Pattern matching must be exhaustive");
        }
    }

}