package org.example.lab2;

public class Friedman {

    public static int findVigenereKeyLength(double[] observedFrequencies, double[] expectedFrequencies) {
        int maxLength = Math.min(observedFrequencies.length, expectedFrequencies.length);
        double maxFriedmanIndex = Double.MIN_VALUE;
        int vigenereKeyLength = 0;

        int totalObserved = 0;
        for (double frequency : observedFrequencies) {
            totalObserved += frequency;
        }

        for (int k = 1; k <= maxLength; k++) {
            double sumSquaredDiff = 0.0;

            // Розбиття observedFrequencies на k груп
            double[] observedFreqGrouped = new double[k];
            for (int i = 0; i < observedFrequencies.length; i++) {
                observedFreqGrouped[i % k] += observedFrequencies[i];
            }

            // Розрахунок індексу відповідності Фрідмана
            for (double frequency : observedFreqGrouped) {
                sumSquaredDiff += Math.pow(frequency / totalObserved - expectedFrequencies[(int)frequency], 2);
            }

            double friedmanIndex = sumSquaredDiff / expectedFrequencies.length;

            // Оновлення найбільшого значення індексу та відповідної довжини ключа
            if (friedmanIndex > maxFriedmanIndex) {
                maxFriedmanIndex = friedmanIndex;
                vigenereKeyLength = k;
            }
        }
        return vigenereKeyLength;
    }
}