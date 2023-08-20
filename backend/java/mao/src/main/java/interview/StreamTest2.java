package interview;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamTest2 { 
	public static void main(String[] args) {
		List<String> input = new ArrayList<>(Arrays.asList("abc","abd","defc","abddf")); 
		List<String> res = input.stream().filter(s->s.startsWith("a"))
						.collect(Collectors.groupingBy(String::length,Collectors.toList()))
						.entrySet().stream()
						.sorted(Map.Entry.<Integer,List<String>>comparingByKey().reversed())
						.findFirst().get().getValue();
		System.out.println(res);
		
		String res1 = input.stream().max(Comparator.comparing(String::length)).get();
				//collect(Collectors.groupingBy(Function.identity(),Collectors.reducing(BinaryOperator.maxBy(Comparator.comparing(String::length)))));
		System.out.println(res1);
		
		Stream<String> coursesStream = Stream.of("Java", "Frontend", "Backend", "Fullstack");
		Stream<Integer> coursesStreamLength = coursesStream.map(String::length);
		// we get java.lang.IllegalStateException
		Stream<String> emphasisCourses = coursesStream.map(course -> course + "!");
		
		
		Supplier<Stream<String>> streamSupplier = () -> Stream.of("d2", "a2", "b1", "b3", "c").filter(s -> s.startsWith("a"));
	}
	
	Date findMaxDateOf(List<Event> events) {
	    if (events == null || events.isEmpty()) {
	        return null;
	    }
	    return events.stream()
	      .map(Event::getDate)
	      .max(Date::compareTo)   // pass compareTo to max and min for Stream 
	      .get();
	}
}
class Event {

    Date date;

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
}
