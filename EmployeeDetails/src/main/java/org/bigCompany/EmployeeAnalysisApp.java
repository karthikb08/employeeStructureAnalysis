package org.bigCompany;

import org.bigCompany.repository.EmployeeRepository;
import org.bigCompany.service.OrgAnalyzerService;
import org.bigCompany.service.ManagerSalaryIssue;
import org.bigCompany.service.ReportingLineIssue;
import org.bigCompany.model.Employee;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

public class EmployeeAnalysisApp {
    static final Logger logger = Logger.getLogger(org.bigCompany.EmployeeAnalysisApp.class.getName());

    public static void main(String[] args) {
        String csvResource = "employees.csv";
        EmployeeAnalysisApp employeeAnalysisApp = new EmployeeAnalysisApp();
        try {
            employeeAnalysisApp.runAnalysis(csvResource);
        } catch (IOException e) {
            System.err.println("Error processing employee data: " + e.getMessage());
            e.printStackTrace();
        }
    }

      public void runAnalysis(String csvResource) throws  IOException{
            try {
                EmployeeRepository repo = new EmployeeRepository(csvResource);
                OrgAnalyzerService analyzer = new OrgAnalyzerService(repo);

                List<ManagerSalaryIssue> issues = analyzer.findManagersWithSalaryIssues();

                System.out.println("\n--- Manager Salary Issues ---");

                if (issues.isEmpty()) {
                    System.out.println("No managers found with salary issues.");
                }

                for (ManagerSalaryIssue managerSalaryIssue : issues) {
                    System.out.printf("%s: %s by %.2f (subordinates avg salary: %.2f)\n",
                            managerSalaryIssue.manager.getName(),
                            managerSalaryIssue.getType(),
                            managerSalaryIssue.amount,
                            managerSalaryIssue.manager.getSubordinates().stream().mapToDouble(Employee::getSalary).average().orElse(0));
                    logger.info("Subordinates details printed !");
                }

                List<ReportingLineIssue> lineIssues = analyzer.findEmployeesWithLongReportingLines();

                System.out.println("\n--- Employees with Long Reporting Lines ---");

                if (lineIssues.isEmpty()) {
                    System.out.println("No employees with too long reporting lines.");
                }

                for (ReportingLineIssue reportingLineIssue : lineIssues) {
                    System.out.printf("%s: %d levels (over by %d)\n",
                            reportingLineIssue.employee.getName(), reportingLineIssue.levels, reportingLineIssue.overBy);
                }
                logger.info("executed employee block");
            } catch (IOException e) {
                System.err.println("Error processing employee data: " + e.getMessage());
                e.printStackTrace();
            }
        }
}