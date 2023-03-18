import com.mysql.jdbc.Connection;
import com.mysql.jdbc.ResultSet;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Clock;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) throws SQLException{

        Scanner sc = new Scanner(System.in);
        Student stud = new Student();
        Teacher teacher=new Teacher();
        Connection con= null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/studentteacher", "root", "");
            if (con != null) {
                System.out.println("Connected");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        int ch = 0, op = 0;
        do {
            Statement stmt;
            switch (op) {
                case 1:
                    do {
                        stud.setRollno(sc.nextInt());
                        stud.setName(sc.next());
                        stud.setEmail(sc.next());
                        System.out.println(stud.getRollno());
                        System.out.println(stud.getName());
                        System.out.println(stud.getEmail());
                        stmt = con.createStatement();
                        stmt.executeUpdate("insert into student values('" + stud.getRollno() + "','" + stud.getName() + "','" + stud.getEmail() + "')");
                        System.out.println("Data inserted successfully......");
                        System.out.println("Do you want to continue?");
                        ch = sc.nextInt();
                    } while (ch == 1);
                    break;

                case 2:
                    try {

                        stmt = con.createStatement();
                        stmt.executeQuery("select * from student");
                        ResultSet rs = (ResultSet) stmt.getResultSet();
                        while (rs.next()) {
                            System.out.print(rs.getString("rno"));
                            System.out.print(rs.getString("name"));
                            System.out.print(rs.getString("email"));
                            System.out.println("\n");
                        }
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                    break;

                case 3:
                    stud.setRollno(sc.nextInt());
                    stud.setName(sc.next());
                    stud.setEmail(sc.next());
                    try{
                        stmt = con.createStatement();
                        stmt.executeUpdate("update student set name='"+stud.getName()+"',email='"+stud.getEmail()+"' where rno='"+stud.getRollno()+"'");
                        System.out.println("Your record updated successfully.....");

                    }
                    catch (Exception e)
                    {
                        System.out.println(e);
                    }
                    break;


                case 4:
                    stud.setRollno(sc.nextInt());
                    try{
                        stmt = con.createStatement();
                        stmt.executeUpdate("delete from student where rno='"+stud.getRollno()+"'");
                        System.out.println("Your record deleted successfully.....");

                    }
                    catch (Exception e)
                    {
                        System.out.println(e);
                    }
                    break;

                case 5:
                    do {
                        teacher.setId(sc.nextInt());
                        teacher.setT_name(sc.next());
                        teacher.setT_email(sc.next());
                        System.out.println(teacher.getId());
                        System.out.println(teacher.getT_name());
                        System.out.println(teacher.getT_email());
                        stmt = con.createStatement();
                        stmt.executeUpdate("insert into teacher values('" + teacher.getId() + "','" + teacher.getT_name() + "','" + teacher.getT_email() + "')");
                        System.out.println("Data inserted successfully......");
                        System.out.println("Do you want to continue?");
                        ch = sc.nextInt();
                    } while (ch == 5);
                    break;

                case 6:
                    try {
                        stmt = con.createStatement();
                        stmt.executeQuery("select * from teacher");
                        ResultSet rs = (ResultSet) stmt.getResultSet();
                        while (rs.next()) {
                            System.out.print(rs.getString("t_id"));
                            System.out.print(rs.getString("t_name"));
                            System.out.print(rs.getString("t_email"));
                            System.out.println("\n");
                        }
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                    break;

                case 7:
                    teacher.setId(sc.nextInt());
                    teacher.setT_name(sc.next());
                    teacher.setT_email(sc.next());
                    try{
                        stmt = con.createStatement();
                        stmt.executeUpdate("update teacher set t_name='"+teacher.getT_name()+"',t_email='"+teacher.getT_email()+"' where t_id='"+teacher.getId()+"'");
                        System.out.println("Your record updated successfully.....");

                    }
                    catch (Exception e)
                    {
                        System.out.println(e);
                    }
                    break;

                case 8:
                    teacher.setId(sc.nextInt());
                    try{
                        stmt = con.createStatement();
                        stmt.executeUpdate("delete from teacher where t_id='"+teacher.getId()+"'");
                        System.out.println("Your record deleted successfully.....");

                    }
                    catch (Exception e)
                    {
                        System.out.println(e);
                    }
                    break;

            }
            System.out.println("Enter Your choice: ");
            System.out.println("1:InsertStud  2:DisplayStud 3:UpdateStud 4:DeleteStud 5:InsertTeacher 6:DisplayTeacher 7:UpdateTeacher 8:DeleteTeacher");
            op = sc.nextInt();
        }while (op != 0) ;
    }
}