package com.bjpowernode.crm.mapper;

import com.bjpowernode.pojo.Type;

import java.io.Serializable;

public interface TypeMapper {
    // 查询出其下包含商品信息的类别
    Type getWithProducts(Serializable id);

    Type get(Serializable id);

    // 查询类别，同时将商品信息也查询出来：分步查询方式
    Type getWithProducts2(Serializable id);
}
