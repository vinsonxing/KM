package interview;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

public class ArrayTest {
	public static <T> void main(String[] args) {
		String[] stutter = new String[3];
		Arrays.fill(stutter, "once");
		System.out.println(Arrays.toString(stutter));
		
		String[] intro = new String[] { "once", "upon", "a", "time" };
		String[] sorted = Arrays.copyOf(intro, 4);
		Arrays.sort(sorted);
//		Stream.of(sorted).forEach(System.out::println);
		System.out.println(Arrays.toString(intro));
		int exact = Arrays.binarySearch(sorted, "time");
		int caseInsensitive = Arrays.binarySearch(sorted, "TiMe", (a,b)->a.compareToIgnoreCase(b));
//		int caseInsensitive = Arrays.binarySearch(sorted, "TiMe", String::compareToIgnoreCase);
		System.out.println(caseInsensitive);
		
		//T[] elements = new T[5];
		
		
		String[] strings = Stream.of("A", "AAA", "B", "AAB", "C")
				  .filter(string -> string.startsWith("A"))
				  .toArray(len->new String[len]);
				  //.toArray(String[]::new); 
		
//		Object[] objectArray = new Long[1];
//		objectArray[0] = "I don't fit in";
		
		//List<Object> ol = new ArrayList<Long>(); // Incompatible types
		//ol.add("I don't fit in");
		
		// copy section
		String[] strArray = {"orange", "red", "green"};
		String[] copyArray = Arrays.stream(strArray).toArray(String[]::new);
		strArray[0] = "blue";
		System.out.println("original:" + Arrays.toString(strArray));
		System.out.println("copy:" + Arrays.toString(copyArray));
		
		Object[] src = { "NYC", "Washington DC", "New Delhi" };
	    String[] dest = new String[src.length];
	    System.arraycopy(src, 0, dest, 0, src.length);
	 	//System.out.println(Arrays.toString(dest));
	 	
	 	List<String> items = new LinkedList();
		items.add("first item");
		items.add("second item");
		String[] itemsAsArray =  items.toArray(new String[0]); // can't cast directly from Object[] to String[] but below case can
	
		String[] strarr = (String[]) getStringArray();
	    System.out.println(Arrays.toString(strarr));
		
		
		Object[] objectArray = { "NYC", "Washington DC", "New Delhi" };
        String[] stringArray = Arrays.copyOf(objectArray,
                                            objectArray.length,
                                            String[].class);
        //System.out.println(Arrays.toString(stringArray));
        
        List<Object> test = new ArrayList<>();
        test.add(null);
        System.out.println("size:" + test.size());
        
	}
	
	
	 private static Object[] getStringArray()
     {
		 String[] strings = {"monday", "tuesday", "wednesday"};
		 return strings;
//     Object[] original = new Object[3];
//     original[0] = "a";
//     original[1] = "b";
//     original[2] = "c";
//     return original;
     }
}
