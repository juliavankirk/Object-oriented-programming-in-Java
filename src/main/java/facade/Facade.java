package facade;

import project.Model.*;
import project.Model.Employees.*;
import project.Utilities;
import project.review.ComparisonPrinter;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.*;

public class Facade {

    Model mModel;



    // This class only has the skeleton of the methods used by the test.
    // You must fill in this class with your own code. You can (and should) create more classes
    // that implement the functionalities listed in the Facade and in the Test Cases.

    public Facade() {
        mModel = new Model();
    }

    public String createItem(String itemID, String itemName, double unitPrice){
        String result = "";

        if ( itemID.isBlank() || itemName.isBlank() || unitPrice <= 0.00 ) {
            result = "Invalid data for item.";
        } else {
            Item item = new Item(itemID, itemName, unitPrice);
            mModel.addItem(item);
            result = "Item " + item.getID() + " was registered successfully.";
        }
        return result;
    }

    public String printItem(String itemID) {
        String result = "";
        Item printItem = null;

        for (Item item : mModel.getItemList()) {
            if (item.equals(mModel.getItemByID(itemID))) {
                printItem = item;
            }
        }

            if (printItem == null ) {
                result = "Item " + itemID + " was not registered yet.";
            } else {
                result = printItem.toString();
            }
            return result;
    }

    public String removeItem(String itemID) {
        Item item = mModel.getItemByID(itemID);

        if ( null != item && itemID.equals(item.getID())) {
            mModel.removeItem(itemID);
            return "Item " + itemID + " was successfully removed.";
        }

        return "Item " + itemID + " could not be removed.";
    }

    public boolean containsItem(String itemID) {
        Boolean contains;

        return false;
    }

    public double buyItem(String itemID, int amount) {
        Item item = mModel.getItemByID(itemID);

        if ( null != item && itemID.equals(item.getID())) {
            Transaction transaction = item.buyItem(amount);
            mModel.buyItem(item, amount);
            System.out.println("Item " + item.getID() + " was registered successfully.");
            return transaction.getTotal();
        }

        double result = 0;
        Item buyItem = null;

        if (buyItem == null ){
            result = -1.0;
            System.out.println("Item " + itemID + " was not registered yet.");
        } else {
            System.out.println(buyItem.toString());

        }

        return result;
    }

    public String reviewItem(String itemID, String reviewComment, int reviewGrade) {
        Item item = mModel.getItemByID(itemID);
        if (null == item) {
            return "Item " + itemID + " was not registered yet.";
        }

        if ( reviewGrade < 1 || reviewGrade > 5) {
            return "Grade values must be between 1 and 5.";
        }

        Review review = new Review(reviewGrade, reviewComment);

        item.addReview(review);

        return "";
    }

    public String reviewItem(String itemID, int reviewGrade) {
        return reviewItem(itemID, null, reviewGrade);
    }

    public String getItemCommentsPrinted(String itemID) {
        return "";
    }

    public List<String> getItemComments(String itemID) {
        Item item = mModel.getItemByID(itemID);

        ArrayList<String> rv = new ArrayList<String>();

        if (null == item) {
            return rv;
        }

        ArrayList<Review> reviews = item.getRatingAndComment();
        for (Review review : reviews) {
            if (review.getComment() != null) {
                rv.add(review.getComment());
            }
        }

        return rv;
    }

    public double getItemMeanGrade(String itemID) {
        Item item = mModel.getItemByID(itemID);

        if (null == item) {
            return 0;
        }

        return item.getMeanRating();
    }

    public int getNumberOfReviews(String itemID) {
        Item item = mModel.getItemByID(itemID);

        ArrayList<Review> reviews = item.getRatingAndComment();
        return reviews.size();
    }

