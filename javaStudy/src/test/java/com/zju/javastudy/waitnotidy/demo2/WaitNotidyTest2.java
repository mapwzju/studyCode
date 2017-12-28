package com.zju.javastudy.waitnotidy.demo2;

import com.zju.javastudy.waitnotify.demo2.HungryPeople;
import com.zju.javastudy.waitnotify.demo2.Restaurant;
import org.junit.Test;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Arthur
 * @Date 2017/12/28
 * @Decription: 餐馆取号吃饭的测试例子
 */
public class WaitNotidyTest2 {

    @Test
    public void test() throws InterruptedException {
        ReentrantLock allSeat = new ReentrantLock();//座位
        Condition smallSeat = allSeat.newCondition();//小桌
        Condition bigSeat = allSeat.newCondition();//大桌

        //实例化 餐馆，小桌客人和大桌客人
        Restaurant restaurant = new Restaurant(allSeat,smallSeat,bigSeat);
        HungryPeople hungryPeopleWaitSmall = new HungryPeople(allSeat,smallSeat);
        HungryPeople hungryPeopleWaitBig = new HungryPeople(allSeat,bigSeat);

        //小桌客人线程
        Thread hungryPeopleWaitSmallThread = new Thread(hungryPeopleWaitSmall);
        hungryPeopleWaitSmallThread.setName("smallSeatPeople");

        //大桌客人线程
        Thread hungryPeopleWaitBigThread = new Thread(hungryPeopleWaitBig);
        hungryPeopleWaitBigThread.setName("bigSeatPeople");

        //餐馆线程
        Thread restaurantThread = new Thread(restaurant);

        //开始模拟
        hungryPeopleWaitSmallThread.start();
        hungryPeopleWaitBigThread.start();
        restaurantThread.start();

        //子线程结束再结束main线程
        hungryPeopleWaitSmallThread.join();
        hungryPeopleWaitBigThread.join();
        restaurantThread.join();
    }
}
