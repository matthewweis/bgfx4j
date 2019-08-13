package com.bariumhoof.bgfx4j;


import com.bariumhoof.bgfx4j.enums.BGFX_ATTRIB;
import com.bariumhoof.bgfx4j.enums.BGFX_ATTRIB_TYPE;
import com.bariumhoof.bgfx4j.enums.BGFX_RENDERER_TYPE;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.lwjgl.bgfx.BGFXVertexDecl;

import static org.lwjgl.bgfx.BGFX.*;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public final class VertexDecl implements Disposable {

    private final static boolean AS_INT_DEFAULT = false;
    private final static boolean NORMALIZED_DEFAULT = false;

    private final BGFXVertexDecl decl;

    public static BuilderInitialStage builder(BGFX_RENDERER_TYPE rendererType) {
        return new BuilderInitialStage(rendererType);
    }

    public BGFXVertexDecl get() {
        return decl;
    }

    @Override
    public void dispose() {
        decl.free();
    }

    public static class BuilderInitialStage {

        private final BGFXVertexDecl decl;

        private BuilderInitialStage(BGFX_RENDERER_TYPE renderer) {
            this.decl = BGFXVertexDecl.calloc();
            bgfx_vertex_decl_begin(decl, renderer.VALUE);
        }

        public BuilderSurplusStage beginWith(@NotNull BGFX_ATTRIB attrib, @NotNull BGFX_ATTRIB_TYPE type) {
            beginWith(attrib, type, NORMALIZED_DEFAULT, AS_INT_DEFAULT);
            return new BuilderSurplusStage(decl);
        }

        public BuilderSurplusStage beginWithNormalized(@NotNull BGFX_ATTRIB attrib, @NotNull BGFX_ATTRIB_TYPE type) {
            beginWith(attrib, type, true, AS_INT_DEFAULT);
            return new BuilderSurplusStage(decl);
        }

        public BuilderSurplusStage beginWithAsInt(@NotNull BGFX_ATTRIB attrib, @NotNull BGFX_ATTRIB_TYPE type) {
            beginWith(attrib, type, NORMALIZED_DEFAULT, true);
            return new BuilderSurplusStage(decl);
        }

        public BuilderSurplusStage beginWithNormalizedAsInt(@NotNull BGFX_ATTRIB attrib, @NotNull BGFX_ATTRIB_TYPE type) {
            beginWith(attrib, type, true, true);
            return new BuilderSurplusStage(decl);
        }

        public BuilderSurplusStage beginWith(@NotNull BGFX_ATTRIB attrib, @NotNull BGFX_ATTRIB_TYPE type, boolean normalized, boolean asInt) {
            bgfx_vertex_decl_add(decl, attrib.VALUE, getNumFromAttrib(attrib), type.VALUE, normalized, asInt);
            return new BuilderSurplusStage(decl);
        }
    }

    public static class BuilderSurplusStage {

        private final BGFXVertexDecl decl;

        private BuilderSurplusStage(BGFXVertexDecl decl) {
            this.decl = decl;
        }

        public BuilderSurplusStage thenUse(@NotNull BGFX_ATTRIB attrib, @NotNull BGFX_ATTRIB_TYPE type) {
            thenUse(attrib, type, NORMALIZED_DEFAULT, AS_INT_DEFAULT);
            return new BuilderSurplusStage(decl);
        }

        public BuilderSurplusStage thenUseNormalized(@NotNull BGFX_ATTRIB attrib, @NotNull BGFX_ATTRIB_TYPE type) {
            thenUse(attrib, type, true, AS_INT_DEFAULT);
            return new BuilderSurplusStage(decl);
        }

        public BuilderSurplusStage thenUseAsInt(@NotNull BGFX_ATTRIB attrib, @NotNull BGFX_ATTRIB_TYPE type) {
            thenUse(attrib, type, NORMALIZED_DEFAULT, true);
            return new BuilderSurplusStage(decl);
        }

        public BuilderSurplusStage thenUseNormalizedAsInt(@NotNull BGFX_ATTRIB attrib, @NotNull BGFX_ATTRIB_TYPE type) {
            thenUse(attrib, type, true, true);
            return new BuilderSurplusStage(decl);
        }

        // todo see old code save for nums associated with each
        public BuilderSurplusStage thenUse(@NotNull BGFX_ATTRIB attrib, @NotNull BGFX_ATTRIB_TYPE type, boolean normalized, boolean asInt) {
            bgfx_vertex_decl_add(decl, attrib.VALUE, getNumFromAttrib(attrib), type.VALUE, normalized, asInt);
            return new BuilderSurplusStage(decl);
        }

        public VertexDecl build() {
            bgfx_vertex_decl_end(decl);
            return new VertexDecl(decl);
        }
    }

    private static int getNumFromAttrib(BGFX_ATTRIB attrib) {
        switch (attrib) {
            case POSITION:
                return 3;
            case NORMAL:
                return 4;
            case TANGENT:
                return 4;
            case BITANGENT:
                return 4;
            case COLOR0:
                return 4;
            case COLOR1:
                return 4;
            case COLOR2:
                return 4;
            case COLOR3:
                return 4;
            case INDICES:
                return 4;
            case WEIGHT:
                return -1; // todo: determine weight value(s)
            case TEXCOORD0:
                return 2;
            case TEXCOORD1:
                return 2;
            case TEXCOORD2:
                return 2;
            case TEXCOORD3:
                return 2;
            case TEXCOORD4:
                return 2;
            case TEXCOORD5:
                return 2;
            case TEXCOORD6:
                return 2;
            case TEXCOORD7:
                return 2;
            default:
                throw new IllegalStateException("Unhandled enum.");
        }
    }
}
