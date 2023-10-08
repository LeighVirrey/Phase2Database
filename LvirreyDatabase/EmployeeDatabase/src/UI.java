/**
 * @author lvirrey
 * @createdOn 10/4/2023 at 4:11 PM
 * @projectName EmployeeDatabase
 * @packageName PACKAGE_NAME;
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
public class UI {
    //I use a Console class that was given to OOP students by Brett Beardall, I'm pretty sure Beardall created it.
    //I use it because it's a simpler way to write the console things instead of just blasting my UI class with a ton of lines dedicated to getting an int input.
    public static int Menu(){
        Line();
        System.out.println("Database of Employees");
        return Console.getIntInput("""
                1. Unstructured Employee List
                2. Structured Employee List
                3. Exit
                Enter a number corresponding to the command
                """);
    }
    public static void Line(){
        System.out.println("----------------------------------------------");
    }
}
