package com.study.algo;

import java.util.HashMap;
import java.util.Map;

public class LRUCache {
    int capacity;
    Map<Integer, MyNode> map = new HashMap<>();
    MyNode head = null;
    MyNode tail = null;
    public LRUCache(int capacity){
        this.capacity = capacity;
    }
    public int get(int key){
        MyNode myNode = map.get(key);
        if (myNode != null){
            remove(myNode);
            setHead(myNode);
            return myNode.value;
        } else return -1;
    }
    public void put(int key, int value){
        MyNode node = map.get(key);
        if(node != null){
            node.value = value;
            remove(node);
        } else{
            node = new MyNode(key, value);
            map.put(key, node);
            if(map.size() > capacity) {
                map.remove(tail.key);
                remove(tail);
            }
        }
        setHead(node);
    }
    private void setHead(MyNode node) {
        node.prev = null;
        node.next = head;
        if(head != null) {
            head.prev = node;
        }
        node = head;
        if(tail == null) {
            tail = node;
        }
    }
    private void remove(MyNode node) {
        MyNode prev = node.prev;
        MyNode next = node.next;
        if (prev != null) {
            prev.next = next;
        } else {
            head = next;
        }
        if (next != null) {
            next.prev = prev;
        } else {
            tail = prev;
        }
    }
}
class MyNode{
    int key;
    int value;
    MyNode prev;
    MyNode next;
    public MyNode(int key, int value) {
        this.key = key;
        this.value = value;
    }
}
