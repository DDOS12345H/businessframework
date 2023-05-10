package com.zdc.business.business.context;

import com.zdc.business.business.factory.AbstractBFactory;
import com.zdc.business.business.factory.ChainsBFactory;
import com.zdc.business.business.factory.IChainsEnumBFactory;
import com.zdc.business.business.handle.chains.AbstractChainHandle;
import com.zdc.business.business.util.AssertUtil;
import com.zdc.business.business.util.ExceptionUtil;
import com.zdc.business.business.util.StringUtil;
import org.springframework.stereotype.Component;

import java.util.*;

public class ChainsBContext extends AbstractBContext<AbstractChainHandle> {


    @Override
    public void register(AbstractChainHandle handle) {
        //获取处理器属性名称、优先级等配置
        IChainsEnumBFactory type = handle.getType();
        AssertUtil.notNull(type,"适配器配置信息不能为空");
        Integer priorityOrder = type.getPriorityOrder();
        String adapterType = type.getType();
        AbstractBFactory abstractBFactory = factoryMap.get(adapterType);
        if (Objects.isNull(abstractBFactory)){
            abstractBFactory=new ChainsBFactory(new TreeMap<Integer, AbstractChainHandle>(new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    return (int)o1-02;
                }
            }));
            factoryMap.put(adapterType,abstractBFactory);
        }
        abstractBFactory.register(handle, Arrays.asList(abstractBFactory.get().size()+priorityOrder));

    }

    @Override
    public <R> R invoke(String handleType, String handleName, Object context, Class<R> resp) {
        ChainsBFactory<AbstractChainHandle> handleFactory = (ChainsBFactory) super.getHandleFactory(handleType);
        AssertUtil.notNull(handleFactory,"获取不到类型为“{}”的责任链工厂",handleType);
        if (StringUtil.isEmpty(handleName)){
            return (R)handleFactory.getHeadHandle().execute(context);
        }
        return (R)getHandleOfChains(handleFactory,handleName).execute(context);
    }

    /**
     * 指定处理器开始执行
     * @param handleType
     * @param context
     * @return
     */
    public <R> R execute(String handleType, String handleName,  Object context, Class<R> resp) {
        AssertUtil.notNull(handleType,"处理器类型参数不能为空");
       return (R)this.invoke(handleType,handleName,context,Integer.class);
    }
    /**
     * 从头节点开始执行
     * @param handleType
     * @param context
     * @return
     */
    public <R>R start(String handleType,  Object context, Class<R> resp) {
        AssertUtil.notNull(handleType,"处理器类型参数不能为空");
        return (R)this.invoke(handleType,null,context,Integer.class);
    }

    /**
     * 获取处理器集
     */
    public List<AbstractChainHandle> getHandles(String type){
        AbstractBFactory handleFactory = super.getHandleFactory(type);
        AssertUtil.notNull(handleFactory,"查询不到类型为“{}”的责任链工厂",type);
        Collection handles = handleFactory.get();
        AssertUtil.notNull(handleFactory,"查询不到类型为“{}”的责任链处理器集合",type);

        return new ArrayList<>(handles);
    }

    /**
     * 将工厂中的处理器进行链化
     */
    public void initChains(){
        Set<String> keys = factoryMap.keySet();
        for (String factoryKey : keys) {
            //获取责任链工厂
            ChainsBFactory factory = (ChainsBFactory) factoryMap.get(factoryKey);
            List<AbstractChainHandle> handles = this.getHandles(factoryKey);
            for (int i = 0; i < handles.size(); i++) {
                if (i==handles.size()-1){
                    break;
                }
                handles.get(i).setNextNode(handles.get(i+1));
            }
            //设置头节点
            factory.setHeadHandle(handles.get(0));

        }

    }

    /**
     * 查找链表中的处理器
     * @param factoryMap
     */
    public AbstractChainHandle getHandleOfChains(ChainsBFactory<AbstractChainHandle> factoryMap,String handleName){
        AbstractChainHandle headHandle = factoryMap.getHeadHandle();
        AbstractChainHandle currentHandle=headHandle;
        while (currentHandle!=null){
            if (handleName.equals(currentHandle.getType().getName())){
                return currentHandle;
            }
            currentHandle=currentHandle.getNextNode();
        }
        ExceptionUtil.newRuntime("查询不到名称为{}的处理器","{}",handleName);
        return null;
    }

}
