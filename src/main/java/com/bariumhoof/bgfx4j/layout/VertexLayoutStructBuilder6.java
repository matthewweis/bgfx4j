package com.bariumhoof.bgfx4j.layout;

import com.bariumhoof.Capabilities;
import com.bariumhoof.bgfx4j.enums.BGFX_RENDERER_TYPE;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.lwjgl.bgfx.BGFXVertexLayout;

public class VertexLayoutStructBuilder6<E1 extends BgfxAttrib, N1 extends Num, T1 extends BgfxAttribType, V1 extends Vec<N1, T1>,
        E2 extends BgfxAttrib, N2 extends Num, T2 extends BgfxAttribType, V2 extends Vec<N2, T2>, E3 extends BgfxAttrib, N3 extends Num, T3 extends BgfxAttribType, V3 extends Vec<N3, T3>,
        E4 extends BgfxAttrib, N4 extends Num, T4 extends BgfxAttribType, V4 extends Vec<N4, T4>, E5 extends BgfxAttrib, N5 extends Num, T5 extends BgfxAttribType, V5 extends Vec<N5, T5>,
        E6 extends BgfxAttrib, N6 extends Num, T6 extends BgfxAttribType, V6 extends Vec<N6, T6>> extends VertexLayoutStructBuilder<E6, N6, T6, V6> {
    private final VertexLayoutStructBuilder5<E1, N1, T1, V1, E2, N2, T2, V2, E3, N3, T3, V3, E4, N4, T4, V4, E5, N5, T5, V5> last;

    protected VertexLayoutStructBuilder6(VertexLayoutStructBuilder5<E1, N1, T1, V1, E2, N2, T2, V2, E3, N3, T3, V3, E4, N4, T4, V4, E5, N5, T5, V5> last, E6 attrib, N6 num, T6 type, boolean normalized, boolean asInt) {
        super(attrib, num, type, normalized, asInt);
        this.last = last;
    }

    public VertexLayoutStruct6<E1, N1, T1, V1, E2, N2, T2, V2, E3, N3, T3, V3, E4, N4, T4, V4, E5, N5, T5, V5, E6, N6, T6, V6> build() {
        return build(Capabilities.getRendererType());
    }

    public VertexLayoutStruct6<E1, N1, T1, V1, E2, N2, T2, V2, E3, N3, T3, V3, E4, N4, T4, V4, E5, N5, T5, V5, E6, N6, T6, V6> build(@NotNull BGFX_RENDERER_TYPE rendererType) {
        final VertexLayoutStructBuilder<?, ?, ?, ?>[] builders = createBuildersArray(6, this);
        final BGFXVertexLayout layout = createLayout(rendererType, builders);
        return new VertexLayoutStruct6<>(layout);
    }

    @Override
    @Nullable VertexLayoutStructBuilder<E5, N5, T5, V5> previousBuilder() {
        return last;
    }

    public <E7 extends BgfxAttrib> VertexLayoutStructBuilder7<E1, N1, T1, V1, E2, N2, T2, V2, E3, N3, T3, V3, E4, N4, T4, V4, E5, N5, T5, V5, E6, N6, T6, V6, E7, Num.ONE, BgfxAttribType.UINT8, Vec.UINT8_Vec1> thenUse(E7 attrib, Num.ONE num, BgfxAttribType.UINT8 type, boolean normalized, boolean asInt) {
        return new VertexLayoutStructBuilder7<>(this, attrib, num, type, normalized, asInt);
    }
    public <E7 extends BgfxAttrib> VertexLayoutStructBuilder7<E1, N1, T1, V1, E2, N2, T2, V2, E3, N3, T3, V3, E4, N4, T4, V4, E5, N5, T5, V5, E6, N6, T6, V6, E7, Num.TWO, BgfxAttribType.UINT8, Vec.UINT8_Vec2> thenUse(E7 attrib, Num.TWO num, BgfxAttribType.UINT8 type, boolean normalized, boolean asInt) {
        return new VertexLayoutStructBuilder7<>(this, attrib, num, type, normalized, asInt);
    }
    public <E7 extends BgfxAttrib> VertexLayoutStructBuilder7<E1, N1, T1, V1, E2, N2, T2, V2, E3, N3, T3, V3, E4, N4, T4, V4, E5, N5, T5, V5, E6, N6, T6, V6, E7, Num.THREE, BgfxAttribType.UINT8, Vec.UINT8_Vec3> thenUse(E7 attrib, Num.THREE num, BgfxAttribType.UINT8 type, boolean normalized, boolean asInt) {
        return new VertexLayoutStructBuilder7<>(this, attrib, num, type, normalized, asInt);
    }
    public <E7 extends BgfxAttrib> VertexLayoutStructBuilder7<E1, N1, T1, V1, E2, N2, T2, V2, E3, N3, T3, V3, E4, N4, T4, V4, E5, N5, T5, V5, E6, N6, T6, V6, E7, Num.FOUR, BgfxAttribType.UINT8, Vec.UINT8_Vec4> thenUse(E7 attrib, Num.FOUR num, BgfxAttribType.UINT8 type, boolean normalized, boolean asInt) {
        return new VertexLayoutStructBuilder7<>(this, attrib, num, type, normalized, asInt);
    }

    public <E7 extends BgfxAttrib> VertexLayoutStructBuilder7<E1, N1, T1, V1, E2, N2, T2, V2, E3, N3, T3, V3, E4, N4, T4, V4, E5, N5, T5, V5, E6, N6, T6, V6, E7, Num.THREE, BgfxAttribType.UINT10, Vec.UINT10_Vec3> thenUse(E7 attrib, Num.THREE num, BgfxAttribType.UINT10 type, boolean normalized, boolean asInt) {
        return new VertexLayoutStructBuilder7<>(this, attrib, num, type, normalized, asInt);
    }
    public <E7 extends BgfxAttrib> VertexLayoutStructBuilder7<E1, N1, T1, V1, E2, N2, T2, V2, E3, N3, T3, V3, E4, N4, T4, V4, E5, N5, T5, V5, E6, N6, T6, V6, E7, Num.FOUR, BgfxAttribType.UINT10, Vec.UINT10_Vec4> thenUse(E7 attrib, Num.FOUR num, BgfxAttribType.UINT10 type, boolean normalized, boolean asInt) {
        return new VertexLayoutStructBuilder7<>(this, attrib, num, type, normalized, asInt);
    }

    public <E7 extends BgfxAttrib> VertexLayoutStructBuilder7<E1, N1, T1, V1, E2, N2, T2, V2, E3, N3, T3, V3, E4, N4, T4, V4, E5, N5, T5, V5, E6, N6, T6, V6, E7, Num.ONE, BgfxAttribType.INT16, Vec.INT16_Vec1> thenUse(E7 attrib, Num.ONE num, BgfxAttribType.INT16 type, boolean normalized, boolean asInt) {
        return new VertexLayoutStructBuilder7<>(this, attrib, num, type, normalized, asInt);
    }
    public <E7 extends BgfxAttrib> VertexLayoutStructBuilder7<E1, N1, T1, V1, E2, N2, T2, V2, E3, N3, T3, V3, E4, N4, T4, V4, E5, N5, T5, V5, E6, N6, T6, V6, E7, Num.TWO, BgfxAttribType.INT16, Vec.INT16_Vec2> thenUse(E7 attrib, Num.TWO num, BgfxAttribType.INT16 type, boolean normalized, boolean asInt) {
        return new VertexLayoutStructBuilder7<>(this, attrib, num, type, normalized, asInt);
    }
    public <E7 extends BgfxAttrib> VertexLayoutStructBuilder7<E1, N1, T1, V1, E2, N2, T2, V2, E3, N3, T3, V3, E4, N4, T4, V4, E5, N5, T5, V5, E6, N6, T6, V6, E7, Num.THREE, BgfxAttribType.INT16, Vec.INT16_Vec3> thenUse(E7 attrib, Num.THREE num, BgfxAttribType.INT16 type, boolean normalized, boolean asInt) {
        return new VertexLayoutStructBuilder7<>(this, attrib, num, type, normalized, asInt);
    }
    public <E7 extends BgfxAttrib> VertexLayoutStructBuilder7<E1, N1, T1, V1, E2, N2, T2, V2, E3, N3, T3, V3, E4, N4, T4, V4, E5, N5, T5, V5, E6, N6, T6, V6, E7, Num.FOUR, BgfxAttribType.INT16, Vec.INT16_Vec4> thenUse(E7 attrib, Num.FOUR num, BgfxAttribType.INT16 type, boolean normalized, boolean asInt) {
        return new VertexLayoutStructBuilder7<>(this, attrib, num, type, normalized, asInt);
    }

    public <E7 extends BgfxAttrib> VertexLayoutStructBuilder7<E1, N1, T1, V1, E2, N2, T2, V2, E3, N3, T3, V3, E4, N4, T4, V4, E5, N5, T5, V5, E6, N6, T6, V6, E7, Num.ONE, BgfxAttribType.HALF, Vec.HALF_Vec1> thenUse(E7 attrib, Num.ONE num, BgfxAttribType.HALF type, boolean normalized, boolean asInt) {
        return new VertexLayoutStructBuilder7<>(this, attrib, num, type, normalized, asInt);
    }
    public <E7 extends BgfxAttrib> VertexLayoutStructBuilder7<E1, N1, T1, V1, E2, N2, T2, V2, E3, N3, T3, V3, E4, N4, T4, V4, E5, N5, T5, V5, E6, N6, T6, V6, E7, Num.TWO, BgfxAttribType.HALF, Vec.HALF_Vec2> thenUse(E7 attrib, Num.TWO num, BgfxAttribType.HALF type, boolean normalized, boolean asInt) {
        return new VertexLayoutStructBuilder7<>(this, attrib, num, type, normalized, asInt);
    }
    public <E7 extends BgfxAttrib> VertexLayoutStructBuilder7<E1, N1, T1, V1, E2, N2, T2, V2, E3, N3, T3, V3, E4, N4, T4, V4, E5, N5, T5, V5, E6, N6, T6, V6, E7, Num.THREE, BgfxAttribType.HALF, Vec.HALF_Vec3> thenUse(E7 attrib, Num.THREE num, BgfxAttribType.HALF type, boolean normalized, boolean asInt) {
        return new VertexLayoutStructBuilder7<>(this, attrib, num, type, normalized, asInt);
    }
    public <E7 extends BgfxAttrib> VertexLayoutStructBuilder7<E1, N1, T1, V1, E2, N2, T2, V2, E3, N3, T3, V3, E4, N4, T4, V4, E5, N5, T5, V5, E6, N6, T6, V6, E7, Num.FOUR, BgfxAttribType.HALF, Vec.HALF_Vec4> thenUse(E7 attrib, Num.FOUR num, BgfxAttribType.HALF type, boolean normalized, boolean asInt) {
        return new VertexLayoutStructBuilder7<>(this, attrib, num, type, normalized, asInt);
    }

    public <E7 extends BgfxAttrib> VertexLayoutStructBuilder7<E1, N1, T1, V1, E2, N2, T2, V2, E3, N3, T3, V3, E4, N4, T4, V4, E5, N5, T5, V5, E6, N6, T6, V6, E7, Num.ONE, BgfxAttribType.FLOAT, Vec.FLOAT_Vec1> thenUse(E7 attrib, Num.ONE num, BgfxAttribType.FLOAT type, boolean normalized, boolean asInt) {
        return new VertexLayoutStructBuilder7<>(this, attrib, num, type, normalized, asInt);
    }
    public <E7 extends BgfxAttrib> VertexLayoutStructBuilder7<E1, N1, T1, V1, E2, N2, T2, V2, E3, N3, T3, V3, E4, N4, T4, V4, E5, N5, T5, V5, E6, N6, T6, V6, E7, Num.TWO, BgfxAttribType.FLOAT, Vec.FLOAT_Vec2> thenUse(E7 attrib, Num.TWO num, BgfxAttribType.FLOAT type, boolean normalized, boolean asInt) {
        return new VertexLayoutStructBuilder7<>(this, attrib, num, type, normalized, asInt);
    }
    public <E7 extends BgfxAttrib> VertexLayoutStructBuilder7<E1, N1, T1, V1, E2, N2, T2, V2, E3, N3, T3, V3, E4, N4, T4, V4, E5, N5, T5, V5, E6, N6, T6, V6, E7, Num.THREE, BgfxAttribType.FLOAT, Vec.FLOAT_Vec3> thenUse(E7 attrib, Num.THREE num, BgfxAttribType.FLOAT type, boolean normalized, boolean asInt) {
        return new VertexLayoutStructBuilder7<>(this, attrib, num, type, normalized, asInt);
    }
    public <E7 extends BgfxAttrib> VertexLayoutStructBuilder7<E1, N1, T1, V1, E2, N2, T2, V2, E3, N3, T3, V3, E4, N4, T4, V4, E5, N5, T5, V5, E6, N6, T6, V6, E7, Num.FOUR, BgfxAttribType.FLOAT, Vec.FLOAT_Vec4> thenUse(E7 attrib, Num.FOUR num, BgfxAttribType.FLOAT type, boolean normalized, boolean asInt) {
        return new VertexLayoutStructBuilder7<>(this, attrib, num, type, normalized, asInt);
    }
}
