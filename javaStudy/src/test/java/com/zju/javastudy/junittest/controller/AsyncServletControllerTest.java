package com.zju.javastudy.junittest.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.Java6Assertions.assertThat;

/**
 * @author Arthur
 * @Date 2018/1/17
 * @Decription:
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,classes= AsyncController.class)
@EnableAutoConfiguration
public class AsyncServletControllerTest {

    @Value("${local.server.port}")
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void asyncServletControllerTest(){
        String url = "http://127.0.0.1:"+port+"/asynctest";
        String body = restTemplate.postForObject(url, null,String.class);
        assertThat(body).isEqualTo("Hello World");
    }
}
