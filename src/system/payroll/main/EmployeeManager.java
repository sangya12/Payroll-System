package system.payroll.main;

import java.math.BigDecimal;
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

    
    private List<Employee> ShowEmployees()
    {
        System.out.println("Printing all Employees");
        List<Employee> employees = dataStore.Employees().GetAll();
        employees.forEach((emp) ->
        {
            System.out.println(emp.id + " " + emp.name + " "+emp.address+" "+emp.designation+" "+emp.salary);
        });
        return employees;
    }
    
    
    
    
    private void UpdateEmployees()
    {
        ShowEmployees();
        System.out.println("Please Enter id of employee to change its values : ");
        
        int empId = reader.nextInt(); reader.nextLine(); // next line is just for consuming enter. nextInt does not consume enter.
        
        //fetching employee from databse using provided id
        Employee emp = dataStore.Employees().Get(empId);
        
        // No need to create new scanner. it is already available as reader. see above at the beginning of this class.
        String temp;
        
        //Brackets will show old values. Change values only if new values are entered otherwise keep old value
        System.out.println("Press just enter to keep old value.");
        
        // Entering Name
        System.out.println("Enter Employee Name ("+emp.name+") : ");
        temp = reader.nextLine();
        emp.name = temp.equals("") ?  emp.name : temp; // pressing just enter will keep the old value
        System.out.println("Enter Employee Address ("+emp.address+") : ");
        temp = reader.nextLine();
        emp.address = temp.equals("") ? emp.address : temp;
        System.out.println("Enter Employee Designation ("+emp.designation+") : "); 
        temp = reader.nextLine();
        emp.designation = temp.equals("") ? emp.designation : temp;
        //Entering joining Date
        do
        {
            System.out.print("Enter Employee Joining Date(dd/mm/yyyy) : ");
            try
            {
                temp = reader.nextLine();
                emp.joiningDate = temp.equals("") ?  emp.joiningDate : new SimpleDateFormat("dd/MM/yyyy").parse(temp);
                break;
            } catch (ParseException ex)
            {
                System.out.println("Wrong date format.Please Enter Again");
            }
        }while(true);
        
        //Entering Employee Salary
        System.out.print("Enter Employee Salary ("+emp.salary+") : ");
        temp = reader.nextLine();
        emp.salary = temp.equals("") ?  emp.salary : (new BigDecimal(temp)); //http://stackoverflow.com/a/28784180
        System.out.println("Is accomodation provided?(y/n) ("+emp.accomodation+") : ");
        temp = reader.nextLine();
        //emp.accomodation = temp.equals("") ? emp.accomodation : temp;
        System.out.println("Is conveyance provided?(y/n) ("+emp.conveyance+") : ");
        temp = reader.nextLine();
       // emp.conveyance = temp.equals("") ? emp.conveyance : new boolean("n").parse(temp);
        System.out.println("Updating...");
        dataStore.Employees().update(emp);// update function is also not yet completed
        System.out.println("Done.");
    }        
}