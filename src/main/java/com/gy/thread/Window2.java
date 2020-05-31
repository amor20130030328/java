package com.gy.thread;

public class Window2 extends Thread {

    private static  int ticket = 100;


    public void run() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        synchronized (Window2.class) {
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

class SaleTicket {

    public static void main(String[] args) {

        Window2 t1 = new Window2();
        Window2 t2 = new Window2();
        Window2 t3 = new Window2();



        t1.setName("窗口一");
        t2.setName("窗口二");
        t3.setName("窗口三");

        t1.start();
        t2.start();
        t3.start();

    }


}
