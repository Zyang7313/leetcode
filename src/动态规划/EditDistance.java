package 动态规划;

/**
 * 72. 编辑距离
 * 给你两个单词 word1 和 word2，请你计算出将 word1 转换成 word2 所使用的最少操作数 。
 * 你可以对一个单词进行如下三种操作：
 * 插入一个字符
 * 删除一个字符
 * 替换一个字符
 *
 * 示例 1：
 * 输入：word1 = "horse", word2 = "ros"
 * 输出：3
 * 解释：
 * horse -> rorse (将 'h' 替换为 'r')
 * rorse -> rose (删除 'r')
 * rose -> ros (删除 'e')
 *
 * 示例 2：
 * 输入：word1 = "intention", word2 = "execution"
 * 输出：5
 * 解释：
 * intention -> inention (删除 't')
 * inention -> enention (将 'i' 替换为 'e')
 * enention -> exention (将 'n' 替换为 'x')
 * exention -> exection (将 'n' 替换为 'c')
 * exection -> execution (插入 'u')
 *
 * 提示：
 * 0 <= word1.length, word2.length <= 500
 * word1 和 word2 由小写英文字母组成
 */
public class EditDistance {

    //1、暴力DFS，自顶向下递归，超时，不可取，要么加备忘录，要么使用dp
    public int minDistance(String word1, String word2) {

        int len1 = word1.length();
        int len2 = word2.length();
        char[] chars1 = word1.toCharArray();
        char[] chars2 = word2.toCharArray();
        return dp(len1,len2,chars1,chars2);
    }

    public static int dp(int i,int j,char[] a,char[] b){

        //base case
        if (i == -1) return j+1;
        if (j == -1) return i+1;

        if (a[i] == b[j]) {
            return dp(i-1,j-1,a,b);
        }else {
            // 插入
            int insert = dp(i,j-1,a,b) + 1;
            // 删除
            int delete = dp(i-1,j,a,b) + 1;
            // 替换
            int replace = dp(i-1,j-1,a,b) + 1;
            return Math.min(insert,Math.min(delete,replace));
        }
    }

    //2、dp
    public static int minDistance1(String word1, String word2) {

        int len1 = word1.length();
        int len2 = word2.length();
        char[] a = word1.toCharArray();
        char[] b = word2.toCharArray();
        //创建并初始化dp
        /**
         * dp[i][j]代表word1[0..i-1]和word2[0..j-1]的最小编辑距离
         * 0代表空串，初始化第一行第一列为非空字符串长度
         */
        int[][] dp = new int[len1+1][len2+1];
        for (int i = 1; i <= len1; i++) {
            dp[i][0] = i;
        }
        for (int i = 1; i <= len2; i++) {
            dp[0][i] = i;
        }

        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                if (a[i-1] == b[j-1]) {
                    //dp[i-1][j-1]代表跳过
                    dp[i][j] = dp[i-1][j-1];
                }else {
                    //dp[i-1][j]代表删除,dp[i][j-1]代表插入，dp[i-1][j-1]+1代表替换
                    dp[i][j] = Math.min(Math.min(dp[i-1][j],dp[i][j-1])+1,dp[i-1][j-1]+1);
                }
            }
        }
        return dp[len1][len2];
    }

    //优化二维dp为一维dp
    public static int minDistance2(String word1, String word2) {

        int len1 = word1.length();
        int len2 = word2.length();

        //创建并初始化dp
        int[] dp = new int[len2+1];
        for (int i = 1; i <= len2; i++) {
            dp[i] = i;
        }

        for (int i = 1; i <= len1; i++) {
            dp[0] = i;
            int upleft = i-1;
            for (int j = 1; j <= len2; j++) {
                int tmp = dp[j];
                if (word1.charAt(i-1) == word2.charAt(j-1)) { //a[i-1] == b[j-1]
                    dp[j] = upleft;
                }else {
                    dp[j] = Math.min(Math.min(dp[j],dp[j-1])+1,upleft+1);
                }
                upleft = tmp;
            }
        }
        return dp[len2];
    }

    public static void main(String[] args) {
        System.out.println(minDistance2("horse","ros"));
    }

}
