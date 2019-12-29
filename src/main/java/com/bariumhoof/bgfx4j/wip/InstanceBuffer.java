package com.bariumhoof.bgfx4j.wip;

import com.bariumhoof.bgfx4j.Disposable;
import com.bariumhoof.bgfx4j.Handle;
import org.jetbrains.annotations.NotNull;
import org.lwjgl.bgfx.BGFXInstanceDataBuffer;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.NativeType;

import java.nio.ByteBuffer;

import static org.lwjgl.bgfx.BGFX.bgfx_alloc_instance_data_buffer;

public final class InstanceBuffer implements Disposable, Handle {

    private final @NotNull BGFXInstanceDataBuffer buffer;

    private InstanceBuffer(@NotNull BGFXInstanceDataBuffer buffer) {
        this.buffer = buffer;
    }

    @NotNull
    public static InstanceBuffer create(@NotNull MemoryStack memoryStack, int num, int stride) {
        final BGFXInstanceDataBuffer buf = BGFXInstanceDataBuffer.callocStack(memoryStack);
        bgfx_alloc_instance_data_buffer(buf, num, stride);
        return new InstanceBuffer(buf);
    }

    @Override
    public void dispose() {
        buffer.free();
    }

    public int sizeof() {
        return buffer.sizeof();
    }

    @NativeType("uint8_t *")
    public ByteBuffer data() {
        return buffer.data();
    }

    @NativeType("uint32_t")
    public int size() {
        return buffer.size();
    }

    @NativeType("uint32_t")
    public int offset() {
        return buffer.offset();
    }

    @NativeType("uint32_t")
    public int num() {
        return buffer.num();
    }

    @NativeType("uint16_t")
    public short stride() {
        return buffer.stride();
    }

    @Override
    @NativeType("bgfx_vertex_buffer_handle_t")
    public short handle() {
        return buffer.handle();
    }

    @NotNull
    final BGFXInstanceDataBuffer get() {
        return buffer;
    }
}
