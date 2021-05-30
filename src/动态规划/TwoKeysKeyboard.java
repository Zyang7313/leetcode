package 动态规划;

/**
 * 650. 只有两个键的键盘(中等)
 * 最初在一个记事本上只有一个字符 'A'。你每次可以对这个记事本进行两种操作：
 * Copy All (复制全部) : 你可以复制这个记事本中的所有字符(部分的复制是不允许的)。
 * Paste (粘贴) : 你可以粘贴你上一次复制的字符。
 * 给定一个数字 n 。你需要使用最少的操作次数，在记事本中打印出恰好 n 个 'A'。输出能够打印出 n 个 'A' 的最少操作次数。
 * <p>
 * 示例 1:
 * 输入: 3
 * 输出: 3
 * 解释:
 * 最初, 我们只有一个字符 'A'。
 * 第 1 步, 我们使用 Copy All 操作。
 * 第 2 步, 我们使用 Paste 操作来获得 'AA'。
 * 第 3 步, 我们使用 Paste 操作来获得 'AAA'。
 * 说明:
 * <p>
 * n 的取值范围是 [1, 1000] 。
 */
public class TwoKeysKeyboard {

    public int minSteps(int n) {

        //dp[i]表示得到i个A所需要的操作数，则我们的目的是求dp[n]
        //状态转移方程：dp[i] = dp[j] + dp[i/j] （j是一个小于i的数，且能被i整除，其实就是i的因数，注意把j==1的情况排除掉）
        //说明：上述方程对应了前面介绍的规律，dp[6] = dp[2] + dp[3]
        //初始化：dp[i] = i （表示一个A一个A的粘贴，是实现步骤最多的方法）

        int[] dp = new int[n + 1];
        int h = (int) Math.sqrt(n);
        for (int i = 2; i < n + 1; i++) {
            dp[i]=i;
            /* 这里的j不需要写成j <= i，因为判断j是不是因数只要检查到开平方的大小就好了，算是个小优化> */
            for (int j = 2; j * j <= i; j++) {
                if (i % j == 0)
                    dp[i] = dp[j] + dp[i / j];
            }
        }
        return dp[n];
    }

    //本题核心就是求数的素数之和
    public int minSteps1(int n) {
        int ans = 0, d = 2;
        while (n > 1) {
            while (n % d == 0) {
                ans += d;
                n /= d;
            }
            d++;
        }
        return ans;
    }

}
