package ru.testtask.sber.repository;

import ru.testtask.sber.model.Department;

import java.util.Collection;

public interface DepartmentRepository {

    Department save(Department department);

    Department get(int id);

    boolean delete(int id);

    Collection<Department> getAll();
}
