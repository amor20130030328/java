package com.gy.callable;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 *
 *
 * 如何理解实现Callable接口的方式创建多线程比实现Runnable接口创建多线程方式强大
 * 1. call() 可以有返回值
 * 2. call() 可以抛出异常，被外面的操作捕获，获取异常的信息
 * 3. Callable是支持泛型的
 *
 *
 * 创建线程的方式三：实现callable接口，  ---JDK 5.0 新增
 * 1.创建一个实现Callable的实现类
 */
public class NumberThread implements Callable<Integer> {

    //2.实现call方法，将此线程需要执行的操作声明在call中
    @Override
    public Integer call() throws Exception {
        int sum  = 0;
        for (int i = 0; i <=100 ; i++) {
            if (i % 2 == 0){
                System.out.println(i);
                sum += i;
            }
        }
        return sum;
    }
}


class ThreadCall{
    public static void main(String[] args) {
        //3.创建Callable接口实现类的对象
        NumberThread n = new NumberThread();
        //4.将此Callable 接口实现类的对象作为传递到FutureTask构造器中，创建FutureTask的对象
        FutureTask futureTask = new FutureTask(n);
        //5.将FutureTask的对象作为参数传递给Thread类的构造器中，创建Thread对象，并调用start()
        Thread t = new Thread(futureTask);

        try {
            //get() 返回值即为FutureTask构造器参数Callable 实现类重写的call() 的返回值
            t.start();
            Object sum = futureTask.get();
            System.out.println(sum);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


    }
}