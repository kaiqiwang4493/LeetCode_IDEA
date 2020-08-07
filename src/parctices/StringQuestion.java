package parctices;

import dataStructure.Pair;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

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

    private void charArraySwapAll(char[] array, int left, int right){
        while(left < right) {
            char temp = array[left];
            array[left] = array[right];
            array[right] = temp;
            left++;
            right--;
        }
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


        /*
        Palindromic Substrings
        Given a string, your task is to count how many palindromic substrings in this string.

        The substrings with different start indexes or end indexes are counted as different substrings even they consist of same characters.
         */

    public int countSubstrings(String s){
        if(s == null || s.length() == 0){
            return 0;
        }
        int result = 0;
        for (int i = 0; i < s.length(); i++) {
            result += countSubstringsHelper(s, i, i);
            result += countSubstringsHelper(s, i, i + 1);
        }
        return result;
    }

    private int countSubstringsHelper(String s, int left, int right){
        int tmp = 0;
        while(left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)){
            tmp++;
            left--;
            right++;
        }
        return tmp;
    }

    /*
    Longest subarray contains only 1s
    Given an array of integers that contains only 0s and 1s and a positive integer k,
    you can flip at most k 0s to 1s, return the longest subarray that contains only integer 1 after flipping.
     */
    public int longestConsecutiveOnes(int[] nums, int k){
        if(nums == null || nums.length == 0){
            return 0;
        }
        int counter = k;
        int left = 0;
        int right = 0;
        int max = 0;

        while(right < nums.length){
            if(nums[right] == 1 || counter > 0){
                if(nums[right] == 0) counter--;
                max = Math.max(max, right - left + 1);
                right++;
            }else{
                if(nums[left] == 0) counter++;
                left++;
            }
        }
        return max;
    }


    /*
    Reverse Words In A Sentence I
    “I love Google” → “Google love I”
     */

    public String reverseWords(String string){
        if(string == null || string.length() ==0){
            return string;
        }
        char[] array = string.toCharArray();
        int left= 0;
        int right = 0;

        // swap the signal word in array.
        while(right < array.length) {
            if (array[right] == ' ') {
                charArraySwapAll(array, left, right - 1);
                left = right + 1;
            } else if (right == array.length - 1) {
                charArraySwapAll(array, left, right);
            }
            right++;
        }

        //swap the whole array
        charArraySwapAll(array, 0, array.length - 1);

        return new String(array);
    }

    /*
    compress(unfinished)
     */

    public String compress(String input){
        if(input == null|| input.length() == 0){
            return input;
        }
        char[] array = input.toCharArray();
        int i = 0;
        int j = 0;
        int oneLetters = 0;
        char number = '0';
        char pre = array[0];

        while(j <= array.length){
            if(j == array.length || array[j] != pre){
                array[i] = pre;
                i++;
                if(number > '1'){
                    array[i] =number;
                    i++;
                }else{
                    oneLetters++;
                }
                number = '1';
            }else{
                number++;
            }
            if(j < array.length) pre = array[j];
            j++;
        }

        char[] result = new char[i + number];

        j = result.length - 1;
        i--;
        while(i>= 0){
            if(array[i]>='0' && array[i] <= '9'){
                for (int k = (int)'0'; k <+ (int)array[i] ; k++) {

                }

            }
        }

        System.out.println("oneLetter:" + oneLetters);
        return new String(array).substring(0 , i);
    }

    /*
    String replace
     */

    public String replaceI(String input ,String source, String target){
        if(input == null || input.length() == 0){
            return input;
        }
        char[] array = input.toCharArray();
        if(source.length() >= target.length()){
            return replaceShort(array, source, target);
        }else{
            return replaceLong(array, source, target);
        }

    }

    private String replaceShort(char[] input, String source, String target){
        int slow = 0;
        int fast = 0;
        while(fast< input.length){
            if(fast < input.length - source.length() && equalSubstring(input, fast, source)){
                copySubstring(input, slow, target);
                slow += target.length();
                fast += source.length();
            }else{
                input[slow++] = input[fast++];
            }
        }
        return new String(input, 0, slow);
    }

    private String replaceLong(char[] input, String source, String target){
        //get All the matches end posisiton in the input char array of source
        ArrayList<Integer> matches = getAllMatches(input,source);
        char[] result = new char[input.length + matches.size() * (target.length() - source.length())];
        int lastIndex = matches.size() - 1;
        int fast = input.length - 1;
        int slow = result.length - 1;
        while(fast >= 0){
            if(lastIndex > 0 && fast == matches.get(lastIndex)){
                copySubstring(result, slow - target.length() + 1, target);
                slow -= target.length();
                fast -= source.length();
                lastIndex--;
            }else{
                result[slow--] = input[fast--];
            }
        }
        return new String(result);
    }

    private ArrayList<Integer> getAllMatches(char[] input, String source){
        ArrayList<Integer> matches = new ArrayList<>();
        int i = 0;
        while(i <= input.length - source.length()){
            if(equalSubstring(input, i, source)){
                matches.add(i + source.length() - 1);
                i += source.length();
            }else{
                i++;
            }
        }
        return matches;
    }

    private boolean equalSubstring(char[] input, int fromIndex, String source){
        for (int i = 0; i < source.length(); i++) {
            if(input[fromIndex + i] != source.charAt(i)){
                return false;
            }
        }
        return true;
    }

    private void copySubstring(char[] reuslt, int fromIndex, String target){
        for (int i = 0; i < target.length(); i++) {
            reuslt[fromIndex + i] = target.charAt(i);
        }
    }

    public String decompress(String input){
        if(input == null || input.length() == 0){
            return input;
        }
        char[] inputArray = input.toCharArray();
        //step1 : create a new array as result.
        int length = 0;
        for (int i = 0; i < input.length(); i++) {
            if(input.charAt(i) > '0' && input.charAt(i) <= '9'){
                length += (int)(input.charAt(i)) - '0';
            }
        }
        char[] result = new char[length];

        //step2: copy the original array to new result
        int outIndex = result.length - 1;
        int inIndex = inputArray.length - 1;

        while(inIndex > 0){
            if(inputArray[inIndex] > '0' && inputArray[inIndex] <= '9'){
                int number = (int)(inputArray[inIndex] - '0');
                char tmp = inputArray[inIndex - 1];
                for (int i = 0; i < number; i++) {
                    result[outIndex] = tmp;
                    outIndex--;
                }
                inIndex -=2;
            }else{
                inIndex--;
            }
        }
        return new String(result);
    }

   /*
   遇到重复的characters，至多保留两个重复的数字
    Remove All Adjacent Duplicates in String II
    Remove adjacent, repeated characters in a given string, leaving only two characters for each group of such characters.
    The characters in the string are sorted in ascending order.
    */
    public String RemoveDuplicateKeepTwo(String input){
        if(input == null || input.length() == 0){
            return input;
        }
        int end = 0;
        char[] array = input.toCharArray();
        for (int i = 0; i < input.length(); i++) {
            if(i == 0 ||i == 1||array[i] != array[end - 2]){
                array[end++] = array[i];
            }
        }
        return new String(array,0,end);
   }


    /*
    去除重复了K次的characters，并且是在check完第一遍之后还需要check again
    Given a string s, a k duplicate removal consists of choosing k adjacent and equal letters from s and removing them causing the left and the right side of the deleted substring to concatenate together.

We repeatedly make k duplicate removals on s until we no longer can.

Return the final string after all such duplicate removals have been made.

It is guaranteed that the answer is unique.
*/

    public String removeDuplicates(String s, int k) {
        if(s == null || s.length() == 0 || k > s.length() || k == 0){
            return s;
        }
        StringBuilder sb = new StringBuilder(s);
        boolean flag = true;
        while (flag) {
            int index = 0;
            int count = 0;
            flag = false;
            while (index < sb.length()) {
                if (index == 0 || sb.charAt(index) != sb.charAt(index - 1)) {
                    count = 1;
                } else {
                    count++;
                }
                if (count == k) {
                    flag = true;
                    sb.delete((index - k + 1), index + 1);
                    index -= k;
                }
                index++;
            }
        }
        return sb.toString();
    }

    /*
    去除所有的重复的数字，
    Remove Adjacent Repeated Characters IV
    Repeatedly remove all adjacent, repeated characters in a given string from left to right,
    No adjacent characters should be identified in the final string.
     */

    public String deDupIV(String input){
        if(input == null || input.length() == 0){
            return input;
        }
        Deque<Character> deque = new ArrayDeque<>();
        StringBuilder sb = new StringBuilder(input);
        int index = 0;
        while(index < sb.length()){
            if(deque.isEmpty() || sb.charAt(index) != deque.peekLast()){
                deque.offerLast(sb.charAt(index));
                index++;
            }else{
                int tmp = index + 1;
                while(tmp < sb.length() && sb.charAt(tmp) == sb.charAt(index)){
                    tmp++;
                }
                sb.delete(index- 1, tmp);
                deque.pollLast();
                index=Math.max(--index, 0);
            }
        }
        return sb.toString();

    }


    public boolean wordPattern(String pattern, String str) {
        if(pattern == null || str == null){
            return false;
        }
        if(pattern.length() == 0 || str.length() == 0){
            return false;
        }

        int pPointer = 0;
        int sPointerL = 0, sPointerR = 0;
        Map<Character, String> pairs = new HashMap<>();

        // init the sPointerR position
        while(sPointerR < str.length() && str.charAt(sPointerR) != ' '){
            sPointerR++;
        }

        while(pPointer < pattern.length()){
            //case1, the pattern longer than str.
            if(sPointerL > str.length()) return false;

            if(!pairs.containsKey(pattern.charAt(pPointer))){
                for(String string : pairs.values()){
                    if(string.equals(str.substring(sPointerL, sPointerR))){
                        return false;
                    }
                }
                pairs.put(pattern.charAt(pPointer), str.substring(sPointerL, sPointerR));
            }else{
                if(!pairs.get(pattern.charAt(pPointer)).equals(str.substring(sPointerL, sPointerR))){
                    return false;
                }
            }
            //reset the three pointers.
            pPointer++;
            sPointerL = sPointerR + 1;
            sPointerR++;
            while(sPointerR < str.length() && str.charAt(sPointerR) != ' '){
                sPointerR++;
            }
        }
        // we must conside the pattern shorter than str
        return sPointerL > str.length() ? true : false;
    }

    /*
    word search
     */

    public boolean exist(char[][] board, String word){
        int m = board.length;
        int n = board[0].length;
        boolean[][] seen = new boolean[m][n];
        // the corner case, the length of word is longer than the characters.
        if(word.length() > m * n){
            return false;
        }
        boolean result = false;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if(board[i][j] == word.charAt(0)){
                    result =  existHelper(board, word,seen, i, j, 0);
                    if(result) return true;
                }
            }
        }

        return false;
    }

    private boolean existHelper(char[][] board, String word,boolean[][] seen, int x, int y, int index){
        if(index == word.length()){
            return true;
        }else if(x >= board.length || x< 0 || y >= board[x].length || y < 0 || seen[x][y] == true){
            return false;
        }

        if(board[x][y] == word.charAt(index)){
            seen[x][y] = true;
            boolean up = false,left= false, down= false, right= false;
            if(x + 1 < board.length && !seen[x+ 1][y]) down = existHelper(board, word, seen, x + 1, y, index + 1);
            if(x - 1 >= 0 && !seen[x - 1][y]) up = existHelper(board, word, seen, x- 1, y, index + 1);
            if(y + 1 < board[x].length && !seen[x][y + 1])  right = existHelper(board, word, seen, x, y+ 1, index + 1);
            if(y - 1 >= 0 && ! seen[x][y - 1]) left = existHelper(board, word, seen, x, y - 1, index + 1);
            seen[x][y] =false;
            return (up || down || left|| right);
        }
        return  false;
    }




}


