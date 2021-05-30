package 动态规划;

import java.util.Arrays;

/**
 * 221. 最大正方形（中等）
 * 在一个由 '0' 和 '1' 组成的二维矩阵内，找到只包含 '1' 的最大正方形，并返回其面积。
 *
 * 示例 1：
 * 输入：matrix = [["1","0","1","0","0"],["1","0","1","1","1"],["1","1","1","1","1"],["1","0","0","1","0"]]
 * 输出：4
 *
 * 示例 2：
 * 输入：matrix = [["0","1"],["1","0"]]
 * 输出：1
 *
 * 示例 3：
 * 输入：matrix = [["0"]]
 * 输出：0
 *
 * 提示：
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= m, n <= 300
 * matrix[i][j] 为 '0' 或 '1'
 */
public class MaximalSquare {
    //二维dp
    public int maximalSquare(char[][] matrix) {
        /*
            分析：
                1.dp[i][j]表示数组char以第i行第j列的元素为右下角的元素权威‘1’的最大正方形的边长
                2.base case: char[i][j]='0' -> dp[i][j]=0
                             char[i][j]='1' -> dp[i][j]=1
                3.状态转移：
                    dp[i][j] = min(左，上，左上)
        */
        // base condition：若矩阵为空，则返回0
        if (matrix == null || matrix.length < 1 || matrix[0].length < 1) return 0;
        int m = matrix.length;
        int n = matrix[0].length;
        // 相当于已经预处理新增第一行、第一列均为0
        int[][] dp = new int[m+1][n+1];
        int max = 0;
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (matrix[i-1][j-1]=='1'){
                    dp[i][j] = Math.min(dp[i-1][j-1],Math.min(dp[i-1][j],dp[i][j-1]))+1;
                    max = Math.max(dp[i][j],max);
                }
            }
        }
        return max*max;
    }

    //可以发现计算dp[i][j]只用到了左，上，左上三个元素，故可以优化二维dp为一维dp
    public int maximalSquare1(char[][] matrix) {
        /*
            分析：
                1.dp[i][j]表示数组char以第i行第j列的元素为右下角的元素权威‘1’的最大正方形的边长
                2.base case: char[i][j]='0' -> dp[i][j]=0
                             char[i][j]='1' -> dp[i][j]=1
                3.状态转移：
                    dp[i][j] = min(左，上，左上)
        */
        // base condition
        if (matrix == null || matrix.length < 1 || matrix[0].length < 1) return 0;
        int m = matrix.length;
        int n = matrix[0].length;
        // 相当于已经预处理新增第一行、第一列均为0
        int[] dp = new int[n+1];
        int max = 0;
        for (int i = 1; i <= m; i++) {
            int leftup = 0;
            for (int j = 1; j <= n; j++) {
                if (matrix[i-1][j-1]=='1'){
                    int tmp = dp[j];
                    dp[j] = Math.min(leftup,Math.min(dp[j],dp[j-1]))+1;
                    leftup = tmp;
                    max = Math.max(dp[j],max);
                }else {
                    dp[j] = 0;
                }
            }
        }
        return max*max;
    }


}
