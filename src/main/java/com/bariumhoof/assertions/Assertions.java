package com.bariumhoof.assertions;

import com.bariumhoof.bgfx4j.enums.BGFX_CAPS;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.function.Supplier;

public final class Assertions {

    private static final AssertionFunctions DELEGATE = new DevelopmentAssertionFunctions();

    @Contract("null -> fail")
    public static void requireNonNull(Object object) {
        DELEGATE.requireNonNull(object);
    }

    @Contract("null, _ -> fail")
    public static void requireNonNull(Object object, String message) {
        DELEGATE.requireNonNull(object, message);
    }

    @Contract("null, _ -> fail")
    public static void requireNonNull(Object object, Supplier<String> message) {
        DELEGATE.requireNonNull(object, message);
    }

    public static void requireBgfxInitialized(String reason) {
        DELEGATE.requireBgfxInitialized(reason);
    }

    public static void require(boolean b) {
        DELEGATE.require(b);
    }

    public static void requireIfCapEnabled(boolean b, @NotNull BGFX_CAPS cap) { DELEGATE.requireIfCapEnabled(b, cap); }

    public static void requireIfCapsEnabled(boolean b, @NotNull BGFX_CAPS ... caps) { DELEGATE.requireIfCapsEnabled(b, caps); }

    public static void requireIfCapDisabled(boolean b, @NotNull BGFX_CAPS cap) { DELEGATE.requireIfCapDisabled(b, cap); }

    public static void requireIfCapsDisabled(boolean b, @NotNull BGFX_CAPS ... caps) { DELEGATE.requireIfCapsDisabled(b, caps); }

    public static void requireEmpty(Collection<?> c) { DELEGATE.requireEmpty(c); }
    public static void requireEmpty(Object[] arr) { DELEGATE.requireEmpty(arr); }
    public static void requireEmpty(byte[] arr) { DELEGATE.requireEmpty(arr); }
    public static void requireEmpty(short[] arr) { DELEGATE.requireEmpty(arr); }
    public static void requireEmpty(int[] arr) { DELEGATE.requireEmpty(arr); }
    public static void requireEmpty(long[] arr) { DELEGATE.requireEmpty(arr); }
    public static void requireEmpty(float[] arr) { DELEGATE.requireEmpty(arr); }
    public static void requireEmpty(double[] arr) { DELEGATE.requireEmpty(arr); }
    public static void requireEmpty(boolean[] arr) { DELEGATE.requireEmpty(arr); }
    public static void requireEmpty(char[] arr) { DELEGATE.requireEmpty(arr); }

    public static void requireNonEmpty(Collection<?> c) { DELEGATE.requireNonEmpty(c); }
    public static void requireNonEmpty(Object[] arr) { DELEGATE.requireNonEmpty(arr); }
    public static void requireNonEmpty(byte[] arr) { DELEGATE.requireEmpty(arr); }
    public static void requireNonEmpty(short[] arr) { DELEGATE.requireEmpty(arr); }
    public static void requireNonEmpty(int[] arr) { DELEGATE.requireEmpty(arr); }
    public static void requireNonEmpty(long[] arr) { DELEGATE.requireEmpty(arr); }
    public static void requireNonEmpty(float[] arr) { DELEGATE.requireEmpty(arr); }
    public static void requireNonEmpty(double[] arr) { DELEGATE.requireEmpty(arr); }
    public static void requireNonEmpty(boolean[] arr) { DELEGATE.requireEmpty(arr); }
    public static void requireNonEmpty(char[] arr) { DELEGATE.requireEmpty(arr); }

