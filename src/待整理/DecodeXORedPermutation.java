package 待整理;

/**
 * 1734. 解码异或后的排列(中等)
 * 给你一个整数数组 perm ，它是前 n 个正整数的排列，且 n 是个 奇数 。
 * 它被加密成另一个长度为 n - 1 的整数数组 encoded ，满足 encoded[i] = perm[i] XOR perm[i + 1] 。比方说，如果 perm = [1,3,2] ，那么 encoded = [2,1] 。
 * 给你 encoded 数组，请你返回原始数组 perm 。题目保证答案存在且唯一。
 *
 * 示例 1：
 * 输入：encoded = [3,1]
 * 输出：[1,2,3]
 * 解释：如果 perm = [1,2,3] ，那么 encoded = [1 XOR 2,2 XOR 3] = [3,1]
 *
 * 示例 2：
 * 输入：encoded = [6,5,4,6]
 * 输出：[2,4,1,5,3]
 *
 * 提示：
 * 3 <= n < 105
 * n 是奇数。
 * encoded.length == n - 1
 */
public class DecodeXORedPermutation {
    /*
    可以看到encoded数组中所有偶数位（下标从0开始的，是奇数）元素的异或结果其实就是数组perm中除第一个元素以外，
    其他所有元素异或的结果，我们记为m，看到这里我们很容易想到136. 只出现一次的数字，我们只需要从1到n都异或一遍然后在与m异或，
    结果就是数组encoded中的第一个元素了。求出第一个元素之后，后面就简单了，
    */
    public int[] decode(int[] encoded) {

        int n = encoded.length + 1;
        int[] res = new int[n];
        int tmp1 = 0;
        int tmp2 = 0;
        //数组encoded中第偶数个元素都异或一遍
        for(int i = 1;i < n;i+=2) {
            tmp1 ^= encoded[i];
        }
        //把1到n中间的所有数字都异或一遍
        for(int i = 1;i <= n;i++) {
            tmp2 ^= i;
        }
        //求出第一个值，后面就简单了
        res[0] = tmp1^tmp2;
        for(int i=1;i<n;i++) {
            res[i] = res[i-1]^encoded[i-1];
        }
        return res;

    }
}
