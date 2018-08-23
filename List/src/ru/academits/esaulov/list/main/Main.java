package ru.academits.esaulov.list.main;

import ru.academits.esaulov.list.MySimpleList;

public class Main {
    public static void main(String[] args) {
        MySimpleList<Integer> listSimpleInteger = new MySimpleList<>();
        listSimpleInteger.add(2);
        listSimpleInteger.add(4);
        listSimpleInteger.add(5);
        listSimpleInteger.add(null);

        System.out.println(listSimpleInteger.removeItemByValue(7));
        System.out.println(listSimpleInteger);
    }
}