package com.example.springboot.context;

import com.example.springboot.demo1.Jobs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ContextController {

    @Autowired
    Jobs jobs;

    @PostMapping(value = {"/apply"})
    public void useContext(){
        jobs.useDealWfTask();
    }


}
