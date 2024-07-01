# Swiss Se Report generator

## Task description
BIG COMPANY is employing a lot of employees. Company would like to analyze its organizational
structure and identify potential improvements. Board wants to make sure that every manager earns
at least 20% more than the average salary of its direct subordinates, but no more than 50% more
than that average. Company wants to avoid too long reporting lines, therefore we would like to
identify all employees which have more than 4 managers between them and the CEO
Write a simple program which will read the file and report:
- which managers earn less than they should, and by how much
- which managers earn more than they should, and by how much
- which employees have a reporting line which is too long, and by how much

## Requirements:
- JDK 22
- MAVEN 3


### CSV example:
```bash
id,firstName,lastName,salary,managerId
123,Joe,Doe,60000,
124,Martin,Chekov,45000,123
125,Bob,Ronstad,47000,123
300,Alice,Hasacat,50000,124
305,Brett,Hardleaf,34000,300
409,Fname,Lname,50000,305
507,Fnam2,Lname2,34000,409
```


## Assumptions
### CSV processing
- Invalid format handling. Assumption: Throw exception, do not process the file.
- Missing CEO value. Assumption: Unhandled CEO. (The task require CEO in every cases)
### Report 
- Output looks like. Assumption: Combine all the reportable state into one line and attach the raw values of the employee.
- Four manager is allowed or It's fine. Assumption:m Only report if more then four.
# Output
- This implementation only support the CLI input and output.
Output example:
```
[ReportDto[employee=Employee[id=123, firstName=Joe, lastName=Doe, salary=60000, managerId=null], hasMoreThanFourManager=false, managerCount=0, lessThenRequiredManagerSalary=true, moreThanAllowedManagerSalary=false, salaryCorrection=-37143.0],
 ReportDto[employee=Employee[id=124, firstName=Martin, lastName=Chekov, salary=45000, managerId=123], hasMoreThanFourManager=false, managerCount=0, lessThenRequiredManagerSalary=true, moreThanAllowedManagerSalary=false, salaryCorrection=-22143.0], 
 ReportDto[employee=Employee[id=125, firstName=Bob, lastName=Ronstad, salary=47000, managerId=123], hasMoreThanFourManager=false, managerCount=0, lessThenRequiredManagerSalary=true, moreThanAllowedManagerSalary=false, salaryCorrection=-24143.0], 
 ReportDto[employee=Employee[id=300, firstName=Alice, lastName=Hasacat, salary=50000, managerId=124], hasMoreThanFourManager=false, managerCount=1, lessThenRequiredManagerSalary=true, moreThanAllowedManagerSalary=false, salaryCorrection=-27143.0], 
 ReportDto[employee=Employee[id=305, firstName=Brett, lastName=Hardleaf, salary=34000, managerId=300], hasMoreThanFourManager=false, managerCount=2, lessThenRequiredManagerSalary=true, moreThanAllowedManagerSalary=false, salaryCorrection=-11143.0], 
 ReportDto[employee=Employee[id=409, firstName=Fname, lastName=Lname, salary=50000, managerId=305], hasMoreThanFourManager=false, managerCount=3, lessThenRequiredManagerSalary=true, moreThanAllowedManagerSalary=false, salaryCorrection=-27143.0], 
 ReportDto[employee=Employee[id=507, firstName=Fnam2, lastName=Lname2, salary=34000, managerId=409], hasMoreThanFourManager=false, managerCount=4, lessThenRequiredManagerSalary=true, moreThanAllowedManagerSalary=false, salaryCorrection=-11143.0]]
```
## How to build it
- Please ensure that the [requirements](#requirements) are met
- Go to the root folder of the project and execute the following
```bash
mvn clean install  
```
- If the build is success the following command can be used to test the implementation.
```bash 
java -jar./target/SwissSe-1.0-SNAPSHOT.jar <path to the file>
```