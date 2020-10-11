package ru.testtask.sber.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.testtask.sber.model.Employee;
import ru.testtask.sber.repository.EmployeeRepository;

import java.time.LocalDate;
import java.util.List;

@Repository
public class EmployeeRepositoryImpl implements EmployeeRepository {

    @Autowired
    private final CrudEmployeeRepository employeeRepository;

    @Autowired
    private final CrudDepartmentRepository departmentRepository;

    public EmployeeRepositoryImpl(CrudEmployeeRepository employeeRepository, CrudDepartmentRepository departmentRepository) {
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
    }

    @Override
    public Employee save(Employee employee, int departmentId) {
        employee.setDepartment(departmentRepository.getOne(departmentId));
        return employeeRepository.save(employee);
    }

    @Override
    public Employee get(int id, int departmentId) {
        return employeeRepository.findById(id)
                .filter(employee -> employee.getDepartment().getId() == departmentId)
                .orElse(null);
    }

    @Override
    public boolean delete(int id) {
        return (employeeRepository.delete(id) != 0);
    }

    @Override
    public List<Employee> getAllForDepartment(int departmentId) {
        return employeeRepository.getAllForDepartment(departmentId);
    }

    @Override
    public List<Employee> getBetweenHalfOpen(LocalDate atStartOfDayOrMin, LocalDate atStartOfNextDayOrMax, int departmentId) {
        return employeeRepository.getBetweenHalfOpen(atStartOfDayOrMin, atStartOfNextDayOrMax, departmentId);
    }

    @Override
    public List<Employee> getByDate(LocalDate date, int departmentId) {
        return employeeRepository.getByDate(date, departmentId);
    }
}