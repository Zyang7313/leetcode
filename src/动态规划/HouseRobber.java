package 动态规划;

/**
 * 198. 打家劫舍（中等）
 * 你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
 * 给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。
 *
 * 示例 1：
 * 输入：[1,2,3,1]
 * 输出：4
 * 解释：偷窃 1 号房屋 (金额 = 1) ，然后偷窃 3 号房屋 (金额 = 3)。
 *      偷窃到的最高金额 = 1 + 3 = 4 。
 * 示例 2：
 * 输入：[2,7,9,3,1]
 * 输出：12
 * 解释：偷窃 1 号房屋 (金额 = 2), 偷窃 3 号房屋 (金额 = 9)，接着偷窃 5 号房屋 (金额 = 1)。
 *      偷窃到的最高金额 = 2 + 9 + 1 = 12 。
 *
 * 提示：
 * 1 <= nums.length <= 100
 * 0 <= nums[i] <= 400
 */
public class HouseRobber {
    public int rob(int[] nums) {
        //dp[i]表示前i间屋子所能偷窃的最多钱
        //base case:dp[0]=0,dp[1]=max(nums[0],nums[1])
        //对第i间屋子的选择如下，选择其中较大者：
        //  1.若偷第i间屋子，则第i-1不能偷，dp[i] = dp[i-2]+ nums[i]
        //  2.若不偷第i间屋子，则dp[i]=dp[i-1]
        int n = nums.length;
        if(n==1){
            return nums[0];
        }
        int[] dp = new int[n];
        dp[0] = nums[0];dp[1]=Math.max(nums[0],nums[1]);
        for (int i = 2; i < n; i++) {
            dp[i] = Math.max(dp[i-2]+nums[i],dp[i-1]);
        }
        return dp[n-1];
    }

    //由于计算dp[i]只用到dp[i-1]和dp[i-2]，故可以优化空间复杂度
    public int rob1(int[] nums) {
        //dp[i]表示前i间屋子所能偷窃的最多钱
        //base case:dp[0]=0,dp[1]=max(nums[0],nums[1])
        //对第i间屋子的选择如下，选择其中较大者：
        //  1.若偷第i间屋子，则第i-1不能偷，dp[i] = dp[i-2]+ nums[i]
        //  2.若不偷第i间屋子，则dp[i]=dp[i-1]
        int n = nums.length;
        if(n==1){
            return nums[0];
        }
        int first = nums[0];int second = Math.max(nums[0],nums[1]);
        for (int i = 2; i < n; i++) {
            int tmp = Math.max(first+nums[i],second);
            first = second;
            second = tmp;
        }
        return second;
    }
}
