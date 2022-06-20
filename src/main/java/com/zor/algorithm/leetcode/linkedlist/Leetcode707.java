package com.zor.algorithm.leetcode.linkedlist;

/**
 * 707. 设计链表
 * 设计链表的实现。您可以选择使用单链表或双链表。单链表中的节点应该具有两个属性：val 和 next。val 是当前节点的值，next 是指向下一个节点的指针/引用。如果要使用双向链表，则还需要一个属性 prev 以指示链表中的上一个节点。假设链表中的所有节点都是 0-index 的。
 * <p>
 * 在链表类中实现这些功能：
 * <p>
 * get(index)：获取链表中第 index 个节点的值。如果索引无效，则返回-1。
 * addAtHead(val)：在链表的第一个元素之前添加一个值为 val 的节点。插入后，新节点将成为链表的第一个节点。
 * addAtTail(val)：将值为 val 的节点追加到链表的最后一个元素。
 * addAtIndex(index,val)：在链表中的第 index 个节点之前添加值为 val  的节点。如果 index 等于链表的长度，则该节点将附加到链表的末尾。如果 index 大于链表长度，则不会插入节点。如果index小于0，则在头部插入节点。
 * deleteAtIndex(index)：如果索引 index 有效，则删除链表中的第 index 个节点。
 * <p>
 * <p>
 * 示例：
 * <p>
 * MyLinkedList linkedList = new MyLinkedList();
 * linkedList.addAtHead(1);
 * linkedList.addAtTail(3);
 * linkedList.addAtIndex(1,2);   //链表变为1-> 2-> 3
 * linkedList.get(1);            //返回2
 * linkedList.deleteAtIndex(1);  //现在链表是1-> 3
 * linkedList.get(1);            //返回3
 * <p>
 * <p>
 * 提示：
 * <p>
 * 所有val值都在 [1, 1000] 之内。
 * 操作次数将在  [1, 1000] 之内。
 * 请不要使用内置的 LinkedList 库。
 * create by tm on 2022/6/20
 */
public class Leetcode707 {
    public static void main(String[] args) {
        MyDoubleLinkedList linkedList = new MyDoubleLinkedList();
        linkedList.addAtIndex(0, 10);
        linkedList.addAtIndex(0, 20);
        linkedList.addAtIndex(1, 30);
        System.out.println(linkedList.get(0));


    }

    // todo 单链表版本，更加简单
    class MyLinkedList {
        public MyLinkedList() {

        }

        public int get(int index) {

            return index;
        }

        public void addAtHead(int val) {

        }

        public void addAtTail(int val) {

        }

        public void addAtIndex(int index, int val) {

        }

        public void deleteAtIndex(int index) {

        }
    }

    static class MyDoubleLinkedList {
        int size;
        Node head;
        Node tail;

        public MyDoubleLinkedList() {
            this.size = 0;
            head = new Node(-1);
            tail = new Node(-1);
            head.next = tail;
            tail.prev = head;
        }

        public int get(int index) {
            Node node = getNodeAt(index);
            if (node != null) return node.val;
            return -1;
        }

        public void addAtHead(int val) {
            Node node = new Node(val);
            Node next = head.next;
            head.next = node;
            node.prev = head;
            node.next = next;
            next.prev = node;
            size++;
        }

        public void addAtTail(int val) {
            Node node = new Node(val);
            Node prev = tail.prev;
            prev.next = node;
            node.prev = prev;
            node.next = tail;
            tail.prev = node;
            size++;
        }

        public void addAtIndex(int index, int val) {
            if (index == size) {
                addAtTail(val);
            } else if (index < 0) {
                addAtHead(val);
            } else if (index < size) {
                Node prev = getNodeAt(index - 1);
                if (prev == null) return;
                Node node = new Node(val);
                Node next = prev.next;
                prev.next = node;
                node.next = next;
                node.prev = prev;
                next.prev = node;
                size++;
            }
        }

        public void deleteAtIndex(int index) {
            Node node = getNodeAt(index);
            if (node != null) {
                Node prev = node.prev;
                Node next = node.next;
                prev.next = next;
                next.prev = prev;
                node.prev = null;
                node.next = null;
            }
        }

        private Node getNodeAt(int index) {
            int temp = 0;
            Node cur = head;
            while (temp <= index && cur.next != tail) {
                cur = cur.next;
                temp++;
            }
            if (temp > index) {
                return cur;
            }
            return null;
        }
    }

    static class Node {
        int val;
        Node prev;
        Node next;

        Node(int val) {
            this.val = val;
        }
    }
}
