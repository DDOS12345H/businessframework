package com.zdc.business.business.context.listener;

import com.zdc.business.business.context.ChainsBContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class InitChainListener implements ApplicationListener<ContextRefreshedEvent> {

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        //Spring IOC容器刷新完成后，发布该事件
        //此时所有的Bean 已经完成实例化跟初始化操作
        ApplicationContext applicationContext = event.getApplicationContext();
        ChainsBContext chainsBContext = (ChainsBContext) applicationContext.getBean("ChainsBContext");
        chainsBContext.initChains();
    }
}