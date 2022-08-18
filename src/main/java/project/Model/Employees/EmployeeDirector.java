package project.Model.Employees;

import project.Model.DegreeType;

public class EmployeeDirector extends EmployeeManager {
    private String mDepartment;

    public EmployeeDirector(String ID, String name, Double salary, DegreeType degreeType, String department) {
        super(ID, name, salary, degreeType);
        mDepartment = department;
    }

    public String getDept() { return mDepartment; }
    public void setDept(String department) { mDepartment = department; }

    @Override
    public Double getSalary() {
        Double salary = super.getSalary();
        Integer bonus = 5000;

        Double newSalary = salary + bonus;
        return Math.floor(newSalary * 100) /100;
    }

    public Double getNetSalary() {
        Double salary = getSalary();
        if ( salary < 30000 ) {
            return ( salary * 0.9 );
        } else if ( salary > 50000 ) {
            Double ceiling = salary - 30000;
            return (( ceiling * 0.6 ) + 24000);
        } else {
            return ( salary * 0.8 );
        }
    }



    public Double getTruncNet() {
        Double salary = getNetSalary();
        int numTrunc = (int)(salary * 100);
        Double netSalary = numTrunc * 100.00;

        return netSalary;
    }

    public Double getTruncGross() {
        Double salary = getNetSalary();
        int numTrunc = (int)(salary * 100);
        double grossSalary = numTrunc / 100.00;

        return grossSalary;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(super.toString());
        sb.append(" Dept: ");
        sb.append(getDept());

        return sb.toString();
    }

}
