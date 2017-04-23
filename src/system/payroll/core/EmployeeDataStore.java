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
    public boolean Add(Employee entity)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean Add(Iterable<Employee> entities)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
