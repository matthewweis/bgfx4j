package com.bariumhoof.bgfx4j.layout;

import com.bariumhoof.bgfx4j.layout.BgfxAttrib.*;
import com.bariumhoof.bgfx4j.layout.Vec.*;
import org.jetbrains.annotations.NotNull;

/*
 todo add warnings when users don't use best practices, see:
 https://www.khronos.org/opengl/wiki/Vertex_Specification_Best_Practices
 */

public abstract class VertexLayoutStrideBuilder {

    protected abstract <E extends BgfxAttrib, V extends Vec<?,?>, R extends VertexLayoutStructBuilder<V>>
    R factory(E attrib, V vec, boolean normalized, boolean asInt);

    VertexLayoutStrideBuilder.InitialStage begin() {
        return new InitialStage();
    }

    public class InitialStage {

        protected InitialStage() { }

        @NotNull
        public final AttribStage<POSITION> position() {
            return new AttribStage<POSITION>(POSITION.POSITION);
        }

        @NotNull
        public final AttribStage<NORMAL> normal() {
            return new AttribStage<NORMAL>(NORMAL.NORMAL);
        }

        @NotNull
        public final AttribStage<TANGENT> tangent() {
            return new AttribStage<TANGENT>(TANGENT.TANGENT);
        }

        @NotNull
        public final AttribStage<BITANGENT> bitangent() {
            return new AttribStage<BITANGENT>(BITANGENT.BITANGENT);
        }

        @NotNull
        public final AttribStage<COLOR0> color0() {
            return new AttribStage<COLOR0>(COLOR0.COLOR0);
        }

        @NotNull
        public final AttribStage<COLOR1> color1() {
            return new AttribStage<COLOR1>(COLOR1.COLOR1);
        }

        @NotNull
        public final AttribStage<COLOR2> color2() {
            return new AttribStage<COLOR2>(COLOR2.COLOR2);
        }

        @NotNull
        public final AttribStage<COLOR3> color3() {
            return new AttribStage<COLOR3>(COLOR3.COLOR3);
        }

        @NotNull
        public final AttribStage<INDICES> indices() {
            return new AttribStage<INDICES>(INDICES.INDICES);
        }

        @NotNull
        public final AttribStage<WEIGHT> weight() {
            return new AttribStage<WEIGHT>(WEIGHT.WEIGHT);
        }

        @NotNull
        public final AttribStage<TEXCOORD0> texcoord0() {
            return new AttribStage<TEXCOORD0>(TEXCOORD0.TEXCOORD0);
        }

        @NotNull
        public final AttribStage<TEXCOORD1> texcoord1() {
            return new AttribStage<TEXCOORD1>(TEXCOORD1.TEXCOORD1);
        }

        @NotNull
        public final AttribStage<TEXCOORD2> texcoord2() {
            return new AttribStage<TEXCOORD2>(TEXCOORD2.TEXCOORD2);
        }

        @NotNull
        public final AttribStage<TEXCOORD3> texcoord3() {
            return new AttribStage<TEXCOORD3>(TEXCOORD3.TEXCOORD3);
        }

        @NotNull
        public final AttribStage<TEXCOORD4> texcoord4() {
            return new AttribStage<TEXCOORD4>(TEXCOORD4.TEXCOORD4);
        }

        @NotNull
        public final AttribStage<TEXCOORD5> texcoord5() {
            return new AttribStage<TEXCOORD5>(TEXCOORD5.TEXCOORD5);
        }

        @NotNull
        public final AttribStage<TEXCOORD6> texcoord6() {
            return new AttribStage<TEXCOORD6>(TEXCOORD6.TEXCOORD6);
        }

