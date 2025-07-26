package org.bigcompany.service;

import org.bigCompany.model.Employee;
import org.bigCompany.repository.EmployeeRepository;
import org.bigCompany.service.ManagerSalaryIssue;
import org.bigCompany.service.OrgAnalyzerService;
import org.bigCompany.service.ReportingLineIssue;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.*;

public class OrgAnalyzerServiceTest {

    private OrgAnalyzerService orgAnalyzerService;
    private EmployeeRepository employeeRepository;

    @Before
    public void setUp() throws IOException {
        employeeRepository = new EmployeeRepository("employees.csv");
        orgAnalyzerService = new OrgAnalyzerService(employeeRepository);
    }

    @Test
    public void testFindEmployeesWithLongReportingLines() {
        List<ReportingLineIssue> issues = orgAnalyzerService.findEmployeesWithLongReportingLines();
        assertNotNull(issues);

        for (ReportingLineIssue issue : issues) {
            assertTrue(issue.getLevels() > 4);
            assertEquals(issue.getLevels() - 4, issue.getOverBy());
        }
    }

    @Test
    public void testCountManagersToCEOCaching() {
        OrgAnalyzerService service = new OrgAnalyzerService(employeeRepository);
        java.util.Map<Integer, Integer> cache = new java.util.HashMap<>();

        for (Employee e : employeeRepository.getAllEmployees()) {
            int levelsFirst = service.countManagersToCEO(e, cache);
            int levelsSecond = service.countManagersToCEO(e, cache);
            assertEquals(levelsFirst, levelsSecond);
        }
    }
}
