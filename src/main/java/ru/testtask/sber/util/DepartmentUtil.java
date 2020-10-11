package ru.testtask.sber.util;

import ru.testtask.sber.model.Department;
import ru.testtask.sber.model.DepartmentTo;
import ru.testtask.sber.model.Employee;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
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

    private static BigDecimal getAverageSalary(List<Employee> employees) {
        BigDecimal summingSolary = new BigDecimal("0");
        BigDecimal count = new BigDecimal("0");
        for (Employee employee : employees) {
            count = count.add(new BigDecimal("1.0"));
            summingSolary = summingSolary.add(employee.getSalary());
        }
        return summingSolary.divide(count);
    }
}