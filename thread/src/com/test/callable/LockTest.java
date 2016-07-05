package com.test.callable;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by king on 2016/7/4 0004.
 */
public class LockTest{

    public static void main(String[] args) {
        final ReadWrite rw = new ReadWrite();
        ExecutorService threadPool = Executors.newCachedThreadPool();
        Runnable write = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i <205 ; i++) {
                    rw.setDate(new Random().nextInt());
                }
            }
        };
        Runnable read = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i <205 ; i++) {
                    rw.getDate();
                }
            }
        };
        for (int i = 0; i <3; i++) {
            threadPool.execute(write);
        }
        for (int i = 0; i < 3; i++) {
            threadPool.execute(read);
        }

        threadPool.shutdown();
        }

    }
 class ReadWrite{

    private int date = 0;

     private ReadWriteLock rwl = new ReentrantReadWriteLock();

    public void setDate(int date){
        rwl.writeLock().lock();
        this.date = date;
        try {
            Thread.sleep(200);
            System.out.println(Thread.currentThread().getName()+"write--"+date);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            rwl.writeLock().unlock();
        }

    }

    public void getDate(){
        rwl.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName()+"get--"+date);
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            rwl.readLock().unlock();
        }


    }


}