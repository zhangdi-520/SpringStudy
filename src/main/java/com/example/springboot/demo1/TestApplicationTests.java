package com.example.springboot.demo1;

import com.alibaba.fastjson.JSONObject;
import com.example.springboot.Socket.Executor;
import com.example.springboot.Socket.RuleExecutor;
import com.example.springboot.querydsl.entity.MoreBrand;
import com.example.springboot.querydsl.entity.NewBrand;
import com.example.springboot.querydsl.repository.BrandRepository;
import com.example.springboot.querydsl.repository.MoreBrandRepository;
import com.example.springboot.querydsl.repository.NewBrandRepository;
import com.example.springboot.querydsl.service.DslUserService;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.*;
import java.net.URLEncoder;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


@RunWith(SpringRunner.class)
@SpringBootTest
public class TestApplicationTests {

    @Autowired
    BrandRepository brandRepository;

    @Autowired
    NewBrandRepository newBrandRepository;

    @Autowired
    MoreBrandRepository moreBrandRepository;

    @Autowired
    RuleExecutor ruleExecutor;

    @Autowired
    Map<String, Executor> executorMap;

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
//        Optional<Brand> brand = brandRepository.findById(2032L);
//        List<Category> categoryList = brand.get().getCategoryList();
//        for (Category category:categoryList){
//            System.out.println(category.toString());
//        }
//        MoreBrand moreBrand =null;
//        if(brand.isPresent()){
//            Brand brand1 = brand.get();
//            if(brand1 instanceof MoreBrand){
//                moreBrand = (MoreBrand)brand1;
//            }
//        }
//        System.out.println(moreBrand.toString());
//        NewBrand brand = new NewBrand();
//        brand.setName("张小三");
//        brand.setImage("1.jpg");
//        brand.setLetter("z");
//        newBrandRepository.save(brand);
        MoreBrand moreBrand = new MoreBrand();
        moreBrand.setLogo("xiaomi");
        moreBrand.setName("小米手机");
        moreBrand.setSeq("111");
        moreBrand.setSize("12");
        moreBrand.setImage("2.jpg");
        moreBrand.setLetter("Z");
        moreBrand.setId(123L);
        moreBrandRepository.save(moreBrand);
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

    //字节流读取文件
    @Test
    public void testFile() throws IOException {
        File file = new File("C:\\Users\\ZD\\Desktop\\学习笔记.txt");
        long fileSize = file.length();
        if(fileSize>Integer.MAX_VALUE){
            System.out.println("file too big");
            return;
        }
        FileInputStream fi = new FileInputStream(file);
        byte[] buffer = new byte[(int)(fileSize)];
        int offset = 0;
        int numRead = 0;
        while (offset < buffer.length
                && (numRead = fi.read(buffer, offset, buffer.length - offset)) >= 0) {
            offset += numRead;
        }

        if(offset !=buffer.length){
            throw new IOException("Could not completely read file" + file.getName());
        }
        fi.close();
        InputStreamReader isr = new InputStreamReader(new ByteArrayInputStream(buffer));

        BufferedReader br = new BufferedReader(isr);

        String line = "";
        String content = "";
        while ((line=br.readLine())!=null){
            line +="\n";
            content+=line;
        }

        String lines[] = content.split("\n");


    }

    class MyThread implements Runnable{

        private int index;

        public MyThread(int index){
            this.index = index;
        }

        @Override
        public void run() {
            System.out.println("处理任务:"+index);
        }
    }

    /**
     * @Description: newFixedThreadPool使用测试
     * @Param: []
     * @return: void
     * @Author: Mr.Wang
     * @Date: 2020/4/13
     */
    @Test
    public void testPool(){
        long startTime = System.currentTimeMillis();

        ExecutorService es = Executors.newFixedThreadPool(5);

        for(int i=0;i<15;i++){
            MyThread myThread = new MyThread(i);
            es.execute(myThread);
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        es.shutdown();

    }

    @Test
    public void testBasicHeader(){
        Executor executor = executorMap.get("ruleExecutor");
        executor.get();

    }

    @Autowired
    DslUserService dslUserService ;

    @Test
    public void testConsumer(){
        dslUserService.invokeFlow(data->{
            System.out.println(data);
        });
    }

    @Test
    public void xmlToJson() throws FileNotFoundException {
        File file = new File("src/main/resources/test.xml");
        InputStream in = new FileInputStream(file);
        System.out.println(in);
//        String xml = IOUtils.toString(in);
//        JSONObject xmlJSONObj = XML.toJSONObject(xml);
    }

}
