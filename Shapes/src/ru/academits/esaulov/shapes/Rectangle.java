package ru.academits.esaulov.shapes;

public class Rectangle implements Shape {
    private double sideA;
    private double sideB;

    public Rectangle(double sideA, double sideB) {
        this.sideA = sideA;
        this.sideB = sideB;
    }

    @Override
    public double getWidth() {
        return sideA;
    }

    @Override
    public double getHeight() {
        return sideB;
    }

    @Override
    public double getArea() {
        return sideA * sideB;
    }

    @Override
    public double getPerimeter() {
        return (sideA + sideB) * 2;
    }

    @Override
    public String toString() {
        return String.format("[Rectangle area = %.4f, perimeter = %.4f]", getArea(), getPerimeter());
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o == null || o.getClass() != this.getClass()) {
            return false;
        }
        Rectangle rectangle = (Rectangle) o;
        return rectangle.sideA == this.sideA && rectangle.sideB == this.sideB;
    }

    @Override
    public int hashCode() {
        return Double.hashCode((sideA + sideB) * sideA);
    }
}

