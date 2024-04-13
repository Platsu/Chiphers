package org.example.lab2;

import java.util.ArrayList;

public class Wigenerik {
    public String encrypt(ArrayList<String> alphabet, String message, String keyWord) {
        StringBuilder encryptedMessage = new StringBuilder();
        ArrayList<String> secret = secret(message, keyWord);
        int n = 0;
        for (char currentChar : message.toCharArray()) {
            String charLower = String.valueOf(currentChar).toUpperCase(); // перетворення у нижній регістр
            int index1 = alphabet.indexOf(charLower);
            int index2 = alphabet.indexOf(secret.get(n % secret.size()));
            if (index1 != -1) {
                int newIndex = (index1 + index2) % alphabet.size(); // Використання підрахунку залишку
                encryptedMessage.append(alphabet.get(newIndex));
            } else {
                // Якщо символ не належить алфавіту, пропускаємо його
                continue;
            }
            n++;
        }
        return encryptedMessage.toString();
    }


    public String decrypt(ArrayList<String> alphabet, String encryptedMessage, String keyWord) {
        StringBuilder decryptedMessage = new StringBuilder();
        ArrayList<String> secret = secret(encryptedMessage, keyWord);
        int n = 0;
        for (char currentChar : encryptedMessage.toCharArray()) {
            String charLower = String.valueOf(currentChar); // перетворення у нижній регістр
            int index1 = alphabet.indexOf(charLower);
            int index2 = alphabet.indexOf(secret.get(n % secret.size()));
            if (index1 != -1) {
                int newIndex = (index1 - index2 + alphabet.size()) % alphabet.size(); // Використання підрахунку залишку
                decryptedMessage.append(alphabet.get(newIndex));
            } else if (currentChar == ' ') {
                decryptedMessage.append(' '); // Додати пропуск, якщо він присутній у зашифрованому повідомленні
            } else {
                decryptedMessage.append(currentChar); // Додати символ без змін, якщо його немає в алфавіті
            }
            n++;
        }
        return decryptedMessage.toString();
    }

    private ArrayList<String> secret(String message, String keyWord) {
        ArrayList<String> forMessage = new ArrayList<>();
        for (char currentChar : message.toCharArray()) {
            if (currentChar != ' ') {
                forMessage.add(String.valueOf(currentChar));
            }
        }
        ArrayList<String> forKeyWord = new ArrayList<>();
        for (char currentChar : keyWord.toCharArray()) {
            forKeyWord.add(String.valueOf(currentChar));
        }

        // Повторюємо ключове слово до тих пір, поки його довжина не буде дорівнювати довжині повідомлення
        ArrayList<String> repeatedKey = new ArrayList<>();
        while (repeatedKey.size() < forMessage.size()) {
            repeatedKey.addAll(forKeyWord);
        }

        // Обрізаємо зайві символи, якщо отримана послідовність довша за повідомлення
        if (repeatedKey.size() > forMessage.size()) {
            repeatedKey = new ArrayList<>(repeatedKey.subList(0, forMessage.size()));
        }
        return repeatedKey;
    }
}
