package com.wso2.sample;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Created by tharindu on 6/3/17.
 * Thread safe singleton class for obtaining database connection
 */
public class DBCon {

    //connection is made volatile to avoid thread caching
    private static volatile Connection conn = null;

    private DBCon() {
        //constructor is made private to avoid instantiation outside the class
    }

    public static Connection getConnection() {

        if (conn == null) {

            synchronized (DBCon.class) {
                //doble checked locking

                if (conn == null) {

                    try {

                        Class.forName("org.h2.Driver");

                        conn = DriverManager.getConnection("jdbc:h2:tcp://127.0.1.1:9595/~/mydatabase", "sa", "");

                    } catch (java.lang.ClassNotFoundException e) {

                        System.out.println("Error occured : " + e.getMessage());

                    } catch (java.sql.SQLException e) {

                        System.out.println("Error occured : " + e.getMessage());

                    } catch (Exception e) {
                        System.out.println("Error occured : " + e.getMessage());
                    }
                }
            }
        }

        return conn;
    }

    public static void closeConnection() {

        if (conn != null) {

            try {

                conn.close();
                conn = null;

            } catch (java.sql.SQLException e) {

                System.out.println("Error occured : " + e.getMessage());

            } catch (Exception e) {

                System.out.println("Error occured : " + e.getMessage());

            }
        }

    }
}
