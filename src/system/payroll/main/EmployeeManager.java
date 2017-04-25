package system.payroll.main;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.*;
import system.payroll.core.*;

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
            System.out.println("3. Update Employees");
            System.out.println("0. Exit");
            System.out.println("Enter your choice again:");
            choice = reader.nextInt();
            System.out.println(choice);
            reader.nextLine();  // Consume newline left-over
            switch(choice)
            {
                case 1:
                    this.EnterEmployees();
                    break;
                case 2:
                    this.ShowEmployees();
                    break;
                case 3:
                    this.UpdateEmployees();
                case 0:
                    return;
                default:
                    System.out.println("Wrong choice entered. Please enter correct choice only.");
            }
        }while(true);
    }

    private void EnterEmployees()
    {
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
            System.out.println(emp.id + " " + emp.name + " "+emp.address+" "+emp.designation+" "+emp.salary);
        }
    }
    private void UpdateEmployees()
    {
        ShowEmployees();
        System.out.println("Please Enter id of employee to change its values : ");
        int empId = reader.nextInt();
        Employee emp = dataStore.Employees().Get(empId); //get function not completed in data sore yet. Complete it too.
        Scanner sc =new Scanner(System.in);
        System.out.println("Enter name");
        String name= sc.next();
        System.out.println(name);
        System.out.println("Updating...");
        dataStore.Employees().update(emp); // update function is also not yet completed
        System.out.println("Done.");
    }        
}