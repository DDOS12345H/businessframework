package com.zdc.business.business.wrapper;


public class BExceptionWrapper<T> {
    private Exception exception;
    private T context;

    public Exception getException() {
        return exception;
    }

    public void setException(Exception exception) {
        this.exception = exception;
    }

    public T getContext() {
        return context;
    }

    public void setContext(T context) {
        this.context = context;
    }

    public BExceptionWrapper(Exception exception, T context) {
        this.exception = exception;
        this.context = context;
    }
}
