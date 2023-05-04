package com.zdc.business.business.factory.constant;

public  enum AdapterEnumBFactory {
    ADAPTER_DEFAULT_TYPE_NAME("default","默认适配器名称");
    ;
    private final String type;
    private final String description;
    AdapterEnumBFactory(String type,String description) {
        this.type=type;
        this.description=description;
    }

    public String getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

}
