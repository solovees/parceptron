package domain;

import java.util.Objects;

/**
 * Вектор входных данных нейрона
 */
public class InputVector {

    private double x1;
    private double x2;

    public InputVector(double x1, double x2) {
        this.x1 = x1;
        this.x2 = x2;
    }

    public double getX1() {
        return x1;
    }

    public void setX1(double x1) {
        this.x1 = x1;
    }

    public double getX2() {
        return x2;
    }

    public void setX2(double x2) {
        this.x2 = x2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InputVector that = (InputVector) o;
        return Double.compare(that.x1, x1) == 0 &&
                Double.compare(that.x2, x2) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x1, x2);
    }

    @Override
    public String toString() {
        return "Входные данные " +
                "x1=" + x1 +
                ", x2=" + x2;
    }
}
