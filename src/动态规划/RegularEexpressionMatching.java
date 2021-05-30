package 动态规划;

import sun.util.resources.cldr.ebu.LocaleNames_ebu;

/**
 * 剑指 Offer 19. 正则表达式匹配
 * 请实现一个函数用来匹配包含'. '和'*'的正则表达式。模式中的字符'.'表示任意一个字符，而'*'表示它前面的字符可以出现任意次（含0次）。在本题中，匹配是指字符串的所有字符匹配整个模式。例如，字符串"aaa"与模式"a.a"和"ab*ac*a"匹配，但与"aa.a"和"ab*a"均不匹配。
 *
 * 示例 1:
 * 输入:
 * s = "aa"
 * p = "a"
 * 输出: false
 * 解释: "a" 无法匹配 "aa" 整个字符串。
 *
 * 示例 2:
 * 输入:
 * s = "aa"
 * p = "a*"
 * 输出: true
 * 解释: 因为 '*' 代表可以匹配零个或多个前面的那一个元素, 在这里前面的元素就是 'a'。因此，字符串 "aa" 可被视为 'a' 重复了一次。
 *
 * 示例 3:
 * 输入:
 * s = "ab"
 * p = ".*"
 * 输出: true
 * 解释: ".*" 表示可匹配零个或多个（'*'）任意字符（'.'）。
 * <p>
 *
 * 示例 4:
 * 输入:
 * s = "aab"
 * p = "c*a*b"
 * 输出: true
 * 解释: 因为 '*' 表示零个或多个，这里 'c' 为 0 个, 'a' 被重复一次。因此可以匹配字符串 "aab"。
 *
 * 示例 5:
 * 输入:
 * s = "mississippi"
 * p = "mis*is*p*."
 * 输出: false
 *
 * 提示：
 * s 可能为空，且只包含从 a-z 的小写字母。
 * p 可能为空，且只包含从 a-z 的小写字母以及字符 . 和 *，无连续的 '*'。
 */
public class RegularEexpressionMatching {

    //暴力递归，超出时间限制
    public boolean isMatch(String s, String p) {

        int m = s.length();
        int n = p.length();
        int i = 0;
        int j = 0;
        while (i < m && j < n) {
            if (p.charAt(j) == '.' || s.charAt(i) == p.charAt(j)) {
                //匹配
                if (j + 1 < n && p.charAt(j + 1) == '*') {
                    //有*通配符，匹配0次或多次
                    if (p.charAt(j) == '.') {
                        return true;
                    } else {
                        while (s.charAt(i) == p.charAt(j)) {
                            i++;
                        }
                        j += 2;
                    }
                } else {
                    //无*通配符，匹配1次
                    i++;
                    j++;
                }
            } else {
                //不匹配
                if (j + 1 < n && p.charAt(j + 1) == '*') {
                    //有*通配符，只能匹配0次
                } else {
                    //无*通配符，匹配无法进行下去
                    return false;
                }
            }
        }
        return i == m && j == n;

    }

    //二维动态规划
    public boolean isMatch1(String s, String p) {

        int m = s.length();
        int n = p.length();
        boolean[][] dp = new boolean[m + 1][n + 1];
        //初始化dp数组，dp[i][j]表示s[0..i-1]与p[0..j-1]是否能匹配
        //p为空时，s只能为空才能匹配，故第一列除dp[0][0]为true，其它均为false；
        //s为空时，p可能为c*c*...，故第一行如果p的第偶数个字符为*，也可以匹配
        dp[0][0] = true;
        //初始化第一行
        for (int i = 2; i <= n; i += 2) {
            if (p.charAt(i-1) == '*'&&dp[0][i-2]) {
                dp[0][i] = true;
            }
        }
        for (int i = 0; i <= m; i++) {
            //如果模式串为空，则字符串s除非为空能匹配，否则不能匹配，所以从第一列开始遍历
            for (int j = 1; j <= n; j++) {
                if(p.charAt(j - 1) == '*') {//p的第j个字符为*
                    if(matches(s, p, i, j - 1)) {//匹配s的第i个字符和p的第j-1个字符
                        dp[i][j] = dp[i - 1][j] || dp[i][j - 2];//p中*前面的字符在s中出现多次或者在s中只出现1次
                    }
                    else {
                        dp[i][j] = dp[i][j - 2];//p中*前面的在s中字符出现0次
                    }
                }
                else {//p的第j个字符不为*
                    if(matches(s, p, i, j)) {//匹配s的第i个字符和p的第j个字符
                        dp[i][j] = dp[i - 1][j - 1];//匹配成功，状态转移；匹配不成功，默认是false
                    }
                }
            }
        }
        return dp[m][n];
    }

    private static boolean matches(String s, String p, int i, int j) {//注意在字符串中的下标变换
        if(i == 0) {
            return false;
        }
        if(p.charAt(j - 1) == '.') {
            return true;
        }
        return s.charAt(i - 1) == p.charAt(j - 1);
    }



}
