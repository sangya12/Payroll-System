package system.payroll.core;

public class EmpQueries
{
    public final static String GetAllQuery = "Select * from Employees";
    public final static String InsertQuery = "Insert into Employees( name, address, designation, joiningDate, salary, accomodation, connviance) values (?,?,?,?,?,?,?) ";
}
