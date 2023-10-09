# Selenium-TestNG-Framework

## Overview
This framework is designed to automate web testing scenarios using Selenium and TestNG. It is built around the Page Object Model (POM) design pattern, ensuring a clean separation between test logic and page-specific code. The framework is capable of running tests in parallel, managing browser drivers efficiently, and generating detailed test reports using Extent Reports.

## Key Features
- Generates detailed Extent and Allure reports encapsulating step-by-step details of tests.
- Supports parallel execution of test cases to enhance execution efficiency.
- Produces a test execution log file for in-depth analysis and troubleshooting.
- Offers the capability to re-run failed test cases ensuring robustness of the testing process.
- Parallel test execution support.
- Utilizes Page Object Model (POM) for clean test script organization.
- Flexible browser driver management.
- Capability to re-run failed test cases.
- Configurable test setup through TestNG XML configuration.
- Easy integration with CI/CD pipelines.

## Required Setup
- Ensure Java and Maven are installed and configured.
- Clone the repository to your local machine.
- Import the project into your preferred IDE.

## Running Tests
1. Update the TestNG XML file with the desired test configuration.
2. Execute the Maven command: `mvn clean test`
3. Check the generated Extent Report at: `/test-output/report/test-report.html`

## Reporting
- Extent Report provides a detailed view of test execution, including passed, failed, and skipped tests.
- To view the Extent Report, open `/test-output/report/test-report.html` in your browser.

- ![image](https://github.com/durgesh14/Selenium-Framework/assets/34915741/e5878aeb-86e1-4367-ab99-1f993955907a)
- ![image](https://github.com/durgesh14/Selenium-Framework/assets/34915741/439c4e12-1657-4649-8ef1-4e28470d49c6)
- ![image](https://github.com/durgesh14/Selenium-Framework/assets/34915741/b9cbf293-349a-4502-be24-777e739803e0)




## Contributing
Feel free to fork the repository, create a feature branch, and open a pull request for review.

