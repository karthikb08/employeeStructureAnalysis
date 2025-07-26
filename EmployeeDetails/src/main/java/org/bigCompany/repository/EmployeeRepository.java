package org.bigCompany.repository;

import org.bigCompany.model.Employee;

import java.io.*;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class EmployeeRepository {
    private final Map<Integer, Employee> employees = new HashMap<>();
    private Employee ceo;

    public EmployeeRepository(String resourcePath) throws IOException {
        loadFromResource(resourcePath);
    }

    private void loadFromResource(String resourcePath) throws IOException {
        try (InputStream is = getClass().getClassLoader().getResourceAsStream(resourcePath)) {
            if (is == null) {
                throw new IllegalArgumentException("Resource not found: " + resourcePath);
            }
            try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is))) {
                String header = bufferedReader.readLine(); // Skip CSV header
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    if (line.trim().isEmpty()) continue; // skipped empty lines

                    String[] resourceValue = line.split(",", -1);
                    if (resourceValue.length < 5) {
                        System.err.println("Skipping malformed line: " + line);
                        continue;
                    }

                    int id = Integer.parseInt(resourceValue[0].trim());
                    String firstName = resourceValue[1].trim();
                    String lastName = resourceValue[2].trim();
                    double salary = Double.parseDouble(resourceValue[3].trim());
                    Integer managerId = resourceValue[4].trim().isEmpty() ? null : Integer.parseInt(resourceValue[4].trim());
                    String fullName = firstName + " " + lastName;

                    Employee emp = new Employee(id, fullName, salary, managerId);
                    employees.put(id, emp);
                }
            }
        }

        //print all loaded employee ids
        System.out.println("Loaded employee IDs: " + employees.keySet());

        // Link subordinates to managers
        for (Employee employee : employees.values()) {
            Integer managerId = employee.getManagerId();
            if (managerId == null) {
                //CEO identified by null manager ID
                ceo = employee;
            } else {
                Employee manager = employees.get(managerId);
                if (manager != null) {
                    manager.getSubordinates().add(employee);
                } else {
                    throw new IllegalArgumentException("Manager id not found: " + managerId);
                }
            }
        }

        if (ceo == null) {
            throw new IllegalArgumentException("No CEO found (employee with null managerId).");
        }
    }

    public Collection<Employee> getAllEmployees() {
        return employees.values();
    }

    public Employee getCEO() {
        return ceo;
    }

    public Employee findById(int id) {
        return employees.get(id);
    }
}
