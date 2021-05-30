package 动态规划;

/**
 * 96. 不同的二叉搜索树
 * 给定一个整数 n，求以 1 ... n 为节点组成的二叉搜索树有多少种？
 */
public class UniqueBinarySearchTrees {

    //递归 效率低
    public int numTrees(int n) {
        return G(n);
    }

    public static int G(int n){

        if (n<2){return 1; }

        int sum = 0;
        for (int i = 1; i < n+1; i++) {
            sum += F(i,n);
        }
        return sum;
    }

    private static int F(int i, int n) {
        return G(i-1)*G(n-i);
    }

}

class UniqueBinarySearchTrees1 {

    //动态规划
    public int numTrees(int n) {

        int[] G = new int[n+1];
        G[0]=G[1]=1;
        for (int i = 2; i < n + 1; i++) {
            for (int j = 1; j <= n; j++) {
                G[i]+=G[j-1]*G[n-j];
            }
        }
        return G[n];
    }

    //公式法
    public int numTrees1(int n) {

        long c = 1;
        for (int i = 0; i < n; i++) {
            c = 2*(2*i+1)*c/(i+2);
        }

        return (int)c;
    }


}