package ru.academits.esaulov.range;

public class Range {
    private double from;
    private double to;

    public Range(double from, double to) {
        this.from = from;
        this.to = to;
    }

    public double getRangeLength() {
        return Math.abs(from - to);
    }

    public boolean isInside(double testNumber) {
        return (testNumber >= from && testNumber <= to);
    }

    public Range[] concatenationRanges(Range range2) {
        Range[] arrayRanges;
        if (this.crossRanges(range2) == null) {
            arrayRanges = new Range[2];
            arrayRanges[0] = new Range(this.from, this.to);
            arrayRanges[1] = new Range(range2.from, range2.to);
        } else {
            arrayRanges = new Range[1];
            arrayRanges[0] = new Range(Math.min(this.from, range2.from), Math.max(this.to, range2.to));
        }
        return arrayRanges;
    }

    public Range[] differenceRanges(Range range) {
        Range[] arrayRanges;
        Range crossRange = this.crossRanges(range);

        if (crossRange == null) {
            arrayRanges = new Range[1];
            arrayRanges[0] = new Range(this.from, this.to);
        } else if (this.from <= range.from && this.to >= range.to) {
            arrayRanges = new Range[2];
            arrayRanges[0] = new Range(this.from, range.from);
            arrayRanges[1] = new Range(range.to, this.to);
        } else if (this.from <= range.from && this.to >= range.from && range.to >= this.to) {
            arrayRanges = new Range[1];
            arrayRanges[0] = new Range(this.from, range.from);
        } else if (range.from <= this.from && range.to >= this.to) {
            arrayRanges = new Range[2];
            arrayRanges[0] = new Range(range.from, this.from);
            arrayRanges[1] = new Range(this.to, range.to);
        } else {
            arrayRanges = new Range[1];
            arrayRanges[0] = new Range(range.from, this.from);
        }
        return arrayRanges;
    }

    public Range crossRanges(Range range) {
        if (range.from >= this.from && range.to <= this.to) {
            return new Range(range.from, range.to);
        } else if (this.from >= range.from && this.to <= range.to) {
            return new Range(this.from, this.to);
        } else if (range.from >= this.from && range.from <= this.to && range.to >= this.to) {
            return new Range(range.from, this.to);
        } else if (this.from >= range.from && this.from <= range.to && this.to >= range.to) {
            return new Range(this.from, range.to);
        } else return null;
    }

    public String toString() {
        return "(" + this.from + ", " + this.to + ")";
    }
}
