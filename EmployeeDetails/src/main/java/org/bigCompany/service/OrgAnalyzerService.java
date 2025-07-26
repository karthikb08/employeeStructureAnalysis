package org.bigCompany.service;

import org.bigCompany.model.Employee;
import org.bigCompany.repository.EmployeeRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrgAnalyzerService {
    private final EmployeeRepository employeeRepository;

    public OrgAnalyzerService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<ManagerSalaryIssue> findManagersWithSalaryIssues() {
        List<ManagerSalaryIssue> managerSalaryIssues = new ArrayList<>();
        for (Employee employee : employeeRepository.getAllEmployees()) {
            List<Employee> subordinates = employee.getSubordinates();
            if (subordinates.isEmpty()) continue;
            double avgSubSalary = subordinates.stream().mapToDouble(Employee::getSalary).average().orElse(0);
            double minManagerSalary = avgSubSalary * 1.2;
            double maxManagerSalary = avgSubSalary * 1.5;
            if (employee.getSalary() < minManagerSalary) {
                managerSalaryIssues.add(new ManagerSalaryIssue(employee, ManagerSalaryIssue.IssueType.OVERPAID, minManagerSalary - employee.getSalary()));
            } else if (employee.getSalary() > maxManagerSalary) {
                managerSalaryIssues.add(new ManagerSalaryIssue(employee, ManagerSalaryIssue.IssueType.UNDERPAID, employee.getSalary() - maxManagerSalary));
            }
        }
        return managerSalaryIssues;
    }

    public List<ReportingLineIssue> findEmployeesWithLongReportingLines() {
        List<ReportingLineIssue> issues = new ArrayList<>();
        Map<Integer, Integer> cache = new HashMap<>();
        for (Employee employee : employeeRepository.getAllEmployees()) {
            int levels = countManagersToCEO(employee, cache);
            if (levels > 4) {
                issues.add(new ReportingLineIssue(employee, levels, levels - 4));
            }
        }
        return issues;
    }

    public int countManagersToCEO(Employee employee, Map<Integer, Integer> cache) {
        if (employee.getManagerId() == null) return 0;
        if (cache.containsKey(employee.getId())) return cache.get(employee.getId());
        Employee manager = employeeRepository.findById(employee.getManagerId());
        if (manager == null) return 0;
        int count = 1 + countManagersToCEO(manager, cache);
        cache.put(employee.getId(), count);
        return count;
    }
}
