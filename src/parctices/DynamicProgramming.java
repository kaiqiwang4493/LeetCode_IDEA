package parctices;

import java.beans.BeanInfo;
import java.lang.String;
import java.util.*;

public class DynamicProgramming<pubilc> {

    /*
    Max Product Of Cutting Rope
    n = 12, the max product is 3 * 3 * 3 * 3 = 81
    (cut the rope into 4 pieces with length of each is 3).
    Assumption: at least we have to cut once.
     */
    public int maxProduct(int length){
        int[] M = new int[length + 1];
        M[0] = 0;
        //we cannot cut the rop when the length == 1;
        M[1] = 1;
        for(int i = 1; i <= length; i++){
            int curMax = Integer.MIN_VALUE;
            // we also can use j<i/2, the result is symmetrical.
            for(int j = 1; j<i; j++){
                // j*M[i-j] is the situation of the right side of rop still has to been cut.
                // j*(i - j) is the situation of the right side of rop doesn't be cut.
                curMax = Math.max(curMax, Math.max(j * M[i-j], j * (i-j)));
            }
            M[i] = curMax;
        }
        return M[length];
    }

    /*
    Array Hopper I
    Given an array A of non-negative integers, you are initially positioned at index 0 of the array.
     */

    public boolean canJump1(int[] array){
        if(array.length == 1){
            return true;
        }
        boolean[] M = new boolean[array.length];
        M[M.length-1] = true;
        for(int i = M.length-2; i>=0; i--){
            // caution the position of M[i] = false.
            //it must before break, else the M[i] is always false
            M[i] = false;
            for(int j = 1; j<= array[i]; j++){
                if((j+i)<M.length && M[j + i]){
                    M[i] = true;
                    break;
                }
            }
        }
        return M[0];
    }

    /*
    Array Hopper II
    Given an array A of non-negative integers,
    you are initially positioned at index 0 of the array.
    Determine the minimum number of jumps you need to reach the end of array.
    If you can not reach the end of the array, return -1.
     */
    public int minJump(int[] array){
        if(array.length == 1){
            return 0;
        }
        int[] M = new int[array.length];
        M[array.length - 1] = 0;
        for(int i = array.length - 2; i >= 0; i--){
            M[i] = -1;
            int curMin = Integer.MAX_VALUE;
            for(int j = 0; j <= array[i]; j++){
                if((i+j)<M.length && M[i+j]>=0){
                    curMin = Math.min(curMin, M[i+j]);
                }
            }
            if(curMin!=Integer.MAX_VALUE) M[i] = curMin + 1;
        }
        return M[0];
    }

    /*
    Dictionary Word I
    Given a word and a dictionary,
    determine if it can be composed by concatenating words from the given dictionary.
     */

    public boolean canBreak(String input, String[] dict){
        char[] array = input.toCharArray();
        Set<String> set= getSet(dict);
        boolean[] M = new boolean[array.length + 1];
        M[0] = true;
        //the most important part is we add extra M[0]
        // thus we merge the checking whole substring and part of substring into same expression
       for(int i = 1; i< M.length; i++){
           for(int j = 0; j < i; j++){
               if(set.contains(input.substring(j, i)) && M[j]){
                   M[i] = true;
                   break;
               }
           }
       }
        return M[M.length - 1];
    }

    private Set<String> getSet(String[] dict){
        Set<String> set = new HashSet<>();
        for(String i : dict){
            set.add(i);
        }
        return set;
    }

    /*
    Largest Square Of 1s
    Determine the largest square of 1s in a binary matrix (a binary matrix only contains 0 and 1),
    return the length of the largest square.


    public int SquareConsist1(int[][] matrix){
        if(matrix.length == 0){
            return 0;
        }
        int N = matrix.length;
        int result = 0;
        int[][] M = new int[matrix.length][matrix[0].length];
        for(int i =0; i < N; i++){
            if(matrix[i][0] == 1){
                M[i][0] = 1;
                result = 1;
            }
            if(matrix[0][i] == 1){
                M[0][i] = 1;
                result = 1;
            }
        }

        for(int i = 1; i<N; i++){
            for(int j = 1; j<N; j++){
                if(matrix[i][j] == 1){
                    int temp = Math.min(M[i-1][j-1], M[i-1][j]);
                    M[i][j] = Math.min(temp, M[i][j-1]) + 1;
                    result =  Math.max(result, M[i][j]);
                }
            }
        }
        return result;
    }
     */

