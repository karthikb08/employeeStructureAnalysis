package org.bigCompany.model;

import java.util.ArrayList;
import java.util.List;

public class Employee {
    private int id;
    private String name;
    private double salary;
    private Integer managerId;
    private List<Employee> subordinates = new ArrayList<>();

    public Employee(int id, String name, double salary, Integer managerId) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.managerId = managerId;
    }

    public int getId() { return id; }

    public String getName() { return name; }

    public double getSalary() { return salary; }

    public Integer getManagerId() { return managerId; }

    public List<Employee> getSubordinates() { return subordinates; }

    @Override
    public String toString() {
        return String.format("%s (id=%d, salary=%.2f)", name, id, salary);
    }
}
