package thread;

/**
 * Created by ym on 2014/8/28.
 */
public class MyThread implements Runnable{

    public void run() {

    }

    public static void main(String[] args) {

        MyThread thread = new MyThread();
        Thread thread1 = new Thread(thread);
        thread1.start();
        
    }
}
