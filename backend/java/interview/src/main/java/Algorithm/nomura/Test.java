package Algorithm.nomura;

/**
 * Write a function solution that, given two integers A and B, returns the number of integers from the range [A..B] (ends are included) which can be expressed as the product of two consecutive integers, that is X^ * (X + 1) for some integer X.
 *
 * test-inp
 *
 * Examples:
 *
 * 1. Given A = 6 and B = 20 the function should return 3. These Integers are: 6=2*3, 12-34 and 20 = 4*5.
 *
 * 2. Given A = 21 and B = 29 , the function should return 0. There are no integers of the form X^ * (X + 1) in this interval
 *
 * Write an efficient algorithm for the following assumptions:
 *
 * A and B are integers within the range [1 1,000,000,000), As B
 */

public class Test {
    public static int solution(int a, int b) {
        int result = 0;
        long start = System.currentTimeMillis();
        for (int i = a; i <= b; i++) {
            if (i % 2 == 1) {
                continue;
            }
            int j = (int)Math.floor(Math.sqrt(i));
            if (j * (j +1) == i) {
                result++;
            }
        }

//        Map<Integer, Integer> cache = new HashMap<>();
//        for (int i = a; i <= b; i++) {
//
//            for (int j = (int)Math.floor(Math.sqrt(i)); j <= i / 2; j++) {
//                if (cache.computeIfAbsent(j, k -> k * (k +1)) == i) {
//                    System.out.println(j + "*" + (j+1) + "=" + i);
//                    result++;
//                    break;
//                }
//            }
//        }

        System.out.println(System.currentTimeMillis() - start);
        return result;
    }

    public static void main(String[] args) {
        System.out.println("Test case 1: " + solution(2, 3) + " (expected output: 3)");
//        System.out.println("Test case 2: " + solution(21, 29) + " (expected output: 0)");
    }
}
