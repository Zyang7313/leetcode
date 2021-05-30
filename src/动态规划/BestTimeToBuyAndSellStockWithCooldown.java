package 动态规划;

/**
 * 309. 最佳买卖股票时机含冷冻期
 * 给定一个整数数组，其中第 i 个元素代表了第 i 天的股票价格 。
 * 设计一个算法计算出最大利润。在满足以下约束条件下，你可以尽可能地完成更多的交易（多次买卖一支股票）:
 * 你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 * 卖出股票后，你无法在第二天买入股票 (即冷冻期为 1 天)。
 * <p>
 * 示例:
 * 输入: [1,2,3,0,2]
 * 输出: 3
 * 解释: 对应的交易状态为: [买入, 卖出, 冷冻期, 买入, 卖出]
 */
public class BestTimeToBuyAndSellStockWithCooldown {

    public int maxProfit(int[] prices) {
        int n = prices.length;
        //dp[i]表示第 i 天结束之后的累计最大收益
        //j=0表示持股；j=1表示不持股，处于冷冻期中 ；j=2表示不持股，不处于冷冻期中
        int[][] dp = new int[n][3];
        //base case
        //在第 0 天时，如果持有股票，那么只能是在第 0 天买入的，对应负收益−prices[0]；如果不持有股票，那么收益为0。
        dp[0][0] = -prices[0];
        dp[0][1] = 0;
        dp[0][2] = 0;

        for (int i = 1; i < n; i++) {
            //第i天结束时持股，则可能是i-1天持股或第i天买入股票
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][2] - prices[i]);
            //第i天结束时不持股，处于冷冻期，则只可能为i-1天持股并第i天卖出
            dp[i][1] = dp[i - 1][0] + prices[i];
            //第i天结束时不持股，不处于冷冻期中，则第i-1天可能为不持股处于冷冻期，或不持股非冷冻期
            dp[i][2] = Math.max(dp[i - 1][1], dp[i - 1][2]);
        }
        return Math.max(dp[n - 1][1], dp[n - 1][2]);
    }

    //由第一种解法可以发现，dp[i][x]只与dp[i-1][x]相关，故可以进行空间优化
    public int maxProfit1(int[] prices) {

        int n = prices.length;
        int dp0 = -prices[0];
        int dp1 = 0;
        int dp2 = 0;

        for (int i = 1; i < n; i++) {
            //第i天结束时持股，则可能是i-1天持股或第i天买入股票
            int tmp0 = Math.max(dp0, dp2 - prices[i]);
            //第i天结束时不持股，处于冷冻期，则只可能为i-1天持股并第i天卖出
            int tmp1 = dp0 + prices[i];
            //第i天结束时不持股，不处于冷冻期中，则第i-1天可能为不持股处于冷冻期，或不持股非冷冻期
            int tmp2 = Math.max(dp1, dp2);
            dp0 = tmp0;
            dp1 = tmp1;
            dp2 = tmp2;
        }
        return Math.max(dp1, dp2);

    }

}
