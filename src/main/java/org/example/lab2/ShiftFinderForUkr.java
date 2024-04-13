package org.example.lab2;

public class ShiftFinderForUkr {

    public static char findBestShiftUkr(double[] columnFrequency, double[] alphabetFrequency) {
        // Визначення найкращого зсуву для колонки
        double minDifference = Double.MAX_VALUE;
        char bestShift = 'а'; // За замовчуванням вважаємо зсув 'а'

        for (char shift = 'а'; shift <= 'я'; shift++) {
            double difference = 0.0;

            // Обчислення різниці між частотами колонки та загальним алфавітом
            for (int i = 0; i < columnFrequency.length; i++) {
                int shiftedIndex = (i + (shift - 'а')) % 33; // Визначаємо індекс з урахуванням зсуву
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
