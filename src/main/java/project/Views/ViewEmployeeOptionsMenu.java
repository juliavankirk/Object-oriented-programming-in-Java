package project.Views;

import project.Utilities;

public class ViewEmployeeOptionsMenu {
    //prints user options with prompted response
    public void renderEmployeeOptions(){
    System.out.println(Utilities.line() +
        "Employee options menu:\n" +
        "0. Return to Main Menu.\n" +
        "1. Create an employee (Regular Employee).\n" +
        "2. Create an employee (Manager).\n" +
        "3. Create an employee (Director).\n" +
        "4. Create an employee (Intern).\n" +
        "5. Remove an employee.\n" +
        "6. Print specific employee.\n" +
        "7. Print all registered employees.\n" +
        "8. Print the total expense with net salary.\n" +
        "9. Print all employees sorted by gross salary.\n" +

        "Type an option number: \n"
        );
    }
    //returns user to main menu
    public void returnMainMenu() { System.out.println("Returning to main menu.\n"); }

    //takes the input of the user
    public int employeeOptionsInput(){
        int employeeOptionsInput = Utilities.intInput();

        return employeeOptionsInput;
    }
}