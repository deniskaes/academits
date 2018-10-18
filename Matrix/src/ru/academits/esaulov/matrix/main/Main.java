package ru.academits.esaulov.matrix.main;

import ru.academits.esaulov.matrix.Matrix;
import ru.academits.esaulov.vector.Vector;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Vector vector1 = new Vector(new double[]{2,1,5});
//        Vector vector2 = new Vector(new double[]{-5, 0});
//        Vector vector3 = new Vector(new double[]{-4, -3});
//        Vector vector4 = new Vector(new double[]{15, 7});
//        Vector[] av = new Vector[2];
//        av[0] = vector1;
//        av[1] = vector2;
//        Vector[] av1 = new Vector[2];
//        av1[0] = vector3;
//        av1[1] = vector4;
//        av[2] = vector3;
        Matrix av3 = new Matrix(new double[][]{{5,7,1},{-4,1,0},{2,0,3}});
//        Matrix mat = new Matrix(av);
//        Matrix mat2 = new Matrix(mat);

        System.out.println(av3.determinant());




    }
}
