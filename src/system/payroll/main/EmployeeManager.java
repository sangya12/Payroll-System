package system.payroll.main;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import system.payroll.core.DataStore;
import system.payroll.core.Employee;

public class EmployeeManager
{
    private final DataStore dataStore;
    private final Scanner reader;
    public EmployeeManager(DataStore _dataStore)
    {
        dataStore = _dataStore;
        reader = new Scanner(System.in);
    }
    
    public void start()
    {
        //possibly for future https://stackoverflow.com/questions/26446599/how-to-use-java-util-scanner-to-correctly-read-user-input-from-system-in-and-act
        int choice;
        System.out.println("Manage Employees");
        System.out.println();
        do
        {
            System.out.println("1. Enter new Employee");
            System.out.println("2. View all Employees");
            System.out.println("0. Exit");
            choice = reader.nextInt();
            switch(choice)
            {
                case 1:
                    this.EnterEmployees();
                    break;
                case 2:
                    this.ShowEmployees();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Wrong choice entered. Please enter correct choice only.");
            }
        }while(true);
    }

    private void EnterEmployees()
    {
        reader.nextLine();  // Consume newline left-over
        Employee emp = new Employee();
        System.out.print("Enter Employee Name : ");
        emp.name = reader.nextLine();
        System.out.print("Enter Employee Address : ");
        emp.address = reader.nextLine();
        System.out.print("Enter Employee Designation : ");
        emp.designation = reader.nextLine();
        do
        {
            System.out.print("Enter Employee Joining Date(dd/mm/yyyy) : ");
            try
            {
                emp.joiningDate = new SimpleDateFormat("dd/MM/yyyy").parse(reader.nextLine()); //http://stackoverflow.com/a/23491400
                break;
            } catch (ParseException ex)
            {
                System.out.println("Wrong date format.Please Enter Again");
            }
        }while(true);
        System.out.print("Enter Employee Salary : ");
        emp.salary = reader.nextBigDecimal();
        reader.nextLine();  // Consume newline left-over
        System.out.print("Is Accomodation provided(y/n) : ");
        emp.accomodation = "y".equals(reader.nextLine());
        System.out.print("Is conveyance provided(y/n) : ");
        emp.conveyance = "y".equals(reader.nextLine());
        
        System.out.println("Saving...");
        dataStore.Employees().Add(emp);
        System.out.println("Done.");
    }

    private void ShowEmployees()
    {
        System.out.println("Printing all Employees");
        List<Employee> employees = dataStore.Employees().GetAll();
        for(Employee emp : employees )
        {
            System.out.println(emp.name + " " + emp.salary);
        }
    }
}
