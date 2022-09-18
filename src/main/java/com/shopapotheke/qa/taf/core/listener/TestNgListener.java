package com.shopapotheke.qa.taf.core.listener;

import com.shopapotheke.qa.taf.core.reporter.ReportManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

/**
 * Test listener for the Framework configured
 */
public class TestNgListener implements ITestListener {
    private static Logger log = LogManager.getLogger(TestNgListener.class);

    private String getCurrentTest(ITestResult result) {
        return result.getMethod().getQualifiedName();
    }

    @Override
    public void onTestStart(ITestResult result) {
        log.info("--- Test Start: " + getCurrentTest(result));
        ReportManager.initializeTest(result.getMethod().getQualifiedName(), result.getMethod().getDescription());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        log.info("--- Test Passed: " + getCurrentTest(result));
        ReportManager.updateTestStatus(result);
    }

    @Override
    public void onTestFailure(ITestResult result) {
        log.info("--- Test Failed: '" + getCurrentTest(result) + "' , Error: " + result.getThrowable().getMessage());
        ReportManager.updateTestStatus(result);
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        log.debug("--- Test Skipped: " + result.getThrowable().getMessage());
        log.debug("--- Test Skipped: ", result.getThrowable());
        log.info("--- Test Skipped: " + getCurrentTest(result));
        ReportManager.updateTestStatus(result);
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {

    }

    @Override
    public void onStart(ITestContext context) {
        log.info("---------------------------------------------------------------------------------");
        log.info("---------------------------- Starting Test Execution ----------------------------");
        log.info("---------------------------------------------------------------------------------");
        ReportManager.initializeReport();
    }

    @Override
    public void onFinish(ITestContext context) {
        log.info("---------------------------------------------------------------------------------");
        log.info("---------------------------- Completed Test Execution ---------------------------");
        log.info("---------------------------------------------------------------------------------");
        ReportManager.createReport();
    }
}
