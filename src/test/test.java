package test;

import parctices.Array;
import parctices.DynamicProgramming;
import parctices.StringQuestion;

import java.util.ArrayList;
import java.util.List;

public class test {
    public static void main(String[] args) {

        int array[] = {5,4,3,2,6};
        StringQuestion sq = new StringQuestion();
        int result = sq.MinSubSum(array, 9);
        System.out.println(result);


//
//        StringQuestion sq = new StringQuestion();
//        Integer result = sq.MinReplace(source, target);
//        System.out.println(result);




//        List<Integer> result = new ArrayList<>();
//        result = sq.findAnagrams(p1,s);
//        showMethod(result);
//        result = sq.findAnagrams(p2, s);
//        showMethod(result);
//        result = sq.findAnagrams(p3, s);
//        showMethod(result);


//        StringBuilder builder = new StringBuilder(string);
//        builder.insert(1, "_");
//        System.out.println(builder.toString());
//        builder.insert(0, "_");
//        System.out.println(builder.toString());
//        builder.deleteCharAt(1);
//        System.out.println(builder.toString());
    }

    public static void showMethod(List<Integer> list){
        for(Integer temp : list){
            System.out.print(temp+", ");
        }
        System.out.println();
    }
}
