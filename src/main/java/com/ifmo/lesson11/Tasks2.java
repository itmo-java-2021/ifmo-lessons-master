package com.ifmo.lesson11;

import com.ifmo.lesson11.inner.Message;
import com.ifmo.lesson11.inner.User;

import java.util.Collections;
import java.util.List;
import java.util.NavigableSet;

import static com.ifmo.lesson11.inner.UserGenerator.generate;

/*
    Реализуйте все методы.
 */
public class Tasks2 {
    public static void main(String[] args) {
        System.out.println(generate(10));
    }

    /**
     * Сортирует сообщения в списке в порядке возрастания приоритета.
     *
     * @param messages Сообщения.
     */
    private static void sortByPriority(List<Message> messages) {
        // TODO implement.
    }

    /**
     * Возвращает Set, отсортированный по компании и имени пользователя.
     *
     * @param users Пользователи.
     * @return Сортированный Set.
     */
    private static NavigableSet<User> sortedByCompanyAndName(List<User> users) {
        // TODO implement.

        return Collections.emptyNavigableSet();
    }

    /**
     * Возвращает Set, отсортированный по зарплате и имени пользователя.
     *
     * @param users Пользователи.
     * @return Сортированный Set.
     */
    private static NavigableSet<User> sortedBySalaryAndName(List<User> users) {
        // TODO implement.

        return Collections.emptyNavigableSet();
    }

    /**
     * Возвращает Set, отсортированный по зарплате, компании и имени пользователя.
     *
     * @param users Пользователи.
     * @return Сортированный Set.
     */
    private static NavigableSet<User> sortedBySalaryAgeCompanyAndName(List<User> users) {
        // TODO implement.

        return Collections.emptyNavigableSet();
    }

}
