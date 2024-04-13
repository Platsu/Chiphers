package org.example.lab1;

import java.util.*;

public class MonoCrypt {
    private final Scanner scanner = new Scanner(System.in);

    public ArrayList<String> getChangedAlph(ArrayList<String> alphabet) {
        ArrayList<String> changedAlphabet = new ArrayList<>(alphabet);
        Collections.shuffle(changedAlphabet);
        return changedAlphabet;
    }

    public String monoEncrypt(String message, ArrayList<String> alphabet, ArrayList<String> changedAlphabet) {
        StringBuilder monoEncryptedMessage = new StringBuilder();
        for (char currentChar : message.toCharArray()) {
            String current = String.valueOf(currentChar).toLowerCase();
            if (!alphabet.contains(current)) {
                monoEncryptedMessage.append(current);
            } else {
                int index = alphabet.indexOf(current);
                monoEncryptedMessage.append(changedAlphabet.get(index));
            }
        }
        return monoEncryptedMessage.toString();
    }

    public String monoDecrypt(String encryptedMessage, ArrayList<String> alphabet, ArrayList<String> changedAlphabet) {
        StringBuilder monoDecryptedMessage = new StringBuilder();
        for (char currentChar : encryptedMessage.toCharArray()) {
            String current = String.valueOf(currentChar);
            if (!alphabet.contains(current)) {
                monoDecryptedMessage.append(current);
            } else {
                int index = changedAlphabet.indexOf(current);
                monoDecryptedMessage.append(alphabet.get(index));
            }
        }
        return monoDecryptedMessage.toString();
    }

    public void decoding(String encryptedMessage, ArrayList<String> alphabet) {
        ArrayList<String> changingAlphabet = new ArrayList<>(Arrays.asList(encryptedMessage.split("")));
        boolean continueDecoding = true;

        while (continueDecoding) {
            System.out.println("Зашифроване повідомлення: " + encryptedMessage);

            System.out.print("Введіть букву, яку хочете замінити: ");
            String wantToChange = scanner.nextLine().toLowerCase();
            System.out.print("Введіть букву, на яку хочете замінити: ");
            String newLetter = scanner.nextLine().toLowerCase();

            for (int i = 0; i < changingAlphabet.size(); i++) {
                String currentLetter = changingAlphabet.get(i).toLowerCase();
                if (alphabet.contains(currentLetter)) {
                    if (currentLetter.equals(wantToChange)) {
                        changingAlphabet.set(i, newLetter);
                    }
                }
            }

            System.out.println("Розшифроване повідомлення: " + String.join("", changingAlphabet));
            System.out.print("Продовжуємо розшифрування? (так/ні): ");
            String response = scanner.nextLine();
            continueDecoding = response.equalsIgnoreCase("так");
        }
    }


    // Метод для підрахунку частоти літер
    public double[] countLetterFrequency(String message, ArrayList<String> alphabet) {
        double[] frequency = new double[alphabet.size()];
        int totalCharacters = 0;

        // Підрахунок загальної кількості символів у тексті
        for (char currentChar : message.toCharArray()) {
            String current = String.valueOf(currentChar).toLowerCase();
            if (alphabet.contains(current)) {
                totalCharacters++;
            }
        }

        for (char currentChar : message.toCharArray()) {
            String current = String.valueOf(currentChar).toLowerCase();
            int index = alphabet.indexOf(current);
            if (index != -1) {
                frequency[index]++;
            }
        }

        for (int i = 0; i < frequency.length; i++) {
            frequency[i] = (frequency[i] / totalCharacters) * 100;
        }
        return frequency;
    }
}

