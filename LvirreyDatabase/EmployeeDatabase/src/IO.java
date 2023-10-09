/**
 * @author lvirrey
 * @createdOn 10/4/2023 at 4:15 PM
 * @projectName EmployeeDatabase
 * @packageName PACKAGE_NAME;
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileWriter;
import java.time.LocalDate;
import java.time.Year;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class IO {
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
    /*--Creates an employee using the 4 given parameters and adds it to the /long/ directory--*/
    public void createEmployee(int id, String firstName, String lastName, int hireYear){
        if(id != 0 && !firstName.isEmpty() && !lastName.isEmpty() && hireYear > 1000 || hireYear > Year.now().getValue()){
            try {
                File myObj = new File("LvirreyDatabase/EmployeeDatabase/people/long/" + id + ".txt");
                if (!myObj.createNewFile()) {
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
}

