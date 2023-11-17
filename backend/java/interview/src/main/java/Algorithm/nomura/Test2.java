package Algorithm.nomura;
public class Test2 {
    public int[] solution(String R, int[] V) {
        int[] ret = new int[2];
        int bankA = 0, bankB = 0, i = 0;
        int len = R.length();

         do {
            if (R.charAt(i) == 'A') {
                bankA -= V[i];
                bankB += V[i];
                ret[1] = Math.max(ret[1], bankB);
            } else {
                bankA += V[i];
                bankB -= V[i];
                ret[0] = Math.max(ret[0], bankA);
            }
        } while (++i < len);
        return ret;
    }

    public static void main(String[] args) {
        Test1 t = new Test1();
        String R = "BAABA";
//        List<Integer> V = new ArrayList<>();
        int[] V = new int[] {0, 0, 5, 0, 0};
//        V.add(2);
//        V.add(4);
//        V.add(1);
//        V.add(1);
//        V.add(2);

        int[] ans = t.solution(R, V);

        System.out.println("\nTest case 1:");
        System.out.print("R = " + R + ", V = ");
        for (int i = 0; i < V.length; i++) {
            System.out.print(V[i] + " ");
        }
        System.out.println("\nresult = " + ans[0] + " " + ans[1]);

//        String R2 = "ABAB";
////        List<Integer> V2 = new ArrayList<>();
//        int[] V2 = new int[]{10, 5, 10, 15};
////        V2.add(10);
////        V2.add(5);
////        V2.add(10);
////        V2.add(15);
//        int[] ans2 = t.solution(R2, V2);
//        System.out.println("\nTest case 2:");
//        System.out.print("R = " + R2 + ", V = ");
//        for (int i = 0; i < V2.length; i++) {
//            System.out.print(V2[i] + " ");
//        }
//        System.out.println("\nresult = " + ans2[0] + " " + ans2[1]);
//
//        String R3 = "B";
////        List<Integer> V3 = new ArrayList<>();
////        V3.add(100);
//        int[] V3 = new int[] {100};
//        int[] ans3 = t.solution(R3, V3);
//        System.out.println("\nTest case 3:");
//        System.out.print("R = " + R3 + ", V = ");
//        for (int i = 0; i < V3.length; i++) {
//            System.out.print(V3[i] + " ");
//        }
//        System.out.println("\nresult = " + ans3[0] + " " + ans3[1]);
    }
}


