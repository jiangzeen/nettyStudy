package com.jze.example6;

/**
 * @author: 蒋泽恩
 * @date: 2019/5/29 12:11
 * @description: 快慢指针法判断回文字符串
 * 时间复杂度：O(n)
 * 空间复杂度：O(1)
 */
public class Solution {

    static class LinkedNode{
        char c;
        LinkedNode next;
        public LinkedNode(char c, LinkedNode next){
            this.c = c;
            this.next = next;
        }

        public LinkedNode(char c){
            this(c, null);
        }
    }

    public static boolean isPalindrome(LinkedNode head){
        LinkedNode prev = null;
        LinkedNode slow = head;
        LinkedNode fast = head;

        while (fast != null && fast.next != null){
            fast = fast.next.next;
            LinkedNode next = slow.next;
            slow.next = prev;
            prev = slow;
            slow = next;
        }
        if (fast != null){
            slow = slow.next;
        }

        while (slow != null){
            assert prev != null;
            if (slow.c != prev.c){
                return false;
            }
            prev = prev.next;
            slow = slow.next;
        }
        return true;
    }

    public static void main(String[] args) {
         LinkedNode head = new LinkedNode('a');
         head.next = new LinkedNode('b');
         head.next.next = new LinkedNode('b');
         head.next.next.next = new LinkedNode('a');
         System.out.println(isPalindrome(head));
    }
}
