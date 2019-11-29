package com.bariumhoof.assertions;

import com.bariumhoof.Capabilities;
import com.bariumhoof.bgfx4j.Application;
import com.bariumhoof.bgfx4j.enums.BGFX_CAPS;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.Objects;
import java.util.function.Supplier;

@Slf4j
final class DevelopmentAssertionFunctions implements AssertionFunctions {

    @Contract("null -> fail")
    @Override
    public void requireNonNull(Object object) {
        Objects.requireNonNull(object);
    }

    @Contract("null, _ -> fail")
    @Override
    public void requireNonNull(Object object, String message) {
        Objects.requireNonNull(object, message);
    }

    @Contract("null, _ -> fail")
    @Override
    public void requireNonNull(Object object, Supplier<String> message) {
        Objects.requireNonNull(object, message);
    }

    @Override
    public void requireBgfxInitialized(String reason) {
        if (!Application.isInitialized()) {
            log.error("Assertion Failed!");
            Thread.dumpStack();
            throw new IllegalStateException();
        }
    }

    @Override
    public void require(boolean b) {
        if (!b) {
            log.error("Assertion Failed!");
            Thread.dumpStack();
            throw new IllegalArgumentException();
        }
    }

    @Override
    public void requireIfCapEnabled(boolean b, @NotNull BGFX_CAPS cap) {
        if (Capabilities.isSupported(cap)) {
            if (!b) {
                log.error("Assertion Failed!");
                Thread.dumpStack();
                throw new UnsupportedOperationException();
            }
        }
    }

    @Override
    public void requireIfCapsEnabled(boolean b, @NotNull BGFX_CAPS... caps) {
        if (Capabilities.allSupported(caps)) {
            if (!b) {
                log.error("Assertion Failed!");
                Thread.dumpStack();
                throw new UnsupportedOperationException();
            }
        }
    }

    @Override
    public void requireIfCapDisabled(boolean b, @NotNull BGFX_CAPS cap) {
        if (!Capabilities.isSupported(cap)) {
            if (!b) {
                log.error("Assertion Failed!");
                Thread.dumpStack();
                throw new UnsupportedOperationException();
            }
        }
    }

    @Override
    public void requireIfCapsDisabled(boolean b, @NotNull BGFX_CAPS... caps) {
        if (Capabilities.noneSupported(caps)) {
            if (!b) {
                log.error("Assertion Failed!");
                Thread.dumpStack();
                throw new UnsupportedOperationException();
            }
        }
    }

    @Override
    public void requireEmpty(Collection<?> c) {
        if (!c.isEmpty()) {
            log.error("Assertion Failed!");
            Thread.dumpStack();
            throw new IllegalArgumentException("Collection must be empty.");
        }
    }

    @Override
    public void requireNonEmpty(Collection<?> c) {
        if (!c.isEmpty()) {
            log.error("Assertion Failed!");
            Thread.dumpStack();
            throw new IllegalArgumentException("Collection must be empty.");
        }
    }

    @Override
    public void requireNonNegative(byte n) {
        if (n < 0) {
            log.error("Assertion Failed!");
            Thread.dumpStack();
            throw new IllegalArgumentException();
        }
    }

    @Override
    public void requireNonNegative(short n) {
        if (n < 0) {
            log.error("Assertion Failed!");
            Thread.dumpStack();
            throw new IllegalArgumentException();
        }
    }

    @Override
    public void requireNonNegative(int n) {
        if (n < 0) {
            log.error("Assertion Failed!");
            Thread.dumpStack();
            throw new IllegalArgumentException();
        }
    }

    @Override
    public void requireNonNegative(long n) {
        if (n < 0) {
            log.error("Assertion Failed!");
            Thread.dumpStack();
            throw new IllegalArgumentException();
        }
    }

    @Override
    public void requireNonNegative(float n) {
        if (n < 0) {
            log.error("Assertion Failed!");
            Thread.dumpStack();
            throw new IllegalArgumentException();
        }
    }

    @Override
    public void requireNonNegative(double n) {
        if (n < 0) {
            log.error("Assertion Failed!");
            Thread.dumpStack();
            throw new IllegalArgumentException();
        }
    }

