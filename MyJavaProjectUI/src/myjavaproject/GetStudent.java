package myjavaproject;

import java.sql.ResultSet;
import java.sql.SQLException;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Admin
 */
public class GetStudent {
    int sno,batchnumber,year,maths,dpco,fdsa,daa,oop,economics;
    String name,dob,department;
    public GetStudent(ResultSet rsg) throws SQLException{
        if(rsg!=null){
            while(rsg.next()){
                name=rsg.getString("name");
                batchnumber=rsg.getInt("batchno");
                year=rsg.getInt("year");
                dob=rsg.getString("dob");
                department=rsg.getString("department");
                maths=rsg.getInt("maths");
                dpco=rsg.getInt("dpco");
                fdsa=rsg.getInt("fdsa");
                daa=rsg.getInt("daa");
                oop=rsg.getInt("oop");
                economics=rsg.getInt("economics");
        }
        }else{
            System.out.println("Resultset is null");
        }
    }
    public String getname(){
        return name;
    }
    public int getbatchno(){
        return batchnumber;
    }
    public int getyear(){
        return year;
    }
    public String getdepartment(){
        return department;
    }
    public String getdob(){
        return dob;
    }
    public int getmaths(){
        return maths;
    }
    public int getdpco(){
        return dpco;
    }
    public int getdaa(){
        return daa;
    }
    public int getfdsa(){
        return fdsa;
    }
    public int getoop(){
        return oop;
    }
    public int geteco(){
        return economics;
    }   
}