    public int SquareConsist1(int[][] matrix){
        if(matrix == null || matrix. length == 0) return 0;
        int[][] M = new int[matrix.length][matrix[0].length];
        int Max = 0;

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j <matrix[0].length ; j++) {
                if(i == 0 || j == 0){
                    M[i][j] = matrix[i][j];
                }else{
                    if(matrix[i][j] == 0){
                        M[i][j] =0;
                    }else{
                        int min = Math.min(M[i][j - 1], M[i - 1][j]);
                        min = Math.min(min, M[i- 1][j - 1]);
                        M[i][j] = min + 1;

                    }
                }
                // caution: we must caution the position of update Max. once we change the M[i][j] value,
                // we have to update the value of Max.
                Max = Math.max(Max, M[i][j]);
            }
        }
        return Max;
    }


    /*
    Largest Square Of 1s(ignore the 0s inside the square)
    {0,1,1,1,1},
    {0,1,0,0,1},
    {1,1,1,0,1},
    {0,1,1,1,1},
    {1,0,1,1,0},
    the result == 4

    public int largest(int[][] matrix){
        if(matrix.length == 0){
            return 0;
        }
        int[][] right = getRightMatrix(matrix);
        int[][] left = getLeftMatrix(matrix);
        int[][] down = getDownMatrix(matrix);
        int[][] up = getUpMatrix(matrix);
        int maxLength = 0;

        for(int i = 0; i < matrix.length; i++){
            for(int j = 0; j < matrix[i].length; j++){
                if(matrix[i][j] ==1){
                    //get the minmum number.
                    int length = Math.min(left[i][j],up[i][j]);
                    int checkRange = length;
                    for(int offest = 1; offest +1 <= checkRange; offest++){
                        if(down[i+offest][j+offest] !=0 && right[i+offest][j+offest] !=0){
                            //get the minimum number from right.matrix, down.matrix, length, offest+1;
                            int temp = Math.min(right[i+offest][j+offest],down[i+offest][j+offest]);
                            length = Math.min(length, temp);
                        }
                    }
                    maxLength = Math.max(maxLength, length);
                }
            }
        }
        return maxLength;
    }


    private int[][] getLeftMatrix(int[][] matrix){
        int[][] cur = new int[matrix.length][matrix[0].length];
        for(int i = 0; i < matrix.length; i++){
            int number = 0;
            for(int j = matrix[i].length - 1; j>=0; j--){
                if(matrix[i][j] == 1) {
                    cur[i][j] = number + 1;
                    number++;
                }else{
                    cur[i][j] = 0;
                    number = 0;
                }
            }
        }
        return cur;
    }

    private int[][] getRightMatrix(int[][] matrix){
        int[][] cur = new int[matrix.length][matrix[0].length];
        for(int i = 0; i < matrix.length; i++){
            int number = 0;
            for(int j = 0; j < matrix[i].length; j++){
                if(matrix[i][j] == 1) {
                    cur[i][j] = number + 1;
                    number++;
                }else{
                    cur[i][j] = 0;
                    number = 0;
                }
            }
        }
        return cur;
    }

    private int[][] getDownMatrix(int[][] matrix){
        int[][] cur = new int[matrix.length][matrix[0].length];
        for(int i = 0; i < matrix[0].length; i++){
            int number = 0;
            for(int j = 0; j < matrix.length; j++){
                if(matrix[j][i] == 1) {
                    cur[j][i] = number + 1;
                    number++;
                }else{
                    cur[j][i] = 0;
                    number = 0;
                }
            }
        }
        return cur;
    }

    private int[][] getUpMatrix(int[][] matrix){
        int[][] cur = new int[matrix.length][matrix[0].length];
        for(int i = 0; i < matrix[0].length; i++){
            int number = 0;
            for(int j = matrix.length - 1; j >= 0; j--){
                if(matrix[j][i] == 1) {
                    cur[j][i] = number + 1;
                    number++;
                }else{
                    cur[j][i] = 0;
                    number = 0;
                }
            }
        }
        return cur;
    }

     */

    public int largest(int[][] matrix){
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0){
            return 0;
        }
        int n = matrix.length;
        int m = matrix[0].length;
        int MAX= 0;
        int[][] rl = RL(matrix);
        int[][] bt = BT(matrix);

        for (int i = 0; i < n ; i++) {
            for (int j = 0; j < m; j++) {
                int biggest = Math.min(rl[i][j], bt[i][j]);
                for (int k = biggest; k >0 ; k--) {
                    // the bottom and right sides length is not smaller than top and left sides.
                    if(rl[i + k - 1][j] >= k &&  bt[i][j + k -1] >= k){
                        MAX = Math.max(MAX, k);
                        break;
                    }
                }
            }
        }
        return MAX;
    }

    private int[][] RL(int[][] input){
        int m = input.length;
        int n = input[0].length;
        int[][] rl = new int[m][n];
        for (int i = 0; i <m ; i++) {
            for (int j = n - 1; j >= 0; j--) {
                if(input[i][j] == 0 || j == n - 1){
                    rl[i][j] = input[i][j];
                }else{
                    rl[i][j] = rl[i][j + 1] + 1;
                }
            }
        }
        return rl;
    }

    private  int[][] LR(int[][] input){
        int m = input.length;
        int n = input[0].length;
        int[][] lr = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if(input[i][j] == 0 || j == 0){
                    lr[i][j] = input[i][j];
                }else{
                    lr[i][j] = lr[i][j - 1] + 1;
                }
            }
        }
        return lr;
    }

    private int[][] TB(int[][] input){
        int m = input.length;
        int n = input[0].length;
        int[][] tb = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if(input[i][j] == 0 || i == 0){
                    tb[i][j] = input[i][j];
                }else{
                    tb[i][j] = tb[i - 1][j] + 1;
                }
            }
        }
        return tb;
    }

    private int[][] BT(int[][] input){
        int n = input.length;
        int m = input[0].length;
        int[][] bt = new int[n][m];
        for (int i = n - 1; i >= 0 ; i--) {
            for (int j = 0; j < m; j++) {
                if(input[i][j] == 0 || i == n - 1){
                    bt[i][j] = input [i][j];
                }else{
                    bt[i][j] = bt[i + 1][j] + 1;
                }
            }
        }
        return bt;
    }

    /*
    Edit Distance
    Given two strings of alphanumeric characters,
    determine the minimum number of Replace, Delete, and Insert operations needed
    to transform one string into the other.

    When we meet the question of finding the different of same part of two string,
    we SHOULD come up the solution base on 2D array that has extra row and column firstly.

    public int editDistance(String one, String two){
        int[][] M = new int[one.length() + 1][two.length() + 1];
        for(int i = 0; i<=one.length() + 1; i++){
            M[i][0] = i;
        }
        for(int i = 0; i<= two.length()+ 1; i++){
            M[0][i] = i;
        }

        for(int i = 1; i<=one.length();i++){
            for(int j = 0; j<two.length() + 1; j++){
                if(one.charAt(j - 1) == two.charAt(i - 1)){
                    // the string does not need change anything with the two same character
                    M[i][j] = M[i - 1][j - 1];
                }else{
                    //if the two characters are different,
                    //we need one step(replace or delete or add) to make two string become same
                    //we check the which operate is based on the minimum number of strings without these two characters.
                    M[i][j] = Math.min(M[i - 1][j], M[i][j - 1]);//delete and add
                    M[i][j] = Math.min(M[i][j], M[i - 1][j - 1]) + 1;//replace
                }
            }
        }
        return M[one.length()][two.length()];
    }
  */

    public int editDistance(String one, String two){
        if(one.equals(two)) return 0;
        int[][] M = new int[one.length()+ 1][two.length() + 1];
        for(int i = 0 ; i <= one.length(); i++){
            M[i][0] = i;
        }
        for (int i = 0; i <= two.length(); i++) {
            M[0][i] = i;
        }
        for (int i = 1; i <= one.length() ; i++) {
            for (int j = 1; j < two.length(); j++) {
                int min = Math.min(M[i - 1][j], M[i][j - 1]);
                min = Math.min(min, M[i - 1][j - 1]);
                if(one.charAt(i - 1) == two.charAt( j - 1)){
                    // caution this line. if the two character is the same, the editDistance = shorter subString for each String.
                    // not the M[i][j] = min.
                    M[i][j] = M[i - 1][j - 1];
                }else{
                    M[i][j] = min + 1;
                }
            }
        }
        return M[one.length()][two.length()];
    }

    /*
        Frog cross the river
        we just need to record the last reachable stone position and last step length.
     */
    public boolean canCross(int[] stones){
        if(stones.length == 0){
            return true;
        }
        int stepRange = 1;
        int lastTrue = 0;

        for(int i = 1; i < stones.length; i++){
            int stepJump = Math.abs(stones[i] - stones[lastTrue]);
            if(stepJump<= stepRange+ 1 && stepJump >= stepRange - 1){
                lastTrue = i;
                stepRange = stepJump;
            }
        }
        return lastTrue == stones.length - 1;
    }


    /*
    Longest Cross Of 1s
    Given a matrix that contains only 1s and 0s, find the largest cross which contains only 1s,
    with the same arm lengths and the four arms joining at the central point.
     */

    public int CrossLargest(int[][] matrix){
        int m = matrix.length;
        int n = matrix[0].length;
        if(matrix == null || m == 0 || n == 0) return 0;

        int[][] tb = TB(matrix);
        int[][] bt = BT(matrix);
        int[][] lr = LR(matrix);
        int[][] rl = RL(matrix);
        int max = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if(matrix[i][j] == 1){
                    int tmp = Math.min(tb[i][j], bt[i][j]);
                    tmp = Math.min(tmp, lr[i][j]);
                    tmp = Math.min(tmp, rl[i][j]);
                    max = Math.max(max, tmp);
                }
            }
        }
        return max;
    }

    /*
    Largest SubMatrix Sum
    Given a matrix that contains integers, find the submatrix with the largest sum.
    Return the sum of the submatrix.
     */
    public int largest1(int[][] matrix){
        if(matrix == null || matrix.length == 0){
            return 0;
        }
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] tb = TB2(matrix);
        int Max = Integer.MIN_VALUE;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j <= i; j++) {
                int[] array = new int[n];
                for (int k = 0; k < n; k++) {
                    array[k] = tb[i][k] - tb[j][k] + matrix[j][k];
                }
                int tmp = fingBiggestSubarray(array);
                Max = Math.max(Max, tmp);
            }
        }
        return Max;
    }

    private int[][] TB2(int[][] matrix){
        if(matrix == null || matrix.length == 0){
            return null;
        }
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] tb = new int[m][n];
        for (int i = 0; i <m; i++) {
            for (int j = 0; j < n; j++) {
                if(i == 0){
                    tb[i][j] = matrix[i][j];
                }else{
                    tb[i][j] = tb[i-1][j] + matrix[i][j];
                }
            }
        }
        return tb;
    }

    public int fingBiggestSubarray(int[] array){
        if(array == null || array.length == 0){
            return 0;
        }
        int pre = 0;
        int Max = Integer.MIN_VALUE;
        for (int i = 0; i < array.length; i++) {
            if(i ==0 || pre < 0){
                pre = array[i];
            }else{
                pre = pre + array[i];
            }
            Max = Math.max(Max, pre);
        }
        return Max;
    }



    /*
    Dictionary Word I
     */

    public boolean canBreakReview(String input, String[] dict){
        if(dict == null || input == null || input.length() == 0){
            return false;
        }
        boolean[] M = new boolean[input.length() + 1];
        M[0] = true;
        for (int i = 1; i < M.length; i++) {
            for (int j = 0; j < i; j++) {
                //the substring includes charAt(j) and not includes charAt(i)
                if(dictContain(dict, input.substring(j, i)) && M[j]){
                    M[i] = true;
                    break;
                }
            }
        }
        return M[M.length - 1];
    }

    private boolean dictContain(String[] dict, String input){
        for(String string : dict){
            if(input.equals(string)){
                return true;
            }
        }
        return false;
    }

    /*
    Given a string s, find the longest palindromic substring in s.
    You may assume that the maximum length of s is 1000.
    solution: the dp[j][i] means then the substring start from index = j to index = i is or not palindromic substring.
    so: if j - i < 2 means the length of substring is two, then if charAt[j] == charAt[i] we can say the sub string is palindromic.
    Or: if j - i >2, we chech the smaller size of substring, dp[j + 1][i - 1] is the palindromic and charAt[j] == charAt[i], we also can say
    the substring is palindromic.
     */
    public String longestPalindrome(String s) {
        if(s == null || s.length() == 0){
            String result = "";
            return result;
        }

        int length = s.length();
        int left = 0;
        int size = 1;
        boolean [][] dp = new boolean[length][length];
        for(int i = 0; i < length; i++){
            dp[i][i] = true;
            for(int j = 0; j < i; j ++){
                if(i - j < 2){
                    dp[j][i] = (s.charAt(j) == s.charAt(i));
                }else{
                    dp[j][i] = (dp[j + 1][i - 1] && s.charAt(j) == s.charAt(i));
                }
                if(dp[j][i] == true && i - j + 1 > size){
                    size = i - j + 1;
                    left = j;
                }
            }
        }
        return s.substring(left, left + size);

    }

    /*
    review the coin change;
     */
    public int coinChange(int[] coins, int amount) {
        Arrays.sort(coins);
        if(coins == null||amount == 0|| amount < coins[0]){
            return -1;
        }
        int[] dp = new int[amount + 1];
        dp[0] = -1;
        for (int target = 1; target <= amount; target++) {
            int[] tmp = new int[coins.length];
            for (int face = 0; face < coins.length; face++) {
                if(target - coins[face] == 0){
                    dp[target] = 1;
                    break;
                }else if(target - coins[face] <0){
                    tmp[face] = -1;
                }else{
                    if(dp[target-coins[face]] == -1){
                        tmp[face] = -1;
                    }else{
                        tmp[face] = dp[target - coins[face]] + 1;
                    }
                }
            }
            // condition that dp value has not been update
            //that mean the target != coins[face]
            if (dp[target] == 0) {
                int tmpResult = -1;
                for (int i : tmp) {
                    if (i > 0) {
                        if (tmpResult == -1) {
                            tmpResult = i;
                        } else {
                            tmpResult = Math.min(tmpResult, i);
                        }
                    }
                }
                dp[target] = tmpResult;
            }
        }
        return dp[dp.length - 1];

    }


    public boolean wordBreak(String s, List<String> wordDict){
        Set<String> set = wordBreakGetSet(wordDict);
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                if(set.contains(s.substring(j, i)) && dp[j]){
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[dp.length - 1];
    }

    private Set<String> wordBreakGetSet(List<String> wordDict){
        Set<String> set = new HashSet<>();
        for(String string : wordDict){
            set.add(string);
        }
        return set;
    }

    /*
    Maximal Rectangle
    solution: using two for loop to decide the upper and bottom bound of rectangle. Then we calculate the difference between upper and bottom bound for each column
    then we get a new 1D array that consist on this differents. Finally we just need get the biggest sum consist on continually same value in this array.
    the value in the 1D array is actually the height of each column. And we have to ensure the height is the same for each column that make up the rectangle.
    Time = O(N^2*M)
     */



    public int maximalRectangle(char[][] matrix){
        if(matrix.length ==0) return 0;
        int maxarea = 0;
        int[][] dp = new int[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if(matrix[i][j] == '1'){
                    //get the width of the bottom line.
                    dp[i][j] = j==0 ? 1 : dp[i][j-1] + 1;
                }
                int width = dp[i][j];
                for (int k = i; k >= 0 ; k--) {
                    width = Math.min(width, dp[k][j]);
                    // area = width * high.
                    int area = width * (i-k+1);
                    maxarea = Math.max(maxarea, area);
                }
            }
        }
        return maxarea;
    }

    // the Max is the size of square containing only 1. And the return value is the area of the square.
    public int maximalSquare(char[][] matrix){
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] dp = new int[m][n];
        int Max = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if(i == 0 || j == 0){
                    dp[i][j] = matrix[i][j] == '1'? 1 : 0;
                }else{
                    if(matrix[i][j] == '1'){
                        int tmp = Math.min(dp[i- 1][j], dp[i][j - 1]);
                        tmp = Math.min(tmp, dp[i - 1][j - 1]);
                        dp[i][j] = tmp + 1;
                        Max = Math.max(Max, dp[i][j]);
                    }
                }
            }
        }
        // Max is the side length of biggest square.
        return Max * Max;
    }

    /*
    Decode Ways
    Input: "12"
    Output: 2
    Explanation: It could be decoded as "AB" (1 2) or "L" (12).
     */

    public int numDecodings(String string){
        if(string == null || string.length() ==0){
            return 0;
        }
        int[] dp = new int[string.length()];
        for (int index = 0; index < dp.length; index++) {
            if(index == 0){
                if (string.charAt(index) != '0') {
                    dp[index] = 1;
                }
                continue;
            }
            int case1 = 0;
            int case2 = 0;
            //case1, keep the last two numbers as a Integer
            if(string.charAt(index - 1)=='1' || string.charAt(index - 1) == '2' && string.charAt(index) <= '6'){
                if(index - 2 < 0){
                    case1 = 1;
                }else{
                    case1 = dp[index - 2];
                }
            }
            //case2, keep the last one number as a Integer
            if(string.charAt(index) != '0'){
                case2 = dp[index - 1];
            }
            dp[index] = case1 + case2;
        }
        return dp[dp.length - 1];
    }

    /*
    Longest String Chain
    Input: ["a","b","ba","bca","bda","bdca"]
    Output: 4
    Explanation: one of the longest word chain is "a","ba","bda","bdca".
     */

    public int longestStrChain(String[] words){
        int Biggest = 0;
        if(words == null || words.length ==0){
            return Biggest;
        }
        if(words.length == 1){
            Biggest++;
            return Biggest;
        }
        words = StringArrayOrder(words);
        int[] dp = new int[words.length];
        dp[0] = 1;

        for (int i = 1; i <words.length ; i++) {
            int maxPre = 0;
            for (int j = i - 1; j >=0 ; j--) {
                if(longestStrChainCheck(words[j], words[i])){
                   maxPre = Math.max(maxPre, dp[j]);
                }
            }
            if(maxPre == 0) {
                dp[i] = 1;
                Biggest = Math.max(Biggest, dp[i]);
            }else{
                dp[i] = maxPre + 1;
                Biggest = Math.max(Biggest, dp[i]);
            }
        }
        return Biggest;
    }
    //["ksqvsyq","ks","kss","czvh","zczpzvdhx","zczpzvh","zczpzvhx","zcpzvh","zczvh","gr","grukmj","ksqvsq","gruj","kssq","ksqsq","grukkmj","grukj","zczpzfvdhx","gru"]
    //   1          1   2    1          1        1         2         1
    private boolean longestStrChainCheck(String stringS, String stringL){
        if(stringL.length() - stringS.length() != 1){
            return false;
        }
        // check all the characters of short string are included in longer string.
        for (int i = 0; i < stringS.length(); i++) {
            int j = 0;
            while(j < stringL.length()){
                if(stringS.charAt(i) == stringL.charAt(j)){
                    break;
                }
                j++;
            }
            // find a character in short string is not found in longer string. Return false.
            if(j == stringL.length()) return false;
        }
        return true;
    }

    private String[] StringArrayOrder(String[] strings){
        if(strings == null || strings.length == 0 || strings.length == 1){
            return strings;
        }
        PriorityQueue<String> pq = new PriorityQueue<>( (string1, string2) ->{
            if (string1.length() < string2.length()) return -1;
            if(string1.length() >  string2.length()) return 1;
            return 0;
        });

        for (int i = 0; i < strings.length; i++) {
            pq.add(strings[i]);
        }

        for (int i = 0; i < strings.length; i++) {
            strings[i] = pq.poll();
        }
        return strings;
    }



    public int lenfthOfLIS(int[] nums){
       if(nums.length == 0){
           return 0;
       }
       int[] dp = new int[nums.length];
       int result = 1;
        dp[0] = 1;
        for (int i = 1; i <nums.length ; i++) {
            for (int j = 0; j < i; j++) {
                if(nums[i] > nums[j]){
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                    result = Math.max(result, dp[i]);
                }
            }
        }
       return result;
    }

    public boolean checkSubarraySum(int[] nums, int k){
        if(nums == null || nums.length == 0){
            return false;
        }
        for (int i = 0; i < nums.length; i++) {
            int sum = 0;
            //if(nums[i] == k) return ;
            for (int j = i; j < nums.length ; j++) {
                sum += nums[j];
                if(k != 0){
                    if(sum % k == 0 && i!=j ) return true;
                }else{
                    if(sum == 0 && i!=j) return true;
                }
            }
        }
        return false;
    }

    public int knightDialer(int N){
        int[][] nextPosTable = {{4,6},{6,8},{7,9},{4,8},{3,9,0},{},{1,7,0},{6,2},{1,3},{4,2}};
        long[] countTable = new long[10];
        for (int i = 0; i < 10; i++) {
            countTable[i] = 1;
        }

        for (int j = 1; j < N; j++) {
            long[] nextCountTable = new long[10];
            for (int i = 0; i <10 ; i++) {
                int[] nextPosList = nextPosTable[i];
                for(int next : nextPosList){
                    nextCountTable[next] += countTable[i];
                }
            }
            countTable = nextCountTable;
            for (int i = 0; i < 10; i++) {
                countTable[i] %= 1_000_000_007;
            }
        }
        long sum = 0;
        for (int i = 0; i < 10; i++) {
            sum += countTable[i];
        }
        return (int)(sum%1_000_000_007);
    }

}
