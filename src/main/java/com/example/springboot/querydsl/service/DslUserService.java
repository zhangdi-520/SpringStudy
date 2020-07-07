package com.example.springboot.querydsl.service;

import com.example.springboot.querydsl.dto.DslUserDto;
import com.example.springboot.querydsl.dto.UserDeptDTO;
import com.example.springboot.querydsl.entity.*;
import com.example.springboot.querydsl.repository.DslUserRepository;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionSynchronization;
import org.springframework.transaction.support.TransactionSynchronizationManager;


import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * @version V1.0
 * @program: Spring
 * @description: TODO
 * @author: Mr.Zhang
 * @create: 2020-04-17 15:31
 **/

@Slf4j
@Service
public class DslUserService {

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    ZSetOperations<String,String> opsForZSet;


    @Autowired
    TestService testService;

    @Autowired
    DslUserRepository dslUserRepository;

    @Autowired
    JPAQueryFactory jpaQueryFactory;

    @PostConstruct
    public void initOpsForZSet(){
        opsForZSet = stringRedisTemplate.opsForZSet();
    }

    /**
     * @Description: 根据用户名和密码查找
     * @Param: [username, password]
     * @return: com.example.springboot.querydsl.entity.DslUser
     * @Author: Mr.Wang
     * @Date: 2020/4/17
     */
    public DslUser findByUsernameAndPassword(String username,String password){
        QDslUser qDslUser = QDslUser.dslUser;
        return jpaQueryFactory
                .selectFrom(qDslUser)
                .where(
                        qDslUser.username.eq(username),
                        qDslUser.password.eq(password)
                )
                .fetchOne();
    }

    /**
     * @Description:查询所有实体，根据uIndex字段排序
     * @Param: []
     * @return: java.util.List<com.example.springboot.querydsl.entity.DslUser>
     * @Author: Mr.Wang
     * @Date: 2020/4/17
     */
    public List<DslUser> findAll(){
        QDslUser qDslUser = QDslUser.dslUser;
        return jpaQueryFactory
                .selectFrom(qDslUser)
                .orderBy(
                        qDslUser.uIndex.asc()
                )
                .fetch();
    }

