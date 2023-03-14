package com.email.project.utils;

import java.util.Arrays;
import java.util.List;

public class StingBuilder {
    public static void main(String[] args) {
        List<String> tree = Arrays.asList("Oak", "pine", "fir", "birch", "elm", "yew");
        System.out.println(listToString(tree));
    }

    private static String listToString(List<String> list){
        StringBuilder sb = new StringBuilder(32);

        for (String el: list) {
            sb.append(el).append(" ");
        }
        return sb.toString();
    }
}
