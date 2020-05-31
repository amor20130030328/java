package com.gy.pratice;


class Account {
    private  double balance;

    public Account(double balance) {
        this.balance = balance;
    }

    public synchronized void  deposit(double amt) {
        if (amt > 0){
            balance += amt;

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + ":存钱成功. 余额为:" + balance);
        }
    }
}


class Cosumer extends Thread{

    private Account acc ;

    public Cosumer(Account acc) {
        this.acc = acc;
    }


    @Override
    public void run() {
        for(int i = 0 ; i< 3 ;i++){
            acc.deposit(1000);
        }
    }
}



public class AccountTest {


    public static void main(String[] args) {

        Account acc = new Account(0);
        Cosumer c = new Cosumer(acc);
        Cosumer c2 = new Cosumer(acc);


        Thread t1 = new Thread(c);
        Thread t2 = new Thread(c2);

        t1.start();
        t2.start();


    }

}
