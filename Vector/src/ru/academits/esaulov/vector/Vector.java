package ru.academits.esaulov.vector;

import java.util.Arrays;

public class Vector {
    private double[] coordinates;
    private int dimension;

    public Vector(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("не верная размернасть");
        }
        dimension = n;
        coordinates = new double[dimension];
        Arrays.fill(coordinates, 0);
    }

    public Vector(Vector v) {
        dimension = v.getSize();
        coordinates = Arrays.copyOf(v.coordinates, dimension);
    }

    public Vector(double[] array) {
        dimension = array.length;
        coordinates = Arrays.copyOf(array, dimension);
    }

    public Vector(int n, double[] array) {
        if (n <= 0) {
            throw new IllegalArgumentException("не верная размернасть");
        }
        dimension = n;
        coordinates = Arrays.copyOf(array, dimension);
    }

    public static Vector getSumVectors(Vector vector1, Vector vector2) {
        if (vector1.coordinates.length < vector2.coordinates.length) {
            vector1.coordinates = Arrays.copyOf(vector1.coordinates, vector2.coordinates.length);
        }
        if (vector1.coordinates.length > vector2.coordinates.length) {
            vector2.coordinates = Arrays.copyOf(vector2.coordinates, vector1.coordinates.length);
        }
        double[] resultVectorCoordinates = new double[vector1.coordinates.length];
        for (int i = 0; i < vector1.coordinates.length; i++) {
            resultVectorCoordinates[i] = vector1.coordinates[i] + vector2.coordinates[i];
        }
        return new Vector(resultVectorCoordinates);
    }

    public static Vector getDifferenceVectors(Vector vector1, Vector vector2) {
        if (vector1.coordinates.length < vector2.coordinates.length) {
            vector1.coordinates = Arrays.copyOf(vector1.coordinates, vector2.coordinates.length);
        }
        if (vector1.coordinates.length > vector2.coordinates.length) {
            vector2.coordinates = Arrays.copyOf(vector2.coordinates, vector1.coordinates.length);
        }
        double[] resultVectorCoordinates = new double[vector1.coordinates.length];
        for (int i = 0; i < vector1.coordinates.length; i++) {
            resultVectorCoordinates[i] = vector1.coordinates[i] - vector2.coordinates[i];
        }
        return new Vector(resultVectorCoordinates);
    }

    public static double getScalarMultiply(Vector vector1, Vector vector2) {
        double resultMultiply = 0;
        for (int i = 0; i < Math.min(vector1.coordinates.length, vector2.coordinates.length); i++) {
            resultMultiply += vector1.coordinates[i] * vector2.coordinates[i];
        }
        return resultMultiply;
    }

    public void getSumVectors(Vector vector) {
        if (this.coordinates.length < vector.coordinates.length) {
            this.coordinates = Arrays.copyOf(this.coordinates, vector.coordinates.length);
        }
        if (this.coordinates.length > vector.coordinates.length) {
            vector.coordinates = Arrays.copyOf(vector.coordinates, this.coordinates.length);
        }

        for (int i = 0; i < this.coordinates.length; i++) {
            this.coordinates[i] = this.coordinates[i] + vector.coordinates[i];
        }
    }

    public void getDifferenceVectors(Vector vector) {
        if (this.coordinates.length < vector.coordinates.length) {
            this.coordinates = Arrays.copyOf(this.coordinates, vector.coordinates.length);
        }
        if (this.coordinates.length > vector.coordinates.length) {
            vector.coordinates = Arrays.copyOf(vector.coordinates, this.coordinates.length);
        }
        for (int i = 0; i < this.coordinates.length; i++) {
            this.coordinates[i] = this.coordinates[i] - vector.coordinates[i];
        }
    }

    public void getMultiplyVectorByNumber(double number) {
        for (int i = 0; i < this.coordinates.length; i++) {
            this.coordinates[i] = this.coordinates[i] * number;
        }
    }

    public void getRotateVector() {
        for (int i = 0; i < this.coordinates.length; i++) {
            this.coordinates[i] = this.coordinates[i] * -1;
        }
    }

    public double getVectorLength() {
        double lengthVector = 0;
        for (double e : this.coordinates) {
            lengthVector += Math.pow(e, 2);
        }
        return Math.sqrt(lengthVector);
    }

    public void insertValueByIndex(int index, double value) {
        if (index < 0 || index > this.coordinates.length - 1) {
            throw new IllegalArgumentException("не верный индекс");
        }
        this.coordinates[index] = value;
    }

    public double getCoordinateVectorByIndex(int index) {
        if (index < 0 || index > this.coordinates.length - 1) {
            throw new IllegalArgumentException("не верный индекс");
        }
        return this.coordinates[index];
    }

    private int getSize() {
        return dimension;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (dimension == 1) {
            return "{" + coordinates[0] + "}";
        }
        sb.append("{");
        for (int i = 0; ; i++) {
            if (i == dimension - 1) {
                return sb.append(coordinates[i]).append("}").toString();
            }
            sb.append(coordinates[i]).append(",");
        }
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
        double number = 0;
        for (double e : this.coordinates) {
            number += e * dimension;
        }
        return Double.hashCode(number);
    }
}
