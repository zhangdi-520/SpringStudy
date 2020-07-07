package com.example.springboot.utils;

import com.alibaba.fastjson.JSONException;
import com.google.common.base.Charsets;
import com.google.common.io.ByteSource;
import com.google.common.io.ByteStreams;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.core.io.ClassPathResource;

import java.io.*;
import java.net.InetAddress;


/**
 * @version V1.0
 * @program: Spring
 * @description: TODO
 * @author: Mr.Zhang
 * @create: 2020-07-07 09:19
 **/
public class XmlToJson {

//    public static JSONObject xml2jsonString() throws JSONException, IOException, org.json.JSONException {
//        File file = new File("src/main/resources/test.xml");
//        InputStream in = new FileInputStream(file);
//        StringBuilder sb = new StringBuilder();
//        String line;
//
//        BufferedReader br = new BufferedReader(new InputStreamReader(in));
//        while ((line = br.readLine()) != null) {
//            sb.append(line);
//        }
//
//        String str = sb.toString();
//        JSONObject xmlJSONObj = XML.toJSONObject(str);
//        System.out.println(xmlJSONObj);
//
//        return xmlJSONObj;

//        JSONObject xmlJSONObj = XML.toJSONObject(xml);
//        return xmlJSONObj.toString();
//    }

    public static void main(String[] args) throws IOException, org.json.JSONException, DocumentException {
//        xml2jsonString();
        String path = "test".concat(".xml");
        ClassPathResource resource = new ClassPathResource(path);
        ByteSource byteSource;
        try(InputStream is = resource.getInputStream()){
            byteSource = ByteSource.wrap(ByteStreams.toByteArray(is));
        }
//        System.out.println(byteSource.asCharSource(Charsets.UTF_8).read());
        String response = byteSource.asCharSource(Charsets.UTF_8).read();
        SAXReader reader = new SAXReader();
        Document document = reader.read(new ByteArrayInputStream(response.getBytes(Charsets.UTF_8)));
        System.out.println(document.getRootElement().element("PRH").asXML());





    }
}
