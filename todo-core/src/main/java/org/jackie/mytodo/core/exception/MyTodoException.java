package org.jackie.mytodo.core.exception;

public class MyTodoException extends RuntimeException {

    public MyTodoException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
