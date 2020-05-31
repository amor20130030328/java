package com.gy.pratice.producerAndConsumer;


import java.util.concurrent.CompletionService;

class Clerk{
    private int productCount = 0;

    public synchronized void produceProduct(){
        if(productCount <20){
            productCount ++;
            System.out.println(Thread.currentThread().getName() + "：开始生产第" + productCount +"个产品");
            notify();
        }else {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized void consumeProduct(){
        if(productCount > 0){
            System.out.println(Thread.currentThread().getName() + "：开始消费第" + productCount +"个产品");
            productCount --;
            notify();
        }else {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}

class Produer extends Thread{

    private Clerk clerk;

    public Produer(Clerk clerk) {
        this.clerk = clerk;
    }

    @Override
    public void run() {
        System.out.println(getName() + ":开始生产产品");
        while (true){
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            clerk.produceProduct();
        }
    }
}

class Consumer extends Thread{

    private Clerk clerk;

    public Consumer(Clerk clerk) {
        this.clerk = clerk;
    }

    @Override
    public void run() {
        System.out.println(getName() + ":开始消费产品");
        while (true){
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            clerk.consumeProduct();
        }
    }
}

public class ProductTest {

    public static void main(String[] args) {

        Clerk c = new Clerk();
        Produer p = new Produer(c);
        Consumer consumer = new Consumer(c);

        Thread t = new Thread(p);
        Thread t2 = new Thread(consumer);

        t.setName("生产者");
        t2.setName("消费者");

        t.start();
        t2.start();


    }
}
