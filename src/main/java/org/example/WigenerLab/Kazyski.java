package org.example.WigenerLab;

import java.util.*;

public class Kazyski {
    public HashMap<String, Integer> getMatchesOfLettersAndTheirNumber(String encryptedMessage, int choice) {
        HashMap<String, Integer> matchesOfLettersAndTheirNumber = new HashMap<>();
        String match = "";
        for (int i = 0; i <= encryptedMessage.length() - choice; i++) {
            match = encryptedMessage.substring(i, i + choice);
            matchesOfLettersAndTheirNumber.put(match, matchesOfLettersAndTheirNumber.getOrDefault(match, 0) + 1);
        }
        // Створення копії, щоб уникнути ConcurrentModificationException
        HashMap<String, Integer> copyOfMatches = new HashMap<>(matchesOfLettersAndTheirNumber);
        for (Map.Entry<String, Integer> entry : copyOfMatches.entrySet()) {
            String key = entry.getKey();
            Integer value = entry.getValue();
            if(value <= 1) {
                matchesOfLettersAndTheirNumber.remove(key);
            }
        }
        return matchesOfLettersAndTheirNumber;
    }

    public static int[][] calculateDistances(HashMap<String, Integer> matches, String encryptedMessage) {
        int[][] distances = new int[matches.size()][];
        int count = 0;
        for (Map.Entry<String, Integer> entry : matches.entrySet()) {
            String key = entry.getKey();
            Integer value = entry.getValue();
            distances[count] = new int[value - 1]; // Ініціалізація масиву distances[count]
            int[] occurrences = new int[value]; // Зберігає індекси всіх входжень підрядка
            int lastIndex = -1;
            for (int i = 0; i < value; i++) {
                lastIndex = encryptedMessage.indexOf(key, lastIndex + 1);
                occurrences[i] = lastIndex;
            }

            // Обчислення відстаней між усіма парами входжень підрядка
            for (int i = 0; i < value - 1; i++) {
                distances[count][i] = occurrences[i + 1] - occurrences[i];
            }

            count++;
        }
        return distances;
    }




    public static void printHashMap(HashMap<String, Integer> map, String encryptedMessage) {
        int[][] distances = calculateDistances(map, encryptedMessage);
        int idx = 0;
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
                System.out.print("Набір символів: " + entry.getKey() + ", кількість випадінь: " + entry.getValue() + ", відстань між випадіннями: ");
                System.out.println(Arrays.toString(distances[idx++]));
        }
    }

    public void findingNSD() {
        int nsd;
        System.out.println("Знайдемо НСД для даних відстаней");
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введіть перше число: ");
        int a = scanner.nextInt();

        System.out.print("Введіть друге число: ");
        int b = scanner.nextInt();

        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        nsd = a;
        System.out.println("НСД = " + nsd);
    }
}

