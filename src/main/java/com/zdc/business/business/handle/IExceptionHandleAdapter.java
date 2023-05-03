package com.zdc.business.business.handle;

public interface IExceptionHandleAdapter {
    /**
     * 是否支持
     * @param handle
     * @param e
     * @return
     */
    public boolean isSupport(AbstractBHandle handle,Exception e);

    /**
     * 执行处理
     * @param handle
     * @param e
     * @return
     */
    public boolean execute(AbstractBHandle handle,Exception e);
}
