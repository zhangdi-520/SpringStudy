package com.example.springboot.ORM.OneToMany;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @version V1.0
 * @program: Spring
 * @description: TODO
 * @author: Mr.Zhang
 * @create: 2020-08-06 10:39
 **/
public interface RolesRepository extends JpaRepository<Roles,Integer> {

    @EntityGraph(value="Roles.Graph",type=EntityGraph.EntityGraphType.FETCH)
    Optional<Roles> findByRoleid(Integer id);
}
