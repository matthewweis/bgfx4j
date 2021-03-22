package com.bariumhoof.bgfx4j.buffer;


import com.bariumhoof.Capabilities;
import com.bariumhoof.assertions.Assertions;
import com.bariumhoof.bgfx4j.Disposable;
import com.bariumhoof.bgfx4j.enums.BGFX_ATTRIB;
import com.bariumhoof.bgfx4j.enums.BGFX_ATTRIB_TYPE;
import com.bariumhoof.bgfx4j.enums.BGFX_RENDERER_TYPE;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.lwjgl.bgfx.BGFXVertexLayout;

import static org.lwjgl.bgfx.BGFX.*;

// todo make this use builder lib
@Slf4j
public final class VertexLayoutOld implements Disposable {

    private final static boolean AS_INT_DEFAULT = false;

    private final static boolean NORMALIZED_DEFAULT = false;

    @Getter
    private final BGFXVertexLayout decl;

    @Getter
    private final int strideBytes;

    private VertexLayoutOld(BGFXVertexLayout decl, int strideBytes) {
        this.decl = decl;
        this.strideBytes = strideBytes;
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

    // todo how to impl skip if tracking strideBytes
//    public static VertexLayout skip(@NotNull VertexLayout source, int num) {
//        return new VertexLayout(bgfx_vertex_layout_skip(source.decl, num), strideBytes);
//    }

    // todo how to impl skip if tracking strideBytes
//    public VertexLayout skip(int num) {
//        return new VertexLayout(bgfx_vertex_layout_skip(decl, num), strideBytes);
//    }

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

        public BuilderSurplusStage beginWith(@NotNull BGFX_ATTRIB attrib, int num, @NotNull BGFX_ATTRIB_TYPE type) {
            return beginWith(attrib, num, type, NORMALIZED_DEFAULT, AS_INT_DEFAULT);
        }

        public BuilderSurplusStage beginWithNormalized(@NotNull BGFX_ATTRIB attrib, int num, @NotNull BGFX_ATTRIB_TYPE type) {
            return beginWith(attrib, num, type, true, AS_INT_DEFAULT);
        }

        public BuilderSurplusStage beginWithAsInt(@NotNull BGFX_ATTRIB attrib, int num, @NotNull BGFX_ATTRIB_TYPE type) {
            return beginWith(attrib, num, type, NORMALIZED_DEFAULT, true);
        }

        public BuilderSurplusStage beginWithNormalizedAsInt(@NotNull BGFX_ATTRIB attrib, int num, @NotNull BGFX_ATTRIB_TYPE type) {
            return beginWith(attrib, num, type, true, true);
        }

        public BuilderSurplusStage beginWith(@NotNull BGFX_ATTRIB attrib, int num, @NotNull BGFX_ATTRIB_TYPE type, boolean normalized, boolean asInt) {

            if (asInt) {
                // asInt can only be true for UINT8 and INT16 types
                Assertions.require(type == BGFX_ATTRIB_TYPE.UINT8 || type == BGFX_ATTRIB_TYPE.INT16);
            }

            bgfx_vertex_layout_add(decl, attrib.VALUE, num, type.VALUE, normalized, asInt);

            final int bytes;
            switch(type) {
                case FLOAT:
                    bytes = Float.BYTES;
                    break;
                case UINT8:
                    bytes = Byte.BYTES;
                    break;
                case INT16:
                    bytes = Short.BYTES;
                    break;
                case HALF:
                    throw new IllegalStateException("todo, how many bytes is this??");
                case UINT10:
                    throw new IllegalStateException("todo, how many bytes is this??");
                default:
                    throw new IllegalStateException("Unmatched type");
            }

            return new BuilderSurplusStage(decl, bytes * num);
        }
    }



    public static class BuilderSurplusStage {

        private final BGFXVertexLayout decl;
        private final int numBytes;

