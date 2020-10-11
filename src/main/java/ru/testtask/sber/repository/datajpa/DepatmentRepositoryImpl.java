package ru.testtask.sber.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.testtask.sber.model.Department;
import ru.testtask.sber.repository.DepartmentRepository;

import java.util.List;

@Repository
public class DepatmentRepositoryImpl implements DepartmentRepository {

    @Autowired
    private final CrudDepartmentRepository departmentRepository;

    public DepatmentRepositoryImpl(CrudDepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    @Transactional
    public Department save(Department department) {
        return departmentRepository.save(department);
    }

    @Override
    public Department get(int id) {
        return departmentRepository.findById(id).orElse(null);
    }

    @Override
    public boolean delete(int id) {
        return (departmentRepository.delete(id) != 0);
    }

    @Override
    public List<Department> getAll() {
        return departmentRepository.findAll();
    }
}
