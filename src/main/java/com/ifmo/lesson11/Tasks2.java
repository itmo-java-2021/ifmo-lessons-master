package com.ifmo.lesson11;

import com.ifmo.lesson11.inner.Message;
import com.ifmo.lesson11.inner.MessageGenerator;
import com.ifmo.lesson11.inner.User;

import java.util.*;
import java.util.stream.Collectors;

import static com.ifmo.lesson11.inner.UserGenerator.generate;

/*
    Реализуйте все методы.
 */
public class Tasks2 {
    public static void main(String[] args) {

        System.out.println(generate(10));

        MessageGenerator generator = new MessageGenerator();
        sortByPriority(generator.generate(100));

        List<User> users = new ArrayList<>();
        users.add(new User("David", 22, 15000, "Gazprom"));
        users.add(new User("David", 27, 15000, "Gazprom"));
        users.add(new User("David2", 22, 15000, "ITMO"));

        sortedByCompanyAndName(users);
    }

    /**
     * Сортирует сообщения в списке в порядке возрастания приоритета.
     *
     * @param messages Сообщения.
     */
    private static void sortByPriority(List<Message> messages) {
        List<Message> vs = messages.stream().sorted((x, y) -> x.getPriority().compareTo(y.getPriority())).collect(Collectors.toList());
    }

    /**
     * Возвращает Set, отсортированный по компании и имени пользователя.
     *
     * @param users Пользователи.
     * @return Сортированный Set.
     */
    private static NavigableSet<User> sortedByCompanyAndName(List<User> users) {

        NavigableSet<User> set = new TreeSet<>(Comparator.comparing(User::getCompany).thenComparing(User::getName).thenComparing(User::getAge).thenComparing(User::getSalary));
        for (User user: users){
            set.add(user);
        }

        //NavigableSet<User> vs = set.stream().sorted(Comparator.comparing(User::getCompany).thenComparing(User::getName));

        return set;
    }

    /**
     * Возвращает Set, отсортированный по зарплате и имени пользователя.
     *
     * @param users Пользователи.
     * @return Сортированный Set.
     */
    private static NavigableSet<User> sortedBySalaryAndName(List<User> users) {
        NavigableSet<User> set = new TreeSet<>(Comparator.comparing(User::getSalary).thenComparing(User::getName).thenComparing(User::getCompany).thenComparing(User::getAge));
        for (User user: users){
            set.add(user);
        }

        return set;
    }

    /**
     * Возвращает Set, отсортированный по зарплате, компании и имени пользователя.
     *
     * @param users Пользователи.
     * @return Сортированный Set.
     */
    private static NavigableSet<User> sortedBySalaryAgeCompanyAndName(List<User> users) {
        NavigableSet<User> set = new TreeSet<>(Comparator.comparing(User::getSalary).thenComparing(User::getCompany).thenComparing(User::getName).thenComparing(User::getAge));
        for (User user: users){
            set.add(user);
        }

        return set;
    }

}
