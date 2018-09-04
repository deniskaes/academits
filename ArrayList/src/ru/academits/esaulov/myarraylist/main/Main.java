package ru.academits.esaulov.myarraylist.main;

import ru.academits.esaulov.myarraylist.MyArrayList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class Main {
    public static void main(String[] args) {
        MyArrayList<Integer> intList = new MyArrayList<>(5);
        intList.add(3);
        intList.add(3);
        intList.add(6);

        Integer[] listToArray = intList.toArray();

        for(Integer e: listToArray){
            System.out.print(e + " ");
        }


    }
}
