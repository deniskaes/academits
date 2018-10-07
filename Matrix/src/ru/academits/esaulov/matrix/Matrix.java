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
        rows = new Vector[matrix.getRowDimension()];
        for (int i = 0; i < matrix.rows.length; i++) {
            this.rows[i] = new Vector(matrix.rows[i]);
        }
    }

    public int getRowDimension() {
        return rows.length;
    }

    public int getColumnDimension() {
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
        int dimensionRow = getColumnDimension();

        if (vector.getSize() < dimensionRow) {
            throw new IndexOutOfBoundsException("размерность вектора больше размерности матрицы");
        }

        double[] coordinates = new double[dimensionRow];
        for (int i = 0; i < dimensionRow; i++) {
            coordinates[i] = vector.getCoordinateByIndex(i);
        }
        rows[index] = new Vector(coordinates);
    }

    public Vector getColumn(int index) {
        if (index < 0 || index >= this.getColumnDimension()) {
            throw new IndexOutOfBoundsException("не верный индекс");
        }
        double[] coordinates = new double[rows.length];
        for (int i = 0; i < rows.length; i++) {
            coordinates[i] = rows[i].getCoordinateByIndex(index);
        }
        return new Vector(coordinates);
    }

    public void setColumn(int index, Vector vector) {
        if (index < 0 || index >= this.getColumnDimension()) {
            throw new IndexOutOfBoundsException("не верный индекс");
        }
        int dimensionColumn = getColumnDimension();

        if (vector.getSize() > dimensionColumn) {
            throw new IndexOutOfBoundsException("размерность вектора больше размерности матрицы");
        }

        for (int i = 0; i < rows.length; i++) {
            rows[i].setCoordinateByIndex(index, vector.getCoordinateByIndex(i));
        }
    }

    public void transposeMatrix() {
        int columnNumberOld = getColumnDimension();
        Vector[] arrayVector = new Vector[columnNumberOld];
        for (int i = 0; i < columnNumberOld; i++) {
            arrayVector[i] = new Vector(getColumn(i));
        }
        rows = arrayVector;
    }

    public void multiplicationByScalar(double number) {
        for (Vector v : rows) {
            v.multiplyVectorByNumber(number);
        }
    }

    public Vector multiplicationMatrixByVector(Vector vector) {
        int columnMatrixNumber = getColumnDimension();
        if (columnMatrixNumber != vector.getSize()) {
            throw new IndexOutOfBoundsException("перемножение не допустимо");
        }
        Vector result = new Vector(columnMatrixNumber);
        for (int i = 0; i < columnMatrixNumber; i++) {
            result.setCoordinateByIndex(i, Vector.getScalarMultiply(getRow(i), vector));
        }
        return result;
    }

    public void sumMatrix(Matrix matrix) {
        if (getRowDimension() != matrix.getRowDimension() || getColumnDimension() != matrix.getColumnDimension()) {
            throw new IndexOutOfBoundsException("размерность матриц отличается");
        }
        for (int i = 0; i < this.rows.length; i++) {
            this.rows[i] = Vector.getSumVectors(this.rows[i], matrix.getRow(i));
        }
    }

    public void differenceMatrix(Matrix matrix) {
        if (getRowDimension() != matrix.getRowDimension() || getColumnDimension() != matrix.getColumnDimension()) {
            throw new IndexOutOfBoundsException("размерность матриц отличается");
        }
        matrix.multiplicationByScalar(-1);
        this.sumMatrix(matrix);
    }

    public static Matrix getSumMatrix(Matrix matrix1, Matrix matrix2) {
        if (matrix1.getRowDimension() != matrix2.getRowDimension() || matrix1.getColumnDimension() != matrix2.getColumnDimension()) {
            throw new IndexOutOfBoundsException("размерность матриц отличается");
        }
        Matrix resultMatrix = new Matrix(matrix1);
        resultMatrix.sumMatrix(matrix2);
        return resultMatrix;
    }

    public static Matrix getDifferenceMatrix(Matrix matrix1, Matrix matrix2) {
        if (matrix1.getRowDimension() != matrix2.getRowDimension() || matrix1.getColumnDimension() != matrix2.getColumnDimension()) {
            throw new IndexOutOfBoundsException("размерность матриц отличается");
        }
        Matrix resultMatrix = new Matrix(matrix1);
        resultMatrix.differenceMatrix(matrix2);
        return resultMatrix;
    }

    public static Matrix getMultiplyMatrix(Matrix matrix1, Matrix matrix2) {
        if (matrix1.getColumnDimension() != matrix2.getRowDimension()) {
            throw new IndexOutOfBoundsException("размерность матриц отличается");
        }
        int rowMatrix2Number = matrix2.getRowDimension();
        int columnMatrix2Number = matrix2.getColumnDimension();
        Matrix resultMatrix = new Matrix(rowMatrix2Number, columnMatrix2Number);
        for (int i = 0; i < rowMatrix2Number; i++) {
            for (int m = 0; m < columnMatrix2Number; m++) {
                resultMatrix.rows[i].setCoordinateByIndex(m, Vector.getScalarMultiply(matrix1.getRow(i),
                        matrix2.getColumn(m)));
            }
        }
        return resultMatrix;
    }

    public double determinantMatrix() {
        int rowMatrixNumber = getRowDimension();
        int columnMatrixNumber = getColumnDimension();
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
            determinant += rows[decayLine].getCoordinateByIndex(i) * Math.pow(-1, i + decayLine) * minorMatrix.determinantMatrix();
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
