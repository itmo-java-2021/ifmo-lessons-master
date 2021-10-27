package com.ifmo.lesson10;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class CollectionUtils {
    public static <T> Iterable<T> view(Iterable<T>...iterables) {
        ListView vs = new ListView<Iterable<T>>();
        for (Iterable<T> item: iterables) {
            vs.add(item);
        }
        return vs;
    }

    public static void main(String[] args) {
        List<String> list1 = new ArrayList<>();
        List<String> list2 = new LinkedList<>();
        List<String> list3 = new LinkedList<>();

        list1.add("1");
        list1.add("2");
        list2.add("3");
        list2.add("4");
        list3.add("5");
        list3.add("6");

        Iterable<String> view = view(list1, list2, list3);

        list3.add("7");

        Iterator<String> iterator = view.iterator();

        while (iterator.hasNext()) {
            System.out.println(iterator.next()); // 1, 2, 3, 4, 5, 6, 7
        }

        for (String s : view) {
            System.out.println(s); // 1, 2, 3, 4, 5, 6, 7
        }

        System.out.println("====");

        list1.add(0, "0");

        for (String s : view) {
            System.out.println(s); // 0, 1, 2, 3, 4, 5, 6, 7
        }
    }
}
