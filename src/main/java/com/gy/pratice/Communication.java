package com.gy.pratice;

/**
 * 线程通信的例子：使用两个线程打印1-100 。线程1，线程2 交替打印
 * wait() : 一旦执行此方法，当前线程就进入阻塞状态，并释放同步监视器
 * notify() : 一旦执行此方法，就会唤醒被wait() 的一个线程，如果有多个线程被wait，就唤醒优先级高的那个
 * notifyAll() : 一旦执行此方法，就会唤醒所有被wait 的线程
 *
 * 说明：
 * 1.wait（） notify() ,notifyAll() 三个方法必须使用在同步代码块或同步方法中。
 * 2.wait（） notify() ,notifyAll() 三个方法的调用者必须是同步代码块或同步方法中的同步监视器
 *  否则，会出现IllegalMonitorStateException异常
 * 这三个方法是声明在Object类中
 *
 * 面试题：
 *   sleep 和 wait 的异同
 * 1.相同点：一旦执行方法，都可以使得当前线程进入阻塞状态
 * 2.不同点：1）两个方法声明的位置不同：Thread类中声明sleep() 而Object类中声明wait()
 *          2) 调用的要求不同：sleep 可以在任何需要的场景下调用，wait() 必须使用在
 *          同步代码块和同步方法中
 *
 *          3)关于是否释放同步监视器：如果两个方法都使用在同步代码块，或同步方法中sleep() 不会
 *          释放锁，wait会释放锁
 *
 *
 */
class Number implements Runnable{

    private int num = 0 ;
    private Class c = Number.class;

    @Override
    public void run() {

        while (true){
            synchronized (c) {
                c.notify();
                if(num <= 100){
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    System.out.println(Thread.currentThread().getName() + " : " + num);
                    num ++;

                    try {
                        //使得调用如下wait() 方法的线程进入阻塞状态
                        c.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }else {
                    break;
                }
            }
        }

    }
}


public class Communication {

    public static void main(String[] args) {

        Number c = new Number();

        Thread t1 = new Thread(c);
        Thread t2 = new Thread(c);
        t1.setName("甲");
        t2.setName("乙");

        t1.start();
        t2.start();

    }

}



