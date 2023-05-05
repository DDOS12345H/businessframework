package com.zdc.business.business.factory.constant;

public  enum AdapterEnumBFactory {
    ADAPTER_GLOBAL_EXCEPTION_TYPE_NAME("default","全局异常适配器类型"),
    ;
    private final String code;
    private final String description;
    AdapterEnumBFactory(String code,String description) {
        this.code=code;
        this.description=description;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

}
