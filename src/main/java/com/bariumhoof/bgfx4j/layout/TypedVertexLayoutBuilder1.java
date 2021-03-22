package com.bariumhoof.bgfx4j.layout;

import com.bariumhoof.Capabilities;
import com.bariumhoof.bgfx4j.enums.BGFX_RENDERER_TYPE;
import org.jetbrains.annotations.NotNull;
import org.lwjgl.bgfx.BGFXVertexLayout;

public class TypedVertexLayoutBuilder1 {

    TypedVertexLayoutBuilder1() { }

    // staging for Builder1, the all contain for next
    public static class InitialStage {

        protected InitialStage() { }

        @NotNull
        public final AttribStage<BgfxAttrib.POSITION> position() {
            return new AttribStage<>(BgfxAttrib.POSITION.POSITION);
        }

        @NotNull
        public final AttribStage<BgfxAttrib.NORMAL> normal() {
            return new AttribStage<>(BgfxAttrib.NORMAL.NORMAL);
        }

        @NotNull
        public final AttribStage<BgfxAttrib.TANGENT> tangent() {
            return new AttribStage<>(BgfxAttrib.TANGENT.TANGENT);
        }

        @NotNull
        public final AttribStage<BgfxAttrib.BITANGENT> bitangent() {
            return new AttribStage<>(BgfxAttrib.BITANGENT.BITANGENT);
        }

        @NotNull
        public final AttribStage<BgfxAttrib.COLOR0> color0() {
            return new AttribStage<>(BgfxAttrib.COLOR0.COLOR0);
        }

        @NotNull
        public final AttribStage<BgfxAttrib.COLOR1> color1() {
            return new AttribStage<>(BgfxAttrib.COLOR1.COLOR1);
        }

        @NotNull
        public final AttribStage<BgfxAttrib.COLOR2> color2() {
            return new AttribStage<>(BgfxAttrib.COLOR2.COLOR2);
        }

        @NotNull
        public final AttribStage<BgfxAttrib.COLOR3> color3() {
            return new AttribStage<>(BgfxAttrib.COLOR3.COLOR3);
        }

        @NotNull
        public final AttribStage<BgfxAttrib.INDICES> indices() {
            return new AttribStage<>(BgfxAttrib.INDICES.INDICES);
        }

        @NotNull
        public final AttribStage<BgfxAttrib.WEIGHT> weight() {
            return new AttribStage<>(BgfxAttrib.WEIGHT.WEIGHT);
        }

        @NotNull
        public final AttribStage<BgfxAttrib.TEXCOORD0> texcoord0() {
            return new AttribStage<>(BgfxAttrib.TEXCOORD0.TEXCOORD0);
        }

        @NotNull
        public final AttribStage<BgfxAttrib.TEXCOORD1> texcoord1() {
            return new AttribStage<>(BgfxAttrib.TEXCOORD1.TEXCOORD1);
        }

        @NotNull
        public final AttribStage<BgfxAttrib.TEXCOORD2> texcoord2() {
            return new AttribStage<>(BgfxAttrib.TEXCOORD2.TEXCOORD2);
        }

        @NotNull
        public final AttribStage<BgfxAttrib.TEXCOORD3> texcoord3() {
            return new AttribStage<>(BgfxAttrib.TEXCOORD3.TEXCOORD3);
        }

        @NotNull
        public final AttribStage<BgfxAttrib.TEXCOORD4> texcoord4() {
            return new AttribStage<>(BgfxAttrib.TEXCOORD4.TEXCOORD4);
        }

        @NotNull
        public final AttribStage<BgfxAttrib.TEXCOORD5> texcoord5() {
            return new AttribStage<>(BgfxAttrib.TEXCOORD5.TEXCOORD5);
        }

        @NotNull
        public final AttribStage<BgfxAttrib.TEXCOORD6> texcoord6() {
            return new AttribStage<>(BgfxAttrib.TEXCOORD6.TEXCOORD6);
        }

        @NotNull
        public final AttribStage<BgfxAttrib.TEXCOORD7> texcoord7() {
            return new AttribStage<>(BgfxAttrib.TEXCOORD7.TEXCOORD7);
        }

    }

    public static class AttribStage<E extends BgfxAttrib> {
        final E attrib;

        protected AttribStage(E attrib) {
            this.attrib = attrib;
        }

        // vec 1
        public AttribTypeStageFixedPointOptQs<E, Vec.UINT8_Vec1> uint8_vec1() {
            return new AttribTypeStageFixedPointOptQs<>(attrib, Vec.default_uint8_vec1);
        }

        public AttribTypeStageFixedPointOptQs<E, Vec.INT16_Vec1> int16_vec1() {
            return new AttribTypeStageFixedPointOptQs<>(attrib, Vec.default_int16_vec1);
        }

        public AttribTypeStageDefaultOpts<E, Vec.HALF_Vec1> half_vec1() {
            return new AttribTypeStageDefaultOpts<>(attrib, Vec.default_half_vec1);
        }

        public AttribTypeStageDefaultOpts<E, Vec.FLOAT_Vec1> float_vec1() {
            return new AttribTypeStageDefaultOpts<>(attrib, Vec.default_float_vec1);
        }

        // vec 2
        public AttribTypeStageFixedPointOptQs<E, Vec.UINT8_Vec2> uint8_vec2() {
            return new AttribTypeStageFixedPointOptQs<>(attrib,Vec.default_uint8_vec2);
        }

        public AttribTypeStageFixedPointOptQs<E, Vec.INT16_Vec2> int16_vec2() {
            return new AttribTypeStageFixedPointOptQs<>(attrib, Vec.default_int16_vec2);
        }

