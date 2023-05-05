package com.zdc.business.business.handle.chains;

import com.zdc.business.business.context.AdapterBContext;
import com.zdc.business.business.context.ChainsBContext;
import com.zdc.business.business.util.SpringUtil;
import org.springframework.beans.factory.InitializingBean;

public abstract class AbstractChainHandle<T,R> implements InitializingBean {
    private AbstractChainHandle nextNode;

    /**
     * 调用下个节点
     * @return
     */
    public abstract boolean nextNode(T context);

    /**
     * 执行处理
     * @return
     */
    public abstract R execute(T context);



    /**
     * 适配器类型
     * @throws Exception
     */
    public abstract String getType();

    @Override
    public void afterPropertiesSet() throws Exception {
        ChainsBContext chainsBContext = (ChainsBContext) SpringUtil.getBean("chainsBContext");
        chainsBContext.register(this);
    }

    public AbstractChainHandle getNextNode() {
        return nextNode;
    }

    public void setNextNode(AbstractChainHandle nextNode) {
        this.nextNode = nextNode;
    }
}
