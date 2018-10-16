package ru.academits.esaulov.matrix;

import ru.academits.esaulov.vector.Vector;


public class Matrix {
    private Vector[] rows;

    public Matrix(int rowsNumber, int columnsNumber) {
        if (rowsNumber < 1 || columnsNumber < 1) {
            throw new IllegalArgumentException("аргумент матрицы меньше единицы");
        }

        rows = new Vector[rowsNumber];
        for (int i = 0; i < rowsNumber; i++) {
            rows[i] = new Vector(columnsNumber);
        }
    }

    public Matrix(Vector[] vectorsArray) {
        if (vectorsArray.length == 0) {
            throw new IllegalArgumentException("передан массив 0 длины");
        }

        int rowsCount = vectorsArray.length;
        rows = new Vector[rowsCount];
        int maxLengthVector = 0;

        for (Vector e : vectorsArray) {
            maxLengthVector = (e.getSize() > maxLengthVector) ? e.getSize() : maxLengthVector;
        }

        for (int i = 0; i < rowsCount; i++) {
            int coordinatesVectorNumber = vectorsArray[i].getSize();
            double[] coordinates = new double[coordinatesVectorNumber];

            for (int m = 0; m < coordinatesVectorNumber; m++) {
                coordinates[m] = vectorsArray[i].getCoordinateByIndex(m);
            }
            rows[i] = new Vector(maxLengthVector, coordinates);
        }
    }

    public Matrix(double[][] arrayOfArrays) {
        if (arrayOfArrays.length == 0) {
            throw new IllegalArgumentException("массив нулевой длины");
        }
        if (arrayOfArrays[0].length == 0) {
            throw new IllegalArgumentException("строка нулевой длины");
        }

        int rowsNumber = arrayOfArrays.length;
        rows = new Vector[rowsNumber];
        int maxLengthArray = 0;

        for (double[] e : arrayOfArrays) {
            maxLengthArray = (e.length > maxLengthArray) ? e.length : maxLengthArray;
        }
        for (int i = 0; i < rowsNumber; i++) {
            rows[i] = new Vector(maxLengthArray, arrayOfArrays[i]);
        }
    }

    public Matrix(Matrix matrix) {
        rows = new Vector[matrix.rows.length];
        for (int i = 0; i < matrix.rows.length; i++) {
            this.rows[i] = new Vector(matrix.rows[i]);
        }
    }

    public int getRowsCount() {
        return rows.length;
    }

    public int getColumnsCount() {
        return rows[0].getSize();
    }

    public Vector getRow(int index) {
        if (index < 0 || index >= rows.length) {
            throw new IndexOutOfBoundsException("не верный индекс");
        }
        return new Vector(rows[index]);
    }

    public void setRow(int index, Vector vector) {
        if (index < 0 || index >= rows.length) {
            throw new IndexOutOfBoundsException("не верный индекс");
        }
        int countRow = getColumnsCount();

        if (vector.getSize() != countRow) {
            throw new IllegalArgumentException("размерность вектора не подходит к размерности матрицы");
        }

        double[] coordinates = new double[countRow];
        for (int i = 0; i < countRow; i++) {
            coordinates[i] = vector.getCoordinateByIndex(i);
        }
        rows[index] = new Vector(coordinates);
    }

    public Vector getColumn(int index) {
        if (index < 0 || index >= this.getColumnsCount()) {
            throw new IndexOutOfBoundsException("не верный индекс");
        }
        double[] coordinates = new double[rows.length];
        for (int i = 0; i < rows.length; i++) {
            coordinates[i] = rows[i].getCoordinateByIndex(index);
        }
        return new Vector(coordinates);
    }

    public void setColumn(int index, Vector vector) {
        if (index < 0 || index >= this.getColumnsCount()) {
            throw new IndexOutOfBoundsException("не верный индекс");
        }
        int countColumn = rows.length;

        if (vector.getSize() != countColumn) {
            throw new IllegalArgumentException("размерность вектора не подходит к размерности матрицы");
        }

        for (int i = 0; i < rows.length; i++) {
            rows[i].setCoordinateByIndex(index, vector.getCoordinateByIndex(i));
        }
    }

    public void transposeMatrix() {
        int columnCountOld = getColumnsCount();
        Vector[] arrayVector = new Vector[columnCountOld];
        for (int i = 0; i < columnCountOld; i++) {
            arrayVector[i] = getColumn(i);
        }
        rows = arrayVector;
    }

    public void multiplicationByScalar(double number) {
        for (Vector v : rows) {
            v.multiplyVectorByNumber(number);
        }
    }

