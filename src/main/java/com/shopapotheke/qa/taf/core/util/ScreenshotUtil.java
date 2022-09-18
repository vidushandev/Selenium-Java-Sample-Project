package com.shopapotheke.qa.taf.core.util;

import com.shopapotheke.qa.taf.core.base.Session;
import com.shopapotheke.qa.taf.core.exceptions.FrameworkException;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;

public class ScreenshotUtil {
    private static Logger log = LogManager.getLogger(ScreenshotUtil.class);

    private static final String SCREENSHOT_DIR = "/target/test-output/screenshots/";
    private static final String SCREENSHOT_EXTENSION = ".png";

    /**
     * Takes Screenshot and return the file path
     *
     * @param name - required name to take the screenshot.
     * @return - Respective name of the screenshot (Eg. LoginTest_20200530171308)
     * @throws FrameworkException throws any IOExceptions
     */
    public static String takeScreenshot(String name) throws FrameworkException {
        // adding datetime stamp to the file name to avoid overriding the same file
        String fileName = name + "_" + DateTimeUtil.getCurrentTimeStampAsString() + SCREENSHOT_EXTENSION;
        String screenshot_dir = getScreenshotDir();
        String path = screenshot_dir + fileName;
        try {
            File scrFile = ((TakesScreenshot) Session.getWebDriver()).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile, new File(path));
        } catch (IOException e) {
            throw new FrameworkException("Error when taking the screenshot. Error: " + e.getMessage(), e);
        }
        log.info("screenshot taken: " + path);
        return fileName;
    }

    public static String getScreenshotDir() {
        return System.getProperty("user.dir") + SCREENSHOT_DIR;
    }
}
