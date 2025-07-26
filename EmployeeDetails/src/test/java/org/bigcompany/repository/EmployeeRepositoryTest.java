package org.bigcompany.repository;

import org.bigCompany.model.Employee;
import org.bigCompany.repository.EmployeeRepository;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Collection;

import static org.junit.Assert.*;

public class EmployeeRepositoryTest {

    private EmployeeRepository employeeRepository;

    @Before
    public void setUp() throws IOException {
        employeeRepository = new EmployeeRepository("employees.csv");
    }

    @Test
    public void testLoadAllEmployees() {
        Collection<Employee> employees = employeeRepository.getAllEmployees();
        assertNotNull(employees);
        assertFalse(employees.isEmpty());
        assertTrue(employees.size() >= 5);
    }

    @Test
    public void testGetCEO() {
        Employee ceo = employeeRepository.getCEO();
        assertNotNull(ceo);
        assertNull(ceo.getManagerId());
    }

    @Test
    public void testHierarchyLinking() {
        Employee ceo = employeeRepository.getCEO();
        assertFalse(ceo.getSubordinates().isEmpty());

        for (Employee employee : ceo.getSubordinates()) {
            assertEquals(ceo.getId(), employee.getManagerId().intValue());
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void testThrowsOnInvalidManager() throws IOException {
        new EmployeeRepository("invalid-manager.csv");
    }
}
