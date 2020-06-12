package com.bariumhoof.bgfx4j.layout;

import com.bariumhoof.Capabilities;
import com.bariumhoof.bgfx4j.enums.BGFX_RENDERER_TYPE;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.lwjgl.bgfx.BGFXVertexLayout;

public class VertexLayoutStructBuilder14<E1 extends BgfxAttrib, N1 extends Num, T1 extends BgfxAttribType, V1 extends Vec<N1, T1>,
        E2 extends BgfxAttrib, N2 extends Num, T2 extends BgfxAttribType, V2 extends Vec<N2, T2>, E3 extends BgfxAttrib, N3 extends Num, T3 extends BgfxAttribType, V3 extends Vec<N3, T3>,
        E4 extends BgfxAttrib, N4 extends Num, T4 extends BgfxAttribType, V4 extends Vec<N4, T4>, E5 extends BgfxAttrib, N5 extends Num, T5 extends BgfxAttribType, V5 extends Vec<N5, T5>,
        E6 extends BgfxAttrib, N6 extends Num, T6 extends BgfxAttribType, V6 extends Vec<N6, T6>, E7 extends BgfxAttrib, N7 extends Num, T7 extends BgfxAttribType, V7 extends Vec<N7, T7>,
        E8 extends BgfxAttrib, N8 extends Num, T8 extends BgfxAttribType, V8 extends Vec<N8, T8>, E9 extends BgfxAttrib, N9 extends Num, T9 extends BgfxAttribType, V9 extends Vec<N9, T9>,
        E10 extends BgfxAttrib, N10 extends Num, T10 extends BgfxAttribType, V10 extends Vec<N10, T10>, E11 extends BgfxAttrib, N11 extends Num, T11 extends BgfxAttribType, V11 extends Vec<N11, T11>,
        E12 extends BgfxAttrib, N12 extends Num, T12 extends BgfxAttribType, V12 extends Vec<N12, T12>, E13 extends BgfxAttrib, N13 extends Num, T13 extends BgfxAttribType, V13 extends Vec<N13, T13>,
        E14 extends BgfxAttrib, N14 extends Num, T14 extends BgfxAttribType, V14 extends Vec<N14, T14>> extends VertexLayoutStructBuilder<E14, N14, T14, V14> {
    private final VertexLayoutStructBuilder13<E1, N1, T1, V1, E2, N2, T2, V2, E3, N3, T3, V3, E4, N4, T4, V4, E5, N5, T5, V5, E6, N6, T6, V6, E7, N7, T7, V7, E8, N8, T8, V8, E9, N9, T9, V9, E10, N10, T10, V10, E11, N11, T11, V11, E12, N12, T12, V12, E13, N13, T13, V13> last;

    protected VertexLayoutStructBuilder14(VertexLayoutStructBuilder13<E1, N1, T1, V1, E2, N2, T2, V2, E3, N3, T3, V3, E4, N4, T4, V4, E5, N5, T5, V5, E6, N6, T6, V6, E7, N7, T7, V7, E8, N8, T8, V8, E9, N9, T9, V9, E10, N10, T10, V10, E11, N11, T11, V11, E12, N12, T12, V12, E13, N13, T13, V13> last, E14 attrib, N14 num, T14 type, boolean normalized, boolean asInt) {
        super(attrib, num, type, normalized, asInt);
        this.last = last;
    }

    public VertexLayoutStruct14<E1, N1, T1, V1, E2, N2, T2, V2, E3, N3, T3, V3, E4, N4, T4, V4, E5, N5, T5, V5, E6, N6, T6, V6, E7, N7, T7, V7, E8, N8, T8, V8, E9, N9, T9, V9, E10, N10, T10, V10, E11, N11, T11, V11, E12, N12, T12, V12, E13, N13, T13, V13, E14, N14, T14, V14> build() {
        return build(Capabilities.getRendererType());
    }

    public VertexLayoutStruct14<E1, N1, T1, V1, E2, N2, T2, V2, E3, N3, T3, V3, E4, N4, T4, V4, E5, N5, T5, V5, E6, N6, T6, V6, E7, N7, T7, V7, E8, N8, T8, V8, E9, N9, T9, V9, E10, N10, T10, V10, E11, N11, T11, V11, E12, N12, T12, V12, E13, N13, T13, V13, E14, N14, T14, V14> build(@NotNull BGFX_RENDERER_TYPE rendererType) {
        final VertexLayoutStructBuilder<?, ?, ?, ?>[] builders = createBuildersArray(14, this);
        final BGFXVertexLayout layout = createLayout(rendererType, builders);
        return new VertexLayoutStruct14<>(layout);
    }

    @Override
    @Nullable VertexLayoutStructBuilder<E13, N13, T13, V13> previousBuilder() {
        return last;
    }

    public <E15 extends BgfxAttrib> VertexLayoutStructBuilder15<E1, N1, T1, V1, E2, N2, T2, V2, E3, N3, T3, V3, E4, N4, T4, V4, E5, N5, T5, V5, E6, N6, T6, V6, E7, N7, T7, V7, E8, N8, T8, V8, E9, N9, T9, V9, E10, N10, T10, V10, E11, N11, T11, V11, E12, N12, T12, V12, E13, N13, T13, V13, E14, N14, T14, V14, E15, Num.ONE, BgfxAttribType.UINT8, Vec.UINT8_Vec1> thenUse(E15 attrib, Num.ONE num, BgfxAttribType.UINT8 type, boolean normalized, boolean asInt) {
        return new VertexLayoutStructBuilder15<>(this, attrib, num, type, normalized, asInt);
    }
    public <E15 extends BgfxAttrib> VertexLayoutStructBuilder15<E1, N1, T1, V1, E2, N2, T2, V2, E3, N3, T3, V3, E4, N4, T4, V4, E5, N5, T5, V5, E6, N6, T6, V6, E7, N7, T7, V7, E8, N8, T8, V8, E9, N9, T9, V9, E10, N10, T10, V10, E11, N11, T11, V11, E12, N12, T12, V12, E13, N13, T13, V13, E14, N14, T14, V14, E15, Num.TWO, BgfxAttribType.UINT8, Vec.UINT8_Vec2> thenUse(E15 attrib, Num.TWO num, BgfxAttribType.UINT8 type, boolean normalized, boolean asInt) {
        return new VertexLayoutStructBuilder15<>(this, attrib, num, type, normalized, asInt);
    }
    public <E15 extends BgfxAttrib> VertexLayoutStructBuilder15<E1, N1, T1, V1, E2, N2, T2, V2, E3, N3, T3, V3, E4, N4, T4, V4, E5, N5, T5, V5, E6, N6, T6, V6, E7, N7, T7, V7, E8, N8, T8, V8, E9, N9, T9, V9, E10, N10, T10, V10, E11, N11, T11, V11, E12, N12, T12, V12, E13, N13, T13, V13, E14, N14, T14, V14, E15, Num.THREE, BgfxAttribType.UINT8, Vec.UINT8_Vec3> thenUse(E15 attrib, Num.THREE num, BgfxAttribType.UINT8 type, boolean normalized, boolean asInt) {
        return new VertexLayoutStructBuilder15<>(this, attrib, num, type, normalized, asInt);
    }
    public <E15 extends BgfxAttrib> VertexLayoutStructBuilder15<E1, N1, T1, V1, E2, N2, T2, V2, E3, N3, T3, V3, E4, N4, T4, V4, E5, N5, T5, V5, E6, N6, T6, V6, E7, N7, T7, V7, E8, N8, T8, V8, E9, N9, T9, V9, E10, N10, T10, V10, E11, N11, T11, V11, E12, N12, T12, V12, E13, N13, T13, V13, E14, N14, T14, V14, E15, Num.FOUR, BgfxAttribType.UINT8, Vec.UINT8_Vec4> thenUse(E15 attrib, Num.FOUR num, BgfxAttribType.UINT8 type, boolean normalized, boolean asInt) {
        return new VertexLayoutStructBuilder15<>(this, attrib, num, type, normalized, asInt);
    }

    public <E15 extends BgfxAttrib> VertexLayoutStructBuilder15<E1, N1, T1, V1, E2, N2, T2, V2, E3, N3, T3, V3, E4, N4, T4, V4, E5, N5, T5, V5, E6, N6, T6, V6, E7, N7, T7, V7, E8, N8, T8, V8, E9, N9, T9, V9, E10, N10, T10, V10, E11, N11, T11, V11, E12, N12, T12, V12, E13, N13, T13, V13, E14, N14, T14, V14, E15, Num.THREE, BgfxAttribType.UINT10, Vec.UINT10_Vec3> thenUse(E15 attrib, Num.THREE num, BgfxAttribType.UINT10 type, boolean normalized, boolean asInt) {
        return new VertexLayoutStructBuilder15<>(this, attrib, num, type, normalized, asInt);
    }
    public <E15 extends BgfxAttrib> VertexLayoutStructBuilder15<E1, N1, T1, V1, E2, N2, T2, V2, E3, N3, T3, V3, E4, N4, T4, V4, E5, N5, T5, V5, E6, N6, T6, V6, E7, N7, T7, V7, E8, N8, T8, V8, E9, N9, T9, V9, E10, N10, T10, V10, E11, N11, T11, V11, E12, N12, T12, V12, E13, N13, T13, V13, E14, N14, T14, V14, E15, Num.FOUR, BgfxAttribType.UINT10, Vec.UINT10_Vec4> thenUse(E15 attrib, Num.FOUR num, BgfxAttribType.UINT10 type, boolean normalized, boolean asInt) {
        return new VertexLayoutStructBuilder15<>(this, attrib, num, type, normalized, asInt);
    }

    public <E15 extends BgfxAttrib> VertexLayoutStructBuilder15<E1, N1, T1, V1, E2, N2, T2, V2, E3, N3, T3, V3, E4, N4, T4, V4, E5, N5, T5, V5, E6, N6, T6, V6, E7, N7, T7, V7, E8, N8, T8, V8, E9, N9, T9, V9, E10, N10, T10, V10, E11, N11, T11, V11, E12, N12, T12, V12, E13, N13, T13, V13, E14, N14, T14, V14, E15, Num.ONE, BgfxAttribType.INT16, Vec.INT16_Vec1> thenUse(E15 attrib, Num.ONE num, BgfxAttribType.INT16 type, boolean normalized, boolean asInt) {
        return new VertexLayoutStructBuilder15<>(this, attrib, num, type, normalized, asInt);
    }
    public <E15 extends BgfxAttrib> VertexLayoutStructBuilder15<E1, N1, T1, V1, E2, N2, T2, V2, E3, N3, T3, V3, E4, N4, T4, V4, E5, N5, T5, V5, E6, N6, T6, V6, E7, N7, T7, V7, E8, N8, T8, V8, E9, N9, T9, V9, E10, N10, T10, V10, E11, N11, T11, V11, E12, N12, T12, V12, E13, N13, T13, V13, E14, N14, T14, V14, E15, Num.TWO, BgfxAttribType.INT16, Vec.INT16_Vec2> thenUse(E15 attrib, Num.TWO num, BgfxAttribType.INT16 type, boolean normalized, boolean asInt) {
        return new VertexLayoutStructBuilder15<>(this, attrib, num, type, normalized, asInt);
    }
    public <E15 extends BgfxAttrib> VertexLayoutStructBuilder15<E1, N1, T1, V1, E2, N2, T2, V2, E3, N3, T3, V3, E4, N4, T4, V4, E5, N5, T5, V5, E6, N6, T6, V6, E7, N7, T7, V7, E8, N8, T8, V8, E9, N9, T9, V9, E10, N10, T10, V10, E11, N11, T11, V11, E12, N12, T12, V12, E13, N13, T13, V13, E14, N14, T14, V14, E15, Num.THREE, BgfxAttribType.INT16, Vec.INT16_Vec3> thenUse(E15 attrib, Num.THREE num, BgfxAttribType.INT16 type, boolean normalized, boolean asInt) {
        return new VertexLayoutStructBuilder15<>(this, attrib, num, type, normalized, asInt);
    }
    public <E15 extends BgfxAttrib> VertexLayoutStructBuilder15<E1, N1, T1, V1, E2, N2, T2, V2, E3, N3, T3, V3, E4, N4, T4, V4, E5, N5, T5, V5, E6, N6, T6, V6, E7, N7, T7, V7, E8, N8, T8, V8, E9, N9, T9, V9, E10, N10, T10, V10, E11, N11, T11, V11, E12, N12, T12, V12, E13, N13, T13, V13, E14, N14, T14, V14, E15, Num.FOUR, BgfxAttribType.INT16, Vec.INT16_Vec4> thenUse(E15 attrib, Num.FOUR num, BgfxAttribType.INT16 type, boolean normalized, boolean asInt) {
        return new VertexLayoutStructBuilder15<>(this, attrib, num, type, normalized, asInt);
    }

    public <E15 extends BgfxAttrib> VertexLayoutStructBuilder15<E1, N1, T1, V1, E2, N2, T2, V2, E3, N3, T3, V3, E4, N4, T4, V4, E5, N5, T5, V5, E6, N6, T6, V6, E7, N7, T7, V7, E8, N8, T8, V8, E9, N9, T9, V9, E10, N10, T10, V10, E11, N11, T11, V11, E12, N12, T12, V12, E13, N13, T13, V13, E14, N14, T14, V14, E15, Num.ONE, BgfxAttribType.HALF, Vec.HALF_Vec1> thenUse(E15 attrib, Num.ONE num, BgfxAttribType.HALF type, boolean normalized, boolean asInt) {
        return new VertexLayoutStructBuilder15<>(this, attrib, num, type, normalized, asInt);
    }
    public <E15 extends BgfxAttrib> VertexLayoutStructBuilder15<E1, N1, T1, V1, E2, N2, T2, V2, E3, N3, T3, V3, E4, N4, T4, V4, E5, N5, T5, V5, E6, N6, T6, V6, E7, N7, T7, V7, E8, N8, T8, V8, E9, N9, T9, V9, E10, N10, T10, V10, E11, N11, T11, V11, E12, N12, T12, V12, E13, N13, T13, V13, E14, N14, T14, V14, E15, Num.TWO, BgfxAttribType.HALF, Vec.HALF_Vec2> thenUse(E15 attrib, Num.TWO num, BgfxAttribType.HALF type, boolean normalized, boolean asInt) {
        return new VertexLayoutStructBuilder15<>(this, attrib, num, type, normalized, asInt);
    }
    public <E15 extends BgfxAttrib> VertexLayoutStructBuilder15<E1, N1, T1, V1, E2, N2, T2, V2, E3, N3, T3, V3, E4, N4, T4, V4, E5, N5, T5, V5, E6, N6, T6, V6, E7, N7, T7, V7, E8, N8, T8, V8, E9, N9, T9, V9, E10, N10, T10, V10, E11, N11, T11, V11, E12, N12, T12, V12, E13, N13, T13, V13, E14, N14, T14, V14, E15, Num.THREE, BgfxAttribType.HALF, Vec.HALF_Vec3> thenUse(E15 attrib, Num.THREE num, BgfxAttribType.HALF type, boolean normalized, boolean asInt) {
        return new VertexLayoutStructBuilder15<>(this, attrib, num, type, normalized, asInt);
    }
    public <E15 extends BgfxAttrib> VertexLayoutStructBuilder15<E1, N1, T1, V1, E2, N2, T2, V2, E3, N3, T3, V3, E4, N4, T4, V4, E5, N5, T5, V5, E6, N6, T6, V6, E7, N7, T7, V7, E8, N8, T8, V8, E9, N9, T9, V9, E10, N10, T10, V10, E11, N11, T11, V11, E12, N12, T12, V12, E13, N13, T13, V13, E14, N14, T14, V14, E15, Num.FOUR, BgfxAttribType.HALF, Vec.HALF_Vec4> thenUse(E15 attrib, Num.FOUR num, BgfxAttribType.HALF type, boolean normalized, boolean asInt) {
        return new VertexLayoutStructBuilder15<>(this, attrib, num, type, normalized, asInt);
    }

    public <E15 extends BgfxAttrib> VertexLayoutStructBuilder15<E1, N1, T1, V1, E2, N2, T2, V2, E3, N3, T3, V3, E4, N4, T4, V4, E5, N5, T5, V5, E6, N6, T6, V6, E7, N7, T7, V7, E8, N8, T8, V8, E9, N9, T9, V9, E10, N10, T10, V10, E11, N11, T11, V11, E12, N12, T12, V12, E13, N13, T13, V13, E14, N14, T14, V14, E15, Num.ONE, BgfxAttribType.FLOAT, Vec.FLOAT_Vec1> thenUse(E15 attrib, Num.ONE num, BgfxAttribType.FLOAT type, boolean normalized, boolean asInt) {
        return new VertexLayoutStructBuilder15<>(this, attrib, num, type, normalized, asInt);
    }
    public <E15 extends BgfxAttrib> VertexLayoutStructBuilder15<E1, N1, T1, V1, E2, N2, T2, V2, E3, N3, T3, V3, E4, N4, T4, V4, E5, N5, T5, V5, E6, N6, T6, V6, E7, N7, T7, V7, E8, N8, T8, V8, E9, N9, T9, V9, E10, N10, T10, V10, E11, N11, T11, V11, E12, N12, T12, V12, E13, N13, T13, V13, E14, N14, T14, V14, E15, Num.TWO, BgfxAttribType.FLOAT, Vec.FLOAT_Vec2> thenUse(E15 attrib, Num.TWO num, BgfxAttribType.FLOAT type, boolean normalized, boolean asInt) {
        return new VertexLayoutStructBuilder15<>(this, attrib, num, type, normalized, asInt);
    }
    public <E15 extends BgfxAttrib> VertexLayoutStructBuilder15<E1, N1, T1, V1, E2, N2, T2, V2, E3, N3, T3, V3, E4, N4, T4, V4, E5, N5, T5, V5, E6, N6, T6, V6, E7, N7, T7, V7, E8, N8, T8, V8, E9, N9, T9, V9, E10, N10, T10, V10, E11, N11, T11, V11, E12, N12, T12, V12, E13, N13, T13, V13, E14, N14, T14, V14, E15, Num.THREE, BgfxAttribType.FLOAT, Vec.FLOAT_Vec3> thenUse(E15 attrib, Num.THREE num, BgfxAttribType.FLOAT type, boolean normalized, boolean asInt) {
        return new VertexLayoutStructBuilder15<>(this, attrib, num, type, normalized, asInt);
    }
    public <E15 extends BgfxAttrib> VertexLayoutStructBuilder15<E1, N1, T1, V1, E2, N2, T2, V2, E3, N3, T3, V3, E4, N4, T4, V4, E5, N5, T5, V5, E6, N6, T6, V6, E7, N7, T7, V7, E8, N8, T8, V8, E9, N9, T9, V9, E10, N10, T10, V10, E11, N11, T11, V11, E12, N12, T12, V12, E13, N13, T13, V13, E14, N14, T14, V14, E15, Num.FOUR, BgfxAttribType.FLOAT, Vec.FLOAT_Vec4> thenUse(E15 attrib, Num.FOUR num, BgfxAttribType.FLOAT type, boolean normalized, boolean asInt) {
        return new VertexLayoutStructBuilder15<>(this, attrib, num, type, normalized, asInt);
    }
}
