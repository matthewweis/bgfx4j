package com.bariumhoof.bgfx4j.layout;

import com.bariumhoof.Capabilities;
import com.bariumhoof.bgfx4j.enums.BGFX_RENDERER_TYPE;
import com.bariumhoof.bgfx4j.layout.BgfxAttrib.*;
import com.bariumhoof.bgfx4j.layout.BgfxAttribType.*;
import com.bariumhoof.bgfx4j.layout.Num.FOUR;
import com.bariumhoof.bgfx4j.layout.Num.ONE;
import com.bariumhoof.bgfx4j.layout.Num.THREE;
import com.bariumhoof.bgfx4j.layout.Num.TWO;
import com.bariumhoof.bgfx4j.layout.Vec.*;
import org.jetbrains.annotations.NotNull;
import org.lwjgl.bgfx.BGFXVertexLayout;

public class TypedVertexLayoutBuilder14<E1 extends BgfxAttrib, N1 extends Num, T1 extends BgfxAttribType, V1 extends Vec<N1, T1>, E2 extends BgfxAttrib, N2 extends Num, T2 extends BgfxAttribType, V2 extends Vec<N2, T2>, E3 extends BgfxAttrib, N3 extends Num, T3 extends BgfxAttribType, V3 extends Vec<N3, T3>, E4 extends BgfxAttrib, N4 extends Num, T4 extends BgfxAttribType, V4 extends Vec<N4, T4>, E5 extends BgfxAttrib, N5 extends Num, T5 extends BgfxAttribType, V5 extends Vec<N5, T5>, E6 extends BgfxAttrib, N6 extends Num, T6 extends BgfxAttribType, V6 extends Vec<N6, T6>, E7 extends BgfxAttrib, N7 extends Num, T7 extends BgfxAttribType, V7 extends Vec<N7, T7>, E8 extends BgfxAttrib, N8 extends Num, T8 extends BgfxAttribType, V8 extends Vec<N8, T8>, E9 extends BgfxAttrib, N9 extends Num, T9 extends BgfxAttribType, V9 extends Vec<N9, T9>, E10 extends BgfxAttrib, N10 extends Num, T10 extends BgfxAttribType, V10 extends Vec<N10, T10>, E11 extends BgfxAttrib, N11 extends Num, T11 extends BgfxAttribType, V11 extends Vec<N11, T11>, E12 extends BgfxAttrib, N12 extends Num, T12 extends BgfxAttribType, V12 extends Vec<N12, T12>, E13 extends BgfxAttrib, N13 extends Num, T13 extends BgfxAttribType, V13 extends Vec<N13, T13>> {
    private final VertexLayoutStructBuilder13<E1, N1, T1, V1, E2, N2, T2, V2, E3, N3, T3, V3, E4, N4, T4, V4, E5, N5, T5, V5, E6, N6, T6, V6, E7, N7, T7, V7, E8, N8, T8, V8, E9, N9, T9, V9, E10, N10, T10, V10, E11, N11, T11, V11, E12, N12, T12, V12, E13, N13, T13, V13> last;

    TypedVertexLayoutBuilder14(
            VertexLayoutStructBuilder13<E1, N1, T1, V1, E2, N2, T2, V2, E3, N3, T3, V3, E4, N4, T4, V4, E5, N5, T5, V5, E6, N6, T6, V6, E7, N7, T7, V7, E8, N8, T8, V8, E9, N9, T9, V9, E10, N10, T10, V10, E11, N11, T11, V11, E12, N12, T12, V12, E13, N13, T13, V13> last) {
        this.last = last;
    }

    InitialStage initialStage() {
        return new InitialStage();
    }

    public class InitialStage {
        protected InitialStage() {
        }

        @NotNull
        public final AttribStage<POSITION> position() {
            return new AttribStage<>(POSITION.POSITION);
        }

        @NotNull
        public final AttribStage<NORMAL> normal() {
            return new AttribStage<>(NORMAL.NORMAL);
        }

        @NotNull
        public final AttribStage<TANGENT> tangent() {
            return new AttribStage<>(TANGENT.TANGENT);
        }

