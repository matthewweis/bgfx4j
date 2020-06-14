package com.bariumhoof.bgfx4j.layout;

import com.bariumhoof.assertions.Assertions;
import lombok.Value;
import org.jetbrains.annotations.NotNull;

import java.nio.ByteBuffer;

public interface Vec<N extends Num, T extends BgfxAttribType> {

    UINT8_Vec1 default_uint8_vec1 = uint8_vec1(b(0));
    UINT8_Vec2 default_uint8_vec2 = uint8_vec2(b(0), b(0));
    UINT8_Vec3 default_uint8_vec3 = uint8_vec3(b(0), b(0), b(0));
    UINT8_Vec4 default_uint8_vec4 = uint8_vec4(b(0), b(0), b(0), b(0));
    UINT10_Vec3 default_uint10_vec3 = uint10_vec3(0, 0, 0);
    UINT10_Vec4 default_uint10_vec4 = uint10_vec4(0, 0, 0, 0);
    INT16_Vec1 default_int16_vec1 = int16_vec1(s(0));
    INT16_Vec2 default_int16_vec2 = int16_vec2(s(0), s(0));
    INT16_Vec3 default_int16_vec3 = int16_vec3(s(0), s(0), s(0));
    INT16_Vec4 default_int16_vec4 = int16_vec4(s(0), s(0), s(0), s(0));
    HALF_Vec1 default_half_vec1 = half_vec1(0.0f);
    HALF_Vec2 default_half_vec2 = half_vec2(0.0f, 0.0f);
    HALF_Vec3 default_half_vec3 = half_vec3(0.0f, 0.0f, 0.0f);
    HALF_Vec4 default_half_vec4 = half_vec4(0.0f, 0.0f, 0.0f, 0.0f);
    FLOAT_Vec1 default_float_vec1 = float_vec1(0.0f);
    FLOAT_Vec2 default_float_vec2 = float_vec2(0.0f, 0.0f);
    FLOAT_Vec3 default_float_vec3 = float_vec3(0.0f, 0.0f, 0.0f);
    FLOAT_Vec4 default_float_vec4 = float_vec4(0.0f, 0.0f, 0.0f, 0.0f);

    N number();
    T type();
    void put(@NotNull ByteBuffer buffer);

    interface Vec1<T extends BgfxAttribType> extends Vec<Num.ONE, T> {
        @Override default Num.ONE number() {
            return Num.ONE;
        }
    }

    interface Vec2<T extends BgfxAttribType> extends Vec<Num.TWO, T> {
        @Override default Num.TWO number() {
            return Num.TWO;
        }
    }

    interface Vec3<T extends BgfxAttribType> extends Vec<Num.THREE, T> {
        @Override default Num.THREE number() {
            return Num.THREE;
        }
    }

    interface Vec4<T extends BgfxAttribType> extends Vec<Num.FOUR, T> {
        @Override default Num.FOUR number() { return Num.FOUR; }
    }

    // TODO
    //   ALL OF THESE NEED SANITIZED INPUT OR ELSE THEY WILL OVERLAP IF USER MAKES MISTAKE
    //    ESPECIALLY LOOK INTO SIGNED INTS?
//float_vec1
    @NotNull
    static UINT8_Vec1 uint8_vec1(byte first) {
        return new UINT8_Vec1(first);
    }

    @NotNull
    static UINT8_Vec1 uint8_vec1(int first) {
        Assertions.require_uint8(first);
        return uint8_vec1((byte) first); // confirm this is safe
    }

    @NotNull
    static UINT8_Vec2 uint8_vec2(byte first, byte second) {
        final short xy = (short) ((((short)first) << 8) | ((short)second));
        return new UINT8_Vec2(xy);
    }

    @NotNull
    static UINT8_Vec2 uint8_vec2(int first, int second) {
        Assertions.require_uint8(first);
        Assertions.require_uint8(second);
        return uint8_vec2((byte) first, (byte) second); // confirm this is safe
    }

