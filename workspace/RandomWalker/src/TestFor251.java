/**
 * Created with IntelliJ IDEA.
 * User: kurmasz
 * Date: 2/19/13
 * Time: 2:21 PM
 * To change this template use File | Settings | File Templates.
 */
public class TestFor251 {

    public static int methodA() {
        int a = 7;
        int b = 8;
        int c = methodA1();
        return a + b + c;
    }

    public static int methodB() {
        int q = 34;
        return q + 6;
    }

    public static int methodA1() {
        int c = methodA1a();
        int d = methodA1b();
        return c + d;
    }

    public static int methodA1a() {
        int e = 8;
        int f = 9;
        return e * f;
    }

    public static int methodA1b() {
        int e = 33;
        int f = 3;
        return e / f;
    }


    public static void main(String[] args) {
        String name = "CS 251";
        methodA();
        methodB();
    }

    public static int bound(int i) {
        return 0;
    }

    public static void constrain(int[] array, int size) {
        for (int i = 0; i < size; i++) {
            array[i] = bound(array[i]);
        }
    }

    public long fib(int n) {
        if (n <= 1) {
            return 1;
        } else {
            return fib(n - 1) + fib(n - 2);
        }
    }


}
