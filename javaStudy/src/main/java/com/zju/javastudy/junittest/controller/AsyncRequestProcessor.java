package com.zju.javastudy.junittest.controller;

import javax.servlet.AsyncContext;
import javax.servlet.ServletResponse;


import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

/**
 * @author Arthur
 * @Date 2018/1/17
 * @Decription:
 */
public class AsyncRequestProcessor implements Runnable {

    private AsyncContext asyncContext;

    public AsyncRequestProcessor(AsyncContext asyncCtx) {
        this.asyncContext = asyncCtx;
    }

    @Override
    public void run() {
        ServletResponse response = asyncContext.getResponse();
        String result = "Hello World";
        OutputStream out = null;
        try {
             out = response.getOutputStream();
             out.write(result.getBytes());

        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if(null != out) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        asyncContext.complete();
    }

    private void longProcessing(int secs) {
        // wait for given time before finishing
        try {
            Thread.sleep(secs);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}