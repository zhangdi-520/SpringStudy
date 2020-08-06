package com.example.springboot.entityTest;

import com.example.springboot.utils.orika.User;
import lombok.*;

import javax.persistence.*;
import java.util.Set;


/**
 * @version V1.0
 * @program: Spring
 * @description: TODO
 * @author: Mr.Zhang
 * @create: 2020-07-20 18:16
 **/
@Data
@Builder
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Gjj_data")
@Entity
@SuppressWarnings("unchecked")
@NamedEntityGraph(name="Gjj.Graph",attributeNodes = {@NamedAttributeNode("gjj51DataResults")})
public class Gjj51Data {

    @EmbeddedId
    private RowKeyPK rowKeyPK;

    @Column(updatable = false,insertable = false)
    private String idCard;

    @Column(updatable = false,insertable = false)
    private long timestamp;

    @Embedded
    private User user;

    @OneToMany(mappedBy = "rowKeyPK")
    private Set<Gjj51DataResult> gjj51DataResults;

}
