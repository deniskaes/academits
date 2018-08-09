package ru.academits.esaulov.range.main;

import ru.academits.esaulov.range.Range;

public class Main {
    public static void main(String[] args) {
        Range range1 = new Range(2, 4);
        Range range2 = new Range(1, 5);
        Range range3 = new Range(7, 13);

        System.out.println(range1.getRangeLength());
        System.out.println(range1.isInside(3));
        System.out.println(range1.isInside(6));
        System.out.println(range2.crossRanges(range1));

        for (Range e : range1.concatenationRanges(range3)) {
            System.out.println(e);
        }

        for (Range e : range1.differenceRanges(range2)) {
            System.out.println(e);
        }
    }
}
