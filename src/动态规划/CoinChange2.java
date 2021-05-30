package 动态规划;

import java.util.Arrays;

/**
 * 518. 零钱兑换 II(中等)
 * 给定不同面额的硬币和一个总金额。写出函数来计算可以凑成总金额的硬币组合数。假设每一种面额的硬币有无限个。
 * <p>
 * 示例 1:
 * 输入: amount = 5, coins = [1, 2, 5]
 * 输出: 4
 * 解释: 有四种方式可以凑成总金额:
 * 5=5
 * 5=2+2+1
 * 5=2+1+1+1
 * 5=1+1+1+1+1
 * <p>
 * 示例 2:
 * 输入: amount = 3, coins = [2]
 * 输出: 0
 * 解释: 只用面额2的硬币不能凑成总金额3。
 * <p>
 * 示例 3:
 * 输入: amount = 10, coins = [10]
 * 输出: 1
 * <p>
 * 注意:
 * 你可以假设：
 * 0 <= amount (总金额) <= 5000
 * 1 <= coin (硬币面额) <= 5000
 * 硬币种类不超过 500 种
 * 结果符合 32 位符号整数
 */
public class CoinChange2 {

    /*
    * 思路：
        1.状态：背包的容量、可供选择的数量
          选择：装进背包、不装进背包
        2.dp数组含义：由于状态有俩个，所以应为二维数组
          dp[i][j]表示为 只使用前i个物品，当背包容量为j时，有dp[i][j]中方法可以装满背包
        3.base case:若j=0，则什么都不放即可，所以dp[i][0]=1;若i=0，则凑不出任何金额，则dp[0][j]=0;
        4.根据选择思考状态转移的逻辑：
            若不把第i个物品装入背包，则dp[i][j]=dp[i-1][j]
            若装入第i个物品，则dp[i][j]=dp[i-1][j-coins[i-1]]
    */
    public int change(int amount, int[] coins) {


        int n = coins.length;
        int[][] dp = new int[n + 1][amount + 1];
        //base case
        for (int i = 0; i <= n; i++) {
            dp[i][0] = 1;
        }
        //开始状态转移n
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= amount; j++) {
                if (j - coins[i - 1] >= 0) {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - coins[i - 1]];
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[n][amount];

    }

    //状态压缩，此方法就是爬楼梯的通用解法
    public int change1(int amount, int[] coins) {

        int n = coins.length;
        int[] dp = new int[amount + 1];
        //base case
        dp[0] = 1;
        //开始状态转移n
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= amount; j++) {
                if (j - coins[i - 1] >= 0) {
                    dp[j] = dp[j] + dp[j - coins[i - 1]];
                }
            }
        }
        return dp[amount];

    }
}