    // todo make library that lets primitive args be annotated with @AllPrims
    public static void requireEqualLength(Collection<?> c1, Collection<?> c2) { DELEGATE.requireEqualLength(c1, c2); }
    public static void requireEqualLength(Object[] arr1, Object[] arr2) { DELEGATE.requireEqualLength(arr1, arr2); }
    public static void requireEqualLength(Object[] arr1, byte[] arr2) { DELEGATE.requireEqualLength(arr1, arr2); }
    public static void requireEqualLength(Object[] arr1, short[] arr2) { DELEGATE.requireEqualLength(arr1, arr2); }
    public static void requireEqualLength(Object[] arr1, int[] arr2) { DELEGATE.requireEqualLength(arr1, arr2); }
    public static void requireEqualLength(Object[] arr1, long[] arr2) { DELEGATE.requireEqualLength(arr1, arr2); }
    public static void requireEqualLength(Object[] arr1, float[] arr2) { DELEGATE.requireEqualLength(arr1, arr2); }
    public static void requireEqualLength(Object[] arr1, double[] arr2) { DELEGATE.requireEqualLength(arr1, arr2); }
    public static void requireEqualLength(Object[] arr1, boolean[] arr2) { DELEGATE.requireEqualLength(arr1, arr2); }
    public static void requireEqualLength(Object[] arr1, char[] arr2) { DELEGATE.requireEqualLength(arr1, arr2); }
    public static void requireEqualLength(byte[] arr1, Object[] arr2) { DELEGATE.requireEqualLength(arr1, arr2); }
    public static void requireEqualLength(byte[] arr1, byte[] arr2) { DELEGATE.requireEqualLength(arr1, arr2); }
    public static void requireEqualLength(byte[] arr1, short[] arr2) { DELEGATE.requireEqualLength(arr1, arr2); }
    public static void requireEqualLength(byte[] arr1, int[] arr2) { DELEGATE.requireEqualLength(arr1, arr2); }
    public static void requireEqualLength(byte[] arr1, long[] arr2) { DELEGATE.requireEqualLength(arr1, arr2); }
    public static void requireEqualLength(byte[] arr1, float[] arr2) { DELEGATE.requireEqualLength(arr1, arr2); }
    public static void requireEqualLength(byte[] arr1, double[] arr2) { DELEGATE.requireEqualLength(arr1, arr2); }
    public static void requireEqualLength(byte[] arr1, boolean[] arr2) { DELEGATE.requireEqualLength(arr1, arr2); }
    public static void requireEqualLength(byte[] arr1, char[] arr2) { DELEGATE.requireEqualLength(arr1, arr2); }
    public static void requireEqualLength(short[] arr1, Object[] arr2) { DELEGATE.requireEqualLength(arr1, arr2); }
    public static void requireEqualLength(short[] arr1, byte[] arr2) { DELEGATE.requireEqualLength(arr1, arr2); }
    public static void requireEqualLength(short[] arr1, short[] arr2) { DELEGATE.requireEqualLength(arr1, arr2); }
    public static void requireEqualLength(short[] arr1, int[] arr2) { DELEGATE.requireEqualLength(arr1, arr2); }
    public static void requireEqualLength(short[] arr1, long[] arr2) { DELEGATE.requireEqualLength(arr1, arr2); }
    public static void requireEqualLength(short[] arr1, float[] arr2) { DELEGATE.requireEqualLength(arr1, arr2); }
    public static void requireEqualLength(short[] arr1, double[] arr2) { DELEGATE.requireEqualLength(arr1, arr2); }
    public static void requireEqualLength(short[] arr1, boolean[] arr2) { DELEGATE.requireEqualLength(arr1, arr2); }
    public static void requireEqualLength(short[] arr1, char[] arr2) { DELEGATE.requireEqualLength(arr1, arr2); }
    public static void requireEqualLength(int[] arr1, Object[] arr2) { DELEGATE.requireEqualLength(arr1, arr2); }
    public static void requireEqualLength(int[] arr1, byte[] arr2) { DELEGATE.requireEqualLength(arr1, arr2); }
    public static void requireEqualLength(int[] arr1, short[] arr2) { DELEGATE.requireEqualLength(arr1, arr2); }
    public static void requireEqualLength(int[] arr1, int[] arr2) { DELEGATE.requireEqualLength(arr1, arr2); }
    public static void requireEqualLength(int[] arr1, long[] arr2) { DELEGATE.requireEqualLength(arr1, arr2); }
    public static void requireEqualLength(int[] arr1, float[] arr2) { DELEGATE.requireEqualLength(arr1, arr2); }
    public static void requireEqualLength(int[] arr1, double[] arr2) { DELEGATE.requireEqualLength(arr1, arr2); }
    public static void requireEqualLength(int[] arr1, boolean[] arr2) { DELEGATE.requireEqualLength(arr1, arr2); }
    public static void requireEqualLength(int[] arr1, char[] arr2) { DELEGATE.requireEqualLength(arr1, arr2); }
    public static void requireEqualLength(long[] arr1, Object[] arr2) { DELEGATE.requireEqualLength(arr1, arr2); }
    public static void requireEqualLength(long[] arr1, byte[] arr2) { DELEGATE.requireEqualLength(arr1, arr2); }
    public static void requireEqualLength(long[] arr1, short[] arr2) { DELEGATE.requireEqualLength(arr1, arr2); }
    public static void requireEqualLength(long[] arr1, int[] arr2) { DELEGATE.requireEqualLength(arr1, arr2); }
    public static void requireEqualLength(long[] arr1, long[] arr2) { DELEGATE.requireEqualLength(arr1, arr2); }
    public static void requireEqualLength(long[] arr1, float[] arr2) { DELEGATE.requireEqualLength(arr1, arr2); }
    public static void requireEqualLength(long[] arr1, double[] arr2) { DELEGATE.requireEqualLength(arr1, arr2); }
    public static void requireEqualLength(long[] arr1, boolean[] arr2) { DELEGATE.requireEqualLength(arr1, arr2); }
    public static void requireEqualLength(long[] arr1, char[] arr2) { DELEGATE.requireEqualLength(arr1, arr2); }
    public static void requireEqualLength(float[] arr1, Object[] arr2) { DELEGATE.requireEqualLength(arr1, arr2); }
    public static void requireEqualLength(float[] arr1, byte[] arr2) { DELEGATE.requireEqualLength(arr1, arr2); }
    public static void requireEqualLength(float[] arr1, short[] arr2) { DELEGATE.requireEqualLength(arr1, arr2); }
    public static void requireEqualLength(float[] arr1, int[] arr2) { DELEGATE.requireEqualLength(arr1, arr2); }
    public static void requireEqualLength(float[] arr1, long[] arr2) { DELEGATE.requireEqualLength(arr1, arr2); }
    public static void requireEqualLength(float[] arr1, float[] arr2) { DELEGATE.requireEqualLength(arr1, arr2); }
    public static void requireEqualLength(float[] arr1, double[] arr2) { DELEGATE.requireEqualLength(arr1, arr2); }
    public static void requireEqualLength(float[] arr1, boolean[] arr2) { DELEGATE.requireEqualLength(arr1, arr2); }
    public static void requireEqualLength(float[] arr1, char[] arr2) { DELEGATE.requireEqualLength(arr1, arr2); }
    public static void requireEqualLength(double[] arr1, Object[] arr2) { DELEGATE.requireEqualLength(arr1, arr2); }
    public static void requireEqualLength(double[] arr1, byte[] arr2) { DELEGATE.requireEqualLength(arr1, arr2); }
    public static void requireEqualLength(double[] arr1, short[] arr2) { DELEGATE.requireEqualLength(arr1, arr2); }
    public static void requireEqualLength(double[] arr1, int[] arr2) { DELEGATE.requireEqualLength(arr1, arr2); }
    public static void requireEqualLength(double[] arr1, long[] arr2) { DELEGATE.requireEqualLength(arr1, arr2); }
    public static void requireEqualLength(double[] arr1, float[] arr2) { DELEGATE.requireEqualLength(arr1, arr2); }
    public static void requireEqualLength(double[] arr1, double[] arr2) { DELEGATE.requireEqualLength(arr1, arr2); }
    public static void requireEqualLength(double[] arr1, boolean[] arr2) { DELEGATE.requireEqualLength(arr1, arr2); }
    public static void requireEqualLength(double[] arr1, char[] arr2) { DELEGATE.requireEqualLength(arr1, arr2); }
    public static void requireEqualLength(boolean[] arr1, Object[] arr2) { DELEGATE.requireEqualLength(arr1, arr2); }
    public static void requireEqualLength(boolean[] arr1, byte[] arr2) { DELEGATE.requireEqualLength(arr1, arr2); }
    public static void requireEqualLength(boolean[] arr1, short[] arr2) { DELEGATE.requireEqualLength(arr1, arr2); }
    public static void requireEqualLength(boolean[] arr1, int[] arr2) { DELEGATE.requireEqualLength(arr1, arr2); }
    public static void requireEqualLength(boolean[] arr1, long[] arr2) { DELEGATE.requireEqualLength(arr1, arr2); }
    public static void requireEqualLength(boolean[] arr1, float[] arr2) { DELEGATE.requireEqualLength(arr1, arr2); }
    public static void requireEqualLength(boolean[] arr1, double[] arr2) { DELEGATE.requireEqualLength(arr1, arr2); }
    public static void requireEqualLength(boolean[] arr1, boolean[] arr2) { DELEGATE.requireEqualLength(arr1, arr2); }
    public static void requireEqualLength(boolean[] arr1, char[] arr2) { DELEGATE.requireEqualLength(arr1, arr2); }
    public static void requireEqualLength(char[] arr1, Object[] arr2) { DELEGATE.requireEqualLength(arr1, arr2); }
    public static void requireEqualLength(char[] arr1, byte[] arr2) { DELEGATE.requireEqualLength(arr1, arr2); }
    public static void requireEqualLength(char[] arr1, short[] arr2) { DELEGATE.requireEqualLength(arr1, arr2); }
    public static void requireEqualLength(char[] arr1, int[] arr2) { DELEGATE.requireEqualLength(arr1, arr2); }
    public static void requireEqualLength(char[] arr1, long[] arr2) { DELEGATE.requireEqualLength(arr1, arr2); }
    public static void requireEqualLength(char[] arr1, float[] arr2) { DELEGATE.requireEqualLength(arr1, arr2); }
    public static void requireEqualLength(char[] arr1, double[] arr2) { DELEGATE.requireEqualLength(arr1, arr2); }
    public static void requireEqualLength(char[] arr1, boolean[] arr2) { DELEGATE.requireEqualLength(arr1, arr2); }
    public static void requireEqualLength(char[] arr1, char[] arr2) { DELEGATE.requireEqualLength(arr1, arr2); }

