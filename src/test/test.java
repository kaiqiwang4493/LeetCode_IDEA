package test;

import parctices.*;

import javax.management.MBeanServerFactory;
import java.util.ArrayList;
import java.util.List;

public class test {

    public static void main(String[] args) {
       int[] array = {0,1,1,0,0,-1,-1,1,0};
       Sort sort = new Sort();
       sort.rainbowSort(array);
        for (int value : array) {
            System.out.print(value + "   ");
        }

       }



//       String string = "bb";
//       StringQuestion sq = new StringQuestion();
//       System.out.println(sq.longest(string));


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


    public static void show2Darray(int[][] array){
        int N = array.length;
        int M = array[0].length;
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                System.out.print(array[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void showMethod(List<Integer> list){
        for(Integer temp : list){
            System.out.print(temp+", ");
        }
        System.out.println();
    }

    public static void showMethodString(List<String> list){
        for(String temp:list){
            System.out.println(temp);
        }
    }
}
