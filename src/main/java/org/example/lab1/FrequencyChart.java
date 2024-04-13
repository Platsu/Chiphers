package org.example.lab1;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.util.ArrayList;


public class FrequencyChart {

    public void displayFrequencyChart(double[] frequency, ArrayList<String> alphabet, String title) {
        // Оновлення частоти з включенням невикористаних літер
        double[] updatedFrequency = addMissingLetters(frequency, alphabet);

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        // Додавання частоти кожної літери в датасет
        for (int i = 0; i < alphabet.size(); i++) {
            String letter = alphabet.get(i);
            dataset.addValue(updatedFrequency[i], "Крипто Аналіз", letter);
        }

        // Створення графіка з заголовком
        JFreeChart barChart = ChartFactory.createBarChart(
                title, // Заголовок графіка
                "символи алфавіту",            // Вісь X
                "частота",         // Вісь Y
                dataset
        );

        // Відображення графіка у вікні
        ChartPanel chartPanel = new ChartPanel(barChart);
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(chartPanel);
        frame.pack();
        frame.setVisible(true);
    }

    public double[] addMissingLetters(double[] frequencies, ArrayList<String> alphabet) {
        double[] updatedFrequencies = new double[alphabet.size()];

        // Заповнення масиву нульовими значеннями
        for (int i = 0; i < alphabet.size(); i++) {
            updatedFrequencies[i] = 0.0;
        }

        // Оновлення значень для літер, які мають відомі частоти
        for (int i = 0; i < alphabet.size(); i++) {
            String letter = alphabet.get(i);
            for (int j = 0; j < frequencies.length; j++) {
                if (letter.equalsIgnoreCase(alphabet.get(j))) {
                    updatedFrequencies[i] = frequencies[j];
                    break;
                }
            }
        }

        return updatedFrequencies;
    }

}