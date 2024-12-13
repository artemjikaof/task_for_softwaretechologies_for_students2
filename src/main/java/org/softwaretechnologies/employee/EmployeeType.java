package org.softwaretechnologies.employee;

import java.time.LocalDate;
import java.time.YearMonth;

/**
 * Тип сотрудника
 */
public enum EmployeeType {
    /*
    Формула вычисления зп: если месяц четный, то baseSalary, иначе baseSalary/2
     */
    Manager{
        @Override
        public Employee create(String name, int baseSalary){
            return new Manager(name,baseSalary);
        }

    },

    /*
    Формула вычисления зп: всегда baseSalary
     */
    Programmer{
        @Override
        public Employee create(String name, int baseSalary) {
            return new Programmer(name, baseSalary);
        }

    },
    /*
    Формула вычисления зп: baseSalary * количество дней в месяце в текущем году
    Вычисление количества дней в месяце: YearMonth.of(LocalDate.now().getYear(), month).lengthOfMonth()
     */
    Tester{
        @Override
        public Employee create(String name, int baseSalary) {
            return new Tester(name, baseSalary);
        }
    };
    public abstract Employee create(String name, int baseSalary);

}
