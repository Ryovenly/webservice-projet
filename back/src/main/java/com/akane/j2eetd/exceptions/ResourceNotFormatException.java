package com.akane.j2eetd.exceptions;

public class ResourceNotFormatException extends Exception {

    public ResourceNotFormatException(Class<?> resourceType, Object resourceId) {
        super(resourceType.getSimpleName() + " " + resourceId + " not good format");
    }

    public ResourceNotFormatException(Class<?> resourceType) {
        super(resourceType.getSimpleName() + " not good format");
    }

}
