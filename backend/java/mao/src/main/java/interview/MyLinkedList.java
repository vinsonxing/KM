package interview;

import java.util.ArrayList;
import java.util.Collection;

public class MyLinkedList<E>{
	transient Node<E> first;
	transient Node<E> last;
	transient int size = 4;
	public boolean addAll(int index, Collection<? extends E> c) {
        //checkPositionIndex(index);

        Object[] a = c.toArray();
        int numNew = a.length;
        if (numNew == 0)
            return false;

        Node<E> pred, succ;
        if (index == size) {
            succ = null;
            pred = last; 
        } else {
            succ = node(index);
            pred = succ.prev;
        }

        for (Object o : a) {
            @SuppressWarnings("unchecked") E e = (E) o;
            Node<E> newNode = new Node<>(pred, e, null);
            if (pred == null)
                first = newNode;
            else
                pred.next = newNode;
            pred = newNode;
        }

        if (succ == null) {
            last = pred;
        } else {
            pred.next = succ;
            succ.prev = pred;
        }

        size += numNew;
        //modCount++;
        return true;
    }
	Node<E> node(int index) {
        // assert isElementIndex(index);

        if (index < (size >> 1)) {
            Node<E> x = first;
            for (int i = 0; i < index; i++)
                x = x.next;
            return x;
        } else {
            Node<E> x = last;
            for (int i = size - 1; i > index; i--)
                x = x.prev;
            return x;
        }
    }
	private static class Node<E> {
        E item;
        Node<E> next;
        Node<E> prev;

        Node(Node<E> prev, E element, Node<E> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
	 }
	public static void main(String[] args) {
		  MyLinkedList<Integer> mll = new MyLinkedList<Integer>(); 
		  Node<Integer> n1 = new Node<Integer>(null,1,null); 
		  Node<Integer> n2 = new Node<Integer>(null,2,null); 
		  Node<Integer> n3 = new Node<Integer>(null,3,null); 
		  Node<Integer> n4 = new Node<Integer>(null,4,null); 
		  mll.first = n1; 
		  mll.last = n4; 
		  n1.next = n2;
		  n2.prev = n1; 
		  n2.next = n3; 
		  n3.prev = n2; 
		  n3.next = n4; 
		  n4.prev = n3;
		  Node<Integer> start = mll.first; 
		  Collection<Integer> toAdd = new ArrayList<Integer>(); 
		  toAdd.add(5); 
		  toAdd.add(6); 
		  toAdd.add(7); 
		  toAdd.add(8);
		  mll.addAll(3, toAdd); 
		  while(start != null) { 
			  System.out.print(start.item + " ");
			  start = start.next; 
		  }	  
		  
	}
}
