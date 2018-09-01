package ru.academits.esaulov.myarraylist.main;

import ru.academits.esaulov.myarraylist.MyArrayList;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        Integer[] array1 = {1,1,1,1,0,0,0,0};
        Integer[] array2 = {2,2,2,2};
        System.arraycopy(array2,0,array1,4,4);
        for(Integer e: array1) {
            System.out.println(e);
        }
    }
}
