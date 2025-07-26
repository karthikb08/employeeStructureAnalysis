package org.bigcompany.model;

import org.bigCompany.model.Employee;
import org.junit.Test;
import static org.junit.Assert.*;

public class EmployeeTest {

    @Test
    public void testEmployeeCreationAndProperties() {
        Employee e = new Employee(1, "karthik", 75000.0, null);
        assertEquals(1, e.getId());
        assertEquals("karthik", e.getName());
        assertEquals(75000.0, e.getSalary(), 0.001);
        assertNull(e.getManagerId());
        assertNotNull(e.getSubordinates());
        assertTrue(e.getSubordinates().isEmpty());
    }

    @Test
    public void testAddSubordinate() {
        Employee manager = new Employee(1, "Manager", 100000, null);
        Employee subordinate = new Employee(2, "Subordinate", 50000, 1);
        manager.getSubordinates().add(subordinate);
        assertEquals(1, manager.getSubordinates().size());
        assertEquals(subordinate, manager.getSubordinates().get(0));
    }
}

