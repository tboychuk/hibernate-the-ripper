package ua.com.devoxx.util.jpa.exception;

public class JpaUtilException extends RuntimeException {
    private static final String DEFAULT_MESSAGE = "Error performing JPA operation. Transaction is rolled back";

    public JpaUtilException(Throwable cause) {
        super(DEFAULT_MESSAGE, cause);
    }
}
