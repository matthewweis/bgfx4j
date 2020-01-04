package com.bariumhoof.bgfx4j.init;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.lwjgl.system.NativeType;

@ToString
@EqualsAndHashCode
public class InitLimits {

    private final short maxEncoders;
    private final int transientVbSize;
    private final int transientIbSize;

    private InitLimits(short maxEncoders, int transientVbSize, int transientIbSize) {
        this.maxEncoders = maxEncoders;
        this.transientVbSize = transientVbSize;
        this.transientIbSize = transientIbSize;
    }

    public static InitLimits create(short maxEncoders, int transientVbSize, int transientIbSize) {
        return new InitLimits(maxEncoders, transientVbSize, transientIbSize);
    }

    @NativeType("uint16_t")
    public short maxEncoders() {
        return maxEncoders;
    }

    @NativeType("uint32_t")
    public int transientVbSize() {
        return transientVbSize;
    }

    @NativeType("uint32_t")
    public int transientIbSize() {
        return transientIbSize;
    }

}
