package com.zdc.business.business.factory;


/**
 * @Author：猪大肠
 * @Package：com.zdc.business.business.factory
 * @Date：2023/5/10 21:04
 * @Wechat：DDOS12345H
 */
public enum ExceptionEnumBFactory implements IAdapterEnumBFactory {
    ADAPTER_GLOBAL_EXCEPTION_TYPE_NAME("0001",0,"全局异常处理器")
    ;
    private final String type;
    private final int priorityOrder;
    private final String description;

    public String getType() {
        return this.type;
    }

    public int getPriorityOrder() {
        return this.priorityOrder;
    }

    public String getDescription() {
        return this.description;
    }

    private ExceptionEnumBFactory(final String type, final int priorityOrder, final String description) {
        this.type = type;
        this.priorityOrder = priorityOrder;
        this.description = description;
    }
}
