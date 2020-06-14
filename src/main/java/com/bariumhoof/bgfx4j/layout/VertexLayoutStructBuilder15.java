package com.bariumhoof.bgfx4j.layout;

import org.jetbrains.annotations.Nullable;

public class VertexLayoutStructBuilder15<E1 extends BgfxAttrib, V1 extends Vec<?,?>,
        E2 extends BgfxAttrib, V2 extends Vec<?,?>, E3 extends BgfxAttrib, V3 extends Vec<?,?>,
        E4 extends BgfxAttrib, V4 extends Vec<?,?>, E5 extends BgfxAttrib, V5 extends Vec<?,?>,
        E6 extends BgfxAttrib, V6 extends Vec<?,?>, E7 extends BgfxAttrib, V7 extends Vec<?,?>,
        E8 extends BgfxAttrib, V8 extends Vec<?,?>, E9 extends BgfxAttrib, V9 extends Vec<?,?>,
        E10 extends BgfxAttrib, V10 extends Vec<?,?>, E11 extends BgfxAttrib, V11 extends Vec<?,?>,
        E12 extends BgfxAttrib, V12 extends Vec<?,?>, E13 extends BgfxAttrib, V13 extends Vec<?,?>,
        E14 extends BgfxAttrib, V14 extends Vec<?,?>, E15 extends BgfxAttrib, V15 extends Vec<?,?>> extends VertexLayoutStructBuilder<E15, V15> {
    private final VertexLayoutStructBuilder14<E1, V1, E2, V2, E3, V3, E4, V4, E5, V5, E6, V6, E7, V7, E8, V8, E9, V9, E10, V10, E11, V11, E12, V12, E13, V13, E14, V14> last;

    protected VertexLayoutStructBuilder15(VertexLayoutStructBuilder14<E1, V1, E2, V2, E3, V3, E4, V4, E5, V5, E6, V6, E7, V7, E8, V8, E9, V9, E10, V10, E11, V11, E12, V12, E13, V13, E14, V14> last, E15 attrib, V15 vec, boolean normalized, boolean asInt) {
        super(attrib, vec, normalized, asInt);
        this.last = last;
    }

//    public VertexLayoutStruct15<E1, V1, E2, V2, E3, V3, E4, V4, E5, V5, E6, V6, E7, V7, E8, V8, E9, V9, E10, V10, E11, V11, E12, V12, E13, V13, E14, V14, E15, V15> build() {
//        return build(Capabilities.getRendererType());
//    }
//
//    public VertexLayoutStruct15<E1, V1, E2, V2, E3, V3, E4, V4, E5, V5, E6, V6, E7, V7, E8, V8, E9, V9, E10, V10, E11, V11, E12, V12, E13, V13, E14, V14, E15, V15> build(@NotNull BGFX_RENDERER_TYPE rendererType) {
//        final VertexLayoutStructBuilder<?, ?>[] builders = createBuildersArray(15, this);
//        final BGFXVertexLayout layout = createLayout(rendererType, builders);
//        return new VertexLayoutStruct15<>(layout);
//    }

    @Override
    @Nullable VertexLayoutStructBuilder<E14, V14> previousBuilder() {
        return last;
    }

//    public <E16 extends BgfxAttrib> VertexLayoutStructBuilder16<E1, V1, E2, V2, E3, V3, E4, V4, E5, V5, E6, V6, E7, V7, E8, V8, E9, V9, E10, V10, E11, V11, E12, V12, E13, V13, E14, V14, E15, V15, E16, Num.ONE, BgfxAttribType.UINT8, Vec.UINT8_Vec1> thenUse(E16 attrib, Num.ONE num, BgfxAttribType.UINT8 type, boolean normalized, boolean asInt) {
//        return new VertexLayoutStructBuilder16<>(this, attrib, vec, normalized, asInt);
//    }
//    public <E16 extends BgfxAttrib> VertexLayoutStructBuilder16<E1, V1, E2, V2, E3, V3, E4, V4, E5, V5, E6, V6, E7, V7, E8, V8, E9, V9, E10, V10, E11, V11, E12, V12, E13, V13, E14, V14, E15, V15, E16, Num.TWO, BgfxAttribType.UINT8, Vec.UINT8_Vec2> thenUse(E16 attrib, Num.TWO num, BgfxAttribType.UINT8 type, boolean normalized, boolean asInt) {
//        return new VertexLayoutStructBuilder16<>(this, attrib, vec, normalized, asInt);
//    }
//    public <E16 extends BgfxAttrib> VertexLayoutStructBuilder16<E1, V1, E2, V2, E3, V3, E4, V4, E5, V5, E6, V6, E7, V7, E8, V8, E9, V9, E10, V10, E11, V11, E12, V12, E13, V13, E14, V14, E15, V15, E16, Num.THREE, BgfxAttribType.UINT8, Vec.UINT8_Vec3> thenUse(E16 attrib, Num.THREE num, BgfxAttribType.UINT8 type, boolean normalized, boolean asInt) {
//        return new VertexLayoutStructBuilder16<>(this, attrib, vec, normalized, asInt);
//    }
//    public <E16 extends BgfxAttrib> VertexLayoutStructBuilder16<E1, V1, E2, V2, E3, V3, E4, V4, E5, V5, E6, V6, E7, V7, E8, V8, E9, V9, E10, V10, E11, V11, E12, V12, E13, V13, E14, V14, E15, V15, E16, Num.FOUR, BgfxAttribType.UINT8, Vec.UINT8_Vec4> thenUse(E16 attrib, Num.FOUR num, BgfxAttribType.UINT8 type, boolean normalized, boolean asInt) {
//        return new VertexLayoutStructBuilder16<>(this, attrib, vec, normalized, asInt);
//    }
//
//    public <E16 extends BgfxAttrib> VertexLayoutStructBuilder16<E1, V1, E2, V2, E3, V3, E4, V4, E5, V5, E6, V6, E7, V7, E8, V8, E9, V9, E10, V10, E11, V11, E12, V12, E13, V13, E14, V14, E15, V15, E16, Num.THREE, BgfxAttribType.UINT10, Vec.UINT10_Vec3> thenUse(E16 attrib, Num.THREE num, BgfxAttribType.UINT10 type, boolean normalized, boolean asInt) {
//        return new VertexLayoutStructBuilder16<>(this, attrib, vec, normalized, asInt);
//    }
//    public <E16 extends BgfxAttrib> VertexLayoutStructBuilder16<E1, V1, E2, V2, E3, V3, E4, V4, E5, V5, E6, V6, E7, V7, E8, V8, E9, V9, E10, V10, E11, V11, E12, V12, E13, V13, E14, V14, E15, V15, E16, Num.FOUR, BgfxAttribType.UINT10, Vec.UINT10_Vec4> thenUse(E16 attrib, Num.FOUR num, BgfxAttribType.UINT10 type, boolean normalized, boolean asInt) {
//        return new VertexLayoutStructBuilder16<>(this, attrib, vec, normalized, asInt);
//    }
//
//    public <E16 extends BgfxAttrib> VertexLayoutStructBuilder16<E1, V1, E2, V2, E3, V3, E4, V4, E5, V5, E6, V6, E7, V7, E8, V8, E9, V9, E10, V10, E11, V11, E12, V12, E13, V13, E14, V14, E15, V15, E16, Num.ONE, BgfxAttribType.INT16, Vec.INT16_Vec1> thenUse(E16 attrib, Num.ONE num, BgfxAttribType.INT16 type, boolean normalized, boolean asInt) {
//        return new VertexLayoutStructBuilder16<>(this, attrib, vec, normalized, asInt);
//    }
//    public <E16 extends BgfxAttrib> VertexLayoutStructBuilder16<E1, V1, E2, V2, E3, V3, E4, V4, E5, V5, E6, V6, E7, V7, E8, V8, E9, V9, E10, V10, E11, V11, E12, V12, E13, V13, E14, V14, E15, V15, E16, Num.TWO, BgfxAttribType.INT16, Vec.INT16_Vec2> thenUse(E16 attrib, Num.TWO num, BgfxAttribType.INT16 type, boolean normalized, boolean asInt) {
//        return new VertexLayoutStructBuilder16<>(this, attrib, vec, normalized, asInt);
//    }
//    public <E16 extends BgfxAttrib> VertexLayoutStructBuilder16<E1, V1, E2, V2, E3, V3, E4, V4, E5, V5, E6, V6, E7, V7, E8, V8, E9, V9, E10, V10, E11, V11, E12, V12, E13, V13, E14, V14, E15, V15, E16, Num.THREE, BgfxAttribType.INT16, Vec.INT16_Vec3> thenUse(E16 attrib, Num.THREE num, BgfxAttribType.INT16 type, boolean normalized, boolean asInt) {
//        return new VertexLayoutStructBuilder16<>(this, attrib, vec, normalized, asInt);
//    }
//    public <E16 extends BgfxAttrib> VertexLayoutStructBuilder16<E1, V1, E2, V2, E3, V3, E4, V4, E5, V5, E6, V6, E7, V7, E8, V8, E9, V9, E10, V10, E11, V11, E12, V12, E13, V13, E14, V14, E15, V15, E16, Num.FOUR, BgfxAttribType.INT16, Vec.INT16_Vec4> thenUse(E16 attrib, Num.FOUR num, BgfxAttribType.INT16 type, boolean normalized, boolean asInt) {
//        return new VertexLayoutStructBuilder16<>(this, attrib, vec, normalized, asInt);
//    }
//
//    public <E16 extends BgfxAttrib> VertexLayoutStructBuilder16<E1, V1, E2, V2, E3, V3, E4, V4, E5, V5, E6, V6, E7, V7, E8, V8, E9, V9, E10, V10, E11, V11, E12, V12, E13, V13, E14, V14, E15, V15, E16, Num.ONE, BgfxAttribType.HALF, Vec.HALF_Vec1> thenUse(E16 attrib, Num.ONE num, BgfxAttribType.HALF type, boolean normalized, boolean asInt) {
//        return new VertexLayoutStructBuilder16<>(this, attrib, vec, normalized, asInt);
//    }
//    public <E16 extends BgfxAttrib> VertexLayoutStructBuilder16<E1, V1, E2, V2, E3, V3, E4, V4, E5, V5, E6, V6, E7, V7, E8, V8, E9, V9, E10, V10, E11, V11, E12, V12, E13, V13, E14, V14, E15, V15, E16, Num.TWO, BgfxAttribType.HALF, Vec.HALF_Vec2> thenUse(E16 attrib, Num.TWO num, BgfxAttribType.HALF type, boolean normalized, boolean asInt) {
//        return new VertexLayoutStructBuilder16<>(this, attrib, vec, normalized, asInt);
//    }
//    public <E16 extends BgfxAttrib> VertexLayoutStructBuilder16<E1, V1, E2, V2, E3, V3, E4, V4, E5, V5, E6, V6, E7, V7, E8, V8, E9, V9, E10, V10, E11, V11, E12, V12, E13, V13, E14, V14, E15, V15, E16, Num.THREE, BgfxAttribType.HALF, Vec.HALF_Vec3> thenUse(E16 attrib, Num.THREE num, BgfxAttribType.HALF type, boolean normalized, boolean asInt) {
//        return new VertexLayoutStructBuilder16<>(this, attrib, vec, normalized, asInt);
//    }
//    public <E16 extends BgfxAttrib> VertexLayoutStructBuilder16<E1, V1, E2, V2, E3, V3, E4, V4, E5, V5, E6, V6, E7, V7, E8, V8, E9, V9, E10, V10, E11, V11, E12, V12, E13, V13, E14, V14, E15, V15, E16, Num.FOUR, BgfxAttribType.HALF, Vec.HALF_Vec4> thenUse(E16 attrib, Num.FOUR num, BgfxAttribType.HALF type, boolean normalized, boolean asInt) {
//        return new VertexLayoutStructBuilder16<>(this, attrib, vec, normalized, asInt);
//    }
//
//    public <E16 extends BgfxAttrib> VertexLayoutStructBuilder16<E1, V1, E2, V2, E3, V3, E4, V4, E5, V5, E6, V6, E7, V7, E8, V8, E9, V9, E10, V10, E11, V11, E12, V12, E13, V13, E14, V14, E15, V15, E16, Num.ONE, BgfxAttribType.FLOAT, Vec.FLOAT_Vec1> thenUse(E16 attrib, Num.ONE num, BgfxAttribType.FLOAT type, boolean normalized, boolean asInt) {
//        return new VertexLayoutStructBuilder16<>(this, attrib, vec, normalized, asInt);
//    }
//    public <E16 extends BgfxAttrib> VertexLayoutStructBuilder16<E1, V1, E2, V2, E3, V3, E4, V4, E5, V5, E6, V6, E7, V7, E8, V8, E9, V9, E10, V10, E11, V11, E12, V12, E13, V13, E14, V14, E15, V15, E16, Num.TWO, BgfxAttribType.FLOAT, Vec.FLOAT_Vec2> thenUse(E16 attrib, Num.TWO num, BgfxAttribType.FLOAT type, boolean normalized, boolean asInt) {
//        return new VertexLayoutStructBuilder16<>(this, attrib, vec, normalized, asInt);
//    }
//    public <E16 extends BgfxAttrib> VertexLayoutStructBuilder16<E1, V1, E2, V2, E3, V3, E4, V4, E5, V5, E6, V6, E7, V7, E8, V8, E9, V9, E10, V10, E11, V11, E12, V12, E13, V13, E14, V14, E15, V15, E16, Num.THREE, BgfxAttribType.FLOAT, Vec.FLOAT_Vec3> thenUse(E16 attrib, Num.THREE num, BgfxAttribType.FLOAT type, boolean normalized, boolean asInt) {
//        return new VertexLayoutStructBuilder16<>(this, attrib, vec, normalized, asInt);
//    }
//    public <E16 extends BgfxAttrib> VertexLayoutStructBuilder16<E1, V1, E2, V2, E3, V3, E4, V4, E5, V5, E6, V6, E7, V7, E8, V8, E9, V9, E10, V10, E11, V11, E12, V12, E13, V13, E14, V14, E15, V15, E16, Num.FOUR, BgfxAttribType.FLOAT, Vec.FLOAT_Vec4> thenUse(E16 attrib, Num.FOUR num, BgfxAttribType.FLOAT type, boolean normalized, boolean asInt) {
//        return new VertexLayoutStructBuilder16<>(this, attrib, vec, normalized, asInt);
//    }
}
