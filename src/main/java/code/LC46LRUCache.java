package code;

import java.util.HashMap;
import java.util.Map;
/**
 * @Author SunnyJ
 * @Date 2022/4/9 3:35 下午
 */
public class LC46LRUCache {
    private Map<Integer, DoubleLinkedNode> cache = new HashMap<>();
    private int size;
    private int capacity;
    private DoubleLinkedNode fakeHead;
    private DoubleLinkedNode fakeTail;
    class DoubleLinkedNode {
        int key;
        int value;
        DoubleLinkedNode prev;
        DoubleLinkedNode next;
        public DoubleLinkedNode() {
        }
        public DoubleLinkedNode(int k, int v) {
            this.key = k;
            this.value = v;
        }
    }
    public LC46LRUCache(int capacity) {
        this.size = 0;
        this.capacity = capacity;
        fakeHead = new DoubleLinkedNode();
        fakeTail = new DoubleLinkedNode();
        fakeHead.next = fakeTail;
        fakeTail.prev = fakeHead;
    }
    //get方法
    public int get(int key) {
        //1.查询哈希
        DoubleLinkedNode node = cache.get(key);
        //为空返回-1
        if (node == null) {
            return -1;
        }
        //2.哈希查询到，将该节点移动到头部
        moveToHead(node);
        return node.value;
    }
    //put方法
    public void put(int key, int value) {
        //1.哈希查询，若不存在节点，则添加
        DoubleLinkedNode node = cache.get(key);
        if (node == null) {
            //创建节点，添加链表，添加哈希，增加size
            DoubleLinkedNode newNode = new DoubleLinkedNode(key, value);
            //新节点加入哈希
            cache.put(key, newNode);
            //新节点加入头部
            addToHead(newNode);
            ++size;
            //容量超出，在链表和哈希中删除尾节点
            if (size > capacity) {

                DoubleLinkedNode tail = removeTail();
                cache.remove(tail.key);
                --size;
            }//若哈希中存在该节点，覆盖链表中的值，并更新该节点作为最近使用
        } else {
            node.value = value;
            moveToHead(node);
        }
    }
    //1.将节点移动到头部
    private void moveToHead(DoubleLinkedNode node) {
        //删除节点
        removeNode(node);
        //头部添加该节点
        addToHead(node);
    }
    //2.删除节点
    private void removeNode(DoubleLinkedNode node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }
    //3.头部添加节点
    private void addToHead(DoubleLinkedNode node) {
        node.prev = fakeHead;
        node.next = fakeHead.next;
        fakeHead.next.prev = node;
        fakeHead.next = node;
    }
    //4.删除尾节点，并返回被删除的节点
    private DoubleLinkedNode removeTail(){
        DoubleLinkedNode tail = fakeTail.prev;
        removeNode(tail);
        return tail;
    }
}
