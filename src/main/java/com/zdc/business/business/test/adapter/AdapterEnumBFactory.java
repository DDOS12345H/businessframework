package com.zdc.business.business.test.adapter;

import com.zdc.business.business.factory.IAdapterEnumBFactory;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public  enum AdapterEnumBFactory implements IAdapterEnumBFactory {
    ADAPTER_GLOBAL_EXCEPTION_TYPE_NAME("default_exception",10,"全局异常适配器类型"),
    ;
    private final String type;
    private final int priorityOrder;
    private final String description;



}
