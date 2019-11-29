package com.bariumhoof.assertions;

import com.bariumhoof.bgfx4j.enums.BGFX_CAPS;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.function.Supplier;

/**
 * During release assertions become NO-OPS to be optimized away.
 * Note this breaks IDEA contracts, but this is OK.
 */
final class ReleaseAssertionFunctions implements AssertionFunctions {

    @Contract("null -> fail")
    @Override
    public void requireNonNull(Object object) {
        // NO OP
    }

    @Contract("null, _ -> fail")
    @Override
    public void requireNonNull(Object object, String message) {
        // NO OP.
    }

    @Contract("null, _ -> fail")
    @Override
    public void requireNonNull(Object object, Supplier<String> message) {
        // NO OP.
    }

    @Override
    public void requireBgfxInitialized(String reason) {
        // NO OP.
    }

    @Override
    public void require(boolean b) {
        // NO OP.
    }

    @Override
    public void requireIfCapEnabled(boolean b, @NotNull BGFX_CAPS cap) {
        // NO OP.
    }

    @Override
    public void requireIfCapsEnabled(boolean b, @NotNull BGFX_CAPS... caps) {
        // NO OP.
    }

    @Override
    public void requireIfCapDisabled(boolean b, @NotNull BGFX_CAPS cap) {
        // NO OP.
    }

    @Override
    public void requireIfCapsDisabled(boolean b, @NotNull BGFX_CAPS... caps) {
        // NO OP.
    }

    @Override
    public void requireEmpty(Collection<?> c) {
        // NO OP.
    }

    @Override
    public void requireNonEmpty(Collection<?> c) {
        // NO OP.
    }

    @Override
    public void requireNonNegative(byte n) {
        // NO OP.
    }

    @Override
    public void requireNonNegative(short n) {
        // NO OP.
    }

    @Override
    public void requireNonNegative(int n) {
        // NO OP.
    }

    @Override
    public void requireNonNegative(long n) {
        // NO OP.
    }

    @Override
    public void requireNonNegative(float n) {
        // NO OP.
    }

    @Override
    public void requireNonNegative(double n) {
        // NO OP.
    }

    @Override
    public void requireNonNegative(byte n, String message) {
        // NO OP.
    }

    @Override
    public void requireNonNegative(short n, String message) {
        // NO OP.
    }

    @Override
    public void requireNonNegative(int n, String message) {
        // NO OP.
    }

    @Override
    public void requireNonNegative(long n, String message) {
        // NO OP.
    }

    @Override
    public void requireNonNegative(float n, String message) {
        // NO OP.
    }

    @Override
    public void requireNonNegative(double n, String message) {
        // NO OP.
    }

    @Override
    public void requireNonNegative(byte n, Supplier<String> message) {
        // NO OP.
    }

    @Override
    public void requireNonNegative(short n, Supplier<String> message) {
        // NO OP.
    }

    @Override
    public void requireNonNegative(int n, Supplier<String> message) {
        // NO OP.
    }

    @Override
    public void requireNonNegative(long n, Supplier<String> message) {
        // NO OP.
    }

    @Override
    public void requireNonNegative(float n, Supplier<String> message) {
        // NO OP.
    }

    @Override
    public void requireNonNegative(double n, Supplier<String> message) {
        // NO OP.
    }

    @Override
    public void requirePositive(byte n) {
        // NO OP.
    }

    @Override
    public void requirePositive(short n) {
        // NO OP.
    }

    @Override
    public void requirePositive(int n) {
        // NO OP.
    }

    @Override
    public void requirePositive(long n) {
        // NO OP.
    }

    @Override
    public void requirePositive(float n) {
        // NO OP.
    }

    @Override
    public void requirePositive(double n) {
        // NO OP.
    }

    @Override
    public void requirePositive(byte n, String message) {
        // NO OP.
    }

    @Override
    public void requirePositive(short n, String message) {
        // NO OP.
    }

    @Override
    public void requirePositive(int n, String message) {
        // NO OP.
    }

    @Override
    public void requirePositive(long n, String message) {
        // NO OP.
    }

    @Override
    public void requirePositive(float n, String message) {
        // NO OP.
    }

    @Override
    public void requirePositive(double n, String message) {
        // NO OP.
    }

    @Override
    public void requirePositive(byte n, Supplier<String> message) {
        // NO OP.
    }

    @Override
    public void requirePositive(short n, Supplier<String> message) {
        // NO OP.
    }

    @Override
    public void requirePositive(int n, Supplier<String> message) {
        // NO OP.
    }

    @Override
    public void requirePositive(long n, Supplier<String> message) {
        // NO OP.
    }

