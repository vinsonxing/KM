package interview;

import java.io.File;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamTest4 {
	public static void main(String[] args) {
		Stream<Integer> data = IntStream.range(0, 34).boxed();
		Collection<List<Integer>> result = data.collect(Collectors.groupingBy(it -> it / 10))
			      .values();
		System.out.println("done");
		
		List<String> input = List.of("abc","def","abcde","abcccd","dddddddddd");
		int res = input.stream().filter(s->s.startsWith("a")).max(Comparator.comparing(String::length)).get().length();
		System.out.println(res);
//		final Scanner scanner = new Scanner(new File("testRead.txt"));
//		PrintWriter writer = new PrintWriter(new File("testWrite.txt"));
//		try (scanner;writer) { 
//		    // omitted
//		}
		
	}
}
