package com.example.springboot.ORM.OneToMany;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @version V1.0
 * @program: Spring
 * @description: TODO
 * @author: Mr.Zhang
 * @create: 2020-08-03 11:18
 **/
public interface UsersRepository  extends JpaRepository<Users,Integer> {
}
