package ru.academits.esaulov.range;

public class Range {
    private double from;
    private double to;

    public Range(double from, double to) {
        this.from = from;
        this.to = to;
    }

    public double getRangeLength() {
        return Math.abs(this.from - this.to);
    }

    public boolean isInside(double testNumber) {
        return (testNumber >= from && testNumber <= to);
    }

    public Range[] concatenationRanges (Range range1, Range range2) {
        //TODO: сделать объединение
        return null;
    }

    public Range[] differenceRanges (Range range1, Range range2) {
        //TODO: сделать разницу
        return null;
    }
}
