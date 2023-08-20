package interview;

import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class OptionalTest {
	public static String getMyDefault() {
	    System.out.println("Getting Default Value");
	    return "Default Value";
	}
	public static void main(String[] args) {
		String text = "Text present";
		System.out.println("Using orElseGet:");
	    String defaultText 
	      = Optional.ofNullable(text).orElseGet(OptionalTest::getMyDefault);
	    System.out.println("Using orElse:");
	    defaultText = Optional.ofNullable(text).orElse(getMyDefault());
	    //caller(callee());
	    
	    Optional<String> found = Stream.<Supplier<Optional<String>>>of(
	    	      () -> createOptional("empty"),
	    	      () -> createOptional("hello")
	    	    ) 
	    	      .map(Supplier::get)
	    	      .filter(Optional::isPresent)
	    	      .map(Optional::get)
	    	      .findFirst();
	}
	
	private static Optional<String> getEmpty() {
	    return Optional.empty();
	}

	private static Optional<String> getHello() {
	    return Optional.of("hello");
	}

	private static Optional<String> getBye() {
	    return Optional.of("bye");
	}

	private static Optional<String> createOptional(String input) {
	    if (input == null || "".equals(input) || "empty".equals(input)) {
	        return Optional.empty();
	    }
	    return Optional.of(input);
	}
	
//	public static String callee()
//	{
//		return "ok";
//	}
//	public static void caller(String input) {
//		System.out.println("I got " + input);
//	}
}
