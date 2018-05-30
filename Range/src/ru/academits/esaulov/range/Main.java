package ru.academits.esaulov.range;

public class Main {
    public static void main(String[] args) {
        Range range1 = new Range(1, 5);
        Range range2 = new Range(3, 6);

        System.out.println(range1.getRangeLength());
        System.out.println(range1.isInside(3));
        System.out.println(range1.isInside(6));
        System.out.println();
    }
}
