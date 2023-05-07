package com.zdc.business.business.factory;

public  interface IAdapterEnumBFactory {
    /**
     * 所属类型
     * @return
     */
    public String getType();

    /**
     * 优先级
     * @return
     */
    public int getPriorityOrder();

    /**
     * 描述
     * @return
     */

    public String getDescription();

}
