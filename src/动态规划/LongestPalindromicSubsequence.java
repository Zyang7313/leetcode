package 动态规划;

import java.util.Arrays;

/**
 * 516. 最长回文子序列(中等)
 * 给定一个字符串 s ，找到其中最长的回文子序列，并返回该序列的长度。可以假设 s 的最大长度为 1000 。
 *
 * 示例 1:
 * 输入:"bbbab"
 * 输出:4
 * 一个可能的最长回文子序列为 "bbbb"。
 *
 * 示例 2:
 * 输入:"cbbd"
 * 输出:2
 * 一个可能的最长回文子序列为 "bb"。
 *
 * 提示：
 * 1 <= s.length <= 1000
 * s 只包含小写英文字母
 */
public class LongestPalindromicSubsequence {
    public int longestPalindromeSubseq(String s) {

        /*
            1、dp[i][j] :表示s[i..j]的最大回文子串,i<=j
            2、base case :dp[i][i] = 1
            3、对于dp[i][j]，如果s.charAt(i)==s.charAt(j),dp[i][j]=dp[i+1][j-1]+2
            如果s.charAt(i)!=s.charAt(j),dp[i][j]=max(dp[i+1][j],dp[i][j-1])

        */

        int n = s.length();
        if (n==1) return 1;
        char[] c = s.toCharArray();
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            dp[i][i] = 1;
        }

        for (int i = n - 2; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                if (c[i]==c[j]) {
                    dp[i][j] = dp[i+1][j-1] + 2;
                }else {
                    dp[i][j] = Math.max(dp[i+1][j],dp[i][j-1]);
                }
            }
        }
        return dp[0][n-1];
    }

    //优化二维dp为一维
    public int longestPalindromeSubseq1(String s) {

        /*
            1、dp[i] :表示s[0..i]的最大回文子串
            2、base case :dp[i] = 1，相当于二维dp的base case投影至一维
            3、参考二维dp，
            对于dp[i]，如果s.charAt(i)==s.charAt(j),dp[i][j]=dp[i+1][j-1]+2
            如果s.charAt(i)!=s.charAt(j),dp[i][j]=max(dp[i+1][j],dp[i][j-1])
        */

        int n = s.length();
        if (n==1) return 1;
        char[] c = s.toCharArray();
        int[] dp = new int[n];
        Arrays.fill(dp,1);

        for (int j = n - 2; j >= 0; j--) {
            int downLeft = 0;
            for (int i = j + 1; i < n; i++) {
                int temp = dp[i];
                if (c[i]==c[j]) {
                    dp[i] = downLeft + 2;
                } else {
                    dp[i] = Math.max(temp,dp[i-1]);
                }
                downLeft = temp;
            }
        }
        return dp[n-1];
    }
}
