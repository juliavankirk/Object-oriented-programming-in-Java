package project.Model.Employees;

import project.Model.DegreeType;

public class EmployeeManager extends Employee {
    private DegreeType mDegree;

    public EmployeeManager(String ID, String name, Double salary, DegreeType degreeType) {
        super(ID, name, salary);
        mDegree = degreeType;
    }

    public DegreeType getDegree() { return mDegree; }
    public void setDegree(DegreeType degree) { mDegree = degree; }

    public Double getBonus() {
        Double bonus = 1.0;
        if (mDegree.equals(DegreeType.BSc)) {
            bonus = 1.1;
        } else if (mDegree.equals(DegreeType.MSc)) {
            bonus = 1.2;
        } else if (mDegree.equals(DegreeType.PhD)) {
            bonus = 1.35;
        }
        return bonus;
    }



    @Override
    public Double getSalary() {
        Double totalGross = super.getSalary() * getBonus();

        return Math.floor(totalGross * 100) / 100;
    }

    @Override
    public String toString() {
        StringBuilder sb  = new StringBuilder(String.valueOf(getDegree()));
        sb.append(" ");
        sb.append(super.toString());

        return sb.toString();
    }


}
