package system.payroll.core;

import system.dbacess.*;

public class DataStore
{
    private final EmployeeDataStore _Employees;
    
    public DataStore(DBAccess dbase)
    {
        _Employees = new EmployeeDataStore(dbase);
    }
    
    public EmployeeDataStore Employees()
    {
        return this._Employees;
    }
    
}
