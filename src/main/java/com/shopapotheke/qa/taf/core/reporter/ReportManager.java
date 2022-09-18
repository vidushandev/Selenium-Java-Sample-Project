package com.shopapotheke.qa.taf.core.reporter;

import org.testng.ITestResult;

/**
 * Act as an Wrapper to the Report plugins.
 * This helps easily remove one plugin and plugin another using the ReportManager Class
 */
public class ReportManager {

    private ReportManager() {
        // making constructor private to restrict unwanted instantiation
    }

    /**
     * Initialize Test Report
     */
    public static void initializeReport() {
        ExtentReportManager.initializeReport();
    }

    /**
     * Initialize Test Case Information in Test Report
     *
     * @param testName    - Test Case name
     * @param description - Test Case Description
     */
    public static void initializeTest(String testName, String description) {
        ExtentReportManager.initializeTest(testName, description);
    }

    /**
     * Update Test Case information in Test Report
     *
     * @param iTestResult {@link ITestResult}
     */
    public static void updateTestStatus(ITestResult iTestResult) {
        ExtentReportManager.updateTestStatus(iTestResult);
    }

    /**
     * Generate Test Report at the end of the execution
     */
    public static void createReport() {
        ExtentReportManager.createReport();
    }

    /**
     * Add Test Steps to the Test Report
     *
     * @param step_info Required step name to add
     */
    public static void addTestStepInformation(String step_info) {
        ExtentReportManager.addStepInformation(step_info);
    }

    /**
     * Add Test Steps with more information
     * (Add to the methods in PageObjects to get the out put to report)
     *
     * @param step_info   required step name to add
     * @param description required information to log with this step
     */
    public static void addTestStepInformation(String step_info, String description) {
        ExtentReportManager.addStepInformation(step_info, description);
    }

    /**
     * Add Code Block to the Test Report in Current test Step.
     * This will add inside a separate block with the code formatting and users will be able to expand and see the code block if required
     *
     * @param information Information about the code block
     * @param code        required code block to add (Eg. API Response, JSON output ...)
     */
    public static void addCodeBlock(String information, String code) {
        ExtentReportManager.addCodeBlock(information, code);
    }

    /**
     * Add Test Steps with more information
     * (Add to the methods in PageObjects to get the out put to report)
     *
     * @param step_info   required step name to add
     * @param description required information to log with this step
     * @param screenshot  [true] to take the screen shot
     */
    public static void addTestStepInformation(String step_info, String description, boolean screenshot) {
        ExtentReportManager.addStepInformation(step_info, description, screenshot);
    }

    /**
     * Add logs from required point of Test Case to the report
     * (add to any point in the Test Case to log)
     *
     * @param description required description to log
     * @param screenshot  [true] to take the screenshot at given point
     */
    public static void addTestInformation(String description, boolean screenshot) {
        ExtentReportManager.addTestInformation(description, screenshot);
    }

    /**
     * Assign Category for the test case as required
     *
     * @param category - required categories to assign
     */
    public static void assignCategory(String... category) {
        ExtentReportManager.assignCategory(category);
    }

    /**
     * Add logs to the Top of the Test Case
     *
     * @param description required description to log
     */
    public static void addTestCaseInformation(String description) {
        ExtentReportManager.addTestCaseInformation(description);
    }

    /**
     * Attach Successful Verification logs and Screenshots in the respective testcase in Test Report
     *
     * @param description - verification message or any description to log
     */
    public static void addSuccessVerificationLogs(String description) {
        ExtentReportManager.addSuccessVerificationLogs(description);
    }

    /**
     * Attach Failed Verification logs and Screenshots in the respective testcase in Extent Report (For Soft Assertions)
     *
     * @param description - verification message or any description to log
     */
    public static void addFailedVerificationLogs(String description) {
        ExtentReportManager.addFailedVerificationLogs(description);
    }

}
