package 待整理;

import java.util.HashMap;
import java.util.Map;

/**
 * 1.两数之和：
 *     给定一个整数数组 nums 和一个整数目标值 target，
 *     请你在该数组中找出和为目标值的那两个整数，并返回它们的数组下标。
 */
public class TwoSum {

    //暴力枚举
    //时间复杂度：O(N^2),空间复杂度：O(1)
    public int[] twoSum1(int[] nums, int target) {

        for(int i =0;i<nums.length;i++){
            for(int j=i+1;j<nums.length;j++){
                if(nums[i]+nums[j]==target){
                    return new int[]{i,j};
                }
            }
        }
        return new int[0];

    }

    //哈希表
    //时间复杂度：O(N),空间复杂度：O(N)
    public int[] twoSum2(int[] nums, int target) {

        for(int i =0;i<nums.length;i++){
            for(int j=i+1;j<nums.length;j++){
                if(nums[i]+nums[j]==target){
                    return new int[]{i,j};
                }
            }
        }
        return new int[0];
    }

}
