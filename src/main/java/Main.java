import utils.WeightsFinder;

import java.util.StringJoiner;

public class Main {

    public static void main(String args[]) {
        double weightFirst = Double.parseDouble(args[0]);
        double weightSecond = Double.parseDouble(args[1]);
        System.out.println(new StringJoiner("\n")
                .add("Начальные значения:")
                .add("w1=" + weightFirst)
                .add("w2=" + weightSecond)
                .add("_____________________________")
                .toString()
        );
        double[] result = new WeightsFinder().correctWeightsForOR(weightFirst, weightSecond);
        System.out.println(new StringJoiner("\n")
                .add("Результат:")
                .add("w1=" + result[0])
                .add("w2=" + result[1])
                .toString()
        );
    }

}

