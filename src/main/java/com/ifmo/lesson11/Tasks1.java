package com.ifmo.lesson11;

import com.ifmo.lesson11.inner.Message;
import com.ifmo.lesson11.inner.MessageGenerator;
import com.ifmo.lesson11.inner.MessagePriority;

import java.util.*;
import java.util.stream.Collectors;

/*
    Реализуйте все методы.
 */
public class Tasks1 {

    public static void main(String[] args) {
        MessageGenerator generator = new MessageGenerator();

        List<Message> messages = generator.generate(100);

        countEachPriority(messages);
        countCountEachCode(messages);
        countUniqueMessages(messages);

        System.out.println("Genuine messages in natural order: \n" + genuineMessagesInOriginalOrder(messages));

        removeEach(generator.generate(100), MessagePriority.LOW);
        removeOther(generator.generate(100), MessagePriority.URGENT);
    }

    private static void countEachPriority(List<Message> messages) {
        // Сосчитайте количество сообщений для каждого приоритета.
        // Ответ необходимо вывести в консоль.

        int[] vs = new int[4];

        for (Message message: messages){
            switch (message.getPriority()){
                case LOW -> {
                    vs[0]++;
                }
                case MEDIUM -> {
                    vs[1]++;
                }
                case HIGH -> {
                    vs[2]++;
                }
                case URGENT -> {
                    vs[3]++;
                }
            }
        }
        System.out.printf("""
                Count:
                    -LOW %d
                    -MEDIUM %d
                    -HIGH %d
                    -URGENT %d
                """,
                vs[0],
                vs[1],
                vs[2],
                vs[3]);
    }

    private static void countCountEachCode(List<Message> messages) {
        // Сосчитайте количество сообщений для каждого кода сообщения.
        // Ответ необходимо вывести в консоль.

        Map<Integer, Integer> map = new HashMap<>();
        for (Message message: messages){
            int code = message.getCode();
            if (map.containsKey(code)){
                map.replace(code, map.get(code) + 1);
            } else {
                map.put(code, 1);
            }
        }
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            System.out.printf("code %d: %d \n", entry.getKey(), entry.getValue());
        }
    }

    private static void countUniqueMessages(List<Message> messages) {
        // Сосчитайте количество уникальных сообщений.
        // Ответ необходимо вывести в консоль.

        Map<Message, Integer> map = new HashMap<>();
        for (Message message: messages){
            if (map.containsKey(message)){
                map.replace(message, map.get(message) + 1);
            } else {
                map.put(message, 1);
            }
        }
        for (Map.Entry<Message, Integer> entry : map.entrySet()) {
            System.out.printf("message %s: %d \n", entry.getKey().toString(), entry.getValue());
        }
    }

    private static List<Message> genuineMessagesInOriginalOrder(List<Message> messages) {
        // Здесь необходимо вернуть только неповторяющиеся сообщения и в том порядке, в котором
        // они встречаются в первоначальном списке. Например, мы на входе имеем такие сообщения:
        // [{URGENT, 4}, {HIGH, 9}, {LOW, 3}, {HIGH, 9}],
        // то на выходе должны получить:
        // [{URGENT, 4}, {HIGH, 9}, {LOW, 3}].
        // Т.е. остались только уникальные значения, и порядок их поступления сохранен.

        Map<Message, Integer> map = new LinkedHashMap<>();
        for (Message message: messages){
            if (map.containsKey(message)){
                map.replace(message, map.get(message) + 1);
            } else {
                map.put(message, 1);
            }
        }
        List<Message> list = new ArrayList<>();
        for (Map.Entry<Message, Integer> entry : map.entrySet()) {
            if (entry.getValue() == 1){
                list.add(entry.getKey());
            }
        }

        return list;
    }

    private static void removeEach(Collection<Message> messages, MessagePriority priority) {
        // Удалить из коллекции каждое сообщение с заданным приоритетом.
        System.out.printf("Before remove each: %s, %s\n", priority, messages);

        Iterator iterator = messages.iterator();
        while (iterator.hasNext()){
            Message message = (Message) iterator.next();
            if (message.getPriority().equals(priority)){
                iterator.remove();
            }
        }

        System.out.printf("After remove each: %s, %s\n", priority, messages);
    }

    private static void removeOther(Collection<Message> messages, MessagePriority priority) {
        // Удалить из коллекции все сообщения, кроме тех, которые имеют заданный приоритет.
        System.out.printf("Before remove other: %s, %s\n", priority, messages);

        Iterator iterator = messages.iterator();
        while (iterator.hasNext()){
            Message message = (Message) iterator.next();
            if (!message.getPriority().equals(priority)){
                iterator.remove();
            }
        }

        System.out.printf("After remove other: %s, %s\n", priority, messages);
    }
}
