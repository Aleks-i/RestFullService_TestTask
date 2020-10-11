package ru.testtask.sber.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "department")
public class Department extends AbstractBaseEntity {

    @Column(name = "name")
    private String name;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "department")
    private List<Employee> employees;

    public Department(Department department) {
        this(department.getId(), department.getName());
    }

    public Department(Integer id, String name) {
        super(id);
        this.name = name;
    }

    public Department() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    @Override
    public String toString() {
        return super.toString() +
                "Department{" +
                "name='" + name + '\'' +
                '}';
    }
}
