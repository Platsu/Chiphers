package org.example.WigenerLab;

import java.util.ArrayList;
import java.util.HashMap;

public class CryptoAnalis {

     private ArrayList<String> textDividingByColumns(int keyLength, String encryptedMessage) {
        ArrayList<String> capacity = new ArrayList<>();
        int length = encryptedMessage.length();
        for (int i = 0; i < length; i += keyLength) {
            if (i + keyLength < length) {
                capacity.add(encryptedMessage.substring(i, i + keyLength));
            } else {
                capacity.add(encryptedMessage.substring(i));
            }
        }
        for (String string : capacity) {
            System.out.println(string);
        }
        return capacity;
    }

    public HashMap<String, Integer>[] getFrequencyOfLettersInColumns(String encryptedMessage, int keyLength) {
        ArrayList<String> textChart = textDividingByColumns(keyLength, encryptedMessage);
        int columnCount = textChart.get(0).length(); // Assuming all rows have the same length
        HashMap<String, Integer>[] columns = new HashMap[columnCount];

        // Initialize HashMap for each column
        for (int i = 0; i < columnCount; i++) {
            columns[i] = new HashMap<>();
        }

        // Count the frequency of letters in each column
        for (String row : textChart) {
            for (int i = 0; i < row.length(); i++) {
                String character = String.valueOf(row.charAt(i)); // Convert char to string
                columns[i].put(character, columns[i].getOrDefault(character, 0) + 1);
            }
        }

        for (int i = 0; i < columnCount; i++) {
            System.out.println("Стовпець " + (i + 1) + ":");
            for (String key : columns[i].keySet()) {
                System.out.println("Літера: " + key + ", Кількість випадінь: " + columns[i].get(key));
            }
            System.out.println();
        }
        return columns;
    }

    public double[] calculateLetterFrequency(HashMap<String, Integer>[] columns, ArrayList<String> alphabet) {
        double[] letterFrequency = new double[alphabet.size()]; // Використовуємо розмір алфавіту
        int totalLetters = 0;

        // Обчислення загальної кількості літер у всіх стовпцях
        for (HashMap<String, Integer> column : columns) {
            for (int count : column.values()) {
                totalLetters += count;
            }
        }

        // Обчислення частоти кожної літери
        for (int i = 0; i < alphabet.size(); i++) { // Використовуємо розмір алфавіту
            String letter = alphabet.get(i);
            int letterCount = 0;
            for (HashMap<String, Integer> column : columns) {
                letterCount += column.getOrDefault(letter, 0); // Використовуємо саме літеру з алфавіту
            }
            letterFrequency[i] = (double) letterCount / totalLetters;
        }

        return letterFrequency;
    }

    public double[] addMissingLetters(double[] frequencies, ArrayList<String> alphabet) {
        double[] updatedFrequencies = new double[alphabet.size()];

        // Заповнення масиву нульовими значеннями
        for (int i = 0; i < alphabet.size(); i++) {
            updatedFrequencies[i] = 0.0;
        }

        // Оновлення значень для літер, які мають відомі частоти
        for (int i = 0; i < alphabet.size(); i++) {
            String letter = alphabet.get(i);
            for (int j = 0; j < frequencies.length; j++) {
                if (letter.equalsIgnoreCase(alphabet.get(j))) {
                    updatedFrequencies[i] = frequencies[j];
                    break;
                }
            }
        }

        return updatedFrequencies;
    }

    public int findBestShift(double[] columnFrequencies, double[] alpFrequency) {
        // Ініціалізуємо масив для зберігання сум абсолютних різниць для кожного зсуву
        double[] shiftDifferences = new double[alpFrequency.length];

        // Для кожного можливого зсуву
        for (int shift = 0; shift < alpFrequency.length; shift++) {
            double differenceSum = 0.0;
            // Порівнюємо частоти символів у поточному стовпці з частотами в алфавіті
            for (int i = 0; i < alpFrequency.length; i++) {
                int shiftedIndex = (i + shift) % alpFrequency.length; // Визначаємо індекс символу з урахуванням зсуву
                differenceSum += Math.abs(columnFrequencies[shiftedIndex] - alpFrequency[i]);
            }
            // Зберігаємо суму абсолютних різниць для поточного зсуву
            shiftDifferences[shift] = differenceSum;
        }

        // Знаходимо індекс мінімального елемента у масиві різниць
        int bestShift = 0;
        double minDifference = shiftDifferences[0];
        for (int i = 1; i < shiftDifferences.length; i++) {
            if (shiftDifferences[i] < minDifference) {
                minDifference = shiftDifferences[i];
                bestShift = i;
            }
        }

        return bestShift;
    }

    public char findBestShiftLetterForEng(double[] columnFrequency, double[] alphabetFrequency) {
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

    public char findBestShiftLetterForUkr(double[] columnFrequency, double[] alphabetFrequency) {
        // Визначення найкращого зсуву для колонки
        double minDifference = Double.MAX_VALUE;
        char bestShift = 'а'; // За замовчуванням вважаємо зсув 'a'

        for (char shift = 'а'; shift <= 'я'; shift++) {
            double difference = 0.0;

            // Обчислення різниці між частотами колонки та загальним алфавітом
            for (int i = 0; i < columnFrequency.length; i++) {
                int shiftedIndex = (i + (shift - 'а')) % alphabetFrequency.length; // Визначаємо індекс з урахуванням зсуву
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
