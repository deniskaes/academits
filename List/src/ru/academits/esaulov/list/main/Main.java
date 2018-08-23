package ru.academits.esaulov.list.main;

import ru.academits.esaulov.list.MySimpleList;

public class Main {
    public static void main(String[] args) {
        MySimpleList<Integer> listSimpleInteger = new MySimpleList<>();
        listSimpleInteger.add(2);
        listSimpleInteger.add(4);
        listSimpleInteger.add(5);
        listSimpleInteger.add(1);
        MySimpleList<Integer> newCopy = listSimpleInteger.copy();
        newCopy.add(10);
        System.out.println(listSimpleInteger);
        System.out.println(newCopy);

    }
}
