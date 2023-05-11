package com.zdc.business.business.handle.chains;

import com.zdc.business.business.context.ChainsBContext;
import com.zdc.business.business.factory.IChainsEnumBFactory;
import com.zdc.business.business.handle.HandleBRegister;
import com.zdc.business.business.util.SpringUtil;
import org.springframework.beans.factory.InitializingBean;

public abstract class AbstractChainHandle<T,R> extends HandleBRegister {
    private AbstractChainHandle<T,R> nextNode;
    //处理器名称，自动生成
    private String handleName;

    /**
     * 调用下个节点
     * @return
     */
    public  R executeNextNode(T context){
        if (nextNode==null){
            return null;
        }
        return getNextNode().execute(context);
    };

    /**
     * 执行处理
     * @return
     */
    public abstract R execute(T context);



    /**
     * 适配器配置
     * @throws Exception
     */
    public abstract IChainsEnumBFactory getType();

    @Override
    public void afterPropertiesSet() throws Exception {
        ChainsBContext chainsBContext = (ChainsBContext) SpringUtil.getBean("chainsBContext");
        chainsBContext.register(this);
    }

    public AbstractChainHandle<T,R> getNextNode() {
        return nextNode;
    }

    public void setNextNode(AbstractChainHandle<T,R> nextNode) {
        this.nextNode = nextNode;
    }

    public String getHandleName() {
        return handleName;
    }

    public void setHandleName(String handleName) {
        this.handleName = handleName;
    }
}