        public AttribTypeStageDefaultOpts<E, Vec.HALF_Vec2> half_vec2() {
            return new AttribTypeStageDefaultOpts<>(attrib, Vec.default_half_vec2);
        }

        public AttribTypeStageDefaultOpts<E, Vec.FLOAT_Vec2> float_vec2() {
            return new AttribTypeStageDefaultOpts<>(attrib, Vec.default_float_vec2);
        }

        // vec 3
        public AttribTypeStageFixedPointOptQs<E, Vec.UINT8_Vec3> uint8_vec3() {
            return new AttribTypeStageFixedPointOptQs<>(attrib, Vec.default_uint8_vec3);
        }

        public AttribTypeStageFixedPointOptQs<E, Vec.UINT10_Vec3> uint10_vec3() {
            return new AttribTypeStageFixedPointOptQs<>(attrib, Vec.default_uint10_vec3);
        }

        public AttribTypeStageFixedPointOptQs<E, Vec.INT16_Vec3> int16_vec3() {
            return new AttribTypeStageFixedPointOptQs<>(attrib, Vec.default_int16_vec3);
        }

        public AttribTypeStageDefaultOpts<E, Vec.HALF_Vec3> half_vec3() {
            return new AttribTypeStageDefaultOpts<>(attrib, Vec.default_half_vec3);
        }

        public AttribTypeStageDefaultOpts<E, Vec.FLOAT_Vec3> float_vec3() {
            return new AttribTypeStageDefaultOpts<>(attrib, Vec.default_float_vec3);
        }

        // vec 4
        public AttribTypeStageFixedPointOptQs<E, Vec.UINT8_Vec4> uint8_vec4() {
            return new AttribTypeStageFixedPointOptQs<>(attrib, Vec.default_uint8_vec4);
        }

        public AttribTypeStageFixedPointOptQs<E, Vec.UINT10_Vec4> uint10_vec4() {
            return new AttribTypeStageFixedPointOptQs<>(attrib,Vec.default_uint10_vec4);
        }

        public AttribTypeStageFixedPointOptQs<E, Vec.INT16_Vec4> int16_vec4() {
            return new AttribTypeStageFixedPointOptQs<>(attrib,Vec.default_int16_vec4);
        }

        public AttribTypeStageDefaultOpts<E, Vec.HALF_Vec4> half_vec4() {
            return new AttribTypeStageDefaultOpts<>(attrib, Vec.default_half_vec4);
        }

        public AttribTypeStageDefaultOpts<E, Vec.FLOAT_Vec4> float_vec4() {
            return new AttribTypeStageDefaultOpts<>(attrib, Vec.default_float_vec4);
        }
    }

    // asks BOTH optional questions at once, or allows both to be skipped (defaulting to false)
    public static class AttribTypeStageFixedPointOptQs<E extends BgfxAttrib, V extends Vec<?,?>> extends AttribTypeStageDefaultOpts<E,V> {

        protected AttribTypeStageFixedPointOptQs(E attrib, V vec) {
            super(attrib, vec);
        }

        public AttribTypeStageDefaultOpts<E,V> normalized() {
            return new AttribTypeStageFixedPointOptQ1T<>(attrib, vec);
        }

        public AttribTypeStageDefaultOpts<E,V> asInt() {
            return new AttribTypeStageFixedPointOptQ2T<>(attrib, vec);
        }

    }

    public static class AttribTypeStageFixedPointOptQ1T<E extends BgfxAttrib, V extends Vec<?,?>> extends AttribTypeStageDefaultOpts<E,V> {
        protected AttribTypeStageFixedPointOptQ1T(E attrib, V vec) {
            super(attrib, vec, true, false);
        }

        public AttribTypeStageDefaultOpts<E,V> asInt() {
            return new AttribTypeStageDefaultOpts<>(attrib, vec, true, true);
        }
    }

    public static class AttribTypeStageFixedPointOptQ2T<E extends BgfxAttrib, V extends Vec<?,?>> extends AttribTypeStageDefaultOpts<E,V> {
        protected AttribTypeStageFixedPointOptQ2T(E attrib, V vec) {
            super(attrib, vec, false, true);
        }

        public AttribTypeStageDefaultOpts<E,V> normalized() {
            return new AttribTypeStageDefaultOpts<>(attrib, vec, true, true);
        }
    }

    public static class AttribTypeStageDefaultOpts<E extends BgfxAttrib, V extends Vec<?,?>> {
        final E attrib;
        final V vec;
        final boolean normalized;
        final boolean asInt;

        protected AttribTypeStageDefaultOpts(E attrib, V vec) {
            this(attrib, vec, false, false);
        }

        protected AttribTypeStageDefaultOpts(E attrib, V vec, boolean normalized, boolean asInt) {
            this.attrib = attrib;
            this.vec = vec;
            this.normalized = normalized;
            this.asInt = asInt;
        }

        final VertexLayoutBuilder<V> newLast() {
            return new VertexLayoutBuilder<>(null, attrib, vec, normalized, asInt);
        }

        public final VertexLayout<Vertex.Vertex1<V>> build() {
            return build(Capabilities.getRendererType());
        }

        public final VertexLayout<Vertex.Vertex1<V>> build(@NotNull BGFX_RENDERER_TYPE rendererType) {
            final VertexLayoutBuilder<?>[] builders = VertexLayoutBuilder.createBuildersArray(1, newLast());
            final BGFXVertexLayout layout = VertexLayoutBuilder.createLayout(rendererType, builders);
            return new VertexLayout<>(layout);
        }

        public final TypedVertexLayoutBuilder2<V>.InitialStage then() {
            final TypedVertexLayoutBuilder2<V> builder = new TypedVertexLayoutBuilder2<>(newLast());
            return builder.initialStage();
        }
    }

}
