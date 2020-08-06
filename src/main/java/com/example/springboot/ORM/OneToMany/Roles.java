package com.example.springboot.ORM.OneToMany;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Set;

/**
 * @version V1.0
 * @program: Spring
 * @description: TODO
 * @author: Mr.Zhang
 * @create: 2020-08-03 10:46
 **/
@Entity
@Table(name = "jpa_Roles")
@Data
@NoArgsConstructor
@NamedEntityGraph(name="Roles.Graph",attributeNodes = {@NamedAttributeNode("users")})
public class Roles {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer roleid;
    private String rolename;

    @OneToMany(mappedBy ="roleid")
    private Set<Users> users;
}
