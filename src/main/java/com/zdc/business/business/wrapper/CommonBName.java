package com.zdc.business.business.wrapper;


import java.util.List;

public class CommonBName<T> {
    /**
     * 别名
     */
    private List<T> alias;
    /**
     * 业务类型
     */
    private T type;

    public CommonBName(T type, List<T> alias) {
        this.alias = alias;
        this.type = type;
    }

    public List<T> getAlias() {
        return alias;
    }

    public void setAlias(List<T> alias) {
        this.alias = alias;
    }

    public T getType() {
        return type;
    }

    public void setType(T type) {
        this.type = type;
    }
}
