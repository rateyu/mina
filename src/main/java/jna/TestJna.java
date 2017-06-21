//package jna;
//
//import com.sun.jna.Library;
//import com.sun.jna.Native;
//
//public class TestJna {
//    public interface LgetLib extends Library {
//        // 调用linux下面的so文件,注意，这里只要写test就可以了，不要写libtest，也不要加后缀
//        // LgetLib INSTANCE = (LgetLib) Native.loadLibrary("testShareLib",LgetLib.class);
//        //
//        LgetLib INSTANCE = (LgetLib) Native.loadLibrary("testStaticLib",LgetLib.class);
//        int SampleAddInt(int a,int b);
//    }
//
//    public int SampleAddInt(int a,int b){
//        return LgetLib.INSTANCE.SampleAddInt(a,b);
//    }
//
//    public static void main(String[] args) {
//        TestJna ts = new TestJna();
//        int c = ts.SampleAddInt(10,20);
//        System.out.println("10+20="+c);
//    }
//}
