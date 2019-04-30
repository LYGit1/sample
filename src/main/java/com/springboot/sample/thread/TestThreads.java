package com.springboot.sample.thread;

public class TestThreads {
    private static final long COUNT = 100001;

    public static void main(String[] args) throws InterruptedException{
        concurrency();
        serial();
    }

    private static void serial() {
        long start = System.currentTimeMillis();
        int a = 0;
        for(long i = 0; i < COUNT ; i++ ){
            a += 5;
        }
        int b = 0;
        for(long i = 0 ; i < COUNT ; i++ ){
            b --;
        }
        long time = System.currentTimeMillis() - start;
        System.out.println("serial ="+time+",b="+b);
    }

    private static void concurrency() throws InterruptedException{
        long start = System.currentTimeMillis();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                int a = 0;
                for(long i = 0; i < COUNT ; i++ ){
                    a += 5;
                }
            }
        });
        thread.start();
        int b = 0;
        for(long i = 0 ; i < COUNT ; i++ ){
            b --;
        }
        long time = System.currentTimeMillis() - start;
        thread.join();
        System.out.println("concurrency ="+time+",b="+b);
    }
}
