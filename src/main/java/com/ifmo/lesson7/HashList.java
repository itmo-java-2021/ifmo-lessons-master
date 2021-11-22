package com.ifmo.lesson7;

public class HashList {

    private Item[] items;
    private int count;

    public HashList() {
        items = new Item[10];
        count = 10;
    }

    public void add(Object val) {
        if (items.length == count){
            Item[] items = new Item[count + 10];
            for (int i = 0; i < items.length; i++) {
                items[i] = this.items[i];
            }
            this.items = items;
        }
        Item item = new Item(val);
        int len = items.length;
        int i = val.hashCode() % (len - 1);
        if (items[i] == null){
            items[i] = item;
        } else {
            Item current = items[i];
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

    public Object get(int i) {
        return 0;
    }

    public Object remove(int i) {
        return null;
    }
}
