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

    public Range[] concatenationRanges(Range range) {
        if (this.to < range.from || range.to < this.from) {
            return new Range[]{this, range};
        } else if (range.to <= this.to && this.from <= range.from) {
            return new Range[]{this};
        } else if (this.to <= range.to && range.from <= this.from) {
            return new Range[]{range};
        } else if (this.from < range.from && range.from <= this.to && range.to > this.to) {
            return new Range[]{new Range(this.from, range.to)};
        } else {
            return new Range[]{new Range(range.from, this.to)};
        }
    }

    public Range[] differenceRanges(Range range) {
        if (this.to <= range.from || this.from >= range.to) {
            return new Range[]{this};
        } else if (this.from < range.from && this.to > range.to) {
            return new Range[]{new Range(this.from, range.from), new Range(range.to,this.to)};
        } else if (this.from < range.from && this.to <= range.to && range.from < this.to) {
            return new Range[]{new Range(this.from,range.from)};
        } else if (this.from < range.to && range.to < this.to && this.from >= range.from) {
            return new Range[]{new Range(range.to, this.to)};
        } else {
            return new Range[]{};
        }
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
