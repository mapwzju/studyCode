package com.zju.javastudy.waitnotidy.demo1;

import com.zju.javastudy.waitnotify.demo1.HungryPeople;
import com.zju.javastudy.waitnotify.demo1.Restaurant;
import org.junit.Test;

/**
 * @author Arthur
 * @Date 2017/12/28
 * @Decription: 餐馆取号吃饭的测试例子
 */
public class WaitNotidyTest {

    @Test
    public void test() throws InterruptedException {
        Object seat = new Object();//座位
        Restaurant restaurant = new Restaurant(seat);
        HungryPeople hungryPeople = new HungryPeople(seat);
        new Thread(hungryPeople).start();
        Thread.sleep(2000);
        new Thread(restaurant).start();
    }
}
