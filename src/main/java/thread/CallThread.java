package thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by myu2 on 2015/5/2.
 */
public class CallThread implements Callable{

    int threadi;

    public CallThread(int i) {
        this.threadi = i;
    }

    public Object call() throws Exception {
//        System.out.println("hello world");
        return threadi;
    }

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newCachedThreadPool();
        List<Future<Object>> list = new ArrayList<Future<Object>>();

        for (int i = 0; i < 10000; i++) {
            list.add(executorService.submit(new CallThread(i)));
        }
//        Future <Object> future = executorService.submit(new CallThread());
        Future<Object> future;
        for (int i = 0; i < list.size(); i++) {
            future =  list.get(i);
            try {
                System.out.println(future.get().toString());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }


    }
}
