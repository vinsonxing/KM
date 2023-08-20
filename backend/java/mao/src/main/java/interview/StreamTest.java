package interview;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.TreeMap;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import static java.util.stream.Collectors.*;

public class StreamTest {

	 public static void main(String[] args) {
		 List<String> stringList = new ArrayList<String>();

		 stringList.add("One flew over the cuckoo's nest");
		 stringList.add("To kill a muckingbird");
		 stringList.add("Gone with the wind");

		 Stream<String> stream = stringList.stream();
         //reduce test 1 
//		 Optional<String> reduced = stream.reduce((value, combinedValue) -> {
//		     return combinedValue + " + " + value;
//		 });
//		 System.out.println(reduced.get());
		 
		 //flatMap test
//		 stream.flatMap((value) -> {
//			    String[] split = value.split(" "); 
//			    return (Stream<String>) Arrays.asList(split).stream();
//		 })
//		 .forEach((value) -> System.out.println(value));
		 
		 //reduce test 2
		 List<Integer> intList = new ArrayList<>();
		 intList.add(3);
		 intList.add(4);
		 intList.add(5);
		 Stream<Integer> s2 = intList.stream();
		 Optional<Integer> r2 = s2.reduce((v1,v2)->v1+v2);
		 //System.out.println(r2.get());
		 
		List<String> source = Arrays.asList("hello","List", "Map", "Set", "Tree", "Goup", "Tree");
		Map<Integer,List<String>> res = source.stream().collect(groupingBy(String::length,TreeMap::new,
				collectingAndThen(toList(),list->list.stream().sorted().collect(toList()))));
				//toList()));
		System.out.println("group by result"+res);
		Map<String,Long> res_counting = source.stream().collect(groupingBy(Function.identity(), Collectors.counting()));
				//toList()));
		System.out.println("counting result:"+res_counting);
		
		List<String> strings = List.of("a","bb","cc","ddd","cc");
	    //TreeMap<Integer, List<String>> result = strings.stream().collect(groupingBy(String::length, TreeMap::new, toList()));
	    Map<Integer, List<String>> res_tomap = collectorToMapByStringLength(strings, HashMap::new, ArrayList::new);
	    //System.out.println("toMap result:"+res_tomap);
	    List<String> res_removedup = strings.stream().distinct().collect(Collectors.toList());
	    String res_join = strings.stream().distinct().collect(Collectors.joining(", "));
	    //System.out.println("remove duplication result:"+res_removedup);
	    //System.out.println("join result:"+res_join);
	    
	    Integer[] sourceArray = { 0, 1, 2, 3, 4, 5, 4 };
	    Set<Integer> targetSet = new HashSet<Integer>(Arrays.asList(sourceArray));
	    //System.out.println(targetSet);
	    
	    List<Integer> intList1 = Arrays.asList(1, 2, 3);
	    String result = intList1.stream()
	      .map(n -> Integer.toString(n))
	      .collect(Collectors.joining("-", "{", "}"));
	 
	    //System.out.println(result);
	    
	    //find duplication
	    List<Integer> src_dup = Arrays.asList(1,2,3,2,5,2,3);
	    List<Integer> res_duplication = src_dup.stream()
	    	      .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
	    	      .entrySet()
	    	      .stream()
	    	      .filter(m -> m.getValue() > 1)
	    	      .map(Map.Entry::getKey)
	    	      .distinct()
	    	      .collect(Collectors.toList());
	    System.out.println("duplication:"+res_duplication);
	 }
	 
	 public static Map<Integer, List<String>> collectorToMapByStringLength(List<String> source, 
		        Supplier<Map<Integer, List<String>>> mapSupplier, 
		        Supplier<List<String>> listSupplier){
		 Function<String, Integer> keyMapper = String::length;
		 Function<String, List<String>> valueMapper = (element)->{
			 List collection = listSupplier.get();
			 collection.add(element);
			 return collection;
		 };
		 // there are two elements that have key conflict, so mergeFunction is needed
		 BinaryOperator<List<String>> mergeFunction = (existing, replacement) -> {
			    existing.addAll(replacement);
			    return existing;
			};	
		 return source.stream().collect(Collectors.toMap(keyMapper, valueMapper,mergeFunction, mapSupplier));
	 }
	 
	 public static <T> Map<T,Long> countBySteamToMap(List<T> input){
		 return input.stream().collect(Collectors.toMap(Function.identity(), v->1L, Long::sum));
	 }
	 
	 public static <T, A extends List<T>> Collector<T, A, List<T>> toImmutableList(Supplier<A> supplier) {
	    return Collector.of(supplier, List::add, (left, right) -> {
	        left.addAll(right);
	        return left;
	    }, Collections::unmodifiableList);
	}

}




