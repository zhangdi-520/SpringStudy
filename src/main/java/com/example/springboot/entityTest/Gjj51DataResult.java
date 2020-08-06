package com.example.springboot.entityTest;


import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

/**
 * @version V1.0
 * @program: Spring
 * @description: TODO
 * @author: Mr.Zhang
 * @create: 2020-07-21 16:41
 **/
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="gjj_data_result")
public class Gjj51DataResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long gjjId;

    @Embedded
    private RowKeyPK rowKeyPK;

    @Column(updatable = false,insertable = false)
    private String idCard;

    @Column(updatable = false,insertable = false)
    private long timestamp;

    private Integer amount;
}
