package ru.testtask.sber.model;

import java.math.BigDecimal;

public class DepartmentTo {
    private final Integer id;

    private final String name;

    private final BigDecimal averageSalary;

    public DepartmentTo(Integer id, String name, BigDecimal averageSalary) {
        this.id = id;
        this.name = name;
        this.averageSalary = averageSalary;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getAverageSalary() {
        return averageSalary;
    }

    @Override
    public String toString() {
        return "DepartmentTo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", averageSalary=" + averageSalary +
                '}';
    }
}
