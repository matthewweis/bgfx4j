package com.bariumhoof.bgfx4j.layout;

import com.bariumhoof.Capabilities;
import com.bariumhoof.bgfx4j.enums.BGFX_RENDERER_TYPE;
import com.bariumhoof.bgfx4j.layout.BgfxAttrib.*;
import com.bariumhoof.bgfx4j.layout.Vec.*;
import org.jetbrains.annotations.NotNull;
import org.lwjgl.bgfx.BGFXVertexLayout;

public class TypedVertexLayoutBuilder4<E1 extends BgfxAttrib, V1 extends Vec<?,?>, E2 extends BgfxAttrib, V2 extends Vec<?,?>, E3 extends BgfxAttrib, V3 extends Vec<?,?>> {
    private final VertexLayoutStructBuilder3<E1, V1, E2, V2, E3, V3> last;

    TypedVertexLayoutBuilder4(
            VertexLayoutStructBuilder3<E1, V1, E2, V2, E3, V3> last) {
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
        public AttribTypeStageFixedPointOptQs<E,UINT8_Vec1> uint8_vec1() {
            return new AttribTypeStageFixedPointOptQs<>(attrib, Vec.default_uint8_vec1);
        }

        public AttribTypeStageFixedPointOptQs<E,INT16_Vec1> int16_vec1() {
            return new AttribTypeStageFixedPointOptQs<>(attrib, Vec.default_int16_vec1);
        }

        public AttribTypeStageDefaultOpts<E,HALF_Vec1> half_vec1() {
            return new AttribTypeStageDefaultOpts<>(attrib, Vec.default_half_vec1);
        }

        public AttribTypeStageDefaultOpts<E,FLOAT_Vec1> float_vec1() {
            return new AttribTypeStageDefaultOpts<>(attrib, Vec.default_float_vec1);
        }

        // vec 2
        public AttribTypeStageFixedPointOptQs<E,UINT8_Vec2> uint8_vec2() {
            return new AttribTypeStageFixedPointOptQs<>(attrib, Vec.default_uint8_vec2);
        }

        public AttribTypeStageFixedPointOptQs<E,INT16_Vec2> int16_vec2() {
            return new AttribTypeStageFixedPointOptQs<>(attrib, Vec.default_int16_vec2);
        }

        public AttribTypeStageDefaultOpts<E,HALF_Vec2> half_vec2() {
            return new AttribTypeStageDefaultOpts<>(attrib, Vec.default_half_vec2);
        }

        public AttribTypeStageDefaultOpts<E,FLOAT_Vec2> float_vec2() {
            return new AttribTypeStageDefaultOpts<>(attrib, Vec.default_float_vec2);
        }

        // vec 3
        public AttribTypeStageFixedPointOptQs<E,UINT8_Vec3> uint8_vec3() {
            return new AttribTypeStageFixedPointOptQs<>(attrib, Vec.default_uint8_vec3);
        }

        public AttribTypeStageFixedPointOptQs<E,UINT10_Vec3> uint10_vec3() {
            return new AttribTypeStageFixedPointOptQs<>(attrib, Vec.default_uint10_vec3);
        }

        public AttribTypeStageFixedPointOptQs<E,INT16_Vec3> int16_vec3() {
            return new AttribTypeStageFixedPointOptQs<>(attrib, Vec.default_int16_vec3);
        }

        public AttribTypeStageDefaultOpts<E,HALF_Vec3> half_vec3() {
            return new AttribTypeStageDefaultOpts<>(attrib, Vec.default_half_vec3);
        }

        public AttribTypeStageDefaultOpts<E,FLOAT_Vec3> float_vec3() {
            return new AttribTypeStageDefaultOpts<>(attrib, Vec.default_float_vec3);
        }

        // vec 4
        public AttribTypeStageFixedPointOptQs<E,UINT8_Vec4> uint8_vec4() {
            return new AttribTypeStageFixedPointOptQs<>(attrib, Vec.default_uint8_vec4);
        }

        public AttribTypeStageFixedPointOptQs<E,UINT10_Vec4> uint10_vec4() {
            return new AttribTypeStageFixedPointOptQs<>(attrib, Vec.default_uint10_vec4);
        }

        public AttribTypeStageFixedPointOptQs<E,INT16_Vec4> int16_vec4() {
            return new AttribTypeStageFixedPointOptQs<>(attrib, Vec.default_int16_vec4);
        }

        public AttribTypeStageDefaultOpts<E,HALF_Vec4> half_vec4() {
            return new AttribTypeStageDefaultOpts<>(attrib, Vec.default_half_vec4);
        }

        public AttribTypeStageDefaultOpts<E,FLOAT_Vec4> float_vec4() {
            return new AttribTypeStageDefaultOpts<>(attrib, Vec.default_float_vec4);
        }

    }

