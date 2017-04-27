package system.payroll.core;

import java.math.BigDecimal;
import java.util.Date;

public class Employee
{
    public int id;
    public String name;
    public String address;
    public Date joiningDate;
    public String designation;
    public boolean accomodation;
    public boolean conveyance;
    
    public void Display()
    {
        System.out.println("Id : " + this.id + " Name : " + this.name);
        System.out.println(this.designation + " Joining Date : " + this.joiningDate);
    }
    
    public void Display(boolean showdetailed)
    {
        Display();
        if(showdetailed)
        {
            System.out.println(address);
            System.out.println("Accomodation : " +(this.accomodation?"Yes":"NO") + " Conveyance : " +(this.conveyance?"Yes":"NO"));
        }
    }
    
    public void Payslip(Additions addi, Deductions dedi)
    {
        Display();
        BigDecimal sal = addi.basicSalary;
        
        System.out.println("Basic Salary      : " + sal);
        
        BigDecimal taxAmount = dedi.TaxRate.multiply(sal).divide(new BigDecimal(100));
        sal = sal.subtract(taxAmount);
        System.out.println("Tax Amount("+dedi.TaxRate+"%) : " + taxAmount);
        
        System.out.println("HRA               : " + addi.HRA);
        sal = sal.add(addi.HRA);
        
        System.out.println("DA                : " + addi.DA);
        sal = sal.add(addi.DA);
        
        System.out.println("Medical Allowance : " + addi.MA);
        sal = sal.add(addi.MA);
        
        System.out.println("Total Pay         : Rs." + sal);
    }
}
