import java.sql.*;
import java.util.Scanner;

class Teachers
{
    Connection con;
    Statement stmt;
    String teacher_name,contact;
    int teacher_id;
    static Scanner sc=new Scanner(System.in);
    Teachers() throws Exception
    {
        con=DriverManager.getConnection("jdbc:mysql://localhost:3306/teachers","harshit","Harsh@9977");
        stmt=con.createStatement();
    }
}
class Department extends Teachers{
    String dep_name[]={"Informatics","Cybernatics"};
    int dept;
    Department() throws Exception
    {
    }
    void insert() throws Exception
    {
        System.out.print("Enter Teacher Name ->");
        teacher_name=sc.nextLine();
        System.out.print("Enter Teacher ID ->");
        teacher_id=sc.nextInt();
        sc.nextLine();
        System.out.print("Enter Contact No. ->");
        contact=sc.nextLine();
        System.out.println("Select Department -:");
        for(int i=0;i<dep_name.length;i++)
            System.out.println((i+1)+". "+dep_name[i]);
        System.out.print("Enter Your Choice ->");
        dept=sc.nextInt();
        sc.nextLine();
        dept--;
        if(dept<0 || dept>1)
        {
            System.out.println("Invalid Choice....");
            return;
        }
        stmt.executeUpdate("insert into "+dep_name[dept]+" values("+teacher_id+",'"+teacher_name+"','"+contact+"');");
        System.out.println("Value Inserted...");
    }
    void delete() throws Exception
    {
        System.out.print("Enter Teacher ID ->");
        teacher_id=sc.nextInt();
        sc.nextLine();
        for(int i=0;i<dep_name.length;i++)
        {
            int check=stmt.executeUpdate("delete from "+dep_name[i]+" where teacher_id="+teacher_id+";");
            if(check>0)
            {
                System.out.println("Deleted Successfully....");
                return;
            }
        }
        System.out.println("Teacher_ID not found...");
    }
    void update() throws Exception{
        System.out.print("Enter Teacher_ID ->");
        teacher_id=sc.nextInt();
        sc.nextLine();
        System.out.print("Enter new Contact ->");
        contact=sc.nextLine();
        for(int i=0;i<dep_name.length;i++)
        {
            int check=stmt.executeUpdate("update "+dep_name[i]+" set contact='"+contact+"' where teacher_id="+teacher_id+";");
            if(check>0)
            {
                System.out.println("Updated Successfully...");
                return;
            }
        }
        System.out.println("Teacher ID not found....");
    }
    void display() throws Exception
    {
        System.out.println("Details-:");
        for(int i=0;i<dep_name.length;i++)
        {
            ResultSet rs=stmt.executeQuery("select * from "+dep_name[i]+";");
            while(rs.next())
            {
                System.out.println(rs.getInt(1)+" - "+rs.getString(2)+" - "+rs.getString(3)+" - "+dep_name[i]);
            }
        }
    }
    static void mainmenu()
    {
        System.out.println("    Main - Menu ");
        System.out.println("1. Add New Teacher");
        System.out.println("2. Remove Teacher");
        System.out.println("3. Update Contact");
        System.out.println("4. Display Details");
        System.out.println("5. Exit");
        System.out.print("Enter your Choice ->");
    }
}
class Main{
    public static void main(String[] args) {
        try{
            Department dept=new Department();
            while(true)
            {
                try{
                    Department.mainmenu();
                    int choice=Teachers.sc.nextInt();
                    Teachers.sc.nextLine();
                    switch(choice)   
                    {
                        case 1:
                            dept.insert();
                            break;
                        case 2:
                            dept.delete();
                            break;
                        case 3:
                            dept.update();
                            break;
                        case 4:
                            dept.display();
                            break;
                        case 5:
                            return;
                        default:
                            System.out.println("Invalid Choice...");
                    }
                }
                catch(Exception e)
                {
                    System.out.println("Error Occured...");
                    System.out.println(e.getMessage());
                }
                System.out.println("Enter any key to continue...");
                Teachers.sc.nextLine();
            }
        }
        catch(Exception e)
        {
            System.out.println("Error Occured...");
            System.out.println(e.getMessage());
        }   
    }
}