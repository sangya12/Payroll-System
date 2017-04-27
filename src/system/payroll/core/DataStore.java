package system.payroll.core;

import system.dbacess.*;

public class DataStore
{
    private final EmployeeDataStore _Employees;
    private final SalaryDataStore _Salaries;
    
    public DataStore(DBAccess dbase)
    {
        _Employees = new EmployeeDataStore(dbase);
        _Salaries = new SalaryDataStore(dbase);
    }
    
    public EmployeeDataStore Employees()
    {
        return this._Employees;
    }

    public SalaryDataStore Salary()
    {
        return this._Salaries;
    }
    
}
