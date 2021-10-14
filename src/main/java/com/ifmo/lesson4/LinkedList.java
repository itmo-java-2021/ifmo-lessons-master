package com.ifmo.lesson4;

/**
 * Односвязный список, где каждый предыдущий
 * элемент харнит ссылку на следующий. Список
 * оканчивается ссылкой со значением {@code null}.
 */
public class LinkedList {
    /** Ссылка на первый элемент списка. */
    private Item head;

    /**
     * Добавляет значение в конец списка.
     *
     * @param val Значение, которое будет добавлено.
     */
    public void add(Object val) {
        Item item = new Item(val);
        if (head == null){
            head = item;
        } else {
            Item current = head;
            while (true){
                if (current.next == null){
                    current.next = item;
                    break;
                } else {
                    current = current.next;
                }
            }
        }
    }

    /**
     * Извлекает значение из списка по индексу.
     *
     * @param i Индекс значения в списке.
     * @return Значение, которое находится по индексу
     * или {@code null}, если не найдено.
     */
    public Object get(int i) {
        if(head == null){
            return null;
        }
        if (i == 0){
            return head.value;
        } else {
            Item current = head;
            for (int j = 1; j <= i; j++) {
                if(current.next == null){
                    return null;
                }
                current = current.next;
            }
            return current.value;
        }
    }

    /**
     * Удаляет значение по индексу и возвращает
     * удаленный элемент.
     *
     * @param i Индекс, по которому будет удален элемент.
     * @return Удаленное значение или {@code null}, если не найдено.
     */
    public Object remove(int i) {
        if(head == null){
            return null;
        }
        if (i == 0){
            Item current = head;
            head = current.next;
            return current.value;
        } else {
            Item current = head;
            Item last = null;
            for (int j = 1; j <= i; j++) {
                if(current.next == null){
                    return null;
                }
                last = current;
                current = current.next;
            }
            last.next = current.next;
            return current.value;
        }
    }
}
