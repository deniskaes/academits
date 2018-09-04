package ru.academits.esaulov.matrix;

import ru.academits.esaulov.vector.Vector;

import java.util.Arrays;

public class Matrix {
    private Vector[] matrix;

    public Matrix(int numberStrings, int numberColumns) {
        if (numberStrings <= 0 || numberColumns <= 0) {
            throw new IllegalArgumentException("не верный аргумент");
        }
        matrix = new Vector[numberStrings];
        for (int i = 0; i < numberStrings; i++) {
            matrix[i] = new Vector(numberColumns);
        }
    }

    public Matrix(Vector[] vectorsArray) {
        matrix = Arrays.copyOf(vectorsArray, vectorsArray.length);
    }

    public Matrix(double[][] arrayOfArrays) {
        for (int i = 0; i < arrayOfArrays.length; i++) {
            matrix[i] = new Vector(arrayOfArrays[i]);
        }
    }

    public Matrix(Matrix matrix) {
        this.matrix = matrix.matrix;
    }

    public int[] getDimension() {
        int[] dimension = new int[2];
        dimension[0] = matrix.length;
        dimension[1] = matrix[0].getSize();
        return dimension;
    }

    public Vector getStringVector(int index) {
        if (index < 0 || index > matrix.length) {
            throw new IllegalArgumentException("не верный индекс");
        }
        return matrix[index];
    }

    public void setStringVector(int index, Vector vector) {
        if (index < 0 || index > matrix.length) {
            throw new IllegalArgumentException("не верный индекс");
        }
//        matrix[index] = Arrays.copyOf();

    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Матрица: " + matrix.length + "x" + matrix[0].getSize());
        sb.append(System.lineSeparator());
        sb.append("{");
        for (int i = 0; i < matrix.length; i++) {
            sb.append(matrix[i]);
        }
        sb.append("}");
        return sb.toString();
    }
}
