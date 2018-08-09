package ru.academits.esaulov.shapes.comparators;

import ru.academits.esaulov.shapes.Shape;

import java.util.Comparator;

public class ShapesPerimeterComparator implements Comparator<Shape> {

    @Override
    public int compare(Shape o1, Shape o2) {
        Double perimeter1 = o1.getPerimeter();
        Double perimeter2 = o2.getPerimeter();
        return perimeter1.compareTo(perimeter2);
    }
}
