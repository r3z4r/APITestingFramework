# Rest-assured Basic Automation Framework 

Framework for writing maintainable REST API tests in Java.
it tests simple functionalities of [gorest](https://gorest.co.in) public [users api](https://gorest.co.in/public/v1/users) interface.

## Prerequisites
* Java JDK 11+
* Maven installed and in your classpath

## Where to find the tests
You can open each test class on `src\test\java` and modify or execute them.

### Running the test suites

The test suites can be run directly by your IDE or by command line.
If you run `mvn test` testNG testsuite will execute. you can find/edit testNG xml file in main project folder (adding/removing tests, setting priority, groups, ...)

### Generating the test report

This project uses surefire-reports to generate the test report.
you can find generated report in "/target/surefire-reports" folder,
in several formats.

## Project Structure

### resource
`config.properties` file to set environment variables, such as base_url, port, token.
`testData.json` used as initial testdata,
also other testdata can be placed in this folder

### src/main/java

####constants
used for global config classes which is used though out the project such as end points

####helpers
used for helper function which is used in test classes, for more Readability

#### model
Model and Builder class to mapping objects thought serialization and deserialization

#### utils
* `ConfigManager` load the properties file and match the attributes
* `JsonMapper` Class to map json files to POJO models,
* `ExcelReader` reads excel files (.xls,.xlsx)
* `Common` other common utilities place here

