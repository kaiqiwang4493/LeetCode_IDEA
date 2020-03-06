package parctices;

import java.util.*;

public class StringQuestion {
    /*
    You are given string: s,p
    Find out the starting indices of all Anagrams of p which are substrings of s
    Hint: sliding window
    Caution: the repeat time of character in sliding window
     */

    public List<Integer> findAnagrams(String p , String s){
        List<Integer> result = new ArrayList<>();
        if(p.length() == 0){
            return result;
        }
        if(p.length()<s.length()) {
            return result;
        }

        Map<Character, Integer> map = getMap(s);
        int match = 0;

        for(int i = 0; i < p.length(); i++){
            char temp = p.charAt(i);
            Integer count = map.get(temp);
            if(count != null){
                map.put(temp, count - 1);
                if(count ==1){
                    match++;
                }
            }

            if(i>=s.length()){
                temp = p.charAt(i - s.length());
                count = map.get(temp);
                if(count != null){
                    map.put(temp, count + 1);
                    if(count == 0){
                        match--;
                    }
                }

            }

            if(match == map.size()){
                result.add(i-s.length()+ 1);
            }
        }

        return result;
    }

    private Map<Character,Integer> getMap(String s){
        Map<Character, Integer> map = new HashMap<>();
        for(int i = 0; i<s.length(); i++){
            Integer count = map.get(s.charAt(i));
            if(count != null){
                map.put(s.charAt(i), count + 1);
            }else{
                map.put(s.charAt(i),1);
            }
        }
        return map;
    }

    public int MinReplace(String source, String target){
        if(target.length() == 0){
            return -1;
        }

        Map<Character, Integer> map = getMap(target);

        for(int i = 0; i < target.length(); i++){
            Character temp = source.charAt(i);
            Integer count = map.get(temp);
            if(count != null){
                if(count == 1){
                    map.remove(temp);
                }else{
                    map.put(temp, count - 1);
                }
            }
        }
        int result = 0;
        for(Integer temp : map.values()){
            result += temp;
        }
        return result;
    }

    /*
    Minimum size subarray sum
     */
    public int MinSubSum(int[] array, int target){
            if(array.length == 0){
                return 0;
            }

            int fast = 0;
            int slow = 0;
            int sum = 0;
            int result = array.length;

            while(slow < array.length){
                if(fast < array.length && array[fast] >= target){
                    return 1;
                }
                if(sum >= target && fast - slow < result){
                    result = fast - slow;
                }
                if(fast == array.length && sum < target){
                    return result;
                }
                if(sum < target && fast < array.length){
                    sum += array[fast];
                    fast++;
                }else if(sum >= target) {
                    sum -= array[slow];
                    slow++;
                }
            }
            return result;
        }

        /*
        Longest Substring without repeating chareacters.

         */

        public int longest(String input){
            if(input.length() == 1){
                return 1;
            }

            int slow = 0;
            int fast = 0;
            Set<Character> set = new HashSet<>();
            int result = 0;

            while(fast < input.length()){
                Character ch = input.charAt(fast);
                if(!set.contains(ch)){
                    set.add(ch);
                    result = Math.max(result, fast - slow + 1);
                    fast++;
                }else{
                    set.remove(input.charAt(slow));
                    slow++;
                }
            }
            return result;

        }



}


