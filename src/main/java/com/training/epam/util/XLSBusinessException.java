package com.training.epam.util;

public class XLSBusinessException extends Exception {
    public XLSBusinessException() {
    }

    public XLSBusinessException(String message) {
        super(message);
    }

    public XLSBusinessException(String message, Throwable cause) {
        super(message, cause);
    }

    public XLSBusinessException(Throwable cause) {
        super(cause);
    }

    public XLSBusinessException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
