package 回溯法;

import java.util.LinkedList;
import java.util.List;

/**
 * 46. 全排列（中等）
 * 给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。你可以 按任意顺序 返回答案。
 *
 * 示例 1：
 * 输入：nums = [1,2,3]
 * 输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 *
 * 示例 2：
 * 输入：nums = [0,1]
 * 输出：[[0,1],[1,0]]
 *
 * 示例 3：
 * 输入：nums = [1]
 * 输出：[[1]]
 *
 * 提示：
 * 1 <= nums.length <= 6
 * -10 <= nums[i] <= 10
 * nums 中的所有整数 互不相同
 */
public class Permutations {

    //结果
    List<List<Integer>> res = new LinkedList<>();

    public List<List<Integer>> permute(int[] nums) {
        //路径
        LinkedList<Integer> track = new LinkedList<>();
        backtrack(nums,track);
        return res;

    }

    //路径：记录在track中
    //选择列表：nums中不存在track中的元素
    //结束条件：nums中的元素全部在track中
    private void backtrack(int[] nums, LinkedList<Integer> track) {
        //结束条件
        if (nums.length == track.size()) {
            //track只是引用，我们需要的是其中的在某个特定时期的内容，所以一定要进行拷贝
            res.add(new LinkedList<>(track));
        }
        for (int i = 0; i < nums.length; i++) {
            //排除不合法的选择
            if (track.contains(nums[i])) {
                continue;
            }
            //做选择
            track.add(nums[i]);
            //进入下一层决策树
            backtrack(nums,track);
            //取消选择
            track.removeLast();
        }
    }
}
