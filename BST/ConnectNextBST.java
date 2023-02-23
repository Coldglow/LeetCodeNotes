package BST;

import Structures.BSTWithNext;

public class ConnectNextBST {
    // https://leetcode.cn/problems/populating-next-right-pointers-in-each-node/
    // 这道题是满二叉树
    // 使用next指针 层次遍历  时间复杂度 O(N)  空间复杂度O(1)
    // 另一种方法就是使用队列层次遍历  时间复杂度 O(N)  空间复杂度O(N)
    public BSTWithNext connect(BSTWithNext root) {
        if (root == null) {
            return null;
        }
        // 因为是满二叉树，所以如果root和root.left不为空
        // 那么至少有两层
        BSTWithNext mostLeft = root;
        while (mostLeft.left != null) {
            // 固定每一层最左边的节点
            BSTWithNext head = mostLeft;
            while (head != null) {
                head.left.next = head.right;
                if (head.next != null) {
                    head.right.next = head.next.left;
                }
                head = head.next;
            }
            mostLeft = mostLeft.left;
        }
        return root;
    }


    // https://leetcode.cn/problems/populating-next-right-pointers-in-each-node-ii/
    // 和上面不一样的是本题的树不是满二叉树
    // 缝缝补补  不如人家方法准确
    public BSTWithNext connect2(BSTWithNext root) {
        if (root == null) {
            return null;
        }
        BSTWithNext mostLeft = root;
        while (mostLeft != null) {
            BSTWithNext head = mostLeft;
            // 记录当前层哪个节点需要连接next
            // 防止 7 null null null null 8 这种情况
            BSTWithNext leftJoin;
            while (head != null) {
                // 如果head不存在左右孩子，head直接右移
                if (head.left == null && head.right == null) {
                    head = head.next;
                    mostLeft = mostLeft.left != null || mostLeft.right != null ? mostLeft : head;
                    continue;
                }
                // 左右孩子都存在
                if (head.left != null && head.right != null) {
                    head.left.next = head.right;
                    // 如果左右孩子都存在，那么一定是右孩子需要连接next指针
                    leftJoin = head.right;
                } else {
                    // 如果左右孩子只存在一个，记录哪个孩子要和右边的节点连接
                    leftJoin = head.right != null ? head.right : head.left;
                }
                // head存在next,判断head.next是否存在左右孩子
                // 如果存在则leftJoin连接
                // 如果不存在，head继续右移
                while (head.next != null) {
                    if (head.next.left == null && head.next.right == null) {
                        head = head.next;
                    } else if (head.next.left != null) {
                        leftJoin.next = head.next.left;
                        // 如果此时mostLeft没有左右孩子，需要移动到当前head再往下
                        mostLeft = mostLeft.left != null || mostLeft.right != null ? mostLeft : head;
                        break;
                    } else {
                        leftJoin.next = head.next.right;
                        mostLeft = mostLeft.left != null || mostLeft.right != null ? mostLeft : head;
                        break;
                    }
                }
                head = head.next;
            }
            if (mostLeft == null) {
                break;
            }
            mostLeft = mostLeft.left != null ? mostLeft.left : mostLeft.right;
        }
        return root;
    }

    // 在每一层的左边用一个虚拟节点把每层串起来
    // 虚拟节点不变  相当于每层最左边的节点
    // 每层结束之后dummy赋值给cur
    public BSTWithNext connect3(BSTWithNext root) {
        if (root == null) {
            return null;
        }
        BSTWithNext cur = root;
        while (cur != null) {
            BSTWithNext dummy = new BSTWithNext();
            BSTWithNext pre = dummy;
            while (cur != null) {
                if (cur.left != null) {
                    pre.next = cur.left;
                    pre = cur.left;
                }
                if (cur.right != null) {
                    pre.next = cur.right;
                    pre = cur.right;
                }
                cur = cur.next;
            }
            cur = dummy.next;
        }
        return root;
    }
}
