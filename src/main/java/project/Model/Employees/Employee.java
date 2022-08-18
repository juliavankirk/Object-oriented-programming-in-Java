package project.Model.Employees;

import project.Model.DegreeType;

import java.math.RoundingMode;
import java.text.DecimalFormat;

public abstract class Employee implements Comparable {
    private String mID, mName;
    private Double mSalary;

    public Employee(String ID, String name, Double salary) {
        if (null == ID) {
            throw new RuntimeException("ID is invalid.");
        } else if (null == name) {
            throw new RuntimeException("Name is invalid.");
        }else if (null == salary) {
            throw new RuntimeException("Salary is invalid.");
        }

        if (ID.isEmpty()) {
            throw new RuntimeException("ID cannot be blank.");
        } else if (name.isEmpty()) {
            throw new RuntimeException("Name cannot be blank.");
        } else if (salary < 1) {
            throw new RuntimeException("Salary must be greater than zero.");
        }

        mID = ID;
        mName = name;
        mSalary = salary;
    }

    public String getID() { return mID; }
    public void setID(String ID) { mID = ID; }

    public String getName() { return mName; }
    public void setName(String name) { mName = name; }

    public Double getBaseSalary() { return mSalary; }

    public Double getSalary() { return getBaseSalary(); }
    public void setSalary(Double salary) { mSalary = salary; }

    public Double getNetSalary() {
        Double netSalary = getSalary() * 0.9;
        return Math.floor(netSalary * 100) / 100;
    }



    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("0.00");
        df.setRoundingMode(RoundingMode.DOWN);

        String retVal = (getName() + "'s gross salary is ");
        retVal += (df.format(getSalary()) + " SEK per month.");
        return  retVal;
    }

    @Override
    public int compareTo(Object o) {
        Employee right = (Employee) o;

        int rv = (int) (this.getSalary() - right.getSalary());
        return rv;
    }
}
