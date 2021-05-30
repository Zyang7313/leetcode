package 动态规划;

/**
 * 5.最长回文子串：
 *    给你一个字符串 s，找到 s 中最长的回文子串。
 */
public class LongestPalindromicSubstring {

    public static void main(String[] args) {
        //System.out.println(isPalindrome("babad"));
        System.out.println(longestPalindrome2("babadf23t23gewgvdsbvdsbsdbsdgsad23t"));

    }

    //1.暴力解法,遍历每一个子串，判断是否为回文串(不推荐)
    //判断字符串是否为回文串
    public static boolean isPalindrome(char[] chars,int left,int right){
        while (left<right){
            if (chars[left++]!=chars[right--]){
                return false;
            }
        }
        return true;
    }
    public static String longestPalindrome(String s) {

        int len = s.length();
        if (len < 2) {
            return s;
        }
        //s.charAt(i)每次都会判断数组下标越界，影响性能，所以转换成字符数组
        char[] chars = s.toCharArray();
        int maxLength = 1;
        int begin = 0;

        for (int i = 0; i < len; i++) {
            for (int j = i+1; j < len; j++) {
                if (j-i+1 > maxLength && isPalindrome(chars,i,j)) {
                    begin = i;
                    maxLength = j-i+1;
                }
            }
        }
        return s.substring(begin,begin+maxLength);
    }


    //2.动态规划
    public static String longestPalindrome2(String s){

        int len = s.length();
        if (len < 2) {
            return s;
        }

        int maxLen = 1;
        int begin = 0;
        char[] chars = s.toCharArray();

        // dp[i][j] 表示 s[i..j] 是否是回文串
        boolean[][] dp = new boolean[len][len];
        // 初始化：所有长度为 1 的子串都是回文串
        for (int i = 0; i < len; i++) {
            dp[i][i] = true;
        }

        // 递推开始,先填左下角
        for (int j = 1; j < len; j++) {
            for (int i = 0; i < j; i++) {
                if(chars[i]!=chars[j]){
                    dp[i][j] = false;
                }else {
                    if(j - i < 3) {
                        dp[i][j] = true;
                    }else {
                        dp[i][j] = dp[i+1][j-1];
                    }
                }
                // 只要 dp[i][j] == true 成立，就表示子串 s[i..L] 是回文，此时记录回文长度和起始位置
                if (dp[i][j] && j - i + 1 >maxLen) {
                    maxLen = j - i + 1;
                    begin = i;
                }
            }
        }
        return s.substring(begin,begin + maxLen);
    }
}
