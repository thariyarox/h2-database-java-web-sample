package com.wso2.sample;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Created by tharindu on 6/3/17.
 */
public class App {

    public static ArrayList<Student> getStudents() {

        ArrayList<Student> students = new ArrayList<Student>();

        Connection conn = DBCon.getConnection();

        Statement stmt = null;
        ResultSet rs = null;

        try {

            stmt = conn.createStatement();
            String sql = "SELECT id, name FROM student";
            rs = stmt.executeQuery(sql);

            //extract student records
            while (rs.next()) {
                //Retrieve by column name
                int id = rs.getInt("id");
                String name = rs.getString("name");

                //Create student object
                Student student = new Student(id, name);

                //Add the student to the list
                students.add(student);
            }

        } catch (java.sql.SQLException e) {

            System.out.println("Error occured : " + e.getMessage());

        } finally {

            //close result set
            if (rs != null) {
                try {
                    rs.close();
                } catch (java.sql.SQLException e) {
                    System.out.println("Error occured : " + e.getMessage());
                }
            }

            //close statement
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (java.sql.SQLException e) {
                    System.out.println("Error occured : " + e.getMessage());
                }
            }

            //close database connection
            DBCon.closeConnection();
        }

        return students;
    }
}
