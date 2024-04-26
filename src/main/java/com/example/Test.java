package com.example;

import com.example.utils.JDBCUtils;
//import com.mysql.cj.x.protobuf.MysqlxCrud;

import java.sql.*;

public class Test {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
//        //1、导入驱动jar包
//        //2、注册驱动
//        //Class.forName("com.mysql.cj.jdbc.Driver");
//
//        //3、获取数据库的连接对象
//        Connection con = JDBCUtils.getConnection();
//
//        //4、定义sql语句
//        String sql = "INSERT INTO users VALUES(?,?)";
//
//
//        //5、获取执行sql语句的对象
//        PreparedStatement preparedStatement= con.prepareStatement(sql);
//        preparedStatement.setInt(1,3);
//        preparedStatement.setString(2,"smith");
////        preparedStatement.setString(3,"12345");
////        preparedStatement.setString(4,"student");
//        //6、执行sql并接收返回结果
//        int count=preparedStatement.executeUpdate();
//
//        //7、处理结果
//        System.out.println(count);
//
//        //8、释放资源
//        preparedStatement.close();
//        con.close();
        JDBCUtils.initConnectionPool();
        ResultSet rs = JDBCUtils.executeQuery("SELECT * FROM users where id=?",1);
// 处理结果集...
        while(rs.next()){
            int id=rs.getInt("id");
            String name=rs.getString("username");
            System.out.println(id);
            System.out.println(name);
        }
        String name="王梓绵";
        String password="1234";
        String role="student";
        JDBCUtils.closeResultSet(rs);
        int rowsAffected = JDBCUtils.executeUpdate("Insert INTO users(username,password,user_type)VALUES(?,?,?)", name, password,role);
        //JDBCUtils.closeResultSet(rs);
        System.out.println(rowsAffected);
        JDBCUtils.shutdownConnectionPool();
        //int rowsAffected = JDBCUtils.executeUpdate("UPDATE users SET name = ? WHERE id = ?", "John Doe", 1);
    }
    }

