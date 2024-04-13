package org.example.lab2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Hardcoding {
    public static int finding(String encryptedMessage) {
        Scanner scanner = new Scanner(System.in);
        HashMap<String, Integer> wordOccurrences = new HashMap<>();
        boolean check = true;
        int choice;

        while (check) {
            System.out.print("Введіть кількість символів для пошуку: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            String regex = ".{" + choice + "}";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(encryptedMessage);

            while (matcher.find()) {
                String sequence = matcher.group();
                wordOccurrences.put(sequence, wordOccurrences.getOrDefault(sequence, 0) + 1);
            }

            System.out.println("Результати пошуку для " + choice + "-символьних послідовностей:");
            for (String word : wordOccurrences.keySet()) {
                int count = wordOccurrences.get(word);
                if (count > 1) {
                    System.out.println(word + ": " + count);
                }
            }

            wordOccurrences.clear();

            System.out.println("Продовжити введення? (так/ні)");
            String continueInput = scanner.nextLine();
            if (!continueInput.equalsIgnoreCase("так")) {
                check = false;
            }
        }

        System.out.print("Введіть остаточний вибір: ");
        choice = scanner.nextInt();
        return choice;
    }


    public ArrayList<String> nextStep(int choice, String encryptedMessage) {
        ArrayList<String> capacity = new ArrayList<>();
        int length = encryptedMessage.length();
        for (int i = 0; i < length; i += choice) {
            if (i + choice < length) {
                capacity.add(encryptedMessage.substring(i, i + choice));
            } else {
                capacity.add(encryptedMessage.substring(i));
            }
        }

        for (String string : capacity) {
            System.out.println(string);
        }
        return capacity;
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

    public HashMap<String, Integer>[] frequency(String encryptedMessage) {
        ArrayList<String> table = nextStep(finding(encryptedMessage), encryptedMessage);
        int columnCount = table.get(0).length(); // Assuming all rows have the same length
        HashMap<String, Integer>[] columns = new HashMap[columnCount];

        // Initialize HashMap for each column
        for (int i = 0; i < columnCount; i++) {
            columns[i] = new HashMap<>();
        }

        // Count the frequency of characters in each column
        for (String row : table) {
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

    public static void multiplyArray(double[] array, double multiplier) {
        for (int i = 0; i < array.length; i++) {
            array[i] *= multiplier;
        }
    }

    public Integer findBestShifts(double[] alpFrequency, double[] updatedFrequency) {
        int lol = 0;
        return lol;
    }
}