package payroll.system;

import java.io.File;
import java.sql.*;

public class PayrollSystem {
    public static void main(String[] args) throws SQLException 
    {
        try 
        {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            
            System.out.println("You can access the MSACCESS db");
            
            File dbFile = new File("data/db.mdb");
            String path = dbFile.getAbsolutePath();
            Connection conn=DriverManager.getConnection("jdbc:ucanaccess://"+path);
            Statement st = conn.createStatement();
            ResultSet rs=st.executeQuery("SELECT * FROM employees");
            rs.next();
            System.out.println(rs.getString(1));
        } catch (ClassNotFoundException cnfe) {
            System.out.println("Error: You can not access the MSACCESS db");
        }
    }
}
