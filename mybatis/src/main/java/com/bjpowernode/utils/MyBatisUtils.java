package com.bjpowernode.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class MyBatisUtils {
    private static SqlSessionFactory sqlSessionFactory;
    // 将连接对象SqlSession和当前线程进行绑定
    private static ThreadLocal<SqlSession> TL = new ThreadLocal<>();

    // 初始化 sqlSessionFactory
    static {
        try {
            InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 链接的工厂对象
    public static SqlSession openSession() {
        // 从线程对象中获取链接
        SqlSession sqlSession = TL.get();
        if (sqlSession == null) {
            // 说明当前线程是第一次调用该方法
            sqlSession = sqlSessionFactory.openSession();
            // 将连接对象和当前线程绑定
            TL.set(sqlSession);
        }
        return sqlSession;
    }

    public static void commit() {
        openSession().commit();
    }

    // 释放资源
    public static void release() {
        // 不是真正的关闭，而是将链接返还给连接池
        openSession().close();
        // 解决 Executor was closed.
        TL.remove();
    }
}
