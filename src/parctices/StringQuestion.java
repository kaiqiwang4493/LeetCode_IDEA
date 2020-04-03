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

        /*
        Reverse Words in A Sentence I
        I love Google -> Google love I
        solution: 1. We need to create a method that can reverse all element in a part of char[].
                         the parameters is array, left-end and right-end.
                  2. Reverse the whole String first. then use Slide window to pick up the words then revers them.
         */

        public String reverseWords1(String input){
            if(input == null || input.length() <= 1){
                return input;
            }
            char[] array= input.toCharArray();
            //reverse the whole input string.
            reverseWhole(array, 0, array.length - 1);

            //initialize the slide window
            int slow = 0;
            int fast = 0;
            while(fast < array.length){
                if(fast == array.length - 1){
                    reverseWhole(array, slow, fast);
                }
                if(array[fast] == ' '){
                    reverseWhole(array, slow, fast -1);
                    slow = fast + 1;
                }
                fast++;
            }
            return new String(array);
        }

        private void reverseWhole(char[] array, int left, int right){
            while(left < right){
                char temp = array[left];
                array[left] = array[right];
                array[right] = temp;
                left++;
                right--;
            }
        }

        /*
        Reverse Words in A Sentence II
        Tunrcate all heading trailing and duplicate space characters.
         */
        public String reverserWords2(String input){
            if(input == null|| input.length() == 0){
                return input;
            }
            char[] array = input.toCharArray();

            return reverseWords1(removeSpaces(array));
        }

        public String removeSpaces(char[] array){
            int slow = -1;
            int fast = 0;
            while(array[fast] == ' '){
                fast++;
            }

            while(fast < array.length){
                if(array[fast] != ' '){
                    slow++;
                    array[slow] = array[fast];
                    //charArraySwap(array, slow, fast);
                    if(fast + 1 < array.length  -1 && array[fast + 1] == ' '){
                        fast++;
                        slow++;
                        array[slow] = array[fast];
                    }
                }
                fast++;
            }
            // If the last element is not space, the number of return string is slow + 1(because the first index of array is 0).
            // the slow may includes the space if the last the character is space.
            //Because of array[fast] == ' ' and fast + 1 =array.length.
            if(array[slow] == ' ') {
                return new String(array,0, slow);
            }else{
                return new String(array, 0, slow + 1);
            }
        }

        private void charArraySwap(char[] array, int left, int right){
            char temp = array[left];
            array[left] = array[right];
            array[right] = temp;
        }


        /*
        * Longest Substring With Only Unique Characters.
         */

        public int longestSubstring(String input){
            if(input == null || input.length() <= 1){
                return 0;
            }
            char[] array = input.toCharArray();
            int right = 0;
            int left = 0;
            Set<Character> set = new HashSet<>();
            int result = 0;

            while(right < array.length){
                if(set.add(array[right])){
                    result = Math.max(result, right - left + 1);
                    right++;
                }else{
                    set.remove(array[left]);
                    left++;
                }
            }
            return result;
        }

}