    @NotNull
    static UINT8_Vec3 uint8_vec3(byte first, byte second, byte third) {
        final short xy = (short) ((((short)first) << 8) | ((short)second));
        return new UINT8_Vec3(xy, third);
    }

    @NotNull
    static UINT8_Vec3 uint8_vec3(int first, int second, int third) {
        Assertions.require_uint8(first);
        Assertions.require_uint8(second);
        Assertions.require_uint8(third);
        return uint8_vec3((byte) first, (byte) second, (byte) third); // confirm this is safe
    }

    @NotNull
    static UINT8_Vec4 uint8_vec4(byte first, byte second, byte third, byte fourth) {
        final int xyzw = (((int)first) << 24) | (((int)second) << 16) | (((int)third) << 8) | ((int) fourth);
        return new UINT8_Vec4(xyzw);
    }

    @NotNull
    static UINT8_Vec4 uint8_vec4(int first, int second, int third, int fourth) {
        Assertions.require_uint8(first);
        Assertions.require_uint8(second);
        Assertions.require_uint8(third);
        Assertions.require_uint8(fourth);
        return uint8_vec4((byte) first, (byte) second, (byte) third, (byte) fourth); // confirm this is safe
    }

    @NotNull
    static UINT10_Vec3 uint10_vec3(int x, int y, int z) {
        Assertions.require_uint10(x);
        Assertions.require_uint10(y);
        Assertions.require_uint10(z);
        final int xyz = (x << 22) | (y << 12) | (z << 2);
        return new UINT10_Vec3(xyz);
    }

    // unsigned wants bigger than a byte anyways (for pos) so just let them use int to avoid casting!
    @NotNull
    static UINT10_Vec4 uint10_vec4(int x, int y, int z, int w) {
        Assertions.require_uint10(x);
        Assertions.require_uint10(y);
        Assertions.require_uint10(z);
        Assertions.require_uint10(w);
        // max 10, 10, 10, 2 bits
        // 32 - 22 = 10
        // 32 - 12 = 20
        // 32 - 2 = 30
        final int xyzw = (x << 22) | (y << 12) | (z << 2) | w;
        return new UINT10_Vec4(xyzw);
    }

    @NotNull
    static INT16_Vec1 int16_vec1(short first) {
        return new INT16_Vec1(first);
    }

    @NotNull
    static INT16_Vec2 int16_vec2(short first, short second) {
        final int xy = (((int)first) << 16) | ((int)second);
        return new INT16_Vec2(xy);
    }

    @NotNull
    static INT16_Vec3 int16_vec3(short first, short second, short third) {
        final int xy = (((int)first) << 16) | ((int)second);
        return new INT16_Vec3(xy, third);
    }

    @NotNull
    static INT16_Vec4 int16_vec4(short first, short second, short third, short fourth) {
        final long xyzw = (((long)first) << 48) | (((long)second) << 32) | (((long)third) << 16) | ((long) fourth);
        return new INT16_Vec4(xyzw);
    }

    @NotNull
    static INT16_Vec1 int16_vec1(int first) {
        Assertions.require_int16(first);
        return int16_vec1((short) first);
    }

    @NotNull
    static INT16_Vec2 int16_vec2(int first, int second) {
        Assertions.require_int16(first);
        Assertions.require_int16(second);
        return int16_vec2((short) first, (short) second);
    }

    @NotNull
    static INT16_Vec3 int16_vec3(int first, int second, int third) {
        Assertions.require_int16(first);
        Assertions.require_int16(second);
        Assertions.require_int16(third);
        return int16_vec3((short) first, (short) second, (short) third);
    }

    @NotNull
    static INT16_Vec4 int16_vec4(int first, int second, int third, int fourth) {
        Assertions.require_int16(first);
        Assertions.require_int16(second);
        Assertions.require_int16(third);
        Assertions.require_int16(fourth);
        return int16_vec4((short) first, (short) second, (short) third, (short) fourth);
    }

    @NotNull
    static HALF_Vec1 half_vec1(float first) {
        final short x = halfFloatToShort(first);
        return new HALF_Vec1(x);
    }