    // asks BOTH optional questions at once, or allows both to be skipped (defaulting to false)
    public class AttribTypeStageFixedPointOptQs<E extends BgfxAttrib, V extends Vec<?,?>> extends AttribTypeStageDefaultOpts<E, V> {

        protected AttribTypeStageFixedPointOptQs(E attrib, V vec) {
            super(attrib, vec);
        }

        public AttribTypeStageFixedPointOptQ1T<E, V> normalized() {
            return new AttribTypeStageFixedPointOptQ1T<>(attrib, vec);
        }

        public AttribTypeStageFixedPointOptQ2T<E, V> asInt() {
            return new AttribTypeStageFixedPointOptQ2T<>(attrib, vec);
        }

    }

    public class AttribTypeStageFixedPointOptQ1T<E extends BgfxAttrib, V extends Vec<?,?>> extends AttribTypeStageDefaultOpts<E, V> {
        protected AttribTypeStageFixedPointOptQ1T(E attrib, V vec) {
            super(attrib, vec, true, false);
        }

        public AttribTypeStageDefaultOpts<E, V> asInt() {
            return new AttribTypeStageDefaultOpts<>(attrib, vec, true, true);
        }
    }

    public class AttribTypeStageFixedPointOptQ2T<E extends BgfxAttrib, V extends Vec<?,?>> extends AttribTypeStageDefaultOpts<E, V> {
        protected AttribTypeStageFixedPointOptQ2T(E attrib, V vec) {
            super(attrib, vec, false, true);
        }

        public AttribTypeStageDefaultOpts<E, V> normalized() {
            return new AttribTypeStageDefaultOpts<>(attrib, vec, true, true);
        }
    }

    public class AttribTypeStageDefaultOpts<E extends BgfxAttrib, V extends Vec<?,?>> {
        final E attrib;
        final V vec;

        final boolean normalized;

        final boolean asInt;

        AttribTypeStageDefaultOpts(E attrib, V vec) {
            this(attrib, vec, false, false);
        }

        AttribTypeStageDefaultOpts(E attrib, V vec, boolean normalized, boolean asInt) {
            this.attrib = attrib;
            this.vec = vec;
            this.normalized = normalized;
            this.asInt = asInt;
        }

        final VertexLayoutStructBuilder4<E1, V1, E2, V2, E3, V3, E, V> newLast() {
            return new VertexLayoutStructBuilder4<>(last, attrib, vec, normalized, asInt);
        }

        public final VertexLayoutStruct4<V1,V2,V3,V> build() {
            return build(Capabilities.getRendererType());
        }

        public final VertexLayoutStruct4<V1,V2,V3,V> build(
                @NotNull BGFX_RENDERER_TYPE rendererType) {
            final VertexLayoutStructBuilder<?, ?>[] builders = VertexLayoutStructBuilder.createBuildersArray(4, newLast());
            final BGFXVertexLayout layout = VertexLayoutStructBuilder.createLayout(rendererType, builders);
            return new VertexLayoutStruct4<>(layout);
        }

        public final TypedVertexLayoutBuilder5<E1, V1, E2, V2, E3, V3, E, V>.InitialStage then() {
            final TypedVertexLayoutBuilder5<E1, V1, E2, V2, E3, V3, E, V> builder = new TypedVertexLayoutBuilder5<>(newLast());
            return builder.initialStage();
        }
    }


}