    @Override
    public void requireNonNegative(byte n, String message) {
        if (n < 0) {
            log.error("Assertion Failed!");
            Thread.dumpStack();
            throw new IllegalArgumentException(message);
        }
    }

    @Override
    public void requireNonNegative(short n, String message) {
        if (n < 0) {
            log.error("Assertion Failed!");
            Thread.dumpStack();
            throw new IllegalArgumentException(message);
        }
    }

    @Override
    public void requireNonNegative(int n, String message) {
        if (n < 0) {
            log.error("Assertion Failed!");
            Thread.dumpStack();
            throw new IllegalArgumentException(message);
        }
    }

    @Override
    public void requireNonNegative(long n, String message) {
        if (n < 0) {
            log.error("Assertion Failed!");
            Thread.dumpStack();
            throw new IllegalArgumentException(message);
        }
    }

    @Override
    public void requireNonNegative(float n, String message) {
        if (n < 0) {
            log.error("Assertion Failed!");
            Thread.dumpStack();
            throw new IllegalArgumentException(message);
        }
    }

    @Override
    public void requireNonNegative(double n, String message) {
        if (n < 0) {
            log.error("Assertion Failed!");
            Thread.dumpStack();
            throw new IllegalArgumentException(message);
        }
    }

    @Override
    public void requireNonNegative(byte n, Supplier<String> message) {
        if (n < 0) {
            log.error("Assertion Failed!");
            Thread.dumpStack();
            throw new IllegalArgumentException(message.get());
        }
    }

    @Override
    public void requireNonNegative(short n, Supplier<String> message) {
        if (n < 0) {
            log.error("Assertion Failed!");
            Thread.dumpStack();
            throw new IllegalArgumentException(message.get());
        }
    }

    @Override
    public void requireNonNegative(int n, Supplier<String> message) {
        if (n < 0) {
            log.error("Assertion Failed!");
            Thread.dumpStack();
            throw new IllegalArgumentException(message.get());
        }
    }

    @Override
    public void requireNonNegative(long n, Supplier<String> message) {
        if (n < 0) {
            log.error("Assertion Failed!");
            Thread.dumpStack();
            throw new IllegalArgumentException(message.get());
        }
    }

    @Override
    public void requireNonNegative(float n, Supplier<String> message) {
        if (n < 0) {
            log.error("Assertion Failed!");
            Thread.dumpStack();
            throw new IllegalArgumentException(message.get());
        }
    }

    @Override
    public void requireNonNegative(double n, Supplier<String> message) {
        if (n < 0) {
            log.error("Assertion Failed!");
            Thread.dumpStack();
            throw new IllegalArgumentException(message.get());
        }
    }

    @Override
    public void requirePositive(byte n) {
        if (n <= 0) {
            log.error("Assertion Failed!");
            Thread.dumpStack();
            throw new IllegalArgumentException();
        }
    }

    @Override
    public void requirePositive(short n) {
        if (n <= 0) {
            log.error("Assertion Failed!");
            Thread.dumpStack();
            throw new IllegalArgumentException();
        }
    }

    @Override
    public void requirePositive(int n) {
        if (n <= 0) {
            log.error("Assertion Failed!");
            Thread.dumpStack();
            throw new IllegalArgumentException();
        }
    }

    @Override
    public void requirePositive(long n) {
        if (n <= 0) {
            log.error("Assertion Failed!");
            Thread.dumpStack();
            throw new IllegalArgumentException();
        }
    }

    @Override
    public void requirePositive(float n) {
        if (n <= 0) {
            log.error("Assertion Failed!");
            Thread.dumpStack();
            throw new IllegalArgumentException();
        }
    }

    @Override
    public void requirePositive(double n) {
        if (n <= 0) {
            log.error("Assertion Failed!");
            Thread.dumpStack();
            throw new IllegalArgumentException();
        }
    }

    @Override
    public void requirePositive(byte n, String message) {
        if (n <= 0) {
            log.error("Assertion Failed!");
            Thread.dumpStack();
            throw new IllegalArgumentException(message);
        }
    }

    @Override
    public void requirePositive(short n, String message) {
        if (n <= 0) {
            log.error("Assertion Failed!");
            Thread.dumpStack();
            throw new IllegalArgumentException(message);
        }
    }

