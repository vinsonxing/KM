package interview;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AnagramTest {
	public static void main(String[] args) throws IOException {
		List<String> src = new ArrayList<>(Arrays.asList("test","tset","apple","alepp","appl"));
//		Map<String, Set<String>> groups = new HashMap<>();
//		Iterator<String> i = src.iterator();
//		while (i.hasNext()) {
//			String word = (String)i.next();
//			groups.computeIfAbsent(alphabetize(word),(unused) -> new TreeSet<>()).add(word);
//		}
//		for (Set<String> group : groups.values())
//			System.out.println(group.size() + ": " + group);
		// stream approach
		Stream<String> stream = src.stream();	
		stream.collect(Collectors.groupingBy(word -> alphabetize(word)))
			  .values()
			  .stream()
		      .forEach(System.out::println);
	}
		private static String alphabetize(String s) {
			char[] a = s.toCharArray();
			Arrays.sort(a);
			return new String(a);
		}
		
}
