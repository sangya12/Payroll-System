package system.payroll.main;

import java.math.BigDecimal;
import java.util.*;
import system.dbacess.DBAccess;
import system.payroll.core.*;

public class PayrollSystem {
    static DBAccess dbase;
    static DataStore dataStore;

    public static void main(String[] args)
    {
        //initializing
        
        try
        {
            dbase = new DBAccess("data/db.mdb");
        } catch (ClassNotFoundException ex)
        {
            System.out.println("Error in loading database");
            return;
        }
        dataStore = new DataStore(dbase);
        //initialization finished
        //Employee emp = dataStore.Employees().Get(8);
        //emp.Payslip(dataStore.Salary().Get(1), (new Deductions()));
        PayrollSystem.start();
        
    }

    private static void start()
    {
        Scanner reader = new Scanner(System.in);
        int choice;
        do
        {
            System.out.println("Payroll Manager");
            System.out.println();
            System.out.println("1. Manage Employees");
            System.out.println("2. Payroll");
            System.out.println("0. Exit");
            System.out.println("Enter youor choice:");
            choice = reader.nextInt();
            reader.nextLine();
            //System.out.println(choice);
            switch(choice)
            {
                case 1:
                    EmployeeManager empManager = new EmployeeManager(dataStore);
                    empManager.start();
                    break;
                case 2:
                    PayrollManager payrollManager = new PayrollManager(dataStore);
                    payrollManager.start();
                case 0:
                    return;
                default:
                    System.out.println("Wrong choice entered. Please enter correct choice only.");
            }
        }while(true);
    }
    
    
}
