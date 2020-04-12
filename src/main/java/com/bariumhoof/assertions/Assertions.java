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

    public static void requireEmpty(Collection<?> c) {
        DELEGATE.requireEmpty(c);
    }

    public static void requireNonEmpty(Collection<?> c) {
        DELEGATE.requireNonEmpty(c);
    }

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

    public static void require_uint8(byte n) {
        DELEGATE.requireGreaterThanOrEqualTo(n, 0x0);
        DELEGATE.requireLessThanOrEqualTo(n, 0xFF);
    }

    public static void require_uint8(short n) {
        DELEGATE.requireGreaterThanOrEqualTo(n, 0x0);
        DELEGATE.requireLessThanOrEqualTo(n, 0xFF);
    }

    public static void require_uint8(int n) {
        DELEGATE.requireGreaterThanOrEqualTo(n, 0x0);
        DELEGATE.requireLessThanOrEqualTo(n, 0xFF);
    }

    public static void require_uint8(long n) {
        DELEGATE.requireGreaterThanOrEqualTo(n, 0x0);
        DELEGATE.requireLessThanOrEqualTo(n, 0xFF);
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