    @Override
    public void requirePositive(int n, String message) {
        if (n <= 0) {
            log.error("Assertion Failed!");
            Thread.dumpStack();
            throw new IllegalArgumentException(message);
        }
    }

    @Override
    public void requirePositive(long n, String message) {
        if (n <= 0) {
            log.error("Assertion Failed!");
            Thread.dumpStack();
            throw new IllegalArgumentException(message);
        }
    }

    @Override
    public void requirePositive(float n, String message) {
        if (n <= 0) {
            log.error("Assertion Failed!");
            Thread.dumpStack();
            throw new IllegalArgumentException(message);
        }
    }

    @Override
    public void requirePositive(double n, String message) {
        if (n <= 0) {
            log.error("Assertion Failed!");
            Thread.dumpStack();
            throw new IllegalArgumentException(message);
        }
    }

    @Override
    public void requirePositive(byte n, Supplier<String> message) {
        if (n <= 0) {
            log.error("Assertion Failed!");
            Thread.dumpStack();
            throw new IllegalArgumentException(message.get());
        }
    }

    @Override
    public void requirePositive(short n, Supplier<String> message) {
        if (n <= 0) {
            log.error("Assertion Failed!");
            Thread.dumpStack();
            throw new IllegalArgumentException(message.get());
        }
    }

    @Override
    public void requirePositive(int n, Supplier<String> message) {
        if (n <= 0) {
            log.error("Assertion Failed!");
            Thread.dumpStack();
            throw new IllegalArgumentException(message.get());
        }
    }

    @Override
    public void requirePositive(long n, Supplier<String> message) {
        if (n <= 0) {
            log.error("Assertion Failed!");
            Thread.dumpStack();
            throw new IllegalArgumentException(message.get());
        }
    }

    @Override
    public void requirePositive(float n, Supplier<String> message) {
        if (n <= 0) {
            log.error("Assertion Failed!");
            Thread.dumpStack();
            throw new IllegalArgumentException(message.get());
        }
    }

    @Override
    public void requirePositive(double n, Supplier<String> message) {
        if (n <= 0) {
            log.error("Assertion Failed!");
            Thread.dumpStack();
            throw new IllegalArgumentException(message.get());
        }
    }

    @Override
    public void requireLessThan(byte n, byte upperBoundExclusive) { if (n >= upperBoundExclusive) { throw new IllegalArgumentException(n + " < " + upperBoundExclusive + " failed!"); } }

    @Override
    public void requireLessThan(short n, short upperBoundExclusive) { if (n >= upperBoundExclusive) { throw new IllegalArgumentException(n + " < " + upperBoundExclusive + " failed!"); } }

    @Override
    public void requireLessThan(int n, int upperBoundExclusive) { if (n >= upperBoundExclusive) { throw new IllegalArgumentException(n + " < " + upperBoundExclusive + " failed!"); } }

    @Override
    public void requireLessThan(long n, long upperBoundExclusive) { if (n >= upperBoundExclusive) { throw new IllegalArgumentException(n + " < " + upperBoundExclusive + " failed!"); } }

    @Override
    public void requireLessThan(float n, float upperBoundExclusive) { if (n >= upperBoundExclusive) { throw new IllegalArgumentException(n + " < " + upperBoundExclusive + " failed!"); } }

    @Override
    public void requireLessThan(double n, double upperBoundExclusive) { if (n >= upperBoundExclusive) { throw new IllegalArgumentException(n + " < " + upperBoundExclusive + " failed!"); } }

    @Override
    public void requireLessThan(byte n, byte upperBoundExclusive, String message) { if (n >= upperBoundExclusive) { throw new IllegalArgumentException(message); } }

    @Override
    public void requireLessThan(short n, short upperBoundExclusive, String message) { if (n >= upperBoundExclusive) { throw new IllegalArgumentException(message); } }

    @Override
    public void requireLessThan(int n, int upperBoundExclusive, String message) { if (n >= upperBoundExclusive) { throw new IllegalArgumentException(message); } }

    @Override
    public void requireLessThan(long n, long upperBoundExclusive, String message) { if (n >= upperBoundExclusive) { throw new IllegalArgumentException(message); } }

    @Override
    public void requireLessThan(float n, float upperBoundExclusive, String message) { if (n >= upperBoundExclusive) { throw new IllegalArgumentException(message); } }

