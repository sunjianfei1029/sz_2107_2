package com.bjpowernode.crm.mapper;

import com.bjpowernode.pojo.Product;
import org.apache.ibatis.annotations.Param;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface ProductMapper {
    Product getById(Serializable id);
    int save(Product product);

    int save2(Map product);
    List<Product> getAll();
    int getCount();
    Product getById2(Serializable id);
    Product getById3(Serializable id);
    Product getById4(@Param("id") Serializable id,
                     @Param("name") Serializable name);

    Product getById5(@Param("id") Serializable id,
                     @Param("pro") Product product);

    List<Product> getByName(String name);
    List<Product> getByName2(String name);

    List<Product> getList(Product product);

    int delete(Serializable... id);


    int saveList(List<Product> products);

    // 关联查询出类别
    Product getWithType(Serializable id);

    // 分步查询出类别信息
    Product getWithType2(Serializable id);

    // 查询某一个类型下的所有商品
    List<Product> getByTypeId(Serializable id);
}
