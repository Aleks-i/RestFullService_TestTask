package ru.testtask.sber.service;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import ru.testtask.sber.model.Employee;
import ru.testtask.sber.repository.EmployeeRepository;

import java.time.LocalDate;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;
import static ru.testtask.sber.util.DateUtil.atStartOfDayOrMin;
import static ru.testtask.sber.util.DateUtil.atStartOfNextDayOrMax;
import static ru.testtask.sber.util.ValidationUtil.checkNotFoundWithId;

@Service
public class EmployeeService {
    private static final Logger log = getLogger(EmployeeService.class);

    @Autowired
    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Employee create(Employee employee, int departmentId) {
        log.info("create emploee {}", employee);
        return employeeRepository.save(employee, departmentId);
    }

    public Employee update(Employee employee, int departmentId) {
        log.info("update emploee {}", employee);
        return checkNotFoundWithId(employeeRepository.save(employee, departmentId), employee.getId());
    }

    public Employee get(int id, int departmentId) {
        log.info("get emploee id {}", id);
        return checkNotFoundWithId(employeeRepository.get(id, departmentId), id);
    }

    public void delete(int id) {
        log.info("delete emploee id {}", id);
        checkNotFoundWithId(employeeRepository.delete(id), id);
    }

    public List<Employee> getAllForDepartment(int departmentId) {
        log.info("get all emploee");
        return employeeRepository.getAllForDepartment(departmentId);
    }

    public List<Employee> getBetweenInclusive(@Nullable LocalDate startDate, @Nullable LocalDate endDate, int departmentId) {
        return employeeRepository.getBetweenHalfOpen(atStartOfDayOrMin(startDate), atStartOfNextDayOrMax(endDate), departmentId);
    }

    public List<Employee> getByDate(LocalDate date, int departmentId) {
        return employeeRepository.getByDate(date, departmentId);
    }
}
