package com.bariumhoof.bgfx4j.layout;

import com.bariumhoof.bgfx4j.Disposable;
import com.bariumhoof.bgfx4j.Handle;
import org.lwjgl.bgfx.BGFXVertexLayout;

import static org.lwjgl.bgfx.BGFX.bgfx_create_vertex_layout;

@SuppressWarnings("unused")
public final class VertexLayout<V extends Vertex> implements Handle, Disposable {

    private final BGFXVertexLayout layout;

    private final short handle;

    protected VertexLayout(BGFXVertexLayout layout) {
        this.layout = layout;
        this.handle = bgfx_create_vertex_layout(get());
    }

    public static TypedVertexLayoutBuilder1.InitialStage builder() {
        return new TypedVertexLayoutBuilder1.InitialStage();
    }

    public final BGFXVertexLayout get() {
        return layout;
    }

    @Override
    public short handle() {
        return handle;
    }

    @Override
    public void dispose() {
        layout.free();
    }
}