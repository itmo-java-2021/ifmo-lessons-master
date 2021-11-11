package com.ifmo.lesson15;

import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

/**
 * Реализация потока ввода, которая генерирует случайные данные
 * указанной длины.
 */
public class RandomInputStream extends InputStream {
    private final Random random;
    private final long length;
    private long count;

    public RandomInputStream(Random random, long length) {
        this.random = random;
        this.length = length;
    }

    @Override
    public int read() throws IOException {
        if (count < length){
            count++;
            return random.nextInt(Byte.MAX_VALUE - Byte.MIN_VALUE) + Byte.MIN_VALUE;
        }
        return -1;
    }
}
