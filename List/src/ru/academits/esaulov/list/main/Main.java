package ru.academits.esaulov.list.main;

import ru.academits.esaulov.list.MySimpleList;

public class Main {
    public static void main(String[] args) {
        MySimpleList<Integer> listSimpleInteger = new MySimpleList<>();
        listSimpleInteger.add(2);
        listSimpleInteger.add(4);
        listSimpleInteger.add(5);
        listSimpleInteger.add(1);
        System.out.println(listSimpleInteger);
        System.out.println(listSimpleInteger.removeItemByIndex(0));
        System.out.println(listSimpleInteger);
        listSimpleInteger.insertFirstItem(9);
        System.out.println(listSimpleInteger);
        listSimpleInteger.removeFistItem();
        System.out.println(listSimpleInteger);
        System.out.println(listSimpleInteger.getLength());
        System.out.println(listSimpleInteger.getValueByIndex(1));
        listSimpleInteger.add(2);
        listSimpleInteger.add(4);
        listSimpleInteger.add(5);
        listSimpleInteger.add(1);
        System.out.println(listSimpleInteger);
        System.out.println(listSimpleInteger.getLength());
        System.out.println(listSimpleInteger.removeItemByValue(9));




    }
}
