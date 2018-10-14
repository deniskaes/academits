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

        int rowsNumber = vectorsArray.length;
        rows = new Vector[rowsNumber];
        int maxLengthVector = 0;

        for (Vector e : vectorsArray) {
            maxLengthVector = (e.getSize() > maxLengthVector) ? e.getSize() : maxLengthVector;
        }

        for (int i = 0; i < rowsNumber; i++) {
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
        rows = new Vector[matrix.getRowCount()];
        for (int i = 0; i < matrix.rows.length; i++) {
            this.rows[i] = new Vector(matrix.rows[i]);
        }
    }

    public int getRowCount() {
        return rows.length;
    }

    public int getColumnCount() {
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
        int countRow = getColumnCount();

        if (vector.getSize() < countRow || vector.getSize() > countRow) {
            throw new IndexOutOfBoundsException("размерность вектора не подходит к размерности матрицы");
        }

        double[] coordinates = new double[countRow];
        for (int i = 0; i < countRow; i++) {
            coordinates[i] = vector.getCoordinateByIndex(i);
        }
        rows[index] = new Vector(coordinates);
    }

    public Vector getColumn(int index) {
        if (index < 0 || index >= this.getColumnCount()) {
            throw new IndexOutOfBoundsException("не верный индекс");
        }
        double[] coordinates = new double[rows.length];
        for (int i = 0; i < rows.length; i++) {
            coordinates[i] = rows[i].getCoordinateByIndex(index);
        }
        return new Vector(coordinates);
    }

    public void setColumn(int index, Vector vector) {
        if (index < 0 || index >= this.getColumnCount()) {
            throw new IndexOutOfBoundsException("не верный индекс");
        }
        int countColumn = getColumnCount();

        if (vector.getSize() > countColumn || vector.getSize() < countColumn) {
            throw new IndexOutOfBoundsException("размерность вектора не подходит к размерности матрицы");
        }

        for (int i = 0; i < rows.length; i++) {
            rows[i].setCoordinateByIndex(index, vector.getCoordinateByIndex(i));
        }
    }

    public void transposeMatrix() {
        int columnNumberOld = getColumnCount();
        Vector[] arrayVector = new Vector[columnNumberOld];
        for (int i = 0; i < columnNumberOld; i++) {
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
        int rowMatrixCount = getRowCount();
        if (rowMatrixCount != vector.getSize()) {
            throw new IndexOutOfBoundsException("перемножение не допустимо");
        }
        Vector result = new Vector(rowMatrixCount);
        for (int i = 0; i < rowMatrixCount; i++) {
            result.setCoordinateByIndex(i, Vector.getScalarMultiply(getColumn(i), vector));
        }
        return result;
    }


    public void sumMatrix(Matrix matrix) {
        if (getRowCount() != matrix.getRowCount() || getColumnCount() != matrix.getColumnCount()) {
            throw new IndexOutOfBoundsException("размерность матриц отличается");
        }
        for (int i = 0; i < this.rows.length; i++) {
            this.rows[i].sumVectors(matrix.getRow(i));
        }
    }

    public void differenceMatrix(Matrix matrix) {
        if (getRowCount() != matrix.getRowCount() || getColumnCount() != matrix.getColumnCount()) {
            throw new IndexOutOfBoundsException("размерность матриц отличается");
        }
        matrix.multiplicationByScalar(-1);
        this.sumMatrix(matrix);
    }

    public static Matrix getSumMatrix(Matrix matrix1, Matrix matrix2) {
        if (matrix1.getRowCount() != matrix2.getRowCount() || matrix1.getColumnCount() != matrix2.getColumnCount()) {
            throw new IndexOutOfBoundsException("размерность матриц отличается");
        }
        Matrix resultMatrix = new Matrix(matrix1);
        resultMatrix.sumMatrix(matrix2);
        return resultMatrix;
    }

    public static Matrix getDifferenceMatrix(Matrix matrix1, Matrix matrix2) {
        if (matrix1.getRowCount() != matrix2.getRowCount() || matrix1.getColumnCount() != matrix2.getColumnCount()) {
            throw new IndexOutOfBoundsException("размерность матриц отличается");
        }
        Matrix resultMatrix = new Matrix(matrix1);
        resultMatrix.differenceMatrix(matrix2);
        return resultMatrix;
    }

    public static Matrix getMultiplyMatrix(Matrix matrix1, Matrix matrix2) {
        if (matrix1.getColumnCount() != matrix2.getRowCount()) {
            throw new IndexOutOfBoundsException("размерность матриц отличается");
        }
        int rowMatrix1Count = matrix1.getRowCount();
        int columnMatrix2Count = matrix2.getColumnCount();
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
        int rowMatrixNumber = getRowCount();
        int columnMatrixNumber = getColumnCount();
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
