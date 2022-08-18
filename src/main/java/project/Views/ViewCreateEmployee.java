package project.Views;

import project.Model.DegreeType;
import project.Model.Employees.EmployeeManager;
import project.Utilities;

import java.util.Collection;

public class ViewCreateEmployee{
    public String employeeId() {
        System.out.println("Please enter the following Employee Data:" + System.lineSeparator() +
                "ID:");
        String id = Utilities.stringInput();

        return id;
    }

    public String employeeName() {
        System.out.println("Name:");
        String name = Utilities.stringInput();

        return name;
    }

    public Double employeeSalary() {
        System.out.println("Gross Salary:");
        Double salary = Utilities.doubleInput();

        return salary;
    }

    public void employeeSuccess(String id) {
        System.out.println("Employee " + id + " was registered successfully.");
    }
    public void employeeError(String id) {
        System.out.println(" ");
    }

    public DegreeType chooseDegree() {
        System.out.println("Degree:");
        DegreeType[] degrees = DegreeType.values();
        DegreeType degree = null;

        String input = Utilities.stringInput();

        for ( DegreeType degreeType : degrees) {
            //comparing to string input
            if (DegreeType.valueOf(input).equals(degreeType)) {
                    //degreeType.toString().equals(input)) {
                degree = degreeType;
                //break to exit loop once I pick up what I need
                break;
            }
        }
        return degree;
    }

    public String directorDept() {
        System.out.println("Department:");
        String department = Utilities.stringInput();

        return department;
    }

    public Integer internGpa() {
        System.out.println("GPA:");
        Integer gpa = Utilities.intInput();

        return gpa;
    }
}
