package org.example.lab1;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Lab1 {

    public static void main(String[] args) {
        Random random = new Random();
        Scanner scanner = new Scanner(System.in);
        Alphabet alph = new Alphabet();
        ArrayList<String> alphabet;
        Cesar crypt = new Cesar();
        MonoCrypt monocrypt = new MonoCrypt();
        FrequencyChart frequencyChart = new FrequencyChart();

        boolean continueSession = true;

        while (continueSession) {
            System.out.println("Оберіть режим шифрування:");
            System.out.println("1. Шифр Цезаря");
            System.out.println("2. Моноалфавітний шифр");
            System.out.println("0. Вийти з програми");
            int encryptionMode = scanner.nextInt();

            if (encryptionMode == 0) {
                System.out.println("Завершення сеансу.");
                break;
            }

            System.out.println("Оберіть алфавіт:");
            System.out.println("1. Український");
            System.out.println("2. Англійський");
            int alphabetChoice = scanner.nextInt();

            switch (alphabetChoice) {
                case 1:
                    alphabet = alph.creatingUkrAlphabet();
                    break;
                case 2:
                    alphabet = alph.creatingEngAlphabet();
                    break;
                default:
                    System.out.println("Невірний вибір алфавіту. Використано англійський алфавіт за замовчуванням.");
                    alphabet = alph.creatingEngAlphabet();
                    break;
            }

            switch (encryptionMode) {
                case 1:
                    int shiftNumber = random.nextInt(alphabet.size());
                    System.out.println(shiftNumber);
                    System.out.print("Введіть текст, який хочете зашифрувати: ");
                    scanner.nextLine();
                    String message = scanner.nextLine();
                    String encryptedMessage = crypt.encrypt(shiftNumber, message, alphabet);
                    System.out.println("Зашифрований шифром Цезаря: " + encryptedMessage);
                    if(alphabet.contains("б")) {
                        double[] alphFrequency = alph.ukrAlphFrequency;
                        frequencyChart.displayFrequencyChart(alphFrequency, alphabet, "Частота символів алфавіту");
                    }
                    else {
                        double[] alphFrequency = alph.engAlphFrequency;
                        frequencyChart.displayFrequencyChart(alphFrequency, alphabet, "Частота символів алфавіту");
                    }
                    double[] cesarFrequency = crypt.countLetterFrequency(encryptedMessage, alphabet);
                    frequencyChart.displayFrequencyChart(cesarFrequency, alphabet, "Частота символів для шифру Цезаря");
                    System.out.print("Введіть ключ для розшифрування тексту зашифрованого шифром Цезаря: ");
                    int key = scanner.nextInt();
                    System.out.println("Розшифрований текст Цезаря: " + crypt.decrypt(key, encryptedMessage, alphabet));
                    break;
                case 2:
                    ArrayList<String> changedAlphabet = monocrypt.getChangedAlph(alphabet);
                    for(String s: changedAlphabet) {
                        System.out.print(s);
                    }
                    System.out.print("Введіть текст, який хочете зашифрувати моноалфавітним шифром: ");
                    scanner.nextLine(); // Очищення буфера введення
                    String messageMono = scanner.nextLine();
                    String monoEncryptedMessage = monocrypt.monoEncrypt(messageMono, alphabet, changedAlphabet);
                    System.out.println("Зашифрований моноалфавітним шифром: " + monoEncryptedMessage);
                    String messages = monocrypt.monoDecrypt(monoEncryptedMessage, alphabet, changedAlphabet);
                    System.out.println(messages);
                    double[] alphFrequency = alph.engAlphFrequency;
                    frequencyChart.displayFrequencyChart(alphFrequency, alphabet, "Частота символів алфавіту");
                    double[] monoCryptFrequency = monocrypt.countLetterFrequency(monoEncryptedMessage, alphabet);
                    frequencyChart.displayFrequencyChart(monoCryptFrequency, alphabet, "Частота символів для моноалфавітного шифру");
//
//                    if(alphabet.contains("б")) {
//
//                    }
//                    else {
//
//                    }
//
//                    System.out.println("Розшифрування за моноалфавітом: ");
//                    monocrypt.decoding(monoEncryptedMessage, alphabet);
//                    break;
//                default:
//                    System.out.println("Невірний вибір режиму шифрування. Використано шифр Цезаря за замовчуванням.");
//                    break;
            }

            System.out.println("Бажаєте продовжити сеанс? (Так - 1, Ні - 0)");
            int continueChoice = scanner.nextInt();
            if (continueChoice == 0) {
                continueSession = false;
                System.out.println("Завершення сеансу.");
            }
        }
    }
}

