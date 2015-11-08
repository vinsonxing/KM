package com.vinson.base;


public class PrimativeWapper {

    //1
   static void go (int x) {
         System. out.println("int" );
   }
    //2
    static void go(long x) {
         System. out.println("long" );
   }
    //3
    static void go(Long x) {
         System. out.println("Long" );
   }
    //4
    static void go(Integer x) {
         System. out.println("Integer" );
   }
    //5
    public static void main(String[] args) {
          int x = 1;
          go(x);
   }
    
    /*基本类型的 会扩大 如果找不到正好 一样的类型。 会找最接近的bigger 类型。

    if run , out put :  int.
    if comment '1' then output :  long
    if comment '1' and '2' then output :  Integer
    if comment '1','2' and '3' then output :  Long.
    if comment '1','2','3' and '4', then complile error.*/

}
