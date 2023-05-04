package com.zdc.business.business.wrapper;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BExceptionWrapper<T> {
    private Exception exception;
    private T context;
}
