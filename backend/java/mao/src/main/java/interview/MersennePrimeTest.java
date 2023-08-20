package interview;

import static java.math.BigInteger.*;
import java.math.BigInteger;
import java.util.stream.Stream;

public class MersennePrimeTest {
	public static void main(String[] args) {
		Stream<BigInteger> stream = Stream.iterate(TWO, BigInteger::nextProbablePrime);
		stream.map(p->TWO.pow(p.intValueExact()).subtract(ONE))
			.filter(mersenne -> mersenne.isProbablePrime(50))
			.limit(20)
			.forEach(System.out::println);
	}
}