        @NotNull
        public final AttribStage<BITANGENT> bitangent() {
            return new AttribStage<>(BITANGENT.BITANGENT);
        }

        @NotNull
        public final AttribStage<COLOR0> color0() {
            return new AttribStage<>(COLOR0.COLOR0);
        }

        @NotNull
        public final AttribStage<COLOR1> color1() {
            return new AttribStage<>(COLOR1.COLOR1);
        }

        @NotNull
        public final AttribStage<COLOR2> color2() {
            return new AttribStage<>(COLOR2.COLOR2);
        }

        @NotNull
        public final AttribStage<COLOR3> color3() {
            return new AttribStage<>(COLOR3.COLOR3);
        }

        @NotNull
        public final AttribStage<INDICES> indices() {
            return new AttribStage<>(INDICES.INDICES);
        }

        @NotNull
        public final AttribStage<WEIGHT> weight() {
            return new AttribStage<>(WEIGHT.WEIGHT);
        }

        @NotNull
        public final AttribStage<TEXCOORD0> texcoord0() {
            return new AttribStage<>(TEXCOORD0.TEXCOORD0);
        }

        @NotNull
        public final AttribStage<TEXCOORD1> texcoord1() {
            return new AttribStage<>(TEXCOORD1.TEXCOORD1);
        }

        @NotNull
        public final AttribStage<TEXCOORD2> texcoord2() {
            return new AttribStage<>(TEXCOORD2.TEXCOORD2);
        }

        @NotNull
        public final AttribStage<TEXCOORD3> texcoord3() {
            return new AttribStage<>(TEXCOORD3.TEXCOORD3);
        }

        @NotNull
        public final AttribStage<TEXCOORD4> texcoord4() {
            return new AttribStage<>(TEXCOORD4.TEXCOORD4);
        }

        @NotNull
        public final AttribStage<TEXCOORD5> texcoord5() {
            return new AttribStage<>(TEXCOORD5.TEXCOORD5);
        }

        @NotNull
        public final AttribStage<TEXCOORD6> texcoord6() {
            return new AttribStage<>(TEXCOORD6.TEXCOORD6);
        }

        @NotNull
        public final AttribStage<TEXCOORD7> texcoord7() {
            return new AttribStage<>(TEXCOORD7.TEXCOORD7);
        }
    }

    public class AttribStage<E extends BgfxAttrib> {
        final E attrib;

        protected AttribStage(E attrib) {
            this.attrib = attrib;
        }

        // vec 1
        public AttribTypeStageFixedPointOptQs<E, ONE, UINT8, UINT8_Vec1> uint8_vec1() {
            return new AttribTypeStageFixedPointOptQs<>(attrib, ONE.ONE, UINT8.UINT8);
        }

        public AttribTypeStageFixedPointOptQs<E, ONE, INT16, INT16_Vec1> int16_vec1() {
            return new AttribTypeStageFixedPointOptQs<>(attrib, ONE.ONE, INT16.INT16);
        }

        public AttribTypeStageDefaultOpts<E, ONE, HALF, HALF_Vec1> half_vec1() {
            return new AttribTypeStageDefaultOpts<>(attrib, ONE.ONE, HALF.HALF);
        }

        public AttribTypeStageDefaultOpts<E, ONE, FLOAT, FLOAT_Vec1> float32_vec1() {
            return new AttribTypeStageDefaultOpts<>(attrib, ONE.ONE, FLOAT.FLOAT);
        }

        // vec 2
        public AttribTypeStageFixedPointOptQs<E, TWO, UINT8, UINT8_Vec2> uint8_vec2() {
            return new AttribTypeStageFixedPointOptQs<>(attrib, TWO.TWO, UINT8.UINT8);
        }

        public AttribTypeStageFixedPointOptQs<E, TWO, INT16, INT16_Vec2> int16_vec2() {
            return new AttribTypeStageFixedPointOptQs<>(attrib, TWO.TWO, INT16.INT16);
        }

        public AttribTypeStageDefaultOpts<E, TWO, HALF, HALF_Vec2> half_vec2() {
            return new AttribTypeStageDefaultOpts<>(attrib, TWO.TWO, HALF.HALF);
        }

