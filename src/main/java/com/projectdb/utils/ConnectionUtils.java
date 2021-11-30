package com.projectdb.utils;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtils {

    private static final String URL = "jdbc:mysql://localhost/shop";
    private static final String USER = "root";
    private static final String PASSWORD = "root";
    private static Logger log = Logger.getLogger(ConnectionUtils.class);

    public static Connection openConnection() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        DOMConfigurator.configure("loggerConfig.xml");
        log.info("Establishing connection to database");
        Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
