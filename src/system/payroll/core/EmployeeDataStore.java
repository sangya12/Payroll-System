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
    public boolean Add(Employee emp)
    {
        List<Employee> emps = new ArrayList<>();
        emps.add(emp);
        return Add(emps);
    }

    @Override
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
                st.setDate(4, new java.sql.Date(emp.joiningDate.getTime())); // http://stackoverflow.com/a/530022
                st.setBigDecimal(5, emp.salary);
                st.setBoolean(6, emp.accomodation);
                st.setBoolean(7, emp.conveyance);
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Employee> GetAll()
    {
        List<Employee> employees = new ArrayList<>();
        try
        {
            ResultSet rs = dbase.ExecuteQuery(EmpQueries.GetAllQuery);
            while(rs.next())
            {
                Employee emp = new Employee();
                emp.id = rs.getInt("id");
                emp.name = rs.getString("name");
                emp.address = rs.getString("address");
                emp.designation = rs.getString("designation");
                emp.joiningDate = rs.getDate("joiningDate");
                emp.salary = rs.getBigDecimal("salary");
                emp.accomodation = rs.getBoolean("accomodation");
                emp.conveyance = rs.getBoolean("connviance");
                employees.add(emp);
            }
        } catch (SQLException ex)
        {
            System.out.println(ex.getMessage());
        }
        return employees;
    }

    @Override
    public List<Employee> GetRange(int from, int to)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(Employee entity)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean remove(Employee entity)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
