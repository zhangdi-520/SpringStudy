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
 * @create: 2020-07-30 17:32
 **/
@Service
public class Gjj51Service {

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    Gjj51Repository gjj51Repository;

    @Autowired
    EmployeeService employeeService;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void insertInfo(String id,String name,String height){
        Gjj51Data gjj51Data = new Gjj51Data();
        RowKeyPK rowKeyPK = new RowKeyPK("12251233655",System.currentTimeMillis());
        gjj51Data.setRowKeyPK(rowKeyPK);

        gjj51Repository.save(gjj51Data);

        employeeService.outinsertInfo(id,name,height);

        int i = 1/0;
    }
}
