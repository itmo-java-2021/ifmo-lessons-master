package com.ifmo.lesson6;

import java.util.Iterator;

/**
 * Этот класс должен реализовывать следующие методы: add(), get(), remove() и iterator() из интерфейса List.
 * Если при выполнении add() в массиве нет свободных элементов, то создать новый - вдвое больше,
 * скопировать в него все значения из старого и + 1, который сейчас добавляется.
 * Удаление должно сдвинуть все элементы влево, если это требуется.
 * Например, если список с такими элементами:
 * |0|1|2|3|4|5|
 * Удаляем элемент по индексу 2:
 * |0|1|_|3|4|5|
 * Перемещаем все элементы влево:
 * |0|1|3|4|5|_|
 * Теперь при итерации по ним после 1 будет идти сразу 3, как в связном списке.
 */
public class ArrayList implements List {
    private static final int DEFAULT_SIZE = 10;
    private int size;

    private Object[] values;

    /**
     * Создаёт новый {@link #ArrayList} с размером внутреннего массива по умолчанию.
     */
    public ArrayList() {
        this(DEFAULT_SIZE);
    }

    /**
     * Создаёт новый {@link #ArrayList} с размером внутреннего массива,
     * равного {@code initialSize}.
     *
     * @param initialSize Начальный размер внутреннего массива.
     */
    public ArrayList(int initialSize) {
        values = new Object[initialSize];
        size = 0;
    }

    /** {@inheritDoc} */
    @Override
    public void add(Object val) {
        // TODO implement.
        if (values.length == size){
            Object[] vs = new Object[size + 10];
            for (int i = 0; i < values.length; i++) {
                vs[i] = values[i];
            }
            values = vs;
        }
        values[size] = val;
        size++;
    }

    /** {@inheritDoc} */
    @Override
    public Object get(int i) {
        // TODO implement.
        if (i >= size){
            return  null;
        } else {
            return values[i];
        }
    }

    /** {@inheritDoc} */
    @Override
    public Object remove(int i) {
        // TODO implement.
        if (i >= size){
            return  null;
        } else {
            Object obj = values[i];
            size--;
            for (int j = i; j < size; j++) {
                values[j] = values[j + 1];
            }
            return obj;
        }
    }

    /** {@inheritDoc} */
    @Override
    public Iterator iterator() {
        // TODO implement.

        return new Iterator() {
            private int currentIndex = 0;

            @Override
            public boolean hasNext() {
                if (currentIndex >= size){
                    return false;
                } else {
                    return true;
                }
            }

            @Override
            public Object next() {
                currentIndex++;
                return values[currentIndex - 1];
            }
        };
    }
}
