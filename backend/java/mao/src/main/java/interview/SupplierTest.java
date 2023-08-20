package interview;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;


public class SupplierTest {
	public static int getVal(Supplier<Integer> supplier) {
		return supplier.get()+1;
	}
	public static int testSupplier() {
		List<Integer> valList = Arrays.asList(1,2,3,4,5);
		int maxVal = getVal(()->{
			int val = Integer.MIN_VALUE;
			for(Integer v : valList) {
				if (v > val) {
					val = v;
				}
			}
			return val;
		});
		return maxVal;
	}
	public static void main(String[] args) {
		int res = testSupplier();
		System.out.println("result is "+res);
	}
}