    /**
     * @Description: 分页查询所有实体，根据Uindex字段排序
     * @Param: [pageable]
     * @return: com.querydsl.core.QueryResults<com.example.springboot.querydsl.entity.DslUser>
     * @Author: Mr.Wang
     * @Date: 2020/4/17
     */
    public QueryResults<DslUser> findAllPage(Pageable pageable){
        QDslUser qDslUser = QDslUser.dslUser;
        return jpaQueryFactory
                .selectFrom(qDslUser)
                .orderBy(
                        qDslUser.uIndex.asc()
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();
    }

    /**
     * @Description: 根据起始日期和终止日期查询
     * @Param: [start, end]
     * @return: java.util.List<com.example.springboot.querydsl.entity.DslUser>
     * @Author: Mr.Wang
     * @Date: 2020/4/17
     */
    public List<DslUser> findByBirthdayBetween(Date start,Date end){
        QDslUser qDslUser = QDslUser.dslUser;
        return jpaQueryFactory
                .selectFrom(qDslUser)
                .where(
                        qDslUser.birthday.between(start,end)
                )
                .fetch();
    }

    /**
     * @Description: 部分字段映射查询
     * @Param: [pageable]
     * @return: java.util.List<com.example.springboot.querydsl.dto.DslUserDto>
     * @Author: Mr.Wang
     * @Date: 2020/4/20
     */
    public List<DslUserDto> findAllUserDto(Pageable pageable){
        QDslUser qDslUser = QDslUser.dslUser;
        List<DslUserDto> dslUserList = jpaQueryFactory
                .select(
                        qDslUser.username,
                        qDslUser.userId,
                        qDslUser.nickName,
                        qDslUser.birthday
                    )
                .from(qDslUser)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch()
                .stream()
                .map(tuple -> DslUserDto.builder()
                .username(tuple.get(qDslUser.username))
                .nickname(tuple.get(qDslUser.nickName))
                .userId(tuple.get(qDslUser.userId).toString())
                .birthday(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(tuple.get(qDslUser.birthday)))
                .build())
                .collect(Collectors.toList());
        return dslUserList;
    }

    /**
     * @Description: 根据昵称和用户名查询，并根据uIndex排序
     * @Param: [nickName, username]
     * @return: java.util.List<com.example.springboot.querydsl.entity.DslUser>
     * @Author: Mr.Wang
     * @Date: 2020/4/20
     */
    public List<DslUser> findByNicknameAndUsername(String nickName,String username){
        QDslUser qDslUser = QDslUser.dslUser;
        List<DslUser> dslUserList = (List<DslUser>) dslUserRepository.findAll(
                qDslUser.nickName.eq(nickName).and(qDslUser.username.eq(username)),
                qDslUser.uIndex.asc());
        return dslUserList;
    }

    /**
     * @Description: 统计名字像likeName的记录数量
     * @Param: [likeName]
     * @return: long
     * @Author: Mr.Wang
     * @Date: 2020/4/20
     */
    public long countByUserNameLike(String likeName){
        QDslUser qDslUser = QDslUser.dslUser;
        return dslUserRepository.count(
                qDslUser.username.like("%"+likeName+"%")
        );
    }

    /**
     * @Description:
     * @Param: [username, password, nickName, birthday, uIndex]
     * @return: org.springframework.data.domain.Page<com.example.springboot.querydsl.entity.DslUser>
     * @Author: Mr.Wang
     * @Date: 2020/4/20
     */
    public Page<DslUser> findByUserProperties(String username, String password, String nickName, Date birthday, BigDecimal uIndex){
        QDslUser qDslUser = QDslUser.dslUser;
        Predicate predicate =  qDslUser.isNotNull().or(qDslUser.isNull());
        predicate = username==null?predicate:ExpressionUtils.and(predicate,qDslUser.username.eq(username));
        predicate = password==null?predicate:ExpressionUtils.and(predicate,qDslUser.password.eq(password));
        predicate = nickName==null?predicate:ExpressionUtils.and(predicate,qDslUser.nickName.eq(nickName));
        predicate = birthday==null?predicate:ExpressionUtils.and(predicate,qDslUser.password.eq(password));
        predicate = uIndex ==null?predicate:ExpressionUtils.and(predicate,qDslUser.uIndex.eq(uIndex));
        Pageable pageable = PageRequest.of(0,3, Sort.Direction.ASC, "username");
        Page<DslUser> page = dslUserRepository.findAll(predicate,pageable);
        return page;
    }


    /**
     * @Description: 带外键的连表查询
     * @Param: [departmentId]
     * @return: java.util.List<com.example.springboot.querydsl.dto.UserDeptDTO>
     * @Author: Mr.Wang
     * @Date: 2020/4/20
     */
//    public List<UserDeptDTO> findByDepatmentIdDTO(int departmentId){
//        QDslUser dslUser = QDslUser.dslUser;
//        QDepartment department = QDepartment.department;
//        return jpaQueryFactory
//                .select(
//                        dslUser.username,
//                        dslUser.nickName,
//                        dslUser.birthday,
//                        department.deptName,
//                        department.createDate
//                )
//                .from(dslUser)
//                .join(dslUser.department,department)
//                .where(department.deptId.eq(departmentId))
//                .fetch()
//                .stream()
//                .map(
//                        tuple -> UserDeptDTO.builder()
//                        .username(tuple.get(dslUser.username))
//                        .nickname(tuple.get(dslUser.nickName))
//                        .birthday(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(tuple.get(dslUser.birthday)))
//                        .deptName(tuple.get(department.deptName))
//                        .deptBirth(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(tuple.get(department.createDate)))
//                        .build()
//                )
//                .collect(Collectors.toList());
//    }

    /**
     * @Description: 无外键情况的连表查询
     * @Param: [departmentId]
     * @return: java.util.List<com.example.springboot.querydsl.dto.UserDeptDTO>
     * @Author: Mr.Wang
     * @Date: 2020/4/20
     */
    public List<UserDeptDTO> findByDepatmentIdDTO(int departmentId){
        QDslUser qDslUser = QDslUser.dslUser;
        QDepartment department = QDepartment.department;
        return jpaQueryFactory
                .select(
                        qDslUser.username,
                        qDslUser.nickName,
                        qDslUser.birthday,
                        department.deptName,
                        department.createDate
                )
                .from(qDslUser,department)
                .where(
                        qDslUser.departmentId.eq(department.deptId).and(department.deptId.eq(departmentId))
                )
                .fetch()
                .stream()
                .map(
                        tuple ->
                                //需要做类型转换，所以使用map函数非常适合
                                UserDeptDTO.builder()
                                        .username(tuple.get(qDslUser.username))
                                        .nickname(tuple.get(qDslUser.nickName))
                                        .birthday(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(tuple.get(qDslUser.birthday)))
                                        .deptName(tuple.get(department.deptName))
                                        .deptBirth(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(tuple.get(department.createDate)))
                                        .build()
                )
                .collect(Collectors.toList());
    }

    public static void say(){
        System.out.println("事务提交后执行了我1");
        System.out.println("事务提交后执行了我2");
        System.out.println("事务提交后执行了我3");
        System.out.println("事务提交后执行了我4");
        System.out.println("事务提交后执行了我5");
        System.out.println("事务提交后执行了我6");
        System.out.println("事务提交后执行了我7");
        System.out.println("事务提交后执行了我8");
        System.out.println("事务提交后执行了我9");
    }
    @Transactional
    public void test(){
        testService.test();
        TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronization() {
            @Override
            public void afterCommit() {
                System.out.println("aaa");
                say();
            }
        });
        System.out.println("提交了事务");
    }

    public DslUserDto initDslUserDto(DslUserDto dslUserDto){
        return InitDslUser.initInfo(dslUserDto).getDslUserDto(
                dslUserDto1 -> {
                    dslUserDto1.setNickname("zhangsan");
                }
        ).endDslUserDto();
    }

    public static class InitDslUser{
        DslUserDto dslUserDto;
        public static InitDslUser initInfo(DslUserDto dslUserDto){
            InitDslUser initDslUser = new InitDslUser();
            initDslUser.dslUserDto = dslUserDto;
            dslUserDto.setBirthday("19951220");
            return initDslUser;
        }

        private InitDslUser getDslUserDto(Consumer<DslUserDto> dslUserDtoConsumer){
            dslUserDtoConsumer.accept(dslUserDto);
            return this;
        }

        private DslUserDto endDslUserDto(){
            dslUserDto.setUserId("12345678");
            return dslUserDto;
        }
    }


    public void addTask(){
        opsForZSet.add("zhangsan","123456",10*60*1000/1000);
        log.info("成功添加Redis任务");
    }

    public void invokeFlow(Consumer<String> data){
        System.out.println("执行Consumer函数前");
        data.accept("我他妈的执行了自己");
        System.out.println("执行Consumer函数后");

    }
}
