package interview;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListAPITest {
	public static void main(String[] args) {
		/*
		 * // case I: asList
		 * 
		 * int[] ints = {1,2,3,4,5}; List list = Arrays.asList(ints);
		 * System.out.println("list size：" + list.size());
		 * System.out.println("list 的类型:" + list.get(0).getClass());
		 * System.out.println("list.get(0) == ints：" + list.get(0).equals(ints));
		 * 
		 * 
		 * // case II: subList List list1 = new ArrayList(); list1.add(1); list1.add(2);
		 * //通过构造函数新建一个包含list1的列表 list2 List<Integer> list2 = new
		 * ArrayList<Integer>(list1);
		 * 
		 * //通过subList生成一个与list1一样的列表 list3 List<Integer> list3 = list1.subList(0,
		 * list1.size());
		 * 
		 * //修改list3 list3.add(3);
		 * 
		 * System.out.println("list1 == list2：" + list1.equals(list2));
		 * System.out.println("list1 == list3：" + list1.equals(list3));
		 */
        
        ArrayList<int[]> rr = new ArrayList<>();
        int[] a = {9,8,7,2,3,4,1,0};
        rr.add(a);
        int[][] p = rr.toArray(new int[rr.size()][]);
        System.out.println(p);
         
        List<String> list = Arrays.asList("a","b","c");
        List<String> copy = new ArrayList<>(list);
        System.out.println("before:" + list.toString());
        System.out.println("after:" + copy.toString());
        list.set(1, "bb");
        System.out.println("before:" + list);
        System.out.println("after:" + copy);
        
        List<Sub> list2 = Arrays.asList(new Sub(1), new Sub(2));
        List<Sub> copy2 = new ArrayList<>(list2);
        System.out.println("before:" + list2.toString());
        System.out.println("after:" + copy2.toString());
        list2.get(0).setA(3);
        System.out.println("before:" + list2.toString());
        System.out.println("after:" + copy2.toString());
        
        List<String> input = Arrays.asList("aa","bb","cc","aa");
        Map<String, Long> res = new HashMap<String, Long>();
        input.forEach(e->res.compute(e, (k,v) -> v==null ? 1L: v + 1L));
        System.out.println(res);
	}
}
class Sub{
	int a;

	public Sub(int a) {
		super();
		this.a = a;
	}

	public int getA() {
		return a;
	}

	public void setA(int a) {
		this.a = a;
	}
	public String toString() { 
		return "a=" + a;
	}
	
}
