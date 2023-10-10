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
    public static UI ui = new UI();
    public static void run() {
        int menuOptions = ui.Menu();
        switch (menuOptions){
            case 1:
                unstructured();
                break;
            case 2:
                structured();
                break;
            case 3:
                createEmployee();
                break;
            case 4:
                updateEmployee();
                break;
            case 5:
                deleteEmployee();
                break;
            case 6:
                findEmployee();
                break;
            case 7:
                System.out.println("bye");
                break;
        }
    }
    public static void findEmployee(){
        int id = ui.findID();
        IO find = new IO();
        List<String> employee = find.findEmployee(id);
        System.out.println(employee);//fix later?
        run();
    }
    public static void createEmployee(){
        List<String> createList = ui.createList();
        IO employee = new IO();
        employee.createEmployee(Integer.parseInt(createList.get(0)), createList.get(1), createList.get(2), Integer.parseInt(createList.get(3)));
        run();
    }
    public static void updateEmployee(){
        int id  = ui.findID();
        IO io = new IO();
        List<String> employeeData = io.findEmployee(id);

        if (!employeeData.isEmpty()) {
            System.out.println("Current Employee Data: ");
            for (String data : employeeData) {
                System.out.println(data);
            }


            List<String> updatedEmployeeData = io.updateEmployeeInfo();


            employeeData.clear();
            employeeData.addAll(updatedEmployeeData);


            io.updateEmployeeData(id, employeeData);

            System.out.println("Employee with ID " + id + " updated successfully.");
        } else {
            System.out.println("Employee with ID " + id + " not found.");
        }
    }
    public static void deleteEmployee(){
        int id = ui.findID();
        IO delete = new IO();
        delete.deleteEmployee(id);
        run();
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
