package 待整理;

import java.util.*;

/**
 * 3.无重复字符的最长子串：
 *    给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 */
public class LongestSubstringWithoutRepeatingCharacters {

    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("aab"));
    }


    //队列思想解题
    public static int lengthOfLongestSubstring(String s) {

        Queue<Character> characters = new LinkedList<>();
        char[] chars = s.toCharArray();
        int maxLength=0;

        if(chars.length<2){
            return chars.length;
        }

        for (int i = 0; i < chars.length; i++) {
            if(characters.contains(chars[i])){
                maxLength = Math.max(maxLength,characters.size());
                characters.add(chars[i]);
                while(characters.poll()!=chars[i]){}
            }else {
                characters.add(chars[i]);
            }
        }
        if(maxLength==0){
            return chars.length;
        }
        maxLength = Math.max(maxLength,characters.size());
        return maxLength;
    }

    /**
     * map (k, v)，其中 key 值为字符，value 值为字符位置;
     */
    //滑动窗口思想
    public int lengthOfLongestSubstring1(String s) {
        int length = s.length();
        int max = 0;

        Map<Character, Integer> map = new HashMap<>();
        for(int start = 0, end = 0; end < length; end++){
            char element = s.charAt(end);
            if(map.containsKey(element)){
                start = Math.max(map.get(element) + 1, start); //map.get()的地方进行+1操作
            }
            max = Math.max(max, end - start + 1);
            map.put(element, end);
        }
        return max;
    }

}
