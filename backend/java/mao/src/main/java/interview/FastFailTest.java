package interview;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.concurrent.CopyOnWriteArrayList;

public class FastFailTest {
	public static void main(String[] args) {
//		CopyOnWriteArrayList<Integer> numbers
//	      = new CopyOnWriteArrayList<>(new Integer[]{1, 3, 5, 8});
//
//	    Iterator<Integer> iterator = numbers.iterator();
//	    while (iterator.hasNext()) {
//	    	iterator.next();
//	        iterator.remove(); //exception
////	        if(iterator.next() == 3) {
////	        	numbers.add(9);
////	        }
//	    }
//	    System.out.println(numbers);
	    
//	    List<Integer> numbers1 = new ArrayList<Integer>(Arrays.asList(1,3,5,8));
//	    ListIterator<Integer> iterator1 = numbers1.listIterator();
//	    while (iterator1.hasNext()) {
//	    	if(iterator1.next() == 3) {
//	    		//iterator1.remove(); // No exception
//	    		iterator1.add(9); // No exception
//	    	}
//	    }
//	    System.out.println(numbers1);
	    
//	    List<Integer> numbers
//	      = new ArrayList<Integer>(Arrays.asList(1,3,5,8));
//
//	    Iterator<Integer> iterator = numbers.iterator();
//	    while (iterator.hasNext()) {
////	    	iterator.next();
////	        iterator.remove(); 
//	        if(iterator.next() == 3) {
//	        	numbers.add(9); //Exception!!
//	        }
//	    }
//	    System.out.println(numbers);
		
		List<Integer> integers = new ArrayList<Integer>(Arrays.asList(1,3,5,8));
		integers.removeIf(i -> i == 3);
		System.out.println(integers);
	}
	
}
