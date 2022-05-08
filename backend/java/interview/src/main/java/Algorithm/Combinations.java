package Algorithm;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 循环调用递归程序设计
 比排列程序难度大，在循环之中定义退出，循环次数是变量
 */
public class Combinations {
  private StringBuilder out = new StringBuilder();
  private final String in;
  public Combinations( final String str ){ in = str; }
  public void combine() { combine( 0 ); }
  private void combine(int start ) {
    for( int i = start; i < in.length(); ++i ) {
      out.append( in.charAt(i) );
      System.out.println( out );
      if ( i < in.length() )
        combine( i + 1);
      out.setLength( out.length() - 1 );
    }
  }

  public static void main(String[] args){
//    Combinations c = new Combinations("abcd");
//    c.combine();
    
//    MyCom my = new MyCom("abcd");
//    my.combine(0);
//	  System.out.println(smallestLengthII(new int[1], 3));
//	  System.out.println(combinationCount(4,3));
//	  System.out.println((int)'a');
	  
	  String[] words = new String[] {"abc","deq","mee","aqq","dkd","ccc"};
	  String pattern = "abb";
	  for (String w : words) {
          if (match(w, pattern)) {
              System.out.println(w);
          }
      }
	  
//	  System.out.println(match("dkd", "cdd"));
  }
  
//  ["abc","deq","mee","aqq","dkd","ccc"]
//		  "abb"
  private static boolean match(String str, String pattern) {
      // abb, cdd
      if (pattern.length() == 1) return true;
      
      char[] s = str.toCharArray();
      char[] p = pattern.toCharArray();
      char[][] map = new char[str.length()][2];
      
      for (int i = 0; i < s.length; i++) {
    	  map[i][0] = s[i];
    	  map[i][1] = p[i];
      }
      
      for (int i = 0; i < s.length; i++) {
    	  char pp = map[i][0];
    	  char ss = map[i][1];
    	  for (int j = i + 1; j < s.length; j ++) {
    		  if (map[j][0] == pp && map[j][1] != ss
    				  || map[j][0] != pp && map[j][1] == ss) {
    			  return false;
    		  }
    	  }
      } 
      return true;
  }
  
  public static int smallestLengthII(int[] nums, int k) {
      // Write your code here
      // calculate sub arr count
      int count = 0;
      for (int i = 0; i < k; i++) {
          count += combinationCount(k, i);
      }
      return count;
  }
  private static int combinationCount(int k, int i) {
      // CKi
      // i*(i-1)*.../k*(k-1)...
      int sun = 1;
      int mom = 1;
      for (int j = i; j > 0; j--) {
          mom *= j;
      }
      for (int m = k; m > (k-i); m--) {
          sun *= m;
      }
      
      return sun/mom;
      
  }
  
  
  
  // C41 + C42 + C43 + C44, C41 = 4, C42=4*3/2=6, C43=4*3*2/3*2*1=4 
  
  private static class MyCom{
	  StringBuilder sb = new StringBuilder();
	  final String in; 
	  public MyCom(String in) {
		  this.in = in;
	  }
	  
	  public void combine(int start) {
		  for (int i = start; i < in.length(); i++) {
			  sb.append(in.charAt(i));
			  System.out.println(sb);
			  if (i < in.length()) {
				  combine(i+1);
			  }
			  sb.setLength(sb.length() - 1);
		  }
	  }
  }
}
