package com.example.springboot.JWT.repository;

import com.example.springboot.JWT.entity.JwtUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * @program: Spring
 * @description: zzz
 * @author: Mr.Wang
 * @create: 2020-04-16 13:51
 **/
public interface JwtUserRepository extends JpaRepository<JwtUser,Integer> {

    JwtUser findByUsername(String username);

    @Modifying
    @Query("update JwtUser u set u.ban = ?1 where u.username = ?2")
    void deleteByUsername(int ban,String username);

    @Modifying
    @Query("update JwtUser u set u.password = ?1 where u.username = ?2")
    void updatePassword(String password,String username);

}
