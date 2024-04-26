package com.example.utils;

import com.example.config.ConnectionPoolConfig;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ConnectionPool {
    private final ConnectionPoolConfig config;  // 连接池的配置信息
    private final ConcurrentLinkedQueue<PooledConnection> connections;  // 使用并发队列存储连接
    private final ReentrantLock lock;  // 使用可重入锁
    private final Condition connectionsAvailable;  // 使用条件变量来控制连接可用性
    private static final Semaphore connectionsSemaphore = new Semaphore(5);

    public ConnectionPool(ConnectionPoolConfig config) {
        this.config = config;
        this.connections = new ConcurrentLinkedQueue<>();
        this.lock = new ReentrantLock();
        this.connectionsAvailable = lock.newCondition();
        initConnections();
    }



    private void initConnections() {
        for (int i = 0; i < config.minConnections; i++) {
            try {
                Connection connection = DriverManager.getConnection(config.url, config.user, config.password);  // 使用配置信息创建数据库连接
                PooledConnection pooledConnection = new PooledConnection(connection);  // 创建连接池中的连接对象
                connections.add(pooledConnection);  // 将连接对象添加到连接池中
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public Connection getConnection() {
        lock.lock();
        try {
            connectionsSemaphore.acquire(); // 获取信号量许可
            while (connections.isEmpty()) {
                connectionsAvailable.await();  // 如果连接池为空，等待连接可用
            }
            PooledConnection pooledConnection = connections.poll();  // 从连接池中取出一个连接

            if (!pooledConnection.isValid()) { // 如果连接无效
                try {
                    pooledConnection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                return getConnection(); // 重新获取新连接
            }
//            if (isConnectionExpired(pooledConnection)) {  // 检查连接是否过期
//                try {
//                    pooledConnection.close();  // 关闭过期连接
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
//                return getConnection();  // 重新获取连接
//            }
            if (pooledConnection != null) {
                return pooledConnection.getConnection(); // 返回有效连接
            } else {
                // 处理 pooledConnection 为 null 的情况
                throw new RuntimeException("无法从连接池获取有效连接");
            }
            //return pooledConnection.getConnection();  // 返回有效连接
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // 恢复中断状态
            throw new RuntimeException(e);
        }finally {
            connectionsSemaphore.release(); // 释放信号量许可
            try {
                lock.unlock();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    private boolean isConnectionExpired(PooledConnection pooledConnection) {
        long currentTime = System.currentTimeMillis();
        long idleTime = currentTime - pooledConnection.getLastAccessTime();
        return idleTime > config.maxIdleTime;  // 判断连接是否过期
    }

    public void releaseConnection(Connection connection) {
        lock.lock();
        try{
            if (connections.size() < config.maxConnections) {  // 如果连接池未满
                PooledConnection pooledConnection = new PooledConnection(connection);
                if (!pooledConnection.isValid()) { // 如果连接无效
                    try {
                        pooledConnection.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                } else { // 如果连接有效
                    connections.add(pooledConnection);// 将连接添加到连接池中
                    connectionsAvailable.signal();// 唤醒等待线程
                }
            } else {  // 如果连接池已满
                try {
                    connection.close();  // 关闭连接
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } finally {
            lock.unlock();
        }
    }
    //允许动态修改连接池最大连接数
    public void setMaxConnections(int maxConnections) {
        config.maxConnections = maxConnections;
    }
    public void shutdown() {
        synchronized (connections) {
            for (PooledConnection conn : connections) { // 关闭连接池中的所有连接
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            connections.clear(); // 清空连接列表
        }
    }
}
