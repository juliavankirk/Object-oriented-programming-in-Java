package project.Model.Employees;

import project.Model.DegreeType;

public class EmployeeIntern extends Employee {
    private Integer mGpa;

    public EmployeeIntern(String ID, String name, Double salary, Integer gpa) {
        super(ID, name, salary);
        mGpa = gpa;
    }

    public Integer getGpa() { return mGpa; }
    public void setGpa(Integer gpa) { mGpa = gpa; }

    @Override
    public Double getSalary() {
        Integer gpa = getGpa();
        Double salary = super.getSalary();

        if ( gpa < 6 ) {
            salary = 0.0;
        } else if ( gpa > 7 ) {
            salary += 1000;
        }
        return Math.floor(salary * 100) / 100;
    }

    @Override
    public Double getNetSalary() {
        return getSalary();
    }



    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(super.toString());
        sb.append(" GPA: ");
        sb.append(getGpa());

        return sb.toString();
    }

}
