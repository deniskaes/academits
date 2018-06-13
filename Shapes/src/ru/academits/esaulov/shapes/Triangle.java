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
        sideA = Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
        sideB = Math.sqrt(Math.pow(x3 - x2, 2) + Math.pow(y3 - y2, 2));
        sideC = Math.sqrt(Math.pow(x3 - x1, 2) + Math.pow(y3 - y1, 2));
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
            double semiPerimeter = (sideA + sideB + sideC) / 2;
            return Math.sqrt(semiPerimeter * (semiPerimeter - sideA) * (semiPerimeter - sideB) * (semiPerimeter - sideC));
        }
    }

    @Override
    public double getPerimeter() {
        return sideA + sideB + sideC;
    }
}
