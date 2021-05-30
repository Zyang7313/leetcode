package 动态规划;

/**
 * 279. 完全平方数
 * 给定正整数 n，找到若干个完全平方数（比如 1, 4, 9, 16, ...）使得它们的和等于 n。你需要让组成和的完全平方数的个数最少。
 * 给你一个整数 n ，返回和为 n 的完全平方数的 最少数量 。
 * 完全平方数 是一个整数，其值等于另一个整数的平方；换句话说，其值等于一个整数自乘的积。例如，1、4、9 和 16 都是完全平方数，而 3 和 11 不是。
 *
 * 示例 1：
 * 输入：n = 12
 * 输出：3
 * 解释：12 = 4 + 4 + 4
 *
 * 示例 2：
 * 输入：n = 13
 * 输出：2
 * 解释：13 = 4 + 9
 *
 * 提示：
 * 1 <= n <= 104
 */
public class PerfectSquares {

    public static void main(String[] args) {
        System.out.println(numSquares(12));
    }

    public static int numSquares(int n) {


        int[] dp = new int[n+1];
        for (int i = 1; i <= n; i++) {
            //dp[i]=i这里做了两件事
            // 1. 初始化最小子问题，dp[1]=1,dp[2]=2,dp[3]=3
            // 2. 初始化最坏情况，每个整数都由n个1组成
            dp[i] = i;
            //动态保存最少数量
            for (int j = 2; i - j*j >= 0 ; j++) {
                //状态选择：
                //选择目标：最少数量
                //选择列表：[小的完全平方数组成，大的完全平方数组成]
                dp[i] = Math.min(dp[i],dp[i-j*j] + 1);
            }
        }
        return dp[n];
    }

}
