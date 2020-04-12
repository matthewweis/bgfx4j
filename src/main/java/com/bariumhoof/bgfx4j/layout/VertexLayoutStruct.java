package com.bariumhoof.bgfx4j.layout;

import com.bariumhoof.Capabilities;
import com.bariumhoof.bgfx4j.enums.BGFX_ATTRIB;
import com.bariumhoof.bgfx4j.enums.BGFX_ATTRIB_TYPE;
import com.bariumhoof.bgfx4j.enums.BGFX_RENDERER_TYPE;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.lwjgl.bgfx.BGFXVertexLayout;

import static org.lwjgl.bgfx.BGFX.*;

public abstract class VertexLayoutStruct {

    @Getter
    private final BGFXVertexLayout layout;

    protected VertexLayoutStruct(BGFXVertexLayout layout) {
        this.layout = layout;
    }


//    static <T extends VertexLayoutStruct<?>> Position<T> position() {
//        return new Position<>();
//    }

    abstract class Builder<E extends BGFX_ATTRIB, T extends BGFX_ATTRIB_TYPE> {
        protected Builder(E attrib, int num, T type, boolean normalized, boolean asInt) {
            this.attrib = attrib;
            this.num = num;
            this.type = type;
            this.normalized = normalized;
            this.asInt = asInt;
        }

        @Nullable abstract Builder<?, ?> previousBuilder();
        final E attrib;
        final int num;
        final T type;
        final boolean normalized;
        final boolean asInt;
    }

    public class Builder1<E1 extends BGFX_ATTRIB, T1 extends BGFX_ATTRIB_TYPE> extends Builder<E1, T1> {

        protected Builder1(E1 attrib, int num, T1 type, boolean normalized, boolean asInt) {
            super(attrib, num, type, normalized, asInt);
        }

        public VertexLayoutStruct1<E1, T1> build() {
            return build(Capabilities.getRendererType());
        }

        public VertexLayoutStruct1<E1, T1> build(@NotNull BGFX_RENDERER_TYPE rendererType) {
            final Builder<?, ?>[] builders = createBuildersArray(2, this);
            final BGFXVertexLayout layout = createLayout(rendererType, builders);
            return new VertexLayoutStruct1<>(layout);
        }

        public <E2 extends BGFX_ATTRIB, T2 extends BGFX_ATTRIB_TYPE> Builder2<E1, T1, E2, T2> thenUse(E2 attrib, int num, T2 type, boolean normalized, boolean asInt) {
            return new Builder2<E1, T1, E2, T2>(this, num, type, normalized, asInt, attrib);
        }

        @Override
        @Nullable
        public Builder<?, ?> previousBuilder() {
            return null;
        }
    }

    public class Builder2<E1 extends BGFX_ATTRIB, T1 extends BGFX_ATTRIB_TYPE, E2 extends BGFX_ATTRIB, T2 extends BGFX_ATTRIB_TYPE> extends Builder<E2, T2> {
        private final Builder1<E1, T1> last;

        protected Builder2(Builder1<E1, T1> last, int num, T2 type, boolean normalized, boolean asInt, E2 attrib) {
            super(attrib, num, type, normalized, asInt);
            this.last = last;
        }

        public VertexLayoutStruct2<E1, T1, E2, T2> build() {
            return build(Capabilities.getRendererType());
        }

        public VertexLayoutStruct2<E1, T1, E2, T2> build(@NotNull BGFX_RENDERER_TYPE rendererType) {
            final Builder<?, ?>[] builders = createBuildersArray(2, this);
            final BGFXVertexLayout layout = createLayout(rendererType, builders);
            return new VertexLayoutStruct2<>(layout);
        }

        @Override
        @Nullable Builder<E1, T1> previousBuilder() {
            return last;
        }

//        public <E3 extends BGFX_ATTRIB, T3 extends BGFX_ATTRIB_TYPE> Builder3<E1, T1, E2, T2> thenUse(E2 attrib2, int num2, T2 type2, boolean normalized2, boolean asInt2) {
//            return new Builder3<>(this, attrib2, num2, type2, normalized2, asInt2);
//        }
    }

    private static Builder<?, ?>[] createBuildersArray(int size, Builder<?, ?> lastBuilder) {
        Builder<?, ?>[] builders = new Builder<?, ?>[size];

        Builder<?, ?> b = lastBuilder;
        for (int i=size-1; i >= 0; i--) {
            builders[i] = b.previousBuilder();
            b = b.previousBuilder();
        }

        return builders;
    }

    @NotNull
    private static BGFXVertexLayout createLayout(@NotNull BGFX_RENDERER_TYPE rendererType, Builder<?, ?>[] builders) {
        final BGFXVertexLayout layout = BGFXVertexLayout.calloc();

        bgfx_vertex_layout_begin(layout, rendererType.VALUE);
        Builder<?, ?> lastBuilder = null;
        for (int i=0; i < builders.length; i++) {
            lastBuilder = builders[i];
            bgfx_vertex_layout_add(layout, lastBuilder.attrib.VALUE, lastBuilder.num, lastBuilder.type.VALUE, lastBuilder.normalized, lastBuilder.asInt);
        }
        bgfx_vertex_layout_end(layout);
        return layout;
    }

    public static class VertexLayoutStruct1<E1 extends BGFX_ATTRIB, T1 extends BGFX_ATTRIB_TYPE> extends VertexLayoutStruct {
        protected VertexLayoutStruct1(BGFXVertexLayout layout) {
            super(layout);
        }
    }

    public static class VertexLayoutStruct2<E1 extends BGFX_ATTRIB, T1 extends BGFX_ATTRIB_TYPE,
            E2 extends BGFX_ATTRIB, T2 extends BGFX_ATTRIB_TYPE> extends VertexLayoutStruct {

        protected VertexLayoutStruct2(BGFXVertexLayout layout) {
            super(layout);
        }
    }

    public static class VertexLayoutStruct3<
            E1 extends BGFX_ATTRIB, T1 extends BGFX_ATTRIB_TYPE,
            E2 extends BGFX_ATTRIB, T2 extends BGFX_ATTRIB_TYPE,
            E3 extends BGFX_ATTRIB, T3 extends BGFX_ATTRIB_TYPE> extends VertexLayoutStruct {

        protected VertexLayoutStruct3(BGFXVertexLayout layout) {
            super(layout);
        }
    }

}