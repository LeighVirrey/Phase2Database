/**
 * @author lvirrey
 * @createdOn 10/4/2023 at 4:15 PM
 * @projectName EmployeeDatabase
 * @packageName PACKAGE_NAME;
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class IO {
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
}