    @Override
    public void requirePositive(float n, Supplier<String> message) {
        // NO OP.
    }

    @Override
    public void requirePositive(double n, Supplier<String> message) {
        // NO OP.
    }

    @Override
    public void requireLessThan(byte n, byte upperBoundExclusive) {
        // NO OP.
    }

    @Override
    public void requireLessThan(short n, short upperBoundExclusive) {
        // NO OP.
    }

    @Override
    public void requireLessThan(int n, int upperBoundExclusive) {
        // NO OP.
    }

    @Override
    public void requireLessThan(long n, long upperBoundExclusive) {
        // NO OP.
    }

    @Override
    public void requireLessThan(float n, float upperBoundExclusive) {
        // NO OP.
    }

    @Override
    public void requireLessThan(double n, double upperBoundExclusive) {
        // NO OP.
    }

    @Override
    public void requireLessThan(byte n, byte upperBoundExclusive, String message) {
        // NO OP.
    }

    @Override
    public void requireLessThan(short n, short upperBoundExclusive, String message) {
        // NO OP.
    }

    @Override
    public void requireLessThan(int n, int upperBoundExclusive, String message) {
        // NO OP.
    }

    @Override
    public void requireLessThan(long n, long upperBoundExclusive, String message) {
        // NO OP.
    }

    @Override
    public void requireLessThan(float n, float upperBoundExclusive, String message) {
        // NO OP.
    }

    @Override
    public void requireLessThan(double n, double upperBoundExclusive, String message) {
        // NO OP.
    }

    @Override
    public void requireLessThan(byte n, byte upperBoundExclusive, Supplier<String> message) {
        // NO OP.
    }

    @Override
    public void requireLessThan(short n, short upperBoundExclusive, Supplier<String> message) {
        // NO OP.
    }

    @Override
    public void requireLessThan(int n, int upperBoundExclusive, Supplier<String> message) {
        // NO OP.
    }

    @Override
    public void requireLessThan(long n, long upperBoundExclusive, Supplier<String> message) {
        // NO OP.
    }

    @Override
    public void requireLessThan(float n, float upperBoundExclusive, Supplier<String> message) {
        // NO OP.
    }

    @Override
    public void requireLessThan(double n, double upperBoundExclusive, Supplier<String> message) {
        // NO OP.
    }

    @Override
    public void requireGreaterThan(byte n, byte upperBoundExclusive) {
        // NO OP.
    }

    @Override
    public void requireGreaterThan(short n, short upperBoundExclusive) {
        // NO OP.
    }

    @Override
    public void requireGreaterThan(int n, int upperBoundExclusive) {
        // NO OP.
    }

    @Override
    public void requireGreaterThan(long n, long upperBoundExclusive) {
        // NO OP.
    }

    @Override
    public void requireGreaterThan(float n, float upperBoundExclusive) {
        // NO OP.
    }

    @Override
    public void requireGreaterThan(double n, double upperBoundExclusive) {
        // NO OP.
    }

    @Override
    public void requireGreaterThan(byte n, byte upperBoundExclusive, String message) {
        // NO OP.
    }

    @Override
    public void requireGreaterThan(short n, short upperBoundExclusive, String message) {
        // NO OP.
    }

    @Override
    public void requireGreaterThan(int n, int upperBoundExclusive, String message) {
        // NO OP.
    }

    @Override
    public void requireGreaterThan(long n, long upperBoundExclusive, String message) {
        // NO OP.
    }

    @Override
    public void requireGreaterThan(float n, float upperBoundExclusive, String message) {
        // NO OP.
    }

    @Override
    public void requireGreaterThan(double n, double upperBoundExclusive, String message) {
        // NO OP.
    }

    @Override
    public void requireGreaterThan(byte n, byte upperBoundExclusive, Supplier<String> message) {
        // NO OP.
    }

    @Override
    public void requireGreaterThan(short n, short upperBoundExclusive, Supplier<String> message) {
        // NO OP.
    }

    @Override
    public void requireGreaterThan(int n, int upperBoundExclusive, Supplier<String> message) {
        // NO OP.
    }

    @Override
    public void requireGreaterThan(long n, long upperBoundExclusive, Supplier<String> message) {
        // NO OP.
    }

    @Override
    public void requireGreaterThan(float n, float upperBoundExclusive, Supplier<String> message) {
        // NO OP.
    }

    @Override
    public void requireGreaterThan(double n, double upperBoundExclusive, Supplier<String> message) {
        // NO OP.
    }

    @Override
    public void requireLessThanOrEqualTo(byte n, byte upperBoundInclusive) {
        // NO OP.
    }

    @Override
    public void requireLessThanOrEqualTo(short n, short upperBoundInclusive) {
        // NO OP.
    }

