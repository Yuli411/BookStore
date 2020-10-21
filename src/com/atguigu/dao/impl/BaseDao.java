package com.atguigu.dao.impl;

import com.alibaba.druid.util.JdbcUtils;
import com.atguigu.utils.JdbcUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import javax.management.Query;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @Auther: Yhurri
 * @Date: 2020/5/5 17:41
 * @Description:
 */
public abstract class BaseDao {
    //用dbUtils操作数据库
    private QueryRunner queryRunner = new QueryRunner();

    /**
     *用来执行insert update delete语句
     * 返回-1 执行失败
     * @return
     */
    public int update(String sql,Object...args){
        Connection connection = JdbcUtil.getConnection();
        try {
            return queryRunner.update(connection,sql,args);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
        //        }finally {
//            JdbcUtil.close();
//        }

    }

    /**
     *
     * @param type  返回的对象类型
     * @param sql   sql语句
     * @param args  sql对应的参数值
     * @param <T>   返回类型的泛型
     * @return
     */

    public<T> Object queryForOne(Class<T> type,String sql,Object...args){
        Connection connection = JdbcUtil.getConnection();
        try {
            return queryRunner.query(connection,sql,new BeanHandler<T>(type),args);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }

    }

    /**
     * 查询返回多个javaBean
     * @param type
     * @param sql
     * @param args
     * @param <T>
     * @return
     */

    public<T> List<T> queryList(Class<T> type,String sql,Object...args){
        Connection connection = JdbcUtil.getConnection();
        try {
            return queryRunner.query(connection,sql,new BeanListHandler<T>(type),args);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }

    }

    /**
     * 返回一行一列的查询结果
     * @param sql
     * @param args
     * @return
     */

    public Object queryForSingleValue(String sql, Object... args){

        Connection conn = JdbcUtil.getConnection();

        try {
            return queryRunner.query(conn, sql, new ScalarHandler(), args);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }


    }

}
