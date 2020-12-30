package com.test.exception;

/**
 * @Created by:chenxu
 * @Created date:12/30/20 11:37 PM
 */
public class SpringCloudException extends RuntimeException {
    public SpringCloudException() {
        super();
    }

    public SpringCloudException(String message) {
        super(message);
    }

    public SpringCloudException(String message, Throwable cause) {
        super(message, cause);
    }

    public SpringCloudException(Throwable cause) {
        super(cause);
    }

    protected SpringCloudException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
