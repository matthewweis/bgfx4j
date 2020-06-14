package com.bariumhoof.bgfx4j.layout;

import org.jetbrains.annotations.Nullable;

public class VertexLayoutStructBuilder13<E1 extends BgfxAttrib, V1 extends Vec<?,?>,
        E2 extends BgfxAttrib, V2 extends Vec<?,?>, E3 extends BgfxAttrib, V3 extends Vec<?,?>,
        E4 extends BgfxAttrib, V4 extends Vec<?,?>, E5 extends BgfxAttrib, V5 extends Vec<?,?>,
        E6 extends BgfxAttrib, V6 extends Vec<?,?>, E7 extends BgfxAttrib, V7 extends Vec<?,?>,
        E8 extends BgfxAttrib, V8 extends Vec<?,?>, E9 extends BgfxAttrib, V9 extends Vec<?,?>,
        E10 extends BgfxAttrib, V10 extends Vec<?,?>, E11 extends BgfxAttrib, V11 extends Vec<?,?>,
        E12 extends BgfxAttrib, V12 extends Vec<?,?>, E13 extends BgfxAttrib, V13 extends Vec<?,?>> extends VertexLayoutStructBuilder<E13, V13> {
    private final VertexLayoutStructBuilder12<E1, V1, E2, V2, E3, V3, E4, V4, E5, V5, E6, V6, E7, V7, E8, V8, E9, V9, E10, V10, E11, V11, E12, V12> last;

    protected VertexLayoutStructBuilder13(VertexLayoutStructBuilder12<E1, V1, E2, V2, E3, V3, E4, V4, E5, V5, E6, V6, E7, V7, E8, V8, E9, V9, E10, V10, E11, V11, E12, V12> last, E13 attrib, V13 vec, boolean normalized, boolean asInt) {
        super(attrib, vec, normalized, asInt);
        this.last = last;
    }

//    public VertexLayoutStruct13<E1, V1, E2, V2, E3, V3, E4, V4, E5, V5, E6, V6, E7, V7, E8, V8, E9, V9, E10, V10, E11, V11, E12, V12, E13, V13> build() {
//        return build(Capabilities.getRendererType());
//    }
//
//    public VertexLayoutStruct13<E1, V1, E2, V2, E3, V3, E4, V4, E5, V5, E6, V6, E7, V7, E8, V8, E9, V9, E10, V10, E11, V11, E12, V12, E13, V13> build(@NotNull BGFX_RENDERER_TYPE rendererType) {
//        final VertexLayoutStructBuilder<?, ?>[] builders = createBuildersArray(13, this);
//        final BGFXVertexLayout layout = createLayout(rendererType, builders);
//        return new VertexLayoutStruct13<>(layout);
//    }

    @Override
    @Nullable VertexLayoutStructBuilder<E12, V12> previousBuilder() {
        return last;
    }

//    public <E14 extends BgfxAttrib> VertexLayoutStructBuilder14<E1, V1, E2, V2, E3, V3, E4, V4, E5, V5, E6, V6, E7, V7, E8, V8, E9, V9, E10, V10, E11, V11, E12, V12, E13, V13, E14, Num.ONE, BgfxAttribType.UINT8, Vec.UINT8_Vec1> thenUse(E14 attrib, Num.ONE num, BgfxAttribType.UINT8 type, boolean normalized, boolean asInt) {
//        return new VertexLayoutStructBuilder14<>(this, attrib, vec, normalized, asInt);
//    }
//    public <E14 extends BgfxAttrib> VertexLayoutStructBuilder14<E1, V1, E2, V2, E3, V3, E4, V4, E5, V5, E6, V6, E7, V7, E8, V8, E9, V9, E10, V10, E11, V11, E12, V12, E13, V13, E14, Num.TWO, BgfxAttribType.UINT8, Vec.UINT8_Vec2> thenUse(E14 attrib, Num.TWO num, BgfxAttribType.UINT8 type, boolean normalized, boolean asInt) {
//        return new VertexLayoutStructBuilder14<>(this, attrib, vec, normalized, asInt);
//    }
//    public <E14 extends BgfxAttrib> VertexLayoutStructBuilder14<E1, V1, E2, V2, E3, V3, E4, V4, E5, V5, E6, V6, E7, V7, E8, V8, E9, V9, E10, V10, E11, V11, E12, V12, E13, V13, E14, Num.THREE, BgfxAttribType.UINT8, Vec.UINT8_Vec3> thenUse(E14 attrib, Num.THREE num, BgfxAttribType.UINT8 type, boolean normalized, boolean asInt) {
//        return new VertexLayoutStructBuilder14<>(this, attrib, vec, normalized, asInt);
//    }
//    public <E14 extends BgfxAttrib> VertexLayoutStructBuilder14<E1, V1, E2, V2, E3, V3, E4, V4, E5, V5, E6, V6, E7, V7, E8, V8, E9, V9, E10, V10, E11, V11, E12, V12, E13, V13, E14, Num.FOUR, BgfxAttribType.UINT8, Vec.UINT8_Vec4> thenUse(E14 attrib, Num.FOUR num, BgfxAttribType.UINT8 type, boolean normalized, boolean asInt) {
//        return new VertexLayoutStructBuilder14<>(this, attrib, vec, normalized, asInt);
//    }
//
//    public <E14 extends BgfxAttrib> VertexLayoutStructBuilder14<E1, V1, E2, V2, E3, V3, E4, V4, E5, V5, E6, V6, E7, V7, E8, V8, E9, V9, E10, V10, E11, V11, E12, V12, E13, V13, E14, Num.THREE, BgfxAttribType.UINT10, Vec.UINT10_Vec3> thenUse(E14 attrib, Num.THREE num, BgfxAttribType.UINT10 type, boolean normalized, boolean asInt) {
//        return new VertexLayoutStructBuilder14<>(this, attrib, vec, normalized, asInt);
//    }
//    public <E14 extends BgfxAttrib> VertexLayoutStructBuilder14<E1, V1, E2, V2, E3, V3, E4, V4, E5, V5, E6, V6, E7, V7, E8, V8, E9, V9, E10, V10, E11, V11, E12, V12, E13, V13, E14, Num.FOUR, BgfxAttribType.UINT10, Vec.UINT10_Vec4> thenUse(E14 attrib, Num.FOUR num, BgfxAttribType.UINT10 type, boolean normalized, boolean asInt) {
//        return new VertexLayoutStructBuilder14<>(this, attrib, vec, normalized, asInt);
//    }
//
//    public <E14 extends BgfxAttrib> VertexLayoutStructBuilder14<E1, V1, E2, V2, E3, V3, E4, V4, E5, V5, E6, V6, E7, V7, E8, V8, E9, V9, E10, V10, E11, V11, E12, V12, E13, V13, E14, Num.ONE, BgfxAttribType.INT16, Vec.INT16_Vec1> thenUse(E14 attrib, Num.ONE num, BgfxAttribType.INT16 type, boolean normalized, boolean asInt) {
//        return new VertexLayoutStructBuilder14<>(this, attrib, vec, normalized, asInt);
//    }
//    public <E14 extends BgfxAttrib> VertexLayoutStructBuilder14<E1, V1, E2, V2, E3, V3, E4, V4, E5, V5, E6, V6, E7, V7, E8, V8, E9, V9, E10, V10, E11, V11, E12, V12, E13, V13, E14, Num.TWO, BgfxAttribType.INT16, Vec.INT16_Vec2> thenUse(E14 attrib, Num.TWO num, BgfxAttribType.INT16 type, boolean normalized, boolean asInt) {
//        return new VertexLayoutStructBuilder14<>(this, attrib, vec, normalized, asInt);
//    }
//    public <E14 extends BgfxAttrib> VertexLayoutStructBuilder14<E1, V1, E2, V2, E3, V3, E4, V4, E5, V5, E6, V6, E7, V7, E8, V8, E9, V9, E10, V10, E11, V11, E12, V12, E13, V13, E14, Num.THREE, BgfxAttribType.INT16, Vec.INT16_Vec3> thenUse(E14 attrib, Num.THREE num, BgfxAttribType.INT16 type, boolean normalized, boolean asInt) {
//        return new VertexLayoutStructBuilder14<>(this, attrib, vec, normalized, asInt);
//    }
//    public <E14 extends BgfxAttrib> VertexLayoutStructBuilder14<E1, V1, E2, V2, E3, V3, E4, V4, E5, V5, E6, V6, E7, V7, E8, V8, E9, V9, E10, V10, E11, V11, E12, V12, E13, V13, E14, Num.FOUR, BgfxAttribType.INT16, Vec.INT16_Vec4> thenUse(E14 attrib, Num.FOUR num, BgfxAttribType.INT16 type, boolean normalized, boolean asInt) {
//        return new VertexLayoutStructBuilder14<>(this, attrib, vec, normalized, asInt);
//    }
//
//    public <E14 extends BgfxAttrib> VertexLayoutStructBuilder14<E1, V1, E2, V2, E3, V3, E4, V4, E5, V5, E6, V6, E7, V7, E8, V8, E9, V9, E10, V10, E11, V11, E12, V12, E13, V13, E14, Num.ONE, BgfxAttribType.HALF, Vec.HALF_Vec1> thenUse(E14 attrib, Num.ONE num, BgfxAttribType.HALF type, boolean normalized, boolean asInt) {
//        return new VertexLayoutStructBuilder14<>(this, attrib, vec, normalized, asInt);
//    }
//    public <E14 extends BgfxAttrib> VertexLayoutStructBuilder14<E1, V1, E2, V2, E3, V3, E4, V4, E5, V5, E6, V6, E7, V7, E8, V8, E9, V9, E10, V10, E11, V11, E12, V12, E13, V13, E14, Num.TWO, BgfxAttribType.HALF, Vec.HALF_Vec2> thenUse(E14 attrib, Num.TWO num, BgfxAttribType.HALF type, boolean normalized, boolean asInt) {
//        return new VertexLayoutStructBuilder14<>(this, attrib, vec, normalized, asInt);
//    }
//    public <E14 extends BgfxAttrib> VertexLayoutStructBuilder14<E1, V1, E2, V2, E3, V3, E4, V4, E5, V5, E6, V6, E7, V7, E8, V8, E9, V9, E10, V10, E11, V11, E12, V12, E13, V13, E14, Num.THREE, BgfxAttribType.HALF, Vec.HALF_Vec3> thenUse(E14 attrib, Num.THREE num, BgfxAttribType.HALF type, boolean normalized, boolean asInt) {
//        return new VertexLayoutStructBuilder14<>(this, attrib, vec, normalized, asInt);
//    }
//    public <E14 extends BgfxAttrib> VertexLayoutStructBuilder14<E1, V1, E2, V2, E3, V3, E4, V4, E5, V5, E6, V6, E7, V7, E8, V8, E9, V9, E10, V10, E11, V11, E12, V12, E13, V13, E14, Num.FOUR, BgfxAttribType.HALF, Vec.HALF_Vec4> thenUse(E14 attrib, Num.FOUR num, BgfxAttribType.HALF type, boolean normalized, boolean asInt) {
//        return new VertexLayoutStructBuilder14<>(this, attrib, vec, normalized, asInt);
//    }
//
//    public <E14 extends BgfxAttrib> VertexLayoutStructBuilder14<E1, V1, E2, V2, E3, V3, E4, V4, E5, V5, E6, V6, E7, V7, E8, V8, E9, V9, E10, V10, E11, V11, E12, V12, E13, V13, E14, Num.ONE, BgfxAttribType.FLOAT, Vec.FLOAT_Vec1> thenUse(E14 attrib, Num.ONE num, BgfxAttribType.FLOAT type, boolean normalized, boolean asInt) {
//        return new VertexLayoutStructBuilder14<>(this, attrib, vec, normalized, asInt);
//    }
//    public <E14 extends BgfxAttrib> VertexLayoutStructBuilder14<E1, V1, E2, V2, E3, V3, E4, V4, E5, V5, E6, V6, E7, V7, E8, V8, E9, V9, E10, V10, E11, V11, E12, V12, E13, V13, E14, Num.TWO, BgfxAttribType.FLOAT, Vec.FLOAT_Vec2> thenUse(E14 attrib, Num.TWO num, BgfxAttribType.FLOAT type, boolean normalized, boolean asInt) {
//        return new VertexLayoutStructBuilder14<>(this, attrib, vec, normalized, asInt);
//    }
//    public <E14 extends BgfxAttrib> VertexLayoutStructBuilder14<E1, V1, E2, V2, E3, V3, E4, V4, E5, V5, E6, V6, E7, V7, E8, V8, E9, V9, E10, V10, E11, V11, E12, V12, E13, V13, E14, Num.THREE, BgfxAttribType.FLOAT, Vec.FLOAT_Vec3> thenUse(E14 attrib, Num.THREE num, BgfxAttribType.FLOAT type, boolean normalized, boolean asInt) {
//        return new VertexLayoutStructBuilder14<>(this, attrib, vec, normalized, asInt);
//    }
//    public <E14 extends BgfxAttrib> VertexLayoutStructBuilder14<E1, V1, E2, V2, E3, V3, E4, V4, E5, V5, E6, V6, E7, V7, E8, V8, E9, V9, E10, V10, E11, V11, E12, V12, E13, V13, E14, Num.FOUR, BgfxAttribType.FLOAT, Vec.FLOAT_Vec4> thenUse(E14 attrib, Num.FOUR num, BgfxAttribType.FLOAT type, boolean normalized, boolean asInt) {
//        return new VertexLayoutStructBuilder14<>(this, attrib, vec, normalized, asInt);
//    }
}
