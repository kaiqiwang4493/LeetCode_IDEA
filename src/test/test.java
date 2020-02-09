package test;

import parctices.Array;
import parctices.DynamicProgramming;

public class test {
    public static void main(String[] args) {
        String string = new String("ABCD");
        System.out.println(string);
        Array array = new Array();
        array.printPermutations(string);
//        StringBuilder builder = new StringBuilder(string);
//        builder.insert(1, "_");
//        System.out.println(builder.toString());
//        builder.insert(0, "_");
//        System.out.println(builder.toString());
//        builder.deleteCharAt(1);
//        System.out.println(builder.toString());
    }
}
