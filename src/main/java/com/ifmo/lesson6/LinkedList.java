package com.ifmo.lesson6;

import java.util.Iterator;

/**
 * Односвязный список, где каждый предыдущий
 * элемент харнит ссылку на следующий. Список
 * оканчивается ссылкой со значением {@code null}.
 */
public class LinkedList implements List, Stack, Queue {
    /** Ссылка на первый элемент списка. */
    private Item head;

    /** {@inheritDoc} */
    @Override
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

    /** {@inheritDoc} */
    @Override
    public Object take() {
        return remove(0);
    }

    /** {@inheritDoc} */
    @Override
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

    /** {@inheritDoc} */
    @Override
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

    /** {@inheritDoc} */
    @Override
    public Iterator iterator() {

        return new Iterator() {
            Item current = head;

            @Override
            public boolean hasNext() {
                if (current == null){
                    return false;
                } else {
                    return true;
                }
            }

            @Override
            public Object next() {
                if (!this.hasNext()) throw new IndexOutOfBoundsException("End of list.");
                Item current = this.current;
                this.current = current.next;
                return current.value;
            }
        };
    }

    /** {@inheritDoc} */
    @Override
    public void push(Object value) {
        this.add(value);
    }

    /** {@inheritDoc} */
    @Override
    public Object pop() {
        if(head == null){
            return null;
        } else if (head.next == null){
            Item current = head;
            head = null;
            return current.value;
        } else {
            Item current = head.next;
            Item last = head;
            while (current.next != null) {
                last = current;
                current = current.next;
            }
            last.next = null;
            return current.value;
        }
    }
}
