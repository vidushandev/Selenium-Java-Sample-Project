package com.shopapotheke.qa.taf.core.reporter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.shopapotheke.qa.taf.core.config.Configurations;
import com.shopapotheke.qa.taf.core.util.ScreenshotUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestResult;

import java.util.Date;

/**
 * Manage Extent Report related Configurations and features
 */
public class ExtentReportManager {
    private static Logger log = LogManager.getLogger(ExtentReportManager.class);
    private static ExtentSparkReporter sparkReporter;
    private static ExtentReports extentReports;
    private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();
    private static ThreadLocal<ExtentTest> extentTestSteps = new ThreadLocal<>();
    private static ThreadLocal<ExtentTest> verifications = new ThreadLocal<>();
    private static String REPORT_DIR;

    private static final String TEST_REPORT_DIR = "/target/test-output";
    private static final String TEST_REPORT_NAME = "index.html";
    private static final String SCREENSHOT_RELATIVE_PATH = "./screenshots/";

    public static ThreadLocal<Integer> METHOD_POSITION = new ThreadLocal<>();
    /**
     * Set all configurations and Initialize Extent Report
     */
    public static void initializeReport() {
        REPORT_DIR = System.getProperty("user.dir") + TEST_REPORT_DIR;
        // initialize ExtentReports and attach the ExtentSparkReporter
        sparkReporter = new ExtentSparkReporter(REPORT_DIR + "/" + TEST_REPORT_NAME);
        extentReports = new ExtentReports();
        extentReports.attachReporter(sparkReporter);
        extentReports.setReportUsesManualConfiguration(true);
        sparkReporter.setStartTime(new Date());


        extentReports.setSystemInfo("TEST_BROWSER", Configurations.TEST_BROWSER);
        extentReports.setSystemInfo("TEST_URL", Configurations.TEST_URL);

        //configuration items to change the look and feel
        sparkReporter.config().setDocumentTitle("CTAF Execution");
        sparkReporter.config().setReportName("Cinglevue Test Automation Execution Report");
        sparkReporter.config().setTheme(Theme.STANDARD);
        sparkReporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");
        sparkReporter.config().enableOfflineMode(true);
        sparkReporter.config().enableTimeline(true);

        //  sparkReporter.setAnalysisStrategy(AnalysisStrategy.SUITE);
        log.info("Initialize Extent Report. Report Location: " + REPORT_DIR + "/" + TEST_REPORT_NAME);
    }


    /**
     * Initialize Test Case Information in Extent Report
     *
     * @param testName    - Test Case name
     * @param description - Test Case Description
     */
    public static void initializeTest(String testName, String description) {
        extentTest.set(extentReports.createTest(testName, description));
        extentTest.get().getModel().setStartTime(new Date());
        extentTest.get().info(MarkupHelper.createTable(new String[][]{{"Configurations"}, {"Browser", Configurations.TEST_BROWSER}, {"Test Url", Configurations.TEST_URL}}));
        //  extentTestSteps.set(extentTest.get().createNode("Test Steps", "All Test Steps"));
        //  verifications.set(extentTest.get().createNode("Successful Verifications", "All Success Verifications"));
        METHOD_POSITION.set(0);
    }


    /**
     * Update Test Case information in Extent Report
     *
     * @param iTestResult {@link ITestResult}
     */
    public static void updateTestStatus(ITestResult iTestResult) {
        log.debug("[updateTestStatus]Test Status: " + iTestResult.getStatus());
        extentTest.get().getModel().setEndTime(new Date());
        // if noTest is created, create the test (if failed/skipped even before starting the test. Eg. failed when dataProvider not available )
        if (extentTest.get() == null) {
            log.debug("[updateTestStatus] creating a test node for " + iTestResult.getMethod().getQualifiedName());
            initializeTest(iTestResult.getMethod().getQualifiedName(), iTestResult.getMethod().getDescription());
        }

        if (iTestResult.getStatus() == ITestResult.FAILURE) {
            log.debug("[updateTestStatus]: Test Failure");
            updateTestFailure(iTestResult);
        } else if (iTestResult.getStatus() == ITestResult.SUCCESS) {
            log.debug("[updateTestStatus]: Test Success");
            updateTestPass(iTestResult);
        }

        extentReports.flush();
    }

