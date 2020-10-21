package com.atguigu.utils;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @Auther: Yhurri
 * @Date: 2020/5/5 16:51
 * @Description:读取配置文件得到数据库连接 暴露getConnection和ReleaseConnection方法
 */
public class JdbcUtil {
    private static Connection connection =null;
    private static PreparedStatement preparedStatement=null;
    private static ResultSet resultSet=null;
    private static DruidDataSource dataSource;//单例
    private static ThreadLocal<Connection> currentThreadConnection = new ThreadLocal<>();


    static{

        Properties properties = new Properties();


        try {
            //读取流文件 指向src下的目录
            InputStream resourceAsStream = JdbcUtil.class.getClassLoader().getResourceAsStream("jdbc.properties");
            properties.load(resourceAsStream);
            //创建了数据库连接池
            dataSource = (DruidDataSource) DruidDataSourceFactory.createDataSource(properties);
            System.out.println(dataSource);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static Connection getConnection(){
        connection = currentThreadConnection.get();
        if (connection==null){
            try {
                connection = dataSource.getConnection();
                currentThreadConnection.set(connection);//保存到ThreadLocal中 供下次使用
                connection.setAutoCommit(false);//设置非自动提交
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }
    //提交并关闭数据库连接池

    public static void commitAndClose(){
        Connection connection = currentThreadConnection.get();
        try {
            if (connection !=null){//说明使用过该连接操作过数据库

                connection.commit();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if (connection !=null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
            currentThreadConnection.remove();
            //tomcat底层使用线程池 此处必须要移除否则下次还会出错
        }


    }

    //业务失败 回滚
    public static void rollback(){
        Connection connection = currentThreadConnection.get();
        try {
                if (connection !=null){//说明使用过该连接操作过数据库

                connection.rollback();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if (connection !=null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
            currentThreadConnection.remove();
            //tomcat底层使用线程池 此处必须要移除否则下次还会出错
        }
    }

//    public static void closeConnection(){
//        if (connection !=null){
//            try {
//                connection.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//        if (preparedStatement != null){
//            try {
//                preparedStatement.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//        if (resultSet != null){
//            try {
//                resultSet.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//
//
//    }

}