    @NotNull
    static HALF_Vec2 half_vec2(float first, float second) {
        final int xy = (((int) halfFloatToShort(first)) << 16) | (((int) halfFloatToShort(second)));
        return new HALF_Vec2(xy);
    }

    @NotNull
    static HALF_Vec3 half_vec3(float first, float second, float third) {
        final int xy = (((int) halfFloatToShort(first)) << 16) | (((int) halfFloatToShort(second)));
        final short z = halfFloatToShort(third);
        return new HALF_Vec3(xy, z);
    }

    @NotNull
    static HALF_Vec4 half_vec4(float first, float second, float third, float fourth) {
        final long xyzw =
                (((long)halfFloatToShort(first)) << 48) |
                (((long)halfFloatToShort(second)) << 32) |
                (((long)halfFloatToShort(third)) << 16) |
                ((long) halfFloatToShort(first));
        return new HALF_Vec4(xyzw);
    }

    @NotNull
    static FLOAT_Vec1 float_vec1(float first) {
        return new FLOAT_Vec1(first);
    }

    @NotNull
    static FLOAT_Vec2 float_vec2(float first, float second) {
        return new FLOAT_Vec2(first, second);
    }

    @NotNull
    static FLOAT_Vec3 float_vec3(float first, float second, float third) {
        return new FLOAT_Vec3(first, second, third);
    }

    @NotNull
    static FLOAT_Vec4 float_vec4(float first, float second, float third, float fourth) {
        return new FLOAT_Vec4(first, second, third, fourth);
    }

    @Value
    class UINT8_Vec1 implements Vec1<BgfxAttribType.UINT8> {
        byte x;
        @Override public void put(@NotNull ByteBuffer buffer) { buffer.put(x); }
        @Override public BgfxAttribType.UINT8 type() { return BgfxAttribType.UINT8; }
    }

    @Value
    class UINT8_Vec2 implements Vec2<BgfxAttribType.UINT8> {
        short xy; // will use bottom 16 bits
        @Override public void put(@NotNull ByteBuffer buffer) { buffer.putShort(xy); }
        @Override public BgfxAttribType.UINT8 type() { return BgfxAttribType.UINT8; }
    }

    @Value
    class UINT8_Vec3 implements Vec3<BgfxAttribType.UINT8> {
        short xy; byte z; // will use bottom 24 bits
        @Override public void put(@NotNull ByteBuffer buffer) { buffer.putShort(xy).put(z); }
        @Override public BgfxAttribType.UINT8 type() { return BgfxAttribType.UINT8; }
    }

    @Value
    class UINT8_Vec4 implements Vec4<BgfxAttribType.UINT8> {
        int xyzw;
        @Override public void put(@NotNull ByteBuffer buffer) { buffer.putInt(xyzw); }
        @Override public BgfxAttribType.UINT8 type() { return BgfxAttribType.UINT8; }
    }

    @Value
    class UINT10_Vec3 implements Vec3<BgfxAttribType.UINT10> {
        int xyz; // just keeps 2 bits empty when made
        @Override public void put(@NotNull ByteBuffer buffer) { buffer.putInt(xyz); }
        @Override public BgfxAttribType.UINT10 type() { return BgfxAttribType.UINT10; }
    }

    @Value
    class UINT10_Vec4 implements Vec4<BgfxAttribType.UINT10> {
        int xyzw;
        @Override public void put(@NotNull ByteBuffer buffer) { buffer.putInt(xyzw); }
        @Override public BgfxAttribType.UINT10 type() { return BgfxAttribType.UINT10; }
    }

    // want shorts since these are SIGNED int16s (aka shorts)

    @Value
    class INT16_Vec1 implements Vec1<BgfxAttribType.INT16> {
        short x;
        @Override public void put(@NotNull ByteBuffer buffer) { buffer.putShort(x); }
        @Override public BgfxAttribType.INT16 type() { return BgfxAttribType.INT16; }
    }

