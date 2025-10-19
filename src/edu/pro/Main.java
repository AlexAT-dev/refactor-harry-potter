package edu.pro;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static String cleanText(String url) throws IOException {
        String content = new String(Files.readAllBytes(Paths.get(url)));
        // Очищення. Лишаємо лише літери, пробіли та апострофи всередині слів
        content = content.replaceAll("[^A-Za-z' ]", " ").toLowerCase(Locale.ROOT);
        content = content.replaceAll("\\s+", " ").trim();
        return content;
    }

    public static void main(String[] args) throws IOException {
        LocalDateTime start = LocalDateTime.now();

        String content = cleanText("src/edu/pro/txt/harry.txt");

        List<String> words = Arrays.asList(content.split(" "));

        Set<String> distinctWords = new HashSet<>(words);

        // Частоти
        Map<String, Integer> freqMap = new HashMap<>();

        for (String distinct : distinctWords) {
            int count = 0;
            for (String word : words) {
                if (distinct.equals(word)) {
                    count++;
                }
            }
            freqMap.put(distinct, count);
        }

        List<String> wordFreqList = freqMap.entrySet().stream()
                .map(e -> e.getKey() + " " + e.getValue())
                .sorted(Comparator.comparingInt(s -> Integer.parseInt(s.replaceAll("[^0-9]", ""))))
                .toList();

        for (int i = 0; i < 30 && i < wordFreqList.size(); i++) {
            System.out.println(wordFreqList.get(wordFreqList.size() - 1 - i));
        }

        LocalDateTime finish = LocalDateTime.now();

        System.out.println("------");
        System.out.println("Час виконання (мс): " + ChronoUnit.MILLIS.between(start, finish));
    }
}
