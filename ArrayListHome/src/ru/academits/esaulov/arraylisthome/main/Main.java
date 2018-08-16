package ru.academits.esaulov.arraylisthome.main;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try(Scanner sc = new Scanner(new FileInputStream("ArrayListHome/src/input.txt"))){
            ArrayList<String> array = new ArrayList<>();
            while (sc.hasNext()){
                array.add(sc.next());
            }
            System.out.println(array);
        }catch (FileNotFoundException e){
            System.out.println("файл не найден");
        }

        ArrayList<Integer> arrayInteger= new ArrayList<>(Arrays.asList(1,3,6,5,2,7,9,8));
        System.out.println(arrayInteger);
        for(int i = arrayInteger.size()- 1; i>=0; i--){
            if(arrayInteger.get(i)%2 == 0){
                arrayInteger.remove(i);
            }
        }
        System.out.println(arrayInteger);

        ArrayList<Integer> arrayListWithRepetitions = new ArrayList<>(Arrays.asList(1,5,2,1,3,5));
        ArrayList<Integer> arrayListWithoutRepetitions = new ArrayList<>();
        for (Integer arrayListWithRepetition : arrayListWithRepetitions) {
            if (arrayListWithoutRepetitions.indexOf(arrayListWithRepetition) == -1) {
                arrayListWithoutRepetitions.add(arrayListWithRepetition);
            }
        }
        System.out.println(arrayListWithRepetitions);
        System.out.println(arrayListWithoutRepetitions);
    }
}