        public AttribTypeStageDefaultOpts<E, TWO, FLOAT, FLOAT_Vec2> float32_vec2() {
            return new AttribTypeStageDefaultOpts<>(attrib, TWO.TWO, FLOAT.FLOAT);
        }

        // vec 3
        public AttribTypeStageFixedPointOptQs<E, THREE, UINT8, UINT8_Vec3> uint8_vec3() {
            return new AttribTypeStageFixedPointOptQs<>(attrib, THREE.THREE, UINT8.UINT8);
        }

        public AttribTypeStageFixedPointOptQs<E, THREE, UINT10, UINT10_Vec3> uint10_vec3() {
            return new AttribTypeStageFixedPointOptQs<>(attrib, THREE.THREE, UINT10.UINT10);
        }

        public AttribTypeStageFixedPointOptQs<E, THREE, INT16, INT16_Vec3> int16_vec3() {
            return new AttribTypeStageFixedPointOptQs<>(attrib, THREE.THREE, INT16.INT16);
        }

        public AttribTypeStageDefaultOpts<E, THREE, HALF, HALF_Vec3> half_vec3() {
            return new AttribTypeStageDefaultOpts<>(attrib, THREE.THREE, HALF.HALF);
        }

        public AttribTypeStageDefaultOpts<E, THREE, FLOAT, FLOAT_Vec3> float32_vec3() {
            return new AttribTypeStageDefaultOpts<>(attrib, THREE.THREE, FLOAT.FLOAT);
        }

        // vec 4
        public AttribTypeStageFixedPointOptQs<E, FOUR, UINT8, UINT8_Vec4> uint8_vec4() {
            return new AttribTypeStageFixedPointOptQs<>(attrib, FOUR.FOUR, UINT8.UINT8);
        }

        public AttribTypeStageFixedPointOptQs<E, FOUR, UINT10, UINT10_Vec4> uint10_vec4() {
            return new AttribTypeStageFixedPointOptQs<>(attrib, FOUR.FOUR, UINT10.UINT10);
        }

        public AttribTypeStageFixedPointOptQs<E, FOUR, INT16, INT16_Vec4> int16_vec4() {
            return new AttribTypeStageFixedPointOptQs<>(attrib, FOUR.FOUR, INT16.INT16);
        }

        public AttribTypeStageDefaultOpts<E, FOUR, HALF, HALF_Vec4> half_vec4() {
            return new AttribTypeStageDefaultOpts<>(attrib, FOUR.FOUR, HALF.HALF);
        }

        public AttribTypeStageDefaultOpts<E, FOUR, FLOAT, FLOAT_Vec4> float32_vec4() {
            return new AttribTypeStageDefaultOpts<>(attrib, FOUR.FOUR, FLOAT.FLOAT);
        }

    }

    // asks BOTH optional questions at once, or allows both to be skipped (defaulting to false)
    public class AttribTypeStageFixedPointOptQs<E extends BgfxAttrib, N extends Num, T extends BgfxAttribType, V extends Vec<N, T>> extends AttribTypeStageDefaultOpts<E, N, T, V> {

        protected AttribTypeStageFixedPointOptQs(E attrib, N num, T type) {
            super(attrib, num, type);
        }

        public AttribTypeStageFixedPointOptQ1T<E, N, T, V> normalized() {
            return new AttribTypeStageFixedPointOptQ1T<>(attrib, num, type);
        }

        public AttribTypeStageFixedPointOptQ2T<E, N, T, V> asInt() {
            return new AttribTypeStageFixedPointOptQ2T<>(attrib, num, type);
        }

    }

    public class AttribTypeStageFixedPointOptQ1T<E extends BgfxAttrib, N extends Num, T extends BgfxAttribType, V extends Vec<N, T>> extends AttribTypeStageDefaultOpts<E, N, T, V> {
        protected AttribTypeStageFixedPointOptQ1T(E attrib, N num, T type) {
            super(attrib, num, type, true, false);
        }

        public AttribTypeStageDefaultOpts<E, N, T, V> asInt() {
            return new AttribTypeStageDefaultOpts<>(attrib, num, type, true, true);
        }
    }