    public String getPrintedItemReview(String itemID, int reviewNumber) {
        Item item = mModel.getItemByID(itemID);
        if (item == null) {
           return "Item " + itemID + " was not registered yet.";
        }

        ArrayList<Review> reviews = item.getRatingAndComment();
        if (reviews.isEmpty()) {
            return "Item " + item.getName() + " has not been reviewed yet.";
        }

        if (reviewNumber > reviews.size() || reviewNumber <= 0 ) {
            return "Invalid review number. Choose between 1 and " + reviews.size() + ".";
        }

        Review review = reviews.get(reviewNumber - 1);

        return review.toString();
    }

    public String getPrintedReviews(String itemID) {
        Item item = mModel.getItemByID(itemID);
        if (null == item) {
            return "Item " + itemID + " was not registered yet.";
        }

        ArrayList<Review> reviews = item.getRatingAndComment();

        StringBuilder sb = new StringBuilder("Review(s) for ");
        sb.append(item.toString());
        sb.append("\n");

        if (reviews.isEmpty()) {
            sb.append("The item "+ item.getName() + " has not been reviewed yet.");
        } else {
            for (Review review : reviews) {
                sb.append(review);
                sb.append("\n");
            }
        }
        return sb.toString();
    }

    public String printMostReviewedItems() {
        ComparisonPrinter cp = new ComparisonPrinter();

        return cp.mostReviews(mModel);
    }

    public List<String> getMostReviewedItems() {

        ComparisonPrinter cp = new ComparisonPrinter();

        return cp.getMostReviews(mModel);
    }

    public List<String> getLeastReviewedItems() {
        ComparisonPrinter cp = new ComparisonPrinter();

        return cp.getLeastReviewed(mModel);
    }

    public String printLeastReviewedItems() {
        ComparisonPrinter cp = new ComparisonPrinter();

        return cp.leastReviews(mModel);
    }

    public double getTotalProfit() {
        return mModel.getTotalProfit();
    }

    public String printItemTransactions(String itemID) {
        return mModel.printItemTransactions(itemID);
    }

    public int getTotalUnitsSold() {
        return mModel.getUnitsSold();
    }

    public int getTotalTransactions() {
        return mModel.getAllTransactions();
    }

    public double getProfit(String itemID) {
        Item item = mModel.getItemByID(itemID);
        return mModel.getProfitForItem(item);
    }

    public int getUnitsSolds(String itemID) {
        Item item = mModel.getItemByID(itemID);

        if(null == item) {
            return 0;
        }

        return mModel.getUnitsForItem(item);
    }

    public String printAllTransactions() {
        DecimalFormat df = new DecimalFormat("0.00");
        df.setRoundingMode(RoundingMode.DOWN);

        double totalProfit = mModel.getTotalProfit();
        int totalUnits = mModel.getUnitsSold();
        int totalPurchases = mModel.getAllTransactions();
        Collection<Transaction> transactions = mModel.getTransactions();
        StringBuilder sb = new StringBuilder("All purchases made: \n");
        sb.append("Total profit: ");
        sb.append(df.format(totalProfit));
        sb.append(" SEK\n");
        sb.append("Total items sold: ");
        sb.append(totalUnits);
        sb.append(" units\n");
        sb.append("Total purchases made: ");
        sb.append(totalPurchases);
        sb.append(" transactions\n");
        sb.append(Utilities.line());
        for (Transaction trans : transactions) {
            sb.append(trans.toString());
            sb.append("\n");
        }
        sb.append(Utilities.line());
        return sb.toString();
    }

    public String printWorseReviewedItems() {
        ComparisonPrinter cp = new ComparisonPrinter();

        return cp.worstRating(mModel);
    }

    public String printBestReviewedItems() {
        ComparisonPrinter cp = new ComparisonPrinter();

        return cp.bestRating(mModel);
    }

    public List<String> getWorseReviewedItems() {

        ComparisonPrinter cp = new ComparisonPrinter();

        return cp.getWorstReviewed(mModel);
    }

    public List<String> getBestReviewedItems() {
        ComparisonPrinter cp = new ComparisonPrinter();

        return cp.bestReviewed(mModel);
    }

