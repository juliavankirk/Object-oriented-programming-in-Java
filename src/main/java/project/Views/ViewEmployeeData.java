package project.Views;

import project.Model.Employees.Employee;
import project.Utilities;

//view for all information regarding employees
public class ViewEmployeeData {
    public String inputEmployeeId() {
        System.out.println("Please enter employee ID:");
        String id = Utilities.stringInput();

        return id;
    }

    public void displayEmpInfo(Employee employee) {
        String output = employee.getClass().toString();

        System.out.println(output);
    }

}
