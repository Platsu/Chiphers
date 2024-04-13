package org.example.WigenerLab;

import org.example.lab1.Alphabet;
import org.example.lab1.Cesar;
import org.example.lab1.FrequencyChart;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Alphabet alp = new Alphabet();
        Wigener wigener = new Wigener();
        Friedman friedman = new Friedman();
        Kazyski kazyski = new Kazyski();
        CryptoAnalis cryptoAnalis = new CryptoAnalis();
        FrequencyChart frequencyChart = new FrequencyChart();
        Cesar cesar = new Cesar();

        ArrayList<Integer> shifts = new ArrayList<>();
        ArrayList<String> variants = new ArrayList<>();
        ArrayList<Double> friedmanIndexIdentity;
        boolean isRun = true;
        boolean trymore = true;
        int languageChoice;
        int applicationChoice;
        int keyLength;
        double[] alphabetFrequency;
        String message;
        String key = "";
        String keyUpperCase;
        String answer;
        ArrayList<String> alphabet;

        while (isRun) {
            System.out.println("Виберіть мову роботи: ");
            System.out.println("Англійська - 1");
            System.out.println("Українська - 2");
            languageChoice = scanner.nextInt();

            switch (languageChoice) {
                case 1:
                    alphabet = alp.creatingEngAlphabet();
                    alphabetFrequency = alp.getEngAlphFrequency();
                    friedmanIndexIdentity = alp.getfriedmanIndexIdentityForEng();
                    System.out.println("Виберіть режим роботи");
                    System.out.println("Зашифрування та розшифрування власного тексту за власним ключем - 1");
                    System.out.println("Розшифрування зашифрованого повідомлення - 2");
                    applicationChoice = scanner.nextInt();

                    switch (applicationChoice) {
                        case 1:
                            System.out.print("Введіть повідомлення для шифрування: ");
                            scanner.nextLine();
                            message = scanner.nextLine();
                            System.out.print("Введіть ключ для шифрування: ");
                            key = scanner.nextLine();
                            keyUpperCase = key.toUpperCase();
                            String encryptedMessage = wigener.encrypt(alphabet, message, keyUpperCase);
                            System.out.println("Довжина повідомлення після обробки становить " + encryptedMessage.length() + " символів");
                            System.out.println("Зашифироване повідомлення: " + encryptedMessage);
                            String decryptedMessage = wigener.decrypt(alphabet, encryptedMessage, keyUpperCase);
                            System.out.println("Розшифироване повідомлення: " + decryptedMessage);
                            System.out.println("Продовжуємо роботу програми? так/ні");
                            answer = scanner.nextLine();
                            if(answer.equals("так")) {
                                System.out.println();
                            } else {
                                isRun = false;
                            }
                            break;


                        case 2:
                            System.out.print("Введіть зашифроване повідомлення: ");
                            scanner.nextLine();
                            encryptedMessage = scanner.nextLine();
                            System.out.println("Довжина ключа за методом Фрідмана: " + friedman.getKeyLengthByFriedman(encryptedMessage, friedmanIndexIdentity, alphabet));
                            System.out.println("Переходимо до методу Казискі");
                            while (trymore) {
                                System.out.print("Введіть довжину символів, яку хочете перевірити: ");
                                int choice = scanner.nextInt();
                                HashMap<String, Integer> kazyskich = kazyski.getMatchesOfLettersAndTheirNumber(encryptedMessage, choice);
                                kazyski.printHashMap(kazyskich, encryptedMessage);
                                kazyski.findingNSD();
                                System.out.println("Продовжуємо пошук? так/ні");
                                scanner.nextLine();
                                answer = scanner.nextLine();
                                if(answer.equals("так")) {
                                    System.out.println();
                                } else {
                                    trymore = false;
                                }
                            }
                            System.out.print("На основі отриманих результатів введіть довжину ключа:");
                            keyLength = scanner.nextInt();
                            HashMap<String, Integer>[] frequency = cryptoAnalis.getFrequencyOfLettersInColumns(encryptedMessage, keyLength);
                            for (int i = 0; i < frequency.length; i++) {
                                double[] FrequencyOfLettersInColumns = cryptoAnalis.calculateLetterFrequency(new HashMap[]{frequency[i]}, alphabet);
                                double[] updatedFrequency = cryptoAnalis.addMissingLetters(FrequencyOfLettersInColumns, alphabet);
                                shifts.add(cryptoAnalis.findBestShift(alphabetFrequency, FrequencyOfLettersInColumns));
                                char bestShift = cryptoAnalis.findBestShiftLetterForEng(FrequencyOfLettersInColumns, alphabetFrequency);
                                frequencyChart.displayFrequencyChart(updatedFrequency, alphabet, "Частота символів алфавіту (Колонка " + (i + 1) + ")");
                                variants.add(String.valueOf(bestShift));
                            }
                            for (int i = 0; i < shifts.size(); i++) {
                                key += cesar.decrypt(shifts.get(i), variants.get(i), alphabet);
                            }
                            System.out.println("Ключове слово: " + key.toUpperCase());
                            decryptedMessage = wigener.decrypt(alphabet, encryptedMessage, key.toUpperCase());
                            System.out.println(decryptedMessage);
                            System.out.println("Продовжуємо роботу програми? так/ні");
                            answer = scanner.nextLine();
                            if(answer.equals("так")) {
                                System.out.println();
                            } else {
                                isRun = false;
                            }
                            scanner.close();
                            break;
                    }
                    break;



                case 2:
                    alphabet = alp.creatingUkrAlphabet();
                    alphabetFrequency = alp.getUkrAlphFrequency();
                    friedmanIndexIdentity = alp.getfriedmanIndexIdentityForUkr();
                    System.out.println("Виберіть режим роботи");
                    System.out.println("Зашифрування та розшифрування власного тексту за власним ключем - 1");
                    System.out.println("Розшифрування зашифрованого повідомлення - 2");
                    applicationChoice = scanner.nextInt();



                    switch (applicationChoice) {
                        case 1:
                            System.out.print("Введіть повідомлення для шифрування: ");
                            scanner.nextLine();
                            message = scanner.nextLine();
                            System.out.print("Введіть ключ для шифрування: ");
                            key = scanner.nextLine();
                            keyUpperCase = key.toUpperCase();
                            String encryptedMessage = wigener.encrypt(alphabet, message, keyUpperCase);
                            System.out.println("Довжина повідомлення після обробки становить " + encryptedMessage.length() + " символів");
                            System.out.println("Зашифироване повідомлення: " + encryptedMessage);
                            String decryptedMessage = wigener.decrypt(alphabet, encryptedMessage, keyUpperCase);
                            System.out.println("Розшифироване повідомлення: " + decryptedMessage);
                            System.out.println("Продовжуємо роботу програми? так/ні");
                            answer = scanner.nextLine();
                            if(answer.equals("так")) {
                                System.out.println();
                            } else {
                                isRun = false;
                            }
                            break;




                        case 2:
                            System.out.print("Введіть зашифроване повідомлення: ");
                            scanner.nextLine();
                            encryptedMessage = scanner.nextLine();
                            System.out.println("Довжина ключа за методом Фрідмана: " + friedman.getKeyLengthByFriedman(encryptedMessage, friedmanIndexIdentity, alphabet));
                            System.out.println("Переходимо до методу Казискі");
                            while (trymore) {
                                System.out.print("Введіть довжину символів, яку хочете перевірити: ");
                                int choice = scanner.nextInt();
                                HashMap<String, Integer> kazyskich = kazyski.getMatchesOfLettersAndTheirNumber(encryptedMessage, choice);
                                kazyski.printHashMap(kazyskich, encryptedMessage);
                                kazyski.findingNSD();
                                System.out.println("Продовжуємо пошук? так/ні");
                                scanner.nextLine();
                                answer = scanner.nextLine();
                                if(answer.equals("так")) {
                                    System.out.println();
                                } else {
                                    trymore = false;
                                }
                            }
                            System.out.print("На основі отриманих результатів введіть довжину ключа:");
                            keyLength = scanner.nextInt();
                            HashMap<String, Integer>[] frequency = cryptoAnalis.getFrequencyOfLettersInColumns(encryptedMessage, keyLength);
                            for (int i = 0; i < frequency.length; i++) {
                                double[] FrequencyOfLettersInColumns = cryptoAnalis.calculateLetterFrequency(new HashMap[]{frequency[i]}, alphabet);
                                double[] updatedFrequency = cryptoAnalis.addMissingLetters(FrequencyOfLettersInColumns, alphabet);
                                shifts.add(cryptoAnalis.findBestShift(alphabetFrequency, FrequencyOfLettersInColumns));
                                char bestShift = cryptoAnalis.findBestShiftLetterForUkr(FrequencyOfLettersInColumns, alphabetFrequency);
                                frequencyChart.displayFrequencyChart(updatedFrequency, alphabet, "Частота символів алфавіту (Колонка " + (i + 1) + ")");
                                variants.add(String.valueOf(bestShift));
                            }
                            for (int i = 0; i < shifts.size(); i++) {
                                key += cesar.decrypt(shifts.get(i), variants.get(i), alphabet);
                            }
                            System.out.println("Ключове слово: " + key.toUpperCase());
                            decryptedMessage = wigener.decrypt(alphabet, encryptedMessage, key.toUpperCase());
                            System.out.println(decryptedMessage);
                            System.out.println("Продовжуємо роботу програми? так/ні");
                            answer = scanner.nextLine();
                            if(answer.equals("так")) {
                                System.out.println();
                            } else {
                                isRun = false;
                            }
                            scanner.close();
                            break;
                    }
                default:
                    System.out.println("Неправильний вибір. Будь ласка, спробуйте ще раз.");
            }
        }
    }
}
