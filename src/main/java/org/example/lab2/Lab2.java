package org.example.lab2;

import org.example.lab1.Alphabet;
import org.example.lab1.Cesar;
import org.example.lab1.FrequencyChart;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import static org.example.lab2.FrequencyCalculator.calculateFrequencies;

public class Lab2 {
    public String text = "ZJYGIGYMNCCACCOXMBLVSWZSZJYGISFVGWHGYIRAUUKWAFRJVPLTSVEKOXCGYIRKCENSRKSFKMLDWSPSUWLCCSILCGYIRKIYKMMSHRCCYGIJZPLDCFVCOMKVCPHSJRESFJWREHSOVBNMLDGUFACGBPVVNXCEPSUJKRCPBWWAXQOJSBMIRATNKVAYSQPFEXOYCWYGIVEWTWOQFJFWSYZRGUCATIRFSQFXHWFFDMGZHRMINKSPIITDMEVNOAQRSCCGBGIESLWSRRIFRVMMDMOYZWCGACRWSACARXESBQRPTJIVJXIUOAULAKHUVMNLSEVWTKCSFXHWFFZRMABQKLEFWEIISHSPKMVWCSNLELVRIXHSHCVVSGBXESWKOYFXOXDRFTLWKUVVENSEKLALDRIWOFABMISZSBIWHWKVCPIEARUMALSYPQACSSIMEFRFRRDOVREXHSHCVVSGBSRGEKOGIEGWRLKLEJSJZPLTSCCINLMBWTEGDYVAHGKVCPCGARKSHWZC";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Hardcoding hardcoding = new Hardcoding();
        Alphabet alphabet = new Alphabet();
        FrequencyChart frequencyChart = new FrequencyChart();
        Cesar cesar = new Cesar();
        Wigenerik wigenerik = new Wigenerik();
        String encryptedMessage = "ZJYGIGYMNCCACCOXMBLVSWZSZJYGISFVGWHGYIRAUUKWAFRJVPLTSVEKOXCGYIRKCENSRKSFKMLDWSPSUWLCCSILCGYIRKIYKMMSHRCCYGIJZPLDCFVCOMKVCPHSJRESFJWREHSOVBNMLDGUFACGBPVVNXCEPSUJKRCPBWWAXQOJSBMIRATNKVAYSQPFEXOYCWYGIVEWTWOQFJFWSYZRGUCATIRFSQFXHWFFDMGZHRMINKSPIITDMEVNOAQRSCCGBGIESLWSRRIFRVMMDMOYZWCGACRWSACARXESBQRPTJIVJXIUOAULAKHUVMNLSEVWTKCSFXHWFFZRMABQKLEFWEIISHSPKMVWCSNLELVRIXHSHCVVSGBXESWKOYFXOXDRFTLWKUVVENSEKLALDRIWOFABMISZSBIWHWKVCPIEARUMALSYPQACSSIMEFRFRRDOVREXHSHCVVSGBSRGEKOGIEGWRLKLEJSJZPLTSCCINLMBWTEGDYVAHGKVCPCGARKSHWZC";

        ArrayList<String> enAlp = alphabet.creatingEngAlphabet();

        String lol = scanner.nextLine();
        String key = "LEMON";
        String lolo = wigenerik.encrypt(alphabet.creatingEngAlphabet(), lol, key);
        System.out.println(lolo);
        String lolol = wigenerik.decrypt(alphabet.creatingEngAlphabet(), lolo, key);
        System.out.println(lolol);
        double[] observedFrequencies = calculateFrequencies(encryptedMessage, enAlp);
//        double totalObserved = Arrays.stream(observedFrequencies).sum();
//        for (int i = 0; i < observedFrequencies.length; i++) {
//            observedFrequencies[i] /= totalObserved;
//        }
//        double[] expectedFrequencies = alphabet.getEngAlphFrequency();
//        int vigenereKeyLength = findVigenereKeyLength(observedFrequencies, expectedFrequencies);
//
//        System.out.println(vigenereKeyLength);

        System.out.println(encryptedMessage);
        HashMap<String, Integer>[] frequency = hardcoding.frequency(encryptedMessage);
        double[] alpFrequency = alphabet.getEngAlphFrequency();
        frequencyChart.displayFrequencyChart(alpFrequency, enAlp, "Частота символів алфавіту (Англійська)");
        ArrayList<Integer> shifts = new ArrayList<>();
        ArrayList<String> variants = new ArrayList<>();
        for (int i = 0; i < frequency.length; i++) {
            double[] alphFrequency = hardcoding.calculateLetterFrequency(new HashMap[]{frequency[i]}, enAlp);
            double[] updatedFrequency = frequencyChart.addMissingLetters(alphFrequency, enAlp);
            shifts.add(hardcoding.findBestShifts(alpFrequency, updatedFrequency));

            // Знаходимо найкращий зсув для поточної колонки
       //     char bestShift = ColumnShiftFinder.findBestShift(alphFrequency, alpFrequency);

            // Виводимо результат для поточної колонки
           // frequencyChart.displayFrequencyChart(updatedFrequency, enAlp, "Частота символів алфавіту (Колонка " + (i + 1) + ", Найкращий зсув: " + enAlp.indexOf(String.valueOf(bestShift).toUpperCase()) + ")");
       //     variants.add(String.valueOf(bestShift));
        }

        String keyword = "";
        for (int i = 0; i < shifts.size(); i++) {
            keyword += cesar.decrypt(shifts.get(i), variants.get(i), enAlp);
        }

        System.out.println("Ключове слово: " + keyword.toUpperCase());
        String decryptedMessage = wigenerik.decrypt(enAlp, encryptedMessage, keyword.toUpperCase());
        System.out.println(decryptedMessage);
        scanner.close();
    }
}