    /**
     * Update Test Failure status in Extent Report
     *
     * @param iTestResult {@link ITestResult}
     */
    private static void updateTestFailure(ITestResult iTestResult) {
        extentTest.get().log(Status.FAIL, MarkupHelper.createLabel(iTestResult.getName() + " :: FAILED", ExtentColor.RED));
        String[][] strings = {{"Error Occurred: " + iTestResult.getThrowable().getMessage()}};
        try {
            // append screenshot to the failed step
            String fileName = SCREENSHOT_RELATIVE_PATH + ScreenshotUtil.takeScreenshot(iTestResult.getName());
            extentTest.get().debug(iTestResult.getThrowable(), MediaEntityBuilder.createScreenCaptureFromPath(fileName).build());
            // add to steps log
            extentTestSteps.get().fail(String.format("<p style=\"color:red\">%s</p>", iTestResult.getThrowable().getMessage()), MediaEntityBuilder.createScreenCaptureFromPath(fileName).build());
        } catch (Exception e) {
            log.error("Error When updating results in test report. " + e.getMessage(), e);
        }
    }


    /**
     * Update Test Success status in Extent Report
     *
     * @param iTestResult {@link ITestResult}
     */
    private static void updateTestPass(ITestResult iTestResult) {
        extentTest.get().log(Status.PASS, MarkupHelper.createLabel(iTestResult.getName() + " :: PASSED", ExtentColor.GREEN));
    }

    /**
     * Generate Extent Report
     */
    public static void createReport() {
        sparkReporter.setEndTime(new Date());
        extentReports.flush();
    }


    /**
     * Add Test Steps to the Test Report
     *
     * @param step_info Required step name to add
     */
    public static void addStepInformation(String step_info) {
        String space = methodPosition(METHOD_POSITION.get()); // create respective space based on the method position
        extentTestSteps.get().log(Status.DEBUG, space + step_info);
    }


    /**
     * Add Test Steps with more information
     * (Add to the methods in PageObjects to get the out put to report)
     *
     * @param step_info   required step name to add
     * @param description required information to log with this step
     */
    public static void addStepInformation(String step_info, String description) {
        String space = methodPosition(METHOD_POSITION.get()); // create respective space based on the method position
        extentTestSteps.get().log(Status.DEBUG, String.format("%s :<br/> %s[Step info] <code>%s</code>", space + step_info, space, description));
    }


    /**
     * Add Code Block to the Test Report in Current test Step.
     * This will add inside a separate block with the code formatting and users will be able to expand and see the code block if required
     *
     * @param information Information about the code block
     * @param code        required code block to add (Eg. API Response, JSON output ...)
     */
    public static void addCodeBlock(String information, String code) {
        extentTestSteps.get().log(Status.DEBUG, MarkupHelper.createCodeBlock(information + ":\n" + code));
    }


    /**
     * Add Test Steps with more information
     * (Add to the methods in PageObjects to get the out put to report)
     *
     * @param step_info   required step name to add
     * @param description required information to log with this step
     * @param screenshot  [true] to take the screen shot
     */
    public static void addStepInformation(String step_info, String description, boolean screenshot) {
        String space = methodPosition(METHOD_POSITION.get());
        if (screenshot) {
            try {
                // append screenshot to the failed step
                String fileName = SCREENSHOT_RELATIVE_PATH + ScreenshotUtil.takeScreenshot("custom_screenshot");
                extentTestSteps.get().log(Status.DEBUG, String.format("%s :<br/> %s[Step info] <code>%s</code><br>", space + step_info, space, description),
                        MediaEntityBuilder.createScreenCaptureFromPath(fileName).build());
            } catch (Exception e) {
                log.error("Error When updating results in test report. " + e.getMessage(), e);
            }
        } else {
            addStepInformation(step_info, description);
        }
    }


