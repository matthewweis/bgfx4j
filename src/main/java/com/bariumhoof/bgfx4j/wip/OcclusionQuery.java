package com.bariumhoof.bgfx4j.wip;

import com.bariumhoof.bgfx4j.Disposable;
import com.bariumhoof.bgfx4j.Handle;
import com.bariumhoof.bgfx4j.encoder.Encoder;
import com.bariumhoof.bgfx4j.view.View;
import org.lwjgl.bgfx.BGFX;

import static org.lwjgl.bgfx.BGFX.*;
import static org.lwjgl.system.MemoryUtil.NULL;

public class OcclusionQuery implements Handle, Disposable {

    private final short handle;

    private OcclusionQuery(short handle) {
        this.handle = handle;
    }

    public static OcclusionQuery create() {
//        bgfx_submit_occlusion_query();
//        bgfx_encoder_submit_occlusion_query();
//        bgfx_occlusio
        return new OcclusionQuery(bgfx_create_occlusion_query());
    }

//    public void Submit(View view, Program program, ) {
//        bgfx_submit_occlusion_query(view.);
//    }

//    public void SubmitToEncoder(Encoder encoder) {
//
//    }

    @Override
    public void dispose() {
        bgfx_destroy_occlusion_query(handle);
    }

    @Override
    public short handle() {
        return this.handle;
    }

}