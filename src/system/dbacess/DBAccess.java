
package system.dbacess;

import java.io.File;
import java.sql.*;

public class DBAccess
{
    Connection con;
    String dbPath;
    
    public DBAccess(String dbasePath) throws ClassNotFoundException
    {
        try
        {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
        }
        catch(ClassNotFoundException ex)
        {
            System.out.println("Error in loading db.");
            throw ex;
        }
        File dbFile = new File(dbasePath);
        dbPath = dbFile.getAbsolutePath();
    }
    
    public ResultSet ExecuteQuery(String query) throws SQLException
    {
        try(
                Connection conn = DriverManager.getConnection("jdbc:ucanaccess://"+dbPath);
                Statement st = conn.createStatement();
            )
        {            
            return st.executeQuery(query);
            
        } catch (SQLException ex)
        {
            throw ex;
        }
    }

    public Connection getConnection() throws SQLException
    {
        return DriverManager.getConnection("jdbc:ucanaccess://"+dbPath);
    }
}
