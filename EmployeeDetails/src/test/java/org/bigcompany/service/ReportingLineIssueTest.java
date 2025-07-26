package org.bigcompany.service;

import org.junit.Test;
import static org.junit.Assert.*;
import org.bigCompany.model.Employee;
import org.bigCompany.service.ReportingLineIssue;

public class ReportingLineIssueTest {

    @Test
    public void testReportingLineIssueFields() {
        Employee emp = new Employee(1, "Test Employee", 50000, 2);
        int levels = 6;
        int overBy = levels - 4;

        ReportingLineIssue issue = new ReportingLineIssue(emp, levels, overBy);

        assertEquals(emp, issue.getEmployee());
        assertEquals(levels, issue.getLevels());
        assertEquals(overBy, issue.getOverBy());
    }
}
