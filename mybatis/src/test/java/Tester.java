import com.bjpowernode.crm.mapper.CommonMapper;
import com.bjpowernode.crm.mapper.ProductMapper;
import com.bjpowernode.crm.mapper.TypeMapper;
import com.bjpowernode.pojo.Product;
import com.bjpowernode.pojo.Type;
import com.bjpowernode.utils.MyBatisUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Tester implements Serializable {
    @Test
    public void test01() {
        // 获取连接会话
        SqlSession sqlSession = MyBatisUtils.openSession();
        // 参数1：命名空间.标签的ID
        // 参数2：SQL的条件
        //Product product = sqlSession.selectOne("product.getById", 41);
        Product product = sqlSession.selectOne("com.bjpowernode.crm.mapper.ProductMapper.getById", 1);

        MyBatisUtils.release(); // 释放资源

        System.out.println(product);

        // try块中的语句执行完毕之后，连接自动释放
        /*try(SqlSession sqlSession = sqlSessionFactory.openSession()) {
            Product product = sqlSession.selectOne("product.getById", 41);
            System.out.println(product);
        }*/
    }

    @Test
    public void test02() {
        // 获取连接会话
        SqlSession sqlSession = MyBatisUtils.openSession();

        Product product = new Product();
        product.setName("MyBatis添加2");
        product.setDescription("MyBatis很简单？");

        //sqlSession.insert("product.save", product);
        sqlSession.insert("com.bjpowernode.crm.mapper.ProductMapper.save", product);

        MyBatisUtils.commit(); // 提交事务
        MyBatisUtils.release(); // 释放资源
    }

    @Test
    public void test03() {
        // 获取连接会话
        SqlSession sqlSession = MyBatisUtils.openSession();
        // getMapper方法根据提供的接口，内部使用动态代理技术创建实现类
        ProductMapper productMapper = sqlSession.getMapper(ProductMapper.class);
        Product product = productMapper.getById3(1); // 在当次会话的第一次查询的数据会保存到内存中作为缓存。
        Product product2 = productMapper.getById3(1); // 之后的相同查询直接从缓存中获取。
        Product product3 = productMapper.getById3(1);

        System.out.println(product==product2);

        MyBatisUtils.commit(); // 提交事务
        MyBatisUtils.release(); // 释放资源（会话结束）

        sqlSession = MyBatisUtils.openSession();
        productMapper = sqlSession.getMapper(ProductMapper.class);
        Product product4 = productMapper.getById3(1);
    }

    @Test
    public void test04() {
        // 获取连接会话
        SqlSession sqlSession = MyBatisUtils.openSession();
        ProductMapper productMapper = sqlSession.getMapper(ProductMapper.class);
        /*Product product = new Product();
        product.setName("MyBatis添加2");
        product.setDescription("MyBatis很简单？");
        productMapper.save(product);*/

        productMapper.save2(new HashMap(){{
            put("name", "name");
            put("description", "description");
        }});

        MyBatisUtils.commit(); // 提交事务
        MyBatisUtils.release(); // 释放资源
    }

    @Test
    public void test05() {
        // 获取连接会话
        SqlSession sqlSession = MyBatisUtils.openSession();
        ProductMapper productMapper = sqlSession.getMapper(ProductMapper.class);
        List<Product> products = productMapper.getAll();

        System.out.println(products);

        MyBatisUtils.release(); // 释放资源
    }

    @Test
    public void test06() {
        // 获取连接会话
        SqlSession sqlSession = MyBatisUtils.openSession();
        // getMapper方法根据提供的接口，内部使用动态代理技术创建实现类
        ProductMapper productMapper = sqlSession.getMapper(ProductMapper.class);
        Product product = productMapper.getById4(41, "神舟（HASEE） 战神K660D-i7D2");

        System.out.println(product);

        MyBatisUtils.commit(); // 提交事务
        MyBatisUtils.release(); // 释放资源
    }

    @Test
    public void test07() {
        // 获取连接会话
        SqlSession sqlSession = MyBatisUtils.openSession();
        // getMapper方法根据提供的接口，内部使用动态代理技术创建实现类
        ProductMapper productMapper = sqlSession.getMapper(ProductMapper.class);
        Product pro = new Product();
        pro.setName("神舟（HASEE） 战神K660D-i7D2");

        Product product = productMapper.getById5(41, pro);

        System.out.println(product);

        MyBatisUtils.commit(); // 提交事务
        MyBatisUtils.release(); // 释放资源
    }

    @Test
    public void test08() {
        // 获取连接会话
        SqlSession sqlSession = MyBatisUtils.openSession();
        // getMapper方法根据提供的接口，内部使用动态代理技术创建实现类
        ProductMapper productMapper = sqlSession.getMapper(ProductMapper.class);

        List<Product> 神 = productMapper.getByName2("神");
        System.out.println(神);

        MyBatisUtils.release(); // 释放资源
    }

    @Test
    public void test09() {
        // 获取连接会话
        SqlSession sqlSession = MyBatisUtils.openSession();
        // getMapper方法根据提供的接口，内部使用动态代理技术创建实现类
        ProductMapper productMapper = sqlSession.getMapper(ProductMapper.class);

        Product product = new Product();
        product.setName("神");
        product.setDescription("1");

        List<Product> products = productMapper.getList(product);

        MyBatisUtils.release(); // 释放资源

        System.out.println(products);
    }

    @Test // 批量删除
    public void test10() {
        // 获取连接会话
        SqlSession sqlSession = MyBatisUtils.openSession();
        // getMapper方法根据提供的接口，内部使用动态代理技术创建实现类
        ProductMapper productMapper = sqlSession.getMapper(ProductMapper.class);

        productMapper.delete(1,2,3);

        MyBatisUtils.commit();
        MyBatisUtils.release(); // 释放资源
    }

    @Test // 批量添加
    public void test11() {
        // 获取连接会话
        SqlSession sqlSession = MyBatisUtils.openSession();
        // getMapper方法根据提供的接口，内部使用动态代理技术创建实现类
        ProductMapper productMapper = sqlSession.getMapper(ProductMapper.class);

        productMapper.saveList(new ArrayList(){{
            add(new Product("iPhone 7", "3666", "999"));
            add(new Product("iPhone 8", "4666", "1999"));
            add(new Product("iPhone 12", "6666", "2999"));
        }});

        MyBatisUtils.commit();
        MyBatisUtils.release(); // 释放资源
    }

    @Test // 批量添加
    public void test12() {
        // 获取连接会话
        SqlSession sqlSession = MyBatisUtils.openSession();
        // getMapper方法根据提供的接口，内部使用动态代理技术创建实现类
        CommonMapper commonMapper = sqlSession.getMapper(CommonMapper.class);

        commonMapper.save("type",new HashMap(){{
            put("name", "iPhone 13");
            //put("description", "听说是120HZ??");
        }});

        MyBatisUtils.commit();
        MyBatisUtils.release(); // 释放资源
    }

    @Test // 通用删除
    public void test13() {
        // 获取连接会话
        SqlSession sqlSession = MyBatisUtils.openSession();
        // getMapper方法根据提供的接口，内部使用动态代理技术创建实现类
        CommonMapper commonMapper = sqlSession.getMapper(CommonMapper.class);

        commonMapper.delete("type", "4");

        MyBatisUtils.commit();
        MyBatisUtils.release(); // 释放资源
    }

    @Test // 通用更新
    public void test14() {
        // 获取连接会话
        SqlSession sqlSession = MyBatisUtils.openSession();
        // getMapper方法根据提供的接口，内部使用动态代理技术创建实现类
        CommonMapper commonMapper = sqlSession.getMapper(CommonMapper.class);

        commonMapper.update("product", new HashMap(){{
            put("id", 57);
            put("name", "手机属马");
            put("num", "555");
        }});

        MyBatisUtils.commit();
        MyBatisUtils.release(); // 释放资源
    }

    @Test // 通用更新
    public void test15() throws JsonProcessingException {
        // 获取连接会话
        SqlSession sqlSession = MyBatisUtils.openSession();
        // getMapper方法根据提供的接口，内部使用动态代理技术创建实现类
        CommonMapper commonMapper = sqlSession.getMapper(CommonMapper.class);

        Map product = commonMapper.get("product", 41);
        ProductMapper mapper = sqlSession.getMapper(ProductMapper.class);
        Product product1 = mapper.getById(41);

        MyBatisUtils.commit();
        MyBatisUtils.release(); // 释放资源

        ObjectMapper objectMapper = new ObjectMapper();
        String json1 = objectMapper.writeValueAsString(product);
        String json2 = objectMapper.writeValueAsString(product1);

        System.out.println(json1);
        System.out.println(json2);
    }

    @Test // 通用更新
    public void test16() throws JsonProcessingException {
        // 获取连接会话
        SqlSession sqlSession = MyBatisUtils.openSession();
        // getMapper方法根据提供的接口，内部使用动态代理技术创建实现类
        CommonMapper commonMapper = sqlSession.getMapper(CommonMapper.class);


        commonMapper.saveOrUpdate("type", new HashMap(){{
            put("name", "yyyy");
            put("id", "5");
        }});


        MyBatisUtils.commit();
        MyBatisUtils.release(); // 释放资源

    }

    @Test // 通用更新
    public void test17() {
        // 获取连接会话
        SqlSession sqlSession = MyBatisUtils.openSession();
        TypeMapper typeMapper = sqlSession.getMapper(TypeMapper.class);
        Type type = typeMapper.getWithProducts(1);
        MyBatisUtils.release(); // 释放资源

        System.out.println(type);

    }
    @Test // 通用更新
    public void test18() {
        // 获取连接会话
        SqlSession sqlSession = MyBatisUtils.openSession();
        ProductMapper productMapper = sqlSession.getMapper(ProductMapper.class);
        Product product = productMapper.getWithType(1);
        MyBatisUtils.release(); // 释放资源

        System.out.println(product);
    }

    @Test // 手动进行多次查询
    public void test19() {
        // 获取连接会话
        SqlSession sqlSession = MyBatisUtils.openSession();
        TypeMapper typeMapper = sqlSession.getMapper(TypeMapper.class);
        // 此时type中不包含商品信息
        Type type = typeMapper.get(1);

        ProductMapper productMapper = sqlSession.getMapper(ProductMapper.class);
        List<Product> products = productMapper.getByTypeId(1);

        type.setProducts(products);

        MyBatisUtils.release(); // 释放资源

        System.out.println(type);
    }

    @Test // 通过MyBatis完成分步查询
    public void test20() {
        // 获取连接会话
        SqlSession sqlSession = MyBatisUtils.openSession();
        TypeMapper typeMapper = sqlSession.getMapper(TypeMapper.class);
        // 此时type中不包含商品信息
        Type type = typeMapper.getWithProducts2(1);

        MyBatisUtils.release(); // 释放资源

        System.out.println(type);
    }

    @Test // 通过MyBatis完成分步查询
    public void test21() {
        // 获取连接会话
        SqlSession sqlSession = MyBatisUtils.openSession();
        ProductMapper productMapper = sqlSession.getMapper(ProductMapper.class);
        // 此时type中不包含商品信息
        Product product = productMapper.getWithType2(1);

        MyBatisUtils.release(); // 释放资源

        Type type = product.getType();
        System.out.println(product);
    }

    @Test // 测试二级缓存
    public void test22() {
        // 获取连接会话
        SqlSession sqlSession = MyBatisUtils.openSession();
        // getMapper方法根据提供的接口，内部使用动态代理技术创建实现类
        ProductMapper productMapper = sqlSession.getMapper(ProductMapper.class);
        Product product = productMapper.getById3(1);

        MyBatisUtils.release(); // 释放资源（会话结束），二级缓存生效

        sqlSession = MyBatisUtils.openSession();
        productMapper = sqlSession.getMapper(ProductMapper.class);

        productMapper.delete("2"); // 执行更新，测试二级缓存是否清空
        MyBatisUtils.commit();

        Product product2 = productMapper.getById3(1);

        MyBatisUtils.release();
    }
}
