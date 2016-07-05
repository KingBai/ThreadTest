package com.test.callable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class CallableTest {
    public static void main(String[] args) throws InterruptedException, ExecutionException {

        //FutureTask<String> future = new FutureTask<String>(callable);
        //new Thread(future).start();
        //ExecutorService threadpool = Executors.newSingleThreadExecutor();
        ExecutorService threadPool = Executors.newCachedThreadPool();
        //ExecutorCompletionService<String> executeResult = new ExecutorCompletionService<String>(threadPool);
        List<Future> list = new ArrayList<Future>();
        for (int i = 0; i <5 ; i++) {
            final int id = i;
            Callable<String> callable = new Callable<String>() {
                @Override
                public String call() throws Exception {
                    return "hello word"+id;
                }
            };
            Future<String> future = threadPool.submit(callable);
            list.add(future);
        }




        //Future<String> future = threadpool.submit(callable);

        try {
            Thread.sleep(5000);
            for (int i = 0; i < 5 ; i++) {
                System.out.println(list.get(i).get());
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}