    @Override
    public void requireLessThan(double n, double upperBoundExclusive, String message) { if (n >= upperBoundExclusive) { throw new IllegalArgumentException(message); } }

    @Override
    public void requireLessThan(byte n, byte upperBoundExclusive, Supplier<String> message) { if (n >= upperBoundExclusive) { throw new IllegalArgumentException(message.get()); } }

    @Override
    public void requireLessThan(short n, short upperBoundExclusive, Supplier<String> message) { if (n >= upperBoundExclusive) { throw new IllegalArgumentException(message.get()); } }

    @Override
    public void requireLessThan(int n, int upperBoundExclusive, Supplier<String> message) { if (n >= upperBoundExclusive) { throw new IllegalArgumentException(message.get()); } }

    @Override
    public void requireLessThan(long n, long upperBoundExclusive, Supplier<String> message) { if (n >= upperBoundExclusive) { throw new IllegalArgumentException(message.get()); } }

    @Override
    public void requireLessThan(float n, float upperBoundExclusive, Supplier<String> message) { if (n >= upperBoundExclusive) { throw new IllegalArgumentException(message.get()); } }

    @Override
    public void requireLessThan(double n, double upperBoundExclusive, Supplier<String> message) { if (n >= upperBoundExclusive) { throw new IllegalArgumentException(message.get()); } }

    @Override
    public void requireGreaterThan(byte n, byte lowerBoundExclusive) { if (n <= lowerBoundExclusive) { throw new IllegalArgumentException(n + " > " + lowerBoundExclusive + " failed!"); } }

    @Override
    public void requireGreaterThan(short n, short lowerBoundExclusive) { if (n <= lowerBoundExclusive) { throw new IllegalArgumentException(n + " > " + lowerBoundExclusive + " failed!"); } }

    @Override
    public void requireGreaterThan(int n, int lowerBoundExclusive) { if (n <= lowerBoundExclusive) { throw new IllegalArgumentException(n + " > " + lowerBoundExclusive + " failed!"); } }

    @Override
    public void requireGreaterThan(long n, long lowerBoundExclusive) { if (n <= lowerBoundExclusive) { throw new IllegalArgumentException(n + " > " + lowerBoundExclusive + " failed!"); } }

    @Override
    public void requireGreaterThan(float n, float lowerBoundExclusive) { if (n <= lowerBoundExclusive) { throw new IllegalArgumentException(n + " > " + lowerBoundExclusive + " failed!"); } }

    @Override
    public void requireGreaterThan(double n, double lowerBoundExclusive) { if (n <= lowerBoundExclusive) { throw new IllegalArgumentException(n + " > " + lowerBoundExclusive + " failed!"); } }

    @Override
    public void requireGreaterThan(byte n, byte lowerBoundExclusive, String message) { if (n <= lowerBoundExclusive) { throw new IllegalArgumentException(message); } }

    @Override
    public void requireGreaterThan(short n, short lowerBoundExclusive, String message) { if (n <= lowerBoundExclusive) { throw new IllegalArgumentException(message); } }

    @Override
    public void requireGreaterThan(int n, int lowerBoundExclusive, String message) { if (n <= lowerBoundExclusive) { throw new IllegalArgumentException(message); } }

    @Override
    public void requireGreaterThan(long n, long lowerBoundExclusive, String message) { if (n <= lowerBoundExclusive) { throw new IllegalArgumentException(message); } }

    @Override
    public void requireGreaterThan(float n, float lowerBoundExclusive, String message) { if (n <= lowerBoundExclusive) { throw new IllegalArgumentException(message); } }

    @Override
    public void requireGreaterThan(double n, double lowerBoundExclusive, String message) { if (n <= lowerBoundExclusive) { throw new IllegalArgumentException(message); } }

    @Override
    public void requireGreaterThan(byte n, byte lowerBoundExclusive, Supplier<String> message) { if (n <= lowerBoundExclusive) { throw new IllegalArgumentException(message.get()); } }

    @Override
    public void requireGreaterThan(short n, short lowerBoundExclusive, Supplier<String> message) { if (n <= lowerBoundExclusive) { throw new IllegalArgumentException(message.get()); } }

