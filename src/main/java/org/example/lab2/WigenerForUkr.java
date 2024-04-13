package org.example.lab2;

import org.example.lab1.Alphabet;
import org.example.lab1.Cesar;
import org.example.lab1.FrequencyChart;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class WigenerForUkr {
    public String encryptedMessage = "ЯБМЩХРЮІЮКЕЧЩЇНАБИЖЄХЗЖІРТГҐЮХІЩРЬЖЖФШНКЯФМЩФШРЩХВЙАВЦСЖБЛИІШХЗЛГАОДЙШМЬЯГРЩЯВҐІЮКНАМЯМІКМЮЬКҐМРХЩЮЩХИЮІХУПЧЬУЮЗІНМЙЯНЗЯКЯЛДЧРҐМІГЬЙБАГЇЩЙНКЯНЗІЄЦІІЯЧНЧЮЯМСШХРНБЩМЩВЇЇЇЦКЇЧМХУЇПХРФУЛКДТФЮЖЮКЄЦМРМЩХТЗИҐГЖМІБМЯКҐЖЛЙДЇЇЬБЮІІШМІШХГЇЛЛБЧГАИШКВМІРГЖЙФГЮМФЖЩШКЯЮЗІЯСЦШЯЮЛЬЛЇЗРГЖІЯЧКЧЬХМЗРЯЖЙЖЦЙЖФЦПМЯВЗХЩСПЦМФЬЗКГЬШЯНОЇПХЛДШАПЧЖЦАЇБОЮІХФСЩКЬЖЗХҐҐККҐСКЮХІЖЯЯЇНБГРКРМЮШҐЬМІКБЖЛКҐЖММЦОЙБАЇНЗСРЖҐЬҐЛЙНЄЦЩЛПЦАХПЧГХЮЗРЧИЖҐИҐМШЛРККЯПОЯВКНМЛЙЧВКЛЧГСКНТЦЛЇЖАИІРРМЗХУЮЗІРМЙФГСЩКНПЦЧБҐКРБЖЛҐНЮЩВКЇДЩЇЇЧБЛЄДМЦЄКРИРЇЇШМЬЯЯЮЯБДЇНМЛЙЧУАОЦКНПМБАСЬЯВПФШХІЗХҐҐККҐСКЮАЛЧҐЩМЩФШЗЬБАКЧПГШЖЯЩСЗІҐСКЮХІСКГМЙФГЧЇМХУЇПХАНЩЇАЇМЦУЇЖЛМЗРЯЮЙЖЦЙЖКГЇААҐЖСЮАІЖКФЮЗКІММКЩҐИЯУСМІЯЮЯБДЇНМЛРҐЕЦЯЧИАЬЖХГШЯКЮПФШЦЕНБЯЮЗФ";

    public static void main(String[] args) {
        WigenerForUkr wigenrForUkr = new WigenerForUkr();
        Scanner scanner = new Scanner(System.in);
        Hardcoding hardcoding = new Hardcoding();
        Alphabet alphabet = new Alphabet();
        FrequencyChart frequencyChart = new FrequencyChart();
        Cesar cesar = new Cesar();
        Wigenerik wigenerik = new Wigenerik();

        String lol = scanner.nextLine();
        String key = "ПРИВІТ";
        String lolo = wigenerik.encrypt(alphabet.creatingUkrAlphabet(), lol, key);
        System.out.println(lolo);
        String lolol = wigenerik.decrypt(alphabet.creatingUkrAlphabet(), lolo, key);
        System.out.println(lolol);

        System.out.println(wigenrForUkr.encryptedMessage);

        ArrayList<String> ukrAlp = alphabet.creatingUkrAlphabet();

        boolean isEnough = true;
        while (isEnough) {
        HashMap<String, Integer>[] frequency = hardcoding.frequency(wigenrForUkr.encryptedMessage);
        double[] alpFrequency = alphabet.getUkrAlphFrequency();
        frequencyChart.displayFrequencyChart(alpFrequency, ukrAlp, "Частота символів алфавіту (Українського)");
        ArrayList<Integer> shifts = new ArrayList<>();
        ArrayList<String> variants = new ArrayList<>();
        for (int i = 0; i < frequency.length; i++) {
            double[] alphFrequency = hardcoding.calculateLetterFrequency(new HashMap[]{frequency[i]}, ukrAlp);
            double[] updatedFrequency = frequencyChart.addMissingLetters(alphFrequency, ukrAlp);
            shifts.add(hardcoding.findBestShift(alpFrequency, updatedFrequency));

            // Знаходимо найкращий зсув для поточної колонки
            char bestShift = ShiftFinderForUkr.findBestShiftUkr(alphFrequency, alpFrequency);

            // Виводимо результат для поточної колонки
            frequencyChart.displayFrequencyChart(updatedFrequency, ukrAlp, "Частота символів алфавіту (Колонка " + (i + 1) + ", Найкращий зсув: " + ukrAlp.indexOf(String.valueOf(bestShift).toUpperCase()) + ")");
            variants.add(String.valueOf(bestShift));
        }
            String keyword = "";
            for (int i = 0; i < shifts.size(); i++) {
                keyword += cesar.decrypt(shifts.get(i), variants.get(i), ukrAlp);
            }

            System.out.println("Ключове слово: " + keyword.toUpperCase());
            String decryptedMessage = wigenerik.decrypt(ukrAlp, wigenrForUkr.encryptedMessage, keyword.toUpperCase());
            System.out.println(decryptedMessage);
            System.out.print("Правильно розшифрувало? (так/ні) ");
            String answer = scanner.nextLine();
            if (answer.equals("так")) {
                isEnough = false;
            } else if (answer.equals("ні")) {
                System.out.println("Продовжуємо далі");
                }
            }
        }
    }