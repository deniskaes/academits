package ru.academits.esaulov.range;

public class Range {
    private double from;
    private double to;

    public double getFrom() {
        return from;
    }

    public double getTo() {
        return to;
    }

    public void setFrom(double from) {
        this.from = from;
    }

    public void setTo(double to) {
        this.to = to;
    }

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

    public Range[] concatenationRanges(Range range1, Range range2) {
        Range[] arrayRanges = new Range[2];

        if (range1.isInside(range2.from) && range1.isInside(range2.to)) {
            arrayRanges[0] = range1;
            arrayRanges[1] = null;
        } else if (range2.isInside(range1.from) && range2.isInside(range1.to)) {
            arrayRanges[0] = range2;
            arrayRanges[1] = null;
        } else if (range1.isInside(range2.from) && range2.isInside(range1.to)) {
            arrayRanges[0] = new Range(range1.from, range2.to);
            arrayRanges[1] = null;
        } else if (range2.isInside(range1.from) && range1.isInside(range2.to)) {
            arrayRanges[0] = new Range(range2.from, range1.to);
            arrayRanges[1] = null;
        } else {
            arrayRanges[0] = range1;
            arrayRanges[1] = range2;
        }
        return arrayRanges;
    }

    public Range[] differenceRanges(Range range1, Range range2) {
        Range[] arrayRanges = new Range[2];
        Range crossRange = crossingRanges(range1, range2);

        if (crossRange.equals(null)) {
            arrayRanges[0] = range1;
            arrayRanges[1] = null;
        } else if (range1.isInside(range2.from) && range2.isInside(range1.to)) {
            arrayRanges[0] = new Range(range1.from, crossRange.from);
            arrayRanges[1] = null;
        } else if (range1.isInside(range2.to) && range2.isInside(range1.from)) {
            arrayRanges[0] = new Range(crossRange.to, range1.to);
            arrayRanges[1] = null;
        } else {
            arrayRanges[0] = new Range(range1.from, crossRange.from);
            arrayRanges[1] = new Range(crossRange.to, range1.to);
        }
        return arrayRanges;
    }

    public Range crossingRanges(Range range1, Range range2) {
        if (range1.isInside(range2.to) && range1.isInside(range2.from)) {
            return range2;
        } else if (range2.isInside(range1.to) && range2.isInside(range1.from)) {
            return range1;
        } else if (range1.isInside(range2.from)) {
            return new Range(range2.from, range1.to);
        } else if (range2.isInside(range1.from)) {
            return new Range(range1.from, range2.to);
        } else {
            return null;
        }
    }

    public String toString() {
        return "(" + this.from + ", " + this.to + ")";
    }
}
