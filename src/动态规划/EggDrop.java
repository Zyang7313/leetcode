package 动态规划;

import java.util.HashMap;
import java.util.Map;

/**
 * 887. 鸡蛋掉落（困难）
 * 给你 k 枚相同的鸡蛋，并可以使用一栋从第 1 层到第 n 层共有 n 层楼的建筑。
 * 已知存在楼层 f ，满足 0 <= f <= n ，任何从 高于 f 的楼层落下的鸡蛋都会碎，从 f 楼层或比它低的楼层落下的鸡蛋都不会破。
 * 每次操作，你可以取一枚没有碎的鸡蛋并把它从任一楼层 x 扔下（满足 1 <= x <= n）。如果鸡蛋碎了，你就不能再次使用它。如果某枚鸡蛋扔下后没有摔碎，则可以在之后的操作中 重复使用 这枚鸡蛋。
 * 请你计算并返回要确定f确切的值的最小操作次数是多少？
 * <p>
 * 示例 1：
 * 输入：k = 1, n = 2
 * 输出：2
 * 解释：
 * 鸡蛋从 1 楼掉落。如果它碎了，肯定能得出 f = 0 。
 * 否则，鸡蛋从 2 楼掉落。如果它碎了，肯定能得出 f = 1 。
 * 如果它没碎，那么肯定能得出 f = 2 。
 * 因此，在最坏的情况下我们需要移动 2 次以确定 f 是多少。
 * <p>
 * 示例 2：
 * 输入：k = 2, n = 6
 * 输出：3
 * <p>
 * 示例 3：
 * 输入：k = 3, n = 14
 * 输出：4
 * <p>
 * 提示：
 * 1 <= k <= 100
 * 1 <= n <= 104
 */
public class EggDrop {

    //递归暴力解法,超出时间限制，时间复杂度是为 O(kn^2)
    public int superEggDrop(int k, int n) {
        return recursive(k, n);
    }

    public static int recursive(int K, int N) {
        //如果只有一个鸡蛋，只能从底向上线性遍历，则返回楼层N
        //如果楼层为1，只需要一次就足够；如果楼层为0，则不需要验证
        if (K == 1 || N == 0 || N == 1) {
            return N;
        }
        //每一个楼层都可以得到一个对应的查找次数，比较得出最小者
        int minimun = N;
        for (int i = 1; i <= N; i++) {
            //刚开始有k个鸡蛋，在第i层扔鸡蛋，根据鸡蛋是否破碎可以将这一栋楼分为两栋楼，分为[1..i]和[i+1..n]，子集继续递归
            //如果鸡蛋碎了，则用剩余k-1个在楼层[1..i]继续测试；如果鸡蛋没碎，则用k个鸡蛋在楼层[i+1..n]继续测试
            int tmp = Math.max(recursive(K - 1, i - 1), recursive(K, N - i));
            minimun = Math.min(minimun, 1 + tmp);
        }
        return minimun;
    }

    //动态规划，二维dp，空间复杂度O(KN),时间复杂度降低到了O(kn^2),超时
    public int superEggDrop1(int k, int n) {

        //dp[i][j]表示i个鸡蛋测试j层高的楼 确定f确切的值的最小操作次数
        int[][] dp = new int[k + 1][n + 1];
        //base case
        for (int i = 1; i < n + 1; i++) {
            dp[1][i] = i;   // only one egg
            dp[0][i] = 0; // no egg,可以不加
        }
        for (int i = 1; i < k + 1; i++) {
            dp[i][0] = 0; // zero floor，可以不加
        }
        //开始遍历
        for (int i = 2; i < k + 1; i++) {     //从俩个鸡蛋开始
            for (int j = 1; j < n + 1; j++) {
                int minimun = n;
                for (int x = 1; x < j + 1; x++) {
                    minimun = Math.min(minimun, 1 + Math.max(dp[i - 1][x - 1], dp[i][j - x]));
                }
                dp[i][j] = minimun;
            }
        }
        return dp[k][n];
    }

    //动态规划优化，二分查找
    Map<Integer, Integer> cache = new HashMap<>();
    public int superEggDrop2(int K, int N) {
        if (N == 0)
            return 0;
        else if (K == 1)
            return N;

        Integer key = N * 1000 + K; // K <= 100
        if (cache.containsKey(key))
            return cache.get(key);

        int low = 1, high = N;
        while (low + 1 < high) {
            int middle = (low + high) / 2;
            int lowVal = superEggDrop(K - 1, middle - 1);
            int highVal = superEggDrop(K, N - middle);

            if (lowVal < highVal)
                low = middle;
            else if (lowVal > highVal)
                high = middle;
            else
                low = high = middle;
        }
        int minimum = 1 + Math.min(
                Math.max(superEggDrop(K - 1, low - 1), superEggDrop(K, N - low)),
                Math.max(superEggDrop(K - 1, high - 1), superEggDrop(K, N - high))
        );

        cache.put(key, minimum);

        return cache.get(key);
    }

}


