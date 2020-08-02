package com.takeaway.test.common.exceptions;

public class ResourceNotFoundException extends PersistenceException {
    public ResourceNotFoundException() {
    }

    public ResourceNotFoundException(Throwable cause) {
        super(cause);
    }
}