        @NotNull
        public final AttribStage<TEXCOORD7> texcoord7() {
            return new AttribStage<TEXCOORD7>(TEXCOORD7.TEXCOORD7);
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

        public AttribTypeStageFloatingPoint<E,HALF_Vec1> half_vec1() {
            return new AttribTypeStageFloatingPoint<>(attrib, Vec.default_half_vec1);
        }

        public AttribTypeStageFloatingPoint<E,FLOAT_Vec1> float_vec1() {
            return new AttribTypeStageFloatingPoint<>(attrib, Vec.default_float_vec1);
        }

        // vec 2
        public AttribTypeStageFixedPointOptQs<E,UINT8_Vec2> uint8_vec2() {
            return new AttribTypeStageFixedPointOptQs<>(attrib, Vec.default_uint8_vec2);
        }

        public AttribTypeStageFixedPointOptQs<E,INT16_Vec2> int16_vec2() {
            return new AttribTypeStageFixedPointOptQs<>(attrib, Vec.default_int16_vec2);
        }

        public AttribTypeStageFloatingPoint<E,HALF_Vec2> half_vec2() {
            return new AttribTypeStageFloatingPoint<>(attrib, Vec.default_half_vec2);
        }

        public AttribTypeStageFloatingPoint<E,FLOAT_Vec2> float_vec2() {
            return new AttribTypeStageFloatingPoint<>(attrib, Vec.default_float_vec2);
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

        public AttribTypeStageFloatingPoint<E,HALF_Vec3> half_vec3() {
            return new AttribTypeStageFloatingPoint<>(attrib, Vec.default_half_vec3);
        }

        public AttribTypeStageFloatingPoint<E,FLOAT_Vec3> float_vec3() {
            return new AttribTypeStageFloatingPoint<>(attrib, Vec.default_float_vec3);
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

        public AttribTypeStageFloatingPoint<E,HALF_Vec4> half_vec4() {
            return new AttribTypeStageFloatingPoint<>(attrib, Vec.default_half_vec4);
        }

        public AttribTypeStageFloatingPoint<E,FLOAT_Vec4> float_vec4() {
            return new AttribTypeStageFloatingPoint<>(attrib, Vec.default_float_vec4);
        }

    }

    // asks BOTH optional questions at once, or allows both to be skipped (defaulting to false)
    public class AttribTypeStageFixedPointOptQs<E extends BgfxAttrib, V extends Vec<?,?>> extends AttribTypeStageFixedPoint<E,V> {

        protected AttribTypeStageFixedPointOptQs(E attrib, V vec) {
            super(attrib, vec);
        }

        public AttribTypeStageFixedPoint<E,V> normalized() {
            return new AttribTypeStageFixedPointOptQ2<>(attrib, vec, true);
        }

        public AttribTypeStageFixedPoint<E,V> asInt() {
            return new AttribTypeStageFixedPointOptQ1<>(attrib, vec, true);
        }

    }

    public class AttribTypeStageFixedPointOptQ1<E extends BgfxAttrib, V extends Vec<?,?>> extends AttribTypeStageFixedPoint<E,V> {

        final boolean asInt;

        protected AttribTypeStageFixedPointOptQ1(E attrib, V vec, boolean asInt) {
            super(attrib, vec);
            this.asInt = asInt;
        }

        public AttribTypeStageFixedPointOptQ2<E,V> normalized() {
            return new AttribTypeStageFixedPointOptQ2<>(attrib, vec, true);
        }

        @Override
        public <R extends VertexLayoutStructBuilder<V>> R build() {
            // floating point types cannot be normalized nor can they be represented as ints
            // so this builder protected the user from making the mistake by setting both to false
            return factory(attrib, vec, false, asInt);
        }
    }

    public class AttribTypeStageFixedPointOptQ2<E extends BgfxAttrib, V extends Vec<?,?>> extends AttribTypeStageFixedPoint<E,V> {

        final boolean normalized;

        protected AttribTypeStageFixedPointOptQ2(E attrib, V vec, boolean normalized) {
            super(attrib, vec);
            this.normalized = normalized;
        }

        public AttribTypeStageFixedPointOptQ1<E,V> asInt() {
            return new AttribTypeStageFixedPointOptQ1<>(attrib, vec, true);
        }

        @Override
        public <R extends VertexLayoutStructBuilder<V>> R build() {
            // floating point types cannot be normalized nor can they be represented as ints
            // so this builder protected the user from making the mistake by setting both to false
            return factory(attrib, vec, normalized, false);
        }

    }

    public class AttribTypeStageFixedPoint<E extends BgfxAttrib, V extends Vec<?,?>> {
        final E attrib;
        final V vec;

        protected AttribTypeStageFixedPoint(E attrib, V vec) {
            this.attrib = attrib;
            this.vec = vec;
        }

        public <R extends VertexLayoutStructBuilder<V>> R build() {
            return factory(attrib, vec, false, false);
        }

    }

    public class AttribTypeStageFloatingPoint<E extends BgfxAttrib, V extends Vec<?,?>> {
        final E attrib;
        final V vec;

        protected AttribTypeStageFloatingPoint(E attrib, V vec) {
            this.attrib = attrib;
            this.vec = vec;
        }

        public <R extends VertexLayoutStructBuilder<V>> R build() {
            // floating point types cannot be normalized nor can they be represented as ints
            // so this builder protected the user from making the mistake by setting both to false
            return factory(attrib, vec, false, false);
        }

    }
}
