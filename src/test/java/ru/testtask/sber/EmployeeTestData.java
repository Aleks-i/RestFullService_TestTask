package ru.testtask.sber;

import ru.testtask.sber.model.Employee;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static ru.testtask.sber.model.AbstractBaseEntity.START_SEQ;

public class EmployeeTestData {
    public static TestMatcher<Employee> EMPLOYEE_MATCHER = TestMatcher.usingFieldsWithIgnoringAssertions(Employee.class, "department");

    public static final int NOT_FOUND = 10;
    public static final int EMPLOYEE_ID = START_SEQ + 2;
    public static final int DEPARTMENT_ID = START_SEQ;
    public static final LocalDate DATE_START_FOR_IVANOV = LocalDate.of(2000, Month.JANUARY, 1);
    public static final LocalDate DATE_END_FOR_IVANOV = LocalDate.of(2001, Month.JANUARY, 10);
    public static final LocalDate DATE_END_FOR_SIDOROV = LocalDate.of(2002, Month.JANUARY, 10);


    public static final Employee EMPLOYEE_1 = new Employee(EMPLOYEE_ID, "Иванов", "Иван",
            "Иванович", LocalDate.of(2000, Month.JANUARY, 10), new BigDecimal("150000.14"));
    public static final Employee EMPLOYEE_2 = new Employee(EMPLOYEE_ID + 1, "Петров", "Пётр",
            "Петрович", LocalDate.of(2001, Month.JANUARY, 10), new BigDecimal("130000.66"));
    public static final Employee EMPLOYEE_3 = new Employee(EMPLOYEE_ID + 2, "Сидоров", "Сидр",
            "Сидорович", LocalDate.of(2002, Month.JANUARY, 10), new BigDecimal("100000.77"));

    public static final List<Employee> EMPLOYEES = List.of(EMPLOYEE_1, EMPLOYEE_2);
    public static final List<Employee> EMPLOYEES_FOR_GETBETWEENINCLUSIVE = List.of(EMPLOYEE_1);
    public static final List<Employee> EMPLOYEES_FOR_GETBYDATE = List.of(EMPLOYEE_3);

    public static Employee getNew() {
        return new Employee(null, "Новая фамилия", "Новое имя", "Новое отчество",
                LocalDate.of(2020, Month.FEBRUARY, 7), new BigDecimal("15.33"));
    }

    public static Employee getUpdated() {
        return new Employee(EMPLOYEE_ID, "обновленная фамилия" , "обновленное имя", "обновленное отчество", LocalDate.of(2000, Month.JANUARY, 11), new BigDecimal("13.13"));
    }
}
