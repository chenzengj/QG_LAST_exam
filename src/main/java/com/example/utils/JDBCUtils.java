package com.example.utils;

import com.example.config.ConnectionPoolConfig;

import java.sql.*;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class JDBCUtils {
        private static ConnectionPool connectionPool;  // 静态变量，用于存储连接池实例
        private static ConnectionPoolConfig config = new ConnectionPoolConfig();
        // 初始化连接池的方法
        public static void initConnectionPool() {
            connectionPool =new ConnectionPool(config);  // 创建连接池实例
        }

        // 获取数据库连接的方法
        public static Connection getConnection() {
            Connection conn = connectionPool.getConnection();
            if (!isConnectionValid(conn)) {  // 检查连接是否有效
                releaseConnection(conn);  // 如果连接无效，则释放连接
                conn = connectionPool.getConnection();   // 并重新获取连接
            }
            return conn;  // 从连接池中获取连接
        }

        // 释放数据库连接的方法
        public static void releaseConnection(Connection conn) {
            if (isConnectionValid(conn)) {
                connectionPool.releaseConnection(conn); // 如果连接有效,则将其释放回连接池
            } else {
                try {
                    conn.close(); // 如果连接无效,则直接关闭这个连接
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

    // 执行查询操作的方法
    public static ResultSet executeQuery(String sql, Object... params) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            stmt = conn.prepareStatement(sql);
            for (int i = 0; i < params.length; i++) {
                stmt.setObject(i + 1, params[i]);
            }
            rs = stmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            releaseConnection(conn);
        }
        return rs;
    }

    // 执行更新操作的方法
    public static int executeUpdate(String sql, Object... params) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rowsAffected = 0;
        try {
            conn = getConnection();

            stmt = conn.prepareStatement(sql);
            for (int i = 0; i < params.length; i++) {
                stmt.setObject(i + 1, params[i]);
            }
            rowsAffected = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            releaseConnection(conn);
        }
        return rowsAffected;
    }

    // 私有方法，用于检查连接是否有效
    private static boolean isConnectionValid(Connection conn) {
        try {
            if (conn == null || conn.isClosed()) {  // 检查连接是否为空或已关闭
                return false;
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public static void shutdownConnectionPool() {
            connectionPool.shutdown();
    }
    public static void closeResultSet(ResultSet rs){
            try{if(rs!=null){
                rs.close();
            }
            }catch (SQLException e){
                e.printStackTrace();
            }
    }
    }


//    private static final String URL = "jdbc:mysql://localhost:3306/mydb";
//    private static final String USERNAME = "root";
//    private static final String PASSWORD = "771015";
//
//    // 静态代码块，用于加载驱动程序
//    static {
//        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//    }
//
//    // 获取数据库连接
//    public static Connection getConnection() throws SQLException {
//        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
//    }
//
//    // 关闭数据库资源
//    public static void close(Connection conn, Statement stmt, ResultSet rs) {
//        try {
//            if (stmt != null) {
//                stmt.close();
//            }
//            if (conn != null) {
//                conn.close();
//            }
//            if(rs!=null){
//                rs.close();
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//    public static void close(Connection conn, Statement stmt) {
//     close(conn,stmt,null);}

