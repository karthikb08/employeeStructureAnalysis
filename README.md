# BIG COMPANY Organizational Structure Analyzer

This Java application reads employee data from a CSV file, builds the organizational hierarchy, and performs analysis to:

- Identify managers who are **underpaid** or **overpaid** based on the average salary of their direct subordinates.
- Detect employees with **too long reporting lines** (more than 4 managers between them and the CEO).

## Key Components

- **Employee Model:** Represents employee data and relationships.
- **EmployeeRepository:** Loads employee data and builds the hierarchy.
- **OrgAnalyzerService:** Contains business logic to analyze salaries and reporting lines.
- **ManagerSalaryIssue & ReportingLineIssue:** Data classes representing analysis results.
- **EmployeeAnalysisApp:** Main class that runs the analysis and outputs the report.

## Usage

Run the application with the employee CSV file included in the classpath.  
The program prints a report highlighting salary issues and reporting line problems.

---
