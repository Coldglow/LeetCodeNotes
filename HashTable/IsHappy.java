// https://leetcode.cn/problems/happy-number/
package HashTable;

public class IsHappy {

    public int getNext(int n) {
        int sum = 0;
        while (n > 0) {
            int p = n % 10;
            n /= 10;
            sum += p * p;
        }
        return sum;
    }

    /**
     * 想出快慢指针的这个解法的前提是直到如果一个数n不是快乐数，那么他会一直循环
     * 这样就n的变化过程就可以当作一个链表，使用快慢指针，当二者相遇的时候
     * 表示n不是快乐数
     * 当fast == 1说明是
     * @param n   n
     * @return   f  t
     */
    public boolean isHappy(int n) {
        int fast = getNext(n);
        int slow = fast;
        fast = getNext(fast);
        while (slow != fast) {
            fast = getNext(fast);
            fast = getNext(fast);
            slow = getNext(slow);
            if (fast == 1) {
                break;
            }
        }
        return fast == 1;
    }
}