    public String printAllReviews() {
        ComparisonPrinter cp = new ComparisonPrinter();

        return cp.everyReview(mModel);
    }

    public String updateItemName(String itemID, String newName) {
        Item item = mModel.getItemByID(itemID);
        if (null == item) {
            return "Item " + itemID + " was not registered yet.";
        }

        if (itemID.isBlank()) {
            return "Invalid data for item.";
        } else if (newName.isEmpty()) {
            return "Invalid data for item.";
        }

        if (item.equals(mModel.getItemByID(itemID))) {
            item.setName(newName);
        }
        return "Item " + itemID + " was updated successfully.";
    }

    public String updateItemPrice(String itemID, double newPrice) {
        boolean found = false;
        Item item = mModel.getItemByID(itemID);

        if (null == item) {
            return "Item " + itemID + " was not registered yet.";
        }

        if (itemID.isBlank()) {
            return "Invalid data for item.";
        } else if (newPrice < 1) {
            return "Invalid data for item.";
        }

        if (item.equals(mModel.getItemByID(itemID))) {
            found = true;
            item.setPrice(newPrice);
        }

        if (!found) {
            return "Item " + itemID + " was not registered yet.";
        }
        return "Item " + itemID + " was updated successfully.";
    }

    public String printAllItems() {
        StringBuilder sb = new StringBuilder("All registered items:\n");
        Collection<Item> items = mModel.sortedItems();

        if(items.isEmpty()) {
            return "No items registered yet.";
        }

        for (Item item : items) {
            sb.append(item.toString());
            sb.append("\n");
        }
        return sb.toString();
    }

    public String printMostProfitableItems() {
        DecimalFormat df = new DecimalFormat("0.00");
        df.setRoundingMode(RoundingMode.DOWN);

        Item item = mModel.mostProfitable();
        double profit = mModel.getProfitForItem(item);

        StringBuilder sb = new StringBuilder("Most profitable items: \n");
        sb.append("Total profit: ");
        sb.append(df.format(profit));
        sb.append(" SEK");
        sb.append("\n");
        sb.append(item);
        sb.append("\n");

        return sb.toString();
    }

    public String createEmployee(String employeeID, String employeeName, double grossSalary) throws Exception {

        if ( employeeID.isEmpty() ) {
            throw new Exception("ID cannot be blank.");
        } else if ( employeeName.trim().isEmpty() || employeeName.isBlank() ) {
            throw new Exception("Name cannot be blank.");
        } else if ( grossSalary < 1 ) {
            throw new Exception("Salary must be greater than zero.");
        }

        Employee employee = new RegularEmployee(employeeID, employeeName, grossSalary);
        mModel.addEmployee(employee);
        return "Employee " + employeeID + " was registered successfully.";
    }

    public String printEmployee(String employeeID) throws Exception {
        Employee employee = mModel.getEmployeeID(employeeID);

        if ( null == employee ) {
            throw new Exception("Employee " + employeeID + " was not registered yet.");
        }
        return employee.toString();
    }

    public String createEmployee(String employeeID, String employeeName, double grossSalary, String degree) throws Exception {

        if ( employeeID.isEmpty() || employeeID.isBlank()) {
            throw new Exception("ID cannot be blank.");
        } else if ( employeeName.trim().isEmpty() || employeeID.isBlank()) {
            throw new Exception("Name cannot be blank.");
        } else if ( grossSalary < 1 ) {
            throw new Exception("Salary must be greater than zero.");
        } else if ( degree.trim().isEmpty() || degree.isBlank() ) {
            throw new Exception("Degree must be one of the options: BSc, MSc, or PhD.");
        } else {
            try {
                DegreeType.valueOf(degree);
            } catch (Exception e) {
                throw new Exception("Degree must be one of the options: BSc, MSc or PhD.");
            }
        }

        double salary = Math.floor(grossSalary * 100) / 100;

        EmployeeManager employee = new EmployeeManager(employeeID, employeeName, salary, DegreeType.valueOf(degree));
        mModel.addEmployee(employee);
        return  "Employee " + employeeID + " was registered successfully.";
    }

