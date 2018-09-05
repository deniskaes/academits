package ru.academits.esaulov.matrix;

import ru.academits.esaulov.vector.Vector;

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
        int numberLinesMatrix = vectorsArray.length;
        matrix = new Vector[numberLinesMatrix];
        int maxLengthVector = 0;
        for (Vector e : vectorsArray) {
            maxLengthVector = (e.getSize() > maxLengthVector) ? e.getSize() : maxLengthVector;
        }
        for (int i = 0; i < numberLinesMatrix; i++) {
            int numberCoordinatesVector = vectorsArray[i].getSize();
            double[] coordinates = new double[numberCoordinatesVector];
            for (int m = 0; m < numberCoordinatesVector; m++) {
                coordinates[m] = vectorsArray[i].getCoordinateByIndex(m);
            }
            matrix[i] = new Vector(maxLengthVector, coordinates);
        }
    }

    public Matrix(double[][] arrayOfArrays) {
        for (int i = 0; i < arrayOfArrays.length; i++) {
            matrix[i] = new Vector(arrayOfArrays[i]);
        }
    }

    public Matrix(Matrix matrix) {
        for (int i = 0; i < matrix.matrix.length; i++) {
            this.matrix[i] = new Vector(matrix.matrix[i]);
        }
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

        int dimensionStringVector = getDimension()[1];
        double[] coordinates = new double[dimensionStringVector];
        for (int i = 0; i < dimensionStringVector; i++) {
            coordinates[i] = vector.getCoordinateByIndex(i);
        }
        matrix[index] = new Vector(coordinates);
    }

    public Vector getColumnVector(int index) {
        if (index < 0 || index > this.getDimension()[1]) {
            throw new IllegalArgumentException("не верный индекс");
        }
        double[] coordinates = new double[matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            coordinates[i] = matrix[i].getCoordinateByIndex(index);
        }
        return new Vector(coordinates);
    }

    public void setColumnVector(int index, Vector vector) {
        if (index < 0 || index > this.getDimension()[1]) {
            throw new IllegalArgumentException("не верный индекс");
        }
        for (int i = 0; i < matrix.length; i++) {
            matrix[i].setCoordinateByIndex(index, vector.getCoordinateByIndex(i));
        }
    }

    public void transposeMatrix() {
        int[] dimensionOldMatrix = getDimension();
        Matrix transposeMatrix = new Matrix(dimensionOldMatrix[1], dimensionOldMatrix[0]);
        for (int i = 0; i < dimensionOldMatrix[1]; i++) {
            transposeMatrix.setStringVector(i, getColumnVector(i));
        }
        matrix = transposeMatrix.matrix;
    }

    public void multiplicationByScalar(double number) {
        for (Vector v : matrix) {
            v.multiplyVectorByNumber(number);
        }
    }

    public void multiplicationMatrixByVector(Vector vector) {
        int numberColumnMatrix = getDimension()[1];
        if (numberColumnMatrix != vector.getSize()) {
            throw new IllegalArgumentException("перемножение не допустимо");
        }
        Vector result = new Vector(numberColumnMatrix);
        for (int i = 0; i < numberColumnMatrix; i++) {
            result.setCoordinateByIndex(i, Vector.getScalarMultiply(getStringVector(i), vector));
        }
        Matrix resultMatrix = new Matrix(numberColumnMatrix, 1);
        resultMatrix.setColumnVector(0, result);
        matrix = resultMatrix.matrix;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Матрица: " + matrix.length + "x" + matrix[0].getSize());
        sb.append(System.lineSeparator());
        sb.append("{");
        for (int i = 0; i < matrix.length - 1; i++) {
            sb.append(matrix[i]).append(",");
        }
        sb.append(matrix[matrix.length - 1]);
        sb.append("}");
        return sb.toString();
    }
}
