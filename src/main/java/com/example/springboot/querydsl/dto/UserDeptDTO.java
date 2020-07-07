package com.example.springboot.querydsl.dto;

import lombok.Builder;
import lombok.Data;

/**
 * @version V1.0
 * @program: Spring
 * @description: TODO
 * @author: Mr.Zhang
 * @create: 2020-04-20 11:16
 **/
@Data
@Builder
public class UserDeptDTO {
    //用户基础信息
    private String username;    //用户名
    private String nickname;    //昵称
    private String birthday;    //用户生日
    //用户的部门信息
    private String deptName;    //用户所属部门
    private String deptBirth;   //部门创建的时间

}
