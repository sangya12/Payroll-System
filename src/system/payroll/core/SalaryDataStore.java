package system.payroll.core;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import system.dbacess.DBAccess;

public class SalaryDataStore implements DataStoreInterface<Additions>
{

    private final DBAccess dbase;
    SalaryDataStore(DBAccess _dbase)
    {
        dbase = _dbase;
    }

    @Override
    public int Add(Additions entity)
    {
        try
        (
            Connection conn = dbase.getConnection();
            PreparedStatement  st = conn.prepareStatement(SalaryQueries.InsertQuery);
        )
        {
            st.setInt(1, entity.id);
            st.setBigDecimal(2, entity.basicSalary);
            st.setBigDecimal(3, entity.HRA);
            st.setBigDecimal(4, entity.DA);
            st.setBigDecimal(5, entity.MA);
            st.executeUpdate();
            return 1;
        }catch(Exception ex)
        {
            System.out.println(ex.getMessage());
            return 0;
        }
    }

    @Override
    public Additions Get(int id)
    {
        try
        (
            Connection conn = dbase.getConnection();
            PreparedStatement  st = conn.prepareStatement(SalaryQueries.GetQuery);
        )
        {
        st.setInt(1, id);
        ResultSet rs = st.executeQuery();
        System.out.println(EmpQueries.GetQuery);
        return FillAdditionsListByResultSet(rs).get(0);
        }catch(Exception ex)
        {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    @Override
    public List<Additions> GetAll()
    {
        try
        {
            ResultSet rs = dbase.ExecuteQuery(SalaryQueries.GetAllQuery);
            return FillAdditionsListByResultSet(rs);
        } catch (SQLException ex)
        {
            System.out.println(ex.getMessage());
        }
        return new ArrayList<>();
    }

    @Override
    public boolean update(Additions entity)
    {
        try
        (
            Connection conn = dbase.getConnection();
            PreparedStatement  st = conn.prepareStatement(SalaryQueries.updateQuery);
        )
        {
            st.setBigDecimal(1, entity.basicSalary);
            st.setBigDecimal(2, entity.HRA);
            st.setBigDecimal(3, entity.DA);
            st.setBigDecimal(4, entity.MA);
            st.setInt(5, entity.id);
            
            st.executeUpdate();            
            return true;
        }catch(Exception ex)
        {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    @Override
    public boolean remove(Additions entity)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private List<Additions> FillAdditionsListByResultSet(ResultSet rs) throws SQLException
    {
        List<Additions> additions = new ArrayList<>();
        while(rs.next())
            {
                Additions addi = new Additions();
                addi.id = rs.getInt("ID");
                addi.basicSalary = rs.getBigDecimal("basicsalary");
                addi.HRA = rs.getBigDecimal("hra");
                addi.DA = rs.getBigDecimal("da");
                addi.MA = rs.getBigDecimal("ma");
                additions.add(addi);
            }
        return additions;
    }
    
}