    public String createEmployee(String employeeID, String employeeName, double grossSalary, int gpa) throws Exception {

        if ( employeeID.isEmpty() || employeeID.isBlank() ) {
            throw new Exception("ID cannot be blank.");
        } else if ( employeeName.isEmpty() || employeeName.isBlank() ) {
            throw new Exception("Name cannot be blank.");
        } else if ( grossSalary < 1 ) {
            throw new Exception("Salary must be greater than zero.");
        } else if ( gpa < 0 || gpa > 10 ) {
            StringBuilder sb = new StringBuilder();
            sb.append(gpa);
            sb.append(" outside range. Must be between 0-10.");
            throw new Exception(sb.toString());
        }

        EmployeeIntern employee = new EmployeeIntern(employeeID, employeeName, grossSalary, gpa);
        mModel.addEmployee(employee);

        return "Employee " + employeeID + " was registered successfully.";
    }

    public double getNetSalary(String employeeID) throws Exception {
        double salary = 0.0;

        Employee employee = mModel.getEmployeeID(employeeID);

        if ( null == employee ) {
            StringBuilder sb = new StringBuilder("Employee ");
            sb.append(employeeID);
            sb.append(" was not registered yet.");
            throw new Exception(sb.toString());
        }

        salary = employee.getNetSalary();

        return salary;
    }

    public String createEmployee(String employeeID, String employeeName, double grossSalary, String degree, String dept) throws Exception {
        if (employeeID.isEmpty()) {
            throw new Exception("ID cannot be blank.");
        } else if (employeeName.isEmpty()) {
            throw new Exception("Name cannot be blank.");
        } else if (grossSalary < 1) {
            throw new Exception("Salary must be greater than zero.");
        } else if (dept.isEmpty()) {
            throw new Exception("Department cannot be empty.");
        } else if (! (dept.equals("Human Resources") || dept.equals("Business") || dept.equals("Technical"))) {
            throw new Exception("Department must be one of the options: Business, Human Resources or Technical.");
        }

        EmployeeDirector employee = new EmployeeDirector(employeeID, employeeName, grossSalary, DegreeType.valueOf(degree), dept);
        mModel.addEmployee(employee);

        return "Employee " + employeeID + " was registered successfully.";
    }

    public String removeEmployee(String empID) throws Exception {
        Employee emp = mModel.getEmployeeID(empID);
        mModel.removeEmployee(empID);

        if (null == emp) {
            StringBuilder sb = new StringBuilder("Employee ");
            sb.append(empID);
            sb.append(" was not registered yet.");
            throw new Exception(sb.toString());
        }

        return "Employee " + empID + " was successfully removed.";
    }

    public String printAllEmployees() throws Exception {
        Collection<Employee> employees = mModel.getEmployeeList();

        if ( employees.isEmpty() ) {
            throw new Exception("No employees registered yet.");
        }

        StringBuilder sb = new StringBuilder("All registered employees:\n");

        for (Employee employee : employees) {
            sb.append(employee.toString());
            sb.append("\n");
        }
        return sb.toString();
    }

    public double getTotalNetSalary() throws Exception {
       double totalSalary = 0.0;
       double totalNet = mModel.getTotalNetPaid();

       if (mModel.getEmployeeList().isEmpty()) {
           throw new Exception("No employees registered yet.");
       }

       totalSalary += totalNet;

       totalSalary = Utilities.trunncate(totalSalary);

       return totalSalary;
    }

    public String printSortedEmployees() throws Exception {
        StringBuilder sb = new StringBuilder("Employees sorted by gross salary (ascending order):\n");

        Collection<Employee> employees = mModel.getSortedEmployees();

        if (employees.isEmpty()) {
            throw new Exception("No employees registered yet.");
        }

        for (Employee employee : employees) {
            sb.append(employee.toString());
            sb.append("\n");
        }

        return sb.toString();
    }

