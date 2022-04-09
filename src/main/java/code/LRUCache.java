package code;

import java.util.HashMap;
import java.util.Map;
/**
 * @Author SunnyJ
 * @Date 2022/4/9 3:35 下午
 */
public class LRUCache {
    private Map<Integer,DoubleLinkedNode> cache = new HashMap<>();
    private int size;
    private int capacity;
    private DoubleLinkedNode fakeHead;
    private DoubleLinkedNode fakeTail;
    class DoubleLinkedNode {
        int key;
        int value;
        DoubleLinkedNode pre;
        DoubleLinkedNode next;
        public DoubleLinkedNode() {
        }
        public DoubleLinkedNode(int k, int v) {
            this.key = k;
            this.value = v;
        }
    }
    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        fakeHead = new DoubleLinkedNode();
        fakeTail = new DoubleLinkedNode();
        fakeHead.next = fakeTail;
        fakeTail.pre = fakeHead;
    }
    public int get(int key) {
        DoubleLinkedNode node = cache.get(key);
        if(node == null) return  -1;
        moveToHead(node);
        return node.value;
    }
    public void put(int key, int value) {
        DoubleLinkedNode node = cache.get(key);
        if(node == null){
            DoubleLinkedNode newNode = new DoubleLinkedNode(key, value);
            cache.put(key,newNode);
            size++;
            if(size>capacity){
                DoubleLinkedNode tail = removeTail();
                cache.remove(tail.key);
                size--;
            }
        }else{
            node.value = value;
            moveToHead(node);
        }
    }

    private DoubleLinkedNode removeTail() {
        DoubleLinkedNode
    }

    private void moveToHead(DoubleLinkedNode node) {
        remove(node);
        addToHead(node);
    }

    private void remove(DoubleLinkedNode node) {
        node.pre.next = node.next;
        node.next.pre = node.pre;
    }
    private void addToHead(DoubleLinkedNode node) {

    }
}
