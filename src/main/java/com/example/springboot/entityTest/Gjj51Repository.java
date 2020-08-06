package com.example.springboot.entityTest;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

/**
 * @version V1.0
 * @program: Spring
 * @description: TODO
 * @author: Mr.Zhang
 * @create: 2020-07-21 16:18
 **/
public interface Gjj51Repository extends JpaRepository<Gjj51Data,RowKeyPK> {

    //@EntityGraph配合@NamedEntityGraph解决一对多是的懒加载问题
    @EntityGraph(value="Gjj.Graph",type=EntityGraph.EntityGraphType.FETCH)
    Optional<Gjj51Data> findByIdCard(String idCard);

    @EntityGraph(value="Gjj.Graph",type=EntityGraph.EntityGraphType.FETCH)
    Optional<Gjj51Data> findById(RowKeyPK rowKeyPK);

    @Query(value="select new com.example.springboot.entityTest.Gjj51DataVo(g.idCard,g.timestamp) from Gjj51Data g where g.idCard=?1")
    Optional<Gjj51DataVo> findByIdenty(String idCard);
}
