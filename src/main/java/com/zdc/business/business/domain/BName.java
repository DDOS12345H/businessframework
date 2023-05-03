package com.zdc.business.business.domain;

import lombok.Data;

import java.util.List;

public class BName {
    /**
     * 别名
     */
    private List<String> alias;
    /**
     * 业务类型
     */
    private String type;

    public BName( String type,List<String> alias) {
        this.alias = alias;
        this.type = type;
    }

    public List<String> getAlias() {
        return alias;
    }

    public void setAlias(List<String> alias) {
        this.alias = alias;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
