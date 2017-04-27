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
            //System.out.println(choice);
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
        
        reader.nextLine();  // Consume newline left-over
        System.out.print("Is Accomodation provided(y/n) : ");
        emp.accomodation = "y".equals(reader.nextLine());
        System.out.print("Is conveyance provided(y/n) : ");
        emp.conveyance = "y".equals(reader.nextLine());
        
        Additions addi = new Additions();
        System.out.print("Enter Employee Base Salary : ");
        addi.basicSalary = reader.nextBigDecimal();
        System.out.print("Enter HRA : ");
        addi.HRA = reader.nextBigDecimal();
        System.out.print("Enter DA : ");
        addi.DA = reader.nextBigDecimal();
        System.out.print("Enter Medical Allowance : ");
        addi.MA = reader.nextBigDecimal();
        
        
        System.out.println("Saving...");
        int id = dataStore.Employees().Add(emp);
        addi.id = id;
        dataStore.Salary().Add(addi);
        System.out.println("Id of new employee is " + id);
        System.out.println("Done.");
    }

    
    private List<Employee> ShowEmployees()
    {
        System.out.println("Printing all Employees");
        List<Employee> employees = dataStore.Employees().GetAll();
        employees.forEach((emp) ->
        {
            System.out.println();
            emp.Display(true);
        });
        return employees;
    }
    
    private void UpdateEmployees()
    {
        ShowEmployees();
        System.out.println("Please Enter id of employee to change its values : ");
        
        int empId = reader.nextInt(); reader.nextLine();
        
        Employee emp = dataStore.Employees().Get(empId);
        
        String temp;
        
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
        
        System.out.print("Is Accomodation provided("+(emp.accomodation?"Yes":"NO")+")(y/n) : ");
        temp = reader.nextLine();
        emp.accomodation = temp.equals("") ? emp.accomodation : temp.equals("y");
        
        System.out.print("Is conveyance provided("+(emp.conveyance?"Yes":"NO")+")(y/n) : ");
        temp = reader.nextLine();
        emp.accomodation = temp.equals("") ? emp.conveyance : temp.equals("y");
        
        Additions addi = dataStore.Salary().Get(emp.id);
        System.out.print("Enter Employee Base Salary ("+addi.basicSalary+") : ");
        temp = reader.nextLine();
        addi.basicSalary = temp.equals("") ?  addi.basicSalary : (new BigDecimal(temp));
        System.out.print("Enter HRA ("+addi.HRA+")  : ");
        temp = reader.nextLine();
        addi.HRA = temp.equals("") ?  addi.HRA : (new BigDecimal(temp));
        System.out.print("Enter DA ("+addi.DA+")  : ");
        temp = reader.nextLine();
        addi.DA = temp.equals("") ?  addi.DA : (new BigDecimal(temp));
        System.out.print("Enter Medical Allowance ("+addi.MA+")  : ");
        temp = reader.nextLine();
        addi.MA = temp.equals("") ?  addi.MA : (new BigDecimal(temp));
        
        System.out.println("Updating...");
        emp.Display(true);
        dataStore.Employees().update(emp);
        dataStore.Salary().update(addi);
        System.out.println("Done.");
    }        
}