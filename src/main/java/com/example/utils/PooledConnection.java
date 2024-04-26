package com.example.utils;

import java.sql.Connection;
import java.sql.SQLException;

public class PooledConnection implements AutoCloseable {
    private  final Connection connection;
    private long lastAccessTime;//最后一次访问的时间戳

    public PooledConnection(Connection connection) {
        this.connection = connection;
        this.lastAccessTime = System.currentTimeMillis();
    }

    public Connection getConnection() {
        lastAccessTime = System.currentTimeMillis();
        return connection;
    }

    public long getLastAccessTime() {
        return lastAccessTime;
    }
    public boolean isValid() {
        try {
            return connection.isValid(3); // 测试连接是否有效,超时时间为3秒
        } catch (SQLException e) {
            return false;
        }
    }
    @Override
    public void close() throws SQLException {
        connection.close();
    }
}
