package org.bigcompany.service;

import org.bigCompany.repository.EmployeeRepository;
import org.bigCompany.service.ManagerSalaryIssue;
import org.bigCompany.service.OrgAnalyzerService;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.*;

public class ManagerSalaryIssueTest {

    private OrgAnalyzerService orgAnalyzerService;
    private EmployeeRepository employeeRepository;

    @Before
    public void setUp() throws IOException {
        // Initialize repository and service just like in OrgAnalyzerServiceTest
        employeeRepository = new EmployeeRepository("employees.csv");
        orgAnalyzerService = new OrgAnalyzerService(employeeRepository);
    }

    @Test
    public void testFindManagersWithSalaryIssues() {
        List<ManagerSalaryIssue> managerSalaryIssues = orgAnalyzerService.findManagersWithSalaryIssues();
        assertNotNull(managerSalaryIssues);
        for (ManagerSalaryIssue salaryIssue : managerSalaryIssues) {
            assertNotNull(salaryIssue.getManager());
            assertTrue(salaryIssue.getAmount() > 0);
            String type = salaryIssue.getType().toString();
            assertTrue(type.equals("UNDERPAID") || type.equals("OVERPAID"));
        }
    }
}
