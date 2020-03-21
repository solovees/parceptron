package utils;

import domain.InputVector;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Класс для поиска весов парцептрона
 */
public class WeightsFinder {

    // скорость обучения
    private static final double LEARN_SPEED = 0.25;
    // контрольные значения с ответами
    private final Map<InputVector, Double> sampleValues;
    // обучающая выборка
    private final static List<InputVector> learnValues = Arrays.asList(
            new InputVector(0.0, 0.0),
            new InputVector(0.0, 1.0),
            new InputVector(1.0, 0.0),
            new InputVector(1.0, 1.0));

    public WeightsFinder() {
        this.sampleValues = new HashMap<>();
        fillSamples();
    }

    /**
     * Корректирует веса ребер, чтобы они соответсвовали логическому ИЛИ
     *
     * @param weightFirst  стартовый вес первого ребра
     * @param weightSecond стартовый вес второго ребра
     */
    public double[] correctWeightsForOR(double weightFirst, double weightSecond) {
        boolean weightsIncorrect = true;
        int epoch = 1;
        while (weightsIncorrect) {
            System.out.println("Эпоха №: " + epoch);
            for (int iteration = 0; iteration < learnValues.size(); iteration++) {
                System.out.println("Итерация №: " + iteration);
                InputVector inputVector = learnValues.get(iteration);
                double x1 = inputVector.getX1();
                double x2 = inputVector.getX2();
                double sigmoid = count(weightFirst, weightSecond, x1, x2);
                Double sample = sampleValues.get(inputVector);
                System.out.println(inputVector.toString());
                System.out.println("Веса: " + "w1=" + weightFirst + ", w2=" + weightSecond);
                System.out.println("Результат вычисления: " + Math.round(sigmoid));
                double deltaWeightX1 = -(LEARN_SPEED * (sigmoid - sample) * sigmoid * (Math.abs(1.0 - sigmoid)) * x1);
                double deltaWeightX2 = -(LEARN_SPEED * (sigmoid - sample) * sigmoid * (Math.abs(1.0 - sigmoid)) * x2);
                weightFirst = weightFirst + deltaWeightX1;
                weightSecond = weightSecond + deltaWeightX2;
                System.out.println("deltaWeightX1: " + deltaWeightX1);
                System.out.println("deltaWeightX2: " + deltaWeightX2);
            }
            epoch++;
            weightsIncorrect = isWeightsIncorrect(weightFirst, weightSecond, sampleValues);
            System.out.println("____________________Конец эпохи____________________");
        }
        return new double[]{weightFirst, weightSecond};
    }

    private boolean isWeightsIncorrect(double weightFirst, double weightSecond, Map<InputVector, Double> sampleValues) {
        long errors = sampleValues.entrySet()
                .stream()
                .filter(entry -> entry.getValue() != Math.round(count(weightFirst, weightSecond, entry.getKey().getX1(), entry.getKey().getX2())))
                .count();
        System.out.println("Суммарная ошибка: " + errors);
        return errors > 0;
    }

    private double count(double weightFirst, double weightSecond, double x1, double x2) {
        return x1 * weightFirst + x2 * weightSecond;
    }

    private void fillSamples() {
        sampleValues.put(new InputVector(0.0, 0.0), 0.0);
        sampleValues.put(new InputVector(0.0, 1.0), 1.0);
        sampleValues.put(new InputVector(1.0, 0.0), 1.0);
        sampleValues.put(new InputVector(1.0, 1.0), 1.0);
    }
}