    @Override
    public void requireGreaterThan(int n, int lowerBoundExclusive, Supplier<String> message) { if (n <= lowerBoundExclusive) { throw new IllegalArgumentException(message.get()); } }

    @Override
    public void requireGreaterThan(long n, long lowerBoundExclusive, Supplier<String> message) { if (n <= lowerBoundExclusive) { throw new IllegalArgumentException(message.get()); } }

    @Override
    public void requireGreaterThan(float n, float lowerBoundExclusive, Supplier<String> message) { if (n <= lowerBoundExclusive) { throw new IllegalArgumentException(message.get()); } }

    @Override
    public void requireGreaterThan(double n, double lowerBoundExclusive, Supplier<String> message) { if (n <= lowerBoundExclusive) { throw new IllegalArgumentException(message.get()); } }

    @Override
    public void requireLessThanOrEqualTo(byte n, byte upperBoundInclusive) { if (n > upperBoundInclusive) { throw new IllegalArgumentException(n + " <= " + upperBoundInclusive + " failed!"); } }

    @Override
    public void requireLessThanOrEqualTo(short n, short upperBoundInclusive) { if (n > upperBoundInclusive) { throw new IllegalArgumentException(n + " <= " + upperBoundInclusive + " failed!"); } }

    @Override
    public void requireLessThanOrEqualTo(int n, int upperBoundInclusive) { if (n > upperBoundInclusive) { throw new IllegalArgumentException(n + " <= " + upperBoundInclusive + " failed!"); } }

    @Override
    public void requireLessThanOrEqualTo(long n, long upperBoundInclusive) { if (n > upperBoundInclusive) { throw new IllegalArgumentException(n + " <= " + upperBoundInclusive + " failed!"); } }

    @Override
    public void requireLessThanOrEqualTo(float n, float upperBoundInclusive) { if (n > upperBoundInclusive) { throw new IllegalArgumentException(n + " <= " + upperBoundInclusive + " failed!"); } }

    @Override
    public void requireLessThanOrEqualTo(double n, double upperBoundInclusive) { if (n > upperBoundInclusive) { throw new IllegalArgumentException(n + " <= " + upperBoundInclusive + " failed!"); } }

    @Override
    public void requireLessThanOrEqualTo(byte n, byte upperBoundInclusive, String message) { if (n > upperBoundInclusive) { throw new IllegalArgumentException(message); } }

    @Override
    public void requireLessThanOrEqualTo(short n, short upperBoundInclusive, String message) { if (n > upperBoundInclusive) { throw new IllegalArgumentException(message); } }

    @Override
    public void requireLessThanOrEqualTo(int n, int upperBoundInclusive, String message) { if (n > upperBoundInclusive) { throw new IllegalArgumentException(message); } }

    @Override
    public void requireLessThanOrEqualTo(long n, long upperBoundInclusive, String message) { if (n > upperBoundInclusive) { throw new IllegalArgumentException(message); } }

    @Override
    public void requireLessThanOrEqualTo(float n, float upperBoundInclusive, String message) { if (n > upperBoundInclusive) { throw new IllegalArgumentException(message); } }

    @Override
    public void requireLessThanOrEqualTo(double n, double upperBoundInclusive, String message) { if (n > upperBoundInclusive) { throw new IllegalArgumentException(message); } }

    @Override
    public void requireLessThanOrEqualTo(byte n, byte upperBoundInclusive, Supplier<String> message) { if (n > upperBoundInclusive) { throw new IllegalArgumentException(message.get()); } }

    @Override
    public void requireLessThanOrEqualTo(short n, short upperBoundInclusive, Supplier<String> message) { if (n > upperBoundInclusive) { throw new IllegalArgumentException(message.get()); } }

    @Override
    public void requireLessThanOrEqualTo(int n, int upperBoundInclusive, Supplier<String> message) { if (n > upperBoundInclusive) { throw new IllegalArgumentException(message.get()); } }

    @Override
    public void requireLessThanOrEqualTo(long n, long upperBoundInclusive, Supplier<String> message) { if (n > upperBoundInclusive) { throw new IllegalArgumentException(message.get()); } }

    @Override
    public void requireLessThanOrEqualTo(float n, float upperBoundInclusive, Supplier<String> message) { if (n > upperBoundInclusive) { throw new IllegalArgumentException(message.get()); } }

