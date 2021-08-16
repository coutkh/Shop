package com.cout.shop.pool;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

public enum ConnectionPool {

    INSTANCE;

    private final Logger logger = LogManager.getLogger();

    private static final int POOL_SIZE = 10;

    private final BlockingDeque<ProxyConnection> freeConnections;
    private final BlockingDeque<ProxyConnection> busyConnections;


    ConnectionPool() {
        DatabaseConfig databaseConfig = new DatabaseConfig();
        try {
            String driverName = databaseConfig.getDriverName();
            String url = databaseConfig.getUrl();
            String username = databaseConfig.getUsername();
            String password = databaseConfig.getPassword();
            Class.forName(driverName);

            freeConnections = new LinkedBlockingDeque<>(POOL_SIZE);
            busyConnections = new LinkedBlockingDeque<>();
            for (int i = 0; i < POOL_SIZE; i++) {
                Connection connection = DriverManager.getConnection(url, username, password);
                freeConnections.offer(new ProxyConnection(connection));
            }
        } catch (ClassNotFoundException | SQLException e) {
            logger.fatal("Cant register driver", e);
            throw new RuntimeException(e);
        }
    }

    public Connection getConnection() {
        return null;
    }
}
