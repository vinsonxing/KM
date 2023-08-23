import java.util.*;
import java.lang.Math;
public class Main {
    public static void main(String[] args) {

//        int[] res = orderPizza(new int[] {-11, -2, 19, 37, 64 ,-18}, 3);
        int[] res = orderPizza(new int[] {35, -42, 23, -56, -84, 92, 39}, 4);
        System.out.println(res);
        for (int i =0; i < res.length; i ++) {
            System.out.println(res[i]);
        }
    }

    private static int[] orderPizza(int[] orderPlaced, int size) {
        int[] answer = new int[10];
        int n = orderPlaced.length;
        int index = 0;
        for (int i = 0; i < n - size + 1; i++) {
            for (int j=i; j < i + size ; j ++) {
                if (orderPlaced[j] < 0) {
                    answer[index++] = orderPlaced[j];
                    break;
                } else {
                    if (j == i + size - 1) {
                        answer[index++] = 0;
                    }
                }
            }
        }
        return answer;
    }
}