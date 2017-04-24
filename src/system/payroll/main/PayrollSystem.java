package system.payroll.main;

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
            System.out.println("2. Payroll(Under Development)");
            System.out.println("0. Exit");
            choice = reader.nextInt();
            switch(choice)
            {
                case 1:
                    EmployeeManager empManager = new EmployeeManager(dataStore);
                    empManager.start();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Wrong choice entered. Please enter correct choice only.");
            }
        }while(true);
    }
    
    
}
