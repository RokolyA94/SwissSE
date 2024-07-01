package e2e;

import com.epam.Main;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class E2ETest {

    private static final String EXPECTED_OUTPUT =  String.join("\r\n",
            "ReportDto[employee=Employee[id=123, firstName=Joe, lastName=Doe, salary=60000, managerId=null], hasMoreThanFourManager=false, managerCount=0, lessThenRequiredManagerSalary=false, moreThanAllowedManagerSalary=true, salaryCorrection=-37143.0]",
            "ReportDto[employee=Employee[id=124, firstName=Martin, lastName=Chekov, salary=45000, managerId=123], hasMoreThanFourManager=false, managerCount=0, lessThenRequiredManagerSalary=false, moreThanAllowedManagerSalary=true, salaryCorrection=-22143.0]",
            "ReportDto[employee=Employee[id=125, firstName=Bob, lastName=Ronstad, salary=47000, managerId=123], hasMoreThanFourManager=false, managerCount=0, lessThenRequiredManagerSalary=false, moreThanAllowedManagerSalary=true, salaryCorrection=-24143.0]",
            "ReportDto[employee=Employee[id=300, firstName=Alice, lastName=Hasacat, salary=50000, managerId=124], hasMoreThanFourManager=false, managerCount=1, lessThenRequiredManagerSalary=false, moreThanAllowedManagerSalary=true, salaryCorrection=-27143.0]",
            "ReportDto[employee=Employee[id=305, firstName=Brett, lastName=Hardleaf, salary=34000, managerId=300], hasMoreThanFourManager=false, managerCount=2, lessThenRequiredManagerSalary=false, moreThanAllowedManagerSalary=true, salaryCorrection=-11143.0]",
            "ReportDto[employee=Employee[id=409, firstName=Fname, lastName=Lname, salary=50000, managerId=305], hasMoreThanFourManager=false, managerCount=3, lessThenRequiredManagerSalary=false, moreThanAllowedManagerSalary=true, salaryCorrection=-27143.0]",
            "ReportDto[employee=Employee[id=507, firstName=Fnam2, lastName=Lname2, salary=34000, managerId=409], hasMoreThanFourManager=false, managerCount=4, lessThenRequiredManagerSalary=false, moreThanAllowedManagerSalary=true, salaryCorrection=-11143.0]");

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @Test
    void fullProcessTestSuccessful() {
        Main.main(new String[]{"src/test/resources/valid_test_input.csv"});
        assertEquals(EXPECTED_OUTPUT.trim(), outContent.toString().trim());
    }
}
