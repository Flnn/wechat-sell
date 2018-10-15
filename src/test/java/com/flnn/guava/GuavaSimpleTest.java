package com.flnn.guava;

import com.google.common.util.concurrent.*;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.concurrent.*;

/**
 * guava 并发测试
 */
public class GuavaSimpleTest {

    public static void main(String[] args) {
        //创建一个线程池用来执行任务线程的回调
        ExecutorService callBackThreads = Executors.newCachedThreadPool();

        ListeningExecutorService listenService = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(10));
        for(int i=0;i<10;i++) {
            ListenableFuture<MultiThreadData> future = listenService.submit(() -> {
                MultiThreadData data = new MultiThreadData();
                data.setThreadName(Thread.currentThread().getName());
                return data;
            });
            Futures.addCallback(future, new SimpleFutureCallBack(), callBackThreads);
        }

        try {
            Thread.sleep(Integer.MAX_VALUE);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static class SimpleFutureCallBack implements FutureCallback<MultiThreadData> {
        @Override
        public void onSuccess(@Nullable MultiThreadData result) {
            System.out.println("执行线程的名字：" + result.getThreadName());
        }

        @Override
        public void onFailure(Throwable t) {
            t.printStackTrace();
        }
    }

}
