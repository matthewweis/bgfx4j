package com.bariumhoof.bgfx4j.layout;

import org.lwjgl.bgfx.BGFXVertexLayout;

public abstract class VertexLayoutStruct {

    private final BGFXVertexLayout layout;
    public final BGFXVertexLayout get() {
        return layout;
    }
    protected VertexLayoutStruct(BGFXVertexLayout layout) {
        this.layout = layout;
    }



}