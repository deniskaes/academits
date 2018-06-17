package ru.academits.esaulov.shapes.main;

import ru.academits.esaulov.shapes.Shape;
import ru.academits.esaulov.shapes.ShapesAreaComparator;
import ru.academits.esaulov.shapes.Square;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        Shape[] shapesList = {new Square(2.5), new Square(2), new Square(4)};
        Arrays.sort(shapesList, new ShapesAreaComparator());
        System.out.println(Arrays.toString(shapesList));
    }
}
