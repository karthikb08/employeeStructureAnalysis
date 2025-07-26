import org.bigCompany.EmployeeAnalysisApp;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

public class EmployeeAnalysisAppTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    private EmployeeAnalysisApp employeeAnalysisApp;

    @Before
    public void setUp() {
        System.setOut(new PrintStream(outContent));
        employeeAnalysisApp = new EmployeeAnalysisApp();
    }

    @After
    public void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    public void testRunAnalysis() {
        String testCsvResource = "employees.csv";

        try {
            employeeAnalysisApp.runAnalysis(testCsvResource);
            String output = outContent.toString();
            assertNotNull(output);
            assertTrue(output.contains("--- Manager Salary Issues ---") || output.contains("No managers found with salary issues."));
            assertTrue(output.contains("--- Employees with Long Reporting Lines ---") || output.contains("No employees with too long reporting lines."));

        } catch (Exception e) {
            fail("runAnalysis threw an exception: " + e.getMessage());
        }
    }
}
