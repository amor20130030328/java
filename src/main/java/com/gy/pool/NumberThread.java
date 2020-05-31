package com.gy.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NumberThread   implements  Runnable{

    @Override
    public void run() {

        for(int i= 0;i<=100 ;i++){
            if(i % 2 == 0){
                System.out.println(Thread.currentThread().getName()+ ":" + i);
            }
        }
    }
}

class NumberThread2 implements  Runnable{

    @Override
    public void run() {

        for(int i= 0;i<=100 ;i++){
            if(i % 2 != 0){
                System.out.println(Thread.currentThread().getName()+ ":" + i);
            }
        }
    }
}

/**
 * 创建线程的方式四：使用线程池
 * 好处：
 * 1.提高响应速度（减少了创建新线程的时间）
 * 2.降低资源消耗（重复利用线程池中线程，不需要每次都创建）
 * 3.便于管理
 *  corePoolSize：线程数
 *  maximumPoolSize : 最大线程数
 *  keepAliveTime : 线程没有任务时最多保持多长时间后会终止
 *
 *
 */
class ThreadPool{

    public static void main(String[] args) {
        //1.提供指定线程数量的线程池
        NumberThread n = new NumberThread();
        NumberThread2 n2 = new NumberThread2();
        //2.执行指定的线程的操作。需要提供实现Runnable接口或Callable接口实现类的对象


        ExecutorService service = Executors.newFixedThreadPool(10);
        service.execute(n);   //适合使用于 Runnable
        service.execute(n2);   //适合使用于 Runnable
        //service.submit();    //适合使用于Callable
        //关闭连接池
        service.shutdown();

    }

}