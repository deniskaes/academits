package ru.academits.esaulov.matrix.main;

import ru.academits.esaulov.matrix.Matrix;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Matrix matrix = new Matrix(1,3);
        System.out.println(matrix);

        int[] array1 = {1,2,3,4};

        int[] array2 = {1,2};

        array2 = Arrays.copyOf(array2,10);

        for(int e: array2){
            System.out.println(e);
        }



    }
}
