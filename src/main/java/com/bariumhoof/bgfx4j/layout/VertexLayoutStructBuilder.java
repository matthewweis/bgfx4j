package com.bariumhoof.bgfx4j.layout;

import com.bariumhoof.assertions.Assertions;
import com.bariumhoof.bgfx4j.enums.BGFX_ATTRIB;
import com.bariumhoof.bgfx4j.enums.BGFX_RENDERER_TYPE;
import org.jetbrains.annotations.NotNull;
import org.lwjgl.bgfx.BGFXVertexLayout;

import java.util.EnumSet;
import java.util.Set;

import static org.lwjgl.bgfx.BGFX.*;

public class VertexLayoutStructBuilder<V extends Vec<?,?>> {

//    @Nullable
//    abstract VertexLayoutStructBuilder<?> previousBuilder();

    final VertexLayoutStructBuilder<?> last;
    final BgfxAttrib attrib;
    final V vec;
    final boolean normalized;
    final boolean asInt;

    static class VertexLayoutStructBuilderTemplate<V extends Vec<?,?>> {
        final BgfxAttrib attrib;
        final V vec;
        final boolean normalized;
        final boolean asInt;
        VertexLayoutStructBuilderTemplate(BgfxAttrib attrib, V vec, boolean normalized, boolean asInt) {
            this.attrib = attrib;
            this.vec = vec;
            this.normalized = normalized;
            this.asInt = asInt;
        }
    }

    protected VertexLayoutStructBuilder(VertexLayoutStructBuilder<?> last, BgfxAttrib attrib, V vec, boolean normalized, boolean asInt) {
        this.last = last;
        this.attrib = attrib;
        this.vec = vec;
        this.normalized = normalized;
        this.asInt = asInt;
        errorOnDuplicateAttribute();
    }

//    protected VertexLayoutStructBuilder(VertexLayoutStructBuilderTemplate<V> template) {
//        this(template, template.attrib, template.vec, template.normalized, template.asInt);
//    }

    static VertexLayoutStructBuilder<?>[] createBuildersArray(int size, VertexLayoutStructBuilder<?> lastBuilder) {
        VertexLayoutStructBuilder<?>[] builders = new VertexLayoutStructBuilder<?>[size];

        VertexLayoutStructBuilder<?> b = lastBuilder;
        for (int i=size-1; i >= 0; i--) {
            builders[i] = b;
            b = b.last;
        }

        return builders;
    }

    @NotNull
    static BGFXVertexLayout createLayout(@NotNull BGFX_RENDERER_TYPE rendererType, VertexLayoutStructBuilder<?>[] builders) {
        final BGFXVertexLayout layout = BGFXVertexLayout.calloc();

        bgfx_vertex_layout_begin(layout, rendererType.VALUE);
        VertexLayoutStructBuilder<?> lastBuilder = null;
        for (int i=0; i < builders.length; i++) {
            lastBuilder = builders[i];
            enforceKhronosSpec(lastBuilder.vec, lastBuilder.normalized, lastBuilder.asInt);
            bgfx_vertex_layout_add(layout, lastBuilder.attrib.representedType().VALUE, lastBuilder.vec.number().value(),
                    lastBuilder.vec.type().representedType().VALUE, lastBuilder.normalized, lastBuilder.asInt);
        }
        bgfx_vertex_layout_end(layout);
        return layout;
    }

    static void enforceKhronosSpec(Vec<?,?> vec, boolean normalized, boolean asInt) {
        // https://www.khronos.org/opengl/wiki/Vertex_Specification#Component_type

        final BgfxAttribType type = vec.type();
        final Num num = vec.number();

        switch (type.representedType()) {
            case UINT10: // 32 bits as 3 or 4. All collectively form into a 32 bit value
                // calls must be 3 or 4 https://www.khronos.org/registry/OpenGL/extensions/OES/OES_vertex_type_10_10_10_2.txt
                Assertions.require(num == Num.THREE || num == Num.FOUR);
                return;
            case HALF: // 16 bit float
            case FLOAT: // 32 bits
                Assertions.require(!normalized && !asInt); // must be false for all floating point types
                return;
            case UINT8:
                return;
            case INT16:
                return;
            default: throw new IllegalStateException("Pattern match must be exhaustive");
        }
    }

    void errorOnDuplicateAttribute() {
        // todo add to build() at end or check as building -- dont check all per step
        Set<BGFX_ATTRIB> attribs = EnumSet.noneOf(BGFX_ATTRIB.class);
        VertexLayoutStructBuilder<?> builder = this;
        while (builder != null) {
            final BGFX_ATTRIB next = builder.attrib.representedType();
            if (attribs.contains(next)) {
                throw new IllegalStateException("VertexLayoutStruct cannot build: duplicate attribute " + attrib);
            }
            attribs.add(next);
            builder = builder.last;
        }
    }
}
