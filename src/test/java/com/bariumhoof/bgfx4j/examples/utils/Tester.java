package com.bariumhoof.bgfx4j.examples.utils;

import com.bariumhoof.bgfx4j.enums.BGFX_BUFFER;
import com.bariumhoof.bgfx4j.layout.TypedDynamicVertexBuffer;
import com.bariumhoof.bgfx4j.layout.TypedVertexLayout;
import com.bariumhoof.bgfx4j.layout.Vertex.Vertex2;
import com.bariumhoof.bgfx4j.layout.VertexLayoutStruct;

import java.util.EnumSet;

import static com.bariumhoof.bgfx4j.layout.Vec.*;
import static com.bariumhoof.bgfx4j.layout.Vertex.vertex;

public class Tester {

    public static void main(String[] args) {

        final VertexLayoutStruct<Vertex2<INT16_Vec1, FLOAT_Vec3>> vertexLayout = TypedVertexLayout.builder()
                .color0().int16_vec1().then()
                .position().float_vec3().build();

        final TypedDynamicVertexBuffer<Vertex2<INT16_Vec1, FLOAT_Vec3>> buffer =
                vertexLayout.mallocDynamicBuffer(3, EnumSet.of(BGFX_BUFFER.NONE));

        buffer.update(
                vertex(int16_vec1(1), float_vec3(0.0f, 1.0f, 2.0f)),
                vertex(int16_vec1(1), float_vec3(0.0f, 1.0f, 2.0f)),
                vertex(int16_vec1(1), float_vec3(0.0f, 1.0f, 2.0f))
        );

    }

}
