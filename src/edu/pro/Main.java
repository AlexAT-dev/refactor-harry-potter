package edu.pro;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.regex.Pattern;

public class Main {
    private static final Pattern SPLIT_PATTERN = Pattern.compile("[^A-Za-z']+");

    public static void main(String[] args) throws IOException {
        Runtime runtime = Runtime.getRuntime();
        runtime.gc();
        long memoryBefore = (runtime.totalMemory() - runtime.freeMemory());

        LocalDateTime start = LocalDateTime.now();

        // TreeMap has less memory usage (but slower)
        Map<String, Integer> freqMap = new TreeMap<>();

        // Buffer reader
        try (BufferedReader br = Files.newBufferedReader(Path.of("src/edu/pro/txt/harry.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] words = SPLIT_PATTERN.split(line.toLowerCase(Locale.ROOT));
                for (String word : words) {
                    if (!word.isEmpty()) {
                        freqMap.merge(word, 1, Integer::sum);
                    }
                }
            }
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
