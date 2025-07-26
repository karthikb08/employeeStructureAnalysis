package org.bigCompany.service;

import org.bigCompany.model.Employee;

public class ManagerSalaryIssue {
    public final Employee manager;
    public final double amount;
    private IssueType type;

    public ManagerSalaryIssue(Employee manager, IssueType typeString, double amount) {
        this.manager = manager;
        this.type = IssueType.valueOf(String.valueOf(typeString));
        this.amount = amount;
    }

    public Employee getManager() {
        return manager;
    }

    public IssueType getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }

    public enum IssueType {
        UNDERPAID,
        OVERPAID
    }
}
