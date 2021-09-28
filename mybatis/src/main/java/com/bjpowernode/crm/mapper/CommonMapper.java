package com.bjpowernode.crm.mapper;

import org.apache.ibatis.annotations.Param;

import java.io.Serializable;
import java.util.Map;

public interface CommonMapper {
    /*
        tableName: 要插入的表名
        map的key: 表中的字段名
        map的value: 字段对应的值
     */
    int save(@Param("tableName") String tableName, @Param("data") Map map);

    // 通用删除，只能根据主键进行删除，而且主键的名字必须是id
/*    @Delete("delete from ${tableName} where id in\n" +
            "   <script>"+
            "        <foreach collection=\"ids\" open=\"(\" close=\")\" separator=\",\" item=\"id\">\n" +
            "            #{id}\n" +
            "        </foreach>" +
            "</script>")*/
    int delete(@Param("tableName") String tableName, @Param("ids") Serializable... ids);

    int update(@Param("tableName") String tableName, @Param("data") Map map);

    Map get(@Param("tableName") String tableName, @Param("id") Serializable id);

    // 根据是否有id来决定指定添加还是修改！如果id为null，则执行添加，否则修改
    int saveOrUpdate(@Param("tableName") String tableName, @Param("data") Map map);
}
