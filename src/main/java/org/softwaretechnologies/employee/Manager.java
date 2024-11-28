package org.softwaretechnologies.employee;

public class Manager extends Employee
{
    public Manager(String name, int baseSalary)
    {
        super(name, baseSalary);
    }

    @Override
    public int getMonthSalary(int month)
    {
        return (month % 2 == 0) ? baseSalary : baseSalary / 2;
    }
}