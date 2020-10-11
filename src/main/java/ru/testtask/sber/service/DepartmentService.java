package ru.testtask.sber.service;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.testtask.sber.model.Department;
import ru.testtask.sber.repository.DepartmentRepository;

import java.util.Collection;

import static org.slf4j.LoggerFactory.getLogger;
import static ru.testtask.sber.util.ValidationUtil.checkNotFoundWithId;

@Service
public class DepartmentService {
    private static final Logger log = getLogger(DepartmentService.class);

    @Autowired
    private final DepartmentRepository departmentRepository;

    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public Department create(Department department) {
        log.info("create department {}", department);
        return departmentRepository.save(department);
    }

    public Department update(Department department) {
        log.info("update department {}", department);
        return checkNotFoundWithId(departmentRepository.save(department), department.getId());
    }

    public Department get(int id) {
        log.info("get department id {}", id);
        return checkNotFoundWithId(departmentRepository.get(id), id);
    }

    public void delete(int id) {
        log.info("delete department id {}", id);
        checkNotFoundWithId(departmentRepository.delete(id), id);
    }

    public Collection<Department> getAll() {
        log.info("get all department");
        return departmentRepository.getAll();
    }
}
