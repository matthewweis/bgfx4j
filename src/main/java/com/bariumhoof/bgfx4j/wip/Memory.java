package com.bariumhoof.bgfx4j.wip;

import com.bariumhoof.bgfx4j.Disposable;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.lwjgl.bgfx.BGFX;
import org.lwjgl.bgfx.BGFXMemory;

import java.nio.*;

import static org.lwjgl.bgfx.BGFX.*;

// todo size vs sizeof in bgfxMemory
@Slf4j
public final class Memory implements Disposable {

    @Getter // temp getter
    protected final BGFXMemory bgfxMemory;

    private Memory(BGFXMemory bgfxMemory) {
        this.bgfxMemory = bgfxMemory;
    }

    @NotNull
    public static Memory alloc(int numBytes) {
        final BGFXMemory bgfxMemory = bgfx_alloc(numBytes);
        return new Memory(bgfxMemory);
    }

    public static Memory copy(@NotNull Memory memory) {
        return new Memory(BGFX.bgfx_copy(memory.bgfxMemory.data()));
    }

    // todo makeRefRelease for function with callback

    // todo add warning that makeRef's backing buffer MUST be available for at least two frame calls

    public static Memory makeRef(int numBytes) {
        return new Memory(bgfx_alloc(numBytes));
    }

    public static Memory makeRef(@NotNull ByteBuffer data) {
        return new Memory(bgfx_make_ref(data));
    }

    public static Memory makeRef(@NotNull ShortBuffer data) {
        return new Memory(bgfx_make_ref(data));
    }

    public static Memory makeRef(@NotNull IntBuffer data) {
        return new Memory(bgfx_make_ref(data));
    }

    public static Memory makeRef(@NotNull LongBuffer data) {
        return new Memory(bgfx_make_ref(data));
    }

    public static Memory makeRef(@NotNull FloatBuffer data) {
        return new Memory(bgfx_make_ref(data));
    }

    public static Memory makeRef(@NotNull DoubleBuffer data) {
        return new Memory(bgfx_make_ref(data));
    }

    public static Memory copy(@NotNull ByteBuffer data) {
        return new Memory(bgfx_copy(data));
    }

    public static Memory copy(@NotNull ShortBuffer data) {
        return new Memory(bgfx_copy(data));
    }

    public static Memory copy(@NotNull IntBuffer data) {
        return new Memory(bgfx_copy(data));
    }

    public static Memory copy(@NotNull LongBuffer data) {
        return new Memory(bgfx_copy(data));
    }

    public static Memory copy(@NotNull FloatBuffer data) {
        return new Memory(bgfx_copy(data));
    }

    public static Memory copy(@NotNull DoubleBuffer data) {
        return new Memory(bgfx_copy(data));
    }

    public static Memory copy(@NotNull short[] data) {
        return new Memory(bgfx_copy(data));
    }

    public static Memory copy(@NotNull int[] data) {
        return new Memory(bgfx_copy(data));
    }

    public static Memory copy(@NotNull long[] data) {
        return new Memory(bgfx_copy(data));
    }

    public static Memory copy(@NotNull float[] data) {
        return new Memory(bgfx_copy(data));
    }

    public static Memory copy(@NotNull double[] data) {
        return new Memory(bgfx_copy(data));
    }

//    public void put(@NotNull byte[] data) {
//        bgfxMemory.data().put(data);
//    }
//
//    public void put(@NotNull short[] data) {
//        bgfxMemory.data().asShortBuffer().put(data);
//    }
//
//    public void put(@NotNull int[] data) {
//        bgfxMemory.data().asIntBuffer().put(data);
//    }
//
//    public void put(@NotNull long[] data) {
//        bgfxMemory.data().asLongBuffer().put(data);
//    }
//
//    public void put(@NotNull float[] data) {
//        bgfxMemory.data().asFloatBuffer().put(data);
//    }
//
//    public void put(@NotNull double[] data) {
//        bgfxMemory.data().asDoubleBuffer().put(data);
//    }

    // todo
//    @NotNull
//    public static Memory makeRef(@NotNull ByteBuffer buffer) {
//        log.warn("bgfxMemory size needs to be confirmed for bytes");
////        final BGFXMemory bgfxMemory = BGFX.bgfx_make_ref()
//        final BGFXMemory bgfxMemory = BGFX.bgfx_make_ref_release()
//        return new Memory(bgfxMemory);
//    }



    @Override
    public void dispose() {
        bgfxMemory.free();
    }
}
