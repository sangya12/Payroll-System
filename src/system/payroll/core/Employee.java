package system.payroll.core;

import java.math.BigDecimal;
import java.util.Date;

public class Employee
{
    public int id;
    public String name;
    public String address;
    public Date joiningDate;
    public BigDecimal salary; // https://stackoverflow.com/questions/3730019/why-not-use-double-or-float-to-represent-currency
    public String designation;
    public boolean accomodation;
    public boolean conveyance;
}
