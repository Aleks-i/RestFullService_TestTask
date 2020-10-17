package ru.testtask.sber;

import ru.testtask.sber.model.Department;
import ru.testtask.sber.model.DepartmentTo;

import java.util.List;

import static ru.testtask.sber.model.AbstractBaseEntity.START_SEQ;

public class DepartmentTestData {
    public static TestMatcher<Department> DEPARTMENT_MATCHER = TestMatcher.usingFieldsWithIgnoringAssertions(Department.class, "employees");
    public static TestMatcher<DepartmentTo> DEPARTMENT_TO_MATCHER = TestMatcher.usingFieldsWithIgnoringAssertions(DepartmentTo.class, "employees");

    public static final int NOT_FOUND = 10;
    public static final int DEPARTMENT_ID = START_SEQ;

    public static final Department DEPARTMENT = new Department(DEPARTMENT_ID, "отдел 1");
    public static final Department DEPARTMENT_1 = new Department(DEPARTMENT_ID + 1, "отдел 2");

    public static final DepartmentTo DEPARTMENT_TO = new DepartmentTo(DEPARTMENT_ID, "отдел 1", "140,000.4");
    public static final DepartmentTo DEPARTMENT_TO_1 = new DepartmentTo(DEPARTMENT_ID + 1, "отдел 2","100,000.77");

    public static final List<DepartmentTo> DEPARTMENTSTO = List.of(DEPARTMENT_TO, DEPARTMENT_TO_1);

    public static final List<Department> DEPARTMENTS = List.of(DEPARTMENT, DEPARTMENT_1);

    public static Department getNew() {
        return new Department(null, "Новый отдел");
    }

    public static Department getUpdated() {
        return new Department(DEPARTMENT_ID, "обновленный отдел");
    }


}
