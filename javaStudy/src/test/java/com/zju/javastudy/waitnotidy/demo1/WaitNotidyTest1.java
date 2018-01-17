package com.zju.javastudy.waitnotidy.demo1;

import com.zju.javastudy.waitnotify.demo1.HungryPeople;
import com.zju.javastudy.waitnotify.demo1.Restaurant;
import org.junit.Test;


/**
 * @author Arthur
 * @Date 2017/12/28
 * @Decription: 餐馆取号吃饭的测试例子
 */
public class WaitNotidyTest1 {

    @Test
    public void test() throws InterruptedException {
        Object seat = new Object();//座位
        Restaurant restaurant = new Restaurant(seat);
        HungryPeople hungryPeople = new HungryPeople(seat);
        Thread hungryPeopleThread = new Thread(hungryPeople);

        hungryPeopleThread.start();
        Thread restaurantThread = new Thread(restaurant);
        restaurantThread.start();

        //子线程结束后，再结束main线程
        hungryPeopleThread.join();
        restaurantThread.join();
    }
}
