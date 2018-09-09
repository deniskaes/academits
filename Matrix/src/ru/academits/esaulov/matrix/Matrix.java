package ru.academits.esaulov.matrix;

import ru.academits.esaulov.vector.Vector;


public class Matrix {
    private Vector[] arrayRowMatrix;

    public Matrix(int rowsNumber, int columnsNumber) {
        if (rowsNumber < 1 || columnsNumber < 1) {
            throw new IllegalArgumentException("не верный аргумент");
        }
        arrayRowMatrix = new Vector[rowsNumber];
        for (int i = 0; i < rowsNumber; i++) {
            arrayRowMatrix[i] = new Vector(columnsNumber);
        }
    }

    public Matrix(Vector[] vectorsArray) {
        if (vectorsArray.length == 0) {
            throw new IllegalArgumentException("передан массив 0 длины");
        }
        int linesMatrixNumber = vectorsArray.length;
        arrayRowMatrix = new Vector[linesMatrixNumber];
        int maxLengthVector = 0;
        for (Vector e : vectorsArray) {
            maxLengthVector = (e.getSize() > maxLengthVector) ? e.getSize() : maxLengthVector;
        }
        for (int i = 0; i < linesMatrixNumber; i++) {
            int coordinatesVectorNumber = vectorsArray[i].getSize();
            double[] coordinates = new double[coordinatesVectorNumber];
            for (int m = 0; m < coordinatesVectorNumber; m++) {
                coordinates[m] = vectorsArray[i].getCoordinateByIndex(m);
            }
            arrayRowMatrix[i] = new Vector(maxLengthVector, coordinates);
        }
    }

    public Matrix(double[][] arrayOfArrays) {
        if (arrayOfArrays.length == 0) {
            throw new IllegalArgumentException("массив нулевой длины");
        }
        int linesMatrixNumber = arrayOfArrays.length;
        arrayRowMatrix = new Vector[linesMatrixNumber];
        int maxLengthArray = 0;
        for (double[] e : arrayOfArrays) {
            maxLengthArray = (e.length > maxLengthArray) ? e.length : maxLengthArray;
        }
        for (int i = 0; i < linesMatrixNumber; i++) {
            arrayRowMatrix[i] = new Vector(maxLengthArray, arrayOfArrays[i]);
        }
    }

    public Matrix(Matrix matrix) {
        arrayRowMatrix = new Vector[matrix.getRowDimension()];
        for (int i = 0; i < matrix.arrayRowMatrix.length; i++) {
            this.arrayRowMatrix[i] = new Vector(matrix.arrayRowMatrix[i]);
        }
    }

    public int getRowDimension() {
        return arrayRowMatrix.length;
    }

    public int getColumnDimension() {
        return arrayRowMatrix[0].getSize();
    }

    public Vector getRow(int index) {
        if (index < 0 || index >= arrayRowMatrix.length) {
            throw new IndexOutOfBoundsException("не верный индекс");
        }
        return arrayRowMatrix[index];
    }

    public void setRow(int index, Vector vector) {
        if (index < 0 || index >= arrayRowMatrix.length) {
            throw new IndexOutOfBoundsException("не верный индекс");
        }

        int dimensionRow = getColumnDimension();
        double[] coordinates = new double[dimensionRow];
        for (int i = 0; i < dimensionRow; i++) {
            coordinates[i] = vector.getCoordinateByIndex(i);
        }
        arrayRowMatrix[index] = new Vector(coordinates);
    }

    public Vector getColumn(int index) {
        if (index < 0 || index >= this.getColumnDimension()) {
            throw new IndexOutOfBoundsException("не верный индекс");
        }
        double[] coordinates = new double[arrayRowMatrix.length];
        for (int i = 0; i < arrayRowMatrix.length; i++) {
            coordinates[i] = arrayRowMatrix[i].getCoordinateByIndex(index);
        }
        return new Vector(coordinates);
    }

    public void setColumn(int index, Vector vector) {
        if (index < 0 || index >= this.getColumnDimension()) {
            throw new IndexOutOfBoundsException("не верный индекс");
        }
        for (int i = 0; i < arrayRowMatrix.length; i++) {
            arrayRowMatrix[i].setCoordinateByIndex(index, vector.getCoordinateByIndex(i));
        }
    }

    public void transposeMatrix() {
        int rowNumberOld = getRowDimension();
        int columnNumberOld = getColumnDimension();
        Matrix transposeMatrix = new Matrix(columnNumberOld, rowNumberOld);
        for (int i = 0; i < columnNumberOld; i++) {
            transposeMatrix.setRow(i, getColumn(i));
        }
        arrayRowMatrix = transposeMatrix.arrayRowMatrix;
    }

    public void multiplicationByScalar(double number) {
        for (Vector v : arrayRowMatrix) {
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
        for (int i = 0; i < this.arrayRowMatrix.length; i++) {
            this.arrayRowMatrix[i] = Vector.getSumVectors(this.arrayRowMatrix[i], matrix.getRow(i));
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
                resultMatrix.arrayRowMatrix[i].setCoordinateByIndex(m, Vector.getScalarMultiply(matrix1.getRow(i),
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
            return arrayRowMatrix[0].getCoordinateByIndex(0);
        }

        double determinant = 0;
        int decayLine = 0;
        for (int i = 0; i < rowMatrixNumber; i++) {
            Matrix minorMatrix = new Matrix(rowMatrixNumber - 1, rowMatrixNumber - 1);
            for (int s = 0; s < rowMatrixNumber - 1; s++) {
                for (int c = 0; c < rowMatrixNumber - 1; c++) {
                    if (i <= c) {
                        minorMatrix.getRow(s).setCoordinateByIndex(c, arrayRowMatrix[s + 1].getCoordinateByIndex(c + 1));
                    } else {
                        minorMatrix.getRow(s).setCoordinateByIndex(c, arrayRowMatrix[s + 1].getCoordinateByIndex(c));
                    }
                }
            }
            determinant += arrayRowMatrix[decayLine].getCoordinateByIndex(i) * Math.pow(-1, i + decayLine) * minorMatrix.determinantMatrix();
        }
        return determinant;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Матрица: " + arrayRowMatrix.length + "x" + arrayRowMatrix[0].getSize());
        sb.append(System.lineSeparator());
        sb.append("{");
        for (int i = 0; i < arrayRowMatrix.length - 1; i++) {
            sb.append(arrayRowMatrix[i]).append(",");
        }
        sb.append(arrayRowMatrix[arrayRowMatrix.length - 1]);
        sb.append("}");
        return sb.toString();
    }
}
