package com.ifmo.lesson10;

public interface Transformer<T, R> {
    R transform(T t);
}
