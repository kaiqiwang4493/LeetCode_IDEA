package test;

import dataStructure.TreeNode;
import parctices.*;

import javax.lang.model.element.NestingKind;
import javax.management.MBeanServerFactory;
import java.util.*;

public class test {

    public static void main(String[] args) {
        DFS dfs = new DFS();
        int[] result = dfs.keepDistance2(4);
        if(result == null) {
            System.out.println("NUll");
        }else {
            for (int i = 0; i < result.length; i++) {
                System.out.print(result[i] + " ");
            }
        }



//        System.out.println(tickets);
//        System.out.println();
//        HashMap<String, PriorityQueue<String>> result = dfs.getAirportMap(tickets);
//        System.out.println(result);
//        System.out.println();

//        List<String> trip = dfs.findItinerary(tickets);
//        System.out.println(trip);
       // showMethodString(dfs.subSets1(input));
        System.out.println("End");
//        TreeNode root1 = new TreeNode(10);
//        TreeNode root2 = new TreeNode(5);
//        TreeNode root3 = new TreeNode(6);
//        TreeNode root4 = new TreeNode(7);
//        TreeNode root5 = new TreeNode(8);
//        TreeNode root6 = new TreeNode(1);
//        TreeNode root7 = new TreeNode(2);
//        TreeNode root8 = new TreeNode(11);
//        TreeNode root9 = new TreeNode(9);
//
//        root3.left = root9;
//        root4.left = root6;
//        root4.right = root7;
//        root2.left = root4;
//        root1.left = root2;
//        root5.right = root8;
//        root3.right = root5;
//        root1.right = root3;
//
//        BinaryTree bt = new BinaryTree();
//        List<Integer> before = bt.InOrderTraversal2(root1);
//        System.out.println(before);
//        TreeNode result = bt.removeEmptyNode(root1);
//        List<Integer> after = bt.InOrderTraversal2(result);
//        System.out.println(after);

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
