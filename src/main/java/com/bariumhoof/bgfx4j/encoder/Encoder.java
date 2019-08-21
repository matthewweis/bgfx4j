package com.bariumhoof.bgfx4j.encoder;

import org.lwjgl.bgfx.BGFXVertexDecl;

import static org.lwjgl.bgfx.BGFX.*;

/**
 *
 * "Encoders are used for submitting draw calls from multiple threads. Only one encoder per thread should be used.
 * Use bgfx::begin() to obtain an encoder for a thread."
 */
public class Encoder {

    private final long id;

    private Encoder() {
        id = bgfx_encoder_begin(false);// default is false
    }

    public static Encoder begin() {
        return new Encoder();
    }

    public void setTransform() {
        final BGFXVertexDecl d = BGFXVertexDecl.create();
        bgfx_vertex_decl_begin(d, BGFX_RENDERER_TYPE_METAL);
    }



}
