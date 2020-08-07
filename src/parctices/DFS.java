package parctices;

import com.sun.source.tree.Tree;
import dataStructure.Node;
import dataStructure.TreeNode;

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
       return  validParenthesesHelper(result, sb, left, right, n);
        //return result;
    }
    private List<String> validParenthesesHelper(List<java.lang.String> result, StringBuilder sb, int left, int right, int n){
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
        return result;
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
    Time = O(n!)
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
    *              we check the correction when we meet the first element of value. We check the forward value. Look right side.
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

    //solution2
    // we check the correction when we add the second one of this value.
    // We check the backward value. Look left side.
    public int[] keepDistance2(int k) {
        if(k == 0){
            return null;
        }
        Map<Integer,Integer> map = new HashMap<>();
        for (int i = 1; i <= k; i++) {
            map.put(i, 2);
        }
        List<Integer> tmpResult = new ArrayList<>();
        keepDistance2Helper(tmpResult, map, k, 0);
        int[] result = new int [2*k];
        if(tmpResult.size() == 0){
            return null;
        }
        for (int i = 0; i < 2*k; i++) {
             result[i] = tmpResult.get(i);
        }
        return result;
    }

    private void keepDistance2Helper(List<Integer> tmpResult, Map<Integer, Integer> map, int k, int index){
        if(index == 2 * k){
            return;
        }
        for (int i = 1; i <= k ; i++) {
            boolean addFlag = false;
            if(map.get(i) == 2){
                addFlag = true;
            }else if(map.get(i) == 1){
                if(index - i - 1 >=0 && tmpResult.get(index - i - 1) == i){
                    addFlag = true;
                }
            }
            if(addFlag){
                tmpResult.add(i);
                map.put(i, map.get(i) -1);
                keepDistance2Helper(tmpResult, map, k, index + 1);
                if(tmpResult.size() == 2 * k){
                    return;
                }else{
                    map.put(i, map.get(i) + 1);
                    tmpResult.remove(tmpResult.size() - 1);
                }
            }
        }
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
        time:O(n!*n)
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
        finally n == 4, we need to check the rest part of number . We drop the result if the rest part number > 255.
        After inserting ".", we also need to check the part value of the number before inserted dot.
        I use IPvalid to check
         */

        public List<String> restorIP2(String ip){
            if(ip.length() < 4 || ip.length() > 12){
                return null;
            }
            StringBuilder sb = new StringBuilder(ip);
            List<String> result = new ArrayList<>();
            restorIP2Helper(result, sb, 0, 1);
            return result;
        }

        private void restorIP2Helper(List<String> result, StringBuilder sb, int level, int insert){
            if(level == 4){
                if(IPvalid2(sb.substring(insert-1))){
                    result.add(new String(sb));
                }
                return;
            }
            for (int i = insert; i <insert + 3 ; i++) {
                sb.insert(i, '.');
                if(IPvalid2(sb.substring(insert - 1, i))) {
                    restorIP2Helper(result, sb, level + 1, i + 2);
                }
                sb.deleteCharAt(i);
            }

        }

        private boolean IPvalid2(String string){
            int m = string.length();
            if(m > 3){
                return false;
            }
            return string.charAt(0)=='0' ? m==1 : (Integer.valueOf(string) <= 255);
        }



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
                if(IPvalid(subString)){
                    result.add(new String(sb.toString()));
                }
                return;
            }

            for(int i= insertPosition; i < insertPosition + 3 && i < sb.length(); i++){
                sb.insert(i, ".");
                if(IPvalid(sb.substring(insertPosition - 1, i))){
                    restoreIPHelper(result, sb, level + 1, i + 2);
                }

                sb.deleteCharAt(i);
            }
        }

        private boolean IPvalid(String string){

            int m = string.length();
            if (m > 3)
                return false;
            // there is OK a single 0 or single one.
            return (string.charAt(0) != '0') ? (Integer.valueOf(string) <= 255) : (m == 1);


//            if(string.length() < 3){
//                if(string.charAt(0) == '0'){
//                    return false;
//                }else if(string.length()  == 1 && )
//                return true;
//
//            }else if(string.length() == 3){
//                int result = string.compareTo("255");
//                return result <= 0;
//            }else{
//                return false;
//            }

        }

        /*
        Combinations For Telephone Pad 1
        solution: using a HashMap to store the each number represents the letters.
                 Then for each number in input, check the map.
        Assumption: the given number >= 0
         */

        public String[] combinationsTelephone1(int number){
            List<String> result = new ArrayList<>();
            // we change the int input to List and remove the 0 and 1.
            List<Integer> input = getNumberList(number);
            String ALPHABET = new String("abcdefghijklmnopqrstuvwxyz");
            HashMap<Integer, Integer> map = getNumberLetter();
            StringBuilder sb = new StringBuilder();
            CombinationsTelephone1Helper(result, sb, input, ALPHABET, map, 0);
            String[] finalResult = new String[result.size()];
            for (int i = 0; i < result.size(); i++) {
                finalResult[i] = result.get(i);
            }
            return finalResult;
        }

        private void CombinationsTelephone1Helper(List<String> result, StringBuilder sb, List<Integer> input, String ALPHABET, HashMap<Integer, Integer> map, int level){
            if(level == input.size()){
                result.add(new String(sb.toString()));
                return;
            }
                int temp = input.get(level);
                if( temp!= 7 && temp != 9){
                    for (int i = 0; i < 3; i++) {
                        int letterPostion = map.get(temp) + i;
                        char addChar = ALPHABET.charAt(letterPostion);
                        sb.append(addChar);
                        CombinationsTelephone1Helper(result, sb, input, ALPHABET, map, level + 1);
                        sb.deleteCharAt(sb.length() - 1);
                    }
                }else{
                    for (int i = 0; i < 4; i++) {
                        int letterPostion = map.get(temp) + i;
                        char addChar = ALPHABET.charAt(letterPostion);
                        sb.append(addChar);
                        CombinationsTelephone1Helper(result, sb, input, ALPHABET, map, level + 1);
                        sb.deleteCharAt(sb.length() - 1);
                    }
                }


        }

        private List<Integer> getNumberList(int number){
            List<Integer> result = new LinkedList<>();
            // we need insert the number from the front of List
            while(number != 0){
                if (number % 10 != 1 && number % 10 != 0) {
                    result.add(0, number % 10);
                }
                number = number / 10;
            }
            return result;
        }

        private  HashMap<Integer, Integer>  getNumberLetter(){
            HashMap<Integer, Integer> map = new HashMap<>();
            int position = 0;
            for (int i = 2; i < 10; i++) {
                map.put(i, position);
                if(i == 7){
                    position += 4;
                }else{
                    position += 3;
                }

            }
            return map;
        }

       public TreeNode reverseTree(TreeNode root){
            if(root == null || root.left == null){
                return root;
            }
            TreeNode newHead = reverseTree(root.left);
            root.left.left = root.right;
            root.left.right = root;
            root.left = null;
            root.right = null;
            return newHead;
       }



    public String decodeString(String s) {
        if(s == null || s.length() == 0){
            return null;
        }
        StringBuilder sb = new StringBuilder();
        decodeStringHelper(s, sb, 0 ,0);
        return sb.toString();
    }

    private void decodeStringHelper(String s, StringBuilder sb, int index, int number){
            if(index == s.length()){
                return;
            }

            char tmp = s.charAt(index);
            if(tmp >= '0' && tmp <='9'){
                if(number == 0){
                    number = tmp - '0';
                    while(s.charAt(index + 1) >= '0' && s.charAt(index + 1) <= '9' ) {
                        tmp = s.charAt(index + 1);
                        number = number * 10 + (tmp - '0');
                        index++;
                    }
                    decodeStringHelper(s, sb, index + 1, number);
                }else{
                    int innerNumber = tmp - '0';
                    while(s.charAt(index + 1) >= '0' && s.charAt(index + 1) <= '9' ) {
                        tmp = s.charAt(index + 1);
                        innerNumber = innerNumber * 10 + (tmp - '0');
                        index++;
                    }
                    decodeStringHelper(s, sb, index + 1, innerNumber);
                }
                int numberRightBacket = 0;
                while(s.charAt(index)!= ']' || numberRightBacket != 0){
                    index++;
                    if(s.charAt(index) == '['){
                        numberRightBacket++;
                    }
                    if(s.charAt(index) == ']'){
                        numberRightBacket--;
                    }
                }
                number = 0;
                decodeStringHelper(s, sb, index + 1, number);
            }else if(tmp == '[' && number != 0){
                for (int i = 0; i < number; i++) {
                    decodeStringHelper(s, sb, index + 1, number);
                }
            }else if(tmp == ']'){
                return;
            }else{
                // the tmp is letter
                sb.append(tmp);
                decodeStringHelper(s, sb, index + 1, number);
            }

    }

    public List<String> findItinerary(List<List<String>> tickets){
            List<String> result = new ArrayList<>();
            if(tickets==null || tickets.size() == 0){
                return result;
            }
            result.add("JFK");
            HashMap<String, List<String>> map = getAirportMap(tickets);
            findItineraryHelper(result,map,"JFK", tickets.size() + 1);
            return result;
    }

    public HashMap<String, List<String>> getAirportMap(List<List<String>> tickets){
        HashMap<String, List<String>> finalResult = new HashMap<>();
        for(List<String> ticket : tickets){
            String start = ticket.get(0);
            String destination = ticket.get(1);
            if(finalResult.containsKey(start)){
                finalResult.get(start).add(destination);
            }else{
                List<String> destinations = new ArrayList<>();
                destinations.add(destination);
                finalResult.put(start, destinations);
            }
        }
        return finalResult;
    }

    private void findItineraryHelper(List<String> result,HashMap<String, List<String>> map, String start, int length){
        if (result.size() == length) {
            return;
        }
        if (map.containsKey(start) && map.get(start).size() != 0) {
            List<String> destinations = map.get(start);
            List<String> list = new ArrayList<>(map.get(start));
            Collections.sort(list);
            for (String transit : list) {
                destinations.remove(transit);
                start = transit;
                result.add(transit);
                findItineraryHelper(result, map, start, length);
                if (result.size() == length) return;
                result.remove(result.size() - 1);
                destinations.add(transit);
            }
        }

    }


    public int findCircleNum(int[][] M){
        if (M == null || M.length == 0) {
            return 0;
        }
        int number = 0;
        for (int i = 0; i < M.length; i++) {
            for (int j = 0; j < M.length; j++) {
                if (M[i][j] == 1) {
                    number++;
                    findCircleNumHelper(M, i);
                }
            }
        }
        return number;
    }

    private void findCircleNumHelper(int[][] M, int start) {
        M[start][start] = 0;
        for (int i = 0; i < M.length; i++) {
            if (M[start][i] == 1) {
                M[start][i] = 0;
                M[i][start] = 0;
                findCircleNumHelper(M, i);
            }
        }
    }

    public int maxDepth(Node root) {
        if (root == null) {
            return 0;
        }

        int max = 0;
        if (root.children == null || root.children.size() == 0) {
            return 1;
        }
        for (int i = 0; i < root.children.size(); i++) {
            int tmpLength = maxDepth(root.children.get(i));
            max = Math.max(max, tmpLength);
        }
        return max + 1;
    }


    public int findPaths(int m, int n, int N, int i, int j) {
        if(m == 0 || n == 0 || N == 0){
            return 0;
        }

        return helper(m, n, N, i, j, 0);
    }

    private int helper(int m, int n, int N, int i, int j ,int step) {
        if (i < 0 || j < 0 || i == m || j == n) {
            return 1;

        }
        if (step ==  N ) {
            return 0;
        }

        int up = helper(m, n, N, i - 1, j,step + 1);
        int down = helper(m, n, N, i + 1, j,step +1  );
        int left = helper(m, n, N, i, j - 1,step +1);
        int right = helper(m, n, N, i, j + 1,step +1);
        return up + down + left + right;
    }

    /*Rreconstruct Binary tree with Preorder  And Inorder
        we do not need the int[] inorder because we has used map to replace it.
        we still need left and right index in inorder due to we have to use those two numbers to calculate the length of
        left child and right child.
     */
    public TreeNode buildTree1(int[] preorder, int[] inorder) {
        if(preorder == null || preorder.length ==0||inorder.length == 0 || inorder.length != preorder.length){
            return null;
        }
        Map<Integer, Integer> map = getInorderMap(inorder);
        return buildTree1Helper(map, preorder,0, preorder.length - 1, 0, inorder.length - 1);
    }

    private Map<Integer,Integer> getInorderMap(int[] inorder){
        Map<Integer,Integer> map = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
        return map;
    }

    private TreeNode buildTree1Helper(Map<Integer, Integer> map, int[] preorder, int preleft, int preright, int inleft, int inright){
        if(inleft > inright){
            return null;
        }

        TreeNode root = new TreeNode(preorder[preleft]);
        int inmid = map.get(root.key);

        root.left = buildTree1Helper(map, preorder, preleft + 1, preleft + (inmid-inleft), inleft, inmid- 1);
        root.right = buildTree1Helper(map, preorder, preleft + (inmid - inleft) + 1, preright, inmid + 1, inright);
        return root;
    }

    /*
    Rreconstruct Binary tree with Postorder  And Inorder
     */

    public TreeNode buildTree2(int[] inorder, int[] postorder) {
        if(inorder == null || postorder == null || inorder.length == 0 || postorder.length ==0 || postorder.length != inorder.length){
            return null;
        }
        Map<Integer, Integer> map = getInorderMap(inorder);
        return buildTree2Helper(postorder, map, 0, inorder.length - 1, 0, postorder.length - 1);
    }

    private TreeNode buildTree2Helper(int[] postorder, Map<Integer,Integer> map, int inleft, int inright, int postleft, int postright){
        if(inleft > inright){
            return null;
        }
        int inmid = map.get(postorder[postright]);
        TreeNode root = new TreeNode(postorder[postright]);
        root.left = buildTree2Helper(postorder, map, inleft, inmid - 1, postleft, postright - (inright - inmid) - 1);
        root.right = buildTree2Helper(postorder, map, inmid + 1, inright, postright-(inright - inmid), postright - 1);
        return root;
    }

    /*
    Course Schedule II
    There are a total of n courses you have to take, labeled from 0 to n-1.
    Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]
    Given the total number of courses and a list of prerequisite pairs, return the ordering of courses you should take to finish all courses.
    There may be multiple correct orders, you just need to return one of them. If it is impossible to finish all courses, return an empty array.
     */

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        if (numCourses == 0){
            return new int[0];
        }
        int[] courses = new int[numCourses];
        for (int i = 0; i < numCourses; i++) {
            courses[i] = i;
        }
        int[] result = findOrderHelper(courses, numCourses, prerequisites, 0);
        if(result == null){
            return new int[0];
        }else{
            return result;
        }
    }

    private int[] findOrderHelper(int[] courses, int numCourses, int[][] prerequisites, int level) {
        if (level == numCourses) {
            if (checkAvliable(courses, prerequisites)) {
                return courses;
            } else {
                return null;
            }
        }

        for (int i = level; i < courses.length; i++) {
            intSwap(courses, level, i);
            int[] result = findOrderHelper(courses, numCourses, prerequisites, level + 1);
            if (result != null) {
                return result;
            }
            intSwap(courses, level, i);
        }
        return null;
    }

    private boolean checkAvliable(int[] courses, int[][] prerequisites) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < courses.length; i++) {
            list.add(courses[i]);
        }
        for (int i = 0; i < prerequisites.length; i++) {
            if(list.indexOf(prerequisites[i][0]) < list.indexOf(prerequisites[i][1])){
                return false;
            }
        }
        return true;
    }

    /*
    public List<Integer> distanceK(TreeNode  root, TreeNode target, int K){
        List<Integer> ans = new LinkedList<>();
        distanceKHelper(root, target, ans, K);
        return ans;
    }

    // the return value is the distance between the root and target. -1 means we haven't find target yet.
    private int distanceKHelper(TreeNode root, TreeNode target, List<Integer> ans, int K){
        if(root == null){
            return -1;
        }else if(root ==target){
            subtree_add(ans, root, 0, K);
            return 1;
        }else{
            int L =distanceKHelper(root.left, target, ans, K);
            int R = distanceKHelper(root.right, target, ans, K);
            if(L != -1) {
                if (L == K) {
                    ans.add(root.key);
                }else {
                    subtree_add(ans, root.right, L + 1, K);
                }
                return L + 1;
            }else  if( R != -1){
                if( R == K){
                    ans.add(root.key);
                }else{
                    subtree_add(ans, root.left, R + 1, K);
                }
                return R + 1;
            }else{
                return - 1;
            }
        }
    }

    private void subtree_add(List<Integer> ans,TreeNode root, int dist, int K){
        if(root == null){
            return;
        }
        if(dist == K){
            ans.add(root.key);
        }else{
            subtree_add(ans, root.left, dist + 1, K);
            subtree_add(ans, root.right, dist + 1, K);
        }
    }

     */

    public List<Integer> distanceK(TreeNode root, TreeNode target, int k){
        List<Integer> result = new ArrayList<>();
        distanceKHelper(result, root, target, k);
        return result;
    }

    private int distanceKHelper(List<Integer> result, TreeNode root, TreeNode target, int k){
        if(root == null) {
            return -1;
        }else  if(root == target){
            subtree_add(result, root, k);
            return 0;
        }else{
            int L = distanceKHelper(result, root.left, target, k) + 1;
            int R = distanceKHelper(result, root.right, target, k);
            if(L != 0){
                if(L == k){
                    result.add(root.key);
                } else{
                    subtree_add(result, root.right, k - L - 1);
                }
                return L;
            }else if(R != 0){
                if(R == k){
                    result.add(root.key);
                }else{
                    subtree_add(result, root.left, k - R - 1);
                }
                return R;
            }
            return -1;
        }
    }

    private void subtree_add(List<Integer> result, TreeNode root, int k){
       if(root == null) {
           return;
       }
       if(k == 0){
           result.add(root.key);
           return;
       }
       subtree_add(result, root.left, k - 1);
       subtree_add(result, root.right, k - 1);
    }

    /*
    Decode Ways
    Input: "12"
    Output: 2
    Explanation: It could be decoded as "AB" (1 2) or "L" (12).
     */

    public int numDecodings(String string){
        if(string == null || string.length() == 0){
            return 0;
        }
        return numDecodingsHelper(string, 0);
    }

    private int numDecodingsHelper(String string, int index){
        if(index == string.length()){
            return 1;
        }
        if(string.charAt(index)== '0'){
            return 0;
        }
        int left = 0;
        int right = 0;
        left = numDecodingsHelper(string, index + 1);
        if(index + 2 <= string.length() && (string.charAt(index)=='1' || string.charAt(index) == '2' && string.charAt(index + 1) <= '6')){
            right = numDecodingsHelper(string, index + 2);
        }
        return left + right;
    }


    /*
    Unique Binary Search Trees II
    Given an integer n, generate all structurally unique BST's (binary search trees) that store values 1 ... n.
     */
    public List<TreeNode>  generateTrees(int n ){
        if(n == 0){
            return new LinkedList<TreeNode>();
        }
        return generateTreesHelper(1 ,n);
    }

    private LinkedList<TreeNode> generateTreesHelper(int start, int end){
        LinkedList<TreeNode> all_trees = new LinkedList<>();
        if(start > end){
            all_trees.add(null);
            return  all_trees;
        }

        for (int i = start; i <= end ; i++) {
            LinkedList<TreeNode> left_trees = generateTreesHelper(start, i - 1);

            LinkedList<TreeNode> right_trees = generateTreesHelper(i + 1, end);

            for(TreeNode l : left_trees){
                for(TreeNode r : right_trees){
                    TreeNode current_tree = new TreeNode(i);
                    current_tree.left = l;
                    current_tree.right = r;
                    all_trees.add(current_tree);
                }
            }
        }
        return all_trees;
    }

    /*
    Burst Balloons
    Given n balloons, indexed from 0 to n-1. Each balloon is painted with a number on it represented by array nums. You are asked to burst all the balloons.
    If the you burst balloon i you will get nums[left] * nums[i] * nums[right] coins. Here left and right are adjacent indices of i.
    After the burst, the left and right then becomes adjacent.

    Find the maximum coins you can collect by bursting the balloons wisely.
     */

    public int maxCoins(int[] nums){
        if(nums == null || nums.length == 0){
            return 0;
        }
        List<Integer> array = new LinkedList<>();
        for(int i : nums){
            array.add(i);
        }
        return maxCoinsHelper(0, 0, array);
    }

    private int maxCoinsHelper(int result, int Max, List<Integer> array){
        if(array.size() == 0){
            Max = Math.max(Max, result);
            return Max;
        }

        for (int i = 0; i < array.size(); i++) {
            int center = array.get(i);
            int left;
            int right;
            if(i - 1 < 0){
                 left = 1;
            }else{
                 left = array.get(i-1);
            }
            if(i + 1 >= array.size()){
                 right = 1;
            }else{
                 right = array.get(i + 1);
            }
            result += center * left * right;
            array.remove(i);
            Max = maxCoinsHelper(result, Max, array);
            array.add(i, center);
            result -= center * left * right;
        }
        return Max;
    }

    /*
    The Knight's Dialer
     */

    public Integer KnightDialer(int n){
        if(n == 0){
            return 0;
        }
        int[][] dialer = getDialerMap();
        HashMap<Integer, Integer> read = new HashMap<>();
        int[] first = {1,2,3,4,5,6,7,8,9,0};
        int result = KnightDialerhelper(dialer, 0, n, read, 0, first);
        return result;
    }

    private int KnightDialerhelper(int[][] dialer, int steps, int n, HashMap<Integer,Integer> readed, int level, int[] nextDials){
        if(level == n || nextDials == null || nextDials.length == 0){
            return readed.size();
        }

        for(int nextDial : nextDials){
            if(!readed.containsKey(nextDial)){
                // if the dial has not been read
                readed.put(nextDial, 1);
            }else{
                //if the dial has been read
                readed.put(nextDial, readed.get(nextDial) + 1);
            }
            int tmp = KnightDialerhelper(dialer, steps, n, readed, level + 1, dialer[nextDial]);
            if(readed.get(nextDial) == 1){
                readed.remove(nextDial);
            }else{
                readed.put(nextDial, readed.get(nextDial) - 1);
            }
            steps = Math.max(steps, tmp);
        }
        return steps;
    }

    public int[][] getDialerMap(){
        int[][] matrix = {
                {4, 6},//0
                {6, 8},//1
                {7, 9},//2
                {4, 8},//3
                {0, 3, 9},//4
                {},//5
                {0, 1, 7},//6
                {2, 6},//7
                {1, 3},//8
                {2 ,4}//9
        };
        return matrix;
    }

    /*
    the area of black retango
     */
    public int areaOfBlack(int[][] matrix, int[] position){
        if(position == null){
            return 0;
        }
        int x = position[0];
        int y = position[1];
        if(x < 0||x > matrix.length - 1 || y < 0 || y > matrix[0].length - 1 || matrix[x][y] == 0){
            return 0;
        }

        return areaOfBlackHelper(matrix,0, x,y,x,x,y,y);

    }

    private int areaOfBlackHelper(int[][] matrix, int area, int x, int y, int minX, int maxX, int minY, int maxY){
        if(matrix[x][y] == 0){
            return area;
        }
        if(x<minX) minX = x;
        if(x>maxX) maxX = x;
        if(y<minY) minY = y;
        if(y>maxY) maxY = y;
        int tmpArea = (maxX - minX + 1) * (maxY - minY + 1);
        area = Math.max(area, tmpArea);

        matrix[x][y] = 0;
        int areaD = 0, areaU = 0, areaL=0, areaR = 0;
        if(x + 1 < matrix.length) areaD = areaOfBlackHelper(matrix, area, x + 1, y, minX, maxX, minY, maxY);
        if(x - 1 >= 0) areaU = areaOfBlackHelper(matrix, area, x - 1, y, minX, maxX, minY, maxY);
        if(y + 1 < matrix[x].length) areaR = areaOfBlackHelper(matrix, area, x, y + 1, minX, maxX, minY, maxY);
        if(y - 1 >= 0) areaL = areaOfBlackHelper(matrix, area, x, y- 1, minX, maxX, minY, maxY);

        int result = Math.max(areaU, areaD);
        result = Math.max(result, areaL);
        result = Math.max(result, areaR);
        return result;
    }



}
