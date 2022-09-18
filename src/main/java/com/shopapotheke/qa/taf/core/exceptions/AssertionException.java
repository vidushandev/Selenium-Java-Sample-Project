package com.shopapotheke.qa.taf.core.exceptions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AssertionException extends FrameworkException {
    Logger log = LogManager.getLogger(AssertionException.class);

    public AssertionException(String message, Throwable e) {
        super(message);
        log.error(message, e);
    }



}