    public static void requireNonNegative(byte n) {
        DELEGATE.requireNonNegative(n);
    }

    public static void requireNonNegative(short n) {
        DELEGATE.requireNonNegative(n);
    }

    public static void requireNonNegative(int n) {
        DELEGATE.requireNonNegative(n);
    }

    public static void requireNonNegative(long n) {
        DELEGATE.requireNonNegative(n);
    }

    public static void requireNonNegative(float n) {
        DELEGATE.requireNonNegative(n);
    }

    public static void requireNonNegative(double n) {
        DELEGATE.requireNonNegative(n);
    }

    public static void requireNonNegative(byte n, String message) {
        DELEGATE.requireNonNegative(n, message);
    }

    public static void requireNonNegative(short n, String message) {
        DELEGATE.requireNonNegative(n, message);
    }

    public static void requireNonNegative(int n, String message) {
        DELEGATE.requireNonNegative(n, message);
    }

    public static void requireNonNegative(long n, String message) {
        DELEGATE.requireNonNegative(n, message);
    }

    public static void requireNonNegative(float n, String message) {
        DELEGATE.requireNonNegative(n, message);
    }

    public static void requireNonNegative(double n, String message) {
        DELEGATE.requireNonNegative(n, message);
    }

    public static void requireNonNegative(byte n, Supplier<String> message) {
        DELEGATE.requireNonNegative(n, message);
    }

