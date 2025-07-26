package org.bigCompany.service;

import org.bigCompany.model.Employee;

public class ReportingLineIssue {
    public final Employee employee;
    public final int levels;
    public final int overBy;

    public ReportingLineIssue(Employee employee, int levels, int overBy) {
        this.employee = employee;
        this.levels = levels;
        this.overBy = overBy;
    }

    public Employee getEmployee() { return employee; }
    public int getLevels() { return levels; }
    public int getOverBy() { return overBy; }
}