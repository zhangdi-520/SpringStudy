package com.example.springboot.querydsl.util;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;


/**
 * @version V1.0
 * @program: Spring
 * @description: TODO
 * @author: Mr.Zhang
 * @create: 2020-04-20 08:47
 **/
public class NewPageable implements Pageable {

    private Integer pageSize;

    private long offset;


    public NewPageable(Integer pageSize, long offset){
        this.pageSize = pageSize;
        this.offset = offset;
    }

    @Override
    public int getPageNumber() {
        return 0;
    }

    @Override
    public int getPageSize() {
        return this.pageSize;
    }

    @Override
    public long getOffset() {
        return this.offset;
    }

    @Override
    public Sort getSort() {
        return null;
    }

    @Override
    public Pageable next() {
        return null;
    }

    @Override
    public Pageable previousOrFirst() {
        return null;
    }

    @Override
    public Pageable first() {
        return null;
    }

    @Override
    public boolean hasPrevious() {
        return false;
    }
}