    public static void requireNonNegative(short n, Supplier<String> message) {
        DELEGATE.requireNonNegative(n, message);
    }

    public static void requireNonNegative(int n, Supplier<String> message) {
        DELEGATE.requireNonNegative(n, message);
    }

    public static void requireNonNegative(long n, Supplier<String> message) {
        DELEGATE.requireNonNegative(n, message);
    }

    public static void requireNonNegative(float n, Supplier<String> message) {
        DELEGATE.requireNonNegative(n, message);
    }

    public static void requireNonNegative(double n, Supplier<String> message) {
        DELEGATE.requireNonNegative(n, message);
    }

    public static void requirePositive(byte n) {
        DELEGATE.requirePositive(n);
    }

    public static void requirePositive(short n) {
        DELEGATE.requirePositive(n);
    }

    public static void requirePositive(int n) {
        DELEGATE.requirePositive(n);
    }

    public static void requirePositive(long n) {
        DELEGATE.requirePositive(n);
    }

    public static void requirePositive(float n) {
        DELEGATE.requirePositive(n);
    }

    public static void requirePositive(double n) {
        DELEGATE.requirePositive(n);
    }

    public static void requirePositive(byte n, String message) {
        DELEGATE.requirePositive(n, message);
    }

    public static void requirePositive(short n, String message) {
        DELEGATE.requirePositive(n, message);
    }

