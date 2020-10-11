package ru.testtask.sber.service;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.testtask.sber.model.Department;
import ru.testtask.sber.util.exception.NotFoundException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.testtask.sber.DepartmentTestData.*;
import static ru.testtask.sber.util.DepartmentUtil.getTos;

@SpringJUnitConfig(locations = {
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
@RunWith(SpringRunner.class)
class DepatmentRepositoryImplTest {

    @Autowired
    protected DepartmentService service;

    @Test
    void save() {
        Department created = service.create(getNew());
        int newId = created.getId();
        Department newDepartment = getNew();
        newDepartment.setId(newId);
        DEPARTMENT_MATCHER.assertMatch(created, newDepartment);
        DEPARTMENT_MATCHER.assertMatch(service.get(newId), newDepartment);
    }

    @Test
    void update() {
        Department updated = getUpdated();
        service.update(updated);
        DEPARTMENT_MATCHER.assertMatch(service.get(DEPARTMENT_ID), getUpdated());
    }

    @Test
    void get() {
        DEPARTMENT_MATCHER.assertMatch(service.get(DEPARTMENT_ID), DEPARTMENT);
    }

    @Test
    void delete() {
        service.delete(DEPARTMENT_ID);
        assertThrows(NotFoundException.class, () -> service.get(DEPARTMENT_ID));
    }

    @Test
    void deleteNotFound() {
        assertThrows(NotFoundException.class, () -> service.delete(NOT_FOUND));
    }

    @Test
    void getAll() {
        DEPARTMENT_MATCHER.assertMatch(service.getAll(), DEPARTMENTS);
    }

    @Test
    void getAllTos() {
        DEPARTMENT_TO_MATCHER.assertMatch(getTos(service.getAll()), DEPARTMENTSTO);
    }
}