        private BuilderSurplusStage(BGFXVertexLayout decl, int numBytes) {
            this.decl = decl;
            this.numBytes = numBytes;
        }

        public BuilderSurplusStage thenUse(@NotNull BGFX_ATTRIB attrib, int num, @NotNull BGFX_ATTRIB_TYPE type) {
            return thenUse(attrib, num, type, NORMALIZED_DEFAULT, AS_INT_DEFAULT);
        }

        public BuilderSurplusStage thenUseNormalized(@NotNull BGFX_ATTRIB attrib, int num, @NotNull BGFX_ATTRIB_TYPE type) {
            return thenUse(attrib, num, type, true, AS_INT_DEFAULT);
        }

        public BuilderSurplusStage thenUseAsInt(@NotNull BGFX_ATTRIB attrib, int num, @NotNull BGFX_ATTRIB_TYPE type) {
            return thenUse(attrib, num, type, NORMALIZED_DEFAULT, true);
        }

        public BuilderSurplusStage thenUseNormalizedAsInt(@NotNull BGFX_ATTRIB attrib, int num, @NotNull BGFX_ATTRIB_TYPE type) {
            return thenUse(attrib, num, type, true, true);
        }

        // todo see old code save for nums associated with each
        public BuilderSurplusStage thenUse(@NotNull BGFX_ATTRIB attrib, int num, @NotNull BGFX_ATTRIB_TYPE type, boolean normalized, boolean asInt) {

            if (asInt) {
                // asInt can only be true for UINT8 and INT16 types
                Assertions.require(type == BGFX_ATTRIB_TYPE.UINT8 || type == BGFX_ATTRIB_TYPE.INT16);
            }

            bgfx_vertex_layout_add(decl, attrib.VALUE, num, type.VALUE, normalized, asInt);

            final int bytes;
            switch(type) {
                case FLOAT:
                    bytes = Float.BYTES;
                    break;
                case UINT8:
                    bytes = Byte.BYTES;
                    break;
                case INT16:
                    bytes = Short.BYTES;
                    break;
                case HALF:
                    throw new IllegalStateException("todo, how many bytes is this??");
                case UINT10:
                    throw new IllegalStateException("todo, how many bytes is this??");
                default:
                    throw new IllegalStateException("Unmatched type");
            }

            final int newNumBytes = numBytes + bytes * num;

            return new BuilderSurplusStage(decl, newNumBytes);
        }

        public VertexLayoutOld build() {
            bgfx_vertex_layout_end(decl);
            return new VertexLayoutOld(decl, numBytes);
        }
    }

//    private static boolean hasWarned = false;
//    private static int getNumFromAttrib(BGFX_ATTRIB attrib) {
//        switch (attrib) {
//            case POSITION:
//                return 3;
//            case NORMAL:
//                if (!hasWarned) {
//                    log.warn("Using NORMAL getNumFromAttrib, however this seems to vary (4 for Bump, 3 for Metaballs)");
//                    hasWarned = false;
//                }
//                return 3;
////                return 4; // todo does this vary??
//            case TANGENT:
//                return 4;
//            case BITANGENT:
//                return 4;
//            case COLOR0:
//                return 4;
//            case COLOR1:
//                return 4;
//            case COLOR2:
//                return 4;
//            case COLOR3:
//                return 4;
//            case INDICES:
//                return 4;
//            case WEIGHT:
//                log.error("weight's value has not been set.");
//                return -1; // todo: determine weight value(s)
//            case TEXCOORD0:
//                return 2;
//            case TEXCOORD1:
//                return 2;
//            case TEXCOORD2:
//                return 2;
//            case TEXCOORD3:
//                return 2;
//            case TEXCOORD4:
//                return 2;
//            case TEXCOORD5:
//                return 2;
//            case TEXCOORD6:
//                return 2;
//            case TEXCOORD7:
//                return 2;
//            default:
//                throw new IllegalStateException("Unhandled enum.");
//        }
//    }

}
