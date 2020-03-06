package test;

import parctices.Array;
import parctices.BFS;
import parctices.DynamicProgramming;
import parctices.StringQuestion;

import javax.management.MBeanServerFactory;
import java.util.ArrayList;
import java.util.List;

public class test {
    public static void main(String[] args) {

        int[][] image = {{1,1,1,},{1,1,0},{1,0,1}};
        int sr = 1, sc = 1;
        int newColor = 2;
        show2Darray(image);
        System.out.println();
        BFS bfs = new BFS();
        show2Darray(bfs.flodFill(image, sr, sc, 2));

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
    }

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
}
