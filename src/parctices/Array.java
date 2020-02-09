package parctices;

import java.util.*;
import java.lang.StringBuilder;

public class Array {
    /*
    2 Sum All Pair I
    Find all pairs of elements in a given array that sum to the given target number.
     Return all the pairs of indices.
     */

    public List<List<Integer>> allPairs(int[] array, int target){
        List<List<Integer>> result = new ArrayList<>();
        if(array.length == 0){
            return result;
        }
        // key is the number of element, value is the list of position of the element
        // we must use the list as value, because we have to consider that there are duplicated element
        Map<Integer, List<Integer>> map = new HashMap<>();
        for(int i = 0; i < array.length; i++){
            int cur = target - array[i];
           if(map.containsKey(cur)){
               for(int j : map.get(cur)){
                   List<Integer> tempResult = new ArrayList<>();
                   tempResult.add(j);
                   tempResult.add(i);
                   result.add(tempResult);
               }
           }
           // add the new element into map.
           if(map.containsKey(array[i])){
               map.get(array[i]).add(i);
           }else{
               List<Integer> temp = new ArrayList<>();
               temp.add(i);
               map.put(array[i], temp);
           }
        }
        return result;
    }

    /*
    2 Sum All Pair II
   Find all pairs of elements in a given array that sum to the pair the given target number.
    Return all the distinct pairs of values.
    solution: use the value as a counter to recorder the times of number appears in array,
    we should consider the means of key and value and when update result separately.
    1.what the mean of map
    2.how to use the map
    3.how to maintain the map.
     */

    public List<List<Integer>> allPairs2(int[] array, int target){
        List<List<Integer>> result = new ArrayList<>();
       if(array.length == 0){
           return result;
       }
       Map<Integer, Integer> map = new HashMap<>();
       for(int num : array){
           Integer count = map.get(num);

           if(num * 2 == target && count == 1){
               result.add(Arrays.asList(num, num));
           }else if(map.containsKey(target - num) && count == null){
               result.add(Arrays.asList(target-num, num));
           }
           if(count == null){
               map.put(num, 1);
           }else{
               map.put(num, count + 1);
           }
       }
       return result;
    }


    /*
    3 Sum
    Determine if there exists three elements in a given array that sum to the given target number.
    Return all the triple of values that sums to target.
     */


    /*
    Given a string, we can insert at most one empty space between each pair of adjacent letters.
     */

    public void printPermutations(String str){
        if(str.length() == 0){
            return;
        }
       StringBuilder builder = new StringBuilder(str);
        printPermutationsHelper(builder, 1, builder.length(), 0);
    }

    private void printPermutationsHelper(StringBuilder builder, int position, int length, int time){
        if(time >= length - 1){
            if(builder.charAt(builder.length() - 1) != '_'){
                System.out.println(builder.toString());
            }
            return;
        }

        builder.insert(position, "_");
        printPermutationsHelper(builder, position + 2,length, time + 1);
        builder.deleteCharAt(position);
        printPermutationsHelper(builder, position + 1,length, time + 1);
    }
}