    @Override
    public void requireLessThanOrEqualTo(int n, int upperBoundInclusive) {
        // NO OP.
    }

    @Override
    public void requireLessThanOrEqualTo(long n, long upperBoundInclusive) {
        // NO OP.
    }

    @Override
    public void requireLessThanOrEqualTo(float n, float upperBoundInclusive) {
        // NO OP.
    }

    @Override
    public void requireLessThanOrEqualTo(double n, double upperBoundInclusive) {
        // NO OP.
    }

    @Override
    public void requireLessThanOrEqualTo(byte n, byte upperBoundInclusive, String message) {
        // NO OP.
    }

    @Override
    public void requireLessThanOrEqualTo(short n, short upperBoundInclusive, String message) {
        // NO OP.
    }

    @Override
    public void requireLessThanOrEqualTo(int n, int upperBoundInclusive, String message) {
        // NO OP.
    }

    @Override
    public void requireLessThanOrEqualTo(long n, long upperBoundInclusive, String message) {
        // NO OP.
    }

    @Override
    public void requireLessThanOrEqualTo(float n, float upperBoundInclusive, String message) {
        // NO OP.
    }

    @Override
    public void requireLessThanOrEqualTo(double n, double upperBoundInclusive, String message) {
        // NO OP.
    }

    @Override
    public void requireLessThanOrEqualTo(byte n, byte upperBoundInclusive, Supplier<String> message) {
        // NO OP.
    }

    @Override
    public void requireLessThanOrEqualTo(short n, short upperBoundInclusive, Supplier<String> message) {
        // NO OP.
    }

    @Override
    public void requireLessThanOrEqualTo(int n, int upperBoundInclusive, Supplier<String> message) {
        // NO OP.
    }

    @Override
    public void requireLessThanOrEqualTo(long n, long upperBoundInclusive, Supplier<String> message) {
        // NO OP.
    }

    @Override
    public void requireLessThanOrEqualTo(float n, float upperBoundInclusive, Supplier<String> message) {
        // NO OP.
    }

    @Override
    public void requireLessThanOrEqualTo(double n, double upperBoundInclusive, Supplier<String> message) {
        // NO OP.
    }

    @Override
    public void requireGreaterThanOrEqualTo(byte n, byte upperBoundInclusive) {
        // NO OP.
    }

    @Override
    public void requireGreaterThanOrEqualTo(short n, short upperBoundInclusive) {
        // NO OP.
    }

    @Override
    public void requireGreaterThanOrEqualTo(int n, int upperBoundInclusive) {
        // NO OP.
    }

    @Override
    public void requireGreaterThanOrEqualTo(long n, long upperBoundInclusive) {
        // NO OP.
    }

    @Override
    public void requireGreaterThanOrEqualTo(float n, float upperBoundInclusive) {
        // NO OP.
    }

    @Override
    public void requireGreaterThanOrEqualTo(double n, double upperBoundInclusive) {
        // NO OP.
    }

    @Override
    public void requireGreaterThanOrEqualTo(byte n, byte upperBoundInclusive, String message) {
        // NO OP.
    }

    @Override
    public void requireGreaterThanOrEqualTo(short n, short upperBoundInclusive, String message) {
        // NO OP.
    }

    @Override
    public void requireGreaterThanOrEqualTo(int n, int upperBoundInclusive, String message) {
        // NO OP.
    }

    @Override
    public void requireGreaterThanOrEqualTo(long n, long upperBoundInclusive, String message) {
        // NO OP.
    }

    @Override
    public void requireGreaterThanOrEqualTo(float n, float upperBoundInclusive, String message) {
        // NO OP.
    }

    @Override
    public void requireGreaterThanOrEqualTo(double n, double upperBoundInclusive, String message) {
        // NO OP.
    }

    @Override
    public void requireGreaterThanOrEqualTo(byte n, byte upperBoundInclusive, Supplier<String> message) {
        // NO OP.
    }

    @Override
    public void requireGreaterThanOrEqualTo(short n, short upperBoundInclusive, Supplier<String> message) {
        // NO OP.
    }

    @Override
    public void requireGreaterThanOrEqualTo(int n, int upperBoundInclusive, Supplier<String> message) {
        // NO OP.
    }

    @Override
    public void requireGreaterThanOrEqualTo(long n, long upperBoundInclusive, Supplier<String> message) {
        // NO OP.
    }

    @Override
    public void requireGreaterThanOrEqualTo(float n, float upperBoundInclusive, Supplier<String> message) {
        // NO OP.
    }

    @Override
    public void requireGreaterThanOrEqualTo(double n, double upperBoundInclusive, Supplier<String> message) {
        // NO OP.
    }
}
