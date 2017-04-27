package system.payroll.core;

public class EmpQueries
{
    public final static String GetAllQuery = "Select * from Employees";
    public final static String InsertQuery = "Insert into Employees( name, address, designation, joiningDate, accomodation, connviance) values (?,?,?,?,?,?) ";
    public final static String GetQuery = GetAllQuery + " where id = ?";
    public final static String updateQuery = "Update Employees SET name=?, address=?, designation=?, joiningDate=?, accomodation=?, connviance=? WHERE id=?";
}
