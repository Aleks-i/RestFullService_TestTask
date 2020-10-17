package ru.testtask.sber.web;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.testtask.sber.model.Employee;
import ru.testtask.sber.service.EmployeeService;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;
import static ru.testtask.sber.util.ValidationUtil.assureIdConsistent;
import static ru.testtask.sber.util.ValidationUtil.checkNew;

@RestController
@RequestMapping(value = EmployeeRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class EmployeeRestController {
    static final String REST_URL = "/employees";
    private static final Logger log = getLogger(EmployeeRestController.class);

    @Autowired
    private EmployeeService employeeService;

    @PostMapping(value = "/create/{departmentId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Employee> create(@RequestBody Employee employee, @PathVariable int departmentId) {
        checkNew(employee);
        log.info("save {} for department id {}", employee, departmentId);
        Employee createdEmployee = employeeService.create(employee, departmentId);
        URI ofNewRecourse = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(createdEmployee.getId()).toUri();
        return ResponseEntity.created(ofNewRecourse).body(createdEmployee);
    }

    @PutMapping(value = "/{departmentId}/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void update(@RequestBody Employee employee, @PathVariable int id, @PathVariable int departmentId) {
        assureIdConsistent(employee, id);
        log.info("update {} for department {}", employee, departmentId);
        employeeService.update(employee, departmentId);
    }

    @GetMapping("/{departmentId}/{id}")
    public Employee get(@PathVariable int id, @PathVariable int departmentId) {
        log.info("get employee {}", id);
        return employeeService.get(id, departmentId);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        log.info("delete employee {}", id);
        employeeService.delete(id);
    }

    @GetMapping("/{departmentId}")
    public List<Employee> getAllForDepartment(@PathVariable int departmentId) {
        log.info("getAll for department {}", departmentId);
        return employeeService.getAllForDepartment(departmentId);
    }

    @GetMapping(value = "/{departmentId}/filter")
    public List<Employee> getBetween(@RequestParam @Nullable LocalDate startDate,
                                     @RequestParam @Nullable LocalDate endDate, @PathVariable int departmentId) {
        log.info("getBetween dates({} - {})", startDate, endDate);
        return employeeService.getBetweenInclusive(startDate, endDate, departmentId);
    }

    @GetMapping(value = "/{departmentId}/findbydate")
    public List<Employee> getByDate(@RequestParam LocalDate date, @PathVariable int departmentId) {
        log.info("getByDate {}", date);
        return employeeService.getByDate(date, departmentId);
    }
}
