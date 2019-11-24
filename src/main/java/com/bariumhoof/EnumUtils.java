package com.bariumhoof;

public class EnumUtils {

//    public static long flags(EnumSet<BGFX_ACCESS> states) {
//        long bits = 0L;
//        for (BGFX_ACCESS next : states) {
//            bits |= next.VALUE;
//        }
//        return bits;
//    }
//
//
//    public static long flags(EnumSet<BGFX_API_VERSION> states) {
//        long bits = 0L;
//        for (BGFX_API_VERSION next : states) {
//            bits |= next.VALUE;
//        }
//        return bits;
//    }
//
//
//    public static long flags(EnumSet<BGFX_ATTRIB> states) {
//        long bits = 0L;
//        for (BGFX_ATTRIB next : states) {
//            bits |= next.VALUE;
//        }
//        return bits;
//    }
//
//
//    public static long flags(EnumSet<BGFX_ATTRIB_TYPE> states) {
//        long bits = 0L;
//        for (BGFX_ATTRIB_TYPE next : states) {
//            bits |= next.VALUE;
//        }
//        return bits;
//    }
//
//
//    public static long flags(EnumSet<BGFX_BACKBUFFER_RATIO> states) {
//        long bits = 0L;
//        for (BGFX_BACKBUFFER_RATIO next : states) {
//            bits |= next.VALUE;
//        }
//        return bits;
//    }
//
//
//    public static long flags(EnumSet<BGFX_BUFFER> states) {
//        long bits = 0L;
//        for (BGFX_BUFFER next : states) {
//            bits |= next.VALUE;
//        }
//        return bits;
//    }
//
//
//    public static long flags(EnumSet<BGFX_BUFFER_COMPUTE> states) {
//        long bits = 0L;
//        for (BGFX_BUFFER_COMPUTE next : states) {
//            bits |= next.VALUE;
//        }
//        return bits;
//    }
//
//
//    public static long flags(EnumSet<BGFX_CAPS> states) {
//        long bits = 0L;
//        for (BGFX_CAPS next : states) {
//            bits |= next.VALUE;
//        }
//        return bits;
//    }
//
//
//    public static long flags(EnumSet<BGFX_CAPS_FORMAT_TEXTURE> states) {
//        long bits = 0L;
//        for (BGFX_CAPS_FORMAT_TEXTURE next : states) {
//            bits |= next.VALUE;
//        }
//        return bits;
//    }
//
//
//    public static long flags(EnumSet<BGFX_CLEAR> states) {
//        long bits = 0L;
//        for (BGFX_CLEAR next : states) {
//            bits |= next.VALUE;
//        }
//        return bits;
//    }
//
//
//    public static long flags(EnumSet<BGFX_CUBE_MAP> states) {
//        long bits = 0L;
//        for (BGFX_CUBE_MAP next : states) {
//            bits |= next.VALUE;
//        }
//        return bits;
//    }
//
//
//    public static long flags(EnumSet<BGFX_DEBUG> states) {
//        long bits = 0L;
//        for (BGFX_DEBUG next : states) {
//            bits |= next.VALUE;
//        }
//        return bits;
//    }
//
//
//    public static long flags(EnumSet<BGFX_FATAL> states) {
//        long bits = 0L;
//        for (BGFX_FATAL next : states) {
//            bits |= next.VALUE;
//        }
//        return bits;
//    }
//
//
//    public static long flags(EnumSet<BGFX_INVALID_HANDLE> states) {
//        long bits = 0L;
//        for (BGFX_INVALID_HANDLE next : states) {
//            bits |= next.VALUE;
//        }
//        return bits;
//    }
//
//
//    public static long flags(EnumSet<BGFX_OCCLUSION_QUERY_RESULT> states) {
//        long bits = 0L;
//        for (BGFX_OCCLUSION_QUERY_RESULT next : states) {
//            bits |= next.VALUE;
//        }
//        return bits;
//    }
//
//
//    public static long flags(EnumSet<BGFX_PCI_ID> states) {
//        long bits = 0L;
//        for (BGFX_PCI_ID next : states) {
//            bits |= next.VALUE;
//        }
//        return bits;
//    }
//
//
//    public static long flags(EnumSet<BGFX_RENDERER_TYPE> states) {
//        long bits = 0L;
//        for (BGFX_RENDERER_TYPE next : states) {
//            bits |= next.VALUE;
//        }
//        return bits;
//    }
//
//
//    public static long flags(EnumSet<BGFX_RESET> states) {
//        long bits = 0L;
//        for (BGFX_RESET next : states) {
//            bits |= next.VALUE;
//        }
//        return bits;
//    }
//
//
//    public static long flags(EnumSet<BGFX_RESET_RESERVED> states) {
//        long bits = 0L;
//        for (BGFX_RESET_RESERVED next : states) {
//            bits |= next.VALUE;
//        }
//        return bits;
//    }
//
//
//    public static long flags(EnumSet<BGFX_RESOLVE> states) {
//        long bits = 0L;
//        for (BGFX_RESOLVE next : states) {
//            bits |= next.VALUE;
//        }
//        return bits;
//    }
//
//
//    public static long flags(EnumSet<BGFX_SAMPLER> states) {
//        long bits = 0L;
//        for (BGFX_SAMPLER next : states) {
//            bits |= next.VALUE;
//        }
//        return bits;
//    }
//
//
//    public static long flags(EnumSet<BGFX_SAMPLER_MASK> states) {
//        long bits = 0L;
//        for (BGFX_SAMPLER_MASK next : states) {
//            bits |= next.VALUE;
//        }
//        return bits;
//    }
//
//
//    public static long flags(EnumSet<BGFX_SAMPLER_SHIFT> states) {
//        long bits = 0L;
//        for (BGFX_SAMPLER_SHIFT next : states) {
//            bits |= next.VALUE;
//        }
//        return bits;
//    }
//
//
//    public static long flags(EnumSet<BGFX_STATE> states) {
//        long bits = 0L;
//        for (BGFX_STATE next : states) {
//            bits |= next.VALUE;
//        }
//        return bits;
//    }
//
//
//    public static long flags(EnumSet<BGFX_STATE_BLEND> states) {
//        long bits = 0L;
//        for (BGFX_STATE_BLEND next : states) {
//            bits |= next.VALUE;
//        }
//        return bits;
//    }
//
//
//    public static long flags(EnumSet<BGFX_STATE_MASK> states) {
//        long bits = 0L;
//        for (BGFX_STATE_MASK next : states) {
//            bits |= next.VALUE;
//        }
//        return bits;
//    }
//
//
//    public static long flags(EnumSet<BGFX_STATE_SHIFT> states) {
//        long bits = 0L;
//        for (BGFX_STATE_SHIFT next : states) {
//            bits |= next.VALUE;
//        }
//        return bits;
//    }
//
//
//    public static long flags(EnumSet<BGFX_STENCIL> states) {
//        long bits = 0L;
//        for (BGFX_STENCIL next : states) {
//            bits |= next.VALUE;
//        }
//        return bits;
//    }
//
//
//    public static long flags(EnumSet<BGFX_STENCIL_MASK> states) {
//        long bits = 0L;
//        for (BGFX_STENCIL_MASK next : states) {
//            bits |= next.VALUE;
//        }
//        return bits;
//    }
//
//
//    public static long flags(EnumSet<BGFX_STENCIL_SHIFT> states) {
//        long bits = 0L;
//        for (BGFX_STENCIL_SHIFT next : states) {
//            bits |= next.VALUE;
//        }
//        return bits;
//    }
//
//
//    public static long flags(EnumSet<BGFX_TEXTURE> states) {
//        long bits = 0L;
//        for (BGFX_TEXTURE next : states) {
//            bits |= next.VALUE;
//        }
//        return bits;
//    }
//
//
//    public static long flags(EnumSet<BGFX_TEXTURE_FORMAT> states) {
//        long bits = 0L;
//        for (BGFX_TEXTURE_FORMAT next : states) {
//            bits |= next.VALUE;
//        }
//        return bits;
//    }
//
//
//    public static long flags(EnumSet<BGFX_TEXTURE_RT> states) {
//        long bits = 0L;
//        for (BGFX_TEXTURE_RT next : states) {
//            bits |= next.VALUE;
//        }
//        return bits;
//    }
//
//
//    public static long flags(EnumSet<BGFX_TEXTURE_SHIFT> states) {
//        long bits = 0L;
//        for (BGFX_TEXTURE_SHIFT next : states) {
//            bits |= next.VALUE;
//        }
//        return bits;
//    }
//
//
//    public static long flags(EnumSet<BGFX_TOPOLOGY> states) {
//        long bits = 0L;
//        for (BGFX_TOPOLOGY next : states) {
//            bits |= next.VALUE;
//        }
//        return bits;
//    }
//
//
//    public static long flags(EnumSet<BGFX_TOPOLOGY_CONVERT> states) {
//        long bits = 0L;
//        for (BGFX_TOPOLOGY_CONVERT next : states) {
//            bits |= next.VALUE;
//        }
//        return bits;
//    }
//
//
//    public static long flags(EnumSet<BGFX_TOPOLOGY_SORT> states) {
//        long bits = 0L;
//        for (BGFX_TOPOLOGY_SORT next : states) {
//            bits |= next.VALUE;
//        }
//        return bits;
//    }
//
//
//    public static long flags(EnumSet<BGFX_UNIFORM_TYPE> states) {
//        long bits = 0L;
//        for (BGFX_UNIFORM_TYPE next : states) {
//            bits |= next.VALUE;
//        }
//        return bits;
//    }
//
//
//    public static long flags(EnumSet<BGFX_VIEW_MODE> states) {
//        long bits = 0L;
//        for (BGFX_VIEW_MODE next : states) {
//            bits |= next.VALUE;
//        }
//        return bits;
//    }

}
