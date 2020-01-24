package test;

import parctices.DynamicProgramming;

public class test {
    public static void main(String[] args) {
        int[] stones = {0,1,2,3,4,6,9,11};
        DynamicProgramming dp = new DynamicProgramming();
        boolean result = dp.canCross(stones);
        System.out.println(result);
    }
}
