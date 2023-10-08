import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author lvirrey
 * @createdOn 10/4/2023 at 6:17 PM
 * @projectName EmployeeDatabase
 * @packageName PACKAGE_NAME;
 */

public class start {
    public static void run() {
        UI ui = new UI();
        int menuOptions = ui.Menu();
        switch (menuOptions){
            case 1:
                unstructured();
                break;
            case 2:
                structured();
                break;
            case 3:
                break;
        }
    }
    public static void unstructured(){
        List<String> employeeData = getEmployeeData();
        for (String employeeInfo: employeeData
             ) {
            System.out.println(employeeInfo);
        }
        run();
    }
    public static void structured(){
        List<String> employeeData = getEmployeeData();
        Employees employees = null;
        List<String> employeeList = new ArrayList<>();
        for (String employee: employeeData
             ) {
            String[] structuredEmployee = structuredEmployee(employee);
            employees = new Employees(Integer.parseInt(structuredEmployee[0]),structuredEmployee[1],structuredEmployee[2],Integer.parseInt(structuredEmployee[3]));
            employeeList.add(Arrays.toString(employees.employeeInfo()));
        }
        System.out.println(employeeList);
        run();
    }

    public static List<String> getEmployeeData(){
        IO data = new IO();
        List<String> employeeData = data.returnAllData();
        return employeeData;
    }

    public static String[] structuredEmployee(String employee){
        String[] structuredEmployee = employee.split(", ");
        return structuredEmployee;
    }
}
