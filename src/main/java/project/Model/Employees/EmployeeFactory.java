package project.Model.Employees;

import project.Model.DegreeType;

public class EmployeeFactory {
    public static RegularEmployee newRegular(String id, String name, double salary) {
        return new RegularEmployee(id, name, salary);
    }

    public static EmployeeManager newManager(String id, String name, double salary, DegreeType degree) {
        return new EmployeeManager(id, name, salary, degree);
    }

    public static EmployeeDirector newDirector(String id, String name, double salary, DegreeType degree, String dept) {
        return new EmployeeDirector(id, name, salary, degree, dept);
    }

    public static EmployeeIntern newIntern(String id, String name, double salary, int gpa) {
        return new EmployeeIntern(id, name, salary, gpa);
    }
}
