package project.Model.Employees;

import project.Model.DegreeType;

public class RegularEmployee extends Employee {
    public RegularEmployee(String ID, String name, Double salary) {
        super(ID, name, salary);

    }

    public Employee toRegular() {
        return this;
    }

    public Employee toManager(DegreeType degreeType) {
        return new EmployeeManager(getID(), getName(), getBaseSalary(), degreeType);
    }

    public Employee toDirector(DegreeType degreeType, String dept) {
        return new EmployeeDirector(getID(), getName(), getBaseSalary(), degreeType, dept);
    }

    public Employee toIntern(int gpa) {
        return new EmployeeIntern(getID(), getName(), getBaseSalary(), gpa);
    }

    @Override
    public String toString() {
        String retVal = (getName() + "'s gross salary is ");
        retVal += (String.format("%.2f", getSalary())+ " SEK per month.");
        return  retVal;
    }

}
