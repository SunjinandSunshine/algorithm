package code;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @Author SunnyJ
 * @Date 2022/2/25 10:44 上午
 */
public class Test {
    public static void main(String[] args) {
        ListNode l1 = new ListNode(0); l1.next = new ListNode(0);
        ListNode l2 = new ListNode(4); l2.next = new ListNode(8);
        mergeTwoLists(l1,l2);
    }
    public static void mergeTwoLists(ListNode l1, ListNode l2) {
//        ListNode dummy = new ListNode(-1), cur = dummy;
//        while(l1!=null && l2!=null){
//            if(l1.val<l2.val){
//                cur.next = l1;
//                l1 = l1.next;
//                cur = cur.next;
//            }else{
//                cur.next = l2;
//                l2 = l2.next;
//                cur = cur.next;
//            }
//        }
//        cur.next=l2==null?l1:l2;
//        //return dummy.next;
//        while (dummy.next!=null){
//            System.out.println(dummy.next.val);
//            dummy.next = dummy.next;
//        }
        Queue<Integer> queue = new PriorityQueue<>((o1,o2)->{
            return o2-o1;
        });
        queue.add(4);
        queue.add(5);
        System.out.println(queue.poll());
    }
}

