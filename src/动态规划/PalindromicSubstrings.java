package 动态规划;

/**
 * 647. 回文子串(中等)
 * 给定一个字符串，你的任务是计算这个字符串中有多少个回文子串。
 * 具有不同开始位置或结束位置的子串，即使是由相同的字符组成，也会被视作不同的子串。
 *
 * 示例 1：
 * 输入："abc"
 * 输出：3
 * 解释：三个回文子串: "a", "b", "c"
 *
 * 示例 2：
 * 输入："aaa"
 * 输出：6
 * 解释：6个回文子串: "a", "a", "a", "aa", "aa", "aaa"
 *
 * 提示：
 * 输入的字符串长度不会超过 1000 。
 */
public class PalindromicSubstrings {
    //二维dp
    public int countSubstrings(String s) {

        //dp[i][j]表示子串s[i..j]是否是回文串
        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        //base case:
        //  子串长度为1：dp[i][i]=true;
        //  子串长度为2：dp[i][i+1]，如果俩元素相等则为3，否则为2
        int count = 0;
        for (int j = 0; j < n; j++) {
            for (int i = 0; i <= j; i++) {
                /*if (s.charAt(i) == s.charAt(j)) {
                    if ( j - i <= 1 || j - i > 1 && dp[i + 1][j - 1]) {
                        dp[i][j] = true;
                        count++;
                    }
                }*/
                if (i==j){
                    dp[i][j] = true;
                    count++;
                }else if (j-i==1 && s.charAt(i)==s.charAt(j)){
                    dp[i][j] = true;
                    count++;
                }else if (j-i>1 && dp[i+1][j-1] && s.charAt(i)==s.charAt(j)){
                    dp[i][j] = true;
                    count++;
                }
            }
        }
        return count;
    }

    //由转移方程可以发现 只用到了dp[i][j]左下角即dp[i+1][j-1]的值，故可以对二维dp进行空间优化
    public int countSubstrings1(String s) {

        //dp[i][j]表示子串s[i..j]是否是回文串
        int n = s.length();
        boolean[] dp = new boolean[n];
        int count = 0;
        for (int j = 0; j < n; j++) {
            for (int i = 0; i <= j; i++) {
/*                if (i==j){
                    dp[i] = true;
                    count++;
                }else if (j-i==1 && s.charAt(i)==s.charAt(j)){
                    dp[i] = true;
                    count++;
                }else if (j-i>1 && dp[i+1] && s.charAt(i)==s.charAt(j)){
                    dp[i] = true;
                    count++;
                }else {
                    dp[i] = false;
                }*/
                if (s.charAt(i)==s.charAt(j) && (j-i<=1 || j-i>1 && dp[i+1])){
                    dp[i] = true;
                    count++;
                }else{
                    dp[i] = false;
                }
            }
        }
        return count;
    }

    //中心扩散法1，思想：是找到一个字符作为回文字符串的中心，往两边扩散
    int count = 0;
    public int countSubstrings2(String s) {

        //边界判断
        if (s == null || s.length() == 0){
            return 0;
        }

        int n = s.length();
        for (int i = 0; i < n; i++) {
            extendPalindrome(i,i,s);
        }

        return 1;
    }

    private void extendPalindrome(int left, int right, String s) {
        while (left>=0 && right<s.length()&&s.charAt(left--)==s.charAt(right++)){
            count++;
        }
    }

    //中心扩散法2
    public int countSubstrings3(String s) {
        int n = s.length(), ans = 0;
        for (int i = 0; i < 2 * n - 1; ++i) {
            int l = i / 2, r = i / 2 + i % 2;
            while (l >= 0 && r < n && s.charAt(l) == s.charAt(r)) {
                --l;
                ++r;
                ++ans;
            }
        }
        return ans;
    }

}
