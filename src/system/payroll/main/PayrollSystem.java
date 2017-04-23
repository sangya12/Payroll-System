package system.payroll.main;

import java.util.*;
import system.dbacess.DBAccess;
import system.payroll.core.*;

public class PayrollSystem {
    public static void main(String[] args)
    {
        DBAccess dbase;
        try
        {
            dbase = new DBAccess("data/db.mdb");
        } catch (ClassNotFoundException ex)
        {
            System.out.println("Error in loading database");
            return;
        }
        DataStore dataStore = new DataStore(dbase);
        
        System.out.println("Printing all Employees");
        List<Employee> employees = dataStore.Employees().GetAll();
        for(Employee emp : employees )
        {
            System.out.println(emp.name + " " + emp.salary);
        }
    }
}
