package ru.testtask.sber.service;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.testtask.sber.model.Employee;
import ru.testtask.sber.util.exception.NotFoundException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.testtask.sber.EmployeeTestData.*;

@SpringJUnitConfig(locations = {
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
@RunWith(SpringRunner.class)
class EmployeeServiceTest {

    @Autowired
    protected EmployeeService service;

    @Test
    void create() {
        Employee created = service.create(getNew(), DEPARTMENT_ID);
        int newId = created.getId();
        Employee newEmployee = getNew();
        newEmployee.setId(newId);
        EMPLOYEE_MATCHER.assertMatch(created, newEmployee);
        EMPLOYEE_MATCHER.assertMatch(service.get(newId, DEPARTMENT_ID), newEmployee);
    }

    @Test
    void update() {
        Employee updated = getUpdated();
        service.update(updated, DEPARTMENT_ID);
        EMPLOYEE_MATCHER.assertMatch(service.get(EMPLOYEE_ID, DEPARTMENT_ID), getUpdated());
    }

    @Test
    void get() {
        EMPLOYEE_MATCHER.assertMatch(service.get(EMPLOYEE_ID, DEPARTMENT_ID), EMPLOYEE_1);
    }

    @Test
    void delete() {
        service.delete(EMPLOYEE_ID);
        assertThrows(NotFoundException.class, () -> service.get(EMPLOYEE_ID, DEPARTMENT_ID));
    }

    @Test
    void deleteNotFound() {
        assertThrows(NotFoundException.class, () -> service.delete(NOT_FOUND));
    }

    @Test
    void getAllForDepartment() {
        EMPLOYEE_MATCHER.assertMatch(service.getAllForDepartment(DEPARTMENT_ID), EMPLOYEES);
    }

    @Test
    void getBetween() {
        EMPLOYEE_MATCHER.assertMatch(service.getBetweenInclusive(DATE_START_FOR_IVANOV, DATE_END_FOR_IVANOV, DEPARTMENT_ID),
                EMPLOYEES_FOR_GETBETWEENINCLUSIVE);
    }

    @Test
    void getByDate() {
        EMPLOYEE_MATCHER.assertMatch(service.getByDate(DATE_END_FOR_SIDOROV, DEPARTMENT_ID + 1), EMPLOYEES_FOR_GETBYDATE);
    }
}