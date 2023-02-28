// https://leetcode.cn/problems/house-robber-iii/
package DynamicProgramming;

import Structures.TreeNode;

import java.util.HashMap;

/**
 * @author David Wong
 * @date 27/02/2023 16:45
 * Scanner in = new Scanner(System.in);
 */
public class HouseRobber03 {

    public int rob(TreeNode root) {
        int[] res = dfs(root);
        return Math.max(res[0], res[1]);
    }

    // 返回一个长度为2的数组，0位置表示不偷该节点所能取得的最大额度，1位置表示偷该节点能取得的最大额度
    // 当不偷该节点的时候，不应该直接偷该节点的子节点，而应该选择子节点中返回的最大值去偷
    public int[] dfs(TreeNode node) {
        if (node == null) {
            return new int[] {0, 0};
        }
        if (node.left == null && node.right == null) {
            return new int[] {0, node.val};
        }
        int[] left = dfs(node.left);
        int[] right = dfs(node.right);
        // cur[0]应该是左孩子返回的最大值 + 右孩子返回的最大值
        // 不能left[1] + right[1]，这样就表示一定要取左孩子和右孩子，但其实不是这样
        // 当不偷该节点的时候，左孩子和右孩子不一定也被偷，所以要取最大值去偷
        return new int[] {Math.max(left[0], left[1]) + Math.max(right[0], right[1]), left[0] + right[0] + node.val};
    }

    // 记忆化搜索
    private HashMap<TreeNode, Integer> map = new HashMap();
    public int rob2(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            return root.val;
        }
        // 如果存在记录就直接返回
        if (map.containsKey(root)) {
            return map.get(root);
        }
        // 偷父节点
        int val1 = root.val;
        // 偷父节点的左子树，跳过左孩子；偷父节点的右子树，跳过右孩子
        if (root.left != null) {
            val1 += rob2(root.left.left) + rob2(root.left.right);
        }
        if (root.right != null) {
            val1 += rob2(root.right.left) + rob2(root.right.right);
        }
        /// 不偷父节点，那就直接偷左孩子和右孩子
        int val2 = rob2(root.left) + rob2(root.right);
        map.put(root, Math.max(val1, val2));
        return Math.max(val1, val2);
    }
}
