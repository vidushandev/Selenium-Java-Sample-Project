package com.shopapotheke.qa.taf.core.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Global Parameters Required for the execution are handled through this Class
 */
public class Configurations {
    private static Logger log = LogManager.getLogger(Configurations.class);

    // TIMEOUTS
    public static int TIMEOUT_IMPLICIT_WAIT = 5;
    public static int TIMEOUT_PAGE_LOAD = 50;
    public static int TIMEOUT_GLOBAL_ELEMENT = 20;

    // Execution related information
    public static String TEST_URL = "https://www.shop-apotheke.com/nx/login/";
    public static String TEST_BROWSER = Browser.CHROME;


}
