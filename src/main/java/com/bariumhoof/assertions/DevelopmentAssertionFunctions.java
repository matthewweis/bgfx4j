package com.bariumhoof.assertions;

import org.jetbrains.annotations.Contract;

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
