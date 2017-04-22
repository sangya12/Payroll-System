package payroll.system;

public class PayrollSystem {
    public static void main(String[] args) 
    {
        System.out.println("Hello World!");
        try 
        {
		Class.forName("com.mysql.jdbc.Driver");
                System.out.println("Hello World!");
	} catch (ClassNotFoundException e) 
        {
		System.out.println("Where is your MySQL JDBC Driver?");
	}
    }
}
