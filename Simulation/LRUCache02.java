// https://leetcode.cn/problems/lru-cache/
package Simulation;

import java.util.HashMap;
import java.util.LinkedList;

/**
 * @author David Wong
 * @date 23/06/2023 12:53
 * Scanner in = new Scanner(System.in);
 */
public class LRUCache02 {
    /**
     * 最近最少使用 (least used)
     * 双向链表 + 哈希表
     * put逻辑:
     *  判断是否存在, 如果存在, 更新, 放到链表末尾;
     *  如果不存在, 判断缓存是否满, 如果满, 则删除头节点, 添加到末尾; 如果不满, 直接添加
     * get:
     *  判断是否存在, 不存在直接返回空.
     *  如果存在, 直接根据哈希表得到该元素的位置, 然后放到链表末尾
     * 所以不能用内置的双向链表, 需要自己实现
     */

    class Node {
        public int key;
        public int value;
        public Node prev;
        public Node next;

        Node() {};
        Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    private int capacity;
    private int count;
    // key存储的元素, value是该元素本身
    private HashMap<Integer, Node> map;
    // 双向链表
    private Node start;
    private Node end;

    public LRUCache02(int capacity) {
        this.capacity = capacity;
        this.count = 0;
        this.map = new HashMap<>();
        this.start = new Node();
        this.end = new Node();
        this.start.next = this.end;
        this.end.prev = this.start;
    }

    public int get(int key) {
        Node node = map.get(key);
        if (node == null) {
            return -1;
        }
        moveToEnd(node);
        return node.value;
    }

    public void put(int key, int value) {
        Node node = map.get(key);
        if (node == null) {
            // 不存在, 判断是否满
            if (this.count == this.capacity) {
                // map中删除
                map.remove(this.start.next.key);
                // 满了, 链表中删除第一个
                delNode(this.start.next);
            } else {
                // 没满, 直接添加
                this.count++;
            }
            Node newNode = new Node(key, value);
            addAtEnd(newNode);
            map.put(key, newNode);
        } else {
            // 存在, 修改value值,再移动到末尾
            node.value = value;
            moveToEnd(node);
        }
    }

    private void addAtEnd(Node node) {
        node.next = this.end;
        node.prev = this.end.prev;
        node.prev.next = node;
        this.end.prev = node;
    }

    private void moveToEnd(Node node) {
        delNode(node);
        addAtEnd(node);
    }

    private void delNode(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }
}
