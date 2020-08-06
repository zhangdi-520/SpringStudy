package com.example.springboot.ORM;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @version V1.0
 * @program: Spring
 * @description: TODO
 * @author: Mr.Zhang
 * @create: 2020-07-27 09:43
 **/
public interface EmployeeRepository extends JpaRepository<Employee,Integer>, JpaSpecificationExecutor<Employee> {

    EmployeeNameOnly findByHeight(String height);

    //查询数据库部分字段
    @Query(value="select new com.example.springboot.ORM.EmployeeOnlyDto(e.name,e.sex ) from Employee e where e.height=?1")
    List<EmployeeOnlyDto> findByOnlyId(String id);

    @Transactional
    @Modifying
    @Query(value = "update Employee e set e.name=?2 ,e.height=?3 where e.id=?1")
    void updateById(String id,String name,String height);

    @Query(value="select s from Employee s where s.sex=?1")
    Page<Employee> findBySex(String sex, Pageable pageable);

    List<Employee> findByNameLike(String name);

    @Query(value = "select s from Employee s order by s.height desc ")
    List<Employee> sortByHeight();

}