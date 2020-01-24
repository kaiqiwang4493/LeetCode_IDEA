package parctices;

import java.lang.String;
import java.util.HashSet;
import java.util.Set;

public class DynamicProgramming {

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
       for(int i = 1; i< array.length; i++){
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
     */

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
                    result = Math.max(result, M[i][j]);
                }
            }
        }
        return result;
    }


    /*
    Largest Square Of 1s(ignore the 0s inside the square)
    {0,1,1,1,1},
    {0,1,0,0,1},
    {1,1,1,0,1},
    {0,1,1,1,1},
    {1,0,1,1,0},
    the result == 4
     */


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

    /*
    Edit Distance
    Given two strings of alphanumeric characters,
    determine the minimum number of Replace, Delete, and Insert operations needed
    to transform one string into the other.

    When we meet the question of finding the different of same part of two string,
    we SHOULD come up the solution base on 2D array that has extra row and column firstly.
     */
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
}
