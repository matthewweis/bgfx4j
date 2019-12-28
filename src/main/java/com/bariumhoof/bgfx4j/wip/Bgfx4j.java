package com.bariumhoof.bgfx4j.wip;

import com.bariumhoof.bgfx4j.enums.BGFX_ATTRIB;
import org.jetbrains.annotations.NotNull;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;

import static org.lwjgl.bgfx.BGFX.*;

public class Bgfx4j {

    public static int U_INT8_MAX = 255;
//    public static int U_INT16_MAX = 65535;
    public static int U_INT16_MAX = 0xFFFF;

    public static void vertexPack(float[] input, boolean isNormalized, @NotNull BGFX_ATTRIB attrib, @NotNull VertexLayout layout, int index) {
        bgfx_vertex_pack(input, isNormalized, attrib.VALUE, layout.get(), ByteBuffer.allocate(input.length), index);
    }

    public static void vertexPack(@NotNull FloatBuffer input, boolean isNormalized, @NotNull BGFX_ATTRIB attrib, @NotNull VertexLayout layout, int index) {
        bgfx_vertex_pack(input, isNormalized, attrib.VALUE, layout.get(), ByteBuffer.allocate(input.capacity()), index);
    }

    public static void vertexConvert(@NotNull ByteBuffer source, @NotNull VertexLayout sourceLayout, @NotNull VertexLayout dstLayout, int num) {
        final ByteBuffer dst = ByteBuffer.allocate(source.capacity()); // todo same size?
        bgfx_vertex_convert(dstLayout.get(), dst, sourceLayout.get(), source, num);
    }

    public static void weldVertices(short[] output, @NotNull VertexLayout layout, @NotNull ByteBuffer data, float epsilon) {
        bgfx_weld_vertices(output, layout.get(), data, epsilon);
    }

//    public static int[] topologyConvert(@NotNull BGFX_TOPOLOGY_CONVERT topologyConvert, @NotNull int[] src, @NotNull int[] dst, boolean isIndex32) {
//        bgfx_topology_convert(topologyConvert.VALUE, dst, src, isIndex32);
//    }
//
//    public static ByteBuffer topologyConvert(@NotNull BGFX_TOPOLOGY_CONVERT topologyConvert, @NotNull ByteBuffer src, @NotNull ByteBuffer dst, boolean isIndex32) {
//        bgfx_topology_convert(topologyConvert.VALUE, dst, src, isIndex32);
//    }
//
//    public static ShortBuffer topologyConvert(@NotNull BGFX_TOPOLOGY_CONVERT topologyConvert, @NotNull ShortBuffer src, @NotNull ShortBuffer dst, boolean isIndex32) {
//        bgfx_topology_convert(topologyConvert.VALUE, dst, src, isIndex32);
//    }
//
//    public static IntBuffer topologyConvert(@NotNull BGFX_TOPOLOGY_CONVERT topologyConvert, @NotNull IntBuffer src, @NotNull IntBuffer dst, boolean isIndex32) {
//        bgfx_topology_convert(topologyConvert.VALUE, dst, src, isIndex32);
//    }
//
//    public static short[] topologyConvert(@NotNull BGFX_TOPOLOGY_CONVERT topologyConvert, @NotNull short[] src, @NotNull short[] dst, boolean isIndex32) {
//        bgfx_topology_convert(topologyConvert.VALUE, dst, src, isIndex32);
//    }

    // todo
    //    public static short[] topologyConvertTriList(@NotNull BGFX_TOPOLOGY_CONVERT topologyConvert, @NotNull short[] src, @NotNull short[] dst, boolean isIndex32) {
//        bgfx_topology_convert(topologyConvert.VALUE, dst, src, isIndex32);
//    }

}
