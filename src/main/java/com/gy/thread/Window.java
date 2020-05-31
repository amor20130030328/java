package com.gy.thread;


/**
 * 例子：创建三个窗口卖票，总票数为100张，使用实现Runnable接口的方式
 * 存在线程的安全问题，待解决
 */
public class Window implements Runnable {

    private int ticket = 100;


    public void run() {


        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        synchronized (this) {
            while(true){
                if(ticket > 0){
                    System.out.println(Thread.currentThread().getName() + "卖票，票号为:" + ticket);
                    ticket --;
                }else{
                    break;
                }
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


