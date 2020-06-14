package com.bariumhoof.bgfx4j.layout;

import org.jetbrains.annotations.Nullable;

public class VertexLayoutStructBuilder2<E1 extends BgfxAttrib, V1 extends Vec<?,?>,
        E2 extends BgfxAttrib, V2 extends Vec<?,?>> extends VertexLayoutStructBuilder<E2, V2> {
    private final VertexLayoutStructBuilder1<E1, V1> last;

    protected VertexLayoutStructBuilder2(VertexLayoutStructBuilder1<E1, V1> last, E2 attrib, V2 vec, boolean normalized, boolean asInt) {
        super(attrib, vec, normalized, asInt);
        this.last = last;
    }

//    public VertexLayoutStruct2<E1, V1, E2, V2> build() {
//        return build(Capabilities.getRendererType());
//    }
//
//    public VertexLayoutStruct2<E1, V1, E2, V2> build(@NotNull BGFX_RENDERER_TYPE rendererType) {
//        final VertexLayoutStructBuilder<?, ?>[] builders = createBuildersArray(2, this);
//        final BGFXVertexLayout layout = createLayout(rendererType, builders);
//        return new VertexLayoutStruct2<>(layout);
//    }

    @Override
    @Nullable VertexLayoutStructBuilder<E1, V1> previousBuilder() {
        return last;
    }

//    public InitialStage then() {
//        return new InitialStage();
//    }
//
//    public VertexLayoutStructBuilder2<E1,V1,E2,V2> self() {
//        return this;
//    }
//
//    // all staging for NEXT one
//    public class InitialStage {
//
//        protected InitialStage() { }
//
//        @NotNull
//        public final AttribStage<POSITION> position() {
//            return new AttribStage<POSITION>(POSITION.POSITION);
//        }
//
//        @NotNull
//        public final AttribStage<NORMAL> normal() {
//            return new AttribStage<NORMAL>(NORMAL.NORMAL);
//        }
//
//        @NotNull
//        public final AttribStage<TANGENT> tangent() {
//            return new AttribStage<TANGENT>(TANGENT.TANGENT);
//        }
//
//        @NotNull
//        public final AttribStage<BITANGENT> bitangent() {
//            return new AttribStage<BITANGENT>(BITANGENT.BITANGENT);
//        }
//
//        @NotNull
//        public final AttribStage<COLOR0> color0() {
//            return new AttribStage<COLOR0>(COLOR0.COLOR0);
//        }
//
//        @NotNull
//        public final AttribStage<COLOR1> color1() {
//            return new AttribStage<COLOR1>(COLOR1.COLOR1);
//        }
//
//        @NotNull
//        public final AttribStage<COLOR2> color2() {
//            return new AttribStage<COLOR2>(COLOR2.COLOR2);
//        }
//
//        @NotNull
//        public final AttribStage<COLOR3> color3() {
//            return new AttribStage<COLOR3>(COLOR3.COLOR3);
//        }
//
//        @NotNull
//        public final AttribStage<INDICES> indices() {
//            return new AttribStage<INDICES>(INDICES.INDICES);
//        }
//
//        @NotNull
//        public final AttribStage<WEIGHT> weight() {
//            return new AttribStage<WEIGHT>(WEIGHT.WEIGHT);
//        }
//
//        @NotNull
//        public final AttribStage<TEXCOORD0> texcoord0() {
//            return new AttribStage<TEXCOORD0>(TEXCOORD0.TEXCOORD0);
//        }
//
//        @NotNull
//        public final AttribStage<TEXCOORD1> texcoord1() {
//            return new AttribStage<TEXCOORD1>(TEXCOORD1.TEXCOORD1);
//        }
//
//        @NotNull
//        public final AttribStage<TEXCOORD2> texcoord2() {
//            return new AttribStage<TEXCOORD2>(TEXCOORD2.TEXCOORD2);
//        }
//
//        @NotNull
//        public final AttribStage<TEXCOORD3> texcoord3() {
//            return new AttribStage<TEXCOORD3>(TEXCOORD3.TEXCOORD3);
//        }
//
//        @NotNull
//        public final AttribStage<TEXCOORD4> texcoord4() {
//            return new AttribStage<TEXCOORD4>(TEXCOORD4.TEXCOORD4);
//        }
//
//        @NotNull
//        public final AttribStage<TEXCOORD5> texcoord5() {
//            return new AttribStage<TEXCOORD5>(TEXCOORD5.TEXCOORD5);
//        }
//
//        @NotNull
//        public final AttribStage<TEXCOORD6> texcoord6() {
//            return new AttribStage<TEXCOORD6>(TEXCOORD6.TEXCOORD6);
//        }
//
//        @NotNull
//        public final AttribStage<TEXCOORD7> texcoord7() {
//            return new AttribStage<TEXCOORD7>(TEXCOORD7.TEXCOORD7);
//        }
//
//    }
//
//    public class AttribStage<E extends BgfxAttrib> {
//        final E attrib;
//
//        protected AttribStage(E attrib) {
//            this.attrib = attrib;
//        }
//
//        // vec 1
//        public AttribTypeStageFixedPointOptQs<E,UINT8_Vec1> uint8_vec1() {
//            return new AttribTypeStageFixedPointOptQs<>(attrib,ONE.ONE,UINT8.UINT8);
//        }
//
//        public AttribTypeStageFixedPointOptQs<E,INT16_Vec1> int16_vec1() {
//            return new AttribTypeStageFixedPointOptQs<>(attrib,ONE.ONE,INT16.INT16);
//        }
//
//        public AttribTypeStageFloatingPoint<E,HALF_Vec1> half_vec1() {
//            return new AttribTypeStageFloatingPoint<>(attrib, ONE.ONE, HALF.HALF);
//        }
//
//        public AttribTypeStageFloatingPoint<E,FLOAT_Vec1> float_vec1() {
//            return new AttribTypeStageFloatingPoint<>(attrib,ONE.ONE, FLOAT.FLOAT);
//        }
//
//        // vec 2
//        public AttribTypeStageFixedPointOptQs<E,UINT8_Vec2> uint8_vec2() {
//            return new AttribTypeStageFixedPointOptQs<>(attrib, Vec.default_uint8_vec2);
//        }
//
//        public AttribTypeStageFixedPointOptQs<E,INT16_Vec2> int16_vec2() {
//            return new AttribTypeStageFixedPointOptQs<>(attrib,Vec.default_int16_vec2);
//        }
//
//        public AttribTypeStageFloatingPoint<E,HALF_Vec2> half_vec2() {
//            return new AttribTypeStageFloatingPoint<>(attrib,TWO.TWO,HALF.HALF);
//        }
//
//        public AttribTypeStageFloatingPoint<E,FLOAT_Vec2> float_vec2() {
//            return new AttribTypeStageFloatingPoint<>(attrib,TWO.TWO, FLOAT.FLOAT);
//        }
//
//        // vec 3
//        public AttribTypeStageFixedPointOptQs<E,UINT8_Vec3> uint8_vec3() {
//            return new AttribTypeStageFixedPointOptQs<>(attrib, THREE.THREE,UINT8.UINT8);
//        }
//
//        public AttribTypeStageFixedPointOptQs<E,UINT10_Vec4> uint10_vec3() {
//            return new AttribTypeStageFixedPointOptQs<>(attrib, THREE.THREE,UINT10.UINT10);
//        }
//
//        public AttribTypeStageFixedPointOptQs<E,INT16_Vec3> int16_vec3() {
//            return new AttribTypeStageFixedPointOptQs<>(attrib, THREE.THREE,INT16.INT16);
//        }
//
//        public AttribTypeStageFloatingPoint<E,HALF_Vec3> half_vec3() {
//            return new AttribTypeStageFloatingPoint<>(attrib, THREE.THREE,HALF.HALF);
//        }
//
//        public AttribTypeStageFloatingPoint<E,FLOAT_Vec3> float_vec3() {
//            return new AttribTypeStageFloatingPoint<>(attrib, THREE.THREE, FLOAT.FLOAT);
//        }
//
//        // vec 4
//        public AttribTypeStageFixedPointOptQs<E,UINT8_Vec4> uint8_vec4() {
//            return new AttribTypeStageFixedPointOptQs<>(attrib, FOUR.FOUR,UINT8.UINT8);
//        }
//
//        public AttribTypeStageFixedPointOptQs<E,UINT10_Vec4> uint10_vec4() {
//            return new AttribTypeStageFixedPointOptQs<>(attrib,FOUR.FOUR,UINT10.UINT10);
//        }
//
//        public AttribTypeStageFixedPointOptQs<E,INT16_Vec4> int16_vec4() {
//            return new AttribTypeStageFixedPointOptQs<>(attrib,FOUR.FOUR,INT16.INT16);
//        }
//
//        public AttribTypeStageFloatingPoint<E,HALF_Vec4> half_vec4() {
//            return new AttribTypeStageFloatingPoint<>(attrib,FOUR.FOUR,HALF.HALF);
//        }
//
//        public AttribTypeStageFloatingPoint<E,FLOAT_Vec4> float_vec4() {
//            return new AttribTypeStageFloatingPoint<>(attrib,FOUR.FOUR, FLOAT.FLOAT);
//        }
//
//    }
//
//    // asks BOTH optional questions at once, or allows both to be skipped (defaulting to false)
//    public class AttribTypeStageFixedPointOptQs<E extends BgfxAttrib, V extends Vec<?,?>> extends AttribTypeStageFixedPoint<E,V> {
//
//        protected AttribTypeStageFixedPointOptQs(E attrib, V vec) {
//            super(attrib, vec);
//        }
//
//        public AttribTypeStageFixedPoint<E,V> normalized() {
//            return new AttribTypeStageFixedPointOptQ2<>(attrib, vec, true);
//        }
//
//        public AttribTypeStageFixedPoint<E,V> asInt() {
//            return new AttribTypeStageFixedPointOptQ1<>(attrib, vec, true);
//        }
//
//    }
//
//    public class AttribTypeStageFixedPointOptQ1<E extends BgfxAttrib, V extends Vec<?,?>> extends AttribTypeStageFixedPoint<E,V> {
//
//        final boolean asInt;
//
//        protected AttribTypeStageFixedPointOptQ1(E attrib, V vec, boolean asInt) {
//            super(attrib, vec);
//            this.asInt = asInt;
//        }
//
//        public AttribTypeStageFixedPointOptQ2<E,V> normalized() {
//            return new AttribTypeStageFixedPointOptQ2<>(attrib, vec, true);
//        }
//
//        @Override
//        public VertexLayoutStructBuilder3<E1,V1,E2,V2,E,V> build() {
//            return new VertexLayoutStructBuilder3<>(self(), attrib, vec, false, asInt);
//        }
//    }
//
//    public class AttribTypeStageFixedPointOptQ2<E extends BgfxAttrib, V extends Vec<?,?>> extends AttribTypeStageFixedPoint<E,V> {
//
//        final boolean normalized;
//
//        protected AttribTypeStageFixedPointOptQ2(E attrib, V vec, boolean normalized) {
//            super(attrib, vec);
//            this.normalized = normalized;
//        }
//
//        public AttribTypeStageFixedPointOptQ1<E,V> asInt() {
//            return new AttribTypeStageFixedPointOptQ1<>(attrib, vec, true);
//        }
//
//        @Override
//        public VertexLayoutStructBuilder3<E1,V1,E2,V2,E,V> build() {
//            return new VertexLayoutStructBuilder3<>(self(), attrib, vec, normalized, false);
//        }
//
//    }
//
//    public class AttribTypeStageFixedPoint<E extends BgfxAttrib, V extends Vec<?,?>> {
//        final E attrib;
//        final N num;
//        final T type;
//
//        protected AttribTypeStageFixedPoint(E attrib, V vec) {
//            this.attrib = attrib;
//            this.num = num;
//            this.type = type;
//        }
//
//        public VertexLayoutStructBuilder3<E1,V1,E2,V2,E,V> build() {
//            return new VertexLayoutStructBuilder3<>(self(), attrib, vec, false, false);
//        }
//
//    }
//
//    public class AttribTypeStageFloatingPoint<E extends BgfxAttrib, V extends Vec<?,?>> {
//        final E attrib;
//        final N num;
//        final T type;
//
//        protected AttribTypeStageFloatingPoint(E attrib, V vec) {
//            this.attrib = attrib;
//            this.num = num;
//            this.type = type;
//        }
//
//        public VertexLayoutStructBuilder3<E1,V1,E2,V2,E,V> build() {
//            return new VertexLayoutStructBuilder3<>(self(), attrib, vec, false, false);
//        }
//    }

//    public <E3 extends BgfxAttrib> VertexLayoutStructBuilder3<E1, V1, E2, V2, E3, Num.ONE, BgfxAttribType.UINT8, Vec.UINT8_Vec1> thenUse(E3 attrib, Num.ONE num, BgfxAttribType.UINT8 type, boolean normalized, boolean asInt) {
//        return new VertexLayoutStructBuilder3<>(this, attrib, vec, normalized, asInt);
//    }
//    public <E3 extends BgfxAttrib> VertexLayoutStructBuilder3<E1, V1, E2, V2, E3, Num.TWO, BgfxAttribType.UINT8, Vec.UINT8_Vec2> thenUse(E3 attrib, Num.TWO num, BgfxAttribType.UINT8 type, boolean normalized, boolean asInt) {
//        return new VertexLayoutStructBuilder3<>(this, attrib, vec, normalized, asInt);
//    }
//    public <E3 extends BgfxAttrib> VertexLayoutStructBuilder3<E1, V1, E2, V2, E3, Num.THREE, BgfxAttribType.UINT8, Vec.UINT8_Vec3> thenUse(E3 attrib, Num.THREE num, BgfxAttribType.UINT8 type, boolean normalized, boolean asInt) {
//        return new VertexLayoutStructBuilder3<>(this, attrib, vec, normalized, asInt);
//    }
//    public <E3 extends BgfxAttrib> VertexLayoutStructBuilder3<E1, V1, E2, V2, E3, Num.FOUR, BgfxAttribType.UINT8, Vec.UINT8_Vec4> thenUse(E3 attrib, Num.FOUR num, BgfxAttribType.UINT8 type, boolean normalized, boolean asInt) {
//        return new VertexLayoutStructBuilder3<>(this, attrib, vec, normalized, asInt);
//    }
//
//    public <E3 extends BgfxAttrib> VertexLayoutStructBuilder3<E1, V1, E2, V2, E3, Num.THREE, BgfxAttribType.UINT10, Vec.UINT10_Vec3> thenUse(E3 attrib, Num.THREE num, BgfxAttribType.UINT10 type, boolean normalized, boolean asInt) {
//        return new VertexLayoutStructBuilder3<>(this, attrib, vec, normalized, asInt);
//    }
//    public <E3 extends BgfxAttrib> VertexLayoutStructBuilder3<E1, V1, E2, V2, E3, Num.FOUR, BgfxAttribType.UINT10, Vec.UINT10_Vec4> thenUse(E3 attrib, Num.FOUR num, BgfxAttribType.UINT10 type, boolean normalized, boolean asInt) {
//        return new VertexLayoutStructBuilder3<>(this, attrib, vec, normalized, asInt);
//    }
//
//    public <E3 extends BgfxAttrib> VertexLayoutStructBuilder3<E1, V1, E2, V2, E3, Num.ONE, BgfxAttribType.INT16, Vec.INT16_Vec1> thenUse(E3 attrib, Num.ONE num, BgfxAttribType.INT16 type, boolean normalized, boolean asInt) {
//        return new VertexLayoutStructBuilder3<>(this, attrib, vec, normalized, asInt);
//    }
//    public <E3 extends BgfxAttrib> VertexLayoutStructBuilder3<E1, V1, E2, V2, E3, Num.TWO, BgfxAttribType.INT16, Vec.INT16_Vec2> thenUse(E3 attrib, Num.TWO num, BgfxAttribType.INT16 type, boolean normalized, boolean asInt) {
//        return new VertexLayoutStructBuilder3<>(this, attrib, vec, normalized, asInt);
//    }
//    public <E3 extends BgfxAttrib> VertexLayoutStructBuilder3<E1, V1, E2, V2, E3, Num.THREE, BgfxAttribType.INT16, Vec.INT16_Vec3> thenUse(E3 attrib, Num.THREE num, BgfxAttribType.INT16 type, boolean normalized, boolean asInt) {
//        return new VertexLayoutStructBuilder3<>(this, attrib, vec, normalized, asInt);
//    }
//    public <E3 extends BgfxAttrib> VertexLayoutStructBuilder3<E1, V1, E2, V2, E3, Num.FOUR, BgfxAttribType.INT16, Vec.INT16_Vec4> thenUse(E3 attrib, Num.FOUR num, BgfxAttribType.INT16 type, boolean normalized, boolean asInt) {
//        return new VertexLayoutStructBuilder3<>(this, attrib, vec, normalized, asInt);
//    }
//
//    public <E3 extends BgfxAttrib> VertexLayoutStructBuilder3<E1, V1, E2, V2, E3, Num.ONE, BgfxAttribType.HALF, Vec.HALF_Vec1> thenUse(E3 attrib, Num.ONE num, BgfxAttribType.HALF type, boolean normalized, boolean asInt) {
//        return new VertexLayoutStructBuilder3<>(this, attrib, vec, normalized, asInt);
//    }
//    public <E3 extends BgfxAttrib> VertexLayoutStructBuilder3<E1, V1, E2, V2, E3, Num.TWO, BgfxAttribType.HALF, Vec.HALF_Vec2> thenUse(E3 attrib, Num.TWO num, BgfxAttribType.HALF type, boolean normalized, boolean asInt) {
//        return new VertexLayoutStructBuilder3<>(this, attrib, vec, normalized, asInt);
//    }
//    public <E3 extends BgfxAttrib> VertexLayoutStructBuilder3<E1, V1, E2, V2, E3, Num.THREE, BgfxAttribType.HALF, Vec.HALF_Vec3> thenUse(E3 attrib, Num.THREE num, BgfxAttribType.HALF type, boolean normalized, boolean asInt) {
//        return new VertexLayoutStructBuilder3<>(this, attrib, vec, normalized, asInt);
//    }
//    public <E3 extends BgfxAttrib> VertexLayoutStructBuilder3<E1, V1, E2, V2, E3, Num.FOUR, BgfxAttribType.HALF, Vec.HALF_Vec4> thenUse(E3 attrib, Num.FOUR num, BgfxAttribType.HALF type, boolean normalized, boolean asInt) {
//        return new VertexLayoutStructBuilder3<>(this, attrib, vec, normalized, asInt);
//    }
//
//    public <E3 extends BgfxAttrib> VertexLayoutStructBuilder3<E1, V1, E2, V2, E3, Num.ONE, BgfxAttribType.FLOAT, Vec.FLOAT_Vec1> thenUse(E3 attrib, Num.ONE num, BgfxAttribType.FLOAT type, boolean normalized, boolean asInt) {
//        return new VertexLayoutStructBuilder3<>(this, attrib, vec, normalized, asInt);
//    }
//    public <E3 extends BgfxAttrib> VertexLayoutStructBuilder3<E1, V1, E2, V2, E3, Num.TWO, BgfxAttribType.FLOAT, Vec.FLOAT_Vec2> thenUse(E3 attrib, Num.TWO num, BgfxAttribType.FLOAT type, boolean normalized, boolean asInt) {
//        return new VertexLayoutStructBuilder3<>(this, attrib, vec, normalized, asInt);
//    }
//    public <E3 extends BgfxAttrib> VertexLayoutStructBuilder3<E1, V1, E2, V2, E3, Num.THREE, BgfxAttribType.FLOAT, Vec.FLOAT_Vec3> thenUse(E3 attrib, Num.THREE num, BgfxAttribType.FLOAT type, boolean normalized, boolean asInt) {
//        return new VertexLayoutStructBuilder3<>(this, attrib, vec, normalized, asInt);
//    }
//    public <E3 extends BgfxAttrib> VertexLayoutStructBuilder3<E1, V1, E2, V2, E3, Num.FOUR, BgfxAttribType.FLOAT, Vec.FLOAT_Vec4> thenUse(E3 attrib, Num.FOUR num, BgfxAttribType.FLOAT type, boolean normalized, boolean asInt) {
//        return new VertexLayoutStructBuilder3<>(this, attrib, vec, normalized, asInt);
//    }
}
