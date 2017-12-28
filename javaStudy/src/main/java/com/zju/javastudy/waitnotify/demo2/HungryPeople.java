package com.zju.javastudy.waitnotify.demo2;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Arthur
 * @Date 2017/12/28
 * @Decription: 饿着的人，去餐馆取号，等待者
 */
public class HungryPeople implements Runnable {
    /**
     * 所有座位
     */
    private ReentrantLock allSeat;

    /**
     * 等待的桌（大桌/小桌）
     */
    private Condition seat;
    

    public HungryPeople(ReentrantLock allSeat,Condition seat) {
        this.allSeat = allSeat;
        this.seat = seat;
    }


    @Override
    public void run() {
        allSeat.lock();
        try {
            seat.await();
            System.out.println(Thread.currentThread().getName()+":等到位置");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            allSeat.unlock();
        }

    }
}
