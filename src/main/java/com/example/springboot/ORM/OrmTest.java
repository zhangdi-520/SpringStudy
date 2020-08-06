package com.example.springboot.ORM;


import com.example.springboot.ORM.OneToMany.Roles;
import com.example.springboot.ORM.OneToMany.RolesRepository;
import com.example.springboot.ORM.OneToMany.Users;
import com.example.springboot.ORM.OneToMany.UsersRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.*;
import static org.apache.commons.io.FileUtils.*;


/**
 * @version V1.0
 * @program: Spring
 * @description: TODO
 * @author: Mr.Zhang
 * @create: 2020-07-27 09:41
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrmTest {

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    EmployeeService employeeService;

    @Autowired
    Gjj51Service gjj51Service;

    @Autowired
    UsersRepository usersRepository;

    @Autowired
    RolesRepository rolesRepository;

    @Test
    public void test(){

//        employeeRepository.deleteAll();

//        List<Employee> employees = new ArrayList<>();
//        for(int i=0;i<=2;i++){
//            Employee employee = new Employee();
//            employee.setName(UUID.randomUUID().toString().substring(0,5));
//            employee.setHeight(String.valueOf(i));
//            if(i%2==0){
//                employee.setSex("nan");
//            }else{
//                employee.setSex("nv");
//            }
//            employees.add(employee);
//        }
//        employeeRepository.saveAll(employees);

//        employeeRepository.updateById("750e115d-f99c-41e9-8775-1712727b4c38","赵武","19");

        //简单分页
        Pageable pageable = PageRequest.of(0,10);
//        Page<Employee> nv = employeeRepository.findBySex("nv", pageable);
//        System.out.println(nv.getTotalElements());
//        System.out.println(nv.getTotalPages());
//        nv.toList().forEach(item->{
//            System.out.println(item.toString());
//        });

        //复杂查询
//        Pageable pageable =PageRequest.of(0,10);
//        Specification<Employee> specification = (root,query,criteriaBuilder)->{
//            List<Predicate> list = new ArrayList<>();
//            Predicate p1 = criteriaBuilder.equal(root.get("sex"), "nv");
//            list.add(p1);
//            Predicate name = criteriaBuilder.like(root.get("name"), "%d%");
//            list.add(name);
//            return criteriaBuilder.and(list.toArray(new Predicate[0]));
//        };
//        List<Employee> all = employeeRepository.findAll(specification);

//        Page<Employee> all = employeeRepository.findAll(specification, pageable);
//        System.out.println(all.getTotalElements());
//        System.out.println(all.getTotalPages());
//        all.forEach(item->{
//            System.out.println(item.toString());
//        });
    }

    @Test
    public void testOrmOnly() {
//        EmployeeNameOnly byHeight = employeeRepository.findByHeight("12");
//        System.out.println(byHeight.toString());
//        System.out.println(byHeight.getName());

//        List<EmployeeOnlyDto> byOnlyId = employeeRepository.findByOnlyId("12");
//        byOnlyId.forEach(item->{
//            System.out.println(item.toString());
//        });

        List<Employee> b = employeeRepository.findByNameLike("%b%");
        b.forEach(System.out::println);
    }

    @Test
    public void testTransactional(){
//        gjj51Service.insertInfo("2f83f1c7-d383-42ba-b1e6-66dad4ad64f6","李四","188");

        gjj51Service.insertInfo("2f83f1c7-d383-42ba-b1e6-66dad4ad64f6","张三","88");

    }

    @Test
    public void testRepositorySortingAndPaging(){

        //jpa排序
        List<Employee> height = employeeRepository.findAll(Sort.by("height").descending().and(Sort.by("name").ascending()));
        height.forEach(System.out::println);
        //jpa自定义sql排序
//        List<Employee> employees = employeeRepository.sortByHeight();
//        employees.forEach(System.out::println);

        //jpa分页
//        Pageable pageable = PageRequest.of(0,2);
//        Page<Employee> all = employeeRepository.findAll(pageable);
//        all.getContent().forEach(System.out::println);

        //分页+排序
        Pageable pageable = PageRequest.of(0,2, Sort.Direction.DESC,"height");
        Page<Employee> all = employeeRepository.findAll(pageable);
        all.getContent().forEach(System.out::println);

    }

    @Test
    public void testJpaSpecificationExecutor(){
        //该接口不能单独继承，要不工厂注入报错
        Specification<Employee> specification = new Specification<Employee>() {
            @Override
            public Predicate toPredicate(Root<Employee> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Predicate pre = criteriaBuilder.equal(root.get("height"),"0");
                Predicate pre1 = criteriaBuilder.equal(root.get("sex"),"nan");

                return criteriaBuilder.or(criteriaBuilder.and(Arrays.asList(pre,pre1).toArray(new Predicate[0])),criteriaBuilder.equal(root.get("name"),"张三"));
            }
        };
        List<Employee> all = employeeRepository.findAll(specification,Sort.by("height").ascending());
        all.forEach(System.out::println);

//        List<Employee> height = employeeRepository.findAll(Sort.by("height").descending());
//        height.forEach(System.out::println);
    }

    @Test
    public void testOneToMany(){
//        Users users = new Users();
//        users.setAddress("天津");
//        users.setAge(32);
//        users.setName("小刚");
//        Users users1 = new Users();
//        users1.setAddress("天津1");
//        users1.setAge(321);
//        users1.setName("小刚1");
//        Set<Users> usersSet = new HashSet<>();
//        usersSet.add(users);
//        usersSet.add(users1);
//
//        Roles roles = new Roles();
//        roles.setRolename("管理员");
//
//        roles.setUsers(usersSet);
//
//        rolesRepository.save(roles);
//
//        usersSet.forEach(users2 -> {
//            users2.setRoleid(roles.getRoleid());
//        });
//        usersRepository.saveAll(usersSet);


        Optional<Users> byId = usersRepository.findById(9);
        if(byId.isPresent()){
            Users users = byId.get();
            users.setAge(null);
            users.setAddress(null);
            usersRepository.save(users);
        }else {
            System.out.println("查无此人");
        }

//        Optional<Roles> byId = rolesRepository.findByRoleid(4);
//        if(byId.isPresent()){
//            System.out.println(byId.get().toString());
//            System.out.println(byId.get().getUsers());
//        }



    }
}
