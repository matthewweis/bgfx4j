package com.bariumhoof.bgfx4j.extras;

import org.joml.Matrix4f;

import java.nio.FloatBuffer;

public class ModelMatrix {

    private FloatBuffer buffer = FloatBuffer.allocate(16);
    private Matrix4f delegate = new Matrix4f();

}
