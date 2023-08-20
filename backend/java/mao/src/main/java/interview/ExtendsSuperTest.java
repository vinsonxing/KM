package interview;

import java.util.ArrayList;
import java.util.List;

public class ExtendsSuperTest {
	static class A {}
	static class B extends A {}
	static class C extends B {}
	
	public static void main(String[] args) {
		List<? extends B> list1 = new ArrayList<C>();
//		list1.add(new A());
//		list1.add(new B());
		A obj1 = list1.get(0);
		B obj2 = list1.get(1);
		List<? super B> list2 = new ArrayList<A>();
//		list2.add(new A());
		list2.add(new B());
		Object obj3 = list2.get(0);
	}

//	public static <T> List<T> asList1(T... a) { 
//        return new ArrayList<>(a);
//    }
}
