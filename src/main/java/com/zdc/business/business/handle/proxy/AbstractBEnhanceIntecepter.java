package com.zdc.business.business.handle.proxy;

import com.zdc.business.business.context.ChainsBContext;
import com.zdc.business.business.context.IntecepterBContext;
import com.zdc.business.business.handle.HandleBRegister;
import com.zdc.business.business.util.SpringUtil;
import com.zdc.business.business.wrapper.IntecepterProceedWrapper;

/**
 * @Author：猪大肠
 * @Package：com.zdc.business.business.wrapper
 * @Date：2023/5/7 21:18
 * @Wechat: DDOS12345H
 */
public abstract class AbstractBEnhanceIntecepter extends HandleBRegister implements BEnhanceIntecepter {

     @Override
     public void afterPropertiesSet() throws Exception {
          IntecepterBContext intecepterBContext = (IntecepterBContext) SpringUtil.getBean("intecepterBContext");
          intecepterBContext.register(this);
     }
}
