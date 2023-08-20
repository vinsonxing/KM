package interview;

import java.util.function.Supplier;

public class AnonymousTest {
	public static void main(String[] args) {
		AbstractFruit ab = AbstractFruit.newInstance();
		ab.setName("apple");
		ab.setCount(100);
	}
	
	//private int start = 0;

//    Supplier<Integer> incrementer(int start) {
//        return () -> start++;
//    }
	
	abstract static class AbstractFruit{
		private String name;
		private int count;
		
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		
		public int getCount() {
			return count;
		}
		public void setCount(int count) {
			this.count = count;
		}
		public static AbstractFruit newInstance() {
			return new AbstractFruit() {};
		}
	}

}
