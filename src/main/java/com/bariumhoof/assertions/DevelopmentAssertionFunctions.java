package com.bariumhoof.assertions;

import com.bariumhoof.Capabilities;
import com.bariumhoof.bgfx4j.Application;
import com.bariumhoof.bgfx4j.enums.BGFX_CAPS;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.Objects;
import java.util.function.Supplier;

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
            throw new IllegalStateException();
        }
    }

    @Override
    public void require(boolean b) {
        if (!b) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public void requireIfCapEnabled(boolean b, @NotNull BGFX_CAPS cap) {
        if (Capabilities.isSupported(cap)) {
            if (!b) {
                throw new UnsupportedOperationException();
            }
        }
    }

    @Override
    public void requireIfCapsEnabled(boolean b, @NotNull BGFX_CAPS ... caps) {
        if (Capabilities.allSupported(caps)) {
            if (!b) {
                throw new UnsupportedOperationException();
            }
        }
    }

    @Override
    public void requireIfCapDisabled(boolean b, @NotNull BGFX_CAPS cap) {
        if (!Capabilities.isSupported(cap)) {
            if (!b) {
                throw new UnsupportedOperationException();
            }
        }
    }

    @Override
    public void requireIfCapsDisabled(boolean b, @NotNull BGFX_CAPS ... caps) {
        if (Capabilities.noneSupported(caps)) {
            if (!b) {
                throw new UnsupportedOperationException();
            }
        }
    }

    @Override
    public void requireEmpty(Collection<?> c) {
        if (!c.isEmpty()) {
            throw new IllegalArgumentException("Collection must be empty.");
        }
    }

    @Override
    public void requireNonEmpty(Collection<?> c) {
        if (!c.isEmpty()) {
            throw new IllegalArgumentException("Collection must be empty.");
        }
    }

    @Override
    public void requireNonNegative(byte n) {
        if (n < 0) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public void requireNonNegative(short n) {
        if (n < 0) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public void requireNonNegative(int n) {
        if (n < 0) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public void requireNonNegative(long n) {
        if (n < 0) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public void requireNonNegative(float n) {
        if (n < 0) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public void requireNonNegative(double n) {
        if (n < 0) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public void requireNonNegative(byte n, String message) {
        if (n < 0) {
            throw new IllegalArgumentException(message);
        }
    }

    @Override
    public void requireNonNegative(short n, String message) {
        if (n < 0) {
            throw new IllegalArgumentException(message);
        }
    }

    @Override
    public void requireNonNegative(int n, String message) {
        if (n < 0) {
            throw new IllegalArgumentException(message);
        }
    }

    @Override
    public void requireNonNegative(long n, String message) {
        if (n < 0) {
            throw new IllegalArgumentException(message);
        }
    }

    @Override
    public void requireNonNegative(float n, String message) {
        if (n < 0) {
            throw new IllegalArgumentException(message);
        }
    }

    @Override
    public void requireNonNegative(double n, String message) {
        if (n < 0) {
            throw new IllegalArgumentException(message);
        }
    }

    @Override
    public void requireNonNegative(byte n, Supplier<String> message) {
        if (n < 0) {
            throw new IllegalArgumentException(message.get());
        }
    }

    @Override
    public void requireNonNegative(short n, Supplier<String> message) {
        if (n < 0) {
            throw new IllegalArgumentException(message.get());
        }
    }

    @Override
    public void requireNonNegative(int n, Supplier<String> message) {
        if (n < 0) {
            throw new IllegalArgumentException(message.get());
        }
    }

    @Override
    public void requireNonNegative(long n, Supplier<String> message) {
        if (n < 0) {
            throw new IllegalArgumentException(message.get());
        }
    }

    @Override
    public void requireNonNegative(float n, Supplier<String> message) {
        if (n < 0) {
            throw new IllegalArgumentException(message.get());
        }
    }

    @Override
    public void requireNonNegative(double n, Supplier<String> message) {
        if (n < 0) {
            throw new IllegalArgumentException(message.get());
        }
    }

    @Override
    public void requirePositive(byte n) {
        if (n <= 0) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public void requirePositive(short n) {
        if (n <= 0) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public void requirePositive(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public void requirePositive(long n) {
        if (n <= 0) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public void requirePositive(float n) {
        if (n <= 0) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public void requirePositive(double n) {
        if (n <= 0) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public void requirePositive(byte n, String message) {
        if (n <= 0) {
            throw new IllegalArgumentException(message);
        }
    }

    @Override
    public void requirePositive(short n, String message) {
        if (n <= 0) {
            throw new IllegalArgumentException(message);
        }
    }

    @Override
    public void requirePositive(int n, String message) {
        if (n <= 0) {
            throw new IllegalArgumentException(message);
        }
    }

    @Override
    public void requirePositive(long n, String message) {
        if (n <= 0) {
            throw new IllegalArgumentException(message);
        }
    }

    @Override
    public void requirePositive(float n, String message) {
        if (n <= 0) {
            throw new IllegalArgumentException(message);
        }
    }

    @Override
    public void requirePositive(double n, String message) {
        if (n <= 0) {
            throw new IllegalArgumentException(message);
        }
    }

    @Override
    public void requirePositive(byte n, Supplier<String> message) {
        if (n <= 0) {
            throw new IllegalArgumentException(message.get());
        }
    }

    @Override
    public void requirePositive(short n, Supplier<String> message) {
        if (n <= 0) {
            throw new IllegalArgumentException(message.get());
        }
    }

    @Override
    public void requirePositive(int n, Supplier<String> message) {
        if (n <= 0) {
            throw new IllegalArgumentException(message.get());
        }
    }

    @Override
    public void requirePositive(long n, Supplier<String> message) {
        if (n <= 0) {
            throw new IllegalArgumentException(message.get());
        }
    }

    @Override
    public void requirePositive(float n, Supplier<String> message) {
        if (n <= 0) {
            throw new IllegalArgumentException(message.get());
        }
    }

    @Override
    public void requirePositive(double n, Supplier<String> message) {
        if (n <= 0) {
            throw new IllegalArgumentException(message.get());
        }
    }
}
