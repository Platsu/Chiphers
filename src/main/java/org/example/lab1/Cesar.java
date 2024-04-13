package org.example.lab1;

import java.util.ArrayList;
import java.util.List;

public class Cesar {

    public String encrypt(int shiftNumber, String message, ArrayList<String> alphabet) {
        StringBuilder encryptedMessage = new StringBuilder();
        for (char currentChar : message.toCharArray()) {
            int index = alphabet.indexOf(String.valueOf(currentChar).toLowerCase());
            if (index != -1) {
                int newIndex = (index + shiftNumber) % alphabet.size();
                encryptedMessage.append(alphabet.get(newIndex));
            } else {
                encryptedMessage.append(currentChar);
            }
        }
        return encryptedMessage.toString();
    }

    public String decrypt(int shiftNumber, String encryptedMessage, ArrayList<String> alphabet) {
        StringBuilder decryptedMessage = new StringBuilder();
        for (char currentChar : encryptedMessage.toCharArray()) {
            int index = alphabet.indexOf(String.valueOf(currentChar).toLowerCase());
            if (index != -1) { // Якщо символ знаходиться в алфавіті
                int newIndex = (index - shiftNumber) % alphabet.size();
                // Виправлення для від'ємних значень
                if (newIndex < 0) {
                    newIndex = alphabet.size() + newIndex;
                }
                decryptedMessage.append(alphabet.get(newIndex));
            } else {
                decryptedMessage.append(currentChar);
            }
        }
        return decryptedMessage.toString();
    }

    public double[] countLetterFrequency(String message, ArrayList<String> alphabet) {
        double[] frequency = new double[alphabet.size()]; // Масив для зберігання частоти літер

        int totalCharacters = 0;
        for (char currentChar : message.toCharArray()) {
            char currentLowerChar = Character.toLowerCase(currentChar);
            if (alphabet.contains(String.valueOf(currentLowerChar))) {
                totalCharacters++;
            }
        }

        for (char currentChar : message.toCharArray()) {
            char currentLowerChar = Character.toLowerCase(currentChar);
            int index = alphabet.indexOf(String.valueOf(currentLowerChar));
            if (index != -1) { // Якщо символ знаходиться в алфавіті
                frequency[index]++;
            }
        }

        for (int i = 0; i < frequency.length; i++) {
            frequency[i] = (frequency[i] / totalCharacters) * 100;
        }

        return frequency;
    }
}

