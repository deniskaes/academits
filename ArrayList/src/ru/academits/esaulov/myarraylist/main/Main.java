package ru.academits.esaulov.myarraylist.main;

import ru.academits.esaulov.myarraylist.MyArrayList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class Main {
    public static void main(String[] args) {
        MyArrayList<Integer> intList1 = new MyArrayList<>(5);
        intList1.add(1);
        intList1.add(3);
        intList1.add(6);

        intList1.clear();
        System.out.println(intList1.get(1));

        System.out.println(intList1);
        ArrayList<Integer> sdasd = new ArrayList<>();
    }
}
