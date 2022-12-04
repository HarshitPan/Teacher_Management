import java.sql.*;
public class Create_temp {
    public static void main(String[] args){
        try{
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306","root","root");
        Statement stmt=con.createStatement();
        stmt.executeUpdate("create database teachers;");
        stmt.executeUpdate("use teachers;");
        stmt.executeUpdate("create table Informatics(teacher_id int,teacher_name varchar(30),contact varchar(10),PRIMARY KEY(teacher_id));");
        stmt.executeUpdate("create table Cybernatics(teacher_id int,teacher_name varchar(30),contact varchar(10),PRIMARY KEY(teacher_id));");
        System.out.println("Successfully...");
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
}
