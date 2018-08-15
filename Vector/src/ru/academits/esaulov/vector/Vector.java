package ru.academits.esaulov.vector;

import java.util.Arrays;

public class Vector {
    private double[] coordinates;

    public Vector(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("не верная размернасть");
        }

        coordinates = new double[n];
    }

    public Vector(Vector v) {
        coordinates = Arrays.copyOf(v.coordinates, v.coordinates.length);
    }

    public Vector(double[] array) {
        if (array.length == 0) {
            throw new IllegalArgumentException("передан вектор нулевой длины");
        }
        coordinates = Arrays.copyOf(array, array.length);
    }

    public Vector(int n, double[] array) {
        if (n <= 0) {
            throw new IllegalArgumentException("не верная размернасть");
        }
        coordinates = Arrays.copyOf(array, n);
    }

    public static Vector getSumVectors(Vector vector1, Vector vector2) {
        Vector resultVector = new Vector(vector1);
        resultVector.sumVectors(vector2);
        return resultVector;
    }

    public static Vector getDifferenceVectors(Vector vector1, Vector vector2) {
        Vector resultVector = new Vector(vector1);
        resultVector.differenceVectors(vector2);
        return resultVector;
    }

    public static double getScalarMultiply(Vector vector1, Vector vector2) {
        double resultMultiply = 0;
        int minVectorLength = Math.min(vector1.coordinates.length, vector2.coordinates.length);
        for (int i = 0; i < minVectorLength; i++) {
            resultMultiply += vector1.coordinates[i] * vector2.coordinates[i];
        }
        return resultMultiply;
    }

    public void sumVectors(Vector vector) {
        if (this.coordinates.length < vector.coordinates.length) {
            this.coordinates = Arrays.copyOf(this.coordinates, vector.coordinates.length);
        }
        for (int i = 0; i < vector.coordinates.length; i++) {
            this.coordinates[i] += vector.coordinates[i];
        }
    }

    public void differenceVectors(Vector vector) {
        if (this.coordinates.length < vector.coordinates.length) {
            this.coordinates = Arrays.copyOf(this.coordinates, vector.coordinates.length);
        }
        for (int i = 0; i < vector.coordinates.length; i++) {
            this.coordinates[i] -= vector.coordinates[i];
        }
    }

    public void multiplyVectorByNumber(double number) {
        for (int i = 0; i < this.coordinates.length; i++) {
            this.coordinates[i] *= number;
        }
    }

    public void rotateVector() {
        this.multiplyVectorByNumber(-1);
    }

    public double getVectorLength() {
        double lengthVector = 0;
        for (double e : this.coordinates) {
            lengthVector += Math.pow(e, 2);
        }
        return Math.sqrt(lengthVector);
    }

    public void setCoordinateByIndex(int index, double value) {
        if (index < 0 || index >= this.coordinates.length) {
            throw new ArrayIndexOutOfBoundsException("не верный индекс");
        }
        this.coordinates[index] = value;
    }

    public double getCoordinateByIndex(int index) {
        if (index < 0 || index >= this.coordinates.length) {
            throw new ArrayIndexOutOfBoundsException("не верный индекс");
        }
        return this.coordinates[index];
    }

    public int getSize() {
        return this.coordinates.length;
    }

    @Override
    public String toString() {
        if (this.coordinates.length == 1) {
            return "{" + coordinates[0] + "}";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        for (int i = 0; i < this.coordinates.length - 1; i++) {
            sb.append(coordinates[i]).append(",");
        }
        return sb.append(coordinates[this.coordinates.length - 1]).append("}").toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }
        Vector vector = (Vector) obj;
        if (this.coordinates.length != vector.coordinates.length) {
            return false;
        }
        for (int i = 0; i < this.coordinates.length; i++) {
            if (this.coordinates[i] != vector.coordinates[i]) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {

        return Arrays.hashCode(this.coordinates);
    }
}
