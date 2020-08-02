package com.takeaway.test.common.exceptions;

public class DuplicateResourceException extends PersistenceException {
    public DuplicateResourceException() {
    }

    public DuplicateResourceException(Throwable cause) {
        super(cause);
    }
}
