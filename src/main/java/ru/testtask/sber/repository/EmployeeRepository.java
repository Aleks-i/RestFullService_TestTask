package ru.testtask.sber.repository;

import ru.testtask.sber.model.Employee;

import java.time.LocalDate;
import java.util.List;

public interface EmployeeRepository {

    Employee save(Employee employee, int departmentId);

    Employee get(int id, int departmentId);

    boolean delete(int id);

    List<Employee> getAllForDepartment(int departmentId);

    List<Employee> getBetweenHalfOpen(LocalDate atStartOfDayOrMin, LocalDate atStartOfNextDayOrMax, int departmentId);

    List<Employee> getByDate(LocalDate date, int departmentId);
}