    public String updateEmployeeName(String empID, String newName) throws Exception {
        Employee employee = mModel.getEmployeeID(empID);

        if (null == employee) {
            StringBuilder sb = new StringBuilder("Employee ");
            sb.append(empID);
            sb.append(" was not registered yet.");
            throw new Exception(sb.toString());
        }

        if (mModel.getEmployeeList().isEmpty()) {
            throw new Exception("No employees registered yet.");
        }

        if (empID.isEmpty() || empID.isBlank()) {
            throw new Exception("ID cannot be blank.");
        } else if (newName.isEmpty() || newName.isBlank()) {
            throw new Exception("Name cannot be blank.");
        }

        employee.setName(newName);
        return "Employee " + empID + " was updated successfully";
    }

    public String updateInternGPA(String empID, int newGPA) throws Exception {
        Employee employee = mModel.getEmployeeID(empID), updated;

        if( null == employee ) {
            StringBuilder sb = new StringBuilder("Employee ");
            sb.append(empID);
            sb.append(" was not registered yet.");
            throw new Exception(sb.toString());
        }

        if (empID.isEmpty() || empID.isBlank()) {
            throw new Exception("ID cannot be blank.");
        } else if (newGPA < 0 || newGPA > 10) {
            StringBuilder sb = new StringBuilder();
            sb.append(newGPA);
            sb.append(" outside range. Must be between 0-10.");
            throw new Exception(sb.toString());
        }

        updated = new EmployeeIntern(employee.getID(), employee.getName(), employee.getBaseSalary(), newGPA);

        mModel.addEmployee(updated);
        return "Employee " + empID + " was updated successfully";
    }

    public String updateManagerDegree(String empID, String newDegree) throws Exception {
        Employee employee = mModel.getEmployeeID(empID), updated;

        if (null == employee) {
            StringBuilder sb = new StringBuilder("Employee ");
            sb.append(empID);
            sb.append(" was not registered yet.");
            throw new Exception(sb.toString());
        }

        if ( empID.trim().isEmpty() || empID.isBlank() ) {
            throw new Exception("ID cannot be blank");
        } else if ( newDegree.trim().isEmpty() || newDegree.isBlank() ) {
            throw new Exception("Degree must be one of the options: BSc, MSc, or PhD.");
        } else {
            try {
                DegreeType type = DegreeType.valueOf(newDegree);
            } catch (Exception e) {
                throw new Exception("Degree must be one of the options: BSc, MSc or PhD.");
            }
        }

        if (employee instanceof EmployeeManager) {
            EmployeeManager manager = (EmployeeManager) employee;

            updated = new EmployeeManager(manager.getID(), manager.getName(), manager.getBaseSalary(), DegreeType.valueOf(newDegree));
        } else {
            throw new Exception("Employee must be a manager.");
        }

        mModel.addEmployee(updated);
        return "Employee " + empID + " was updated successfully";
    }

    public String updateDirectorDept(String empID, String newDepartment) throws Exception {

        Employee employee = mModel.getEmployeeID(empID), updated;

        if (null == employee) {
            StringBuilder sb = new StringBuilder("Employee ");
            sb.append(empID);
            sb.append(" was not registered yet.");
            throw new Exception(sb.toString());
        }

        if (empID.trim().isEmpty() || empID.isBlank()) {
            throw new Exception("ID cannot be blank.");
        } else if (newDepartment.isEmpty()) {
            throw new Exception("Department cannot be empty.");
        } else if (! (newDepartment.equals("Human Resources") || newDepartment.equals("Business") || newDepartment.equals("Technical"))) {
            throw new Exception("Department must be one of the options: Business, Human Resources or Technical.");
        }


        if (employee instanceof EmployeeDirector) {
            EmployeeDirector director = (EmployeeDirector) employee;

            updated = new EmployeeDirector(director.getID(), director.getName(), director.getBaseSalary(),
                    director.getDegree(), newDepartment);
        } else if (employee instanceof EmployeeManager) {
            EmployeeManager manager = (EmployeeManager) employee;

            updated = EmployeeFactory.newDirector(manager.getID(), manager.getName(), manager.getBaseSalary(),
                    manager.getDegree(), newDepartment);
        } else {
            throw new Exception("Employee is not a director.");
        }
        mModel.addEmployee(updated);
        return "Employee " + empID + " was updated successfully";
    }