    public static void requirePositive(int n, String message) {
        DELEGATE.requirePositive(n, message);
    }

    public static void requirePositive(long n, String message) {
        DELEGATE.requirePositive(n, message);
    }

    public static void requirePositive(float n, String message) {
        DELEGATE.requirePositive(n, message);
    }

    public static void requirePositive(double n, String message) {
        DELEGATE.requirePositive(n, message);
    }

    public static void requirePositive(byte n, Supplier<String> message) {
        DELEGATE.requirePositive(n, message);
    }

    public static void requirePositive(short n, Supplier<String> message) {
        DELEGATE.requirePositive(n, message);
    }

    public static void requirePositive(int n, Supplier<String> message) {
        DELEGATE.requirePositive(n, message);
    }

    public static void requirePositive(long n, Supplier<String> message) {
        DELEGATE.requirePositive(n, message);
    }

    public static void requirePositive(float n, Supplier<String> message) {
        DELEGATE.requirePositive(n, message);
    }

    public static void requirePositive(double n, Supplier<String> message) {
        DELEGATE.requirePositive(n, message);
    }

    public static void require_uint8_t(int n) {
        try {
            DELEGATE.requireGreaterThanOrEqualTo(n, 0x0);
            DELEGATE.requireLessThanOrEqualTo(n, 0xFF);
        } catch (Throwable t) {
            throw new IllegalArgumentException(t);
        }
    }

    public static void require_uint16_t(int n) {
        try {
            DELEGATE.requireGreaterThanOrEqualTo(n, 0x0);
            DELEGATE.requireLessThanOrEqualTo(n, 0xFFFF);
        } catch (Throwable t) {
            throw new IllegalArgumentException(t);
        }
    }

    // todo is it feasible to make a require uint32_t? (would a long need to be passed?)

    public static void require_uint8(byte n) {
        try {
            DELEGATE.requireGreaterThanOrEqualTo(Byte.toUnsignedInt(n), 0x0);
            DELEGATE.requireLessThanOrEqualTo(Byte.toUnsignedInt(n), 0xFF);
        } catch (Throwable t) {
            throw new IllegalArgumentException(t);
        }
    }

    public static void require_uint8(int n) {
        try {
            DELEGATE.requireGreaterThanOrEqualTo(n, 0x0);
            DELEGATE.requireLessThanOrEqualTo(n, 0xFF);
        } catch (Throwable t) {
            throw new IllegalArgumentException(t);
        }
    }

    public static void require_uint10(short n) {
        try {
            DELEGATE.requireGreaterThanOrEqualTo(n, 0x0);
            DELEGATE.requireLessThanOrEqualTo(n, 0x3FF);
        } catch (Throwable t) {
            throw new IllegalArgumentException(t);
        }
    }

    public static void require_uint10(int n) {
        try {
            DELEGATE.requireGreaterThanOrEqualTo(n, 0x0);
            DELEGATE.requireLessThanOrEqualTo(n, 0x3FF);
        } catch (Throwable t) {
            throw new IllegalArgumentException(t);
        }
    }

    public static void require_int16(short n) {
        // true by definition
    }

    public static void require_int16(int n) {
        try {
            DELEGATE.requireGreaterThanOrEqualTo(n, (int) Short.MIN_VALUE);
            DELEGATE.requireLessThanOrEqualTo(n, (int) Short.MAX_VALUE);
        } catch (Throwable t) {
            throw new IllegalArgumentException(t);
        }
    }

    public static void require_half(float n) {
        try {
            // todo confirm?
            // https://software.intel.com/content/www/us/en/develop/articles/performance-benefits-of-half-precision-floats.html
            DELEGATE.requireGreaterThanOrEqualTo(n, -65504.0f);
            DELEGATE.requireLessThanOrEqualTo(n, 65504.0f);
        } catch (Throwable t) {
            throw new IllegalArgumentException(t);
        }
    }

