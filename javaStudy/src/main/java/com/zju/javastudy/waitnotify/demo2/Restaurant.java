package com.zju.javastudy.waitnotify.demo2;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Arthur
 * @Date 2017/12/28
 * @Decription: 餐馆，通知者
 */
public class Restaurant implements Runnable {

    /**
     * 所有座位
     */
    private ReentrantLock allSeat;

    /**
     * 小桌
     */
    private Condition smallSeat;

    /**
     * 大桌
     */
    private Condition bigSeat;

    public Restaurant(ReentrantLock allSeat,Condition smallSeat, Condition bigSeat) {
        this.allSeat = allSeat;
        this.smallSeat = smallSeat;
        this.bigSeat = bigSeat;
    }


    @Override
    public void run() {
        //这里模拟通知大桌
        try {
            allSeat.lock();
            Thread.sleep(2000);
            System.out.println("Restaurant:有大桌空位，通知客人来就餐...");
            bigSeat.signalAll();
            System.out.println("Restaurant:完成大桌通知");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            allSeat.unlock();
        }

        //这里模拟通知小桌
        try {
            Thread.sleep(3000);
            allSeat.lock();
            System.out.println("Restaurant:有小桌空位，通知客人来就餐...");
            smallSeat.signalAll();
            System.out.println("Restaurant:完成小桌通知");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            allSeat.unlock();
        }



        }
}