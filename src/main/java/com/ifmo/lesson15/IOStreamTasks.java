package com.ifmo.lesson15;

import com.ifmo.lesson3.EncryptArray;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/*
    Реализуйте все методы с использованием потоков ввода-вывода.
 */
public class IOStreamTasks {
    public static void main(String[] args) {

        try {
            List<File> files = split("c:\\Users\\U\\Documents\\java\\test\\test.txt", "c:\\Users\\U\\Documents\\java\\test", 20);

            try(InputStream inputStream = new FileInputStream("c:\\Users\\U\\Documents\\java\\test\\test.txt");
                OutputStream outputStream = new FileOutputStream("c:\\Users\\U\\Documents\\java\\test\\test2.txt")){
                copy(inputStream, outputStream);
            }

            assembly(files, new File("c:\\Users\\U\\Documents\\java\\test\\test3.txt"));

            try(InputStream inputStream = new FileInputStream("c:\\Users\\U\\Documents\\java\\test\\test.txt");
                OutputStream outputStream = new FileOutputStream("c:\\Users\\U\\Documents\\java\\test\\test4.txt")){
                encrypt(inputStream, outputStream, "lol");
            }
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
                files.add(file1);
                try (OutputStream write = new FileOutputStream(file1.getPath())){
                    write.write(buffer);
                }
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
        byte[] key = passphrase.getBytes();

        byte[] buffer = new byte[1024];
        int len;
        while ((len = src.read(buffer)) > 0)
        {
            EncryptArray.encrypt(buffer, key);
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

    }
}
