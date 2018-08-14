package ru.academits.esaulov.vector.main;

import ru.academits.esaulov.vector.Vector;

public class Main {
    public static void main(String[] args) {
        double[] array = {1,1,1,1};
        System.out.print("vector1 ");
        Vector vector1 = new Vector(array);
        System.out.println(vector1);
        System.out.print("vector2 ");
        Vector vector2 = new Vector(5);
        System.out.println(vector2);
        System.out.print("vector3 ");
        Vector vector3 = new Vector(vector1);
        System.out.println(vector3);
        System.out.print("vector4 ");
        Vector vector4 = new Vector(6, array);
        System.out.println(vector4);
        System.out.print("vector1 + vector4 ");
        vector1.getSumVectors(vector4);
        System.out.println(vector1);
        System.out.print("vector1 - vector4 ");
        vector1.getDifferenceVectors(vector4);
        System.out.println(vector1);
        System.out.print("vector1 * 3 ");
        vector1.getMultiplyVectorByNumber(3);
        System.out.println(vector1);
        System.out.print("vector1 inversion ");
        vector1.getRotateVector();
        System.out.println(vector1);
        System.out.print("vector1 Length = ");
        System.out.println(vector1.getVectorLength());
        System.out.print("vector1 value 10 in index 2");
        vector1.insertValueByIndex(2,10);
        System.out.println(vector1);
        System.out.print("vector1 coordinate index 2 = ");
        System.out.println(vector1.getCoordinateVectorByIndex(2));

    }


}
