package com.ifmo.lesson19;

import java.util.Arrays;
import java.util.List;

public class ReflectionMain {
    public static void main(String[] args) {
        ToStringObject obj1 = new ToStringObject("a", 1, List.of("b", "c"), null);
        ToStringObject obj2 = new ToStringObject("d", 2, List.of("e", "f"), obj1);

        System.out.println(ReflectionUtils.toString(obj1));
        System.out.println(ReflectionUtils.toString(obj2));
        System.out.println(Arrays.asList(1, 2 ,3));
    }
}