    public static void requireLessThan(byte n, byte upperBoundExclusive) { DELEGATE.requireLessThan(n, upperBoundExclusive); }
    public static void requireLessThan(short n, short upperBoundExclusive) { DELEGATE.requireLessThan(n, upperBoundExclusive); }
    public static void requireLessThan(int n, int upperBoundExclusive) { DELEGATE.requireLessThan(n, upperBoundExclusive); }
    public static void requireLessThan(long n, long upperBoundExclusive) { DELEGATE.requireLessThan(n, upperBoundExclusive); }
    public static void requireLessThan(float n, float upperBoundExclusive) { DELEGATE.requireLessThan(n, upperBoundExclusive); }
    public static void requireLessThan(double n, double upperBoundExclusive) { DELEGATE.requireLessThan(n, upperBoundExclusive); }
    public static void requireLessThan(byte n, byte upperBoundExclusive, String message) { DELEGATE.requireLessThan(n, upperBoundExclusive, message); }
    public static void requireLessThan(short n, short upperBoundExclusive, String message) { DELEGATE.requireLessThan(n, upperBoundExclusive, message); }
    public static void requireLessThan(int n, int upperBoundExclusive, String message) { DELEGATE.requireLessThan(n, upperBoundExclusive, message); }
    public static void requireLessThan(long n, long upperBoundExclusive, String message) { DELEGATE.requireLessThan(n, upperBoundExclusive, message); }
    public static void requireLessThan(float n, float upperBoundExclusive, String message) { DELEGATE.requireLessThan(n, upperBoundExclusive, message); }
    public static void requireLessThan(double n, double upperBoundExclusive, String message) { DELEGATE.requireLessThan(n, upperBoundExclusive, message); }
    public static void requireLessThan(byte n, byte upperBoundExclusive, Supplier<String> message) { DELEGATE.requireLessThan(n, upperBoundExclusive, message); }
    public static void requireLessThan(short n, short upperBoundExclusive, Supplier<String> message) { DELEGATE.requireLessThan(n, upperBoundExclusive, message); }
    public static void requireLessThan(int n, int upperBoundExclusive, Supplier<String> message) { DELEGATE.requireLessThan(n, upperBoundExclusive, message); }
    public static void requireLessThan(long n, long upperBoundExclusive, Supplier<String> message) { DELEGATE.requireLessThan(n, upperBoundExclusive, message); }
    public static void requireLessThan(float n, float upperBoundExclusive, Supplier<String> message) { DELEGATE.requireLessThan(n, upperBoundExclusive, message); }
    public static void requireLessThan(double n, double upperBoundExclusive, Supplier<String> message) { DELEGATE.requireLessThan(n, upperBoundExclusive, message); }
    public static void requireGreaterThan(byte n, byte lowerBoundExclusive) { DELEGATE.requireGreaterThan(n, lowerBoundExclusive); }
    public static void requireGreaterThan(short n, short lowerBoundExclusive) { DELEGATE.requireGreaterThan(n, lowerBoundExclusive); }
    public static void requireGreaterThan(int n, int lowerBoundExclusive) { DELEGATE.requireGreaterThan(n, lowerBoundExclusive); }
    public static void requireGreaterThan(long n, long lowerBoundExclusive) { DELEGATE.requireGreaterThan(n, lowerBoundExclusive); }
    public static void requireGreaterThan(float n, float lowerBoundExclusive) { DELEGATE.requireGreaterThan(n, lowerBoundExclusive); }
    public static void requireGreaterThan(double n, double lowerBoundExclusive) { DELEGATE.requireGreaterThan(n, lowerBoundExclusive); }
    public static void requireGreaterThan(byte n, byte lowerBoundExclusive, String message) { DELEGATE.requireGreaterThan(n, lowerBoundExclusive, message); }
    public static void requireGreaterThan(short n, short lowerBoundExclusive, String message) { DELEGATE.requireGreaterThan(n, lowerBoundExclusive, message); }
    public static void requireGreaterThan(int n, int lowerBoundExclusive, String message) { DELEGATE.requireGreaterThan(n, lowerBoundExclusive, message); }
    public static void requireGreaterThan(long n, long lowerBoundExclusive, String message) { DELEGATE.requireGreaterThan(n, lowerBoundExclusive, message); }
    public static void requireGreaterThan(float n, float lowerBoundExclusive, String message) { DELEGATE.requireGreaterThan(n, lowerBoundExclusive, message); }
    public static void requireGreaterThan(double n, double lowerBoundExclusive, String message) { DELEGATE.requireGreaterThan(n, lowerBoundExclusive, message); }
    public static void requireGreaterThan(byte n, byte lowerBoundExclusive, Supplier<String> message) { DELEGATE.requireGreaterThan(n, lowerBoundExclusive, message); }
    public static void requireGreaterThan(short n, short lowerBoundExclusive, Supplier<String> message) { DELEGATE.requireGreaterThan(n, lowerBoundExclusive, message); }
    public static void requireGreaterThan(int n, int lowerBoundExclusive, Supplier<String> message) { DELEGATE.requireGreaterThan(n, lowerBoundExclusive, message); }
    public static void requireGreaterThan(long n, long lowerBoundExclusive, Supplier<String> message) { DELEGATE.requireGreaterThan(n, lowerBoundExclusive, message); }
    public static void requireGreaterThan(float n, float lowerBoundExclusive, Supplier<String> message) { DELEGATE.requireGreaterThan(n, lowerBoundExclusive, message); }
    public static void requireGreaterThan(double n, double lowerBoundExclusive, Supplier<String> message) { DELEGATE.requireGreaterThan(n, lowerBoundExclusive, message); }
    public static void requireLessThanOrEqualTo(byte n, byte upperBoundInclusive) { DELEGATE.requireLessThanOrEqualTo(n, upperBoundInclusive); }
    public static void requireLessThanOrEqualTo(short n, short upperBoundInclusive) { DELEGATE.requireLessThanOrEqualTo(n, upperBoundInclusive); }
    public static void requireLessThanOrEqualTo(int n, int upperBoundInclusive) { DELEGATE.requireLessThanOrEqualTo(n, upperBoundInclusive); }
    public static void requireLessThanOrEqualTo(long n, long upperBoundInclusive) { DELEGATE.requireLessThanOrEqualTo(n, upperBoundInclusive); }
    public static void requireLessThanOrEqualTo(float n, float upperBoundInclusive) { DELEGATE.requireLessThanOrEqualTo(n, upperBoundInclusive); }
    public static void requireLessThanOrEqualTo(double n, double upperBoundInclusive) { DELEGATE.requireLessThanOrEqualTo(n, upperBoundInclusive); }
    public static void requireLessThanOrEqualTo(byte n, byte upperBoundInclusive, String message) { DELEGATE.requireLessThanOrEqualTo(n, upperBoundInclusive, message); }
    public static void requireLessThanOrEqualTo(short n, short upperBoundInclusive, String message) { DELEGATE.requireLessThanOrEqualTo(n, upperBoundInclusive, message); }
    public static void requireLessThanOrEqualTo(int n, int upperBoundInclusive, String message) { DELEGATE.requireLessThanOrEqualTo(n, upperBoundInclusive, message); }
    public static void requireLessThanOrEqualTo(long n, long upperBoundInclusive, String message) { DELEGATE.requireLessThanOrEqualTo(n, upperBoundInclusive, message); }
    public static void requireLessThanOrEqualTo(float n, float upperBoundInclusive, String message) { DELEGATE.requireLessThanOrEqualTo(n, upperBoundInclusive, message); }
    public static void requireLessThanOrEqualTo(double n, double upperBoundInclusive, String message) { DELEGATE.requireLessThanOrEqualTo(n, upperBoundInclusive, message); }
    public static void requireLessThanOrEqualTo(byte n, byte upperBoundInclusive, Supplier<String> message) { DELEGATE.requireLessThanOrEqualTo(n, upperBoundInclusive, message); }
    public static void requireLessThanOrEqualTo(short n, short upperBoundInclusive, Supplier<String> message) { DELEGATE.requireLessThanOrEqualTo(n, upperBoundInclusive, message); }
    public static void requireLessThanOrEqualTo(int n, int upperBoundInclusive, Supplier<String> message) { DELEGATE.requireLessThanOrEqualTo(n, upperBoundInclusive, message); }
    public static void requireLessThanOrEqualTo(long n, long upperBoundInclusive, Supplier<String> message) { DELEGATE.requireLessThanOrEqualTo(n, upperBoundInclusive, message); }
    public static void requireLessThanOrEqualTo(float n, float upperBoundInclusive, Supplier<String> message) { DELEGATE.requireLessThanOrEqualTo(n, upperBoundInclusive, message); }
    public static void requireLessThanOrEqualTo(double n, double upperBoundInclusive, Supplier<String> message) { DELEGATE.requireLessThanOrEqualTo(n, upperBoundInclusive, message); }
    public static void requireGreaterThanOrEqualTo(byte n, byte lowerBoundInclusive) { DELEGATE.requireGreaterThanOrEqualTo(n, lowerBoundInclusive); }
    public static void requireGreaterThanOrEqualTo(short n, short lowerBoundInclusive) { DELEGATE.requireGreaterThanOrEqualTo(n, lowerBoundInclusive); }
    public static void requireGreaterThanOrEqualTo(int n, int lowerBoundInclusive) { DELEGATE.requireGreaterThanOrEqualTo(n, lowerBoundInclusive); }
    public static void requireGreaterThanOrEqualTo(long n, long lowerBoundInclusive) { DELEGATE.requireGreaterThanOrEqualTo(n, lowerBoundInclusive); }
    public static void requireGreaterThanOrEqualTo(float n, float lowerBoundInclusive) { DELEGATE.requireGreaterThanOrEqualTo(n, lowerBoundInclusive); }
    public static void requireGreaterThanOrEqualTo(double n, double lowerBoundInclusive) { DELEGATE.requireGreaterThanOrEqualTo(n, lowerBoundInclusive); }
    public static void requireGreaterThanOrEqualTo(byte n, byte lowerBoundInclusive, String message) { DELEGATE.requireGreaterThanOrEqualTo(n, lowerBoundInclusive, message); }
    public static void requireGreaterThanOrEqualTo(short n, short lowerBoundInclusive, String message) { DELEGATE.requireGreaterThanOrEqualTo(n, lowerBoundInclusive, message); }
    public static void requireGreaterThanOrEqualTo(int n, int lowerBoundInclusive, String message) { DELEGATE.requireGreaterThanOrEqualTo(n, lowerBoundInclusive, message); }
    public static void requireGreaterThanOrEqualTo(long n, long lowerBoundInclusive, String message) { DELEGATE.requireGreaterThanOrEqualTo(n, lowerBoundInclusive, message); }
    public static void requireGreaterThanOrEqualTo(float n, float lowerBoundInclusive, String message) { DELEGATE.requireGreaterThanOrEqualTo(n, lowerBoundInclusive, message); }
    public static void requireGreaterThanOrEqualTo(double n, double lowerBoundInclusive, String message) { DELEGATE.requireGreaterThanOrEqualTo(n, lowerBoundInclusive, message); }
    public static void requireGreaterThanOrEqualTo(byte n, byte lowerBoundInclusive, Supplier<String> message) { DELEGATE.requireGreaterThanOrEqualTo(n, lowerBoundInclusive, message); }
    public static void requireGreaterThanOrEqualTo(short n, short lowerBoundInclusive, Supplier<String> message) { DELEGATE.requireGreaterThanOrEqualTo(n, lowerBoundInclusive, message); }
    public static void requireGreaterThanOrEqualTo(int n, int lowerBoundInclusive, Supplier<String> message) { DELEGATE.requireGreaterThanOrEqualTo(n, lowerBoundInclusive, message); }
    public static void requireGreaterThanOrEqualTo(long n, long lowerBoundInclusive, Supplier<String> message) { DELEGATE.requireGreaterThanOrEqualTo(n, lowerBoundInclusive, message); }
    public static void requireGreaterThanOrEqualTo(float n, float lowerBoundInclusive, Supplier<String> message) { DELEGATE.requireGreaterThanOrEqualTo(n, lowerBoundInclusive, message); }
    public static void requireGreaterThanOrEqualTo(double n, double lowerBoundInclusive, Supplier<String> message) { DELEGATE.requireGreaterThanOrEqualTo(n, lowerBoundInclusive, message); }

}
