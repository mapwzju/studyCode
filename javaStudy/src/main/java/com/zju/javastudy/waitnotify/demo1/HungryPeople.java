package com.zju.javastudy.waitnotify.demo1;

/**
 * @author Arthur
 * @Date 2017/12/28
 * @Decription: 饿着的人，去餐馆取号，等待者
 */
public class HungryPeople implements Runnable {
    private Object seat;

    public HungryPeople(Object seat) {
        this.seat = seat;
    }


    @Override
    public void run() {
        synchronized (seat) {
            try {
                System.out.println("HungryPeople：去餐馆吃饭，先取号...");
                seat.wait();//等待叫号
                System.out.println("HungryPeople：到号，有位置了！可以吃饭了！");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