    public String updateGrossSalary(String empID, double newSalary) throws Exception {
        Employee employee = mModel.getEmployeeID(empID);
        if (null == employee) {
            StringBuilder sb = new StringBuilder("Employee ");
            sb.append(empID);
            sb.append(" was not registered yet.");
            throw new Exception(sb.toString());
        }

        if (empID.isEmpty() || empID.isBlank()) {
            throw new Exception("ID cannot be blank");
        } else if (newSalary < 1) {
            throw new Exception("Salary must be greater than zero.");
        }

        employee.setSalary(newSalary);

        return "Employee " + empID + " was updated successfully";
    }

    public Map<String, Integer> mapEachDegree() throws Exception {
        if (mModel.getEmployeeList().isEmpty()) {
            throw new Exception("No employees registered yet.");
        }


        return null;
    }

    public String promoteToManager(String empID, String degree) throws Exception {

        if (empID.isEmpty()) {
            throw new Exception("ID cannot be blank.");
        } else if (degree.isEmpty()) {
            throw new Exception("Degree cannot be blank");
        }

        Employee employee = mModel.getEmployeeID(empID);
        if (null == employee) {
            StringBuilder sb = new StringBuilder("Employee ");
            sb.append(empID);
            sb.append(" was not registered yet.");
            throw new Exception(sb.toString());
        }
        DegreeType degreeType = DegreeType.valueOf(degree);



        Employee promoted = EmployeeFactory.newManager(empID, employee.getName(), employee.getBaseSalary(), degreeType);
        mModel.addEmployee(promoted);

        return empID + " promoted successfully to Manager.";

    }

    public String promoteToDirector(String empID, String degree, String department) throws Exception {

        if (empID.isEmpty()) {
            throw new Exception("ID cannot be blank.");
        } else if (degree.isEmpty()) {
            throw new Exception("Degree cannot be blank");
        } else if (department.isEmpty()) {
            throw new Exception("Department cannot be blank");
        }

        Employee employee = mModel.getEmployeeID(empID);
        if (null == employee) {
            StringBuilder sb = new StringBuilder("Employee ");
            sb.append(empID);
            sb.append(" was not registered yet.");
            throw new Exception(sb.toString());
        }
        DegreeType degreeType = DegreeType.valueOf(degree);

        Employee promoted = EmployeeFactory.newDirector(empID, employee.getName(), employee.getBaseSalary(),
                degreeType, department);
        mModel.addEmployee(promoted);

        return empID + " promoted successfully to Director.";
    }

    public String promoteToIntern(String empID, int gpa) throws Exception {
        if (empID.isEmpty()) {
            throw new Exception("ID cannot be blank.");
        } else if (gpa < 0 || gpa > 10) {
            throw new Exception("GPA must be 0-10");
        }

        Employee employee = mModel.getEmployeeID(empID);
        if (null == employee) {
            StringBuilder sb = new StringBuilder("Employee ");
            sb.append(empID);
            sb.append(" was not registered yet.");
            throw new Exception(sb.toString());
        }

        Employee promoted = EmployeeFactory.newIntern(empID, employee.getName(), employee.getBaseSalary(), gpa);
        mModel.addEmployee(promoted);

        return empID + " promoted successfully to Intern.";
    }

}
