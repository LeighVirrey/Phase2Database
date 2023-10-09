/**
 * @author lvirrey
 * @createdOn 10/4/2023 at 4:11 PM
 * @projectName EmployeeDatabase
 * @packageName PACKAGE_NAME;
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class UI {
    //I use a Console class that was given to OOP students by Brett Beardall, I'm pretty sure Beardall created it.
    //I use it because it's a simpler way to write the console things instead of just blasting my UI class with a ton of lines dedicated to getting an int input.
    public static int Menu(){
        Line();
        System.out.println("Database of Employees");
        return Console.getIntInput("""
                1. Unstructured Employee List from /simple/
                2. Structured Employee List from /simple/
                3. Create an Employee to /long/
                4. Update an Employee from /long/
                5. Delete an Employee from /long/
                6. Exit
                Enter a number corresponding to the command
                """);
    }
    public static List<String> createList(){
        Line();
        List<String> employee = new ArrayList<>();
        employee.add(String.valueOf(Console.getIntInput("What is the ID of the Employee?")));
        employee.add(Console.getStringInput("What is the First Name of the Employee"));
        employee.add(Console.getStringInput("What is the Last name of the Employee"));
        employee.add(String.valueOf(Console.getIntInput("What is the Hire Year of the Employee")));
        return employee;
    }
    public static int findID(){
        Line();
        return Console.getIntInput("What is the ID of the Employee?");
    }
    public static void Line(){
        System.out.println("----------------------------------------------");
    }
}
