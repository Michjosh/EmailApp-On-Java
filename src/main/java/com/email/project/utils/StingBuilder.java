package com.email.project.utils;

import java.util.Arrays;

public class StingBuilder {
    public static void main(String[] args) {
//        List<String> tree = Arrays.asList("Oak", "pine", "fir", "birch", "elm", "yew");
//        System.out.println(listToString(tree));

//        int [] numbers = {10,12,3,4};
//        for (int i = numbers.length-1; i >=0 ; i--) {
//            System.out.print(numbers[i]+ ", ");
//        }
//    }

        int[] numberArray = {10, 12, 3, 4};
        int[] reversedNumbers = new int[numberArray.length];
        for (int m = numberArray.length - 1, j = 0; m >= 0; m--, j++) {
            reversedNumbers[j] = numberArray[m];
        }
        System.out.println(Arrays.toString(reversedNumbers));


//    private static String listToString(List<String> list){
//        StringBuilder sb = new StringBuilder(32);
//
//        for (String el: list) {
//            sb.append(el).append(" ");
//        }
//        return sb.toString();
//    }
    }
}
