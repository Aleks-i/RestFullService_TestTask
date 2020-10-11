package ru.testtask.sber.web;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.testtask.sber.model.Department;
import ru.testtask.sber.model.DepartmentTo;
import ru.testtask.sber.service.DepartmentService;

import java.net.URI;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;
import static ru.testtask.sber.util.DepartmentUtil.getTos;
import static ru.testtask.sber.util.ValidationUtil.assureIdConsistent;
import static ru.testtask.sber.util.ValidationUtil.checkNew;

@RestController
@RequestMapping(value = DepartmentRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class DepartmentRestController {
    static final String REST_URL = "/sber/departments";
    private static final Logger log = getLogger(DepartmentRestController.class);

    @Autowired
    private DepartmentService departmentService;

    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Department> create(@RequestBody Department department) {
        checkNew(department);
        log.info("save department {}", department);
        Department createdDepartment = departmentService.create(department);
        URI ofNewRecourse = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL)
                .buildAndExpand(createdDepartment.getId()).toUri();
        return ResponseEntity.created(ofNewRecourse).body(createdDepartment);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void update(@RequestBody Department department, @PathVariable int id) {
        assureIdConsistent(department, id);
        log.info("update department {}", department);
        departmentService.update(department);
    }

    @GetMapping("/{id}")
    public Department get(@PathVariable int id) {
        log.info("get department {}", id);
        return departmentService.get(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        log.info("delete department {}", id);
        departmentService.delete(id);
    }

    @GetMapping()
    public List<DepartmentTo> getAll() {
        log.info("getAll department");
        return getTos(departmentService.getAll());
    }
}
