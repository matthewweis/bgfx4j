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

}
