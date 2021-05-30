package 动态规划;

import java.util.Arrays;

/**
 * 1312. 让字符串成为回文串的最少插入次数
 * 给你一个字符串 s ，每一次操作你都可以在字符串的任意位置插入任意字符。
 * 请你返回让 s 成为回文串的 最少操作次数 。
 * 「回文串」是正读和反读都相同的字符串。
 *
 * 示例 1：
 * 输入：s = "zzazz"
 * 输出：0
 * 解释：字符串 "zzazz" 已经是回文串了，所以不需要做任何插入操作。
 *
 * 示例 2：
 * 输入：s = "mbadm"
 * 输出：2
 * 解释：字符串可变为 "mbdadbm" 或者 "mdbabdm" 。
 *
 * 示例 3：
 * 输入：s = "leetcode"
 * 输出：5
 * 解释：插入 5 个字符后字符串变为 "leetcodocteel" 。
 *
 * 示例 4：
 * 输入：s = "g"
 * 输出：0
 *
 * 示例 5：
 * 输入：s = "no"
 * 输出：1
 *
 * 提示：
 * 1 <= s.length <= 500
 * s 中所有字符都是小写字母。
 */
public class MinimumInsertionStepsToMakePalindromeString {

        /*
            1、dp[i][j] :表示让字符串s[i..j]成为回文串的最少插入次数,i<=j
            2、base case :dp[i][i] = 0,单个字符不需要插入就是回文串
            3、对于dp[i][j]，如果s.charAt(i)==s.charAt(j),dp[i][j]=dp[i+1][j-1]
            如果s.charAt(i)!=s.charAt(j),dp[i][j]=min(dp[i+1][j],dp[i][j-1])+1
        */

    //二维dp
    public int minInsertions(String s) {
        int n = s.length();
        if (n==1) return 0;
        char[] c = s.toCharArray();
        int[][] dp = new int[n][n];

        for (int i = n - 2; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                if (c[i]==c[j]) {
                    dp[i][j] = dp[i+1][j-1];
                }else {
                    dp[i][j] = Math.min(dp[i+1][j],dp[i][j-1]) + 1;
                }
            }
        }
        return dp[0][n-1];
    }

    //一维dp
    public int minInsertions1(String s) {

        /*
            1、dp[i] :表示让字符串s[0..i]成为回文串的最少插入次数
            2、base case :dp[i] = 0，相当于二维dp的base case投影至一维
            3、参考二维dp
            对于dp[i]，如果s.charAt(i)==s.charAt(j),dp[i][j]=dp[i+1][j-1]+2
            如果s.charAt(i)!=s.charAt(j),dp[i][j]=max(dp[i+1][j],dp[i][j-1])
        */

        int n = s.length();
        if (n==1) return 0;
        char[] c = s.toCharArray();
        int[] dp = new int[n];

        for (int j = n - 2; j >= 0; j--) {
            int downLeft = 0;
            for (int i = j + 1; i < n; i++) {
                int temp = dp[i];
                if (c[i]==c[j]) {
                    dp[i] = downLeft;
                } else {
                    dp[i] = Math.min(temp,dp[i-1])+1;
                }
                downLeft = temp;
            }
        }
        return dp[n-1];
    }

}
