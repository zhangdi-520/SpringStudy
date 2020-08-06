package com.example.springboot.ORM;

import com.example.springboot.entityTest.Gjj51Data;
import com.example.springboot.entityTest.Gjj51Repository;
import com.example.springboot.entityTest.RowKeyPK;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


/**
 * @version V1.0
 * @program: Spring
 * @description: TODO
 * @author: Mr.Zhang
 * @create: 2020-07-28 18:04
 **/
@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    Gjj51Repository gjj51Repository;



    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void outinsertInfo(String id,String name,String height){
        employeeRepository.updateById(id,name,height);


    }

}
