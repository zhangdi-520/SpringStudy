package com.example.springboot.demo1;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.assertj.core.util.DateUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestApplicationTests {

    @Autowired
    ToUse toUse;

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Autowired
    Jobs jobs;

    @Test
    public void test(){
        toUse.use();
    }

    @Test
    public void testLock(){
//        BoundValueOperations<String, String> boundValueOperations = stringRedisTemplate.boundValueOps("lalalahahahha");
//        boundValueOperations.set("zhang wu ji ");
//        stringRedisTemplate.delete("aaa");

    }

    //调用其他项目http请求
    @Test
    public void doPostTestOne(){
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        StringBuffer params = new StringBuffer();

        try{
            params.append("name="+ URLEncoder.encode("&","utf-8"));
            params.append("&");
            params.append("age=24");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }


        HttpPost httpPost = new HttpPost("http://localhost:18080/api/doPostControllerOne" +"?"+params);

        httpPost.setHeader("Content-Type","application/json;charset=utf8");

        CloseableHttpResponse response = null;

        try{
            response = httpClient.execute(httpPost);

            HttpEntity responseEntity = response.getEntity();
            System.out.println("响应状态为:"+response.getStatusLine());
            if(responseEntity!=null){
                System.out.println("响应内容长度为:"+ responseEntity.getContentLength());
                System.out.println("响应内容为:"+ EntityUtils.toString(responseEntity));
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try{
                if(httpClient!=null){
                    httpClient.close();
                }
                if (response!=null){
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }


}
