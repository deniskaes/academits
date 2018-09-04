package ru.academits.esaulov.myarraylist.main;

import ru.academits.esaulov.myarraylist.MyArrayList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class Main {
    public static void main(String[] args) {
//        MyArrayList<Integer> intList = new MyArrayList<>(5);
//        intList.add(3);
//        intList.add(3);
//        intList.add(6);
//
//        Object[] listToArray = intList.toArray();
//
//        for(Object e: listToArray){
//            System.out.print(e + " ");
//        }
        ArrayList<Integer> ia = new ArrayList<>(Arrays.asList(1,2,1,3));
        ArrayList<Integer> ia1 = new ArrayList<>(Arrays.asList(1));
        ia.retainAll(ia1);
        System.out.println(ia);

    }
}
