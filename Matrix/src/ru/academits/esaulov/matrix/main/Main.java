package ru.academits.esaulov.matrix.main;

import ru.academits.esaulov.matrix.Matrix;
import ru.academits.esaulov.vector.Vector;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Matrix matrix = new Matrix(2,2);
        System.out.println(matrix);


        Vector vector1 = new Vector(new double[]{1,2,3});
        Vector vector2 = new Vector(new double[]{4,5,6});
        Vector[] av = new Vector[2];
        av[0] = vector1;
        av[1] = vector2;

        Matrix mat = new Matrix(av);
        System.out.println(mat);
        System.out.println(mat.getDimension()[1]);



    }
}
