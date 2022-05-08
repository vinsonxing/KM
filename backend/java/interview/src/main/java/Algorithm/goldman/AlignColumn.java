package Algorithm.goldman;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AlignColumn {
    /**
     * @param a: the csv file a
     * @return: return the processed file
     */
    public List<String> processingFile(List<String> a) {
        // Write your code here
        String line1 = a.get(0);
        String[] words = line1.split(",");
        int[] columeWidth = new int[words.length];

        // calculate the max width of each column
        a.stream().forEach(l -> {
            String[] ws = l.split(",");
            for (int i = 0; i < ws.length; i++) {
                if (ws[i].length() > columeWidth[i]) {
                    columeWidth[i] = ws[i].length();
                }
            }
        });

        return a.stream().map(s -> {
            String[] ws = s.split(",");
            for (int i = 0; i < ws.length; i++) {
                ws[i] = normalizeWord(ws[i], columeWidth[i]);
            }
            return String.join(",", ws);
        }).collect(java.util.stream.Collectors.toList());

    }

    private String normalizeWord(String str, int length) {
        if (null == str || str.length() == 0) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i<length; i++) {
                sb.append(" ");
            }
            return sb.toString();
        }
        if (str.length() == length) return str;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i<length-str.length(); i++) {
            sb.append(" ");
        }
        sb.append(str);
        return sb.toString();

    }
    
    public int findTheRank(int[][] scores, int k) {
        // write your code here
        int[] totalScores = new int[scores.length];
        
        for (int i = 0;i<scores.length;i++) {
            totalScores[i] = sum(scores[i]);
        }
        int[] toSort = Arrays.copyOf(totalScores, totalScores.length);
        Arrays.sort(toSort);
        int j;
        for (j=0;j<totalScores.length;j++) {
            if (totalScores[j] == toSort[k-1]) {
                return j;
            }
        }
        return j;
    }

    private int sum(int[] scores) {
        int ret = 0;
        for (int i : scores) {
            ret +=i;
        }
        return ret;
    }
    
    public static void main(String[] args) {
		AlignColumn ac = new AlignColumn();
		List<String> ss = new ArrayList<>();
		ss.add(",nr,,ncloz");
		ss.add("db,dllau,k,hwb");
		ss.add("x,jo,d,gnbik");
		
		List<String> ret = ac.processingFile(ss);
		ret.forEach(System.out::println);
		
		
	}

}