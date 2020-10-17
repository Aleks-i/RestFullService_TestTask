package ru.testtask.sber.model;

public class DepartmentTo {
    private final Integer id;

    private final String name;

    private final String averageSalary;

    public DepartmentTo(Integer id, String name, String averageSalary) {
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

    public String getAverageSalary() {
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