    public Vector multiplicationMatrixByVector(Vector vector) {
        int columnMatrixCount = getColumnsCount();
        if (columnMatrixCount != vector.getSize()) {
            throw new IllegalArgumentException("перемножение не допустимо");
        }
        Vector result = new Vector(columnMatrixCount);
        for (int i = 0; i < columnMatrixCount; i++) {
            result.setCoordinateByIndex(i, Vector.getScalarMultiply(getRow(i), vector));
        }
        return result;
    }


    public void sumMatrix(Matrix matrix) {
        if (rows.length != matrix.rows.length || getColumnsCount() != matrix.getColumnsCount()) {
            throw new IllegalArgumentException("размерность матриц отличается");
        }
        for (int i = 0; i < this.rows.length; i++) {
            this.rows[i].sumVectors(matrix.rows[i]);
        }
    }

    public void differenceMatrix(Matrix matrix) {
        if (rows.length != matrix.rows.length || getColumnsCount() != matrix.getColumnsCount()) {
            throw new IllegalArgumentException("размерность матриц отличается");
        }
        for (int i = 0; i < this.rows.length; i++) {
            this.rows[i].differenceVectors(matrix.rows[i]);
        }
    }

    public static Matrix getSumMatrix(Matrix matrix1, Matrix matrix2) {
        if (matrix1.rows.length != matrix2.rows.length || matrix1.getColumnsCount() != matrix2.getColumnsCount()) {
            throw new IllegalArgumentException("размерность матриц отличается");
        }
        Matrix resultMatrix = new Matrix(matrix1);
        resultMatrix.sumMatrix(matrix2);
        return resultMatrix;
    }

    public static Matrix getDifferenceMatrix(Matrix matrix1, Matrix matrix2) {
        if (matrix1.rows.length != matrix2.rows.length || matrix1.getColumnsCount() != matrix2.getColumnsCount()) {
            throw new IllegalArgumentException("размерность матриц отличается");
        }
        Matrix resultMatrix = new Matrix(matrix1);
        resultMatrix.differenceMatrix(matrix2);
        return resultMatrix;
    }

    public static Matrix getMultiplyMatrix(Matrix matrix1, Matrix matrix2) {
        if (matrix1.getColumnsCount() != matrix2.rows.length) {
            throw new IllegalArgumentException("размерность матриц отличается");
        }
        int rowMatrix1Count = matrix1.rows.length;
        int columnMatrix2Count = matrix2.getColumnsCount();
        Matrix resultMatrix = new Matrix(rowMatrix1Count, columnMatrix2Count);
        for (int i = 0; i < rowMatrix1Count; i++) {
            for (int j = 0; j < columnMatrix2Count; j++) {
                resultMatrix.rows[i].setCoordinateByIndex(j, Vector.getScalarMultiply(matrix1.getRow(i),
                        matrix2.getColumn(j)));
            }
        }
        return resultMatrix;
    }

    public double determinant() {
        int rowMatrixNumber = rows.length;
        int columnMatrixNumber = getColumnsCount();
        if (rowMatrixNumber != columnMatrixNumber) {
            throw new ArithmeticException("матрица должна быть квадратной");
        }
        if (rowMatrixNumber == 1) {
            return rows[0].getCoordinateByIndex(0);
        }

        double determinant = 0;
        int decayLine = 0;
        for (int i = 0; i < rowMatrixNumber; i++) {
            Matrix minorMatrix = new Matrix(rowMatrixNumber - 1, rowMatrixNumber - 1);
            for (int s = 0; s < rowMatrixNumber - 1; s++) {
                for (int c = 0; c < rowMatrixNumber - 1; c++) {
                    if (i <= c) {
                        minorMatrix.getRow(s).setCoordinateByIndex(c, rows[s + 1].getCoordinateByIndex(c + 1));
                    } else {
                        minorMatrix.getRow(s).setCoordinateByIndex(c, rows[s + 1].getCoordinateByIndex(c));
                    }
                }
            }
            determinant += rows[decayLine].getCoordinateByIndex(i) * Math.pow(-1, i + decayLine) * minorMatrix.determinant();
        }
        return determinant;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Матрица: " + rows.length + "x" + rows[0].getSize());
        sb.append(System.lineSeparator());
        sb.append("{");
        for (int i = 0; i < rows.length - 1; i++) {
            sb.append(rows[i]).append(",");
        }
        sb.append(rows[rows.length - 1]);
        sb.append("}");
        return sb.toString();
    }
}
