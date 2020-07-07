package com.example.springboot.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @version V1.0
 * @program: Spring
 * @description: xml
 * @author: Mr.Zhang
 * @create: 2020-04-21 10:02
 **/
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Boy {

    String name = "CY";
}
