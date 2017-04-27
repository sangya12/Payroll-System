
package system.payroll.main;

import java.util.List;
import java.util.Scanner;
import system.payroll.core.*;

public class PayrollManager
{

    private final DataStore dataStore;
    private final Scanner reader;
    
    PayrollManager(DataStore _dataStore)
    {
        dataStore = _dataStore;
        reader = new Scanner(System.in);
    }

    void start()
    {
        int choice;
        System.out.println("Manage Employees");
        System.out.println();
        do
        {
            System.out.println("1. Payslip for Specific Employee");
            System.out.println("2. Payslip for all");
            System.out.println("0. Exit");
            System.out.println("Enter your choice again:");
            choice = reader.nextInt();
            reader.nextLine();  // Consume newline left-over
            switch(choice)
            {
                case 1:
                    this.ShowPlaySlip();
                    break;
                case 2:
                    this.ShowPlaySlipForAll();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Wrong choice entered. Please enter correct choice only.");
            }
        }while(true);
    }

    private void ShowPlaySlipForAll()
    {
        List<Employee> employees = dataStore.Employees().GetAll();
        Deductions dedi = new Deductions();
        employees.forEach((emp) ->
        {
            System.out.println();
            emp.Payslip(dataStore.Salary().Get(emp.id), dedi);
        });
    }

    private void ShowPlaySlip()
    {
        System.out.println("Please enter employee id : ");
        Employee emp = dataStore.Employees().Get(reader.nextInt());
        reader.nextLine();
        if(emp == null)
        {
            System.out.println("Invalid Id");
        }
        else
        {
            Deductions dedi = new Deductions();
            emp.Payslip(dataStore.Salary().Get(emp.id), dedi);
        }
        
    }
    
    
    
}
