package org.example.lab2;

import java.util.Arrays;

public class ColumnShiftFinder {

    public static char findBestShiftLetter(double[] columnFrequency, double[] alphabetFrequency) {
        // Визначення найкращого зсуву для колонки
        double minDifference = Double.MAX_VALUE;
        char bestShift = 'a'; // За замовчуванням вважаємо зсув 'a'

        for (char shift = 'a'; shift <= 'z'; shift++) {
            double difference = 0.0;

            // Обчислення різниці між частотами колонки та загальним алфавітом
            for (int i = 0; i < columnFrequency.length; i++) {
                int shiftedIndex = (i + (shift - 'a')) % 26; // Визначаємо індекс з урахуванням зсуву
                difference += Math.abs(columnFrequency[shiftedIndex] - alphabetFrequency[i]);
            }

            // Перевірка, чи знайдений зсув є кращим за попередній
            if (difference < minDifference) {
                minDifference = difference;
                bestShift = shift;
            }
        }

        return bestShift;
    }
}