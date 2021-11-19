package com.ifmo.lesson18;

import com.ifmo.lesson3.EncryptArray;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Реализовать шифрующий (XOR) поток вывода.
 */
public class CryptoOutputStream extends FilterOutputStream {
    /**
     * Создаёт новый {@link CryptoOutputStream}.
     * При записи применяет операцию XOR последовательно:
     * каждый байт из ключа ^ каждый байт из выходящего потока.
     * Когда встречается конец ключа, то на следующий байт потока
     * берётся первый байт из ключа (по принципу кольцевого буфера).
     *
     * @param out Поток вывода.
     * @param key Ключ шифрования.
     */

    private byte[] key;
    private int nextKey;

    public CryptoOutputStream(OutputStream out, byte[] key) {
        super(out);
        this.key = key;
        this.nextKey = 0;
    }

    @Override
    public void write(int b) throws IOException {
        b = (byte)b ^ key[nextKey];
        super.write(b);
        if (key.length - 1 == nextKey){
            nextKey = 0;
        } else {
            nextKey++;
        }
    }
}
