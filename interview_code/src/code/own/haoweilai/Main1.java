package code.own.haoweilai;

/**
 * @Description:
 * @User: Dell
 * @Author: hezhidong
 * @Date: 2020/9/5 13:57
 */
public class Main1 {
    public static void main(String[] args) {

    }

    public ListNode reverseList(ListNode head) {
        ListNode newHead = null;
        while (head != null) {
            ListNode temp = head.next;
            head.next = newHead;
            newHead = head;
            head = temp;
        }
        return newHead;
    }

    class ListNode {
        int val;
        ListNode next = null;
    }
}
