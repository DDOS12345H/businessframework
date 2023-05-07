package com.zdc.business.business.test.chains;

import com.zdc.business.business.factory.IChainsEnumBFactory;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public  enum ChainsEnumBFactory implements IChainsEnumBFactory {
    CHAINS_DEFAULT_TYPE_1("default","1",1,"默认责任链处理器A"),
    CHAINS_DEFAULT_TYPE_2("default","2",2,"默认责任链处理器B"),
    ;
    //责任链 链条名称
    private  String type;
    //处理器名称
    private  String name;
    //处理器优先级
    private  int priorityOrder;
    //处理器功能描述
    private  String description;


}
