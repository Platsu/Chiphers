package org.example.WigenerLab;

import java.util.ArrayList;

public class Wigener {
    public String encrypt(ArrayList<String> alphabet, String message, String keyWord) {
        StringBuilder encryptedMessage = new StringBuilder();
        ArrayList<String> secret = secret(message, keyWord);
        int n = 0;
        for (char currentChar : message.toCharArray()) {
            String charUpper = String.valueOf(currentChar).toUpperCase();
            int index1 = alphabet.indexOf(charUpper);
            if (index1 != -1) {
                int index2 = alphabet.indexOf(secret.get(n % secret.size()));
                int newIndex = (index1 + index2) % alphabet.size();
                encryptedMessage.append(alphabet.get(newIndex));
                n++;
            }
        }
        return encryptedMessage.toString();
    }

    public String decrypt(ArrayList<String> alphabet, String encryptedMessage, String keyWord) {
        StringBuilder decryptedMessage = new StringBuilder();
        ArrayList<String> secret = secret(encryptedMessage, keyWord);
        int n = 0;
        for (char currentChar : encryptedMessage.toCharArray()) {
            String charLower = String.valueOf(currentChar);
            int index1 = alphabet.indexOf(charLower);
            int index2 = alphabet.indexOf(secret.get(n % secret.size()));
            if (index1 != -1) {
                int newIndex = (index1 - index2 + alphabet.size()) % alphabet.size();
                decryptedMessage.append(alphabet.get(newIndex));
            }
            else {
                continue;
            }
            n++;
        }
        return decryptedMessage.toString();
    }

    private ArrayList<String> secret(String message, String keyWord) {
        ArrayList<String> forKeyWord = new ArrayList<>();
        for (char currentChar : keyWord.toCharArray()) {
            forKeyWord.add(String.valueOf(currentChar));
        }

        ArrayList<String> repeatedKey = new ArrayList<>();
        while (repeatedKey.size() < message.length()) {
            repeatedKey.addAll(forKeyWord);
        }

        if (repeatedKey.size() > message.length()) {
            repeatedKey = new ArrayList<>(repeatedKey.subList(0, message.length()));
        }
        return repeatedKey;
    }
}
