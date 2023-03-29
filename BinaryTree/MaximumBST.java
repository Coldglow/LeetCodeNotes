// https://leetcode.com/problems/maximum-binary-tree/
package BinaryTree;

import Structures.TreeNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class MaximumBST {

    public TreeNode constructMaximumBinaryTree(int[] nums) {
        if (nums.length == 1) {
            return new TreeNode(nums[0]);
        }
        return constructMax(nums, 0, nums.length - 1);
    }

    public TreeNode constructMax(int[] nums, int left, int right) {
        if (left > right) {
            return null;
        }
        // [left, right]这段范围内的最大值的下标
        int index = getMax(nums, left, right);
        // [left, right]范围内的最大值
        int rootVal = nums[index];
        TreeNode root = new TreeNode(rootVal);
        root.left = constructMax(nums, left, index - 1);
        root.right = constructMax(nums, index + 1, right);
        return root;
    }

    public int getMax(int[] nums, int left, int right) {
        int index = left;
        int max = nums[left];
        while (left <= right) {
            if (max < nums[left]) {
                index = left;
                max = nums[left];
            }
            left++;
        }
        return index;
    }

    /**
     * 先通过单调栈记录左右两侧最近的比该节点小的节点
     * 然后比较左右两侧较大值：
     *  1. 如果左侧值小于右侧值，说明当前元素是左侧值的右孩子
     *  2. 否则说明当前元素是右侧值的左孩子
     * @param nums nums
     * @return nu
     */
    public TreeNode buildTree2(int[] nums) {
        if (nums.length == 1) {
            return new TreeNode(nums[0]);
        }
        int n = nums.length;
        Stack<Integer> stack = new Stack<>();
        TreeNode[] tree = new TreeNode[n];
        // left[i]表示在nums[i]左侧比nums[i]大的数的下标
        // 不是表示nums[i]的左孩子
        int[] left = new int[n];
        int[] right = new int[n];
        Arrays.fill(left, -1);
        Arrays.fill(right, -1);

        for (int i = 0; i < n; i++) {
            TreeNode node = new TreeNode(nums[i]);
            tree[i] = node;
            // 如果nums[i]大于栈顶元素，记录并弹出
            while (!stack.isEmpty() && nums[i] > nums[stack.peek()]) {
                right[stack.pop()] = i;
            }
            // 如果当前栈不为空，并且nums[i]小于栈顶元素
            // 说明栈顶元素是nums[i]左侧最近的大于nums[i]的节点
            // 记录在left数组中
            if (!stack.isEmpty()) {
                left[i] = stack.peek();
            }
            stack.push(i);
        }
        // 根据left和right的值加工出树
        TreeNode root = null;
        for (int i = 0; i < n; i++) {
            if (left[i] == -1 && right[i] == -1) {
                // 根节点
                root = tree[i];
            } else if (right[i] == -1 || (left[i] != -1 && nums[left[i]] < nums[right[i]])) {
                // right[i] == -1 说明tree[i]是root的右孩子
                // 如果right != -1，则进入后半部分判断：
                //      如果left != -1, 排除根节点左孩子的情况
                //      比较左右两侧的大小
                //      如果左侧元素小，那么tree[i]就是左侧元素的右子节点
                tree[left[i]].right = tree[i];
            } else {
                //      否则，tree[i]就是右侧元素的左子节点
                //      又或者说是连接根节点的左孩子
                tree[right[i]].left = tree[i];
            }
        }
        return root;
    }

    /**
     * 也是利用单调栈 不过省去了left和right的空间
     * 当栈顶元素小于当前元素的时候，栈顶元素是当前元素的左孩子，循环直到栈顶元素大于当前元素或者栈为空
     * 当栈顶元素小于当前元素的时候，栈顶元素是当前元素的左孩子
     * 二者的判断顺序不能改变
     * @param nums  123
     * @return  31
     */
    public TreeNode buildTree3(int[] nums) {
        if (nums.length == 1) {
            return new TreeNode(nums[0]);
        }

        List<Integer> stack = new ArrayList<>();
        int n = nums.length;
        TreeNode[] tree = new TreeNode[n];
        for (int i = 0; i < n; i++) {
            tree[i] = new TreeNode(nums[i]);
            while (!stack.isEmpty() && nums[i] > nums[stack.get(stack.size() - 1)]) {
                tree[i].left = tree[stack.get(stack.size() - 1)];
                stack.remove(stack.size() - 1);
            }
            if (!stack.isEmpty()) {
                tree[stack.get(stack.size() - 1)].right = tree[i];
            }
            stack.add(i);
        }
        return tree[stack.get(0)];
    }
}
