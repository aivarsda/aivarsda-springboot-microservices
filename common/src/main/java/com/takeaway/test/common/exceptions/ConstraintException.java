package com.takeaway.test.common.exceptions;

public class ConstraintException extends PersistenceException {
    public ConstraintException() {
    }

    public ConstraintException(Throwable cause) {
        super(cause);
    }

}
