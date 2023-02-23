package BackTracking;

public class BackTrackingTemple {
    /*
     * 如果是记录路径的问题，例如 子集问题  则需要在处理节点之后记录
     * 如果是记录叶子节点问题，则需要在终止条件处记录
     *
     * 全局数组用于纵向（路径）去重
     * void backtracking(参数) {
     *     剪枝操作
     *     if (终止条件) {
     *         存放结果;    // 记录叶子节点问题的情况
     *         return;
     *     }
     *     局部数组用于横向，每一层去重
     *     for (选择：本层集合中元素（树中节点孩子的数量就是集合的大小）) {
     *         剪枝操作
     *         [存放结果]      // 记录路径的问题
     *         处理节点;
     *         backtracking(路径，选择列表); // 递归
     *         回溯，撤销处理结果
     *     }
     * }
     */
}