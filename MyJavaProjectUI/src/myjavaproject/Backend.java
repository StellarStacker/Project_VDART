package myjavaproject;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Admin
 */
import java.sql.*;
import java.time.LocalDateTime;


public class Backend {
    Connection c=null;
    Statement st;
    ResultSet rs;
    GetStudent ob;
    PreparedStatement p1;
    public Backend() throws ClassNotFoundException,SQLException{
          System.out.println("Connection going to be established");
          Class.forName("org.sqlite.JDBC");
          c=DriverManager.getConnection("jdbc:sqlite:src/database/Project");
          System.out.println("Connection established");
    }
    public void getdata(int batchno) throws SQLException{
        ResultSet rs=null;
        String qurey="Select * from student where batchno=?";
        PreparedStatement ps=c.prepareStatement(qurey);
        ps.setInt(1,batchno);
        rs=ps.executeQuery();
        GetStudent ob=new GetStudent(rs);
        this.ob=ob;
    }
    public void getlogintime(){
        //System.out.println(java.time.LocalDateTime.now());
        System.out.println(java.time.LocalDateTime.now().toString());
        //System.out.println(time);
     }
    public GetStudent getobject(){
        return ob;
    }
 }
