package interview;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiFunction;

import org.junit.Test;

import doublecolon.Computer;
import doublecolon.ComputerUtils;
import doublecolon.MacbookPro;
import function.TriFunction;

public class DoubleColonTest {
	 @Test
	 public void testConstructorReference() {
		 Computer c1 = new Computer(2015, "white");
	     Computer c2 = new Computer(2009, "black");
	     Computer c3 = new Computer(2014, "black");
	     BiFunction<Integer, String, Computer> cFunction = Computer::new;
	     Computer c4 = cFunction.apply(2013, "white");
	     Computer c5 = cFunction.apply(2010, "black");
	     Computer c6 = cFunction.apply(2008, "black");
	     List<Computer> inventory = Arrays.asList(c1,c2,c3,c4,c5,c6);
	     List<Computer> blackComputer = ComputerUtils.filter(inventory, ComputerUtils.blackPredicate);
	     List<Computer> after2010Computer = ComputerUtils.filter(inventory, ComputerUtils.after2010Predicate);
	     List<Computer> before2011Computer = ComputerUtils.filter(inventory, c -> c.getAge() < 2011);
	     inventory.sort(Comparator.comparing(Computer::getAge));
	 }
	 @Test
	 public void testStaticMethodReference() {
		 Computer c1 = new Computer(2015, "white", 35);
	     Computer c2 = new Computer(2009, "black", 65);
	     TriFunction<Integer, String, Integer, Computer> c6Function = Computer::new;
	     Computer c3 = c6Function.apply(2008, "black", 90);
	     List<Computer> inventory = Arrays.asList(c1,c2,c3);
	     inventory.forEach(ComputerUtils::repair);
	 }
	 @Test
	 public void testInstanceMethodArbitraryObjectParticularType() {
		 Computer c1 = new Computer(2015, "white", 35);
         Computer c2 = new MacbookPro(2009, "black", 65);
         List<Computer> inventory = Arrays.asList(c1, c2);
         inventory.forEach(Computer::turnOnPc);
	 }
	 @Test
	 public void testSuperMethodReference() {
		 final TriFunction<Integer, String, Integer, MacbookPro> integerStringIntegerObjectTriFunction = MacbookPro::new;
		 final MacbookPro macbookPro = integerStringIntegerObjectTriFunction.apply(2010, "black", 100);
	     Double initialValue = 999.99;
	     final Double actualValue = macbookPro.calculateValue(initialValue);
	 }

}
