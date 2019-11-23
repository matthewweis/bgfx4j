package com.bariumhoof;

import com.bariumhoof.assertions.Assertions;
import com.bariumhoof.bgfx4j.enums.*;
import lombok.*;
import org.jetbrains.annotations.NotNull;
import org.lwjgl.bgfx.BGFXCaps;
import org.lwjgl.bgfx.BGFXCapsGPU;
import org.lwjgl.bgfx.BGFXCapsLimits;

import java.util.*;

import static org.lwjgl.bgfx.BGFX.*;

// todo check whole project, make sure I didnt use EnumSet somewhere where COUNT is a valid part of the enum!
// todo COUNT IN ENUM SHOULD INSTEAD BE PUBLIC STATIC VARIABLE OR USE JAVA BUILT IN!!!
// see https://github.com/LWJGL/lwjgl3/blob/master/modules/lwjgl/bgfx/src/generated/java/org/lwjgl/bgfx/BGFXCaps.java
//@UtilityClass
public class Capabilities {

    static {
        // todo if this is an issue, just make all gpu and renderer code non-final and check in method instead
        Assertions.requireBgfxInitialized("Cannot determine capabilities before they are set by BGFXInit");
    }

    @Getter @NotNull
    private static final BGFXCaps caps = Objects.requireNonNull(bgfx_get_caps());

    // todo generate getters for all
    @Getter
    private static final byte numGPUs = caps.numGPUs();

    @Getter
    private static final short deviceId = caps.deviceId();

    @Getter @NotNull
    private static final BGFX_PCI_ID vendor = determineSelectedVendor();

    @Getter @NotNull
    private static final BGFX_RENDERER_TYPE rendererType = determineSelectedRendererType();

    @Getter @NotNull
    private static final List<CapsGPU> gpus = determineGPUs();

    @Getter @NotNull
    private static final CapsLimits limits = determineCapsLimits();

    @Getter
    private static final boolean homogeneousDepth = caps.homogeneousDepth();

    @Getter
    private static final boolean originBottomLeft = caps.originBottomLeft();

    // intentionally not exposed via getter
    private static final long supported = caps.supported();

    @Getter @NotNull
    private static final Set<BGFX_CAPS> supportedCapabilities = determineSupportedCapabilities();

    @Getter @NotNull
    private static final Set<BGFX_TEXTURE_FORMAT> textureFormats = determineTextureFormats();

    public static boolean isSupported(@NotNull BGFX_CAPS capability) {
        return Flags.containsFlag(supported, capability.VALUE);
    }

    public static boolean isSupported(@NotNull BGFX_TEXTURE_FORMAT format) {
        return textureFormats.contains(format); // Set is an EnumSet with very fast lookup time
    }

    private static BGFX_PCI_ID determineSelectedVendor() {
        Assertions.requireBgfxInitialized("Cannot determine selected vendor before it is set by BGFXInit");
        return determineVendor(caps.vendorId());
    }

    private static BGFX_PCI_ID determineVendor(short vendorId) {
        for (BGFX_PCI_ID id : BGFX_PCI_ID.values()) {
            if (vendorId == id.VALUE) {
                return id;
            }
        }

        throw new IllegalStateException("Capabilities was unable to determine vendorId");
    }

    private static BGFX_RENDERER_TYPE determineSelectedRendererType() {
        Assertions.requireBgfxInitialized("Cannot determine selected renderer before it is set by BGFXInit");
        return determineRendererType(caps.rendererType());
    }

    private static BGFX_RENDERER_TYPE determineRendererType(int rendererType) {
        for (BGFX_RENDERER_TYPE type : BGFX_RENDERER_TYPE.values()) {
            if (rendererType == type.VALUE) {
                return type;
            }
        }

        throw new IllegalStateException("Capabilities was unable to determine renderer type");
    }

    private static Set<BGFX_CAPS> determineSupportedCapabilities() {
        final EnumSet<BGFX_CAPS> supportedCaps = EnumSet.noneOf(BGFX_CAPS.class);
        final long supportedFlags = caps.supported();

        for (BGFX_CAPS capability : BGFX_CAPS.values()) {
            if (Flags.containsFlag(supportedFlags, capability.VALUE)) {
                supportedCaps.add(capability);
            }
        }

        return Collections.unmodifiableSet(supportedCaps);
    }

