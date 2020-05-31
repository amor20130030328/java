package com.gy.lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 解决线程安全问题的方式三 ： Lock 锁  --- JDK5.0 新增
 * 相同：二者都可以解决线程安全问题
 * 不同：synchronized 机制在执行完相应的同步代码以后，自动的释放同步监视器
 *     lock需要手动的启动同步（lock()） ，同时结束同步也需要手动的实现（unlock）
 *
 * 2.优先使用顺序
 * Lock -> 同步代码块 (已经进入了方法体，分配了相应资源) -> 同步方法 （在方法体之外）
 *
 *
 *
 *
 */
public class Window  implements Runnable{

    private int ticket = 100;
    private ReentrantLock lock = new ReentrantLock();

    public void run() {

        while(true){
            try {
                lock.lock();
                if(ticket > 0){
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + "卖票，票号为:" + ticket);
                    ticket --;
                }else{
                    break;
                }
            } finally {
                lock.unlock();
            }
        }
    }
}

class WindowTest{

    public static void main(String[] args) {

        Window w = new Window();
        Thread t1 = new Thread(w);
        Thread t2 = new Thread(w);
        Thread t3 = new Thread(w);
        t1.setName("窗口一");
        t2.setName("窗口二");
        t3.setName("窗口三");
        t1.start();
        t2.start();
        t3.start();

    }


}