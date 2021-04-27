package com.zor.advanced.cache.redis;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * TODO
 * https://cdn.jsdelivr.net/gh/kuqi007/picGo/2021/20210217152621.jpg
 *
 * @author zqq
 * @date 2021/2/17
 */
public class SkipList<T> {
    public static final float SKIPLIST_P = 0.5f;
    private static final int MAX_LEVEL = 16;

    private int levelCount = 1;

    // 层数
    private int level = 0;
    // 顶层节点
    private SkipListNode<T> top;

    public SkipList(int level) {
        this.level = level;
        int i = level;
        SkipListNode<T> temp = null;
        SkipListNode<T> pre = null;
        while (i-- != 0) {
            temp = new SkipListNode<T>(null, Double.MIN_VALUE);
            temp.down = pre;
            pre = temp;
        }
        top = temp;
    }

    /**
     * 搜索
     */
    public T get(double score) {
        SkipListNode<T> t = top;
        while (t != null) {
            if (t.score == score) {
                return t.val;
            }
            if (t.next == null) {
                if (t.down != null) {
                    t = t.down;
                    continue;
                } else {
                    return null;
                }
            }
            // 如果下一个值比要找的值大，向下走
            if (t.next.score > score) {
                t = t.down;
            } else {
                // 下个数比他小或者等于则到下一个节点
                t = t.next;
            }
        }
        return null;
    }

    public void put(double score, T val) {
        //1，找到需要插入的位置
        SkipListNode<T> t = top, cur = null;//若cur不为空，表示当前score值的节点存在
        List<SkipListNode<T>> path = new ArrayList<>();//记录每一层当前节点的前驱节点
        while (t != null) {
            if (t.score == score) {
                cur = t;
                break;//表示存在该值的点，表示需要更新该节点
            }
            if (t.next == null) {
                path.add(t);//需要向下查找，先记录该节点
                if (t.down != null) {
                    t = t.down;
                    continue;
                } else {
                    break;
                }
            }
            if (t.next.score > score) {
                path.add(t);//需要向下查找，先记录该节点
                if (t.down == null) {
                    break;
                }
                t = t.down;
            } else
                t = t.next;
        }
        if (cur != null) {
            while (cur != null) {
                cur.val = val;
                cur = cur.down;
            }
        } else {//当前表中不存在score值的节点，需要从下到上插入
            int lev = getRandomLevel();
            if (lev > level) {//需要更新top这一列的节点数量，同时需要在path中增加这些新的首节点
                SkipListNode<T> temp = null;
                SkipListNode<T> prev = top;//前驱节点现在是top了
                while (level++ != lev) {
                    temp = new SkipListNode<T>(null, Double.MIN_VALUE);
                    path.add(0, temp);//加到path的首部
                    temp.down = prev;
                    prev = temp;
                }
                top = temp;//头节点
                level = lev;//level长度增加到新的长度
            }
            //从后向前遍历path中的每一个节点，在其后面增加一个新的节点
            SkipListNode<T> downTemp = null, temp = null, prev = null;
//            System.out.println("当前深度为"+level+",当前path长度为"+path.size());
            for (int i = level - 1; i >= level - lev; i--) {
                temp = new SkipListNode<T>(val, score);
                prev = path.get(i);
                temp.next = prev.next;
                prev.next = temp;
                temp.down = downTemp;
                downTemp = temp;
            }
        }
    }

    private int getRandomLevel() {
        Random random = new Random();
        int lev = 1;
        while (random.nextInt() % 2 == 0)
            lev++;
        return lev;
    }

    public void delete(double score) {
        SkipListNode<T> t = top;
        while (t != null) {
            if (t.next == null) {
                t = t.down;
                continue;
            }
            if (t.next.score == score) {
                // 在这里说明找到了该删除的节点
                t.next = t.next.next;
                t = t.down;
                //删除当前节点后，还需要继续查找之后需要删除的节点
                continue;
            }
            if (t.next.score > score)
                t = t.down;
            else
                t = t.next;
        }
    }


    // 构建一个跳表节点属性
    static class SkipListNode<T> {
        T val;
        SkipListNode<T> next, down;
        double score;

        SkipListNode() {
        }

        SkipListNode(T val, double score) {
            this.val = val;
            this.score = score;
        }
    }


}
