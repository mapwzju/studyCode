package com.zju.javastudy.junittest.controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.AsyncContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.*;

/**
 * @author Arthur
 * @Date 2018/1/15
 * @Decription:
 */
@Controller
@EnableAutoConfiguration
public class AsyncController {

    @PostMapping("/asynctest")
    @ResponseBody
    public void home(HttpServletRequest request) {
        AsyncContext asyncCtx = request.startAsync();
        asyncCtx.addListener(new AppAsyncListener());
        asyncCtx.setTimeout(9000);

        ExecutorService pool = new ThreadPoolExecutor(1, 1, 0L,
                TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());

        pool.execute(new AsyncRequestProcessor(asyncCtx));

    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(AsyncController.class, args);
    }
}
