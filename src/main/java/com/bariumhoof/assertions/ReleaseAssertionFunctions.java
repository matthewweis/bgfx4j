package com.bariumhoof.assertions;

import org.jetbrains.annotations.Contract;

import java.util.function.Supplier;

/**
 * During release assertions become NO-OPS to be optimized away.
 * Note this breaks contracts, but this is OK.
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
}
