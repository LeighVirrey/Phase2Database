import java.io.Serializable;

/**
 * @author lvirrey
 * @createdOn 10/4/2023 at 4:12 PM
 * @projectName EmployeeDatabase
 * @packageName PACKAGE_NAME;
 */

public class Employees implements Serializable {
    private int id;
    private String firstName;
    private String lastName;
    private int hireYear;

    public Employees(int id, String firstName, String lastName, int hireYear){
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.hireYear = hireYear;
    }

    public String[] employeeInfo(){
        String[] employeeInfo = {String.valueOf(getId()), getFirstName(), getLastName(), String.valueOf(getHireYear())};
        return employeeInfo;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getHireYear() {
        return hireYear;
    }

    public void setHireYear(int hireYear) {
        this.hireYear = hireYear;
    }
}
