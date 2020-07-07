package com.example.springboot.xml;

import com.mysql.cj.x.protobuf.MysqlxDatatypes;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;

/**
 * @version V1.0
 * @program: Spring
 * @description: 测试JAXB
 * @author: Mr.Zhang
 * @create: 2020-04-21 10:04
 **/
public class JAXBTest {

    public static void main(String[] args) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(Boy.class);
        Marshaller marshaller = context.createMarshaller();
        Unmarshaller unmarshaller = context.createUnmarshaller();
        Boy boy = new Boy();
        marshaller.marshal(boy,System.out);
        System.out.println();
        String xml="<boy><name>David</name></boy>";
        Boy boy2 = (Boy)unmarshaller.unmarshal(new StringReader(xml));
        System.out.println(boy2.name);
    }
}
