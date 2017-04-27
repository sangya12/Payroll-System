package system.payroll.core;

public class SalaryQueries
{

    public final static String InsertQuery = "Insert into salary( ID, basicsalary, hra, da, ma) values (?,?,?,?,?)";
    public final static String GetAllQuery = "Select * from salary";
    public final static String GetQuery = GetAllQuery + " where ID = ?";
    public final static String updateQuery = "Update salary SET basicsalary=?, hra=?, da=?, ma=? WHERE id=?";
}