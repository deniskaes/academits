package ru.academits.esaulov.shapes;

public class Triangle implements Shape {
    private double x1;
    private double x2;
    private double x3;
    private double y1;
    private double y2;
    private double y3;
    private double sideA;
    private double sideB;
    private double sideC;

    public Triangle(double x1, double x2, double x3, double y1, double y2, double y3) {
        this.x1 = x1;
        this.x2 = x2;
        this.x3 = x3;
        this.y1 = y1;
        this.y2 = y2;
        this.y3 = y3;
        sideA = getSideLength(x1, y1, x2, y2);
        sideB = getSideLength(x2, y2, x3, y3);
        sideC = getSideLength(x1, y1, x3, y3);
    }

    private double getSideLength(double x1, double y1, double x2, double y2) {
        return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }

    @Override
    public double getWidth() {
        return Math.max(Math.max(x1, x2), x3) - Math.min(Math.min(x1, x2), x3);
    }

    @Override
    public double getHeight() {
        return Math.max(Math.max(y1, y2), y3) - Math.min(Math.min(y1, y2), y3);
    }

    @Override
    public double getArea() {
        double epsilon = 0.001;
        if (Math.abs((x3 - x2) * (y2 - y1) - (x2 - x1) * (y3 - y2)) < epsilon) {
            return 0;
        } else {
            double semiPerimeter = getPerimeter() / 2;
            return Math.sqrt(semiPerimeter * (semiPerimeter - sideA) * (semiPerimeter - sideB) * (semiPerimeter - sideC));
        }
    }

    @Override
    public double getPerimeter() {
        return sideA + sideB + sideC;
    }

    @Override
    public String toString() {
        return String.format("[Triangle area = %.4f, perimeter = %.4f]", getArea(), getPerimeter());
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o == null || o.getClass() != this.getClass()) {
            return false;
        }
        Triangle triangle = (Triangle) o;
        return triangle.x1 == x1 && triangle.x2 == x2 && triangle.x3 == x3
                && triangle.y1 == y1 && triangle.y2 == y2 && triangle.y3 == y3;
    }

    @Override
    public int hashCode() {
        return Double.hashCode((x1 + x2 + x3) * (y1 + y2 + y3));
    }
}

