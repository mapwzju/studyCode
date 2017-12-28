package com.zju.javastudy.waitnotify.demo1;

/**
 * @author Arthur
 * @Date 2017/12/28
 * @Decription: 餐馆，通知者
 */
public class Restaurant implements Runnable {
    /**
     * 座位，实质就是锁
     */
    private Object seat;

    public Restaurant(Object o) {
        this.seat = o;
    }


    @Override
    public void run() {
        try {
            //模拟等待
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        synchronized (seat) {
            System.out.println("Restaurant:有空位，通知客人来就餐...");
            seat.notify();//通知到号
            System.out.println("Restaurant:完成通知");

        }

    }

}