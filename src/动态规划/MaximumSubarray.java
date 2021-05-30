package 动态规划;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.SocketTimeoutException;
import java.util.Arrays;

/**
 * 53.最大子序和：
 *    给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 */
public class MaximumSubarray {

    // 1.贪心思想
    //   解题关键：若当前指针所指元素之前的和小于0,则丢弃当前元素之前的数列
    public int maxSubArray(int[] nums) {

        int len = nums.length;
        int sum = 0;
        int max = nums[0];

        for (int i = 1; i < len; i++) {
            //若当前指针所指元素之前的和小于0,则丢弃当前元素之前的数列
            sum = Math.max(nums[i],sum+nums[i]);
            max = Math.max(sum,max);
        }

        return max;
    }

    //2.动态规划
    //   解题关键：若前一个元素大于0，则将其加到当前元素上
    public int maxSubArray1(int[] nums) {

        int len = nums.length;
        int max = nums[0];
        for (int i = 1; i < len; i++) {
            nums[i] = Math.max(nums[i],nums[i]+nums[i-1]);
        }
        for (int i = 0; i < len; i++) {
            max = Math.max(max,nums[i]);
        }

        return max;
    }

    //3.优化动态规划(优化失败，和2效率差不多)
    public int maxSubArray2(int[] nums) {

        //由于dp[i]仅仅和dp[i-1]有关，则可以进行状态压缩，从而降低空间复杂度
        int len = nums.length;
        int max = nums[0];
        int dp_0 = max;
        int dp_1 = 0;
        for (int i = 1; i < len; i++) {
            //nums[i] = Math.max(nums[i],nums[i]+nums[i-1]);
            dp_1 = Math.max(nums[i],nums[i]+dp_0);
            dp_0 = dp_1;
            max = Math.max(max,dp_1);
        }
        return max;

    }


}
