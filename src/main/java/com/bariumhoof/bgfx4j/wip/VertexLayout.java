package com.bariumhoof.bgfx4j.wip;


import com.bariumhoof.Capabilities;
import com.bariumhoof.bgfx4j.Disposable;
import com.bariumhoof.bgfx4j.enums.BGFX_ATTRIB;
import com.bariumhoof.bgfx4j.enums.BGFX_ATTRIB_TYPE;
import com.bariumhoof.bgfx4j.enums.BGFX_RENDERER_TYPE;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.lwjgl.bgfx.BGFXVertexLayout;

import static org.lwjgl.bgfx.BGFX.*;

// todo make this use builder lib
@Slf4j
public final class VertexLayout implements Disposable {

    private final static boolean AS_INT_DEFAULT = false;

    private final static boolean NORMALIZED_DEFAULT = false;

    private final BGFXVertexLayout decl;

    private VertexLayout(BGFXVertexLayout decl) {
        this.decl = decl;
    }

    public static BuilderInitialStage builder() {
        return new BuilderInitialStage(Capabilities.getRendererType());
    }

    @Deprecated // depreciate while deciding if we want this option (since capabilities covers this now)
    public static BuilderInitialStage builder(@NotNull BGFX_RENDERER_TYPE rendererType) {
        return new BuilderInitialStage(rendererType);
    }

    public void decode() {
        // todo implement me. not sure
        throw new RuntimeException("not yet implemented");
    }

    public boolean has(@NotNull BGFX_ATTRIB attrib) {
        return bgfx_vertex_layout_has(decl, attrib.VALUE);
    }

    public static VertexLayout skip(@NotNull VertexLayout source, int num) {
        return new VertexLayout(bgfx_vertex_layout_skip(source.decl, num));
    }

    public VertexLayout skip(int num) {
        return new VertexLayout(bgfx_vertex_layout_skip(decl, num));
    }

    public BGFXVertexLayout get() {
        return decl;
    }

    @Override
    public void dispose() {
        decl.free();
    }

    public static class BuilderInitialStage {

        private final BGFXVertexLayout decl;

        private BuilderInitialStage(BGFX_RENDERER_TYPE renderer) {
            this.decl = BGFXVertexLayout.calloc();
            bgfx_vertex_layout_begin(decl, renderer.VALUE);
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
            bgfx_vertex_layout_add(decl, attrib.VALUE, getNumFromAttrib(attrib), type.VALUE, normalized, asInt);
            return new BuilderSurplusStage(decl);
        }
    }

    public static class BuilderSurplusStage {

        private final BGFXVertexLayout decl;

        private BuilderSurplusStage(BGFXVertexLayout decl) {
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
            bgfx_vertex_layout_add(decl, attrib.VALUE, getNumFromAttrib(attrib), type.VALUE, normalized, asInt);
            return new BuilderSurplusStage(decl);
        }

        public VertexLayout build() {
            bgfx_vertex_layout_end(decl);
            return new VertexLayout(decl);
        }
    }

    private static boolean hasWarned = false;
    private static int getNumFromAttrib(BGFX_ATTRIB attrib) {
        switch (attrib) {
            case POSITION:
                return 3;
            case NORMAL:
                if (!hasWarned) {
                    log.warn("Using NORMAL getNumFromAttrib, however this seems to vary (4 for Bump, 3 for Metaballs)");
                    hasWarned = false;
                }
                return 3;
//                return 4; // todo does this vary??
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
