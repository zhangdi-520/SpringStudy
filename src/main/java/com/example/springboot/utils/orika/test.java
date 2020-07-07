package com.example.springboot.utils.orika;

/**
 * @version V1.0
 * @program: Spring
 * @description: TODO
 * @author: Mr.Zhang
 * @create: 2020-07-07 17:13
 **/
public class test {

    public static void main(String[] args) {
        CopyUser user = new CopyUser("zhangsan","123456");
        User copyUser = OrikaMapper.map(user,User.class);
        System.out.println(copyUser.toString());
    }
}
