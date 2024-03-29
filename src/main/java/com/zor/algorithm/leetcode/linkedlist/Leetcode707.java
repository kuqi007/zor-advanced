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
        MyLinkedList linkedList = new MyLinkedList();
        linkedList.addAtHead(7);
        linkedList.addAtTail(7);
        linkedList.addAtHead(9);
        linkedList.addAtTail(8);
        linkedList.addAtHead(6);
        linkedList.addAtHead(0);
        System.out.println(linkedList.get(5));
        linkedList.addAtHead(0);
        System.out.println(linkedList.get(2));
        System.out.println(linkedList.get(5));
        linkedList.addAtTail(5);


    }

    // todo 单链表版本，更加简单
    static class MyLinkedList {
        Node dummyHead;
        Integer size;

        public MyLinkedList() {
            dummyHead = new Node(-1);
            size = 0;
        }

        public int get(int index) {
            if (index < 0 || index > size - 1) return -1;
            Node cur = dummyHead.next;
            while (index > 0) {
                cur = cur.next;
                index--;
            }
            return cur.val;
        }

        public void addAtHead(int val) {
            Node next = dummyHead.next;
            Node node = new Node(val);
            dummyHead.next = node;
            node.next = next;
            size++;
        }

        public void addAtTail(int val) {
            Node cur = dummyHead;
            while (cur.next != null) {
                cur = cur.next;
            }
            cur.next = new Node(val);
            size++;
        }

        public void addAtIndex(int index, int val) {
            if (index == size) {
                addAtTail(val);
            } else if (index < 0) {
                addAtHead(val);
            } else if (index < size) {
                Node prev = dummyHead;
                for (int i = 0; i < index; i++) {
                    prev = prev.next;
                }
                Node next = prev.next;
                Node node = new Node(val);
                prev.next = node;
                node.next = next;
                size++;
            }

        }

        public void deleteAtIndex(int index) {
            if (index < 0 || index > size - 1) return;
            Node prev = dummyHead;
            for (int i = 0; i < index; i++) {
                prev = prev.next;
            }
            prev.next = prev.next.next;

            size--;
        }
    }

    static class Node {
        int val;
        Node next;

        Node(int val) {
            this.val = val;
        }
    }


    static class MyDoubleLinkedList {
        int size;
        DNode head;
        DNode tail;

        public MyDoubleLinkedList() {
            this.size = 0;
            head = new DNode(-1);
            tail = new DNode(-1);
            head.next = tail;
            tail.prev = head;
        }

        public int get(int index) {
            DNode node = getNodeAt(index);
            if (node != null) return node.val;
            return -1;
        }

        public void addAtHead(int val) {
            DNode node = new DNode(val);
            DNode next = head.next;
            head.next = node;
            node.prev = head;
            node.next = next;
            next.prev = node;
            size++;
        }

        public void addAtTail(int val) {
            DNode node = new DNode(val);
            DNode prev = tail.prev;
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
                DNode prev = getNodeAt(index - 1);
                if (prev == null) return;
                DNode node = new DNode(val);
                DNode next = prev.next;
                prev.next = node;
                node.next = next;
                node.prev = prev;
                next.prev = node;
                size++;
            }
        }

        public void deleteAtIndex(int index) {
            DNode node = getNodeAt(index);
            if (node != null) {
                DNode prev = node.prev;
                DNode next = node.next;
                prev.next = next;
                next.prev = prev;
                node.prev = null;
                node.next = null;
            }
        }

        private DNode getNodeAt(int index) {
            int temp = 0;
            DNode cur = head;
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

    static class DNode {
        int val;
        DNode prev;
        DNode next;

        DNode(int val) {
            this.val = val;
        }
    }
}
