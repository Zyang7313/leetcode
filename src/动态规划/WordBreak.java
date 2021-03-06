package 动态规划;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 * 139. 单词拆分
 * 给定一个非空字符串 s 和一个包含非空单词的列表 wordDict，
 * 判定 s 是否可以被空格拆分为一个或多个在字典中出现的单词。
 *
 * 说明：
 * 拆分时可以重复使用字典中的单词。
 * 你可以假设字典中没有重复的单词。
 *
 *  示例 1：
 * 输入: s = "leetcode", wordDict = ["leet", "code"]
 * 输出: true
 * 解释: 返回 true 因为 "leetcode" 可以被拆分成 "leet code"。
 *
 * 示例 2：
 * 输入: s = "applepenapple", wordDict = ["apple", "pen"]
 * 输出: true
 * 解释: 返回 true 因为 "applepenapple" 可以被拆分成 "apple pen apple"。
 *      注意你可以重复使用字典中的单词。

 * 示例 3：
 * 输入: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
 * 输出: false
 */
public class WordBreak {

    public boolean wordBreak(String s, List<String> wordDict) {

        //ArrayList本质就是通过数组实现的，查找一个元素是否包含要用到遍历，时间复杂度是O（n）
        //而HashSetHashSet的查找是通过HashMap的KeySet来实现的，判断是否包含某个元素的实现，时间复杂度是O（1）
        //所以应将所给的List参数转换为哈希表，从而提高查询效率
        Set<String> words = new HashSet<>(wordDict);

        //单词最大长度
        int max = 0;
        for (String s1 : wordDict) {
            max = Math.max(max, s1.length());
        }
        //dp[i]表示s的前i个字符组成的字符串是否能被拆分
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;

        for (int i = 1; i <= s.length(); i++) {
            for (int j = Math.max(i-max,0); j < i; j++) {
                if (dp[j] && words.contains(s.substring(j, i))) {
                    //找到一个满足条件的拆分即可
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length()];

    }
}
