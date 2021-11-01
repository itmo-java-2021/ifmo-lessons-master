package com.ifmo.lesson11;

import com.ifmo.lesson2.Count2;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TopWords {
    public static void main(String[] args) throws IOException {
        // Создаем файл, указывая путь к текстовому файлу на диске
        File text = new File("src/wap.txt");

        // Вычитываем все строки из файла
        List<String> lines = Files.readAllLines(text.toPath());

        // Создаем пустую коллекцию для слов.
        List<String> words = new ArrayList<>();

        for (String line : lines) {
            // Для каждой строки
            String[] wordSplit =
                    line.toLowerCase() // Переводим в нижний регистр
                            .replaceAll("\\p{Punct}", " ") // Заменяем все знаки на пробел
                            .trim() // Убираем пробелы в начале и конце строки.
                            .split("\\s"); // Разбиваем строки на слова

            for (String s : wordSplit) {
                // Выбираем только непустые слова.
                if (s.length() > 0)
                    words.add(s.trim());
            }
        }

        System.out.println(top10Words(words));
        System.out.println(top10Phrases(words));
        System.out.println(charactersFrequency(words));
    }

    /**
     * Возвращает 10 наиболее часто встречающихся слов с их
     * счётчиками.
     *
     * @param words Список слов.
     * @return Map, где ключ - слово, а значение - сколько раз встретилось.
     */
    public static Map<String, Integer> top10Words(List<String> words) {

        Map<String, Count> hashMap = new HashMap<>();
        for (String item: words){
            if (hashMap.containsKey(item)){
                hashMap.get(item).increase();
            } else {
                hashMap.put(item, new Count(item));
            }
        }

        return GetMaxCount(hashMap, 10);
    }

    /**
     * Возвращает 10 наиболее часто встречающихся фраз и соответствующие счётчики.
     * Фраза - два подряд идущих слова.
     *
     * @param words Список слов.
     * @return Топ 10 фраз.
     */
    public static Map<String, Integer> top10Phrases(List<String> words) {

        Map<String, Count> hashMap = new HashMap<>();
        for (int i = 0; i < words.size() - 1; i++) {
            String item = words.get(i) + " " + words.get(i + 1);
            if (hashMap.containsKey(item)) {
                hashMap.get(item).increase();
            } else {
                hashMap.put(item, new Count(item));
            }
        }

        return GetMaxCount(hashMap, 10);
    }

    /**
     * Возвращает символы и частоту их встречаемости.
     *
     * @param words Список слов.
     * @return Map где ключ - символ, а значение - сколько раз он встретился в списке слов.
     */
    public static Map<Character, Integer> charactersFrequency(List<String> words) {

        Map<Character, Count> hashMap = new HashMap<>();
        for (String word : words) {
            char[] chars = word.toCharArray();
            for (char c : chars) {
                if (hashMap.containsKey(c)) {
                    hashMap.get(c).increase();
                } else {
                    hashMap.put(c, new Count(c));
                }
            }
        }

        return GetMaxCount(hashMap, 10);
    }

    public static <T> Map<T, Integer> GetMaxCount(Map<T, Count> map, int length){
        Count[] counts = map.values().stream().sorted((x, y) -> Integer.compare(y.count, x.count)).toArray(Count[]::new);

        HashMap<T, Integer> maps = new HashMap<>(10);
        for (int i = 0; i < length; i++) {
            Count<T> count = counts[i];
            maps.put(count.word, count.count);
        }

        return maps;
    }

    public static class Count<T>{
        public final T word;
        public int count;

        public Count(T word) {
            this.word = word;
            count = 1;
        }

        public void increase(){
            count++;
        }
    }
}
