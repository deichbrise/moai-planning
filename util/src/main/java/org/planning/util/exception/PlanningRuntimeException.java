package org.planning.util.exception;

/**
 * @author pascalstammer
 * @version 04.02.17.
 */
public class PlanningRuntimeException extends RuntimeException {

    private static final long serialVersionUID = -444513238410030299L;

    public PlanningRuntimeException() {
        super();
    }

    public PlanningRuntimeException(String message, Throwable cause,
                                  boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public PlanningRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public PlanningRuntimeException(String message) {
        super(message);
    }

    public PlanningRuntimeException(Throwable cause) {
        super(cause);
    }
}
