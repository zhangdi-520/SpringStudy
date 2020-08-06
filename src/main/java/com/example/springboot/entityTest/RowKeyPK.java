package com.example.springboot.entityTest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

import java.io.Serializable;

/**
 * @version V1.0
 * @program: Spring
 * @description: TODO
 * @author: Mr.Zhang
 * @create: 2020-07-20 18:10
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Embeddable
public class RowKeyPK implements Serializable {

    private String IdCard;
    private long timestamp;
    public static RowKeyPK build(String idCard){
        return new RowKeyPK(idCard,System.currentTimeMillis());
    }
}
