package 动态规划;

/**
 * 375. 猜数字大小 II
 * 我们正在玩一个猜数游戏，游戏规则如下：
 * 我从 1 到 n 之间选择一个数字，你来猜我选了哪个数字。
 * 每次你猜错了，我都会告诉你，我选的数字比你的大了或者小了。
 * 然而，当你猜了数字 x 并且猜错了的时候，你需要支付金额为 x 的现金。
 * 直到你猜到我选的数字，你才算赢得了这个游戏。
 *
 * 示例:
 * n = 10, 我选择了8.
 * 第一轮: 你猜我选择的数字是5，我会告诉你，我的数字更大一些，然后你需要支付5块。
 * 第二轮: 你猜是7，我告诉你，我的数字更大一些，你支付7块。
 * 第三轮: 你猜是9，我告诉你，我的数字更小一些，你支付9块。
 * 游戏结束。8 就是我选的数字。
 * 你最终要支付 5 + 7 + 9 = 21 块钱。
 *
 * 给定 n ≥ 1，计算你至少需要拥有多少现金才能确保你能赢得这个游戏。
 */
public class GuessNumberII {

    //动态规划
    public int getMoneyAmount(int n) {
        /*
            其中dp(i,j)代表在(i,j)中最坏情况下最小开销的代价
            为了求出长度为len(=j-i+1)区间的解，我们需要所有长度为len-1的解。
            因此我们按照区间长度从短到长求出dp数组。
        */
        int[][] dp = new int[n+1][n+1];
        for(int len = 2; len <= n; len++){
            for(int i = 1; i + len - 1 <= n; i++){
                int j = i + len - 1;
                dp[i][j] = Integer.MAX_VALUE;
                /*
                * dp[i][j]以k为分界点分为dp[i][k - 1]和dp[k + 1][j]，
                * 由于右区间的数大于左区间的数，所以第一次肯定选右区间中的点作为分界点从而使得开销最小
                * 只需要从(i+j)/2遍历到 j，而不需要从i遍历到j，从而实现了优化
                */
                //以i，j之间的k作为分界点所得到的开销代价，选择其最小的
                for(int k = i; k <= j; k++){
                    dp[i][j] = Math.min(dp[i][j],
                                        k + Math.max( k - 1 <= i ? 0 : dp[i][k - 1],
                                                      k + 1 >= j ? 0 : dp[k + 1][j])
                                        );
                }
            }
        }
        return dp[1][n];
    }

    //递归,超时
    public int getMoneyAmount1(int n) {
        return recursion(1,n);
    }
    public static int recursion(int start, int end) {
        if (start == end) return 0;
        int res = Integer.MAX_VALUE, max = 0, left = 0, right = 0;
        for (int i = start; i <= end; i++) {
            if (i - 1 >= start) left = recursion(start, i - 1);
            if (i + 1 <= end) right = recursion(i + 1, end);
            max = i + Math.max(left, right);
            res = Math.min(res, max);
        }
        return res;
    }




}
