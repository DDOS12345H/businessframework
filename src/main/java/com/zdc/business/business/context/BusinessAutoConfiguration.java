package com.zdc.business.business.context;

import com.zdc.business.business.util.SpringUtil;
import com.zdc.business.business.util.StringUtil;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;

/**
 * @Author：猪大肠
 * @Package：com.zdc.business.business.context
 * @Date：2023/5/10 21:37
 * @Wechat：DDOS12345H
 */
@AutoConfiguration
public class BusinessAutoConfiguration {

    @Bean(name = "adapterBContext")
    @ConditionalOnMissingBean(name = "adapterBContext")
    public AdapterBContext registerAdapterBContext(){
        return new AdapterBContext();
    }
    @Bean(name = "chainsBContext")
    @ConditionalOnMissingBean(name = "chainsBContext")
    public ChainsBContext registerChainsBContext(){
        return new ChainsBContext();
    }
    @Bean(name = "intecepterBContext")
    @ConditionalOnMissingBean(name = "intecepterBContext")
    public IntecepterBContext registerIntecepterBContext(){
        return new IntecepterBContext();
    }
    @Bean(name = "strategyBContext")
    @ConditionalOnMissingBean(name = "strategyBContext")
    public StrategyBContext registerStrategyBContext(){
        return new StrategyBContext();
    }
    @Bean
    public SpringUtil registerSpringEnviroment(){
        return new SpringUtil();
    }
}
