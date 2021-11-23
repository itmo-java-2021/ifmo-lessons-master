package com.ifmo.lesson7;

public class HashList {

    private Item[] items;
    private int count;

    public HashList() {
        items = new Item[10];
        count = 0;
    }

    public void add(Object val) {
        if (items.length == count){
            Item[] items =  this.items;
            this.items = new Item[items.length + 10];

            for (int i = 0; i < items.length; i++) {
                Item item = items[i];
                while (item != null){
                    Item current = item;
                    item = current.next;
                    current.next = null;
                    add(current);
                }
            }
        }

        Item item = new Item(val);
        int len = items.length;
        int i = val.hashCode() % (len - 1);
        if (items[i] == null){
            items[i] = item;
            count++;
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

    public Object get(Object val) {
        int len = items.length;
        int i = Math.abs(val.hashCode()) % (len - 1);
        Item item = items[i];
        while (item != null){
            if (item.value.equals(val)){
                return item.value;
            } else {
                item = item.next;
            }
        }
        return null;
    }

    public Object remove(Object val) {
        int len = items.length;
        int i = val.hashCode() % (len - 1);
        Item item = items[i];
        Item last = null;
        while (item != null){
            if (item.value.equals(val)){
                if (item.next != null){
                    if (last == null){
                        items[i] = item.next;
                    } else{
                        last.next = item.next;
                    }
                } else {
                    if (last == null){
                        items[i] = null;
                    } else{
                        last.next = null;
                    }
                    count--;
                }
                return item.value;
            } else {
                last = item;
                item = item.next;
            }
        }

        return null;
    }
}
