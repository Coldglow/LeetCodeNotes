package Others;

import java.util.HashMap;
import java.util.Map;

/**
 * Others.LRUCache，练习手写LinkedHashMap
 * 1. 如何通过哈希表定位节点在双向链表中的位置
 * note：
 * 双向链表一般有一个伪头部和虚尾部来标记界限
 */
public class LRUCache {
    /**
     * 双向链表节点
     */
    class DLinkedNode {
        int key;
        int value;
        DLinkedNode prev;
        DLinkedNode next;

        /**
         * 空构造函数用来初始化 伪头部和虚尾部
         */
        public DLinkedNode() {}

        public DLinkedNode(int _key, int _value) {
            key = _key;
            value = _value;
        }
    }

    // 哈希表中的key和链表节点中的key表示同一个值
    private Map<Integer, DLinkedNode> cache = new HashMap<>();
    private int size;       // size表示当前链表中有多少元素
    private int capacity;   // capacity表示链表最多可以有多少元素
    private DLinkedNode head, tail;     // 虚拟的头节点和尾节点

    public LRUCache(int capacity) {
        this.size = 0;
        this.capacity = capacity;
        head = new DLinkedNode();
        tail = new DLinkedNode();
        // 刚开始的时候头尾相连
        head.next = tail;
        tail.prev = head;
    }

    /**
     * 如果key不存在，返回 -1
     * 如果key存在，将key所对应的节点移动到头部，表示最近使用的节点
     * @param key key
     * @return 返回key对应的值
     */
    public int get(int key) {
        DLinkedNode node = cache.get(key);
        if (node == null) {
            return -1;
        }
        moveToHead(node);
        return node.value;
    }

    /**
     * 如果key存在，更新key的值，并且将key移动到链表头部
     * 如果key不存在，添加，然后判断是否超过容量，如果超过，删除尾部节点。
     * @param key
     * @param value
     * @return
     */
    public void put(int key, int value) {
        DLinkedNode node = cache.get(key);
        if (node != null) {
            node.value = value;
            moveToHead(node);
        } else {
            node = new DLinkedNode(key, value);
            addToHead(node);
            this.cache.put(key, node);
            this.size++;
            if (this.size > this.capacity) {
                DLinkedNode tail = removeTail();
                this.cache.remove(tail.key);
                this.size--;
            }
        }
    }

    /**
     * 将node节点移动到头部
     * 因为是双向链表，所以原地删除，连接前后的节点，再把node节点添加到头节点之前即可
     * @param node node
     */
    private void moveToHead(DLinkedNode node) {
        removeNode(node);
        addToHead(node);
    }

    /**
     * 连接node前后的节点，因为有头尾节点，所以不怕空指针
     * @param node node
     */
    private void removeNode(DLinkedNode node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private void addToHead(DLinkedNode node) {
        node.next = head.next;
        head.next.prev = node;
        head.next = node;
        node.prev = head;
    }

    private DLinkedNode removeTail() {
        DLinkedNode res = this.tail.prev;
        removeNode(res);
        return res;
    }
}
