package com.ifmo.lesson2;

public class SymmetricClocks {
    /*
    Электронные часы показывают время в формате от 00:00 до 23:59. Подсчитать сколько
    раз за сутки случается так, что слева от двоеточия показывается симметричная комбинация
    для той, что справа от двоеточия (например, 02:20, 11:11 или 15:51)
     */
    public static void main(String[] args) {
        System.out.println(symmetricTimes());
    }

    public static int symmetricTimes() {
        int count = 0;
        for (int i = 0; i < 24; i++) {
            for (int j = 0; j < 60; j++) {
                String h = String.format("%2s", Integer.toString(i)).replace(' ', '0');
                String m = String.format("%2s", Integer.toString(j)).replace(' ', '0');
                String mm = String.format("%s%s", m.charAt(1), m.charAt(0));
                if (h.equals(mm)){
                    count++;
                }
            }
        }

        return count;
    }
}
