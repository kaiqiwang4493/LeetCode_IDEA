package parctices;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.lang.String;


public class DFS {
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
    All Subsets II
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
}
