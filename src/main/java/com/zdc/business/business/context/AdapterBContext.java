package com.zdc.business.business.context;

import com.zdc.business.business.factory.AbstractBFactory;
import com.zdc.business.business.factory.CommonBFactory;
import com.zdc.business.business.factory.IAdapterEnumBFactory;
import com.zdc.business.business.handle.adapter.AbstractHandlesAdapter;
import com.zdc.business.business.util.AssertUtil;
import org.springframework.stereotype.Component;

import java.util.*;

public class AdapterBContext extends AbstractBContext<AbstractHandlesAdapter> {


    @Override
    public void register(AbstractHandlesAdapter handle) {

        //获取处理器属性名称
        IAdapterEnumBFactory type = handle.getType();
        AssertUtil.notNull(type,"适配器类型名称不能为空");
        Integer priorityOrder = type.getPriorityOrder();
        String adapterType = type.getType();
        AbstractBFactory abstractBFactory = factoryMap.get(adapterType);
        if (Objects.isNull(abstractBFactory)){
            abstractBFactory=new CommonBFactory(new TreeMap<Integer, AbstractHandlesAdapter>(new Comparator<Integer>() {
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
        List<AbstractHandlesAdapter> handles = this.getHandles(handleType);
        //记录被处理次数
        Object result=null;
        for (AbstractHandlesAdapter handle : handles) {
            if (handle.isSupport(context)){
                result=handle.execute(context);
                 if (handle.isInterrupt()){
                     break;
                 }
            }

        }
        return result!=null?(R)result:null;
    }

    /**
     * 执行入口
     * @param handleType
     * @param context
     * @return
     */
    public <R>R execute(String handleType,  Object context, Class<R> resp) {
        AssertUtil.notNull(handleType,"处理器类型参数不能为空");
       return this.invoke(handleType,null,context,resp);
    }
    /**
     * 获取处理器集
     */
    public List<AbstractHandlesAdapter> getHandles(String type){
        AbstractBFactory handleFactory = super.getHandleFactory(type);
        AssertUtil.notNull(handleFactory,"查询不到类型为“{}”的适配器工厂",type);
        Collection handles = handleFactory.get();
        AssertUtil.notNull(handleFactory,"查询不到类型为“{}”的适配器处理器集合",type);

        return new ArrayList<>(handles);
    }


}