    private static List<CapsGPU> determineGPUs() {
        final List<CapsGPU> gpus = new ArrayList<>(numGPUs);

        for (int i=0; i < numGPUs; i++) {
            final BGFXCapsGPU bgfxGpu = caps.gpu(i);
            final short deviceId = bgfxGpu.deviceId();
            final BGFX_PCI_ID vendor = determineVendor(bgfxGpu.vendorId());
            final CapsGPU gpu = new CapsGPU(deviceId, vendor);
            gpus.add(gpu);
        }

        return Collections.unmodifiableList(gpus);
    }

    private static CapsLimits determineCapsLimits() {
        return new CapsLimits(caps.limits());
    }

    private static Set<BGFX_TEXTURE_FORMAT> determineTextureFormats() {
        final int count = BGFX_TEXTURE_FORMAT.COUNT.VALUE;

        final var availableFormats = EnumSet.noneOf(BGFX_TEXTURE_FORMAT.class);
        final BGFX_TEXTURE_FORMAT[] potentialFormats = BGFX_TEXTURE_FORMAT.values();

        for (int i=0; i < count; i++) {
            final short nextAvailableFormat = caps.formats(i);
            boolean wasFormatDetermined = false;
            for (BGFX_TEXTURE_FORMAT potentialFormat : potentialFormats) {
                if (nextAvailableFormat == potentialFormat.VALUE) {
                    availableFormats.add(potentialFormat);
                    wasFormatDetermined = true;
                    break;
                }
            }
            Assertions.require(wasFormatDetermined);
        }

        return Collections.unmodifiableSet(availableFormats);
    }

    @ToString
    @EqualsAndHashCode
    @SuppressWarnings("WeakerAccess")
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static final class CapsGPU {
        @Getter
        private final short deviceId;

        @Getter @NotNull
        private final BGFX_PCI_ID vendor;
    }

    @ToString
    @EqualsAndHashCode
    @SuppressWarnings("WeakerAccess")
    public static final class CapsLimits {
        @Getter private final int maxDrawCalls;
        @Getter private final int maxBlits;
        @Getter private final int maxTextureSize;
        @Getter private final int maxTextureLayers;
        @Getter private final int maxViews;
        @Getter private final int maxFrameBuffers;
        @Getter private final int maxFBAttachments;
        @Getter private final int maxPrograms;
        @Getter private final int maxShaders;
        @Getter private final int maxTextures;
        @Getter private final int maxTextureSamplers;
        @Getter private final int maxComputeBindings;
        @Getter private final int maxVertexDecls;
        @Getter private final int maxVertexStreams;
        @Getter private final int maxIndexBuffers;
        @Getter private final int maxVertexBuffers;
        @Getter private final int maxDynamicIndexBuffers;
        @Getter private final int maxDynamicVertexBuffers;
        @Getter private final int maxUniforms;
        @Getter private final int maxOcclusionQueries;
        @Getter private final int maxEncoders;
        @Getter private final int transientVbSize;
        @Getter private final int transientIbSize;

        private CapsLimits(@NotNull BGFXCapsLimits reference) {
            this.maxDrawCalls = reference.maxDrawCalls();
            this.maxBlits = reference.maxBlits();
            this.maxTextureSize = reference.maxTextureSize();
            this.maxTextureLayers = reference.maxTextureLayers();
            this.maxViews = reference.maxViews();
            this.maxFrameBuffers = reference.maxFrameBuffers();
            this.maxFBAttachments = reference.maxFBAttachments();
            this.maxPrograms = reference.maxPrograms();
            this.maxShaders = reference.maxShaders();
            this.maxTextures = reference.maxTextures();
            this.maxTextureSamplers = reference.maxTextureSamplers();
            this.maxComputeBindings = reference.maxComputeBindings();
            this.maxVertexDecls = reference.maxVertexDecls();
            this.maxVertexStreams = reference.maxVertexStreams();
            this.maxIndexBuffers = reference.maxIndexBuffers();
            this.maxVertexBuffers = reference.maxVertexBuffers();
            this.maxDynamicIndexBuffers = reference.maxDynamicIndexBuffers();
            this.maxDynamicVertexBuffers = reference.maxDynamicVertexBuffers();
            this.maxUniforms = reference.maxUniforms();
            this.maxOcclusionQueries = reference.maxOcclusionQueries();
            this.maxEncoders = reference.maxEncoders();
            this.transientVbSize = reference.transientVbSize();
            this.transientIbSize = reference.transientIbSize();
        }
    }

}























