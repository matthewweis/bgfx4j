package com.bariumhoof.assertions;

import org.jetbrains.annotations.Contract;

import java.util.function.Supplier;

// todo make seperate lib
interface AssertionFunctions {

    @Contract("null -> fail")
    void requireNonNull(Object object);
    @Contract("null, _ -> fail")
    void requireNonNull(Object object, String message);
    @Contract("null, _ -> fail")
    void requireNonNull(Object object, Supplier<String> message);
    void requireNonNegative(byte n);
    void requireNonNegative(short n);
    void requireNonNegative(int n);
    void requireNonNegative(long n);
    void requireNonNegative(float n);
    void requireNonNegative(double n);
    void requireNonNegative(byte n, String message);
    void requireNonNegative(short n, String message);
    void requireNonNegative(int n, String message);
    void requireNonNegative(long n, String message);
    void requireNonNegative(float n, String message);
    void requireNonNegative(double n, String message);
    void requireNonNegative(byte n, Supplier<String> message);
    void requireNonNegative(short n, Supplier<String> message);
    void requireNonNegative(int n, Supplier<String> message);
    void requireNonNegative(long n, Supplier<String> message);
    void requireNonNegative(float n, Supplier<String> message);
    void requireNonNegative(double n, Supplier<String> message);
    void requirePositive(byte n);
    void requirePositive(short n);
    void requirePositive(int n);
    void requirePositive(long n);
    void requirePositive(float n);
    void requirePositive(double n);
    void requirePositive(byte n, String message);
    void requirePositive(short n, String message);
    void requirePositive(int n, String message);
    void requirePositive(long n, String message);
    void requirePositive(float n, String message);
    void requirePositive(double n, String message);
    void requirePositive(byte n, Supplier<String> message);
    void requirePositive(short n, Supplier<String> message);
    void requirePositive(int n, Supplier<String> message);
    void requirePositive(long n, Supplier<String> message);
    void requirePositive(float n, Supplier<String> message);
    void requirePositive(double n, Supplier<String> message);

}