    @Override
    public void requireLessThanOrEqualTo(double n, double upperBoundInclusive, Supplier<String> message) { if (n > upperBoundInclusive) { throw new IllegalArgumentException(message.get()); } }

    @Override
    public void requireGreaterThanOrEqualTo(byte n, byte lowerBoundInclusive) { if (n < lowerBoundInclusive) { throw new IllegalArgumentException(n + " >= " + lowerBoundInclusive + " failed!"); } }

    @Override
    public void requireGreaterThanOrEqualTo(short n, short lowerBoundInclusive) { if (n < lowerBoundInclusive) { throw new IllegalArgumentException(n + " >= " + lowerBoundInclusive + " failed!"); } }

    @Override
    public void requireGreaterThanOrEqualTo(int n, int lowerBoundInclusive) { if (n < lowerBoundInclusive) { throw new IllegalArgumentException(n + " >= " + lowerBoundInclusive + " failed!"); } }

    @Override
    public void requireGreaterThanOrEqualTo(long n, long lowerBoundInclusive) { if (n < lowerBoundInclusive) { throw new IllegalArgumentException(n + " >= " + lowerBoundInclusive + " failed!"); } }

    @Override
    public void requireGreaterThanOrEqualTo(float n, float lowerBoundInclusive) { if (n < lowerBoundInclusive) { throw new IllegalArgumentException(n + " >= " + lowerBoundInclusive + " failed!"); } }

    @Override
    public void requireGreaterThanOrEqualTo(double n, double lowerBoundInclusive) { if (n < lowerBoundInclusive) { throw new IllegalArgumentException(n + " >= " + lowerBoundInclusive + " failed!"); } }

    @Override
    public void requireGreaterThanOrEqualTo(byte n, byte lowerBoundInclusive, String message) { if (n < lowerBoundInclusive) { throw new IllegalArgumentException(message); } }

    @Override
    public void requireGreaterThanOrEqualTo(short n, short lowerBoundInclusive, String message) { if (n < lowerBoundInclusive) { throw new IllegalArgumentException(message); } }

    @Override
    public void requireGreaterThanOrEqualTo(int n, int lowerBoundInclusive, String message) { if (n < lowerBoundInclusive) { throw new IllegalArgumentException(message); } }

    @Override
    public void requireGreaterThanOrEqualTo(long n, long lowerBoundInclusive, String message) { if (n < lowerBoundInclusive) { throw new IllegalArgumentException(message); } }

    @Override
    public void requireGreaterThanOrEqualTo(float n, float lowerBoundInclusive, String message) { if (n < lowerBoundInclusive) { throw new IllegalArgumentException(message); } }

    @Override
    public void requireGreaterThanOrEqualTo(double n, double lowerBoundInclusive, String message) { if (n < lowerBoundInclusive) { throw new IllegalArgumentException(message); } }

    @Override
    public void requireGreaterThanOrEqualTo(byte n, byte lowerBoundInclusive, Supplier<String> message) { if (n < lowerBoundInclusive) { throw new IllegalArgumentException(message.get()); } }

    @Override
    public void requireGreaterThanOrEqualTo(short n, short lowerBoundInclusive, Supplier<String> message) { if (n < lowerBoundInclusive) { throw new IllegalArgumentException(message.get()); } }

    @Override
    public void requireGreaterThanOrEqualTo(int n, int lowerBoundInclusive, Supplier<String> message) { if (n < lowerBoundInclusive) { throw new IllegalArgumentException(message.get()); } }

    @Override
    public void requireGreaterThanOrEqualTo(long n, long lowerBoundInclusive, Supplier<String> message) { if (n < lowerBoundInclusive) { throw new IllegalArgumentException(message.get()); } }

    @Override
    public void requireGreaterThanOrEqualTo(float n, float lowerBoundInclusive, Supplier<String> message) { if (n < lowerBoundInclusive) { throw new IllegalArgumentException(message.get()); } }

    @Override
    public void requireGreaterThanOrEqualTo(double n, double lowerBoundInclusive, Supplier<String> message) { if (n < lowerBoundInclusive) { throw new IllegalArgumentException(message.get()); } }
}
