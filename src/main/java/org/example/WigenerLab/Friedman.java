package org.example.WigenerLab;

import org.example.lab1.Alphabet;

import java.util.ArrayList;
import java.util.Arrays;

public class Friedman {
    private ArrayList<Integer> frequency(String encryptedMessage, ArrayList<String> alphabet) {
        ArrayList<Integer> numberOfLetters = new ArrayList<>();
        int number = 0;
        for (int i = 0; i < alphabet.size(); i++) {
            for(Character currentChar: encryptedMessage.toCharArray()) {
                if(String.valueOf(currentChar).equals(alphabet.get(i))) {
                    number++;
                }
            }
            numberOfLetters.add(number);
            number = 0;
        }
        return numberOfLetters;
    }

    public double friedmanIndex(String encryptedMessage, ArrayList<String> alphabet) {
        double indexOfMessageLength = encryptedMessage.length() * (encryptedMessage.length() - 1);
        double indexOfLettersFrequency = 0.0;
        ArrayList<Integer> letterFrequencies = frequency(encryptedMessage, alphabet);
        for (Integer frequency : letterFrequencies) {
            indexOfLettersFrequency += frequency * (frequency - 1);
        }
        double indexOfFriedman = indexOfLettersFrequency / indexOfMessageLength;
        System.out.println("Коефіцієнт Фрідмана: " + indexOfFriedman);
        return indexOfFriedman;
    }

    public int getKeyLengthByFriedman(String encryptedMessage, ArrayList<Double> friedmanIndexIdentity, ArrayList<String> alphabet) {
        int keyLengthByFriedman = 0;
        double min = Double.MAX_VALUE;
        double index = friedmanIndex(encryptedMessage, alphabet);
        for (double value : friedmanIndexIdentity) {
            double difference = Math.abs(value - index);
            if (difference < min) {
                min = difference;
                keyLengthByFriedman = friedmanIndexIdentity.indexOf(value) + 1;
            }
        }
        return keyLengthByFriedman;
    }

}
