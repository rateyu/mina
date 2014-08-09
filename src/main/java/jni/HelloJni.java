package jni;

/**
 * Created by ym on 9/8/14.
 */
public class HelloJni
{
    static
    {
        System.loadLibrary("HelloJni");
    }

    public native static int get();
    public native static void set(int i);

    public static void main(String[] args)
    {
        HelloJni test = new HelloJni();
        test.set(20);
        System.out.println(test.get());
    }
}
