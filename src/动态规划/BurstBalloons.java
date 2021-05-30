package 动态规划;

/**
 * 312. 戳气球
 * 有 n 个气球，编号为0 到 n - 1，每个气球上都标有一个数字，这些数字存在数组 nums 中。
 * 现在要求你戳破所有的气球。戳破第 i 个气球，你可以获得 nums[i - 1] * nums[i] * nums[i + 1] 枚硬币。
 * 这里的 i - 1 和 i + 1 代表和 i 相邻的两个气球的序号。如果 i - 1或 i + 1 超出了数组的边界，
 * 那么就当它是一个数字为 1 的气球。求所能获得硬币的最大数量。
 *
 * 示例 1：
 * 输入：nums = [3,1,5,8]
 * 输出：167
 * 解释：
 * nums = [3,1,5,8] --> [3,5,8] --> [3,8] --> [8] --> []
 * coins =  3*1*5    +   3*5*8   +  1*3*8  + 1*8*1 = 167
 *
 * 示例 2：
 * 输入：nums = [1,5]
 * 输出：10
 *
 * 提示：
 * n == nums.length
 * 1 <= n <= 500
 * 0 <= nums[i] <= 100
 */
public class BurstBalloons {

    public static void main(String[] args) {
        System.out.println(maxCoins(new int[]{3, 1, 5, 4, 2}));
    }

    public static int maxCoins(int[] nums) {

        int len = nums.length;

        // 创建一个辅助数组，并在首尾各添加1，方便处理边界情况
        int[] val = new int[len+2];
        val[0]=val[len+1]=1;

        for (int i = 1; i < len + 1; i++) {
            val[i] = nums[i-1];
        }

        //创建dp表
        len += 2;
        int[][] dp = new int[len][len];

        //dp[0][4] =max {   dp[0][1]+dp[1][4]+nums[0]*nums[1]*nums[4] ,
        //                  dp[0][2]+dp[2][4]+nums[0]*nums[2]*nums[4] ,
        //                  dp[0][3]+dp[3][4]+nums[0]*nums[3]*nums[4] }
        //dp[i][j]依赖的是dp[i][k]与dp[k][j]其中i<k<j,也就是说如果要求解dp[i][j]依赖了[i][0]到[i][j-1]以及[i+1][j]到[j-1][j]的值。
        //开始dp：i为begin，j为end，k为在i、j区间划分子问题时的边界
        for (int i = len - 2; i >= 0; i--) {
            for (int j = i + 2; j < len; j++) {
                //假设最后戳破的气球为k，k∈(i,j)，区间i到j之间的任意气球都有可能为最后戳破的气球，故遍历求得最大值
                int max = 0;
                for (int k = i + 1; k < j; k++) {
                    int temp = dp[i][k] + dp[k][j] + val[i] * val[k] * val[j];
                    if (temp > max) {
                        max = temp;
                    }
                }
                dp[i][j] = max;
            }
        }
        return dp[0][len-1];
    }

}
