package com.bariumhoof.bgfx4j.examples.utils;

import com.bariumhoof.bgfx4j.enums.BGFX_BUFFER;
import com.bariumhoof.bgfx4j.layout.TypedVertexLayout;

import java.util.EnumSet;

public class Tester {

    public static void main(String[] args) {

        final var vertexLayout = TypedVertexLayout.builder()
                .color0().float32_vec1().then()
                .normal().half_vec1().then()
                .position().uint8_vec2().normalized().build();

        final var buffer = vertexLayout.mallocDynamicBuffer(3, EnumSet.noneOf(BGFX_BUFFER.class));

    }

}
