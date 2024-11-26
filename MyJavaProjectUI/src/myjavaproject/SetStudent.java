/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package myjavaproject;

import java.sql.SQLException;

/**
 *
 * @author Admin
 */
public class SetStudent extends Backend{
    String query;
    public SetStudent(
      String name,int batchno,String dob,String department,int year,
      int maths,int dpco,int fdsa,int oop,int daa,int economics
    ) throws SQLException, ClassNotFoundException{
        
        super();
       query = "INSERT INTO student(name, batchno, dob, department, year, maths, dpco, fdsa, oop, daa, economics) "
              + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        p1=c.prepareStatement(query);
        p1.setString(1,name);
        p1.setInt(2,batchno);
        p1.setString(3,dob);
        p1.setString(4,department);
        p1.setInt(5,year);
        p1.setInt(6,maths);
        p1.setInt(7,dpco);
        p1.setInt(8,fdsa);
        p1.setInt(9,oop);
        p1.setInt(10,daa);
        p1.setInt(11,economics);
        p1.executeUpdate();
        System.out.println("Database has been updated"); 
        MyJavaProject.showInimation("Successful","Your data has been Dumped successfully");
        }   
}
