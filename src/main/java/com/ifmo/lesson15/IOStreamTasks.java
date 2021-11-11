package com.ifmo.lesson15;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/*
    Реализуйте все методы с использованием потоков ввода-вывода.
 */
public class IOStreamTasks {
    public static void main(String[] args) {
        String path = "c:\\Users\\matveychukiv\\source\\Java\\lesson\\test";

        try {
            List<File> files = split(path + "\\test.txt", path, 20);

            try(InputStream inputStream = new FileInputStream(path + "\\test.txt");
                OutputStream outputStream = new FileOutputStream(path + "\\test2.txt")){
                copy(inputStream, outputStream);
            }

            assembly(files, new File(path + "\\test3.txt"));

            try(InputStream inputStream = new FileInputStream(path + "\\test.txt");
                OutputStream outputStream = new FileOutputStream(path + "\\test4.txt")){
                encrypt(inputStream, outputStream, "lol");
            }

            encrypt(new File(path + "\\test.txt"), new File(path + "\\test5.txt"), new File(path + "\\key.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Полностью копирует один поток в другой.
     *
     * @param src Входящий поток.
     * @param dst Выходящий поток.
     * @throws IOException Будет выброшен в случае ошибки.
     */
    public static void copy(InputStream src, OutputStream dst) throws IOException {
        // TODO implement
        byte[] buffer = new byte[1024];
        int len;
        while ((len = src.read(buffer)) > 0)
        {
            dst.write(buffer, 0, len);
        }
    }

    /**
     * Последовательно разбивает файл на несколько более мелких, равных
     * указанному размеру. Последний файл может быть меньше заданного
     * размера.
     *
     * @param file Файл, который будет разбит на несколько.
     * @param dstDir Директория, в которой будут созданы файлы меньшего размера.
     * @param size Размер каждого файла-части, который будет создан.
     * @return Список файлов-частей в том порядке, в котором они должны считываться.
     * @throws IOException Будет выброшен в случае ошибки.
     */
    public static List<File> split(String file, String dstDir, int size) throws IOException {
        List<File> files = new ArrayList<>();
        try(InputStream reader = new FileInputStream(file);){
            for (int i = 0; ; i++) {
                byte[] buffer = new byte[size];
                int len  = reader.read(buffer);
                if (len == -1) break;
                File file1 = new File(dstDir + "\\" + i +".txt");
                try (OutputStream write = new FileOutputStream(file1.getPath())){
                    write.write(buffer, 0, len);
                }
                files.add(file1);
            }

        }
        return files;
    }

    /**
     * Собирает из частей один файл.
     *
     * @param files Список файлов в порядке чтения.
     * @param dst Файл, в который будут записаны все части.
     * @throws IOException Будет выброшен в случае ошибки.
     */
    public static void assembly(List<File> files, File dst) throws IOException {
        try (OutputStream outputStream = new FileOutputStream(dst)){
            for (File file: files){
                try (InputStream inputStream = new FileInputStream(file.getPath())){
                    copy(inputStream, outputStream);
                }
            }
        }

    }

    /**
     * Шифрует/дешифрует поток с помощью XOR. В качестве ключа используется пароль.
     *
     * @param src Входящий поток, данные которого будут зашифрованы/расшифрованы.
     * @param dst Выходящий поток, куда будут записаны зашифрованные/расшифрованные данные.
     * @param passphrase Пароль.
     * @throws IOException Будет выброшен в случае ошибки.
     */
    public static void encrypt(InputStream src, OutputStream dst, String passphrase) throws IOException {

        encrypt(src, dst, passphrase.getBytes());
    }

    public static void encrypt(InputStream src, OutputStream dst, byte[] key) throws IOException {
        int q = 1024 % key.length;
        int lenBuffer = q > 0 ? q * key.length : key.length;
        byte[] buffer = new byte[lenBuffer];
        int len;
        while ((len = src.read(buffer)) > 0)
        {
            com.ifmo.lesson3.EncryptArray.encrypt(buffer, key);
            dst.write(buffer, 0, len);
        }
    }

    /**
     * Шифрует/дешифрует файл с помощью файла-ключа.
     *
     * @param src Файл, который должен быть зашифрован/расшифрован.
     * @param dst Файл, куда будут записаны зашифрованные/расшифрованные данные.
     * @param key Файл-ключ.
     * @throws IOException Будет выброшен в случае ошибки.
     */
    public static void encrypt(File src, File dst, File key) throws IOException {
        try (InputStream inputStream = new FileInputStream(src.getPath());
             InputStream inputKey = new FileInputStream(key.getPath());
             ByteArrayOutputStream readKey = new ByteArrayOutputStream();
             OutputStream outputStream = new FileOutputStream(dst.getPath())){
            byte[] buffer = new byte[1024];
            int len;
            while ((len = inputKey.read(buffer)) > 0)
            {
                readKey.write(buffer, 0, len);
            }

            encrypt(inputStream, outputStream, readKey.toByteArray());

        }
    }
}
