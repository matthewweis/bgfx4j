package com.bariumhoof.bgfx4j.layout;

import org.jetbrains.annotations.Nullable;

public class VertexLayoutStructBuilder18<E1 extends BgfxAttrib, V1 extends Vec<?,?>,
        E2 extends BgfxAttrib, V2 extends Vec<?,?>, E3 extends BgfxAttrib, V3 extends Vec<?,?>,
        E4 extends BgfxAttrib, V4 extends Vec<?,?>, E5 extends BgfxAttrib, V5 extends Vec<?,?>,
        E6 extends BgfxAttrib, V6 extends Vec<?,?>, E7 extends BgfxAttrib, V7 extends Vec<?,?>,
        E8 extends BgfxAttrib, V8 extends Vec<?,?>, E9 extends BgfxAttrib, V9 extends Vec<?,?>,
        E10 extends BgfxAttrib, V10 extends Vec<?,?>, E11 extends BgfxAttrib, V11 extends Vec<?,?>,
        E12 extends BgfxAttrib, V12 extends Vec<?,?>, E13 extends BgfxAttrib, V13 extends Vec<?,?>,
        E14 extends BgfxAttrib, V14 extends Vec<?,?>, E15 extends BgfxAttrib, V15 extends Vec<?,?>,
        E16 extends BgfxAttrib, V16 extends Vec<?,?>, E17 extends BgfxAttrib, V17 extends Vec<?,?>,
        E18 extends BgfxAttrib, V18 extends Vec<?,?>> extends VertexLayoutStructBuilder<E18, V18> {
    private final VertexLayoutStructBuilder17<E1, V1, E2, V2, E3, V3, E4, V4, E5, V5, E6, V6, E7, V7, E8, V8, E9, V9, E10, V10, E11, V11, E12, V12, E13, V13, E14, V14, E15, V15, E16, V16, E17, V17> last;

    protected VertexLayoutStructBuilder18(VertexLayoutStructBuilder17<E1, V1, E2, V2, E3, V3, E4, V4, E5, V5, E6, V6, E7, V7, E8, V8, E9, V9, E10, V10, E11, V11, E12, V12, E13, V13, E14, V14, E15, V15, E16, V16, E17, V17> last, E18 attrib, V18 vec, boolean normalized, boolean asInt) {
        super(attrib, vec, normalized, asInt);
        this.last = last;
    }

//    public VertexLayoutStruct18<E1, V1, E2, V2, E3, V3, E4, V4, E5, V5, E6, V6, E7, V7, E8, V8, E9, V9, E10, V10, E11, V11, E12, V12, E13, V13, E14, V14, E15, V15, E16, V16, E17, V17, E18, V18> build() {
//        return build(Capabilities.getRendererType());
//    }
//
//    public VertexLayoutStruct18<E1, V1, E2, V2, E3, V3, E4, V4, E5, V5, E6, V6, E7, V7, E8, V8, E9, V9, E10, V10, E11, V11, E12, V12, E13, V13, E14, V14, E15, V15, E16, V16, E17, V17, E18, V18> build(@NotNull BGFX_RENDERER_TYPE rendererType) {
//        final VertexLayoutStructBuilder<?, ?>[] builders = createBuildersArray(18, this);
//        final BGFXVertexLayout layout = createLayout(rendererType, builders);
//        return new VertexLayoutStruct18<>(layout);
//    }

    @Override
    @Nullable VertexLayoutStructBuilder<E17, V17> previousBuilder() {
        return last;
    }
}
