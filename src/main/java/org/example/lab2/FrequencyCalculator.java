package org.example.lab2;

import java.util.List;

public class FrequencyCalculator {

    public static double[] calculateFrequencies(String text, List<String> alphabet) {
        double[] frequencies = new double[alphabet.size()];
        int totalCount = 0;

        // Обчислення кількості кожної букви у тексті
        for (char ch : text.toCharArray()) {
            String letter = String.valueOf(ch).toUpperCase();
            int index = alphabet.indexOf(letter);
            if (index != -1) { // Якщо буква належить алфавіту
                frequencies[index]++;
                totalCount++;
            }
        }

        // Нормалізація частот до відносних значень
        for (int i = 0; i < frequencies.length; i++) {
            frequencies[i] /= totalCount;
        }

        return frequencies;
    }
}
