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
        Range[] arrayRanges = new Range[2];

        if (this.isInside(range2.from) && this.isInside(range2.to)) {
            arrayRanges[0] = this;
            arrayRanges[1] = null;
        } else if (range2.isInside(this.from) && range2.isInside(this.to)) {
            arrayRanges[0] = range2;
            arrayRanges[1] = null;
        } else if (this.isInside(range2.from) && range2.isInside(this.to)) {
            arrayRanges[0] = new Range(this.from, range2.to);
            arrayRanges[1] = null;
        } else if (range2.isInside(this.from) && this.isInside(range2.to)) {
            arrayRanges[0] = new Range(range2.from, this.to);
            arrayRanges[1] = null;
        } else {
            arrayRanges[0] = this;
            arrayRanges[1] = range2;
        }
        return arrayRanges;
    }

    public Range[] differenceRanges(Range range2) {
        Range[] arrayRanges = new Range[2];
        Range crossRange = this.crossingRanges(range2);

        if (crossRange == null) {
            arrayRanges[0] = this;
            arrayRanges[1] = null;
        } else if (this.isInside(range2.from) && range2.isInside(this.to)) {
            arrayRanges[0] = new Range(this.from, crossRange.from);
            arrayRanges[1] = null;
        } else if (this.isInside(range2.to) && range2.isInside(this.from)) {
            arrayRanges[0] = new Range(crossRange.to, this.to);
            arrayRanges[1] = null;
        } else {
            arrayRanges[0] = new Range(this.from, crossRange.from);
            arrayRanges[1] = new Range(crossRange.to, this.to);
        }
        return arrayRanges;
    }

    public Range crossingRanges(Range range2) {
        if (this.isInside(range2.to) && this.isInside(range2.from)) {
            return range2;
        } else if (range2.isInside(this.to) && range2.isInside(this.from)) {
            return this;
        } else if (this.isInside(range2.from)) {
            return new Range(range2.from, this.to);
        } else if (range2.isInside(this.from)) {
            return new Range(this.from, range2.to);
        } else {
            return null;
        }
    }

    public String toString() {
        return "(" + this.from + ", " + this.to + ")";
    }
}
