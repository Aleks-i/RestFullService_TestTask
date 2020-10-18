package ru.testtask.sber.util;

import ru.testtask.sber.model.Department;
import ru.testtask.sber.model.DepartmentTo;
import ru.testtask.sber.model.Employee;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class DepartmentUtil {

    public static List<DepartmentTo> getTos(Collection<Department> departments) {
        return departments.stream()
                .map(DepartmentUtil::createTo)
                .collect(Collectors.toList());
    }

    private static DepartmentTo createTo(Department department) {
        return new DepartmentTo(department.getId(), department.getName(), getAverageSalary(department.getEmployees()));
    }

    private static String getAverageSalary(List<Employee> employees) {
        if (employees.isEmpty()) return "0";
        else {
            NumberFormat formatter = NumberFormat.getInstance(new Locale("en_US"));
            BigDecimal summingSolary = new BigDecimal("0");
            BigDecimal count = new BigDecimal("0");
            for (Employee employee : employees) {
                count = count.add(new BigDecimal("1"));
                summingSolary = summingSolary.add(employee.getSalary());
            }
            return formatter.format(summingSolary.divide(count).setScale(2));
        }
    }
}