package 动态规划;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 416. 分割等和子集（中等），子集背包问题
 * 给你一个只包含正整数的非空数组nums。请你判断是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。
 * <p>
 * 示例 1：
 * 输入：nums = [1,5,11,5]
 * 输出：true
 * 解释：数组可以分割成 [1, 5, 5] 和 [11] 。
 * <p>
 * 示例 2：
 * 输入：nums = [1,2,3,5]
 * 输出：false
 * 解释：数组不能分割成两个元素和相等的子集。
 * <p>
 * 提示：
 * 1 <= nums.length <= 200
 * 1 <= nums[i] <= 100
 */
public class PartitionEqualSubsetSum {

    //二维dp，运用子集背包思想，背包容量为数组和的一半，装的物品则是数组中的值，求能不能恰好装满背包
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum % 2 != 0) return false;
        int n = nums.length;
        //dp[i][j]代表数组前i个数是否可以组成数组的和的一半
        boolean[][] dp = new boolean[n + 1][sum / 2 + 1];
        //base case：若j=0，则数组前任意个数都可以组成0，只要一个都不选即可
        for (int i = 0; i <= n; i++) dp[i][0] = true;
        //开始状态转移
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= sum / 2; j++) {
                if (j - nums[i - 1] < 0) {
                    //背包容量不足，不能装入第i个物品
                    dp[i][j] = dp[i - 1][j];
                } else {
                    //装入或不装入背包
                    //看是否存在一种情况使得恰好装满
                    dp[i][j] = dp[i - 1][j] || dp[i - 1][j - nums[i - 1]];
                }
            }
        }
        return dp[n][sum / 2];

    }

    //状态压缩
    public boolean canPartition1(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum % 2 != 0) return false;
        int n = nums.length;
        //dp[i][j]代表数组前i个数是否可以组成数组的和的一半
        boolean[] dp = new boolean[sum / 2 + 1];
        //base case：若j=0，则数组前任意个数都可以组成0，只要一个都不选即可
        dp[0] = true;
        //开始状态转移
        for (int i = 1; i <= n; i++) {
            //第二层的循环我们需要从大到小计算，因为如果我们从小到大更新 dp 值，
            //那么在计算 dp[j] 值的时候，dp[j−nums[i]] 已经是被更新过的状态，不再是上一行的 dp 值。
            for (int j = sum / 2; j > 0; j--) {
                if (j - nums[i - 1] >= 0) {
                    //背包容量不足，不能装入第i个物品
                    dp[j] = dp[j] || dp[j - nums[i - 1]];
                }
            }
        }
        return dp[sum / 2];

    }
}
