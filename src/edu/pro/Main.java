package edu.pro;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class Main {
    private static final Pattern SPLIT_PATTERN = Pattern.compile("[^A-Za-z']+");

    public static void main(String[] args) throws IOException {
        Runtime runtime = Runtime.getRuntime();
        runtime.gc();
        long memoryBefore = (runtime.totalMemory() - runtime.freeMemory());

        LocalDateTime start = LocalDateTime.now();

        // HashMap faster than TreeMap
        Map<String, Integer> freqMap = new HashMap<>();

        // Stream reading faster than Buffer reader. But has larger memory usage.
        try (Stream<String> lines = Files.lines(Path.of("src/edu/pro/txt/harry.txt"))) {
            lines.forEach(line -> {
                String[] words = SPLIT_PATTERN.split(line.toLowerCase(Locale.ROOT));
                for (String word : words) {
                    if (!word.isEmpty()) {
                        freqMap.merge(word, 1, Integer::sum);
                    }
                }
            });
        }

        freqMap.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .limit(30)
                .forEach(e -> System.out.println(e.getKey() + " " + e.getValue()));

        LocalDateTime finish = LocalDateTime.now();

        runtime.gc();
        long memoryAfter = (runtime.totalMemory() - runtime.freeMemory());
        long memoryUsed = memoryAfter - memoryBefore;
        double memoryUsedMb = memoryUsed / (1024.0 * 1024.0);

        System.out.println("------");
        System.out.println("Execution time (ms): " + ChronoUnit.MILLIS.between(start, finish));
        System.out.println("Memory used: " + memoryUsed + " bytes (" + String.format("%.2f", memoryUsedMb) + " MB)");
    }
}
