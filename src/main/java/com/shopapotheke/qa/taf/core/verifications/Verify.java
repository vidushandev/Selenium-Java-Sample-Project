package com.shopapotheke.qa.taf.core.verifications;

import com.shopapotheke.qa.taf.core.exceptions.AssertionException;
import com.shopapotheke.qa.taf.core.reporter.ReportManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

/**
 * Verification methods are wrapped to take screenshots and proper error reporting
 */
public class Verify {
    private static final Logger log = LogManager.getLogger(Verify.class);
    private final String VERIFY_EQUALS_SUCCESS_MESSAGE = "[Verify: SUCCESS] Expected and Actual values Matched. (Expected: '%s')";
    private final String VERIFY_EQUALS_FAIL_MESSAGE = "[Verify: FAIL] Expected and Actual values NOT Matched. (Expected: '%s', Actual: '%s')";


    /**
     * Asserts that two Strings are equal. If they are not, an AssertionException, with the given message,
     * is thrown.
     *
     * @param actual         the actual value
     * @param expected       the expected value
     * @param failureMessage the assertion error message
     * @throws AssertionException if values are not equal
     */
    public void verifyEquals(String actual, String expected, String failureMessage) throws AssertionException {
        try {
            if (failureMessage == null) {
                Assert.assertEquals(actual, expected);
            } else {
                Assert.assertEquals(actual, expected, failureMessage);
            }
            log.debug(String.format(VERIFY_EQUALS_SUCCESS_MESSAGE, expected));
            ReportManager.addSuccessVerificationLogs(String.format("[verifyEquals]: %s, [expected value]: '%s'", getCurrentExecutingMethodName(), expected));
        } catch (AssertionError e) {
            throw new AssertionException(VERIFY_EQUALS_FAIL_MESSAGE + e.getMessage(), e);
        }
    }


    /**
     * Asserts that a condition is true. If it isn't, an AssertionException, with the given message, is
     * thrown.
     *
     * @param condition      the condition to evaluate
     * @param failureMessage the assertion error message
     */
    public void verifyTrue(Boolean condition, String failureMessage) throws AssertionException {
        try {
            if (failureMessage == null) {
                Assert.assertTrue(condition);
            } else {
                Assert.assertTrue(condition, failureMessage);
            }
            log.debug(String.format(VERIFY_EQUALS_SUCCESS_MESSAGE, true));
            ReportManager.addSuccessVerificationLogs(String.format("[verifyTrue]: %s, [expected value]: 'true'", getCurrentExecutingMethodName()));
        } catch (AssertionError e) {
            throw new AssertionException(VERIFY_EQUALS_FAIL_MESSAGE + e.getMessage(), e);
        }
    }


    /**
     * @return Return current executing method name
     */
    private String getCurrentExecutingMethodName() {
        return Thread.currentThread().getStackTrace()[3].getMethodName();
    }

}
