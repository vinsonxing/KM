package interview;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BinarySearchTest {

	public static void main(String[] args) {
		List<String> items = new ArrayList<>();
		 
        // Adding elements to object
        // using add() method
        items.add("Shoes");
        items.add("Toys");
        items.add("Horse");
        items.add("Ball");
        items.add("Grapes");
 
        // Sort the List
        Collections.sort(items);
  
        // BinarySearch on the List
        System.out.println(
            "The index of Horse is "
            + Collections.binarySearch(items, "Horse"));
 
        // BinarySearch on the List  – (insertion point where the element should have been present if exist)-1. 
        System.out.println(
            "The index of Z is "
            + Collections.binarySearch(items, "Z")); // -5-1
        System.out.println(
            "The index of D is "
            + Collections.binarySearch(items, "D")); //-1-1
	}
}
