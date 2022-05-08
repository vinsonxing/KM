package Algorithm;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class Test {
	
	public static void main(String[] args) {
//		getAllIntPair();
//		System.out.println(11/ 10);
// 		System.out.println(isPowerOf10(1));
// 		System.out.println(isPowerOf10(2));
// 		System.out.println(isPowerOf10(11));
// 		System.out.println(isPowerOf10(10));
// 		System.out.println(isPowerOf10(99));
// 		System.out.println(isPowerOf10(100));
// 		System.out.println(isPowerOf10(101));
 		
// 		printDupString();
//		reverseLinkedList();
//		mergeArray();
//		countDuplicatedE();
//		fund();
//		fund2();
//		List<String> ss= new ArrayList<>();
//		ss.stream().map(s -> {
//            String[] ws = s.split(",");
//            for (int i = 0; i < ws.length; i++) {
////                ws[i] = normalizeWord(ws[i], columeWidth[i]);
//            }
//            return String.join(",", ws);
//        }).collect(Collectors.toList());
		
//		int[] aa = new int[] {2,-1,2,-1,4,2};//,5,19,46,39,38,41,1,21,33,14,26,11,4,6,27,3,33,9,19,17,38,36,7,22,33,12,22};
//		getUniqueArray(aa);
		
//		System.out.println(smallestLength3(aa, 5));
		
		
//		String[] words = new String[] {"abc","deq","mee","aqq","dkd","ccc"};
//		String pattern = "abc";
//		findAndReplacePattern(words, pattern);
		System.out.println(Integer.MAX_VALUE);
		System.out.println(-1013904242 & 15);
		testThreadLocal();
	}
	
	public static void testThreadLocal() {
		TLocal l = new TLocal();
		l.printCode();
		for (int i = 0; i < 10; i++) {
			l = new TLocal();
			l.printCode();
		}
	}
	
	
	 static class TLocal {
		private final int threadLocalHashCode = nextHashCode();

	    /**
	     * The next hash code to be given out. Updated atomically. Starts at
	     * zero.
	     */
	    private static AtomicInteger nextHashCode =
	        new AtomicInteger();

	    /**
	     * The difference between successively generated hash codes - turns
	     * implicit sequential thread-local IDs into near-optimally spread
	     * multiplicative hash values for power-of-two-sized tables.
	     */
	    private static final int HASH_INCREMENT = 0x61c88647;

	    /**
	     * Returns the next hash code.
	     */
	    private static int nextHashCode() {
	        return nextHashCode.getAndAdd(HASH_INCREMENT);
	    }
	    
	    public TLocal() {
			
		}
	    
	    public void printCode() {
	    	System.out.println(threadLocalHashCode);
	    }
	}
	
	public static List<String> findAndReplacePattern(String[] words, String pattern) {
        // Write your code here.
        int[] p = F(pattern);
        List<String> res = new ArrayList<String>();
        for (String w : words)
            if (Arrays.equals(F(w), p)) res.add(w);
        return res;
    }

    public static int[] F(String w) {
        HashMap<Character, Integer> m = new HashMap<>();
        int n = w.length();
        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            m.putIfAbsent(w.charAt(i), m.size());
            res[i] = m.get(w.charAt(i));
        }
        return res;
    }
	
	public static int smallestLength3(int[] nums, int k) {
		if (nums == null || nums.length == 0) {
            return -1;
        }
		int start = 0;
		int sum = 0;
		int res = Integer.MAX_VALUE;
		for (int i = 0; i < nums.length; i++) {
			sum += nums[i];
			while(sum >= k) {
				res = Math.min(res, i - start + 1);
				sum -= nums[start];
				start ++;
			}
		}
		
	
		
		if (res == Integer.MAX_VALUE) return -1;
		return res;
	}
	
	public String highestGrowth(List<List<String>> stock) {
        // Write your code here
        int maxIdx = 0;
        String maxId = "";
        float max = 0;
        for (int i = 0; i < stock.size(); i++) {
            float diff = getIncrease(stock.get(i).get(1), stock.get(i).get(2));
            if ( diff > max) {
                max = diff;
                maxIdx = i;
                maxId = stock.get(i).get(0);
            }
        }
        return maxId;
    }

    private float getIncrease(String start, String end) {
        return (Float.valueOf(end) - Float.valueOf(start))/Float.valueOf(start);
    }
    
	public static int smallestLength2(int[] nums, int k) {
        // Write your code here
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int low = 0;
        int result = Integer.MAX_VALUE;
        int prefixSum = 0;
        int sum2 = 0;
        for (int i = 0; i < nums.length; i++) {
            prefixSum += nums[i];
            sum2 += nums[i];
            while (prefixSum >= k) {
                result = Math.min(result, i - low + 1);
                prefixSum -= nums[low];
                low++;
            }
        }
        if (sum2 < k) return -1;
        return result;
    }
	
	public static int smallestLength(int[] nums, int k) {
        // Write your code here

        int start = 0;
        int end = 0;
        boolean done = false;
        int maybeSum = Integer.MAX_VALUE;
        Map<Integer, Integer> map = new HashMap<>();
        	
        	while(start < nums.length) {
                end = start;
        		while (end < nums.length) {
        			int ss = sum(nums, start, end);
        			if (ss == k) {
        			System.out.println(ss);
        				maybeSum = ss;
                        Integer v = map.get(maybeSum);
                        if (v == null) {
                            map.put(maybeSum, end - start + 1);
                        } else if (v != null && v > (end - start + 1)) {
        				    map.put(maybeSum, end - start + 1);
                        }
                        System.out.println(String.format("start=%d,end=%d", start, end));
        			}
        			if (ss > k && ss < maybeSum) {
        				maybeSum = ss;
        				Integer vv = map.get(maybeSum);
                        if (vv == null) {
                            map.put(maybeSum, end - start + 1);
                        } else if (vv != null && vv > (end - start + 1)) {
        				    map.put(maybeSum, end - start + 1);
                        }
        			}
        			end++;
        		}
        		start++;
                
        	}
        if (map.size() == 0) return -1; 
        if (map.size() == 1) {
            return map.get(maybeSum);    
        }

        List<Integer> ll = map.keySet().stream().collect(java.util.stream.Collectors.toList());
        ll.sort((a,b) -> a-b);
        
        
        
        return map.get(ll.get(0));
    }

    private static int sum(int[] arr, int start, int end) {
        int ret = 0;
        if (start == 11)
        System.out.println(arr[start]);
        if (end == 15)
            System.out.println(arr[end]);
        for (int i = start; i <= end; i++) {
            ret += arr[i];
        }
        return ret;
    }
	
	// 斐波那数
	private static int f(int k) {
		
		int n1 = 1;
		int n2 = 1;
		int n3 = 0;
		System.out.print(n1 + " ");
		System.out.print(n2 + " ");
		for (int i = 3; i <= k; i++) {
			n3 = n1 + n2;
			System.out.print(n3 + " ");
			n1 = n2;
			n2 = n3;
		}
		
		return n3;
	}
	
	public static int[] getUniqueArray(int[] arr) {
        // write your code here
        int[] ret = new int[arr.length];
        boolean found = false;
        int finalLen = 0;
        for (int i = 0; i < arr.length; i++) {
            
            for (int j = 0; j < finalLen+1; j++) {
                if (ret[j] == arr[i]) {
                    found = true;
                }
            }
            if (!found) {
                ret[finalLen++] = arr[i];
            }
            found = false;
        }
        
        List<Integer> sets = new ArrayList<>();
        
        sets.stream().mapToInt(s -> s).toArray();
        return Arrays.copyOf(ret, finalLen);
    }
	
	
	
	private static int findRightFirm(Map<Integer, Integer> status) {
		// find the least firm
		int leastFirm = 0;
		int leastValue = Integer.MAX_VALUE;
		for (Map.Entry<Integer, Integer> e : status.entrySet()) {
			if ((leastValue > e.getValue()) || (leastValue == e.getValue() && e.getKey() < leastFirm)) {
				leastValue = e.getValue();
				leastFirm = e.getKey(); 
			}
		}
		
		return leastFirm;
	}
	
	private static void fund() {
		int[] funds = new int[] {1,2,1,3,1,1};
//		int a = 1, b = 2, c =1;
		// out: [4,5,4]
		
		// num/count
		Map<Integer, Integer> status = new HashMap<>();
		status.put(1, 1); // a 
		status.put(2, 2); // b
		status.put(3, 1); // c
		
		for (int f : funds) {
			int firmNum = findRightFirm(status);
			status.put(firmNum, status.get(firmNum) + f);
		}
		
		System.out.println(status.values().stream().mapToInt(i->i).toArray());
	}
	
	private static int findRightFirm2(int[] firms) {
		int min = Integer.MAX_VALUE;
		int ret = 0;
		for (int i = 0; i < firms.length; i ++) {
			if (min > firms[i] || (min == firms[i] && ret < i)) {
				min = firms[i];
				ret = i;
			}
		}
		return ret;
	}
	
	private static void fund2() {
		int[] funds = new int[] {1,2,1,3,1,1};
//		int a = 1, b = 2, c =1;
		int[] firms = new int[] {1,2,1};
		// out: [4,5,4]
		
		for (int f : funds) {
			int firmNum = findRightFirm2(firms);
			firms[firmNum] += f;
		}
		
		for (int k = 0; k < firms.length; k ++) {
			System.out.println(firms[k]);
		}
	}
	
	// get all int paris which has plus value 11Ay
	private static void getAllIntPair() {
		int[] arr = new int[10];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = i + 1;
		}
		
		int S = 15;
		
		
		Set<Integer> ss = new HashSet<>();
		for (int j = 0; j < arr.length; j++) {
			ss.add(S - arr[j]);
		}
		
		Set<Integer> sss = new HashSet<>();
		for (int j = 0; j < arr.length; j++) {
			if (ss.contains(arr[j]) && !sss.contains(S - arr[j])) {
				sss.add(arr[j]);
			}
		}
		
		
		
		System.out.println(sss.size());
		
		sss.stream().forEach(s -> System.out.println(String.format("a=%d, b=%d", s, S-s)));
	}
	
	private static boolean isPowerOf10(int v) {
		int r = v / 10;
		int y = v % 10;
		if (r < 1) return false;
		if (r == 1 && y == 0) return true;
		return isPowerOf10(r+y);
	}
	
	public static void printDupString() {
		String s = "aasddaaadjjkwwwwwwwadaaaaa";
		char[] cc = s.toCharArray();
		int max = 0;
		List<Character> maxC = new ArrayList<>();
		// Method 1
		List<Character> cWin = new ArrayList<>();
		
		for (int i = 0; i < cc.length; i ++) {
			if (cWin.indexOf(cc[i]) < 0 ) {
				if (cWin.size() > max) {
					max = cWin.size();
					maxC.clear();
					maxC.addAll(cWin);
				}
				cWin.clear();
			}
			cWin.add(cc[i]);
		}
		
		if (cWin.size() > max) {
			maxC.clear();
			maxC.addAll(cWin);
		}
		System.out.println(maxC);
		
		// Method 2
		char[] win = new char[1];
		int mx = 0;
		char[] mxc = null;
		win[0] = cc[0];
		for (int i = 0; i < cc.length; i ++) {
			if (win[0] != cc[i]) {
				System.out.println(win);
				if (win.length > mx) {
					mx = win.length;
					mxc = Arrays.copyOf(win, win.length);
				}
				win = new char[1];
			}
			
			win[win.length - 1] = cc[i];
			win = Arrays.copyOf(win, win.length + 1);
		}
		if (win.length > mx) {
			mxc = Arrays.copyOf(win, win.length);
		}
		System.out.println(mxc);
	}
	public static class Node<T> {
		Node<T> next;
		T value;
		public Node(T t) {
			value = t;
		}
	}
	private static void reverseLinkedList() {
		Node<Integer> n1 = new Node<>(1);
		Node<Integer> n2 = new Node<>(2);
		Node<Integer> n3 = new Node<>(3);
		Node<Integer> n4 = new Node<>(4);
		Node<Integer> n5 = new Node<>(5);
		
		// n1->n2->n3->n4-n5
		// head -> n1
		// tail -> n5
		n1.next = n2;
		n2.next = n3;
		n3.next = n4;
		n4.next = n5;
		
		// reverse
		Node<Integer> head = n1;
		Node<Integer> tail = n1.next;
		Node<Integer> preHead = null;;
		while (head != null) {
			head.next = preHead;
			preHead = head;
			head = tail;
			if (tail != null) {
				tail = tail.next;
			}
		}
		
		while (preHead != null) {
			System.out.println(preHead.value);
			preHead = preHead.next;
		}
	}
	
	private static void mergeArray() {
		int[] a = new int[] {1,3,5,7,9};
		int[] b = new int[] {2,10,12};
		int[] c = new int[a.length + b.length];
		int i = 0;
		int j = 0;
		int k = 0;
		while (i < a.length && j < b.length) {
			if (a[i] > b[j]) {
				c[k] = b[j];
				k++;
				j++;
			} else {
				c[k] = a[i];
				k++;
				i++;
			}
		}
		
		
		for (int m = i; m < a.length; m++) {
			c[k+m - 1] = a[m];
		}
		
		for (int n = j; n < b.length; n++) {
			c[k+n - 1] = b[n];
		}
		
		for (int aa : c) {
			System.out.println(aa);
		}
		
	}
	
	private static void countDuplicatedE() {
		
		int[] numbers = new int[] {1,2,1,3,4,2};
		Map<Integer, Integer> res = new HashMap<>();
		
		for (int i : numbers) {
			if (res.containsKey(i)) {
				res.put(i, res.get(i) + 1);
			} else {
				res.put(i, 1);
			}
		}
		
		List<Integer> ret = res.entrySet().stream()
		.filter(ee -> ee.getValue() > 1)
		.map(a -> a.getKey())
		.collect(Collectors.toList());
		
		if (ret.size() > 0) {
			ret.forEach(e -> {
				System.out.println(String.format("gt1=%d", e));
			});
		}
	}
}