    public class AttribTypeStageFixedPointOptQ2T<E extends BgfxAttrib, N extends Num, T extends BgfxAttribType, V extends Vec<N, T>> extends AttribTypeStageDefaultOpts<E, N, T, V> {
        protected AttribTypeStageFixedPointOptQ2T(E attrib, N num, T type) {
            super(attrib, num, type, false, true);
        }

        public AttribTypeStageDefaultOpts<E, N, T, V> normalized() {
            return new AttribTypeStageDefaultOpts<>(attrib, num, type, true, true);
        }
    }

    public class AttribTypeStageDefaultOpts<E extends BgfxAttrib, N extends Num, T extends BgfxAttribType, V extends Vec<N, T>> {
        final E attrib;

        final N num;

        final T type;

        final boolean normalized;

        final boolean asInt;

        AttribTypeStageDefaultOpts(E attrib, N num, T type) {
            this(attrib, num, type, false, false);
        }

        AttribTypeStageDefaultOpts(E attrib, N num, T type, boolean normalized, boolean asInt) {
            this.attrib = attrib;
            this.num = num;
            this.type = type;
            this.normalized = normalized;
            this.asInt = asInt;
        }

        final VertexLayoutStructBuilder14<E1, N1, T1, V1, E2, N2, T2, V2, E3, N3, T3, V3, E4, N4, T4, V4, E5, N5, T5, V5, E6, N6, T6, V6, E7, N7, T7, V7, E8, N8, T8, V8, E9, N9, T9, V9, E10, N10, T10, V10, E11, N11, T11, V11, E12, N12, T12, V12, E13, N13, T13, V13, E, N, T, V> newLast(
        ) {
            return new VertexLayoutStructBuilder14<>(last, attrib, num, type, normalized, asInt);
        }

        public final VertexLayoutStruct14<E1, N1, T1, V1, E2, N2, T2, V2, E3, N3, T3, V3, E4, N4, T4, V4, E5, N5, T5, V5, E6, N6, T6, V6, E7, N7, T7, V7, E8, N8, T8, V8, E9, N9, T9, V9, E10, N10, T10, V10, E11, N11, T11, V11, E12, N12, T12, V12, E13, N13, T13, V13, E, N, T, V> build(
        ) {
            return build(Capabilities.getRendererType());
        }

        public final VertexLayoutStruct14<E1, N1, T1, V1, E2, N2, T2, V2, E3, N3, T3, V3, E4, N4, T4, V4, E5, N5, T5, V5, E6, N6, T6, V6, E7, N7, T7, V7, E8, N8, T8, V8, E9, N9, T9, V9, E10, N10, T10, V10, E11, N11, T11, V11, E12, N12, T12, V12, E13, N13, T13, V13, E, N, T, V> build(
                @NotNull BGFX_RENDERER_TYPE rendererType) {
            final VertexLayoutStructBuilder<?, ?, ?, ?>[] builders = VertexLayoutStructBuilder.createBuildersArray(14, newLast());
            final BGFXVertexLayout layout = VertexLayoutStructBuilder.createLayout(rendererType, builders);
            return new VertexLayoutStruct14<>(layout);
        }

        public final TypedVertexLayoutBuilder15<E1, N1, T1, V1, E2, N2, T2, V2, E3, N3, T3, V3, E4, N4, T4, V4, E5, N5, T5, V5, E6, N6, T6, V6, E7, N7, T7, V7, E8, N8, T8, V8, E9, N9, T9, V9, E10, N10, T10, V10, E11, N11, T11, V11, E12, N12, T12, V12, E13, N13, T13, V13, E, N, T, V>.InitialStage then(
        ) {
            final TypedVertexLayoutBuilder15<E1, N1, T1, V1, E2, N2, T2, V2, E3, N3, T3, V3, E4, N4, T4, V4, E5, N5, T5, V5, E6, N6, T6, V6, E7, N7, T7, V7, E8, N8, T8, V8, E9, N9, T9, V9, E10, N10, T10, V10, E11, N11, T11, V11, E12, N12, T12, V12, E13, N13, T13, V13, E, N, T, V> builder = new TypedVertexLayoutBuilder15<>(newLast());
            return builder.initialStage();
        }
    }


}
