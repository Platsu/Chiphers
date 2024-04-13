package org.example.lab1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Alphabet extends ArrayList<String> {

    public List<String> ukrAlph;
    public List<String> engAlph;

    public Alphabet() {
            ukrAlph = Arrays.asList(
                    "А", "Б", "В", "Г", "Ґ", "Д", "Е", "Є", "Ж", "З",
                    "И", "І", "Ї", "Й", "К", "Л", "М", "Н", "О", "П",
                    "Р", "С", "Т", "У", "Ф", "Х", "Ц", "Ч", "Ш", "Щ",
                    "Ь", "Ю", "Я"
            );

        engAlph = Arrays.asList(
                "A", "B", "C", "D", "E", "F", "G", "H", "I", "J",
                "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T",
                "U", "V", "W", "X", "Y", "Z"
        );
    }
    public ArrayList<String> creatingUkrAlphabet() {
        return new ArrayList<>(ukrAlph);
    }

    public ArrayList<String> creatingEngAlphabet() {
        return new ArrayList<>(engAlph);
    }


    double[] ukrAlphFrequency = {0.0834, 0.0153, 0.055, 0.013, 0.0009, 0.0306, 0.0459, 0.0039, 0.0071, 0.021, 0.06, 0.0623, 0.0084, 0.0124, 0.04, 0.0393, 0.0302, 0.0701, 0.0928, 0.0284, 0.0548, 0.0457, 0.0477, 0.0338, 0.0035, 0.0117, 0.0102, 0.0115, 0.0071, 0.0032, 0.0183, 0.007, 0.0216};
    double[] engAlphFrequency = {0.0855, 0.016, 0.0316, 0.0387, 0.121, 0.0218, 0.0209, 0.0496, 0.0733, 0.022, 0.081, 0.0421, 0.0253, 0.0717, 0.0747, 0.0207, 0.001, 0.0633, 0.0673, 0.0894, 0.0268, 0.0106, 0.0183, 0.0019, 0.0172, 0.0011};

    ArrayList<Double> friedmanIndexIdentityForEng = new ArrayList<>(
            Arrays.asList(0.0667, 0.0525, 0.0478, 0.0445, 0.0441, 0.0431, 0.0424, 0.041, 0.0407));

    ArrayList<Double> friedmanIndexIdentityForUkr = new ArrayList<>(
            Arrays.asList(0.0667, 0.0525, 0.0478, 0.0445, 0.0441, 0.0431, 0.0424, 0.041, 0.0407));

    public ArrayList<Double> getfriedmanIndexIdentityForEng() {
        return friedmanIndexIdentityForEng;
    }
    public ArrayList<Double> getfriedmanIndexIdentityForUkr() {
        return friedmanIndexIdentityForUkr;
    }
    public double[] getEngAlphFrequency() {
        return engAlphFrequency;
    }

    public double[] getUkrAlphFrequency() {
        return ukrAlphFrequency;
    }
}