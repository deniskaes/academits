package ru.academits.esaulov.shapes.main;

import ru.academits.esaulov.shapes.*;
import ru.academits.esaulov.shapes.comparators.ShapesAreaComparator;
import ru.academits.esaulov.shapes.comparators.ShapesPerimeterComparator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Main {

    private static Shape getMaxAreaShape(List<Shape> shapeList) {
        Shape maxAreaShape = shapeList.get(0);
        for (int i = 1; i < shapeList.size(); i++) {
            if (maxAreaShape.getArea() < shapeList.get(i).getArea()) {
                maxAreaShape = shapeList.get(i);
            }
        }
        return maxAreaShape;
    }

    public static void main(String[] args) {
        ArrayList<Shape> shapeList = new ArrayList<>();


        shapeList.add(new Square(2.5));
        shapeList.add(new Square(3));
        shapeList.add(new Triangle(-2, 2, 3, -1, 2, -1));
        shapeList.add(new Triangle(-2, 4, 2, -1, 2, -3));
        shapeList.add(new Rectangle(2, 4));
        shapeList.add(new Rectangle(1, 0.5));
        shapeList.add(new Circle(3));
        shapeList.add(new Circle(1.5));

        System.out.println(Arrays.toString(shapeList.toArray()));

        Shape[] shapeAscendingAreaArray = new Shape[shapeList.size()];
        shapeAscendingAreaArray = shapeList.toArray(shapeAscendingAreaArray);
        Arrays.sort(shapeAscendingAreaArray, new ShapesAreaComparator());
        System.out.println(Arrays.toString(shapeAscendingAreaArray));

        Shape[] shapeAscendingPerimeterArray = new Shape[shapeList.size()];
        shapeAscendingPerimeterArray = shapeList.toArray(shapeAscendingPerimeterArray);
        Arrays.sort(shapeAscendingPerimeterArray, new ShapesPerimeterComparator());
        System.out.println(Arrays.toString(shapeAscendingPerimeterArray));

        System.out.println("Фигура с максимальной площадью: " + getMaxAreaShape(shapeList));
        System.out.println("Фигура со вторым по величине периметром: " + shapeAscendingPerimeterArray[1]);
    }
}
