package interview;

import java.util.ArrayDeque;

public class CircularArrayTest {
	// not circular
	public static void print(char[] a, int ind) {
		int size = a.length;
		char[] b = new char[2*size];
		for(int i=0; i<size; i++) {
			b[i] = b[size+i] = a[i];
		}
		for(int j=ind; j<ind+size; j++) {
			System.out.print(b[j]+" ");
		}
	}
	public static void main(String[] args) {
		char[] arr = new char[]{'a','b','c','d','e','f'};
		print(arr,3);
//		ArrayDeque<Integer> ad = new ArrayDeque<>(10);
//		ad.add(1);
//		ad.addFirst(2);
//		ad.add(3);
//		ad.addLast(4);
//		ad.addFirst(5); 
//		ad.forEach(System.out::println);
		//System.out.println(1&1);
	}
}