    @Value
    class INT16_Vec2 implements Vec2<BgfxAttribType.INT16> {
        int xy;
        @Override public void put(@NotNull ByteBuffer buffer) { buffer.putInt(xy); }
        @Override public BgfxAttribType.INT16 type() { return BgfxAttribType.INT16; }
    }

    @Value
    class INT16_Vec3 implements Vec3<BgfxAttribType.INT16> {
        int xy; short z; // technically short saves no space, but makes easier to put in buffer
        @Override public void put(@NotNull ByteBuffer buffer) { buffer.putInt(xy).putShort(z); }
        @Override public BgfxAttribType.INT16 type() { return BgfxAttribType.INT16; }
    }

    @Value
    class INT16_Vec4 implements Vec4<BgfxAttribType.INT16> {
        long xyzw;
        @Override public void put(@NotNull ByteBuffer buffer) { buffer.putLong(xyzw); }
        @Override public BgfxAttribType.INT16 type() { return BgfxAttribType.INT16; }
    }

    // not at all worth optimizing the memory on JVM size since jvm likes 32-bit cells and packing is way slower
    // we still must optimize in byteBuffer however

    @Value
    class HALF_Vec1 implements Vec1<BgfxAttribType.HALF> {
        short x;
        @Override public void put(@NotNull ByteBuffer buffer) { buffer.putShort(x); }
        @Override public BgfxAttribType.HALF type() { return BgfxAttribType.HALF; }
    }

    @Value
    class HALF_Vec2 implements Vec2<BgfxAttribType.HALF> {
        int xy;
        @Override public void put(@NotNull ByteBuffer buffer) { buffer.putInt(xy); }
        @Override public BgfxAttribType.HALF type() { return BgfxAttribType.HALF; }
    }

    // todo seriously ALL of these need many tests

    @Value
    class HALF_Vec3 implements Vec3<BgfxAttribType.HALF> {
        int xy; short z;
        @Override public void put(@NotNull ByteBuffer buffer) { buffer.putInt(xy).putShort(z); }
        @Override public BgfxAttribType.HALF type() { return BgfxAttribType.HALF; }
    }

    @Value
    class HALF_Vec4 implements Vec4<BgfxAttribType.HALF> {
        long xyzw;
        @Override public void put(@NotNull ByteBuffer buffer) { buffer.putLong(xyzw); }
        @Override public BgfxAttribType.HALF type() { return BgfxAttribType.HALF; }
    }

    @Value
    class FLOAT_Vec1 implements Vec1<BgfxAttribType.FLOAT> {
        float x;
        @Override public void put(@NotNull ByteBuffer buffer) { buffer.putFloat(x); }
        @Override public BgfxAttribType.FLOAT type() { return BgfxAttribType.FLOAT; }
    }

    @Value
    class FLOAT_Vec2 implements Vec2<BgfxAttribType.FLOAT> {
        float x, y;
        @Override public void put(@NotNull ByteBuffer buffer) { buffer.putFloat(x).putFloat(y); }
        @Override public BgfxAttribType.FLOAT type() { return BgfxAttribType.FLOAT; }
    }

    @Value
    class FLOAT_Vec3 implements Vec3<BgfxAttribType.FLOAT> {
        float x, y, z;
        @Override public void put(@NotNull ByteBuffer buffer) { buffer.putFloat(x).putFloat(y).putFloat(z); }
        @Override public BgfxAttribType.FLOAT type() { return BgfxAttribType.FLOAT; }
    }

    @Value
    class FLOAT_Vec4 implements Vec4<BgfxAttribType.FLOAT> {
        float x, y, z, w;
        @Override public void put(@NotNull ByteBuffer buffer) { buffer.putFloat(x).putFloat(y).putFloat(z).putFloat(w); }
        @Override public BgfxAttribType.FLOAT type() { return BgfxAttribType.FLOAT; }
    }

    private static short halfFloatToShort(float f) {
        // todo look into this floats have bits in reverse order than ints
        return (short) (Integer.reverse(Float.floatToRawIntBits(f)));
    }

    private static byte b(int n) {
        return (byte) n;
    }

    private static short s(int n) {
        return (short) n;
    }

}
