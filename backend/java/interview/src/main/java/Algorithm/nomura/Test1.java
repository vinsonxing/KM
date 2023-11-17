package Algorithm.nomura;
public class Test1 {
    public static int[] solution(String[] s) {
        int lenS = s.length;
        int[] ret = new int[3];
        for (int i = 0; i < lenS; ++i) {
            String x = s[i];
            int lenX = x.length();
            for (int j = i + 1; j < lenS; ++j) {
                String y = s[j];
                int lenY = y.length();
                for (int k = 0; k < Math.max(lenX, lenY); ++k) {
                    if (k < lenX && k < lenY && x.charAt(k) == y.charAt(k)) {
                        ret[0] = i;
                        ret[1] = j;
                        ret[2] = k;
                        break;
                    }
                }
            }
        }
        return ret;
    }


    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        int n = scanner.nextInt();
//        String[] v = new String[n];
//        for (int i = 0; i < n; ++i) {
//            String x = scanner.next();
//            v[i] = x;
//        }
        String[] v = null;
        int[] w = solution(v);
        System.out.print("[");
        for (int i = 0; i < 2; ++i) {
            System.out.print(w[i] + ",");
        }
        System.out.print(w[2] + "]");
    }
}
