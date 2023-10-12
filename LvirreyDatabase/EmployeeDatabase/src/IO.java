/**
 * @author lvirrey
 * @createdOn 10/4/2023 at 4:15 PM
 * @projectName EmployeeDatabase
 * @packageName PACKAGE_NAME;
 */

import java.io.*;
import java.time.LocalDate;
import java.time.Year;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import org.apache.commons.lang3.time.StopWatch;

public class IO {
    private static StopWatch stopWatch = new StopWatch();
    public static void serializeAll(){
        stopWatch.start();
        //File serialized = new File("LvirreyDatabase/EmployeeDatabase/people/long serialized");
        Employees employee = null;
        List<String> employeeFiles = returnAllDataLong();
        for(String file : employeeFiles){
            String[] structuredEmployee = structuredEmployee(file);
            employee = new Employees(Integer.parseInt(structuredEmployee[0]),structuredEmployee[1],structuredEmployee[2],Integer.parseInt(structuredEmployee[3]));
            try {
                FileOutputStream findFile = new FileOutputStream("LvirreyDatabase/EmployeeDatabase/people/long serialized/" + employee.getId() + ".ser");
                ObjectOutputStream serializeFile = new ObjectOutputStream(findFile);
                serializeFile.writeObject(employee);
            } catch (FileNotFoundException e) {
                System.out.println("File not found");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        stopWatch.stop();
        System.out.println("Load Time: " + stopWatch.getTime());
        stopWatch.reset();
    }
    public static String[] structuredEmployee(String employee){
        String[] structuredEmployee = employee.split(", ");
        return structuredEmployee;
    }
    /*--
    This part returns all data from text files from the Simple folder
    takes no parameters
    --*/
    public List<String> returnAllData() {
        File folder = new File("LvirreyDatabase/EmployeeDatabase/people/simple");
        List<String> files = Arrays.asList(folder.list());
        File folderTemp = null;
        int index = 0;
        for (String file : folder.list()) {
            folderTemp = new File("LvirreyDatabase/EmployeeDatabase/people/simple/" + file);
            Scanner read = null;
            try {
                read = new Scanner(folderTemp);
                while (read.hasNextLine()) {
                    String data = read.nextLine();
                    files.set(index, data);
                    index++;
                }
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        return files;
    }
    public static List<String> returnAllDataLong() {
        File folder = new File("LvirreyDatabase/EmployeeDatabase/people/long");
        List<String> files = Arrays.asList(folder.list());
        File folderTemp = null;
        int index = 0;
        for (String file : folder.list()) {
            folderTemp = new File("LvirreyDatabase/EmployeeDatabase/people/long/" + file);
            Scanner read = null;
            try {
                read = new Scanner(folderTemp);
                while (read.hasNextLine()) {
                    String data = read.nextLine();
                    files.set(index, data);
                    index++;
                }
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        return files;
    }
    /*--Creates an employee using the 4 given parameters and adds it to the /long/ directory--*/
    public void createEmployee(int id, String firstName, String lastName, int hireYear){
        if(id != 0 && !firstName.isEmpty() && !lastName.isEmpty() && hireYear > 1000 || hireYear > Year.now().getValue()){
            try {
                File file = new File("LvirreyDatabase/EmployeeDatabase/people/long/" + id + ".txt");
                if (!file.createNewFile()) {
                    System.out.println("ID has been taken");
                }
            } catch (IOException e) {
                System.out.println("file not found, adjust the pathing please.");
            }
            try {
                FileWriter write = new FileWriter("LvirreyDatabase/EmployeeDatabase/people/long/" + id + ".txt");
                write.write(id + ", " + firstName + ", " + lastName + ", " + hireYear);
                write.close();
                System.out.println("file " + id + ".txt was written sucessfully");
            }catch (IOException e){
                System.out.println("File not found??? This shouldn't be possible");
            }
        }else{
            System.out.println("Creation failed, please try again");
        }
    }
    public void deleteEmployee(int id){
        File fileDelete = new File("LvirreyDatabase/EmployeeDatabase/people/long/" + id + ".txt");
        if (fileDelete.delete()) {
            System.out.println("File " + id + ".txt deleted");
        }else{
            System.out.println("Error occured, file might not exist");
        }
    }

    public void updateEmployeeData(int id, List<String> updatedData) {
        File file = new File("LvirreyDatabase/EmployeeDatabase/people/long/" + id + ".txt");

        try (FileWriter writer = new FileWriter(file)) {
            int index = 0;
            for (String data : updatedData) {
                if(index != 3){
                    writer.write(data + ", "); // Write each line with a line separator
                    index++;
                }else{
                    writer.write(data);
                }

            }
            System.out.println("Employee data for ID " + id + " updated successfully.");
        } catch (IOException e) {
            System.out.println("Error updating employee data for ID " + id + ": " + e.getMessage());
        }
    }

    public List<String> updateEmployeeInfo() {
        List<String> updatedData = new ArrayList<>();

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter updated ID: ");
        updatedData.add(scanner.nextLine());

        System.out.print("Enter updated first name: ");
        updatedData.add(scanner.nextLine());

        System.out.print("Enter updated last name: ");
        updatedData.add(scanner.nextLine());

        System.out.print("Enter updated hire year: ");
        updatedData.add(scanner.nextLine());

        return updatedData;
    }

    public static List<String> findEmployee(int id){
        File file = new File ("LvirreyDatabase/EmployeeDatabase/people/long/" + id + ".txt");
        List<String> employee = new ArrayList<>();
        try {
            Scanner read = new Scanner(file);
            while (read.hasNextLine()) {
                String data = read.nextLine();
                employee.add(data);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return employee;
    }
    public Employees findEmployeeSerial(int id){
        stopWatch.start();
        FileInputStream file = null;
        Employees employee;
        try {
            file = new FileInputStream("LvirreyDatabase/EmployeeDatabase/people/long serialized/" + id + ".ser");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            ObjectInputStream read = new ObjectInputStream(file);
            employee = (Employees)read.readObject();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        stopWatch.stop();
        System.out.println("Load Time: " + stopWatch.getTime());
        stopWatch.reset();
        return employee;
    }
    public List<Employees> findLastNameSerial(String lastName){
        stopWatch.start();
        File folder = new File("LvirreyDatabase/EmployeeDatabase/people/long serialized");
        FileInputStream file = null;
        Employees employeeCheck;
        List<Employees> employeesList = new ArrayList<>();
        for (String employee: folder.list()
             ) {
            try{
                file = new FileInputStream("LvirreyDatabase/EmployeeDatabase/people/long serialized/" + employee);
            }catch (FileNotFoundException e){
                System.out.println("error found, fix the pathing please");
            }
            try{
                ObjectInputStream read = new ObjectInputStream(file);
                employeeCheck = (Employees)read.readObject();
                if(lastName.toLowerCase().equals(employeeCheck.getLastName().toLowerCase())){
                    employeesList.add(employeeCheck);
                }
            }catch (IOException e){
                System.out.println("What: " + e);
            }catch (ClassNotFoundException c){
                System.out.println("WHAT HAPPENED TO THE EMPLOYEES CLASS?!?!?!");
            }
        }
        stopWatch.stop();
        System.out.println("Load Time: " + stopWatch.getTime());
        stopWatch.reset();
        return employeesList;
    }
    public List<String> findLastName(String lastName){
        stopWatch.start();
        File folder = new File("LvirreyDatabase/EmployeeDatabase/people/long");
        List<String> files = new ArrayList<>();
        File folderTemp = null;
        for (String file : folder.list()) {
            folderTemp = new File("LvirreyDatabase/EmployeeDatabase/people/long/" + file);
            Scanner read = null;
            try {
                read = new Scanner(folderTemp);
                while (read.hasNextLine()) {
                    String data = read.nextLine();
                    String[] dataArray = data.split(", ");
                    if(dataArray[2].toLowerCase().equals(lastName.toLowerCase())) {
                        files.add(data);
                    }
                }
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        stopWatch.stop();
        System.out.println("Load Time: " + stopWatch.getTime());
        stopWatch.reset();
        return files;
    }
}

