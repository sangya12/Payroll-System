package system.payroll.core;

import java.sql.*;
import java.util.*;
import system.dbacess.*;

public class EmployeeDataStore implements DataStoreInterface<Employee>
{
    private final DBAccess dbase;
    
    public EmployeeDataStore(DBAccess _dbase)
    {
        dbase = _dbase;
    }

    @Override
    public int Add(Employee emp)
    {
        try
        (
            Connection conn = dbase.getConnection();
            PreparedStatement  st = conn.prepareStatement(EmpQueries.InsertQuery);
        )
        {
            st.setString(1, emp.name);
            st.setString(2, emp.address);
            st.setString(3, emp.designation);
            st.setDate(4, new java.sql.Date(emp.joiningDate.getTime())); // http://stackoverflow.com/a/530022
            st.setBoolean(5, emp.accomodation);
            st.setBoolean(6, emp.conveyance);
            int rowNums = st.executeUpdate();
            if( rowNums > 0)
            {
                ResultSet rs = st.getGeneratedKeys();
                rs.next();
                return rs.getInt(1);
            }
            return 0;
        }catch(Exception ex)
        {
            System.out.println(ex.getMessage());
            return 0;
        }
    }

    public boolean Add(Iterable<Employee> employees)
    {
        //https://stackoverflow.com/questions/4355046/java-insert-multiple-rows-into-mysql-with-preparedstatement
        try
        (
            Connection conn = dbase.getConnection();
            PreparedStatement  st = conn.prepareStatement(EmpQueries.InsertQuery);
        )
        {
            conn.setAutoCommit(false);
            for(Employee emp : employees )
            {
                //st.setInt(1, emp.id);
                st.setString(1, emp.name);
                st.setString(2, emp.address);
                st.setString(3, emp.designation);
                st.setDate(4, new java.sql.Date(emp.joiningDate.getTime()));
                st.setBoolean(5, emp.accomodation);
                st.setBoolean(6, emp.conveyance);
                st.addBatch();
            }
            st.executeBatch();
            conn.commit();
            conn.setAutoCommit(true);
            return true;
        }catch(Exception ex)
        {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    @Override
    public Employee Get(int id)
    {
        try
        (
            Connection conn = dbase.getConnection();
            PreparedStatement  st = conn.prepareStatement(EmpQueries.GetQuery);
        )
        {
        st.setInt(1, id);
        ResultSet rs = st.executeQuery();
        return FillEmployeeListByResultSet(rs).get(0);
        }catch(Exception ex)
        {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    @Override
    public List<Employee> GetAll()
    {
        try
        {
            ResultSet rs = dbase.ExecuteQuery(EmpQueries.GetAllQuery);
            return FillEmployeeListByResultSet(rs);
        } catch (SQLException ex)
        {
            System.out.println(ex.getMessage());
        }
        return new ArrayList<>();
    }

    @Override
    public boolean update(Employee emp)
    {
        try
        (
            Connection conn = dbase.getConnection();
            PreparedStatement  st = conn.prepareStatement(EmpQueries.updateQuery);
        )
        {
            st.setString(1, emp.name);
            st.setString(2, emp.address);
            st.setString(3, emp.designation);
            st.setDate(4, new java.sql.Date(emp.joiningDate.getTime()));
            st.setBoolean(5, emp.accomodation);
            st.setBoolean(6, emp.conveyance);
            st.setInt(8, emp.id);
            
            st.executeUpdate();            
            return true;
        }catch(Exception ex)
        {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    @Override
    public boolean remove(Employee entity)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private List<Employee> FillEmployeeListByResultSet(ResultSet rs) throws SQLException
    {
        List<Employee> employees = new ArrayList<>();
        while(rs.next())
            {
                Employee emp = new Employee();
                emp.id = rs.getInt("id");
                emp.name = rs.getString("name");
                emp.address = rs.getString("address");
                emp.designation = rs.getString("designation");
                emp.joiningDate = rs.getDate("joiningDate");
                emp.accomodation = rs.getBoolean("accomodation");
                emp.conveyance = rs.getBoolean("connviance");
                employees.add(emp);
            }
        return employees;
    }
}
