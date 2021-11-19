package com.ifmo.lesson18;

import com.ifmo.lesson3.EncryptArray;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Реализовать шифрующий (XOR) поток вывода.
 */
public class CryptoInputStream extends FilterInputStream {
    /**
     * Создаёт новый {@link CryptoInputStream}.
     * При чтении применяет операцию XOR последовательно:
     * каждый байт из ключа ^ каждый байт из входящего потока.
     * Когда встречается конец ключа, то на следующий байт потока
     * берётся первый байт из ключа (по принципу кольцевого буфера).
     *
     * @param in Поток ввода.
     * @param key Ключ шифрования.
     */

    private byte[] key;
    private int nextKey;

    public CryptoInputStream(InputStream in, byte[] key) {
        super(in);
        this.key = key;
        this.nextKey = 0;
    }

    @Override
    public int read() throws IOException {
        int b = (byte)super.read() ^ key[nextKey];
        if (key.length - 1 == nextKey){
            nextKey = 0;
        } else {
            nextKey++;
        }
        return b;
    }
}
