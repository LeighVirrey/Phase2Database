import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static java.util.Objects.hash;

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
                serialize();
                break;
            case 8:
                hash();
                break;
            case 9:
                //find id by hash
                break;
            case 10:
                //find all users with the same lastname by hash
                break;
            case 11:
                findIdSerialized();
                break;
            case 12:
                findLastNameSerialized();
                break;
            case 13:
                findLastName();
                break;
            case 14:
                System.out.println("bye");
                break;
        }
    }
    public static void serialize(){
        IO serialize = new IO();
        serialize.serializeAll();
        run();
    }
    public static void findIdSerialized(){
        int id = ui.findID();
        IO find = new IO();
        Employees employee = find.findEmployeeSerial(id);
        System.out.println(Arrays.toString(employee.employeeInfo()));
        run();
    }
    public static void findLastNameSerialized(){
        String lastNameUI = ui.getLastName();
        IO serialLastName = new IO();
        List<Employees> employeesList = serialLastName.findLastNameSerial(lastNameUI);
        if (employeesList.size() != 0){
            for (Employees employee: employeesList
            ) {
                System.out.println(Arrays.toString(employee.employeeInfo()));
            }
        }else{
            System.out.println("No records of " + lastNameUI + " found");
        }
        run();
    }
    public static void findLastName(){
        String lastNameUI = ui.getLastName();
        IO lastNameFind = new IO();
        List<String> employeesList = lastNameFind.findLastName(lastNameUI);
        Employees employees;
        if (employeesList.size() != 0) {
            for (String employee: employeesList
            ) {
                String[] structuredEmployee = structuredEmployee(employee);
                employees = new Employees(Integer.parseInt(structuredEmployee[0]),structuredEmployee[1],structuredEmployee[2],Integer.parseInt(structuredEmployee[3]));
                System.out.println(Arrays.toString(employees.employeeInfo()));
            }
        }else{
            System.out.println("No records of " + lastNameUI + " found");
        }
        run();
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
        run();
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
