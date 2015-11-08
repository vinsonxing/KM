package ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: qlu
 * Date: 14-12-4
 * Time: 上午11:25
 * To change this template use File | Settings | File Templates.
 */
public class test {
  public static void main(String[] args) throws Exception{

    MyArrayList array = new MyArrayList();
    array.add(2);
    array.add(3);
    array.add(4);
    array.add(5);
    array.add(6);
    array.add(7);
    array.add(8);
    array.add(9);
    System.out.println("the 5th element is "+(Integer)array.get(4));
    array.remove(2);
    System.out.println("the 4th element is "+(Integer)array.get(4));
  }
}
