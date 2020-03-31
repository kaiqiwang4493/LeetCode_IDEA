package parctices;

import java.awt.desktop.SystemEventListener;
import java.util.*;
import java.lang.String;


public class DFS {
    /* All Subset 1
    Basic DFS - 1
    Given a set of characters represented by a String, return a list containing all subsets of the characters.
     */

    public List<String> subSets1(String set){
        List<String> result = new ArrayList<>();
        if(set == null || set.length() == 0){
            return result;
        }
        StringBuilder sb = new StringBuilder();
        subSets1Helper(set, sb, result, 0);
        return result;
    }
    private void subSets1Helper(String set, StringBuilder sb, List<String> result, int level){
        if(level == set.length()){
            result.add(sb.toString());
            return;
        }

        sb.append(set.charAt(level));
        subSets1Helper(set, sb,  result, level + 1);
        sb.deleteCharAt(sb.length() - 1);
        subSets1Helper(set, sb, result, level + 1);
    }



    /*
     * All Valid Permutations Of Parentheses I
     * Basic DFS - 2
     * Given N pairs of parentheses “()”, return a list with all the valid permutations.
     *  this is similar with the All Subsets question.
     */

    public List<java.lang.String> validParentheses(int n){
        List<java.lang.String> result = new ArrayList<>();
        int left = 0;
        int right = 0;
        StringBuilder sb = new StringBuilder();
        validParenthesesHelper(result, sb, left, right, n);
        return result;
    }
    private void validParenthesesHelper(List<java.lang.String> result, StringBuilder sb, int left, int right, int n){
        if(right == n && left == n){
            result.add(sb.toString());
        }

        if(left < n){
            sb.append('(');
            validParenthesesHelper(result, sb, left + 1, right, n);
            sb.deleteCharAt(sb.length() - 1);
        }

        if(right < left){
            sb.append(')');
            validParenthesesHelper(result, sb, left, right + 1, n);
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    /*
    Combinations Of Coins
    DFS Basic-3
    Given a number of different denominations of coins (e.g., 1 cent, 5 cents, 10 cents, 25 cents),
    get all the possible ways to pay a target number of cents.
     */

    public List<List<Integer>> combinations(int target, int[] coins){
        List<Integer> tempResult = new ArrayList<>();
        List<List<Integer>> finalResult = new ArrayList<>();
        if(target < coins[coins.length-1]){
            return finalResult;
        }
        combinationsHelp(tempResult, finalResult, target, 0, coins);
        return finalResult;
    }

    private void combinationsHelp(List<Integer> tempResult, List<List<Integer>> finalResult, int balance, int level, int[] coins){
        if(level == coins.length - 1){
            if(balance % coins[level] == 0) {
                tempResult.add(balance / coins[level]);
                // this is wrong. finalResult.add(tempResult) just add the original tempResult into finalResult.
                //we must new a new List which can be added into finalResult.
                finalResult.add(new ArrayList<Integer>(tempResult));
                tempResult.remove(tempResult.size() - 1);
            }
            return;
        }

        for(int i = 0; i<= balance / coins[level]; i++){
            tempResult.add(i);
            combinationsHelp(tempResult, finalResult, balance - (i * coins[level]),level+ 1,coins);
            tempResult.remove(tempResult.size() - 1);
        }

    }

    /*
     All Permutations I
     DFS Basic-4
    Given a string with no duplicate characters, return a list with all permutations of the characters.
    Solution: in the each level of recursion tree, swap the array[level] with other letters after array[level].
     */


    public List<String> AllPermutation1(String set){
        List<String> result = new ArrayList<>();
        if(set == null || set.length() == 1){
            result.add(set);
            return result;
        }
        char[] array = set.toCharArray();
        AllPermutation1Help(array, 0, result);
        return result;


    }
    private void AllPermutation1Help(char[] array, int level, List<String> result){
        if(level == array.length){
            result.add(new String(array));
            return;
        }

        for(int i = level; i < array.length; i++){
            swap(array, level, i);
            AllPermutation1Help(array, level + 1, result);
            swap(array,level, i);
        }
    }

    private void swap(char[] array, int i, int j){
        char temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }



    /*
    All Subsets II
    Extend of Basic - 1
    Given a set of characters represented by a String,
    return a list containing all subsets of the characters.
    Notice that each subset returned will be sorted to remove the sequence.
     */
    public List<String> subSets(String set){
        List<String> result = new ArrayList<>();
        if(set == null){
            return result;
        }
        if(set.isEmpty()){
            result.add("");
            return result;
        }

        char[] arraySet = set.toCharArray();
        Arrays.sort(arraySet);
        StringBuilder sb = new StringBuilder();
        int index = 0;
        subSetsHelper(result, arraySet, sb, index);
        return result;
    }

    private void subSetsHelper(List<String> result, char[] arraySet, StringBuilder sb, int index){
        if(index == arraySet.length){
            result.add(sb.toString());
            return;
        }
        sb.append(arraySet[index]);
        subSetsHelper(result, arraySet, sb, index + 1);
        sb.deleteCharAt(sb.length()-1);
        while(index < arraySet.length - 1 && arraySet[index] == arraySet[index + 1]){
            index++;
        }
        subSetsHelper(result, arraySet, sb, index + 1);
    }



    /*
    *All Subsets of Size K
    *Given a set of characters represented by a String,
    * return a list containing all subsets of the characters whose size is K.
     */

    public List<String> subSetOfSizeK(String set, int k){
        List<String> result = new ArrayList<>();
        if (set.length() < k) {
            return result;
        }

        StringBuilder sb = new StringBuilder();
        sebSetOfSizeKHelper(set, k, 0, sb, result);
        return result;

    }

    private void sebSetOfSizeKHelper(String set, int k, int level, StringBuilder sb, List<String> result){
        if(sb.length() == k || level == set.length()){
            if(sb.length() == k){
                result.add(new String(sb.toString()));
                return;
            }
        }
        sb.append(set.charAt(level));
        sebSetOfSizeKHelper(set, k, level + 1, sb, result);
        sb.deleteCharAt(sb.length() - 1);
        sebSetOfSizeKHelper(set, k, level + 1, sb, result);

    }


    /*
    Flood Fill
     */
    // Correct solution of Flood fill

    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        int color = image[sr][sc];
        if (color != newColor) dfs(image, sr, sc, color, newColor);
        return image;
    }
    public void dfs(int[][] image, int r, int c, int color, int newColor) {
        if (image[r][c] == color) {
            image[r][c] = newColor;
            if (r >= 1) dfs(image, r-1, c, color, newColor);
            if (c >= 1) dfs(image, r, c-1, color, newColor);
            if (r+1 < image.length) dfs(image, r+1, c, color, newColor);
            if (c+1 < image[0].length) dfs(image, r, c+1, color, newColor);
        }
    }

    /*
    ()()()find all valid permutation using the parenthesis provided.
     */

    /*
    *Keep Distance For Identical Elements
    *Given an integer k, arrange the sequence of integers [1, 1, 2, 2, 3, 3, ...., k - 1, k - 1, k, k],
    * such that the output integer array satisfy this condition: Between each two i's, they are exactly i integers
    * k = 3, The output = { 2, 3, 1, 2, 1, 3 }.
     */
    /*
    *Solution: 1.find the all combination of the origin array
    *           2. use other method to check whether the result is legal. If legal then return result.
     */

    public int[] keepDistance(int k){
        if(k <= 0){
            return null;
        }
        int[] array = new int[k *2];
        int m = 1;
        for(int i = 0; i < array.length; i = i + 2){
            array[i] = m;
            array[i+1] = m;
            m++;
        }
        int[] result = null;
        return keepDistanceHelper(array, result,0,k);
    }
    //recursion method, exit part code has problem.
    private int[] keepDistanceHelper(int[] array,int[] result, int level, int k){
        if(level == k *2){
            if(keepDistanceCheck(array, k)){
                //caution: result just copy the head address of array list.
                // so we cannot modify both result and array after finding correct result.
                result = array;
                for(int i : result){
                    System.out.print(i + " ");
                }
                System.out.println();
            }
            return result;
        }

        for(int i = level; i < array.length; i++){
            intSwap(array, level, i);
            result = keepDistanceHelper(array, result, level + 1, k);
            //return result once find the correct answer.
            if(result != null) break;
            intSwap(array, level, i);
        }
        return result;
    }
    //check the result is or not valuable.
    private boolean keepDistanceCheck(int[] array, int k){
        Set<Integer> set = new HashSet<>();
        for(int i = 1; i <= k; i++){
            set.add(i);
        }
        int position = 0;
        while(!set.isEmpty()){
            int temp = array[position];
            if(set.contains(temp)){
                if(position + temp + 1>= array.length || array[position + temp + 1] != temp){
                    return false;
                }
                set.remove(temp);
            }
            position++;
        }
        return true;
    }
    // swap for int[]
    private void intSwap(int[] array ,int i, int k){
        int temp = array[i];
        array[i] = array[k];
        array[k] = temp;
    }


    /*
    *Given a string with possible duplicate characters, return a list with all permutations of the characters.
    *Set = "aba", all permutations are ["aab", "aba", "baa"]
    * Set = “abc”, all permutations are [“abc”, “acb”, “bac”, “bca”, “cab”, “cba”]
    * Set = "", all permutations are [""]
    *
    * solution : we use a set to store the permutations, and once we found a permutations same as the element
    *             in the set, we should wipe out the permutations and it's child branch. And caution the position of code.
    *            We check the duplicated permutation JUST at CURRENT LEVEL, instead of the final result pool.
     */

    public List<String> permutations2(String input){
        if(input == null){
            return null;
        }
        List<String> result = new ArrayList<>();
        if(input.length() == 0){
            return result;
        }
        char[] array = input.toCharArray();
        int level = 0;
        permutations2Helper(result, array, level);
        return result;
    }

    private void permutations2Helper(List<String> result, char[] array, int level){
        if(level == array.length){
            result.add(new String(array));
            return;
        }
        /*
        Caution: the rule is just for current level, if a certain element is picked,
        we cannot pick any of its duplicates.
        so that we use a set to record all the distinct elements.
         */
        Set<Character> set = new HashSet<>();
        for(int i = level; i<array.length; i++){
            //.add() will return false if the value of String(array) is already in the set.
            // we will continue swap letters if the value is not existed in the set.
            if(set.add(array[i])){
                swap(array, level, i);
                permutations2Helper(result, array, level + 1);
                swap(array, level, i);
            }
        }
    }

    /*
    *Factor Combinations
    *Given an integer number, return all possible combinations of the factors that can multiply to the target number.
    *
    * 24 = 2 x 2 x 2 x 3
              = 2 x 2 x 6
              = 2 x 3 x 4
              = 2 x 12
              = 3 x 8
              = 4 x 6
                your solution should return { { 2, 2, 2, 3 }, { 2, 2, 6 }, { 2, 3, 4 }, { 2, 12 }, { 3, 8 }, { 4, 6 } }
    * The key to make sure the result without duplicate is keeping the element in the list is incremental or equal
     */

    public List<List<Integer>> factorCominations(int target){
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        if(target == 0 || target == 1){
            return new ArrayList<>();
        }
        factorCominationsHelper(result, temp, 2, target);
        return result;
    }

    private void factorCominationsHelper(List<List<Integer>> result, List<Integer> temp, int start, int target){
        if(target == 1 && temp.size() > 1){
            result.add(new ArrayList<>(temp));
            return;
        }

        for(int i = start; i <= target; i++){

            if(target%i == 0){
                temp.add(i);
                factorCominationsHelper(result, temp,i,target/i);
                temp.remove(temp.size() - 1);
            }
        }
    }

    /*
        N   Queen
        Assume : N >0
     */
        public List<List<Integer>> nqueens(int n){
            List<List<Integer>> result = new ArrayList<>();
            List<Integer> cur = new ArrayList<>();
            nqueensHelper(result, cur, n);
            return result;
        }

        private void nqueensHelper(List<List<Integer>> result, List<Integer> cur, int n){
            if(cur.size() == n){
                result.add(new ArrayList<>(cur));
                return;
            }
            for(int i = 0; i<n; i++){
                if(nqueenVaild(cur, i)){
                    cur.add(i);
                    nqueensHelper(result, cur, n);
                    cur.remove(cur.size() - 1);
                }
            }
        }

        private boolean nqueenVaild(List<Integer>cur, int i){
            for(int temp = 0; temp < cur.size(); temp++){
                if(cur.get(temp) == i || Math.abs(cur.get(temp) - i) == cur.size() - temp){
                    return false;
                }
            }
            return true;
        }

        /*
        Restore IP address
        solution: each layer has three branch. Each branch represents the different position of dot.
        finlly n == 4, we need to check the rest part of number . We drop the result if the rest part number > 255.
         */

        public List<String> restoreIP(String ip){
            if (ip.length() < 4 || ip.length() > 12){
                return null;
            }

            StringBuilder sb = new StringBuilder(ip);
            List<String> result = new ArrayList<>();
            restoreIPHelper(result, sb, 0, 1);
            return result;
        }

        private void restoreIPHelper(List<String> result, StringBuilder sb, int level, int insertPosition){
            if(level == 3){
                String subString = sb.substring(insertPosition - 1);
                if(IPvaild(subString)){
                    result.add(new String(sb.toString()));
                }
                return;
            }

            for(int i= insertPosition; i < insertPosition + 3 && i < sb.length(); i++){
                sb.insert(i, ".");
                if(IPvaild(sb.substring(insertPosition - 1, i))){
                    restoreIPHelper(result, sb, level + 1, i + 2);
                }

                sb.deleteCharAt(i);
            }
        }

        private boolean IPvaild(String string){
            if(string.length() < 3){
                return true;

            }else if(string.length() == 3){
                int result = string.compareTo("255");
                return result <= 0;
            }else{
                return false;
            }

        }


}