    /**
     * Attach Successful Verification logs and Screenshots in the respective testcase in Extent Report
     *
     * @param description - verification message or any description to log
     */
    public static void addSuccessVerificationLogs(String description) {
        try {
            // append screenshot of the success verification step
            String fileName = SCREENSHOT_RELATIVE_PATH + ScreenshotUtil.takeScreenshot("successful_verification");
            verifications.get().log(Status.PASS, String.format("<code>%s</code><br>", description), MediaEntityBuilder.createScreenCaptureFromPath(fileName).build());
            // add log to the step logs
            String space = methodPosition(METHOD_POSITION.get());
            extentTestSteps.get().log(Status.PASS, String.format("%s<code style='color:#549559'>%s</code><br>%s", space, description, space), MediaEntityBuilder.createScreenCaptureFromPath(fileName).build());
        } catch (Exception e) {
            log.error("Error When updating the verification results in test report. " + e.getMessage(), e);
        }
    }

    /**
     * Attach Failed Verification logs and Screenshots in the respective testcase in Extent Report (For Soft Assertions)
     *
     * @param description - verification message or any description to log
     */
    public static void addFailedVerificationLogs(String description) {
        try {
            // append screenshot of the failed verification step
            String fileName = SCREENSHOT_RELATIVE_PATH + ScreenshotUtil.takeScreenshot("failed_verification");
            verifications.get().log(Status.FAIL, String.format("<code>%s</code><br>", description), MediaEntityBuilder.createScreenCaptureFromPath(fileName).build());
            // add log to the step logs
            String space = methodPosition(METHOD_POSITION.get());
            extentTestSteps.get().log(Status.FAIL, String.format("%s<code style='color:#FF0000'>%s</code><br>%s", space, description, space), MediaEntityBuilder.createScreenCaptureFromPath(fileName).build());
        } catch (Exception e) {
            log.error("Error When updating the verification results in test report. " + e.getMessage(), e);
        }
    }


    /**
     * Add logs from required point of Test Case to the report
     * (add to any point in the Test Case to log)
     *
     * @param description required description to log
     * @param screenshot  [true] to take the screenshot at given point
     */
    public static void addTestInformation(String description, boolean screenshot) {
        if (screenshot) {
            try {
                // append screenshot to the failed step
                String fileName = SCREENSHOT_RELATIVE_PATH + ScreenshotUtil.takeScreenshot("test_screenshot");
                extentTestSteps.get().log(Status.INFO, String.format("[Test log] <code>%s</code></br>", description), MediaEntityBuilder.createScreenCaptureFromPath(fileName).build());
            } catch (Exception e) {
                log.error("Error When updating results in test report. " + e.getMessage(), e);
            }
        } else {
            extentTestSteps.get().log(Status.INFO, String.format("[Test log] <code>%s</code>", description));
        }
    }

    /**
     * Add logs to the Top of the Test Case
     *
     * @param description required description to log
     */
    public static void addTestCaseInformation(String description) {
        try {
            extentTest.get().log(Status.INFO, String.format("[Test log] <code>%s</code>", description));
        } catch (Exception e) {
            log.error("Error When updating results in test report. " + e.getMessage(), e);
        }
    }

    /**
     * Assign Category for the test case as required
     *
     * @param category - required categories to assign
     */
    public static void assignCategory(String... category) {
        extentTest.get().assignCategory(category);
    }

    /**
     * Generate number of spaces respective to the method position
     *
     * @param count - no of space required
     * @return String (html space tags)
     */
    public static String methodPosition(int count) {
        String space = "";
        String tab = "&emsp;&emsp;&emsp;&emsp;";
        if (count > 0) {
            space = StringUtils.repeat(tab, count);
        }
        return space;
    }
}
